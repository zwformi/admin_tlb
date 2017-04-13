package com.yunrer.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jdom.JDOMException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.mysql.jdbc.CallableStatement;
import com.yunrer.dao.OrderDao;
import com.yunrer.dao.ProductAttrTemplateDao;
import com.yunrer.dao.ProductBrandDao;
import com.yunrer.dao.ProductDao;
import com.yunrer.dao.ProductImageDao;
import com.yunrer.dao.ProductItemDao;
import com.yunrer.dao.ProductParamDao;
import com.yunrer.dao.ProductSkuDao;
import com.yunrer.dao.ProductTypeDao;
import com.yunrer.entity.OrderInfo;
import com.yunrer.entity.Product;
import com.yunrer.entity.ProductAttrTemplate;
import com.yunrer.entity.ProductBrand;
import com.yunrer.entity.ProductImage;
import com.yunrer.entity.ProductItem;
import com.yunrer.entity.ProductParam;
import com.yunrer.entity.ProductSku;
import com.yunrer.entity.ProductType;
import com.yunrer.util.BaseLogger;
import com.yunrer.util.SignUtil;
import com.yunrer.util.Test;
import com.yunrer.util.ZcyCommonUtil;
import com.yunrer.util.ZcyConfig;
import com.yunrer.util.ZcyConfig;
import com.yunrer.util.zcy.openapi.ZcyOpenClient;
import com.yunrer.util.zcy.openapi.ZcyOpenRequest;
import com.yunrer.util.zcy.openapi.http.HttpMethod;
import com.yunrer.util.zcy.openapi.zoss.model.ZcyResponse;

@Service("ZcyService")
public class ZcyService extends BaseLogger {

		@Resource
		private ProductDao productDao;
		
		@Resource
		private ProductItemDao itemDao;
		
		@Resource
		private ProductBrandDao brandDao;
		
		@Resource
		private ProductParamDao paramDao;
		
		@Resource
		private ProductImageDao imageDao;
		
		@Resource
		private ProductSkuDao skuDao;
		
		@Resource
		private ProductAttrTemplateDao tempDao;
		
		@Resource
		private ProductTypeDao productTypeDao;
		
		 private static Logger UpdateLogger = Logger.getLogger("UpdateTypesandTemplate"); 
		
	     /**
	      * 政采云商品同步
	      * @Title: syncProduct 
	      * @Description:
	      * @param request
	      * @param response
	      * @return String         
	      * @throws
	      */
		public String syncProduct(HttpServletRequest request,HttpServletResponse response){
			String uri = ZcyConfig.instance().NAME_SPACE+ZcyConfig.instance().API_GATEWAY;
			
			ZcyOpenRequest zcyOpenRequest = new ZcyOpenRequest(ZcyConfig.instance().APP_KEY,ZcyConfig.instance().SECRET,ZcyConfig.instance().API_GATEWAY);
	    	zcyOpenRequest.setUri(uri);
	        zcyOpenRequest.setMethod(HttpMethod.POST);

	        /*组装Body参数*/
	        Map<String, Object> bodyMap = new HashMap<String, Object>();
	        
	        String msg = "";
	        String need_success = "";
	        String no_need = "";
	        String need_warn = "";
	        //获取要同步的商品id
	        String[] id_str = request.getParameter("id").split(",");
	        Integer id = 0;
    		//获取要同步的product信息
	        Product product = new Product();
	        for(int i = 0;i<id_str.length;i++){
	        	if(id_str[i]!=null&&id_str[i]!=""){
	        		 id = Integer.parseInt(id_str[i]);
	        		//获取要同步的product信息
	        		 product = productDao.queryProduct(id);
	        		//判断是不是需要同步
	        		 if(product.getIs_zcy()==0){//需要添加
	        			 String message = addProduct(product,0);
	        			 if(!message.equals("")){
	        				 msg +="["+ message.replace("，", "")+"]<br>";
	        			 }else{
	        				 need_success += product.getId()+"，";
	        			 }
	        		 }else if(product.getIs_zcy()==1||product.getIs_zcy()==5||product.getIs_zcy()==7){	//需要修改
	        			 String message = addProduct(product,1);
	        			 if(!message.equals("")){
	        				 msg +="["+ message.replace("，", "")+"]<br>";
	        			 }else{
	        				 need_success += product.getId()+"，";
	        			 }
	        		 }else if(product.getIs_zcy()==2||product.getIs_zcy()==3||product.getIs_zcy()==4||product.getIs_zcy()==6||product.getIs_zcy()==8){	//已同步
	        			 no_need += product.getId()+"，";
	        		 }else{	//有问题的
	        			 need_warn += product.getId()+"，";
	        		 }
	        	}
	        }
	        //拼装msg
	        String re = "";
	        if(need_success!=""){
	        	re += "[商品-"+need_success.substring(0, need_success.length()-1)+"同步成功]<br>";
	        }
	        if(no_need!=""){
	        	re += "[商品-"+no_need.substring(0, no_need.length()-1)+"无需再次同步]<br>";
	        }
	        if(need_warn!=""){
	        	
	        	re += "[商品-"+no_need.substring(0, no_need.length()-1)+"状态有误，请核对]<br>";
	        }
	        re += msg;
	        return re;
			
		} 

