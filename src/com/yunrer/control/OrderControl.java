package com.yunrer.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yunrer.entity.OrderInfo;
import com.yunrer.entity.UserInfo;
import com.yunrer.entity.YunDetails;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunrer.service.OrderService;

/**
 * 合同管理
 * @author wangpeng
 */
@Controller
@RequestMapping("/order")
@SuppressWarnings("unchecked")
public class OrderControl{
	
	@Resource
	private OrderService orderService;
	
	/**
	 * 查询合同数量
	 * @Title: queryCount 
	 * @Description:
	 * @param request
	 * @param session
	 * @return Map         
	 * @throws
	 */
	@RequestMapping(value = "/queryCount", method = RequestMethod.POST)
	@ResponseBody
	public Map queryCount(HttpServletRequest request,HttpSession session) {
		Map map = new HashMap();
		map.put("count", orderService.queryOrderCount(request,session));
		return map;
	}
	
	/**
	 * 查询合同列表
	 * @Title: queryList 
	 * @Description:
	 * @param request
	 * @param session
	 * @return List<OrderInfo>         
	 * @throws
	 */
	@RequestMapping(value = "/queryList", method = RequestMethod.POST)
	@ResponseBody
	public List<OrderInfo> queryList(HttpServletRequest request,HttpSession session) {
		List<OrderInfo> list = orderService.queryOrderList(request,session);
		return list;
	}
	
