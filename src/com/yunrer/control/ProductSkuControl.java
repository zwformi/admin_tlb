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

import com.yunrer.entity.ProductSku;
import com.yunrer.service.ProductSkuService;
import com.yunrer.service.ProductSkuService;
/**
 * 子商品管理
 * @ClassName ProductSkuControl
 * @Description 
 * @author rujun
 * @date 2016-12-16
 */
@Controller
@RequestMapping("/productsku")
public class ProductSkuControl {

	@Resource
	private ProductSkuService service;
	
	/**
	 * 查询子商品列表
	 * @Title: getProductSkuList 
	 * @Description:
	 * @param request
	 * @param response
	 * @return List<ProductSku>         
	 * @throws
	 */
	@RequestMapping(value="/list",method=RequestMethod.POST)
	public @ResponseBody List<ProductSku> getProductSkuList(HttpServletRequest request,HttpServletResponse response){
		int id = request.getParameter("product_id")==null?-1:Integer.parseInt(request.getParameter("product_id"));
		if(id>0)
		return  service.getProductSkuList(id);
		else
			return null;
	}
	
	/**
	 * 查询子商品
	 * @Title: getProductSku 
	 * @Description:
	 * @param request
	 * @param response
	 * @return ProductSku         
	 * @throws
	 */
	@RequestMapping(value="/item",method=RequestMethod.POST)
	public @ResponseBody ProductSku getProductSku(HttpServletRequest request,HttpServletResponse response){
		int id = request.getParameter("id")==null?-1:Integer.parseInt(request.getParameter("id"));
		return service.getProductSku(id);
		
	}
	
	/**
	 * 新增子商品
	 * @Title: addProductSku 
	 * @Description:
	 * @param request
	 * @param response
	 * @return Map<String,Object>         
	 * @throws
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> addProductSku(HttpServletRequest request,HttpServletResponse response){
		int num = 0;
		Map<String,Object> resultMap = new HashMap<String, Object>();
		num = service.addProductSku(request);
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
	 * @Title: updateProductSku 
	 * @Description:
	 * @param request
	 * @param response
	 * @return Map<String,Object>         
	 * @throws
	 */
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> updateProductSku(HttpServletRequest request,HttpServletResponse response){
		int num = 0;
		Map<String,Object> resultMap = new HashMap<String, Object>();
		num = service.updateProductSku2(request);
		if(num>0){
			resultMap.put("res_code", num);
			resultMap.put("resultMsg", "success");
		}else{
			resultMap.put("resultMsg", "failure");
		}
		return resultMap;
	}
	

	
	/**
	 * 删除子商品
	 * @Title: delSku 
	 * @Description:
	 * @param request
	 * @return Map<String,Object>         
	 * @throws
	 */
	@RequestMapping(value="/delitem",method=RequestMethod.GET)
	public @ResponseBody  Map<String,Object>  delSku(HttpServletRequest request){
		int num = 0;
		int id= -1;
		Map<String,Object> resultMap = new HashMap<String, Object>();
		if(request.getParameter("product_items_id")!=null){
			id = Integer.parseInt(request.getParameter("product_items_id"));
			num = service.deleteSku(id);
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
