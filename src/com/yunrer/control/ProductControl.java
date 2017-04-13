package com.yunrer.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunrer.entity.Product;
import com.yunrer.entity.ProductAttrTemplate;
import com.yunrer.entity.ProductBrand;
import com.yunrer.entity.ProductImage;
import com.yunrer.entity.ProductParam;
import com.yunrer.entity.ProductType;
import com.yunrer.entity.SelectTree;
import com.yunrer.entity.YunDetails;
import com.yunrer.service.ProductImageService;
import com.yunrer.service.ProductParamService;
import com.yunrer.service.ProductService;

/**
 * 商品管理
 * @author wangpeng
 */
@Controller
@RequestMapping("/product")
@SuppressWarnings("unchecked")
public class ProductControl {
	
	@Resource
	private ProductService productService;
	
	@Resource
	private ProductParamService productParamService;
	
	@Resource
	private ProductImageService productImageService;
	
	/**
	 * 新增商品信息
	 * @Title: addProduct 
	 * @Description:
	 * @param request
	 * @return Map         
	 * @throws
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Map addProduct(HttpServletRequest request,HttpServletResponse response) {
		Map map = new HashMap();
		int[] list = productService.addProduct(request);
		if(list!=null){
			map.put("count", list[0]);
			map.put("id", list[1]);
			return map;
		}
		map.put("count", 0);
		map.put("id", 0);
		return map;
		
	}
	
	/**
	 * 查询内购商品公司列表
	 * @Title: querySpecialProducts 
	 * @Description:
	 * @param request
	 * @param response
	 * @return Map         
	 * @throws
	 */
	@RequestMapping(value = "/special_products", method = RequestMethod.POST)
	@ResponseBody
	public List querySpecialProducts(HttpServletRequest request,HttpServletResponse response) {
		List list = productService.querySpecialProduct(request);
		
		return list;
		
	}
	
	/**
	 * 修改内购商品价格
	 * @Title: editSpecialProducts 
	 * @Description:
	 * @param request
	 * @param response
	 * @return int         
	 * @throws
	 */
	@RequestMapping(value = "/edit_special_products", method = RequestMethod.POST)
	@ResponseBody
	public int editSpecialProducts(HttpServletRequest request,HttpServletResponse response) {
		int count = productService.editSpecial(request);
		
		return count;
		
	}
	
	/**
	 * 删除内购商品价格
	 * @Title: delSpecialProducts 
	 * @Description:
	 * @param request
	 * @param response
	 * @return int         
	 * @throws
	 */
	@RequestMapping(value = "/del_special_product", method = RequestMethod.POST)
	@ResponseBody
	public int delSpecialProducts(HttpServletRequest request,HttpServletResponse response) {
		int count = productService.delSpecial(request);
		
		return count;
		
	}
	
	/**
	 * 查询商品详细信息
	 * @Title: queryProduct 
	 * @Description:
	 * @param request
	 * @return Product         
	 * @throws
	 */
	@RequestMapping(value = "/query", method = RequestMethod.POST)
	@ResponseBody
	public Product queryProduct(HttpServletRequest request) {
		return productService.queryProduct(request);
	}
	

	@RequestMapping(value = "/query_attrs", method = RequestMethod.POST)
	@ResponseBody
	public List queryAttrs(HttpServletRequest request) {
		return productService.queryAttrs(request);
	}
	
	/**
	 * 查询商品数量
	 * @Title: queryProductCount 
	 * @Description:
	 * @param request
	 * @return Map         
	 * @throws
	 */
	@RequestMapping(value = "/queryCount", method = RequestMethod.POST)
	@ResponseBody
	public Map queryProductCount(HttpServletRequest request) {
		Map map = new HashMap();
		map.put("count", productService.queryProductCount(request));
		return map;
	}
	
	

	
	/**
	 * 查询商品列表
	 * @Title: queryProductList 
	 * @Description:
	 * @param request
	 * @return List<Product>         
	 * @throws
	 */
	@RequestMapping(value = "/queryList", method = RequestMethod.POST)
	@ResponseBody
	public List<Product> queryProductList(HttpServletRequest request) {
		List<Product> list = productService.queryProductList(request);
		return list;
	}
	
