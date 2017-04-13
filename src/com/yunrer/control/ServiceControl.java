package com.yunrer.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunrer.entity.UserService;
import com.yunrer.service.UserServiceService;

/**
 * 服务单管理
 * @author wangpeng
 */
@Controller
@RequestMapping("/service")
@SuppressWarnings("unchecked")
public class ServiceControl {
	
	@Resource
	private UserServiceService userServiceService;
	
	/**
	 * 查询服务单数量
	 * @Title: queryCount 
	 * @Description:
	 * @param request
	 * @return Map         
	 * @throws
	 */
	@RequestMapping(value = "/queryCount", method = RequestMethod.POST)
	@ResponseBody
	public Map queryCount(HttpServletRequest request) {
		Map map = new HashMap();
		map.put("count", userServiceService.queryServicecount(request));
		return map;
	}
	
	/**
	 * 查询服务单列表
	 * @Title: queryList 
	 * @Description:
	 * @param request
	 * @return List<UserService>         
	 * @throws
	 */
	@RequestMapping(value = "/queryList", method = RequestMethod.POST)
	@ResponseBody
	public List<UserService> queryList(HttpServletRequest request) {
		List<UserService> list = userServiceService.queryServiceList(request);
		return list;
	}
	
	/**
	 * 删除服务单
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
			resultMap.put("count",userServiceService.deleteService(request, session));
		}catch(Exception ex){
			resultMap.put("error", true);
			resultMap.put("message", "删除服务单失败");
		}
		return resultMap;
	}
	
	/**
	 * 服务单--修改状态为已完成
	 * @Title: doSite 
	 * @Description:
	 * @param request
	 * @param session
	 * @return Map<String,Object>         
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/state",method = RequestMethod.POST)
	public Map<String,Object> doSite(HttpServletRequest request,HttpSession session) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			resultMap.put("count",userServiceService.updateState(request, session));
		}catch(Exception ex){
			resultMap.put("error", true);
			resultMap.put("message", "修改为已完成失败");
		}
		return resultMap;
	}
	
	/**
	 * 服务单--修改服务备注
	 * @Title: doUpdateComment 
	 * @Description:
	 * @param request
	 * @param session
	 * @return Map<String,Object>         
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/comment",method = RequestMethod.POST)
	public Map<String,Object> doUpdateComment(HttpServletRequest request,HttpSession session) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			resultMap.put("count",userServiceService.updateComment(request, session));
		}catch(Exception ex){
			resultMap.put("error", true);
			resultMap.put("message", "修改服务备注失败");
		}
		return resultMap;
	}
	
	/**
	 * 查询服务单详细信息
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
		return userServiceService.queryService(request, session);
	}
}