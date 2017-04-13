package com.yunrer.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunrer.entity.ProductItem;
import com.yunrer.service.ProductItemService;
/**
 * 子商品管理
 * @ClassName ProductItemControl
 * @Description 
 * @author rujun
 * @date 2016-12-16
 */
@Controller
@RequestMapping("/productitem")
public class ProductItemControl {

	@Resource
	private ProductItemService service;
	
	/**
	 * 查询子商品列表
	 * @Title: getProductItemList 
	 * @Description:
	 * @param request
	 * @param response
	 * @return List<ProductItem>         
	 * @throws
	 */
	@RequestMapping(value="/list",method=RequestMethod.POST)
	public @ResponseBody List<ProductItem> getProductItemList(HttpServletRequest request,HttpServletResponse response){
		int id = request.getParameter("product_id")==null?-1:Integer.parseInt(request.getParameter("product_id"));
		if(id>0)
		return  service.getProductItemList(id);
		else
			return null;
	}
	
	/**
	 * 查询子商品
	 * @Title: getProductItem 
	 * @Description:
	 * @param request
	 * @param response
	 * @return ProductItem         
	 * @throws
	 */
	@RequestMapping(value="/item",method=RequestMethod.POST)
	public @ResponseBody ProductItem getProductItem(HttpServletRequest request,HttpServletResponse response){
		int id = request.getParameter("id")==null?-1:Integer.parseInt(request.getParameter("id"));
		return service.getProductItem(id);
		
	}
	
	/**
	 * 新增子商品
	 * @Title: addProductItem 
	 * @Description:
	 * @param request
	 * @param response
	 * @return Map<String,Object>         
	 * @throws
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> addProductItem(HttpServletRequest request,HttpServletResponse response){
		int num = 0;
		Map<String,Object> resultMap = new HashMap<String, Object>();
		num = service.addProductItem(request);
		if(num>0){
			resultMap.put("res_code", num);
			resultMap.put("resultMsg", "success");
		}else{
			resultMap.put("resultMsg", "failure");
		}
		return resultMap;
	}
	
	/**
	 * 修改子商品
	 * @Title: updateProductItem 
	 * @Description:
	 * @param request
	 * @param response
	 * @return Map<String,Object>         
	 * @throws
	 */
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> updateProductItem(HttpServletRequest request,HttpServletResponse response){
		int num = 0;
		Map<String,Object> resultMap = new HashMap<String, Object>();
		num = service.updateProductItem(request);
		if(num>0){
			resultMap.put("res_code", num);
			resultMap.put("resultMsg", "success");
		}else{
			resultMap.put("resultMsg", "failure");
		}
		return resultMap;
	}
	
	/**
	 * 校验该配置子商品是否存在
	 * @Title: queryItem 
	 * @Description:
	 * @param request
	 * @return Map<String,Object>         
	 * @throws
	 */
	@RequestMapping(value="/checkproduct",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> queryItem(HttpServletRequest request){
		Map<String,Object> resultMap = new HashMap<String, Object>();
		ProductItem item = service.getProductItemByItem(request);
		if( item!=null){
			resultMap.put("res_code",1);
			resultMap.put("message", "success");
			resultMap.put("data", item);
		}else{
			resultMap.put("res_code",-1);
			resultMap.put("message", "failure");
		}
		return resultMap;
		
	}
	
	/**
	 * 删除子商品
	 * @Title: delItem 
	 * @Description:
	 * @param request
	 * @return Map<String,Object>         
	 * @throws
	 */
	@RequestMapping(value="/delitem",method=RequestMethod.GET)
	public @ResponseBody  Map<String,Object>  delItem(HttpServletRequest request){
		int num = 0;
		int id= -1;
		Map<String,Object> resultMap = new HashMap<String, Object>();
		if(request.getParameter("product_items_id")!=null){
			id = Integer.parseInt(request.getParameter("product_items_id"));
			num = service.deleteItem(id);
		}
		if(num>0){
			resultMap.put("res_code", num);
			resultMap.put("resultMsg", "success");
		}else{
			resultMap.put("resultMsg", "failure");
		}
		return resultMap;
	}
}
