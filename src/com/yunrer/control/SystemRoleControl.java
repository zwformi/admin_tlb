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
import com.yunrer.entity.SystemNode;
import com.yunrer.entity.SystemRole;
import com.yunrer.service.SystemRoleService;

/**
 * 系统角色管理
 * @author zhanglei
 */
@Controller
@RequestMapping("/system_role")
@SuppressWarnings("unchecked")
public class SystemRoleControl {

	@Resource
	private SystemRoleService systemRoleService;
	
	/**
	 * 新增系统角色
	 * @Title: addSystemRole 
	 * @Description:
	 * @param request
	 * @return ProcessResult         
	 * @throws
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public ProcessResult addSystemRole(HttpServletRequest request) {
		return systemRoleService.addSystemRole(request);
	}
	
	/**
	 * 删除系统角色
	 * @Title: deleteSystemRole 
	 * @Description:
	 * @param request
	 * @return ProcessResult         
	 * @throws
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public ProcessResult deleteSystemRole(HttpServletRequest request) {
		return systemRoleService.deleteSystemRole(request);
	}
	
	/**
	 * 更新系统角色
	 * @Title: modifySystemRole 
	 * @Description:
	 * @param request
	 * @return ProcessResult         
	 * @throws
	 */
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	@ResponseBody
	public ProcessResult modifySystemRole(HttpServletRequest request) {
		return systemRoleService.modifySystemRole(request);
	}

	/**
	 * 根据id查询系统角色
	 * @Title: querySystemRole 
	 * @Description:
	 * @param request
	 * @return SystemRole         
	 * @throws
	 */
	@RequestMapping(value = "/query", method = RequestMethod.POST)
	@ResponseBody
	public SystemRole querySystemRole(HttpServletRequest request) {
		return systemRoleService.querySystemRole(request);
	}
	
	/**
	 * 根据当前用户查询系统角色
	 * @Title: querySystemRole2 
	 * @Description:
	 * @param request
	 * @return SystemRole         
	 * @throws
	 */
	@RequestMapping(value = "/query2", method = RequestMethod.POST)
	@ResponseBody
	public SystemRole querySystemRole2(HttpServletRequest request) {
		return systemRoleService.querySystemRole2(request);
	}
	
	/**
	 * 查询系统角色数量
	 * @Title: querySystemRoleCount 
	 * @Description:
	 * @param request
	 * @return Map         
	 * @throws
	 */
	@RequestMapping(value = "/queryCount", method = RequestMethod.POST)
	@ResponseBody
	public Map querySystemRoleCount(HttpServletRequest request) {
		Map map = new HashMap();
		map.put("count", systemRoleService.querySystemRoleCount(request));
		return map;
	}
	
	/**
	 * 查询系统角色列表
	 * @Title: querySystemRoleList 
	 * @Description:
	 * @param request
	 * @return List<SystemRole>         
	 * @throws
	 */
	@RequestMapping(value = "/queryList", method = RequestMethod.POST)
	@ResponseBody
	public List<SystemRole> querySystemRoleList(HttpServletRequest request) {
		List<SystemRole> list = systemRoleService.querySystemRoleList(request);
		return list;
	}
	
	/**
	 * 更新角色权限配置
	 * @Title: modifyRoleNode 
	 * @Description:
	 * @param request
	 * @return ProcessResult         
	 * @throws
	 */
	@RequestMapping(value = "/modifyRoleNode", method = RequestMethod.POST)
	@ResponseBody
	public ProcessResult modifyRoleNode(HttpServletRequest request) {
		return systemRoleService.modifyRoleNode(request);
	}
	
	/**
	 * 查询角色相关节点列表
	 * @Title: queryRoleNode 
	 * @Description:
	 * @param request
	 * @return List<SystemNode>         
	 * @throws
	 */
	@RequestMapping(value = "/queryRoleNode", method = RequestMethod.POST)
	@ResponseBody
	public List<SystemNode> queryRoleNode(HttpServletRequest request) {
		List<SystemNode> list = systemRoleService.queryRoleNode(request);
		return list;
	}
}