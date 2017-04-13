package com.yunrer.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunrer.common.ProcessResult;
import com.yunrer.entity.ProductBrand;
import com.yunrer.service.ProductBrandService;

/**
 * 品牌管理
 * @author zhanglei
 */
@Controller
@RequestMapping("/brand")
@SuppressWarnings("unchecked")
public class ProductBrandControl {

	@Resource
	private ProductBrandService productBrandService;
	
	/**
	 * 新增品牌
	 * @Title: addProductBrand 
	 * @Description:
	 * @param request
	 * @return ProcessResult         
	 * @throws
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public ProcessResult addProductBrand(HttpServletRequest request) {
		return productBrandService.addProductBrand(request);
	}
	
	/**
	 * 删除品牌
	 * @Title: deleteProductBrand 
	 * @Description:
	 * @param request
	 * @return ProcessResult         
	 * @throws
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public ProcessResult deleteProductBrand(HttpServletRequest request) {
		return productBrandService.deleteProductBrand(request);
	}
	
	/**
	 * 更新品牌
	 * @Title: modifyProductBrand 
	 * @Description:
	 * @param request
	 * @return ProcessResult         
	 * @throws
	 */
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	@ResponseBody
	public ProcessResult modifyProductBrand(HttpServletRequest request) {
		return productBrandService.modifyProductBrand(request);
	}

	/**
	 * 查询品牌
	 * @Title: queryProductBrand 
	 * @Description:
	 * @param request
	 * @return ProductBrand         
	 * @throws
	 */
	@RequestMapping(value = "/query", method = RequestMethod.POST)
	@ResponseBody
	public ProductBrand queryProductBrand(HttpServletRequest request) {
		return productBrandService.queryProductBrand(request);
	}
	
	/**
	 * 查询品牌数量
	 * @Title: queryProductBrandCount 
	 * @Description:
	 * @param request
	 * @return Map         
	 * @throws
	 */
	@RequestMapping(value = "/queryCount", method = RequestMethod.POST)
	@ResponseBody
	public Map queryProductBrandCount(HttpServletRequest request) {
		Map map = new HashMap();
		map.put("count", productBrandService.queryProductBrandCount(request));
		return map;
	}
	
	/**
	 * 查询品牌列表
	 * @Title: queryProductBrandList 
	 * @Description:
	 * @param request
	 * @return List<ProductBrand>         
	 * @throws
	 */
	@RequestMapping(value = "/queryList", method = RequestMethod.POST)
	@ResponseBody
	public List<ProductBrand> queryProductBrandList(HttpServletRequest request) {
		List<ProductBrand> list = productBrandService.queryProductBrandList(request);
		return list;
	}
}