	/**
	 * 修改订单合同
	 * @Title: updateOrder 
	 * @Description:
	 * @param request
	 * @param session
	 * @param orderinfo
	 * @return Map         
	 * @throws
	 */
	@RequestMapping(value = "/update",method = RequestMethod.POST)
	@ResponseBody
	public Map updateOrder(HttpServletRequest request,HttpSession session,OrderInfo orderinfo) {
		Map<String,Object> map = new HashMap<String,Object>();
		try{
				map.put("count",orderService.updateOrder(orderinfo));
			
		}catch(Exception e){
			e.getStackTrace();
			
			
		}
		return map;
	}
	
	   
	/**
	 * 生成订单合同
	 * @Title: doProductOrder 
	 * @Description:
	 * @param request
	 * @param session
	 * @return String         
	 * @throws
	 */
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	public String doProductOrder(HttpServletRequest request,HttpSession session) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
				return orderService.addOrder(request, session);
		}catch(Exception ex){
			return "error";
		}
	}
	
	/**
	 * 删除订单合同
	 * @Title: deleteProductOrder 
	 * @Description:
	 * @param request
	 * @param session
	 * @return Map<String,Object>         
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/delete",method = RequestMethod.POST)
	public Map<String,Object> deleteProductOrder(HttpServletRequest request,HttpSession session) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			resultMap.put("count",orderService.deleteOrder(request, session));
		}catch(Exception ex){
			resultMap.put("error", true);
			resultMap.put("message", "删除订单合同失败");
		}
		return resultMap;
	}
	
	/**
	 * 订单合同--配货   -弃用
	 */
	/**
	@ResponseBody
	@RequestMapping(value = "/site",method = RequestMethod.POST)
	public Map<String,Object> doSite(HttpServletRequest request,HttpSession session) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			resultMap.put("count",orderService.siteOrder(request, session));
		}catch(Exception ex){
			resultMap.put("error", true);
			resultMap.put("message", "操作配货失败");
		}
		return resultMap;
	}
	**/
	
	/**
	 * 订单合同--发货
	 * @Title: doSend 
	 * @Description:
	 * @param request
	 * @param session
	 * @return Map<String,Object>         
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/send",method = RequestMethod.POST)
	public Map<String,Object> doSend(HttpServletRequest request,HttpSession session) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			resultMap.put("count",orderService.sendOrder(request, session));
		}catch(Exception ex){
			resultMap.put("error", true);
			resultMap.put("message", "操作发货失败");
		}
		return resultMap;
	}
	
	/**
	 * 订单合同--维护应收款状态
	 * @Title: doUpdateYskzt 
	 * @Description:
	 * @param request
	 * @param session
	 * @return Map<String,Object>         
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/yskzt",method = RequestMethod.POST)
	public Map<String,Object> doUpdateYskzt(HttpServletRequest request,HttpSession session) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			resultMap.put("count",orderService.yskztOrder(request, session));
		}catch(Exception ex){
			resultMap.put("error", true);
			resultMap.put("message", "维护应收款状态失败");
		}
		return resultMap;
	}
	
	/**
	 * 订单合同--实施
	 * @Title: dossOrder 
	 * @Description:
	 * @param request
	 * @param session
	 * @return Map<String,Object>         
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/ss",method = RequestMethod.POST)
	public Map<String,Object> dossOrder(HttpServletRequest request,HttpSession session) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			resultMap.put("count",orderService.ssOrder(request, session));
		}catch(Exception ex){
			resultMap.put("error", true);
			resultMap.put("message", "操作实施失败");
		}
		return resultMap;
	}
	
	/**
	 * 查询订单合同详细信息
	 * @Title: query 
	 * @Description:
	 * @param request
	 * @param session
	 * @return Map<String,Object>         
	 * @throws
	 */
	@RequestMapping(value = "/query", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> query(HttpServletRequest request,HttpSession session) {
		return orderService.queryOrder_Detail(request, session);
	}
	
	/**
	 * 备货情况维护
	 * @Title: order_Bh 
	 * @Description:
	 * @param request
	 * @return Map         
	 * @throws
	 */
	@RequestMapping(value = "/order_bh", method = RequestMethod.POST)
	@ResponseBody
	public Map order_Bh(HttpServletRequest request) {
		Map map = new HashMap();
		map.put("count", orderService.order_Bh(request));
		return map;
	}
	
	/**
	 * 删除物流信息
	 * @Title: delSm 
	 * @Description:
	 * @param request
	 * @return Map         
	 * @throws
	 */
	@RequestMapping(value = "/delSm", method = RequestMethod.POST)
	@ResponseBody
	public Map delSm(HttpServletRequest request) {
		Map map = new HashMap();
		map.put("count", orderService.delSm(request));
		return map;
	}
	
	/**
	 * 查询订单合同评价信息
	 * @Title: querypj 
	 * @Description: 
	 * @param request
	 * @param session
	 * @return    设定文件 
	 * Map<String,Object>    返回类型 
	 * @throws
	 */
	@RequestMapping(value = "/querypj", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> querypj(HttpServletRequest request,HttpSession session) {
		return orderService.queryOrder_Pj(request, session);
	}
	
	/**
	 * 查询云订单的detail表详情
	 * @Title: queryDetails 
	 * @Description:
	 * @param request
	 * @param session
	 * @return Map<String,Object>         
	 * @throws
	 */
	@RequestMapping(value = "/query_yun_detail", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> queryDetails(HttpServletRequest request,HttpSession session) {
		return orderService.queryYunDetail(request, session);
	}
	
	
	/**
	 * 订单合同--代客户操作
	 * @Title: dodkhczOrder 
	 * @Description: 
	 * @param request
	 * @param session
	 * @return     
	 * @return Map<String,Object>    
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/dkhqr",method = RequestMethod.POST)
	public Map<String,Object> dodkhczOrder(HttpServletRequest request,HttpSession session) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			return orderService.dkhczOrder(request, session);
		}catch(Exception ex){
			resultMap.put("error", true);
			resultMap.put("message", "代客户操作失败");
		}
		return resultMap;
	}
	
	/**
	 * 订单合同--更新物流 
	 * @Title: updatePost 
	 * @Description: 
	 * @param request
	 * @param session
	 * @return     
	 * @return Map<String,Object>    
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/addPost",method = RequestMethod.POST)
	public Map<String,Object> updatePost(HttpServletRequest request,HttpSession session) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			resultMap.put("count",orderService.addPost(request, session));
		}catch(Exception ex){
			resultMap.put("error", true);
			resultMap.put("message", "添加物流操作失败");
		}
		return resultMap;
	}
	
	/**
	 * 订单合同--上传合同相关附件 
	 * @Title: doorder_file_upload 
	 * @Description:  
	 * @param request
	 * @param session
	 * @return     
	 * @return Map<String,Object>    
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/order_file_upload",method = RequestMethod.POST)
	public Map<String,Object> doorder_file_upload(HttpServletRequest request,HttpSession session) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			return orderService.orderFileUpdate(request, session);
		}catch(Exception ex){
			resultMap.put("error", true);
			resultMap.put("message", "合同保存失败");
		}
		return resultMap;
	}
	
	/**
	 * 订单合同--代客户开通
	 * @Title: doActivate 
	 * @Description: 
	 * @param request
	 * @param session
	 * @return     
	 * @return Map<String,Object>    
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/dkhkt",method = RequestMethod.POST)
	public Map<String,Object> doActivate(HttpServletRequest request,HttpSession session,YunDetails yundetails) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			resultMap.put("count",orderService.activateOrder(yundetails, session));
		}catch(Exception ex){
			resultMap.put("error", true);
			resultMap.put("message", "开通失败");
		}
		return resultMap;
	}
	

	/**
	 * 导出合同信息Excel
	 * @Title: exportOrderInfo 
	 * @Description: 
	 * @param request
	 * @param response     
	 * @return void    
	 * @throws
	 */
	@RequestMapping(value = "/exportOrderList", method = RequestMethod.GET)
	public void exportOrderInfo(HttpServletRequest request,HttpServletResponse response,HttpSession session) {
		String fileName = "/";
		Map<String,Object> map = new HashMap<String,Object>();
		try {
		
			map = orderService.exportOrder(request,response,session);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("error", true);
			map.put("message", "导出失败");
		}
		 
	}
	
	
}