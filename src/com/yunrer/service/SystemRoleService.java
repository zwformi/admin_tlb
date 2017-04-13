package com.yunrer.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yunrer.common.ProcessResult;
import com.yunrer.dao.RoleNodeDao;
import com.yunrer.dao.SystemRoleDao;
import com.yunrer.dao.SystemUserDao;
import com.yunrer.entity.RRoleNode;
import com.yunrer.entity.SystemNode;
import com.yunrer.entity.SystemRole;
import com.yunrer.entity.SystemUser;
/**
 * 系统角色service
 * @ClassName SystemRoleService
 * @Description 
 */
@Service("SystemRoleService")
@Transactional
public class SystemRoleService {

	@Resource
	private SystemRoleDao systemRoleDao;
	
	@Resource
	private RoleNodeDao roleNodeDao;
	
	@Resource
	private SystemUserDao systemUserDao;
	
	/**
	 * 新增系统角色
	 * @Title: addSystemRole 
	 * @Description:
	 * @param request
	 * @return ProcessResult         
	 * @throws
	 */
	public ProcessResult addSystemRole(HttpServletRequest request) {
		ProcessResult pr = new ProcessResult();
		try{
			SystemRole sr = new SystemRole();
			sr.setRole_name(request.getParameter("role_name"));
			sr.setRemarks(request.getParameter("remarks"));
			systemRoleDao.addSystemRole(sr);
			
			pr.setSuccess(true);
			pr.setMessage("保存成功");
		} catch (Exception ex) {
			ex.printStackTrace();
			pr.setSuccess(false);
			pr.setMessage(ex.getMessage());
		}
		return pr;
	}
	
	/**
	 * 删除系统角色
	 * @Title: deleteSystemRole 
	 * @Description:
	 * @param request
	 * @return ProcessResult         
	 * @throws
	 */
	public ProcessResult deleteSystemRole(HttpServletRequest request) {
		ProcessResult pr = new ProcessResult();
		try{
			String id = request.getParameter("id");
			Object[] ids = id.split(",");
			
			systemRoleDao.deleteSystemRole(ids);
			
			pr.setSuccess(true);
			pr.setMessage("删除成功");
		} catch (Exception ex) {
			pr.setSuccess(false);
			pr.setMessage(ex.getMessage());
		}
		return pr;
	}
	
	/**
	 * 更新系统角色
	 * @Title: modifySystemRole 
	 * @Description:
	 * @param request
	 * @return ProcessResult         
	 * @throws
	 */
	public ProcessResult modifySystemRole(HttpServletRequest request) {
		ProcessResult pr = new ProcessResult();
		try{
			SystemRole sr = new SystemRole();
			sr.setRole_id(Integer.parseInt(request.getParameter("id")));
			sr.setRole_name(request.getParameter("role_name"));
			sr.setRemarks(request.getParameter("remarks"));
			systemRoleDao.modifySystemRole(sr);
			
			pr.setSuccess(true);
			pr.setMessage("保存成功");
		} catch (Exception ex) {
			pr.setSuccess(false);
			pr.setMessage(ex.getMessage());
		}
		return pr;
	}
	
	/**
	 * 根据id查询系统角色
	 * @Title: querySystemRole 
	 * @Description:
	 * @param request
	 * @return SystemRole         
	 * @throws
	 */
	public SystemRole querySystemRole(HttpServletRequest request) {
		SystemRole sr = null;
		try{
			int id = Integer.parseInt(request.getParameter("id"));
			sr = systemRoleDao.querySystemRole(id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return sr;
	}
	
	/**
	 * 根据当前用户信息查询角色
	 * @Title: querySystemRole2 
	 * @Description:
	 * @param request
	 * @return SystemRole         
	 * @throws
	 */
	public SystemRole querySystemRole2(HttpServletRequest request) {
		SystemRole sr = null;
		try{
			int id = Integer.parseInt(request.getParameter("id"));
			SystemUser user = systemUserDao.queryUserInfo(id);
			if (user != null) {
				sr = systemRoleDao.querySystemRole(user.getRole_id());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return sr;
	}
	
	/**
	 * 查询系统角色数量
	 * @Title: querySystemRoleCount 
	 * @Description:
	 * @param request
	 * @return int         
	 * @throws
	 */
	public int querySystemRoleCount(HttpServletRequest request) {
		int count = 0;
		try{
			String keyword = request.getParameter("keyword");
			count = systemRoleDao.querySystemRoleCount(keyword);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return count;
	}
	
	/**
	 * 查询系统角色列表
	 * @Title: querySystemRoleList 
	 * @Description:
	 * @param request
	 * @return List<SystemRole>         
	 * @throws
	 */
	public List<SystemRole> querySystemRoleList(HttpServletRequest request) {
		List<SystemRole> list = null;
		try{
			String keyword = request.getParameter("keyword");
			int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
			int pageSize = Integer.parseInt(request.getParameter("pageSize"));
			list = systemRoleDao.querySystemRoleList(keyword, pageIndex, pageSize);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
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
	@Transactional
	public ProcessResult modifyRoleNode(HttpServletRequest request) {
		ProcessResult pr = new ProcessResult();
		try{
			int role_id = Integer.parseInt(request.getParameter("role_id"));
			//删除角色相关节点
			roleNodeDao.deleteRoleNode(role_id);
			
			String node_id = request.getParameter("node_id");
			String[] ids = node_id.split(",");
			
			List<RRoleNode> list = new ArrayList<RRoleNode>();
			for(String id : ids){
				RRoleNode rrn = new RRoleNode();
				rrn.setRole_id(role_id);
				rrn.setNode_id(Integer.parseInt(id));
				list.add(rrn);
			}
			//新增角色相关节点
			roleNodeDao.addRoleNode(list);
			
			pr.setSuccess(true);
			pr.setMessage("保存成功");
		} catch (Exception ex) {
			pr.setSuccess(false);
			pr.setMessage(ex.getMessage());
			throw new RuntimeException(ex.getMessage());
		}
		return pr;
	}
	
	/**
	 * 查询角色相关权限列表
	 * @Title: queryRoleNode 
	 * @Description:
	 * @param request
	 * @return List<SystemNode>         
	 * @throws
	 */
	public List<SystemNode> queryRoleNode(HttpServletRequest request) {
		List<SystemNode> list = null;
		try{
			int role_id = Integer.parseInt(request.getParameter("id"));
			list = roleNodeDao.queryRoleNode(role_id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
}