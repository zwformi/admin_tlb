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

import com.yunrer.entity.YunModule;
import com.yunrer.service.ModuleService;

/**
 * 云模块管理
 * @ClassName ModuleControl
 * @Description 
 * @author rujun
 * @date 2016-12-16
 */
@Controller
@RequestMapping("/yun_module")
@SuppressWarnings("unchecked")
public class ModuleControl{
	
	@Resource
	private ModuleService moduleService;
	
	/**
	 * 查询模块数量
	 * @Title: queryCount 
	 * @Description:
	 * @param request
	 * @param session
	 * @return Map         
	 * @throws
	 */
	@RequestMapping(value = "/queryCount", method = RequestMethod.POST)
	@ResponseBody
	public Map queryCount(HttpServletRequest request,YunModule module) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			resultMap.put("count", moduleService.queryModuleCount(request,module));
		}catch(Exception e){
			e.getStackTrace();
			resultMap.put("error", true);
			resultMap.put("message", "查询模块数量失败");
			
		}
		return resultMap;
	}
	
	/**
	 * 查询模块列表
	 * @Title: queryList 
	 * @Description:
	 * @param request
	 * @param module
	 * @return List<YunModule>         
	 * @throws
	 */
	@RequestMapping(value = "/queryList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryList(HttpServletRequest request,YunModule module) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
		List<YunModule> list = moduleService.queryModuleList(request,module);
		resultMap.put("list",list);
		}catch(Exception e){
			e.getStackTrace();
			resultMap.put("error", true);
			resultMap.put("message", "查询模块失败");
			
		}
		return resultMap;
	}
	
	/**
	 * 新增模块
	 * @Title: addModule 
	 * @Description:
	 * @param request
	 * @param session
	 * @param m
	 * @return Map         
	 * @throws
	 */
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	@ResponseBody
	public Map addModule(HttpServletRequest request,HttpSession session,YunModule m) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
				resultMap.put("count",moduleService.addModule(m));
			
		}catch(Exception e){
			e.getStackTrace();
			resultMap.put("error", true);
			resultMap.put("message", "新建模块失败");
			
		}
		return resultMap;
	}
	
	/**
	 * 修改模块
	 * @Title: updateModule 
	 * @Description:
	 * @param request
	 * @param session
	 * @param m
	 * @return Map         
	 * @throws
	 */
	@RequestMapping(value = "/update",method = RequestMethod.POST)
	@ResponseBody
	public Map updateModule(HttpServletRequest request,HttpSession session,YunModule m) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
				resultMap.put("count",moduleService.updateModule(m));
			
		}catch(Exception e){
			e.getStackTrace();
			resultMap.put("error", true);
			resultMap.put("message", "修改模块失败");
			
		}
		return resultMap;
	}
	
	
	/**
	 * 删除模块
	 * @Title: deleteModule 
	 * @Description:
	 * @param request
	 * @param session
	 * @return Map<String,Object>         
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/delete",method = RequestMethod.POST)
	public Map<String,Object> deleteModule(HttpServletRequest request,HttpSession session) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			resultMap.put("count",moduleService.deleteModule(request, session));
		}catch(Exception ex){
			resultMap.put("error", true);
			resultMap.put("message", "删除模块失败");
		}
		return resultMap;
	}
	
	/**
	 * 查询模块详情
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
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			resultMap.put("module",moduleService.queryModuleDetails(request));
		}catch(Exception ex){
			resultMap.put("error", true);
			resultMap.put("message", "查询模块详情失败");
		}
		return resultMap;
	}
		
}