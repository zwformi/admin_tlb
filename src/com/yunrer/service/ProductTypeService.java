package com.yunrer.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.yunrer.common.ProcessResult;
import com.yunrer.dao.ProductTypeDao;
import com.yunrer.entity.ProductType;
import com.yunrer.util.BaseLogger;
import com.yunrer.util.ZcyCommonUtil;
import com.yunrer.util.zcy.openapi.zoss.model.ZcyResponse;
import com.yunrer.util.zcy.openapi.zoss.util.JsonUtil;

/**
 * 商品分类Service
 * @ClassName ProductTypeService
 * @Description 
 * @author rujun
 * @date 2016-12-21
 */
@Service("ProductTypeService")
public class ProductTypeService extends BaseLogger {

	@Resource
	private ProductTypeDao productTypeDao;
	
	/**
	 * 新增商品分类
	 * @Title: addProductType 
	 * @Description:
	 * @param request
	 * @return ProcessResult         
	 * @throws
	 */
	public ProcessResult addProductType(HttpServletRequest request) {
		ProcessResult pr = new ProcessResult();
		try{
			ProductType pt = new ProductType();
			pt.setName(request.getParameter("name"));
			pt.setParentId(request.getParameter("parentId")==null?0:Integer.parseInt(request.getParameter("parentId")));
			pt.setImg_url(request.getParameter("img_url"));
			pt.setSub_parentId(request.getParameter("subParentId")==null?0:Integer.parseInt(request.getParameter("subParentId")));
			pt.setProduct_level(request.getParameter("product_level")==null?0:Integer.parseInt(request.getParameter("product_level")));
			pt.setSortId(Integer.parseInt(request.getParameter("sortId")));
			
			productTypeDao.addProductType(pt);
			
			pr.setSuccess(true);
			pr.setMessage("保存成功");
		} catch (Exception ex) {
			ex.printStackTrace();
			pr.setSuccess(false);
			pr.setMessage(ex.getMessage());
		}
		return pr;
	}
	
	/**
	 * 删除商品分类
	 * @Title: deleteProductType 
	 * @Description:
	 * @param request
	 * @return ProcessResult         
	 * @throws
	 */
	public ProcessResult deleteProductType(HttpServletRequest request) {
		ProcessResult pr = new ProcessResult();
		try{
			String id = request.getParameter("id");
			Object[] ids = id.split(",");
			
			productTypeDao.deleteProductType(ids);
			
			pr.setSuccess(true);
			pr.setMessage("删除成功");
		} catch (Exception ex) {
			pr.setSuccess(false);
			pr.setMessage(ex.getMessage());
		}
		return pr;
	}
	
	/**
	 * 更新商品分类
	 * @Title: modifyProductType 
	 * @Description:
	 * @param request
	 * @return ProcessResult         
	 * @throws
	 */
	public ProcessResult modifyProductType(HttpServletRequest request) {
		ProcessResult pr = new ProcessResult();
		try{
			ProductType pt = new ProductType();
			pt.setId(Integer.parseInt(request.getParameter("id")));
			pt.setName(request.getParameter("name"));
			pt.setParentId(request.getParameter("parentId")==null?0:Integer.parseInt(request.getParameter("parentId")));
			pt.setImg_url(request.getParameter("img_url"));
			pt.setSub_parentId(request.getParameter("subParentId")==null?0:Integer.parseInt(request.getParameter("subParentId")));
			pt.setProduct_level(request.getParameter("product_level")==null?0:Integer.parseInt(request.getParameter("product_level")));
			pt.setSortId(Integer.parseInt(request.getParameter("sortId")));
			
			productTypeDao.modifyProductType(pt);
			
			pr.setSuccess(true);
			pr.setMessage("保存成功");
		} catch (Exception ex) {
			pr.setSuccess(false);
			pr.setMessage(ex.getMessage());
		}
		return pr;
	}
	
