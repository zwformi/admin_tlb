package com.yunrer.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.yunrer.dao.ProductParamDao;
import com.yunrer.entity.ProductParam;

/**
 * 商品参数管理Service
 * @ClassName ProductParamService
 * @Description 
 * @author rujun
 * @date 2016-12-21
 */
@Service("ProductParamService")
public class ProductParamService {
	
	@Resource
	private ProductParamDao productParamDao;
	
	/**
	 * 新增产品参数信息
	 * @Title: addProductParam 
	 * @Description:
	 * @param request
	 * @return int         
	 * @throws
	 */
	public int addProductParam(HttpServletRequest request) {
		int id = 0;
		try{
			ProductParam productParam = new ProductParam();
			productParam.setProduct_id(Integer.parseInt(request.getParameter("product_id")));    
			productParam.setSortid(Integer.parseInt(request.getParameter("sortid")));      
			productParam.setPar_name(request.getParameter("par_name"));       
			productParam.setPar_value(request.getParameter("par_value"));       
			productParam.setImg_url("img_url");    
			id = productParamDao.addProductParam(productParam);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return id;
	}
	
	/**
	 * 查询产品参数列表
	 * @Title: queryProductParamList 
	 * @Description:
	 * @param request
	 * @return List<ProductParam>         
	 * @throws
	 */
	public List<ProductParam> queryProductParamList(HttpServletRequest request) {
		List<ProductParam> list = null;
		try{
			int product_id = Integer.parseInt(request.getParameter("product_id"));
			list = productParamDao.queryProductParamList(product_id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 删除产品参数
	 * @Title: delProductParam 
	 * @Description:
	 * @param request
	 * @return int         
	 * @throws
	 */
	public int delProductParam(HttpServletRequest request) {
		int count = 0;
		try{
			int id = Integer.parseInt(request.getParameter("id"));
			int product_id = Integer.parseInt(request.getParameter("product_id"));
			count = productParamDao.delProductParam(product_id,id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return count;
	}
}