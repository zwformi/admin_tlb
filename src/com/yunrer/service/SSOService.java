package com.yunrer.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.yunrer.common.CommonMd5;
import com.yunrer.common.HTTPUtils;
import com.yunrer.common.ProcessResult;
import com.yunrer.common.Utils;
import com.yunrer.dao.RoleNodeDao;
import com.yunrer.dao.SSODao;
import com.yunrer.dao.SystemUserDao;
import com.yunrer.entity.SystemNode;
import com.yunrer.entity.SystemUser;

/**
 * SSO的Service
 * @ClassName SSOService
 * @Description 
 */
@Service("SSOService")
public class SSOService {
	
	@Resource
	private SSODao ssoDao;
	
	@Resource
	private RoleNodeDao roleNodeDao;

	/**
	 * 创建SSO登陆session
	 * @Title: createUser 
	 * @Description:
	 * @param request
	 * @param session
	 * @return boolean         
	 * @throws
	 */
	public boolean createUser( HttpServletRequest request, HttpSession session) {
				String authtocken = request.getParameter("authtocken");
				SystemUser loginUser = ssoDao.getSystemUser(authtocken);
				if(loginUser!=null){
					session.setAttribute("loginUser", loginUser);	
					// 查询用户权限
					Map<String, Boolean> authMap = new HashMap<String, Boolean>();
					List<SystemNode> auth_list = roleNodeDao.queryRoleAuth(loginUser.getRole_id());
					for (SystemNode sn : auth_list) {
						if (sn.getKey_name() != null && !"".equals(sn.getKey_name())) {
							authMap.put(sn.getKey_name(), true);
						}
					}
					session.setAttribute("authMap", authMap);
					return true;
				}
				return false;
				
	}
		
	

	
	
	
}