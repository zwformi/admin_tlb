package com.yunrer.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yunrer.entity.OrderInfoXuqiu;
import com.yunrer.service.OrderXuqiuService;

import com.yunrer.common.FileUtils;

/**
 * 需求管理
 * @author wangpeng
 */
@Controller
@RequestMapping("/orderxuqiu")
@SuppressWarnings("unchecked")
public class OrderXuqiuControl {
	
	@Resource
	private OrderXuqiuService orderXuqiuService;
	
	/**
	 * 查询需求数量 
	 * @Title: queryCount 
	 * @Description: 
	 * @param request   
	 * @return Map    
	 * @throws
	 */
	@RequestMapping(value = "/queryCount", method = RequestMethod.POST)
	@ResponseBody
	public Map queryCount(HttpServletRequest request,HttpSession session) {
		Map map = new HashMap();
		map.put("count", orderXuqiuService.queryOrderXuqiuCount(request,session));
		return map;
	}

	/**
	 * 查询需求列表
	 * @Title: queryList 
	 * @Description:
	 * @param request
	 * @return     
	 * @return List<OrderInfoXuqiu>    
	 * @throws
	 */
	@RequestMapping(value = "/queryList", method = RequestMethod.POST)
	@ResponseBody
	public List<OrderInfoXuqiu> queryList(HttpServletRequest request,HttpSession session) {
		List<OrderInfoXuqiu> list = orderXuqiuService.queryOrderXuqiuList(request,session);
		return list;
	}
	
	/**
	 * 查询需求详细信息
	 * @Title: query 
	 * @Description:
	 * @param request
	 * @param session
	 * @return     
	 * @return Map<String,Object>    
	 * @throws
	 */
	@RequestMapping(value = "/query", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> query(HttpServletRequest request,HttpSession session) {
		return orderXuqiuService.queryOrder_Xuqiu_Detail(request, session);
	}
	
	/**
	 * 新增需求产品信息
	 * @Title: addOrderXuqiu 
	 * @Description: 
	 * @param request
	 * @return     
	 * @return Map    
	 * @throws
	 */
	@RequestMapping(value = "/addorderxuqiu", method = RequestMethod.POST)
	@ResponseBody
	public Map addOrderXuqiu(HttpServletRequest request) {
		Map map = new HashMap();
		map.put("id", orderXuqiuService.addOrderXuqiu(request));
		return map;
	}
	
	/**
	 * 删除需求产品信息
	 * @Title: delOrderXuqiu 
	 * @Description:
	 * @param request
	 * @return     
	 * @return Map    
	 * @throws
	 */
	@RequestMapping(value = "/deleteorderxuqiu", method = RequestMethod.POST)
	@ResponseBody
	public Map delOrderXuqiu(HttpServletRequest request) {
		Map map = new HashMap();
		map.put("count", orderXuqiuService.delOrderXuqiu(request));
		return map;
	}
	
	/**
	 * 需求报价
	 * @Title: orderXuqiu_Bj 
	 * @Description:
	 * @param request
	 * @return     
	 * @return Map    
	 * @throws
	 */
	@RequestMapping(value = "/orderxuqiu_bj", method = RequestMethod.POST)
	@ResponseBody
	public Map orderXuqiu_Bj(HttpServletRequest request) {
		Map map = new HashMap();
		map.put("count", orderXuqiuService.orderXuqiu_Bj(request));
		return map;
	}
	
	/**
	 * @Title: deleteProductOrder 
	 * @Description:删除需求单
	 * @param request
	 * @param session
	 * @return     
	 * @return Map<String,Object>    
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/delete",method = RequestMethod.POST)
	public Map<String,Object> deleteProductOrder(HttpServletRequest request,HttpSession session) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			resultMap.put("count",orderXuqiuService.deleteOrderXuqiu(request, session));
		}catch(Exception ex){
			resultMap.put("error", true);
			resultMap.put("message", "删除订单合同失败");
		}
		return resultMap;
	}
	
	/**
	 * 整理需求,弃用
	 * @Title: doMake 
	 * @Description:
	 * @param request
	 * @param session
	 * @return     
	 * @return Map<String,Object>    
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/makeup",method = RequestMethod.POST)
	public Map<String,Object> doMake(HttpServletRequest request,HttpSession session) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			request.setAttribute("product_name", "需求单"+request.getParameter("order_number"));
			request.setAttribute("product_count","1");
			
			resultMap.put("count",orderXuqiuService.addOrderXuqiuOffer(request));
			
		}catch(Exception ex){
			resultMap.put("error", true);
			resultMap.put("message", "操作整理需求失败");
		}
		return resultMap;
	}
	
	/**
	 * 关闭-需求单
	 * @Title: doSite 
	 * @Description:
	 * @param request
	 * @param session
	 * @return     
	 * @return Map<String,Object>    
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/cancel",method = RequestMethod.POST)
	public Map<String,Object> doSite(HttpServletRequest request,HttpSession session) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			resultMap.put("count",orderXuqiuService.cancelOrderXuqiu(request, session));
		}catch(Exception ex){
			resultMap.put("error", true);
			resultMap.put("message", "操作关闭需求单失败");
		}
		return resultMap;
	}
	
}