	/**
	 * 更新商品信息
	 * @Title: modifyProduct 
	 * @Description:
	 * @param request
	 * @return Map         
	 * @throws
	 */
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	@ResponseBody
	public Map modifyProduct(HttpServletRequest request) {
		Map map = new HashMap();
		map.put("count", productService.modifyProduct(request));
		return map;
	}
	
	/**
	 * 修改Attr属性
	 * @Title: modifyProductAttrs 
	 * @Description:
	 * @param request
	 * @return Map         
	 * @throws
	 */
	@RequestMapping(value = "/modifyAttrs", method = RequestMethod.POST)
	@ResponseBody
	public Map modifyProductAttrs(HttpServletRequest request) {
		Map map = new HashMap();
		map.put("count", productService.modifyAttrs(request));
		return map;
	}
	
	/**
	 * 添加Attr属性
	 * @Title: modifyProductAttrs 
	 * @Description:
	 * @param request
	 * @return Map         
	 * @throws
	 */
	@RequestMapping(value = "/addAttrs", method = RequestMethod.POST)
	@ResponseBody
	public Map addProductAttrs(HttpServletRequest request) {
		Map map = new HashMap();
		try{
			map.put("count", productService.addAttrs(request));
		}catch(Exception e){
			
		}
		return map;
	}
	
	/**
	 * 删除商品信息
	 * @Title: delProduct 
	 * @Description:
	 * @param request
	 * @return Map         
	 * @throws
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public Map delProduct(HttpServletRequest request) {
		Map map = new HashMap();
		map.put("count", productService.delProduct(request));
		return map;
	}
	
	
	/**
	 * 查询商品类型列表-树形菜单
	 * @Title: queryTypesList 
	 * @Description:
	 * @param request
	 * @return List<SelectTree>         
	 * @throws
	 */
	@RequestMapping(value = "/types", method = RequestMethod.POST)
	@ResponseBody
	public List<SelectTree> queryTypesList(HttpServletRequest request) {
		List<SelectTree> list = productService.queryProductTypeList(request);
		return list;
	}
	
	/**
	 * 查询商品品牌列表
	 * @Title: queryBrandList 
	 * @Description:
	 * @param request
	 * @return List<ProductBrand>         
	 * @throws
	 */
	@RequestMapping(value = "/brand", method = RequestMethod.POST)
	@ResponseBody
	public List<ProductBrand> queryBrandList(HttpServletRequest request) {
		List<ProductBrand> list = productService.queryProductBrandList(request);
		return list;
	}
	
	/**
	 * 查询商品产品参数列表
	 * @Title: queryProductParamList 
	 * @Description:
	 * @param request
	 * @return List<ProductParam>         
	 * @throws
	 */
	@RequestMapping(value = "/queryParamList", method = RequestMethod.POST)
	@ResponseBody
	public List<ProductParam> queryProductParamList(HttpServletRequest request) {
		List<ProductParam> list = productParamService.queryProductParamList(request);
		return list;
	}
	
	/**
	 * 新增产品参数信息
	 * @Title: addProductParam 
	 * @Description:
	 * @param request
	 * @return Map         
	 * @throws
	 */
	@RequestMapping(value = "/addParam", method = RequestMethod.POST)
	@ResponseBody
	public Map addProductParam(HttpServletRequest request) {
		Map map = new HashMap();
		map.put("id", productParamService.addProductParam(request));
		return map;
	}
	
	/**
	 * 添加内购商品
	 * @Title: addSepcial 
	 * @Description:
	 * @param request
	 * @return Map         
	 * @throws
	 */
	@RequestMapping(value = "/addSpecial", method = RequestMethod.POST)
	@ResponseBody
	public Map addSepcial(HttpServletRequest request) {
		Map map = new HashMap();
		map.put("id", productService.addSpecial(request));
		return map;
	}
	
	/**
	 * 删除产品参数信息
	 * @Title: delProductParam 
	 * @Description:
	 * @param request
	 * @return Map         
	 * @throws
	 */
	@RequestMapping(value = "/deleteParam", method = RequestMethod.POST)
	@ResponseBody
	public Map delProductParam(HttpServletRequest request) {
		Map map = new HashMap();
		map.put("count", productParamService.delProductParam(request));
		return map;
	}
	
