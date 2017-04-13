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

import com.yunrer.common.ProcessResult;
import com.yunrer.entity.SystemUser;
import com.yunrer.service.SystemUserService;

/**
 * 系统用户管理
 * @author zhanglei
 */
@Controller
@RequestMapping("/system_user")
@SuppressWarnings("unchecked")
public class SystemUserControl {

	@Resource
	private SystemUserService systemUserService;
	
	/**
	 * 新增用户
	 * @Title: addUserInfo 
	 * @Description:
	 * @param request
	 * @return ProcessResult         
	 * @throws
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public ProcessResult addUserInfo(HttpServletRequest request) {
		return systemUserService.addUserInfo(request);
	}
	
	/**
	 * 删除用户
	 * @Title: deleteUserInfo 
	 * @Description:
	 * @param request
	 * @return ProcessResult         
	 * @throws
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public ProcessResult deleteUserInfo(HttpServletRequest request) {
		return systemUserService.deleteUserInfo(request);
	}
	
	/**
	 * 修改用户信息（图灵云同步）
	 * @Title: modifyUserInfo 
	 * @Description:
	 * @param request
	 * @return ProcessResult         
	 * @throws
	 */
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	@ResponseBody
	public ProcessResult modifyUserInfo(HttpServletRequest request) {
		return systemUserService.modifyUserInfo(request);
	}
	
	/**
	 * 修改用户信息
	 * @Title: modifyUserInfo2 
	 * @Description:
	 * @param request
	 * @return ProcessResult         
	 * @throws
	 */
	@RequestMapping(value = "/modify2", method = RequestMethod.POST)
	@ResponseBody
	public ProcessResult modifyUserInfo2(HttpServletRequest request) {
		return systemUserService.modifyUserInfo2(request);
	}
	
	/**
	 * 更新用户状态
	 * @Title: modifyUserStatus 
	 * @Description:
	 * @param request
	 * @return ProcessResult         
	 * @throws
	 */
	@RequestMapping(value = "/modifyStatus", method = RequestMethod.POST)
	@ResponseBody
	public ProcessResult modifyUserStatus(HttpServletRequest request) {
		return systemUserService.modifyUserStatus(request);
	}
	
	/**
	 * 查询用户
	 * @Title: queryUserInfo 
	 * @Description:
	 * @param request
	 * @return SystemUser         
	 * @throws
	 */
	@RequestMapping(value = "/query", method = RequestMethod.POST)
	@ResponseBody
	public SystemUser queryUserInfo(HttpServletRequest request) {
		return systemUserService.queryUserInfo(request);
	}
	
	/**
	 * 查询用户数量
	 * @Title: queryUserInfoCount 
	 * @Description:
	 * @param request
	 * @return Map         
	 * @throws
	 */
	@RequestMapping(value = "/queryCount", method = RequestMethod.POST)
	@ResponseBody
	public Map queryUserInfoCount(HttpServletRequest request) {
		Map map = new HashMap();
		map.put("count", systemUserService.queryUserInfoCount(request));
		return map;
	}
	
	/**
	 * 查询用户列表
	 * @Title: queryUserInfoList 
	 * @Description:
	 * @param request
	 * @return List<SystemUser>         
	 * @throws
	 */
	@RequestMapping(value = "/queryList", method = RequestMethod.POST)
	@ResponseBody
	public List<SystemUser> queryUserInfoList(HttpServletRequest request) {
		List<SystemUser> list = systemUserService.queryUserInfoList(request);
		return list;
	}
	
	/**
	 * 重置密码
	 * @Title: resetPassword 
	 * @Description:
	 * @param request
	 * @return ProcessResult         
	 * @throws
	 */
	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	@ResponseBody
	public ProcessResult resetPassword(HttpServletRequest request) {
		ProcessResult pr = systemUserService.resetPassword(request);
		return pr;
	}
	
	/**
	 * 修改密码
	 * @Title: editPassword 
	 * @Description:
	 * @param request
	 * @param session
	 * @return ProcessResult         
	 * @throws
	 */
	@RequestMapping("/editPassword")
	@ResponseBody
	public ProcessResult editPassword(HttpServletRequest request, HttpSession session) {
		ProcessResult pr = systemUserService.modifyUserPassword(request, session);
		return pr;
	}
	
}