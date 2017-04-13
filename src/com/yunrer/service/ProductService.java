package com.yunrer.service;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.support.json.JSONUtils;
import com.yunrer.common.DateUtils;
import com.yunrer.dao.ProductAttrTemplateDao;
import com.yunrer.dao.ProductDao;
import com.yunrer.dao.ProductImageDao;
import com.yunrer.dao.ProductItemDao;
import com.yunrer.dao.ProductSkuDao;
import com.yunrer.dao.UserCompanyDao;
import com.yunrer.entity.Product;
import com.yunrer.entity.ProductAttrTemplate;
import com.yunrer.entity.ProductBrand;
import com.yunrer.entity.ProductParam;
import com.yunrer.entity.ProductType;
import com.yunrer.entity.SelectTree;
import com.yunrer.entity.UserCompany;

/**
 * 商品Service
 * @ClassName ProductService
 * @Description 
 * @author rujun
 * @date 2016-12-21
 */
@Service("ProductService")
public class ProductService {
	
	@Resource
	private ProductDao productDao;
	
	@Resource	
	private UserCompanyDao userCompanyDao;
	
	@Resource
	private ProductAttrTemplateDao tempDao;
	
	@Resource
	private ProductImageDao imageDao;
	
	@Resource
	private ProductSkuDao skuDao;
	
	@Resource
	private ProductItemDao itemDao;
	
