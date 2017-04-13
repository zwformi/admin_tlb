package com.yunrer.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunrer.dao.ProductDao;
import com.yunrer.entity.Product;
import com.yunrer.service.ProductService;
import com.yunrer.service.ZcyOrderService;
import com.yunrer.service.ZcyService;
import com.yunrer.util.ZcyCommonUtil;
import com.yunrer.util.ZcyConfig;
import com.yunrer.util.zcy.openapi.zoss.util.JsonUtil;

/**
 * 政采云同步Control
 * @ClassName ZcyControl
 * @Description 
 * @author rujun
 * @modified zgf
 * @date 2016-12-22
 */


@Controller
@RequestMapping("/zcy")
@SuppressWarnings("unchecked")
public class ZcyControl {
	@Resource
	private ZcyService zcyService;
	@Resource
	private ProductService productService;
	@Resource
	private ZcyOrderService zcyOrderService;
	
	
//	    private static Logger eLogger = Logger.getLogger("ErrorLog");  
	
	/**
	 * 同步/更新商品到政采云
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/syncProduct", method = RequestMethod.POST)
	@ResponseBody
	public Map synchronizeProduct(HttpServletRequest request,HttpServletResponse response) {
		Map map = new HashMap();
		String result = zcyService.syncProduct(request, response);
		map.put("result", result);
//		zcyService.aa(request, response);
		return map;
	}
	
	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
	@ResponseBody
	public String addProduct(HttpServletRequest request,HttpServletResponse response) {
		Map map = new HashMap();
		String result = zcyService.serachForProduct(request,response);
		return result;
	}
	
	@RequestMapping(value = "/addrdata/{code}", method = RequestMethod.POST)
	@ResponseBody
	public String getAddress(HttpServletRequest request,@PathVariable("code") String code) {
		String s =ZcyCommonUtil.sendGet("http://www.staging.zcy.gov.cn/api/address/streets?regionId="+code, "");
		return s;
	}
	
	
	@RequestMapping(value = "/updateAttrTemplate/{categoryId}", method = RequestMethod.POST)
	@ResponseBody
	public String updateAttrTemplate(HttpServletRequest request,@PathVariable("categoryId") Integer categoryId) {
		zcyService.delAttrFromZcy(categoryId);
		zcyService.saveAttrFromZcy(categoryId);
		return "";
	}
	
	@RequestMapping(value = "/updateState", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> updateState(HttpServletRequest request,HttpServletResponse response) {
		Map<String,Object> resultMap = new HashMap<String, Object>();
		String result = zcyService.updateState(request, response);
		resultMap.put("result", result);
		return resultMap;
	}
	
	/**
	 * zcy更新目录attr
	 * @Title: updateAttrTemplateByName 
	 * @Description:
	 * @param request
	 * @param categoryId
	 * @return String         
	 * @throws
	 */
	@RequestMapping(value = "/updateAttrTemplateByName/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> updateAttrTemplateByName(HttpServletRequest request,@PathVariable("id") Integer id) {
			Map<String,Object> resultMap = new HashMap<String, Object>();
			String result = zcyService.updateAttrFromZcy(id);
			resultMap.put("result", result);
			return resultMap;
		
		
	}
	
	/**
	 * 获取商品数据
	 * @Title: getProductData 
	 * @Description:
	 * @param request
	 * @param id
	 * @return String         
	 * @throws
	 */
	@RequestMapping(value = "/getProductData/{id_str}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getProductData(HttpServletRequest request,@PathVariable("id_str") String id_str) {
		Map<String,Object> map = new HashMap<String, Object>();
		try{
			
			map = zcyService.getProductData(id_str);
			map.put("resultCode", 1);
			return map;
			
		}catch(Exception e){
			e.getStackTrace();
			map.put("resultCode", 0);
			map.put("resultMsg", "查询失败");
			return map;		
			}
		
	}
	
	/**
	 * 获取商品状态
	 * @Title: getProductStatus 
	 * @Description:
	 * @param request
	 * @param id_str
	 * @return Map<String,Object>         
	 * @throws
	 */
	@RequestMapping(value = "/getProductStatus/{id_str}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getProductStatus(HttpServletRequest request,@PathVariable("id_str") String id_str) {
		Map<String,Object> map = new HashMap<String, Object>();
		try{
			
			map = zcyService.getProductStatus(id_str);
			return map;
			
		}catch(Exception e){
			e.getStackTrace();
			map.put("resultCode", 0);
			map.put("resultMsg", map.get("resultMsg")+"查询失败!");
			return map;		
			}
		
	}
	
	
	@RequestMapping(value = "/getDepositRate", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getDepositRate(HttpServletRequest request) {
			String rate = ZcyConfig.instance().DEPOSIT_RATE;
			Map<String,Object> resultMap = new HashMap<String, Object>();
			if(rate!=null && !rate.isEmpty()){
				resultMap.put("resultCode", 1);
				resultMap.put("depositRate", rate);
				
				
			}else{
				resultMap.put("resultCode", 0);
				resultMap.put("resultMsg", "获取折扣率失败！");
				
			}

		return resultMap;
	}
	
	/**
	 * 从政采云获取订单列表
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getorderlist",method=RequestMethod.POST)
	public Map<String,Object> getOrderList(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String, Object>();
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		Integer status = Integer.parseInt(request.getParameter("status"));
		Integer pageNo = request.getParameter("pageNo")==null?null:Integer.parseInt(request.getParameter("pageNo"));
		Integer pageSize = request.getParameter("pageSize")==null?null:Integer.parseInt(request.getParameter("pageSize"));
		map = zcyOrderService.getOrderList(start, end, status, pageNo, pageSize);
		return map;

	}
	/**
	 * 从政采云获取订单详情
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getorderdetail")
	public Map<String,Object> getOrderDetail(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String, Object>();
		Long id = request.getParameter("id")==null?null:Long.parseLong(request.getParameter("id"));
		map = zcyOrderService.getOrderDetail(id);
		return map;
		
	}
	
	/**
	 * 接受/确认政采云订单
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/acceptorder",method=RequestMethod.POST)
	public Map<String,Object> acceptOrder(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String, Object>();
		Long id = request.getParameter("id")==null?null:Long.parseLong(request.getParameter("id"));
		map = zcyOrderService.acceptOrder(id);
		return map;
		
	}
	/**
	 * 查询已经确认的发货单ID
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getconfirmbill",method=RequestMethod.POST)
	public Map<String,Object> getConfirmWayBill(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String, Object>();
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		map = zcyOrderService.getConfirmWayBill(start,end);
		return map;
		
	}
	/**
	 * 查询发货单详情detail
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getWayBillDetail",method=RequestMethod.POST)
	public Map<String,Object> getWayBillDetail(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String, Object>();
		Long id = request.getParameter("id")==null?null:Long.parseLong(request.getParameter("id"));
		map = zcyOrderService.getWayBillDetail(id);
		return map;
		
	}
	
	/**
	 * 更新所有大类和模板
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/updateAllTypesandTemplate",method=RequestMethod.POST)
	public Map<String,Object> updateAllTypesandTemplate(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String, Object>();
		map = zcyService.updateAllTypesandTemplate();
		return map;
		
	}
	
	@ResponseBody
	@RequestMapping(value="/add2zcy",method=RequestMethod.POST)
	public Map<String,Object> add2zcy(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String, Object>();
		zcyService.getZcyId();
		return map;
		
	}
}