		public String updateState(HttpServletRequest request,HttpServletResponse response){
			String uri = "/open/zcy.item.update.listing";
			ZcyOpenRequest zcyOpenRequest = new ZcyOpenRequest(ZcyConfig.instance().APP_KEY,ZcyConfig.instance().SECRET,ZcyConfig.instance().API_GATEWAY);
		        zcyOpenRequest.setUri(uri);
		        zcyOpenRequest.setMethod(HttpMethod.POST);
		        String result_msg = "";   
		        String msg = "";
		        Map<String, Object> bodyMap = new HashMap<String, Object>();
		      //获取要同步的商品id
		        String[] id_str = request.getParameter("id").split(",");
		        for(int i = 0;i<id_str.length;i++){
		        	if(id_str[i]!=null&&id_str[i]!=""){
		        			int product_id = Integer.parseInt(id_str[i]);
		        			JSONObject json = new JSONObject().put("itemCode", "tulingbuy-spu-"+product_id);
		        			String _result = ZcyCommonUtil.doPost(json, "zcy.item.detail.get");
		        			JSONObject resultJson = new JSONObject(_result);
		        			int _status = 0;
		        			if(resultJson.has("data_response"))
		        				resultJson = resultJson.getJSONObject("data_response");
		        			if(resultJson.has("item"))
		        				resultJson = resultJson.getJSONObject("item");
		        			if(resultJson.has("status"))
		        				_status = resultJson.getInt("status");
		        				
		        			if(_status==1 || _status ==2)
		        			{
		        				result_msg+="商品-"+product_id+" 已"+(_status==1?"上架":"审核中")+"，请勿重复操作<br> ";
		        				continue;
		        			}
		        			int status = Integer.parseInt(request.getParameter("status")==""?"0":request.getParameter("status"));
		        			JSONObject o = new JSONObject();
		        			o.put("itemCode", "tulingbuy-spu-"+product_id);
		        			o.put("status", status);
		        			bodyMap.put("_data_", o.toString());
		        			zcyOpenRequest.setParamMap(bodyMap);
		        			/*发送http request*/
		        			String result = null; 
		        			try {
		        		        
		        				result = ZcyOpenClient.excute(zcyOpenRequest);
		        				JSONObject jsonResult = new JSONObject(result);
		        		
		        				if((Boolean) jsonResult.get("success")){
		        					msg="商品-"+product_id+"更新状态成功"+"<br> ";
		        					if(status==-1){
		        						productDao.updateProduct(product_id, "is_zcy", 5);
	
		        					}else if(status==1){
		        						productDao.updateProduct(product_id, "is_zcy", 3);
		        					}
		        				}else{
		        					if(jsonResult.has("error")){
		        						msg="商品-"+product_id+jsonResult.get("error")+"<br> ";
		        					}else{
		        						msg="商品-"+product_id+"更新状态失败"+"<br> ";
		        					}
		        				}
		        				result_msg += msg;
		        			} catch (Exception e1) {
		        				// TODO Auto-generated catch block
		        				e1.printStackTrace();
		        			}
		        		}
		        		if(result_msg!=""){
		        			result_msg = result_msg.substring(0, result_msg.length()-1);
		        		}
		        		}
		        		return result_msg;
		}
		