	/**
	 * 查询商品分类
	 * @Title: queryProductType 
	 * @Description:
	 * @param request
	 * @return ProductType         
	 * @throws
	 */
	public ProductType queryProductType(HttpServletRequest request) {
		ProductType pt = null;
		try{
			int id = Integer.parseInt(request.getParameter("id"));
			pt = productTypeDao.queryProductType(id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return pt;
	}
	
	/**
	 * 查询分类数量
	 * @Title: queryProductTypeCount 
	 * @Description:
	 * @param request
	 * @return int         
	 * @throws
	 */
	public int queryProductTypeCount(HttpServletRequest request) {
		int count = 0;
		try{
			String keyword = request.getParameter("keyword")==null?"":request.getParameter("keyword");
			count = productTypeDao.queryProductTypeCount(keyword);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return count;
	}
	
	/**
	 * 查询分类列表
	 * @Title: queryProductTypeList 
	 * @Description:
	 * @param request
	 * @return List<ProductType>         
	 * @throws
	 */
	public List<ProductType> queryProductTypeList(HttpServletRequest request) {
		List<ProductType> list = null;
		try{
			String keyword = request.getParameter("keyword")==null?"":request.getParameter("keyword");
			int pageIndex = request.getParameter("pageIndex")==null?0:Integer.parseInt(request.getParameter("pageIndex"));
			int pageSize = request.getParameter("pageSize")==null?0:Integer.parseInt(request.getParameter("pageSize"));
			list = productTypeDao.queryProductTypeList(keyword, pageIndex, pageSize);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 查询分类列表
	 * @Title: queryProductTypeList 
	 * @Description:
	 * @param request
	 * @return List<ProductType>         
	 * @throws
	 */
	public List<ProductType> queryProductTypeListForTree(HttpServletRequest request) {
		List<ProductType> list = null;
		try{
			String keyword = request.getParameter("keyword")==null?"":request.getParameter("keyword");
			int pageIndex = request.getParameter("pageIndex")==null?0:Integer.parseInt(request.getParameter("pageIndex"));
			int pageSize = request.getParameter("pageSize")==null?0:Integer.parseInt(request.getParameter("pageSize"));
			list = productTypeDao.queryProductTypeListForTree(keyword, pageIndex, pageSize);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 查询分类列表
	 * @Title: queryProductTypeList 
	 * @Description:
	 * @param parentid
	 * @return List<ProductType>         
	 * @throws
	 */
	public List<ProductType> queryProductTypeList(int parentid) {
		return productTypeDao.queryProductTypeList(parentid);
	}
	
	/**
	 * 查询树形所需的全部typelist
	 * @Title: queryForTree 
	 * @Description:
	 * @param request
	 * @return List<ProductType>         
	 * @throws
	 */
	public List<ProductType> queryForTree(HttpServletRequest request) {
		List<ProductType> list = productTypeDao.queryProductByLevel(1);
		list.addAll( productTypeDao.queryProductByLevel(2));
		list.addAll( productTypeDao.queryProductByLevel(3));
		return list;
	}
	

	
	
	
	/**
	 * 更新某一大类及其子类
	 * @param pid
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@Transactional
	public  Map<String,Object> getFullMenu(int pid) {
		Map<String,Object> map = new HashMap<String, Object>();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("root", 0);
        jsonObject.put("depth",4);
		try {
			ZcyResponse<LinkedHashMap> responsebody = ZcyCommonUtil.getZcyResponse(jsonObject, "zcy.category.get");
			if(responsebody.isSuccess()){
				String data =JSONUtils.toJSONString( responsebody.getResult());
				JSONObject jsondata = new JSONObject(data);
				String str = ZcyCommonUtil.scanJson(jsondata,"0");
				str = "["+str.substring(0, str.length()-1)+"]";
				JSONArray arr = new JSONArray(str);
				boolean deleteflag = false;
				for(int i = 0;i<arr.length();i++){
					JSONObject j = arr.getJSONObject(i);
					
					ProductType t = JSON.parseObject(j.toString(), ProductType.class);
					
					if((t.getId()==pid) || (t.getParentId()==pid) || (t.getSub_parentId()==pid) ){
						if(deleteflag==false){
							
							productTypeDao.deleteTypesById(pid);
							deleteflag =true;
							
						}
						
						ProductType type =  productTypeDao.queryProductType(t.getId());
						if(type==null){
							productTypeDao.addProductTypeByZct(t);
						}else{
							productTypeDao.modifyProductType(t);
						}
					
					}
				}
				if(deleteflag)
					map.put("resultCode", 1);
				else{
					map.put("resultCode", 0);
					map.put("resultMsg", "所更新类目不存在！");					
				}
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

	


	/**
	 * 获取某一类下的三级类
	 * @param parentId
	 * @return
	 */
	public List<ProductType> getCategoryTypes(Integer parentId){
		List<ProductType> list = new ArrayList<ProductType>();
		list = productTypeDao.getCategoryIds(parentId);
		return list;
		
	}

	
}