	/**
	 * 新增商品信息
	 * @Title: addProduct 
	 * @Description:
	 * @param request
	 * @return int[]         
	 * @throws
	 */
	public int[] addProduct(HttpServletRequest request) {
		int count = 0;
		try{
			Product product = new Product();
			String type_id=request.getParameter("type_id");
			String parent_type_id = request.getParameter("parent_type_id");
			String sub_type_id = request.getParameter("sub_type_id");
			product.setZcy_price_area(request.getParameter("zcy_price_area"));
			product.setPlatform_price_area(request.getParameter("platform_price_area"));
			product.setType_ids(","+parent_type_id+","+sub_type_id+","+type_id+",");  
			product.setParentId(Integer.parseInt(parent_type_id));
			product.setSub_parentId(Integer.parseInt(sub_type_id));
			product.setCategory_id(Integer.parseInt(type_id));
			product.setBrand_id(Integer.parseInt(request.getParameter("brand_id")));    
			product.setName(request.getParameter("name"));     
			product.setXh(request.getParameter("xh"));
			product.setPz(request.getParameter("pz"));
			product.setSub_title(request.getParameter("sub_title")); 
			product.setImg_url(request.getParameter("img_url"));     
			product.setCount(Integer.parseInt(request.getParameter("count")));       
			product.setPrice_old(Double.parseDouble(request.getParameter("price_old")==null?"0":request.getParameter("price_old")));   
			product.setPrice_new(Double.parseDouble(request.getParameter("price_new")==null?"0":request.getParameter("price_new")));   
			product.setContent(request.getParameter("content"));     
			product.setSortid(Integer.parseInt(request.getParameter("sortid")));      
			product.setIs_cx(Integer.parseInt(request.getParameter("is_cx")));       
			product.setIs_ms(Integer.parseInt(request.getParameter("is_ms")));  
			if(product.getIs_ms()==1){//如果为秒杀
				product.setMs_begintime(DateUtils.str2date(request.getParameter("ms_begintime")));
				product.setMs_kl(request.getParameter("ms_kl"));
			}
			product.setIs_red(Integer.parseInt(request.getParameter("is_red")));      
			product.setIs_wx(Integer.parseInt(request.getParameter("is_wx")));   
			product.setIs_pc(Integer.parseInt(request.getParameter("is_pc")));   
			product.setIs_yun(Integer.parseInt(request.getParameter("is_yun")));   
			product.setDelete_flag(Integer.parseInt(request.getParameter("delete_flag")));
			product.setNeedInstall(request.getParameter("needInstall"));
			product.setLimit(request.getParameter("limit"));
			product.setCountryId(request.getParameter("countryId"));
			product.setProvinceId(request.getParameter("provinceId"));
			product.setCityId(request.getParameter("cityId"));
			product.setFirm(request.getParameter("firm"));
			product.setService(request.getParameter("service"));
			product.setRegionId(request.getParameter("regionId"));
			product.setIs_zcy(request.getParameter("is_zcy")==null?0:Integer.parseInt(request.getParameter("is_zcy")));
			product.setIs_zcysj(request.getParameter("is_zcysj")==null?0:Integer.parseInt(request.getParameter("is_zcysj")));
			product.setSpecial_code(request.getParameter("special_code")==null?0:Integer.parseInt(request.getParameter("special_code")));

			return productDao.addProduct(product);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 新增内购
	 * @Title: addSpecial 
	 * @Description:
	 * @param request
	 * @return int         
	 * @throws
	 */
	public int addSpecial(HttpServletRequest request) {
		int id = 0;
		try{
			String company = request.getParameter("company");
			int product_id = Integer.parseInt((String) (request.getParameter("id")==null?0:request.getParameter("id")));
			Double product_price = Double.parseDouble((String) (request.getParameter("product_price")==null?0:request.getParameter("product_price")));
			
			//检查company是否是内购公司
			List<UserCompany> list = productDao.querySpecialCompany(company);
			int owning_company = 0;
			if(list!=null){
				owning_company = list.get(0).getId();

				id = productDao.addSpecial( product_id,product_price,owning_company); 
				
			}else{
				id = 0;
				
			}
  
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return id;
	}
	
	
	/**
	 * 查询商品详情
	 * @Title: queryProduct 
	 * @Description:
	 * @param request
	 * @return Product         
	 * @throws
	 */
	public Product queryProduct(HttpServletRequest request) {
		Product product = null;
		try{
			int id = Integer.parseInt(request.getParameter("id"));
			product = productDao.queryProduct(id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return product;
	}
	
	
	/**
	 * 添加模板选项数据
	 * @Title: addAttrVals 
	 * @Description:
	 * @param val
	 * @param attr_id
	 * @return Map<String,Object>         
	 * @throws
	 */
	public Map<String,Object> addAttrVals(String val,int attr_id) {
		Map<String,Object> map = new HashMap<String, Object>();
		try{
			if(val!=null&&val.trim().length()>0){
				
			//查重
			ProductAttrTemplate template = tempDao.queryTempById(attr_id);
			String attr_old =template.getAttrVals()==null?"":template.getAttrVals().trim();
			
			String attr_old2 = ","+attr_old+",";
			String val2  = "," + val +",";
			if("".equals(attr_old)){
				int i = tempDao.updateAttrVals(attr_id, val);
				if(i==1){
					map.put("resultCode", 1);
					map.put("resultMsg", "添加成功");
				}else{
					map.put("resultCode", 0);
					map.put("resultMsg", "添加失败");
				}
			}else if(attr_old2.indexOf(val2)==-1){	//非重复
				int i =tempDao.updateAttrVals(attr_id, attr_old +","+val);
				if(i==1){
					map.put("resultCode", 1);
					map.put("resultMsg", "添加成功");
				}else{
					map.put("resultCode", 0);
					map.put("resultMsg", "添加失败");
				}
			}else{
				map.put("resultCode", -1);
				map.put("resultMsg", "添加失败,该选项已存在");
			}
			}else{
				map.put("resultCode", -1);
				map.put("resultMsg", "添加失败,不能添加空选项");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 删除模板选项数据
	 * @Title: delAttrVals 
	 * @Description:
	 * @param val
	 * @param attr_id
	 * @return Map<String,Object>         
	 * @throws
	 */
	public Map<String,Object> delAttrVals(String val,int attr_id) {
		Map<String,Object> map = new HashMap<String, Object>();
		try{
			//查询是否存在
			ProductAttrTemplate template = tempDao.queryTempById(attr_id);
			String attr_old = ","+template.getAttrVals()+",";
			String val2  = "," + val +",";
			
			if(attr_old.indexOf(val2)>-1){	//存在
				attr_old = attr_old.replace(val2,",");
				attr_old = attr_old.substring(0, attr_old.length()-1);
				int i =tempDao.updateAttrVals(attr_id, attr_old);
				if(i==1){
					map.put("resultCode", 1);
					map.put("resultMsg", "删除成功");
				}else{
					map.put("resultCode", 0);
					map.put("resultMsg", "删除失败");
				}
			}else{
				map.put("resultCode", -1);
				map.put("resultMsg", "删除失败,该选项不存在");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			map.put("resultCode", 0);
			map.put("resultMsg", "删除失败");
		}
		return map;
	}

	

	public int updateAttrVals(int attr_id,String attrVal){
		int i=0;
			i =tempDao.updateAttrVals(attr_id,attrVal);
			return i;

	}
	/**
	 * 
	 * @Title: queryAttrs 
	 * @Description:
	 * @param request
	 * @return List         
	 * @throws
	 */
	public List queryAttrs(HttpServletRequest request) {
		List list = null;
		try{

			if(request.getParameter("id")!=null
					&&!request.getParameter("id").isEmpty()
					&&!request.getParameter("id").equals("")){
				
				int id = Integer.parseInt(request.getParameter("id"));
				list = productDao.queryProductAttrs(id);
				
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
	
	
	/**
	 * 查询内购公司
	 * @Title: querySpecialProduct 
	 * @Description:
	 * @param request
	 * @return List         
	 * @throws
	 */
	public List querySpecialProduct(HttpServletRequest request) {
		List list = null;
		try{
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("company")==null?"":request.getParameter("company");
			list = productDao.querySpecialProduct(id,name);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 修改内购商品信息
	 * @Title: editSpecial 
	 * @Description:
	 * @param request
	 * @return int         
	 * @throws
	 */
	public int editSpecial(HttpServletRequest request) {
		int count = 0;
		try{
			int id = Integer.parseInt((String) (request.getParameter("special_id")==null?0:request.getParameter("special_id")));
			Double product_price = Double.parseDouble((String) (request.getParameter("product_price")==null?0.00:request.getParameter("product_price")));
			int product_id = Integer.parseInt((String) (request.getParameter("product_id")==null?0:request.getParameter("product_id")));
			
			count = productDao.editSpecialProduct(product_price,product_id,id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return count;
	}
	
	/**
	 * 删除内购商品
	 * @Title: delSpecial 
	 * @Description:
	 * @param request
	 * @return int         
	 * @throws
	 */
	public int delSpecial(HttpServletRequest request) {
		int count = 0;
		try{
			int id = Integer.parseInt((String) (request.getParameter("special_id")==null?0:request.getParameter("special_id")));
				
			count = productDao.delSpecialProduct(id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return count;
	}
	
	/**
	 * 查询商品数量
	 * @Title: queryProductCount 
	 * @Description:
	 * @param request
	 * @return int         
	 * @throws
	 */
	public int queryProductCount(HttpServletRequest request) {
		int count = 0;
		try{
			String keyword = request.getParameter("keyword");
			String type = request.getParameter("type");
			String sort1 = request.getParameter("sortid1");
			String sort2 = request.getParameter("sortid2");
			Integer sortid1 = null;
			Integer sortid2 = null;
			if(sort1!=null&&!"".equals(sort1)&&sort2!=null&&!"".equals(sort2)){
				sortid1 = Integer.parseInt(sort1);
				sortid2 = Integer.parseInt(sort2);
				if(sortid1 > sortid2){
					Integer t = sortid1;
					sortid1 = sortid2;
					sortid2 = t;
				}
			}	
			String product1 = request.getParameter("product_id1");
			String product2 = request.getParameter("product_id2");
			Integer product_id1 = null;
			Integer product_id2 = null;
			if(product1!=null&&!"".equals(product1)&&product2!=null&&!"".equals(product2)){
				product_id1 = Integer.parseInt(product1);
				product_id2 = Integer.parseInt(product2);
				if(product_id1 > product_id2){
					Integer t = product_id1;
					product_id1 = product_id2;
					product_id2 = t;
				}
			}
			String zcyid1 = request.getParameter("zcy_id1");
			String zcyid2 = request.getParameter("zcy_id2");
			Integer zcy_id1 = null;
			Integer zcy_id2 = null;
			if(zcyid1!=null&&!"".equals(zcyid1)&&zcyid2!=null&&!"".equals(zcyid2)){
				zcy_id1 = Integer.parseInt(zcyid1);
				zcy_id2 = Integer.parseInt(zcyid2);
				if(zcy_id1 > zcy_id2){
					Integer t = zcy_id1;
					zcy_id1 = zcy_id2;
					zcy_id2 = t;
				}
			}
			Integer is_zcy = (request.getParameter("is_zcy")==null||request.getParameter("is_zcy").equals(""))?null:Integer.parseInt(request.getParameter("is_zcy"));
			count = productDao.queryProductcount(keyword,type,sortid1,sortid2,product_id1,product_id2,is_zcy,zcy_id1,zcy_id2);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return count;
	}
	
	/**
	 * 查询商品列表
	 * @Title: queryProductList 
	 * @Description:
	 * @param request
	 * @return List<Product>         
	 * @throws
	 */
	public List<Product> queryProductList(HttpServletRequest request) {
		List<Product> list = null;
		try{
			String keyword = request.getParameter("keyword");
			int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
			int pageSize = Integer.parseInt(request.getParameter("pageSize"));
			String type = request.getParameter("type");
		
			String sort1 = request.getParameter("sortid1");
			String sort2 = request.getParameter("sortid2");
			Integer sortid1 = null;
			Integer sortid2 = null;
			if(sort1!=null&&!"".equals(sort1)&&sort2!=null&&!"".equals(sort2)){
				sortid1 = Integer.parseInt(sort1);
				sortid2 = Integer.parseInt(sort2);
				if(sortid1 > sortid2){
					Integer t = sortid1;
					sortid1 = sortid2;
					sortid2 = t;
				}
			}	
			String product1 = request.getParameter("product_id1");
			String product2 = request.getParameter("product_id2");
			Integer product_id1 = null;
			Integer product_id2 = null;
			if(product1!=null&&!"".equals(product1)&&product2!=null&&!"".equals(product2)){
				product_id1 = Integer.parseInt(product1);
				product_id2 = Integer.parseInt(product2);
				if(product_id1 > product_id2){
					Integer t = product_id1;
					product_id1 = product_id2;
					product_id2 = t;
				}
			}
			String zcyid1 = request.getParameter("zcy_id1");
			String zcyid2 = request.getParameter("zcy_id2");
			Integer zcy_id1 = null;
			Integer zcy_id2 = null;
			if(zcyid1!=null&&!"".equals(zcyid1)&&zcyid2!=null&&!"".equals(zcyid2)){
				zcy_id1 = Integer.parseInt(zcyid1);
				zcy_id2 = Integer.parseInt(zcyid2);
				if(zcy_id1 > zcy_id2){
					Integer t = zcy_id1;
					zcy_id1 = zcy_id2;
					zcy_id2 = t;
				}
			}
			Integer is_zcy = (request.getParameter("is_zcy")==null||request.getParameter("is_zcy").equals(""))?null:Integer.parseInt(request.getParameter("is_zcy"));
			list = productDao.queryProductList(keyword, pageIndex, pageSize,type,sortid1,sortid2,product_id1,product_id2,is_zcy,zcy_id1,zcy_id2);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 删除商品
	 * @Title: delProduct 
	 * @Description:
	 * @param request
	 * @return int         
	 * @throws
	 */
	@Transactional
	public int delProduct(HttpServletRequest request) {
		int count = 0;
		try{
			String id = request.getParameter("id");
			Object[] ids = id.split(",");

			count = productDao.delProductAttr(ids);
			count = imageDao.delProductImage(ids);
			count = skuDao.delProductSku(ids);
			count = itemDao.delProductSku(ids);
			count = productDao.delProduct(ids);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return count;
	}
	
	/**
	 * 商品上架--下架
	 * @Title: updateProduct_delete_flag 
	 * @Description:
	 * @param request
	 * @return int         
	 * @throws
	 */
	public int updateProduct_delete_flag(HttpServletRequest request) {
		int count = 0;
		try{
			int id = Integer.parseInt(request.getParameter("id"));
			int delete_flag = Integer.parseInt(request.getParameter("delete_flag"));
			//zcy同步：更新状态为已下架未同步
			if(productDao.queryProduct(id).getIs_zcy()==0||productDao.queryProduct(id).getIs_zcy()==1){
				
			}else
			count = productDao.updateProduct(id, "delete_flag", delete_flag);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return count;
	}
	
	/**
	 * 修改attr信息
	 * @Title: modifyAttrs 
	 * @Description:
	 * @param request
	 * @return int         
	 * @throws
	 */
	public int modifyAttrs(HttpServletRequest request){
		Enumeration enu=request.getParameterNames();  
		int product_id = Integer.parseInt((String) (request.getParameter("product_id")==null?0:request.getParameter("product_id")));
		List list = productDao.queryProductAttrs(product_id);
		
		
		List<JSONObject> attrlist = new ArrayList<JSONObject>();
		if(list!=null && list.size()>0){
			for (Object object : list) {
				attrlist.add( new JSONObject(JSONUtils.toJSONString(object)));
			}
		}
		while(enu.hasMoreElements()){  
			String paraName=(String)enu.nextElement(); 
			if(paraName.indexOf(",")>0){
				int attr_id = Integer.parseInt(paraName.split(",")[1]);
				String attrKey = paraName.split(",")[0];
				String attrVal = request.getParameter(paraName);
					boolean flag = false;
					if(attrlist!=null && attrlist.size()>0){
						
						for (JSONObject jsonObject : attrlist) {
							if(jsonObject.getString("attrKey").equals(attrKey))
								flag = true;
							if (jsonObject.getString("attrKey").equals(attrKey)&&!jsonObject.getString("attrVal").equals(attrVal)){
								productDao.editAttrs(product_id, attrKey, attrVal);	
								attrlist.remove(jsonObject);
								break;
							}
						}
						
					}
					if(!flag){
						productDao.addAttrs(product_id, attr_id, attrKey, attrVal);
						
					}
				
			}
		}
		return 0;
	}

	public int addAttrs(HttpServletRequest request){
		Enumeration enu=request.getParameterNames();  
		int product_id = Integer.parseInt((String) (request.getParameter("product_id")==null?0:request.getParameter("product_id")));
		
		while(enu.hasMoreElements()){  
		String paraName=(String)enu.nextElement(); 
		if(paraName.indexOf(",")>0){
			int attr_id = Integer.parseInt(paraName.split(",")[1]);
			String attrKey = paraName.split(",")[0];
			String attrVal = request.getParameter(paraName);
//			if(!attrVal.isEmpty())
			  productDao.addAttrs(product_id,attr_id,attrKey,attrVal);	
			
		}
		
	}
		return 0;
	}
	
	/**
	 * 修改商品信息
	 * @Title: modifyProduct 
	 * @Description:
	 * @param request
	 * @return int         
	 * @throws
	 */
	public int modifyProduct(HttpServletRequest request) {
		int count = 0;
		try{
			Product product = new Product();
			product.setId(Integer.parseInt(request.getParameter("id")));
			String type_id=request.getParameter("type_id");
			String parent_type_id = request.getParameter("parent_type_id");
			product.setZcy_price_area(request.getParameter("zcy_price_area"));
			product.setPlatform_price_area(request.getParameter("platform_price_area"));
			String sub_type_id = request.getParameter("sub_type_id");
			product.setType_ids(","+parent_type_id+","+sub_type_id+","+type_id+",");              
			product.setBrand_id(Integer.parseInt(request.getParameter("brand_id")));    
			product.setName(request.getParameter("name"));     
			product.setXh(request.getParameter("xh"));
			product.setPz(request.getParameter("pz"));
			product.setSub_title(request.getParameter("sub_title")); 
			product.setImg_url(request.getParameter("img_url"));     
			product.setCount(Integer.parseInt(request.getParameter("count")));       
			product.setPrice_old(Double.parseDouble(request.getParameter("price_old")==null?"0":request.getParameter("price_old")));   
			product.setPrice_new(Double.parseDouble(request.getParameter("price_new")==null?"0":request.getParameter("price_new")));   
			product.setContent(request.getParameter("content"));     
			product.setSortid(Integer.parseInt(request.getParameter("sortid")));      
			product.setIs_cx(Integer.parseInt(request.getParameter("is_cx")));       
			product.setIs_ms(Integer.parseInt(request.getParameter("is_ms")));  
			if(product.getIs_ms()==1){//如果为秒杀
				product.setMs_begintime(DateUtils.str2date(request.getParameter("ms_begintime")));
				product.setMs_kl(request.getParameter("ms_kl"));
			}
			product.setIs_red(Integer.parseInt(request.getParameter("is_red")));      
			product.setIs_wx(Integer.parseInt(request.getParameter("is_wx")));   
			product.setIs_pc(Integer.parseInt(request.getParameter("is_pc"))); 
			product.setIs_yun(Integer.parseInt(request.getParameter("is_yun"))); 
			product.setDelete_flag(Integer.parseInt(request.getParameter("delete_flag"))); 	
			
			product.setNeedInstall(request.getParameter("needInstall"));
			product.setLimit(request.getParameter("limit"));
			product.setCountryId(request.getParameter("countryId"));
			product.setProvinceId(request.getParameter("provinceId"));
			product.setCityId(request.getParameter("cityId"));
			product.setFirm(request.getParameter("firm"));
			product.setService(request.getParameter("service"));
			product.setRegionId(request.getParameter("regionId"));
			product.setIs_zcy(request.getParameter("is_zcy")==null?0:Integer.parseInt(request.getParameter("is_zcy")));
			product.setIs_zcysj(request.getParameter("is_zcysj")==null?0:Integer.parseInt(request.getParameter("is_zcysj")));
			product.setSpecial_code(request.getParameter("special_code")==null?0:Integer.parseInt(request.getParameter("special_code")));
			Product product_old = productDao.queryProduct(Integer.parseInt(request.getParameter("id")));
			int zcy = product_old.getIs_zcy();
			if(zcy == 0){
				product.setIs_zcy(0);
			}else if(zcy == 2){
				product.setIs_zcy(1);
			}else if(zcy == 1){
				product.setIs_zcy(1);
			}else
				product.setIs_zcy(1);
			count = productDao.modifyProduct(product);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return count;
	}
	
	/**
	 * 查询商品品牌列表
	 * @Title: queryProductBrandList 
	 * @Description:
	 * @param request
	 * @return List<ProductBrand>         
	 * @throws
	 */
	public List<ProductBrand> queryProductBrandList(HttpServletRequest request) {
		List<ProductBrand> list = null;
		try{
			list = productDao.queryProductBrandList();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 查询商品类型列表-树形菜单(修改为层级菜单)
	 * @Title: queryProductTypeList 
	 * @Description:
	 * @param request
	 * @return List<SelectTree>         
	 * @throws
	 */
	public List<SelectTree> queryProductTypeList(HttpServletRequest request) {
		List<ProductType> ProductTypelList = productDao.queryProductTypeList();
		List<SelectTree> trees = new ArrayList<SelectTree>();
		for(ProductType yc:ProductTypelList){
			if(0 == yc.getParentId()){
				SelectTree t = new SelectTree();
				t.setId(yc.getId());
				t.setName(yc.getName());
				t.setParentid(yc.getParentId());
				t.setChild(buildNode(t.getId(),ProductTypelList));
				trees.add(t);
			}
		}
		return trees;
	}
	
	/**
	 * 构建树型菜单数据
	 * @Title: buildNode 
	 * @Description:
	 * @param pid
	 * @param productTypeList
	 * @return List<SelectTree>         
	 * @throws
	 */
	public List<SelectTree> buildNode(int pid,List<ProductType> productTypeList){
		List<SelectTree> result = new ArrayList<SelectTree>();
		for(ProductType pro:productTypeList){
			SelectTree node = new SelectTree();
			if(0 != pro.getParentId() && pro.getParentId()==pid){
				node.setId(pro.getId());
				node.setName(pro.getName());
				node.setParentid(pro.getParentId());
				List<SelectTree> children = buildNode(pro.getId(),productTypeList);
				if(null != children && 0 < children.size()){
					node.setChild(children);
				}
				result.add(node); 
			}
		}
		return result;
	}
	
	/**
	 * 根据本地三级类目id获取三级类目的属性信息
	 * @param categoryId
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public  List  queryProductAttribute1(Integer categoryId){
		return productDao.queryProductAttributeForLocal(categoryId);
	}
	
	/**
	 * 模糊查询属性值
	 * @Title: queryProductAttributeByTemp 
	 * @Description:
	 * @param temp
	 * @param pageIndex
	 * @param pageSize
	 * @return List         
	 * @throws
	 */
	@SuppressWarnings("rawtypes")
	public  List  queryTempByTemp(ProductAttrTemplate temp,int pageIndex,int pageSize){
		return tempDao.queryAttrTempList(temp,pageIndex,pageSize);
	}
	
	public  int  queryTempForCount(ProductAttrTemplate temp){
		return tempDao.queryAttrTempForCount(temp);
	}
	
	/**
	 * 根据政采云三级类目id获取三级类目的属性信息
	 * @param categoryId
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public  List  queryProductAttribute(Integer categoryId){
		return productDao.queryProductAttribute(categoryId);
	}
	
	/**
	 * 根据parentCode获取地域列表
	 * @Title: getAreaList 
	 * @Description:
	 * @param parentId
	 * @return List         
	 * @throws
	 */
	@SuppressWarnings("rawtypes")
	public  List  getAreaList(int parentCode,int level){
		if(level!=0)
		return productDao.getAreaList(parentCode,level);
		else
		return productDao.getCountryList();
	}
	

	@SuppressWarnings("rawtypes")
	public  List<Product>  getProductByStatus(int[] status){
		return productDao.getProductByStatus(status);
	}
}