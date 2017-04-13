package com.yunrer.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunrer.common.ProcessResult;
import com.yunrer.entity.ProductAttrTemplate;
import com.yunrer.entity.ProductType;
import com.yunrer.service.ProductService;
import com.yunrer.service.ProductTypeService;
import com.yunrer.util.BaseLogger;

/**
 * 商品分类管理
 * @author zhanglei
 */
@Controller
@RequestMapping("/product_type")
@SuppressWarnings("unchecked")
public class ProductTypeControl extends BaseLogger{

	@Resource
	private ProductTypeService productTypeService;
	
	/**
	 * 新增商品分类
	 * @Title: addProductType 
	 * @Description:
	 * @param request
	 * @return ProcessResult         
	 * @throws
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public ProcessResult addProductType(HttpServletRequest request) {
		return productTypeService.addProductType(request);
	}
	
	/**
	 * 删除商品分类
	 * @Title: deleteProductType 
	 * @Description:
	 * @param request
	 * @return ProcessResult         
	 * @throws
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public ProcessResult deleteProductType(HttpServletRequest request) {
		return productTypeService.deleteProductType(request);
	}
	

	/**
	 * 更新商品分类
	 * @Title: modifyProductType 
	 * @Description:
	 * @param request
	 * @return ProcessResult         
	 * @throws
	 */
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	@ResponseBody
	public ProcessResult modifyProductType(HttpServletRequest request) {
		return productTypeService.modifyProductType(request);
	}

	/**
	 * 查询商品分类
	 * @Title: queryProductType 
	 * @Description:
	 * @param request
	 * @return ProductType         
	 * @throws
	 */
	@RequestMapping(value = "/query", method = RequestMethod.POST)
	@ResponseBody
	public ProductType queryProductType(HttpServletRequest request) {
		return productTypeService.queryProductType(request);
	}
	
	/**
	 * 查询分类数量
	 * @Title: queryProductTypeCount 
	 * @Description:
	 * @param request
	 * @return Map         
	 * @throws
	 */
	@RequestMapping(value = "/queryCount", method = RequestMethod.POST)
	@ResponseBody
	public Map queryProductTypeCount(HttpServletRequest request) {
		Map map = new HashMap();
		map.put("count", productTypeService.queryProductTypeCount(request));
		return map;
	}
	

	/**
	 * 树形加载
	 * @Title: queryForTree 
	 * @Description:
	 * @param request
	 * @return List<ProductType>         
	 * @throws
	 */
	@RequestMapping(value = "/queryForTree", method = RequestMethod.POST)
	@ResponseBody
	public List<ProductType> queryForTree(HttpServletRequest request) {
		List<ProductType> list = productTypeService.queryForTree(request);
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
	@RequestMapping(value = "/queryList", method = RequestMethod.POST)
	@ResponseBody
	public List<ProductType> queryProductTypeList(HttpServletRequest request) {
		List<ProductType> list = productTypeService.queryProductTypeList(request);
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
	@RequestMapping(value = "/queryListForTree", method = RequestMethod.POST)
	@ResponseBody
	public List<ProductType> queryProductTypeListForTree(HttpServletRequest request) {
		List<ProductType> list = productTypeService.queryProductTypeListForTree(request);
		return list;
	}

	/**
	 * 查询下拉列表
	 * @Title: queryProductTypeList 
	 * @Description:
	 * @return List<ProductType>         
	 * @throws
	 */
	@RequestMapping(value = "/queryListBySelect", method = RequestMethod.POST)
	@ResponseBody
	public List<ProductType> queryProductTypeList() {
		List<ProductType> list = productTypeService.queryProductTypeList(0);
		return list;
	}
	
	
	@RequestMapping(value = "/updateTypeFromZcy/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> queryProductTypeList(HttpServletRequest request,@PathVariable("id") Integer id) {
		
		Map<String,Object> map = new HashMap<String, Object>();
		map = productTypeService.getFullMenu(id);
		return map;
	}
	
	@RequestMapping(value = "/getCategoryIds/{parentId}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getCategoryIds(HttpServletRequest request,@PathVariable("parentId") Integer parentId) {
		
		Map<String,Object> map = new HashMap<String, Object>();
		try{
		List<ProductType> list = productTypeService.getCategoryTypes(parentId);
		if(list!=null && list.size()>0){
			
			map.put("resultCode", 1);
			map.put("list", list);
		}else{
			
			map.put("resultCode", 0);
			map.put("resultMsg", "获取数据为空");
		}
		}catch(Exception e){
			
			logger.error(e);
			
		}
		return map;
	}
	
	
}