	/**
	 * 查询产品图片列表
	 * @Title: queryProductImageList 
	 * @Description:
	 * @param request
	 * @return List<ProductImage>         
	 * @throws
	 */
	@RequestMapping(value = "/queryImageList")
	@ResponseBody
	public List<ProductImage> queryProductImageList(HttpServletRequest request) {
		List<ProductImage> list = productImageService.queryProductImageList(request);
		return list;
	}
	
	/**
	 * 新增产品图片信息
	 * @Title: addProductImage 
	 * @Description:
	 * @param request
	 * @return Map         
	 * @throws
	 */
	@RequestMapping(value = "/addImage", method = RequestMethod.POST)
	@ResponseBody
	public Map addProductImage(HttpServletRequest request) {
		Map map = new HashMap();
		map.put("id", productImageService.addProductImage(request));
		return map;
	}
	
	/**
	 * 删除产品图片信息
	 * @Title: delProductImage 
	 * @Description:
	 * @param request
	 * @return Map         
	 * @throws
	 */
	@RequestMapping(value = "/deleteImage", method = RequestMethod.POST)
	@ResponseBody
	public Map delProductImage(HttpServletRequest request) {
		Map map = new HashMap();
		map.put("count", productImageService.delProductImage(request));
		return map;
	}
	
	/**
	 * 将图片设置为封面
	 * @Title: setAsDefault 
	 * @Description:
	 * @param request
	 * @return Map         
	 * @throws
	 */
	@RequestMapping(value = "/coverImg", method = RequestMethod.POST)
	@ResponseBody
	public Map setAsDefault(HttpServletRequest request) {
		Map map = new HashMap();
		map.put("count", productImageService.setDefaultImage(request));
		return map;
	}
	