		/**
		 * 获取zcy的商品数据
		 * @Title: getProductData 
		 * @Description:
		 * @param request
		 * @param response
		 * @return String         
		 * @throws
		 */
		public Map<String,Object> getProductData(String id_str){
			Map<String,Object> map = new HashMap<String,Object>();
			String uri = "/open/zcy.item.detail.get";
			ZcyOpenRequest zcyOpenRequest = new ZcyOpenRequest(ZcyConfig.instance().APP_KEY,ZcyConfig.instance().SECRET,ZcyConfig.instance().API_GATEWAY);
	        zcyOpenRequest.setUri(uri);
	        zcyOpenRequest.setMethod(HttpMethod.POST);
	        
			String[] ids = id_str.split(",");
			for(String id :ids){
			String itemCode = "tulingbuy-spu-"+id;
			JSONObject o = new JSONObject();
			o.put("itemCode", itemCode);
			Map<String, Object> bodyMap = new HashMap<String, Object>();
		        bodyMap.put("_data_", o.toString());
		        zcyOpenRequest.setParamMap(bodyMap);
		        /*发送http request*/
		        String result = null;
				try {
					result = ZcyOpenClient.excute(zcyOpenRequest);
					System.out.println(result);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			Map<String,Object> map_one = productAnalysis(result);
			if(Integer.parseInt(map_one.get("resultCode").toString())==1){
				productDao.updateProduct(Integer.parseInt(id), "is_zcysj", map_one.get("status"));
				JSONObject result_o = new JSONObject();
				result_o.put("status", map_one.get("status"));
				result_o.put("id", id);
//				map.put(key, value)
			}
			}
			return map;
		}
		
		/**
		 * 获取商品状态
		 * @Title: getProductStatus 
		 * @Description:
		 * @param id_str
		 * @return Map<String,Object>         
		 * @throws
		 */
		public Map<String,Object> getProductStatus(String id_str){
			Map<String,Object> map = new HashMap<String,Object>();
			int flag = 1;
			String msg = "";
			String error_msg = "";
			String right_msg = "";
			String que_msg = "";
			String uri = "/open/zcy.item.detail.get";
			ZcyOpenRequest zcyOpenRequest = new ZcyOpenRequest(ZcyConfig.instance().APP_KEY,ZcyConfig.instance().SECRET,ZcyConfig.instance().API_GATEWAY);
	        zcyOpenRequest.setUri(uri);
	        zcyOpenRequest.setMethod(HttpMethod.POST);
	        
			String[] ids = id_str.split(",");
			for(String id :ids){
			String itemCode = "tulingbuy-spu-"+id;
			JSONObject o = new JSONObject();
			o.put("itemCode", itemCode);
			Map<String, Object> bodyMap = new HashMap<String, Object>();
		        bodyMap.put("_data_", o.toString());
		        zcyOpenRequest.setParamMap(bodyMap);
		        /*发送http request*/
		        String result = null;
				try {
					result = ZcyOpenClient.excute(zcyOpenRequest);
					System.out.println(result);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			Map<String,Object> map_one = productAnalysis(result);
			if(Integer.parseInt(map_one.get("resultCode").toString())==1){
				//成功的情况下，填入政采云状态值
				int status = Integer.parseInt(map_one.get("status").toString());
				if(status==0){	//已同步：0->2
					productDao.updateProduct(Integer.parseInt(id), "is_zcy", 2);
					right_msg = right_msg + id + ",";
				}else if(status==2){	//已申请上架：2->3
					productDao.updateProduct(Integer.parseInt(id), "is_zcy", 3);
					right_msg = right_msg + id + ",";
				}else if(status==1){	//已上架：1->4
					productDao.updateProduct(Integer.parseInt(id), "is_zcy", 4);
					right_msg = right_msg + id + ",";
				}else if(status==-1){	//已下架：-1->5
					productDao.updateProduct(Integer.parseInt(id), "is_zcy", 5);
					right_msg = right_msg + id + ",";
				}else if(status==-2){	//已冻结：-2->6
					productDao.updateProduct(Integer.parseInt(id), "is_zcy", 6);
					right_msg = right_msg + id + ",";
				}else if(status==-4){	//审核不通过：-4->7
					productDao.updateProduct(Integer.parseInt(id), "is_zcy", 7);
					right_msg = right_msg + id + ",";
				}else if(status==5){	//解冻审核中：5->8
					productDao.updateProduct(Integer.parseInt(id), "is_zcy", 8);
					right_msg = right_msg + id + ",";
				}else{		//未识别状态
					que_msg += "商品-" + id +"获取状态为： " + status +" ,不属于已知状态！<br/>"; 
					flag = 0;
				}
			}else{
				flag = 0;
				error_msg = error_msg + id + ",";
			}
			}
			if(!"".equals(que_msg)){
				msg += que_msg;
			}
			if(!"".equals(error_msg)){
				error_msg = "商品-" + error_msg.substring(0,error_msg.length()-1) +"同步状态失败!<br/>";
				msg += error_msg;
			}
			if(!"".equals(right_msg)){
				right_msg = "商品-" + right_msg.substring(0,right_msg.length()-1) +"同步状态成功!<br/>";
				msg += right_msg;
			}
			map.put("resultCode", flag);
			map.put("resultMsg", msg);
			return map;
		}
		
		
		
		private Map<String,Object> productAnalysis(String result){
			Map<String,Object> map = new HashMap<String,Object>();
			try{
			JSONObject o = new JSONObject(result);
			if(o.has("error_response")){
				map.put("resultCode", 0);
				map.put("resultMsg", "查询失败");
			}else if(o.has("data_response")){
				JSONObject item = new JSONObject(o.get("data_response").toString()).getJSONObject("item");
				int status = Integer.parseInt((item.get("status").toString()));
				String itemCode = item.getString("itemCode").replace("tulingbuy-spu-", "");
				
				map.put("resultCode", 1);
				map.put("status", status);
			}else{
				map.put("resultCode", 0);
				map.put("resultMsg", "查询失败");
			}
			}catch(Exception e){
				e.getMessage();
				e.getStackTrace();
			}
			
				return map;
			}
		
	
		
		public String getCategoryData(HttpServletRequest request,HttpServletResponse response){
			String uri = "/open/zcy.category.attrs.get";
			String p = "{\"categoryId\": 19}";
			ZcyOpenRequest zcyOpenRequest = new ZcyOpenRequest(ZcyConfig.instance().APP_KEY,ZcyConfig.instance().SECRET,ZcyConfig.instance().API_GATEWAY);
		        zcyOpenRequest.setUri(uri);
		        zcyOpenRequest.setMethod(HttpMethod.POST);
		        Map<String, Object> bodyMap = new HashMap<String, Object>();
		        JSONObject o = new JSONObject();
		        String item = "icode12213123";
		        o.put("itemCode", item);
		        bodyMap.put("_data_", p);
		        zcyOpenRequest.setParamMap(bodyMap);
		        /*发送http request*/
		        String result = null;
				try {
					result = ZcyOpenClient.excute(zcyOpenRequest);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			return "1";
		}
		
		public String serachForProduct(HttpServletRequest request,HttpServletResponse response){
			String uri = "/open/zcy.item.update.listing";

		        ZcyOpenRequest zcyOpenRequest = new ZcyOpenRequest(ZcyConfig.instance().APP_KEY,ZcyConfig.instance().SECRET,ZcyConfig.instance().API_GATEWAY);
		        zcyOpenRequest.setUri(uri);
		        zcyOpenRequest.setMethod(HttpMethod.POST);
		        Map<String, Object> bodyMap = new HashMap<String, Object>();
		        zcyOpenRequest.setParamMap(bodyMap);
		        /*发送http request*/
		        String result = null;
				try {
					result = ZcyOpenClient.excute(zcyOpenRequest);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			
			 return result;
			
		} 
		
		/**
		 * 拼装接口所需json数据
		 * @Title: addProduct 
		 * @Description:
		 * @param id
		 * @param type
		 * @return String         
		 * @throws
		 */
		public String addProduct(Product product,int type){
			String msg = "";
			int flag = 0;
			
			String uri = "";
			if(type==0){
				uri = "/open/zcy.item.add";
			}else if(type==1){
				uri = "/open/zcy.item.update.bycode";
			}
			
	        ZcyOpenRequest zcyOpenRequest = new ZcyOpenRequest(ZcyConfig.instance().APP_KEY,ZcyConfig.instance().SECRET,ZcyConfig.instance().API_GATEWAY);
	        zcyOpenRequest.setUri(uri);
	        zcyOpenRequest.setMethod(HttpMethod.POST);

	        /*组装Body参数*/
	        Map<String, Object> bodyMap = new HashMap<String, Object>();
	        
			
			//拼装jsonObject
			//1.item
			 JSONObject item = new JSONObject();
			 item.put("selfPlatformLink","http://www.tulingbuy.com/buy/buy_show.json?ID="+product.getId());
			 item.put("needInstall", product.getNeedInstall());
			 String type_cut = product.getType_ids().split(",")[3];
			 if(type_cut==""){
				 flag=1;
				 msg+=product.getId()+"所属类目出错;";
			 }else{
				 //通过三级类id查找zcyid
				
				 item.put("categoryId",type_cut);
			 }
			 item.put("name", product.getName());
			 item.put("itemCode", "tulingbuy-spu-"+product.getId().toString());
			 item.put("mainImage","http://img.tulingbuy.com"+product.getImg_url());		
			 ProductBrand brand = brandDao.queryProductBrand(product.getBrand_id());
			 item.put("brandName", brand.getName());
//			 item.put("spuCode","tulingbuy-spu-"+product.getId().toString());
			 item.put("specification",product.getXh()==""?"无":product.getXh());
			 //这里设置一个默认值，如果未填写的，全部设置为浙江-杭州-拱墅
			 item.put("limit", product.getLimit()==null?"0":product.getLimit());	//0是境内
			 item.put("countryId",product.getCountryId()==null||product.getCountryId().isEmpty()?"228":product.getCountryId());
			 item.put("provinceId",product.getProvinceId()==null?"330000":product.getProvinceId());
			 item.put("cityId",product.getCityId()==null?"330100":product.getCityId());
			 item.put("regionId",product.getRegionId()==null?"330105":product.getRegionId());
			 item.put("firm",product.getFirm());
			 
			 //2.itemDetail
			 JSONObject itemDetail = new JSONObject();
			 
			 List<ProductImage> list = imageDao.queryProductImageList(product.getId(), -1);
			 ArrayList<String> images = new ArrayList<String>();

			 int flag_image = 0;
			for(int i=0;i<list.size();i++){
				if(list.get(i).getcoverImage()!=1){
				images.add("http://img.tulingbuy.com"+list.get(i).getImg_url());
				flag_image ++;
				if(flag_image>3) continue;

				}
			}	
			 //图片上传接口，4张附图
			 itemDetail.put("images", images);

			 String content = product.getContent().replace(" src=\"/upload/", " src=\"http://img.tulingbuy.com/upload/");
			 itemDetail.put("detail",content);
			 
			 //3.otherAttributes
			 ArrayList<JSONObject> attributes = new ArrayList<JSONObject>();
			 List attr_list = productDao.queryProductAttrs(product.getId());
			 if(attr_list!=null && attr_list.size()>0){
				 
				 for(int i=0;i<attr_list.size();i++){
					 JSONObject otherAttribute = new JSONObject();
					 Map<String,String> map =  (Map<String,String>)attr_list.get(i);
					 if(map.get("attrVal")!=null && map.get("attrVal").length()>0){
						 
						 otherAttribute.put("attrVal", map.get("attrVal"));
						 otherAttribute.put("attrKey", map.get("attrKey"));
						 attributes.add(otherAttribute);
						 
					 }
				 }
				 
			 }


			 //5.skuAttributes
			 ArrayList<JSONObject> skuAttributes = new ArrayList<JSONObject>();

			 	//分4次查询sku的Attr结果
			 for(int i=0;i<4;i++){
				 List list_attr = skuDao.queryProductSkuForSkuAttrs(product.getId(), i+1);
				 if(list_attr!=null){
					 for(int j=0;j<list_attr.size();j++){
						 Map map = (Map)list_attr.get(j);
						 JSONObject skuAttribute = new JSONObject();
						 if(map.get("attrVal")!=null&&!"".equals(map.get("attrVal"))&&map.get("attrName")!=null&&!"".equals(map.get("attrName"))){
							 skuAttribute.put("attrVal", map.get("attrVal"));
							 skuAttribute.put("attrKey",map.get("attrName"));
							 skuAttributes.add(skuAttribute);
						 }
						
						
					 }
				 }
			 }
			 List list_candidate = tempDao.getSukAttr(product.getId());
			 if(list_candidate!=null){
				 for(int j=0;j<list_candidate.size();j++){
					 Map map = (Map)list_candidate.get(j);
					 JSONObject skuAttribute = new JSONObject();
					 if(map.get("attrVal")!=null&&!"".equals(map.get("attrVal"))&&map.get("attrKey")!=null&&!"".equals(map.get("attrKey"))){
						 skuAttribute.put("attrVal", map.get("attrVal"));
						 skuAttribute.put("attrKey",map.get("attrKey"));
						 skuAttributes.add(skuAttribute);
					 }
					
					
				 }
			 }

			 //4.sku
			 ArrayList<JSONObject> skuGroup = new ArrayList<JSONObject>();
			 
			 List<ProductSku> skuList = skuDao.queryProductSkuListById(product.getId());

				 for(int i=0;i<skuList.size();i++){
					 JSONObject skus = new JSONObject();
					 ProductSku sku1 = skuList.get(i);
					 skus.put("price", new BigDecimal(sku1.getPrice().toString()).multiply(new BigDecimal(100)));
					 skus.put("originPrice", new BigDecimal(sku1.getOriginPrice().toString()).multiply(new BigDecimal(100)));
					 skus.put("platformPrice", new BigDecimal(sku1.getPlatformPrice().toString()).multiply(new BigDecimal(100)));
					 skus.put("quantity", sku1.getStocks());
					 skus.put("skuCode", "tulingbuy-sku-"+sku1.getId().toString());
					 skus.put("skuTemplateCode", "无");
					 skus.put("warehouseCode", "DEFAULT_1");
					 
					 JSONObject attrs = new JSONObject();
					 if(sku1.getAttrName1()!=null&&!"".equals(sku1.getAttrName1())){
						 attrs.put(sku1.getAttrName1(),sku1.getAttrVal1());
					 }
					 if(sku1.getAttrName2()!=null&&!"".equals(sku1.getAttrName2())){
						 attrs.put(sku1.getAttrName2(),sku1.getAttrVal2());
					 }
					 if(sku1.getAttrName3()!=null&&!"".equals(sku1.getAttrName3())){
						 attrs.put(sku1.getAttrName3(),sku1.getAttrVal3());
					 }
					 if(sku1.getAttrName4()!=null&&!"".equals(sku1.getAttrName4())){
						 attrs.put(sku1.getAttrName4(),sku1.getAttrVal4());
					 }
					 if(sku1.getAttrName5()!=null&&!"".equals(sku1.getAttrName5())){
						 attrs.put(sku1.getAttrName5(),sku1.getAttrVal5());
					 }
					 if(sku1.getAttrName6()!=null&&!"".equals(sku1.getAttrName6())){
						 attrs.put(sku1.getAttrName6(),sku1.getAttrVal6());
					 }
					 if(sku1.getAttrName7()!=null&&!"".equals(sku1.getAttrName7())){
						 attrs.put(sku1.getAttrName7(),sku1.getAttrVal7());
					 }
					 if(sku1.getAttrName8()!=null&&!"".equals(sku1.getAttrName8())){
						 attrs.put(sku1.getAttrName8(),sku1.getAttrVal8());
					 }
					 
					 skus.put("attrs",attrs);
					 skuGroup.add(skus);
				 }

			 //拼装完整data
			 JSONObject data = new JSONObject();
			 data.put("item", item);
			 data.put("itemDetail", itemDetail);
			 data.put("otherAttributes", attributes);
			 data.put("skuAttributes", skuAttributes);
			 data.put("skus", skuGroup);
			 
			 JSONObject d = new JSONObject();
			 d.put("data", data);

			 bodyMap.put("_data_", d.toString());
//			 bodyMap.put("_data_", "{\"data\":{\"item\":{\"brandName\":\"Apple\",\"itemCode\":\"tulingbuy-spu-19654\",\"specification\":\"test_20170118\",\"cityId\":\"410100\",\"provinceId\":\"410000\",\"countryId\":\"0\",\"firm\":\"1\",\"mainImage\":\"http://img.tulingbuy.comnull\",\"selfPlatformLink\":\"http://www.tulingbuy.com/buy/buy_show.json?ID=19654\",\"regionId\":\"410101\",\"name\":\"test_20170118\",\"limit\":\"0\",\"categoryId\":\"948\",\"needInstall\":\"0\"},\"skus\":[{\"quantity\":12,\"price\":0,\"skuTemplateCode\":\"无\",\"originPrice\":1200,\"platformPrice\":1200,\"skuCode\":\"tulingbuy-sku-1200\",\"warehouseCode\":\"DEFAULT_1\",\"attrs\":{\"内存容量\":\"2\",\"颜色分类\":\"白\",\"最大内存容量\":\"8\"}},{\"quantity\":23,\"price\":100,\"skuTemplateCode\":\"无\",\"originPrice\":3200,\"platformPrice\":12300,\"skuCode\":\"tulingbuy-sku-1201\",\"warehouseCode\":\"DEFAULT_1\",\"attrs\":{\"内存容量\":\"2\",\"颜色分类\":\"白\",\"最大内存容量\":\"16\"}},{\"quantity\":32,\"price\":100,\"skuTemplateCode\":\"无\",\"originPrice\":3200,\"platformPrice\":13200,\"skuCode\":\"tulingbuy-sku-1202\",\"warehouseCode\":\"DEFAULT_1\",\"attrs\":{\"内存容量\":\"2\",\"颜色分类\":\"黑\",\"最大内存容量\":\"8\"}}],\"skuAttributes\":[{\"attrVal\":\"2\",\"attrKey\":\"内存容量\"},{\"attrVal\":\"白\",\"attrKey\":\"颜色分类\"},{\"attrVal\":\"黑\",\"attrKey\":\"颜色分类\"},{\"attrVal\":\"16\",\"attrKey\":\"最大内存容量\"},{\"attrVal\":\"8\",\"attrKey\":\"最大内存容量\"},{\"attrVal\":\"ddr5\",\"attrKey\":\"内存类型\"}],\"itemDetail\":{\"images\":[],\"detail\":\"4466\"},\"otherAttributes\":[{\"attrVal\":\"Windows10\",\"attrKey\":\"操作系统\"},{\"attrVal\":\"151\",\"attrKey\":\"屏幕分辨率\"},{\"attrVal\":\"123\",\"attrKey\":\"CPU主频\"},{\"attrVal\":\"12\",\"attrKey\":\"CPU型号\"},{\"attrVal\":\"12\",\"attrKey\":\"质保时间\"},{\"attrVal\":\"1231231\",\"attrKey\":\"显存位宽\"},{\"attrVal\":\"123\",\"attrKey\":\"光驱类型\"},{\"attrVal\":\"128\",\"attrKey\":\"硬盘容量\"},{\"attrVal\":\"13.3\",\"attrKey\":\"屏幕尺寸\"},{\"attrVal\":\"123\",\"attrKey\":\"显存容量\"},{\"attrVal\":\"12312\",\"attrKey\":\"显存类型\"},{\"attrVal\":\"集显\",\"attrKey\":\"显卡类型\"},{\"attrVal\":\"312\",\"attrKey\":\"显卡芯片\"},{\"attrVal\":\"12\",\"attrKey\":\"机壳材质\"},{\"attrVal\":\"121\",\"attrKey\":\"售后服务内容\"},{\"attrVal\":\"是\",\"attrKey\":\"是否有摄像头\"},{\"attrVal\":\"3123\",\"attrKey\":\"插槽数量\"},{\"attrVal\":\"是\",\"attrKey\":\"是否内置扬声器\"},{\"attrVal\":\"否\",\"attrKey\":\"是否内置麦克风\"},{\"attrVal\":\"否\",\"attrKey\":\"是否支持触摸功能\"},{\"attrVal\":\"123\",\"attrKey\":\"机身尺寸(长*宽*厚）(mm）\"},{\"attrVal\":\"1231\",\"attrKey\":\"CPU类型\"},{\"attrVal\":\"ddr5\",\"attrKey\":\"内存类型\"},{\"attrVal\":\"是\",\"attrKey\":\"是都有无线网卡\"},{\"attrVal\":\"23123\",\"attrKey\":\"最大支持内存\"},{\"attrVal\":\"10\",\"attrKey\":\"内存\"},{\"attrVal\":\"12\",\"attrKey\":\"厂商保修政策\"},{\"attrVal\":\"是\",\"attrKey\":\"是否残疾人庇护产品\"}]}}");
	        zcyOpenRequest.setParamMap(bodyMap);
	        
	        String result = null;
			if(flag==0){  
	        /*发送http request*/
			try {
				System.out.println(zcyOpenRequest);
				result = ZcyOpenClient.excute(zcyOpenRequest);
				System.out.println(result);
				JSONObject jsonResult = new JSONObject(result);
				//同步成功时，修改该商品状态
				if((Boolean) jsonResult.get("success")){
					productDao.updateProduct(product.getId(), "is_zcy", 2);
					if(jsonResult.has("result")&&!jsonResult.get("result").toString().equals("true")&&!jsonResult.get("result").toString().equals("false"))
					productDao.updateProduct(product.getId(), "zcy_id", jsonResult.get("result"));
					msg+="商品-"+product.getId().toString()+"同步成功";
					
				}else{
					msg+="商品-"+product.getId().toString()+jsonResult.get("error")+"，";
				}
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
	        return msg;

	}
		
		/**
		 * 获取政采云三级目录必填项
		 */
		
		public List<ProductAttrTemplate> synchronizeZcy(Integer categoryId){
			List<ProductAttrTemplate> tempList = new ArrayList<ProductAttrTemplate>();
			JSONObject json = new JSONObject().put("categoryId", categoryId);
			String result = ZcyCommonUtil.doPost(json, "zcy.category.attrs.get");
			json = new JSONObject(result);
			if(json.has("data_response")){
				
				JSONArray jsonArr = json.getJSONArray("data_response");
				for(int i=0;i<jsonArr.length();i++){
					ProductAttrTemplate attr = new ProductAttrTemplate();
					JSONObject jsonObject = jsonArr.getJSONObject(i);
					JSONObject detialJ = jsonObject.getJSONObject("attrMetas");
					attr.setAttrName(jsonObject.getString("attrName"));
					attr.setGroup(jsonObject.getString("group"));
					attr.setIsRequired(detialJ.getBoolean("isRequired")==true?1:0);
					attr.setIsUserDefined(detialJ.getBoolean("isUserDefined")==true?1:0);
					attr.setIsSukCandidate(detialJ.getBoolean("isSukCandidate")==true?1:0);
					attr.setIsImportant(detialJ.getBoolean("isImportant")==true?1:0);
					attr.setValueType(detialJ.getString("valueType"));
					attr.setCategory_id(categoryId);
					tempList.add(attr);
				}
				return tempList;
			}else
				return null;
	
		}
		/**
		 * 获取并保存政采云三级目录属性信息attr
		 * @param categoryId
		 */
		@Transactional
		public void saveAttrFromZcy(Integer categoryId){
			List<ProductAttrTemplate>  list  = 	synchronizeZcy(categoryId);
			for (ProductAttrTemplate productAttrTemplate : list) {
				tempDao.addAttrTemp(productAttrTemplate);
			}
			
		}
		
		/**
		 * 删除政采云三级目录属性信息attr
		 * @param categoryId
		 */
		@Transactional
		public void delAttrFromZcy(Integer categoryId){
			tempDao.delTemp(categoryId);
		}
		

		@Transactional
		public String updateAttrFromZcy(Integer categoryId){
			String result = "";
			try{
			List<ProductAttrTemplate>  list  = 	new ZcyService().synchronizeZcy(categoryId);
				tempDao.deleteTemplateWithCodeByCate(categoryId, 1);
			if(list!=null && list.size()>0){
				
				for (ProductAttrTemplate productAttrTemplate : list) {
					int count = 0;
					if(productAttrTemplate.getIsUserDefined()==1){
						count = tempDao.updateTemp(categoryId,productAttrTemplate,0);
					}else{
						count = tempDao.updateTemp(categoryId,productAttrTemplate,1);
					}
					if(count==0){
						tempDao.addAttrTemp(productAttrTemplate);	
					}
				}
			}
			result = "同步成功！";
		
			}catch(Exception e){
				System.out.println(e.getCause());
				result = "同步失败！";
			}
				
			return result;
		}		

		
		


		
		/**
		 * 
		 * @return
		 */
		public Map<String,Object> updateAllTypesandTemplate(){
			Map<String,Object> map = new HashMap<String, Object>();
	        JSONObject jsonObject = new JSONObject();
	        jsonObject.put("root", 0);
	        jsonObject.put("depth",4);
			try {
				ZcyResponse<LinkedHashMap> responsebody = ZcyCommonUtil.getZcyResponse(jsonObject, "zcy.category.get");
				if(responsebody.isSuccess()){
					UpdateLogger.warn("===================获取大类      开始=======================");
					
					String data =JSONUtils.toJSONString( responsebody.getResult());
					JSONObject jsondata = new JSONObject(data);
					String str = ZcyCommonUtil.scanJson(jsondata,"0");
					str = "["+str.substring(0, str.length()-1)+"]";
					JSONArray arr = new JSONArray(str);
					UpdateLogger.warn("==获取成功，共获取"+arr.length()+"项==");
					UpdateLogger.warn("===================同步大类      开始=======================");
					for(int i = 0;i<arr.length();i++){
						JSONObject j = arr.getJSONObject(i);
						
						ProductType t = JSON.parseObject(j.toString(), ProductType.class);
						ProductType type =  productTypeDao.queryProductType(t.getId());
						if(type==null){
							productTypeDao.addProductTypeByZct(t);
							UpdateLogger.warn("[add] [level-"+t.getProduct_level()+"] [name- "+t.getName()+']');
							
						}else{
							productTypeDao.modifyProductType(t);
							UpdateLogger.warn("[modified] [level-"+t.getProduct_level()+"] [name- "+t.getName()+']');
						}
					}
					UpdateLogger.warn("===================同步大类      成功=======================");
					UpdateLogger.warn("===================同步模板      开始=======================");
					UpdateLogger.warn("==获取三级类列表==");
					List<ProductType> categoryList = productTypeDao.getAllCategoryIds();
					UpdateLogger.warn("==共获取 "+categoryList.size()+"项==");
					for (ProductType productType : categoryList) {
						if(updateAttrFromZcy(productType.getId()).indexOf("成功")!=-1){
							UpdateLogger.warn("[template] [id-"+productType.getId()+"] [name- "+productType.getName()+"] 更新成功");
						}else{
							UpdateLogger.warn("[template] [id-"+productType.getId()+"] [name- "+productType.getName()+"] 更新失败");
						}
					}
					UpdateLogger.warn("===================同步模板      成功=======================");
						map.put("resultCode", 1);

				}else {
					map.put("resultCode", 0);
					map.put("resultMsg", "[政采云]-错误原因："+responsebody.getError());
					System.out.println(responsebody.getError());
				}
			} catch (Exception e) {
				map.put("resultCode", 0);
				map.put("resultMsg", e.getMessage());
				// TODO Auto-generated catch block
				logger.error(e);		
				}
			return map;
			
		}
		
		
		public int addProduct2Zcy(Product product){
			

			String msg = "";
			int flag = 0;
			
			String uri = "/open/zcy.item.add";
			
	        ZcyOpenRequest zcyOpenRequest = new ZcyOpenRequest(ZcyConfig.instance().APP_KEY,ZcyConfig.instance().SECRET,ZcyConfig.instance().API_GATEWAY);
	        zcyOpenRequest.setUri(uri);
	        zcyOpenRequest.setMethod(HttpMethod.POST);

	        /*组装Body参数*/
	        Map<String, Object> bodyMap = new HashMap<String, Object>();
	        
			
			//拼装jsonObject
			//1.item
			 JSONObject item = new JSONObject();
			 item.put("selfPlatformLink","http://www.tulingbuy.com/buy/buy_show.json?ID="+product.getId());
			 item.put("needInstall", product.getNeedInstall());
			 String type_cut = product.getType_ids().split(",")[3];
			 if(type_cut==""){
				 flag=1;
				 msg+=product.getId()+"所属类目出错;";
			 }else{
				 //通过三级类id查找zcyid
				
				 item.put("categoryId",type_cut);
			 }
			 item.put("name", product.getName());
			 item.put("itemCode", "tulingbuy-spu-"+product.getId().toString());
			 item.put("mainImage","http://img.tulingbuy.com"+product.getImg_url());		
			 ProductBrand brand = brandDao.queryProductBrand(product.getBrand_id());
			 item.put("brandName", brand.getName());
//			 item.put("spuCode","tulingbuy-spu-"+product.getId().toString());
			 item.put("specification",product.getXh()==""?"无":product.getXh());
			 //这里设置一个默认值，如果未填写的，全部设置为浙江-杭州-拱墅
			 item.put("limit", product.getLimit()==null?"0":product.getLimit());	//0是境内
			 item.put("countryId",product.getCountryId()==null||product.getCountryId().isEmpty()?"228":product.getCountryId());
			 item.put("provinceId",product.getProvinceId()==null?"330000":product.getProvinceId());
			 item.put("cityId",product.getCityId()==null?"330100":product.getCityId());
			 item.put("regionId",product.getRegionId()==null?"330105":product.getRegionId());
			 item.put("firm",product.getFirm());
			 
			 //2.itemDetail
			 JSONObject itemDetail = new JSONObject();
			 
			 List<ProductImage> list = imageDao.queryProductImageList(product.getId(), -1);
			 ArrayList<String> images = new ArrayList<String>();

			 int flag_image = 0;
			for(int i=0;i<list.size();i++){
				if(list.get(i).getcoverImage()!=1){
				images.add("http://img.tulingbuy.com"+list.get(i).getImg_url());
				flag_image ++;
				if(flag_image>3) continue;

				}
			}	
			 //图片上传接口，4张附图
			 itemDetail.put("images", images);

			 String content = product.getContent().replace(" src=\"/upload/", " src=\"http://img.tulingbuy.com/upload/");
			 itemDetail.put("detail",content);
			 
			 //3.otherAttributes
			 ArrayList<JSONObject> attributes = new ArrayList<JSONObject>();
			 List attr_list = productDao.queryProductAttrs(product.getId());
			 if(attr_list!=null && attr_list.size()>0){
				 
				 for(int i=0;i<attr_list.size();i++){
					 JSONObject otherAttribute = new JSONObject();
					 Map<String,String> map =  (Map<String,String>)attr_list.get(i);
					 if(map.get("attrVal")!=null && map.get("attrVal").length()>0){
						 
						 otherAttribute.put("attrVal", map.get("attrVal"));
						 otherAttribute.put("attrKey", map.get("attrKey"));
						 attributes.add(otherAttribute);
						 
					 }
				 }
				 
			 }


			 //5.skuAttributes
			 ArrayList<JSONObject> skuAttributes = new ArrayList<JSONObject>();

			 	//分4次查询sku的Attr结果
			 for(int i=0;i<4;i++){
				 List list_attr = skuDao.queryProductSkuForSkuAttrs(product.getId(), i+1);
				 if(list_attr!=null){
					 for(int j=0;j<list_attr.size();j++){
						 Map map = (Map)list_attr.get(j);
						 JSONObject skuAttribute = new JSONObject();
						 if(map.get("attrVal")!=null&&!"".equals(map.get("attrVal"))&&map.get("attrName")!=null&&!"".equals(map.get("attrName"))){
							 skuAttribute.put("attrVal", map.get("attrVal"));
							 skuAttribute.put("attrKey",map.get("attrName"));
							 skuAttributes.add(skuAttribute);
						 }
						
						
					 }
				 }
			 }
			 List list_candidate = tempDao.getSukAttr(product.getId());
			 if(list_candidate!=null){
				 for(int j=0;j<list_candidate.size();j++){
					 Map map = (Map)list_candidate.get(j);
					 JSONObject skuAttribute = new JSONObject();
					 if(map.get("attrVal")!=null&&!"".equals(map.get("attrVal"))&&map.get("attrKey")!=null&&!"".equals(map.get("attrKey"))){
						 skuAttribute.put("attrVal", map.get("attrVal"));
						 skuAttribute.put("attrKey",map.get("attrKey"));
						 skuAttributes.add(skuAttribute);
					 }
					
					
				 }
			 }

			 //4.sku
			 ArrayList<JSONObject> skuGroup = new ArrayList<JSONObject>();
			 
			 List<ProductSku> skuList = skuDao.queryProductSkuListById(product.getId());

				 for(int i=0;i<skuList.size();i++){
					 JSONObject skus = new JSONObject();
					 ProductSku sku1 = skuList.get(i);
					 skus.put("price", sku1.getPrice()*100);
					 skus.put("originPrice", sku1.getOriginPrice()*100);
					 skus.put("platformPrice", sku1.getPlatformPrice()*100);
					 skus.put("quantity", sku1.getStocks());
					 skus.put("skuCode", "tulingbuy-sku-"+sku1.getId().toString());
					 skus.put("skuTemplateCode", "无");
					 skus.put("warehouseCode", "DEFAULT_1");
					 
					 JSONObject attrs = new JSONObject();
					 if(sku1.getAttrName1()!=null&&!"".equals(sku1.getAttrName1())){
						 attrs.put(sku1.getAttrName1(),sku1.getAttrVal1());
					 }
					 if(sku1.getAttrName2()!=null&&!"".equals(sku1.getAttrName2())){
						 attrs.put(sku1.getAttrName2(),sku1.getAttrVal2());
					 }
					 if(sku1.getAttrName3()!=null&&!"".equals(sku1.getAttrName3())){
						 attrs.put(sku1.getAttrName3(),sku1.getAttrVal3());
					 }
					 if(sku1.getAttrName4()!=null&&!"".equals(sku1.getAttrName4())){
						 attrs.put(sku1.getAttrName4(),sku1.getAttrVal4());
					 }
					 if(sku1.getAttrName5()!=null&&!"".equals(sku1.getAttrName5())){
						 attrs.put(sku1.getAttrName5(),sku1.getAttrVal5());
					 }
					 if(sku1.getAttrName6()!=null&&!"".equals(sku1.getAttrName6())){
						 attrs.put(sku1.getAttrName6(),sku1.getAttrVal6());
					 }
					 if(sku1.getAttrName7()!=null&&!"".equals(sku1.getAttrName7())){
						 attrs.put(sku1.getAttrName7(),sku1.getAttrVal7());
					 }
					 if(sku1.getAttrName8()!=null&&!"".equals(sku1.getAttrName8())){
						 attrs.put(sku1.getAttrName8(),sku1.getAttrVal8());
					 }
					 
					 skus.put("attrs",attrs);
					 skuGroup.add(skus);
				 }

			 //拼装完整data
			 JSONObject data = new JSONObject();
			 data.put("item", item);
			 data.put("itemDetail", itemDetail);
			 data.put("otherAttributes", attributes);
			 data.put("skuAttributes", skuAttributes);
			 data.put("skus", skuGroup);
			 
			 JSONObject d = new JSONObject();
			 d.put("data", data);

			 bodyMap.put("_data_", d.toString());
//			 bodyMap.put("_data_", "{\"data\":{\"item\":{\"brandName\":\"Apple\",\"itemCode\":\"tulingbuy-spu-19654\",\"specification\":\"test_20170118\",\"cityId\":\"410100\",\"provinceId\":\"410000\",\"countryId\":\"0\",\"firm\":\"1\",\"mainImage\":\"http://img.tulingbuy.comnull\",\"selfPlatformLink\":\"http://www.tulingbuy.com/buy/buy_show.json?ID=19654\",\"regionId\":\"410101\",\"name\":\"test_20170118\",\"limit\":\"0\",\"categoryId\":\"948\",\"needInstall\":\"0\"},\"skus\":[{\"quantity\":12,\"price\":0,\"skuTemplateCode\":\"无\",\"originPrice\":1200,\"platformPrice\":1200,\"skuCode\":\"tulingbuy-sku-1200\",\"warehouseCode\":\"DEFAULT_1\",\"attrs\":{\"内存容量\":\"2\",\"颜色分类\":\"白\",\"最大内存容量\":\"8\"}},{\"quantity\":23,\"price\":100,\"skuTemplateCode\":\"无\",\"originPrice\":3200,\"platformPrice\":12300,\"skuCode\":\"tulingbuy-sku-1201\",\"warehouseCode\":\"DEFAULT_1\",\"attrs\":{\"内存容量\":\"2\",\"颜色分类\":\"白\",\"最大内存容量\":\"16\"}},{\"quantity\":32,\"price\":100,\"skuTemplateCode\":\"无\",\"originPrice\":3200,\"platformPrice\":13200,\"skuCode\":\"tulingbuy-sku-1202\",\"warehouseCode\":\"DEFAULT_1\",\"attrs\":{\"内存容量\":\"2\",\"颜色分类\":\"黑\",\"最大内存容量\":\"8\"}}],\"skuAttributes\":[{\"attrVal\":\"2\",\"attrKey\":\"内存容量\"},{\"attrVal\":\"白\",\"attrKey\":\"颜色分类\"},{\"attrVal\":\"黑\",\"attrKey\":\"颜色分类\"},{\"attrVal\":\"16\",\"attrKey\":\"最大内存容量\"},{\"attrVal\":\"8\",\"attrKey\":\"最大内存容量\"},{\"attrVal\":\"ddr5\",\"attrKey\":\"内存类型\"}],\"itemDetail\":{\"images\":[],\"detail\":\"4466\"},\"otherAttributes\":[{\"attrVal\":\"Windows10\",\"attrKey\":\"操作系统\"},{\"attrVal\":\"151\",\"attrKey\":\"屏幕分辨率\"},{\"attrVal\":\"123\",\"attrKey\":\"CPU主频\"},{\"attrVal\":\"12\",\"attrKey\":\"CPU型号\"},{\"attrVal\":\"12\",\"attrKey\":\"质保时间\"},{\"attrVal\":\"1231231\",\"attrKey\":\"显存位宽\"},{\"attrVal\":\"123\",\"attrKey\":\"光驱类型\"},{\"attrVal\":\"128\",\"attrKey\":\"硬盘容量\"},{\"attrVal\":\"13.3\",\"attrKey\":\"屏幕尺寸\"},{\"attrVal\":\"123\",\"attrKey\":\"显存容量\"},{\"attrVal\":\"12312\",\"attrKey\":\"显存类型\"},{\"attrVal\":\"集显\",\"attrKey\":\"显卡类型\"},{\"attrVal\":\"312\",\"attrKey\":\"显卡芯片\"},{\"attrVal\":\"12\",\"attrKey\":\"机壳材质\"},{\"attrVal\":\"121\",\"attrKey\":\"售后服务内容\"},{\"attrVal\":\"是\",\"attrKey\":\"是否有摄像头\"},{\"attrVal\":\"3123\",\"attrKey\":\"插槽数量\"},{\"attrVal\":\"是\",\"attrKey\":\"是否内置扬声器\"},{\"attrVal\":\"否\",\"attrKey\":\"是否内置麦克风\"},{\"attrVal\":\"否\",\"attrKey\":\"是否支持触摸功能\"},{\"attrVal\":\"123\",\"attrKey\":\"机身尺寸(长*宽*厚）(mm）\"},{\"attrVal\":\"1231\",\"attrKey\":\"CPU类型\"},{\"attrVal\":\"ddr5\",\"attrKey\":\"内存类型\"},{\"attrVal\":\"是\",\"attrKey\":\"是都有无线网卡\"},{\"attrVal\":\"23123\",\"attrKey\":\"最大支持内存\"},{\"attrVal\":\"10\",\"attrKey\":\"内存\"},{\"attrVal\":\"12\",\"attrKey\":\"厂商保修政策\"},{\"attrVal\":\"是\",\"attrKey\":\"是否残疾人庇护产品\"}]}}");
	        zcyOpenRequest.setParamMap(bodyMap);
	        
	        String result = null;
			if(flag==0){  
	        /*发送http request*/
			try {
				System.out.println(zcyOpenRequest);
				result = ZcyOpenClient.excute(zcyOpenRequest);
				System.out.println(result);
				JSONObject jsonResult = new JSONObject(result);
				//同步成功时，修改该商品状态
				if((Boolean) jsonResult.get("success")){
					if(jsonResult.has("result")&&!jsonResult.get("result").toString().equals("true")&&!jsonResult.get("result").toString().equals("false"))
					productDao.updateProduct(product.getId(), "zcy_id", jsonResult.get("result"));
					msg+="商品-"+product.getId().toString()+"同步成功";
					
				}else{
					msg+="商品-"+product.getId().toString()+jsonResult.get("error")+"，";
				}
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println(msg);
			}
	        return 1;

	
		}
		
		
		public void getZcyId(){
			int[] status = new int[]{1,2,3,4,5,6,7,8};
			List<Product> list  = productDao.getProductByStatus(status);
			System.out.println("获取列表成功，共计"+list.size()+"项");
			for (Product product : list) {
				addProduct2Zcy(product);
			}
		}
}
