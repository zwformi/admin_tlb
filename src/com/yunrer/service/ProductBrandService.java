package com.yunrer.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.yunrer.common.ProcessResult;
import com.yunrer.dao.ProductBrandDao;
import com.yunrer.entity.ProductBrand;
import com.yunrer.util.ZcyCommonUtil;
/**
 * 商品品牌Service
 * @ClassName ProductBrandService
 * @Description 
 * @author rujun
 * @date 2016-12-21
 */
@Service("ProductBrandService")
public class ProductBrandService {
	
	@Resource
	private ProductBrandDao productBrandDao;
	
	/**
	 * 新增品牌
	 */
	public ProcessResult addProductBrand(HttpServletRequest request) {
		ProcessResult pr = new ProcessResult();
		try{
			ProductBrand pt = new ProductBrand();
			pt.setName(request.getParameter("name"));
			pt.setSortid(Integer.parseInt(request.getParameter("sortid")));
			
			productBrandDao.addProductBrand(pt);
			
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
	 * 删除品牌
	 */
	public ProcessResult deleteProductBrand(HttpServletRequest request) {
		ProcessResult pr = new ProcessResult();
		try{
			String id = request.getParameter("id");
			Object[] ids = id.split(",");
			
			productBrandDao.deleteProductBrand(ids);
			
			pr.setSuccess(true);
			pr.setMessage("删除成功");
		} catch (Exception ex) {
			pr.setSuccess(false);
			pr.setMessage(ex.getMessage());
		}
		return pr;
	}
	
	/**
	 * 更新品牌
	 */
	public ProcessResult modifyProductBrand(HttpServletRequest request) {
		ProcessResult pr = new ProcessResult();
		try{
			ProductBrand pt = new ProductBrand();
			pt.setId(Integer.parseInt(request.getParameter("id")));
			pt.setName(request.getParameter("name"));
			pt.setSortid(Integer.parseInt(request.getParameter("sortid")));
			
			productBrandDao.modifyProductBrand(pt);
			
			pr.setSuccess(true);
			pr.setMessage("保存成功");
		} catch (Exception ex) {
			pr.setSuccess(false);
			pr.setMessage(ex.getMessage());
		}
		return pr;
	}
	
	/**
	 * 查询品牌
	 */
	public ProductBrand queryProductBrand(HttpServletRequest request) {
		ProductBrand pt = null;
		try{
			int id = Integer.parseInt(request.getParameter("id"));
			pt = productBrandDao.queryProductBrand(id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return pt;
	}
	
	/**
	 * 查询品牌数量
	 */
	public int queryProductBrandCount(HttpServletRequest request) {
		int count = 0;
		try{
			String keyword = request.getParameter("keyword");
			count = productBrandDao.queryProductBrandCount(keyword);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return count;
	}
	
	/**
	 * 查询品牌列表
	 */
	public List<ProductBrand> queryProductBrandList(HttpServletRequest request) {
		List<ProductBrand> list = null;
		try{
			String keyword = request.getParameter("keyword");
			int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
			int pageSize = Integer.parseInt(request.getParameter("pageSize"));
			list = productBrandDao.queryProductBrandList(keyword, pageIndex, pageSize);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 通过政采云三级类目下的品牌列表
	 */
	public List<Object> getBrandListByZcy(Integer categoryId){
		JSONObject json = new JSONObject().put("categoryId", categoryId).put("zhBrandName", "%").put("enBrandName", "%");
		String result = ZcyCommonUtil.doPost(json, "zcy.category.brand.get");
		json = new JSONObject(result);
		List<Object> list = new ArrayList<Object>();
		if(json.has("data_response")){
			JSONArray jsonArray = json.getJSONArray("brands");
			for (Object obj : jsonArray) {
				JSONObject j = new JSONObject(obj);
				
			}
		}
		return list;
	}
	

	
}