	/**
	 * 添加模板选项数据
	 * @Title: addAttrVals 
	 * @Description:
	 * @param request
	 * @param val
	 * @param attr_id
	 * @return Map<String,Object>         
	 * @throws
	 */
	@RequestMapping(value = "/addAttrVals", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> addAttrVals(HttpServletRequest request) {
		Map<String,Object> map = new HashMap<String, Object>();
		String val = request.getParameter("attrVals");
		Integer attr_id = Integer.parseInt(request.getParameter("attr_id")==null?"0":request.getParameter("attr_id"));
		
		try{
			
			map = productService.addAttrVals(val,attr_id);
			
			
		}catch(Exception e){
			e.getStackTrace();
			map.put("resultCode", 0);
			map.put("resultMsg", "添加失败");
				
			}
		return map;
	}
	
	/**
	 * 删除模板选项数据
	 * @Title: delAttrVals 
	 * @Description:
	 * @param request
	 * @param val
	 * @param attr_id
	 * @return Map<String,Object>         
	 * @throws
	 */
	@RequestMapping(value = "/delAttrVals", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> delAttrVals(HttpServletRequest request) {
		Map<String,Object> map = new HashMap<String, Object>();
		String val = request.getParameter("attrVals");
		Integer attr_id = Integer.parseInt(request.getParameter("attr_id")==null?"0":request.getParameter("attr_id"));
		
		try{
			
			map = productService.delAttrVals(val,attr_id);
			
			
		}catch(Exception e){
			e.getStackTrace();
			map.put("resultCode", 0);
			map.put("resultMsg", "删除失败");
				
			}
		return map;
	}
	
	/**
	 * 根据三级类目id查询属性信息
	 * @Title: queryProductAttribute 
	 * @Description:
	 * @param request
	 * @param categoryId
	 * @return Map<String,Object>         
	 * @throws
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/query_attr/{categoryId}",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object>  queryProductAttribute(HttpServletRequest request,@PathVariable("categoryId") Integer categoryId){
		Map<String,Object> resultMap = new HashMap<String, Object>();
		try{
			List list = productService.queryProductAttribute(categoryId);
			if(list!=null && !list.isEmpty()){
				resultMap.put("success", true);
				resultMap.put("list",list);
				
			}
			
		}catch(Exception e){
			
			resultMap.put("error", true);
			resultMap.put("message","查询出现错误：<br>"+e.getMessage());
		}
		return resultMap;
	}
	
	/**
	 * 查询模板表(分页用)
	 * @Title: queryProductAttribute 
	 * @Description:
	 * @param request
	 * @param categoryId
	 * @return Map<String,Object>         
	 * @throws
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/queryTempList",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object>  queryProductAttribute(HttpServletRequest request,ProductAttrTemplate temp){
		Map<String,Object> resultMap = new HashMap<String, Object>();
		try{
			int pageIndex = Integer.parseInt((String) (request.getParameter("pageIndex")==""?0:request.getParameter("pageIndex")));
			int pageSize = Integer.parseInt((String) (request.getParameter("pageSize")==""?0:request.getParameter("pageSize")));
			
			List list = productService.queryTempByTemp(temp,pageIndex,pageSize);
			if(list!=null && !list.isEmpty()){
				resultMap.put("resultCode", 1);
				resultMap.put("list",list);
				
			}
			
		}catch(Exception e){
			
			resultMap.put("resultCode", 0);
			resultMap.put("resultMsg","查询出现错误：<br>"+e.getMessage());
		}
		return resultMap;
	}
	
	/**
	 * 查询模板数量(分页用)
	 * @Title: queryTempForCount 
	 * @Description:
	 * @param request
	 * @param temp
	 * @return Map<String,Object>         
	 * @throws
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/queryTempForCount",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object>  queryTempForCount(HttpServletRequest request,ProductAttrTemplate temp){
		Map<String,Object> resultMap = new HashMap<String, Object>();
		try{	
			int count = productService.queryTempForCount(temp);
				resultMap.put("resultCode", 1);
				resultMap.put("count",count);
		}catch(Exception e){
			resultMap.put("resultCode", 0);
			resultMap.put("resultMsg","查询出现错误：<br>"+e.getMessage());
			e.getStackTrace();
		}
		return resultMap;
	}


	/**
	 * 更新attrval
	 * @Title: updateAttrTemplateByName 
	 * @Description:
	 * @param request
	 * @param attr_id
	 * @param attrVals
	 * @return Map<String,Object>         
	 * @throws
	 */
	@RequestMapping(value = "/updateAttrVal", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> updateAttrTemplateByName(HttpServletRequest request) {
			Map<String,Object> resultMap = new HashMap<String, Object>();
			String attrVals = request.getParameter("attrVals");
			Integer attr_id = Integer.parseInt(request.getParameter("attr_id")==null?"0":request.getParameter("attr_id"));
			try{
			int count = productService.updateAttrVals(attr_id, attrVals);
			if(count==1){
				resultMap.put("resultCode", 1);
				resultMap.put("resultMsg", "修改成功！");
			}else{
				resultMap.put("resultCode", 0);
				resultMap.put("resultMsg", "修改失败！");
			}
			}catch(Exception e){
				resultMap.put("resultCode", 0);
				resultMap.put("resultMsg", "修改失败！"+e.getMessage());
				System.out.println(e.getMessage());
				System.out.println(e.getStackTrace());
			}
			return resultMap;
		
		
	}
	
	/**
	 * 获取地域列表
	 * @Title: getAreaList 
	 * @Description:
	 * @param request
	 * @return Map<String,Object>         
	 * @throws
	 */
	@RequestMapping(value = "/getAreaList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getAreaList(HttpServletRequest request) {
		Map<String,Object> resultMap = new HashMap<String, Object>();
		int parentCode = Integer.parseInt(request.getParameter("parentCode")==null?"-1":request.getParameter("parentCode"));
		int level = Integer.parseInt(request.getParameter("level"));
		try{
		List list = productService.getAreaList(parentCode,level);
		resultMap.put("resultCode", 1);
		resultMap.put("result", list);
		}catch(Exception e){
			resultMap.put("resultCode", 0);
			resultMap.put("resultMsg", "获取地域列表失败");
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		return resultMap;
	
	
}
	
}