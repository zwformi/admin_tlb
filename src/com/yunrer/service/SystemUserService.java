package com.yunrer.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.alibaba.druid.support.json.JSONUtils;
import com.yunrer.common.CommonMd5;
import com.yunrer.common.HTTPUtils;
import com.yunrer.common.ProcessResult;
import com.yunrer.common.Utils;
import com.yunrer.dao.RoleNodeDao;
import com.yunrer.dao.SystemUserDao;
import com.yunrer.entity.SystemNode;
import com.yunrer.entity.SystemUser;
import com.yunrer.util.BaseLogger;
import com.yunrer.util.ConnectionUtil;
import com.yunrer.util.MD5Util;
import com.yunrer.util.XmlUtil;
/**
 * 系统用户Service
 * @ClassName SystemUserService
 * @Description 
 * @author rujun
 * @date 2016-12-21
 */
@Service("SystemUserService")
public class SystemUserService extends BaseLogger {
	
	@Resource
	private SystemUserDao systemUserDao;
	
	@Resource
	private RoleNodeDao roleNodeDao;
	
	/**
	 * 新增用户
	 * @Title: addUserInfo 
	 * @Description:
	 * @param request
	 * @return ProcessResult         
	 * @throws
	 */
	public ProcessResult addUserInfo(HttpServletRequest request) {
		CommonMd5 md5 = new CommonMd5();
		ProcessResult pr = new ProcessResult();
		try{
			SystemUser user = new SystemUser();
			
			String user_name = request.getParameter("user_name");
			SystemUser v_user = systemUserDao.queryUserInfo(user_name, 1);
			if(v_user == null){
				user.setUser_name(user_name);
				//获取随机密码
				String init_password = String.valueOf(Utils.GetSixRandomNumber());
				user.setUser_password(md5.enCodeStringByKey(init_password,"key"));
				user.setUser_realname(request.getParameter("user_realname"));
				user.setUser_sex(Integer.parseInt(request.getParameter("user_sex")));
				user.setUser_dept(request.getParameter("user_dept"));
				user.setUser_phone(request.getParameter("user_phone"));
				user.setUser_email(request.getParameter("user_email"));
				
				/*******************同步到sso start*******************/

				//到图灵云接口上注册
				Map map = XmlUtil.paserXmlByDOM4J("\\resources\\common.xml");
				String Path = map.get("registerPath").toString();
				String Port = map.get("registerPort").toString();
				String Interface = map.get("registerInterface").toString();
				String requesturl = "http://"+Path+":"+Port+"/"+Interface;
				Map<String,String> paramsMap = new HashMap<String, String>();
				Map<String,String> userMap = new HashMap<String, String>();
				userMap.put("Mobile",user.getUser_phone());
				userMap.put("Password", init_password);
				userMap.put("Name", user.getUser_realname());
				userMap.put("UserName", user.getUser_name());
				userMap.put("ServerName", map.get("hostName").toString());
				String data = JSONUtils.toJSONString(userMap);
				String md5Str = MD5Util.MD5(data+map.get("privateToken"));
				paramsMap.put("data",data );
				paramsMap.put("checkstring", md5Str);
				String result = ConnectionUtil.postRequest(requesturl, paramsMap);
				JSONObject json = new JSONObject(result);
				if(json.getInt("ResultCode")==1){
					//注册用户
					user.setSso_id(json.getString("ResultData"));
					 systemUserDao.addUserInfo(user);
					 pr.setSuccess(true);
					 pr.setMessage("新增用户成功！用户名：" + user_name + "，初始密码为：" + init_password);
				}else{
					pr.setSuccess(false);
					pr.setMessage("新增用户失败！原因：" + json.getString("ResultMessage"));
					logger.warn(result);
				}
				
			
				/*******************同步到sso ehd*********************/

				
			}else{
				pr.setSuccess(false);
				pr.setMessage("新增用户失败！用户名：" + user_name + "已存在");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			pr.setSuccess(false);
			pr.setMessage(ex.getMessage());
		}
		return pr;
	}
	
	/**
	 * 删除用户
	 * @Title: deleteUserInfo 
	 * @Description:
	 * @param request
	 * @return ProcessResult         
	 * @throws
	 */
	public ProcessResult deleteUserInfo(HttpServletRequest request) {
		ProcessResult pr = new ProcessResult();
		try{
			String id = request.getParameter("id");
			Object[] ids = id.split(",");
			
			systemUserDao.deleteUserInfo(ids);
			
			pr.setSuccess(true);
			pr.setMessage("删除成功");
		} catch (Exception ex) {
			pr.setSuccess(false);
			pr.setMessage(ex.getMessage());
		}
		return pr;
	}
	
	/**
	 * 更新用户
	 * @Title: modifyUserInfo 
	 * @Description:
	 * @param request
	 * @return ProcessResult         
	 * @throws
	 */
	public ProcessResult modifyUserInfo(HttpServletRequest request) {
		ProcessResult pr = new ProcessResult();
		try{
			SystemUser user = new SystemUser();
			user.setId(Integer.parseInt(request.getParameter("id")));
			String sso_id = systemUserDao.queryUserInfo(user.getId()).getSso_id();
			user.setSso_id(sso_id);
			user.setUser_realname(request.getParameter("user_realname"));
			user.setUser_sex(Integer.parseInt(request.getParameter("user_sex")));
			user.setUser_dept(request.getParameter("user_dept"));
			user.setUser_phone(request.getParameter("user_phone"));
			user.setUser_email(request.getParameter("user_email"));
			//到图灵云接口上修改
			Map map = XmlUtil.paserXmlByDOM4J("\\resources\\common.xml");
			String Path = map.get("registerPath").toString();
			String Port = map.get("registerPort").toString();
			String Interface = map.get("updateInterface").toString();
			String requesturl = "http://"+Path+":"+Port+"/"+Interface;
			Map<String,String> paramsMap = new HashMap<String, String>();
			Map<String,String> userMap = new HashMap<String, String>();
			userMap.put("Mobile",user.getUser_phone());
			userMap.put("Name", user.getUser_realname());
			userMap.put("UserName ", user.getUser_name());
			userMap.put("ServerName", map.get("hostName").toString());
			String data = JSONUtils.toJSONString(userMap);
			String md5Str = MD5Util.MD5(data+map.get("privateToken"));
			paramsMap.put("data",data );
			paramsMap.put("systemuserid",user.getSso_id() );
			paramsMap.put("checkstring", md5Str);
			String result = ConnectionUtil.postRequest(requesturl, paramsMap);
			Map<String, Object> resMap = (Map<String, Object>)JSONUtils.parse(result);
			if(resMap.get("ResultCode").toString().equals("1")){
				 systemUserDao.modifyUserInfo(user);
				 pr.setSuccess(true);
				 pr.setMessage("修改用户成功！");
			}else{
				pr.setSuccess(false);
				pr.setMessage("修改用户失败，原因：" + resMap.get("ResultMessage"));
			}

		} catch (Exception ex) {
			pr.setSuccess(false);
			pr.setMessage(ex.getMessage());
		}
		return pr;
	}
	
	/**
	 * 更新用户，角色
	 * @Title: modifyUserInfo2 
	 * @Description:
	 * @param request
	 * @return ProcessResult         
	 * @throws
	 */
	public ProcessResult modifyUserInfo2(HttpServletRequest request) {
		ProcessResult pr = new ProcessResult();
		try{
			int id = Integer.parseInt(request.getParameter("id"));
			int role_id = Integer.parseInt(request.getParameter("role_id"));
			
			systemUserDao.modifyUserInfo2(id, role_id);
			
			pr.setSuccess(true);
			pr.setMessage("保存成功");
		} catch (Exception ex) {
			pr.setSuccess(false);
			pr.setMessage(ex.getMessage());
		}
		return pr;
	}
	
	/**
	 * 更新用户状态
	 * @Title: modifyUserStatus 
	 * @Description:
	 * @param request
	 * @return ProcessResult         
	 * @throws
	 */
	public ProcessResult modifyUserStatus(HttpServletRequest request) {
		ProcessResult pr = new ProcessResult();
		try{
			int id = Integer.parseInt(request.getParameter("id"));
			SystemUser user = systemUserDao.queryUserInfo(id);
			
			int typeid = Integer.parseInt(request.getParameter("typeid"));
			
			if (typeid == 1) {
				// 启用
				if (user.getIs_enable() == 1) {
					user.setIs_enable(0);
				}
			} else {
				// 禁用
				if (user.getIs_enable() == 0) {
					user.setIs_enable(1);
				}
			}
			
			systemUserDao.modifyUserStatus(user);
			
			pr.setSuccess(true);
			pr.setMessage("保存成功");
		} catch (Exception ex) {
			pr.setSuccess(false);
			pr.setMessage(ex.getMessage());
		}
		return pr;
	}
	
	/**
	 * 查询用户
	 * @Title: queryUserInfo 
	 * @Description:
	 * @param request
	 * @return SystemUser         
	 * @throws
	 */
	public SystemUser queryUserInfo(HttpServletRequest request) {
		SystemUser user = null;
		try{
			int id = Integer.parseInt(request.getParameter("id"));
			user = systemUserDao.queryUserInfo(id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return user;
	}

	/**
	 * 根据ssoid查询用户
	 * @Title: queryUserInfoBySsoId 
	 * @Description:
	 * @param sso_id
	 * @return SystemUser         
	 * @throws
	 */
	public SystemUser queryUserInfoBySsoId(String sso_id) {
		SystemUser user = null;
		try{
			
			user = systemUserDao.queryUserInfoBySsoId(sso_id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return user;
	}
	
	/**
	 * 用户登录
	 * @Title: userLogin 
	 * @Description:
	 * @param user
	 * @param request
	 * @param session
	 * @return ProcessResult         
	 * @throws
	 */
	public ProcessResult userLogin(SystemUser user, HttpServletRequest request, HttpSession session) {
		CommonMd5 md5 = new CommonMd5();
		ProcessResult pr = new ProcessResult();

		SystemUser loginUser = systemUserDao.queryUserInfo(user.getUser_name(), 0);
		if (loginUser != null && loginUser.getUser_name() != null && md5.deCodeStringBykey(loginUser.getUser_password(),"key").equals(user.getUser_password())) {
			if (loginUser.getIs_enable() == 1 || loginUser.getRole_id() == 0) {
				pr.setSuccess(false);
				pr.setMessage("帐号异常，请联系网站管理员");
				
			} else {
				pr.setSuccess(true);
				pr.setMessage("登陆成功");
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
				
				// 更新用户信息
				// 登录IP，登录次数，登录时间
				SystemUser changeUser = new SystemUser();
				changeUser.setId(loginUser.getId());
				String login_ip = HTTPUtils.getIpAddr(request);
				changeUser.setLogin_ip(login_ip);
				changeUser.setLogin_times(loginUser.getLogin_times() + 1);
				systemUserDao.modifyLoginInfo(changeUser);
			}
		} else {
			pr.setSuccess(false);
			pr.setMessage("帐号或密码错误，请重新输入");
		}
		return pr;
	}
	
	/**
	 * 重置密码
	 * @Title: resetPassword 
	 * @Description:
	 * @param request
	 * @return ProcessResult         
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public ProcessResult resetPassword(HttpServletRequest request) {
		CommonMd5 md5 = new CommonMd5();
		ProcessResult pr = new ProcessResult();
		try{
			int id = Integer.parseInt(request.getParameter("id"));
			SystemUser user = systemUserDao.queryUserInfo(id);
			
//			//获取随机密码
//			String init_password = String.valueOf(Utils.GetSixRandomNumber());
//			user.setUser_password(md5.enCodeStringByKey(init_password,"key"));
			/************************************/
			//到图灵云接口上修改
			Map map = XmlUtil.paserXmlByDOM4J("\\resources\\common.xml");
			String Path = map.get("registerPath").toString();
			String Port = map.get("registerPort").toString();
			String Interface = map.get("passwordResetInterface").toString();
			String requesturl = "http://"+Path+":"+Port+"/"+Interface;
			Map<String,String> userMap = new HashMap<String, String>();
			userMap.put("servername",map.get("hostName").toString());
			userMap.put("systemuserid", user.getSso_id());
			String md5Str = MD5Util.MD5(user.getSso_id()+map.get("privateToken"));
			userMap.put("checkstring", md5Str);
			String result = ConnectionUtil.postRequest(requesturl, userMap);
			Map<String, Object> resMap = (Map<String, Object>)JSONUtils.parse(result);
			if(resMap.get("ResultCode").toString().equals("1")){
				String init_password = resMap.get("ResultData").toString();
				user.setUser_password(md5.enCodeStringByKey(init_password,"key"));
				systemUserDao.modifyUserPassword(user);
				pr.setSuccess(true);
				pr.setMessage("重置成功！新密码为：" + init_password);
			}else{
				pr.setSuccess(false);
				pr.setMessage("重置密码失败，原因：" + resMap.get("ResultMessage"));
			}
			/************************************/

		} catch (Exception ex) {
			pr.setSuccess(false);
			pr.setMessage(ex.getMessage());
		}
		return pr;
	}
	
	/**
	 * 修改密码
	 * @Title: modifyUserPassword 
	 * @Description:
	 * @param request
	 * @param session
	 * @return ProcessResult         
	 * @throws
	 */
	public ProcessResult modifyUserPassword(HttpServletRequest request, HttpSession session) {
		CommonMd5 md5 = new CommonMd5();
		ProcessResult pr = new ProcessResult();
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		
		SystemUser loginUser = (SystemUser) session.getAttribute("loginUser");
		if (loginUser != null && loginUser.getUser_name() != null){
			if (md5.deCodeStringBykey(loginUser.getUser_password(),"key").equals(oldPassword)) {
				//
				//到图灵云上注册信息
				try{
				Map map = XmlUtil.paserXmlByDOM4J("\\resources\\common.xml");
				String Path = map.get("registerPath").toString();
				String Port = map.get("registerPort").toString();
				String Interface = map.get("passwordResetInterface").toString();
				String requesturl = "http://"+Path+":"+Port+"/"+Interface;
				Map<String,String> userMap = new HashMap<String, String>();
				userMap.put("servername",map.get("hostName").toString());
				userMap.put("systemuserid", loginUser.getSso_id());
				userMap.put("password",newPassword);
				String md5Str = MD5Util.MD5(loginUser.getSso_id()+map.get("privateToken"));
				userMap.put("checkstring", md5Str);
				String result = ConnectionUtil.postRequest(requesturl, userMap);
				Map<String, Object> resMap = (Map<String, Object>)JSONUtils.parse(result);
				if(resMap.get("ResultCode").toString().equals("1")){
					SystemUser user = new SystemUser();
					user.setUser_name(loginUser.getUser_name());
					user.setUser_password(md5.enCodeStringByKey(newPassword, "key"));
					systemUserDao.modifyUserPassword(user);
					loginUser.setUser_password(md5.enCodeStringByKey(newPassword, "key"));
					pr.setSuccess(true);
					pr.setMessage("修改成功");
				}
				}catch(Exception e){
					e.getStackTrace();
				}
				//

			} else {
				pr.setSuccess(false);
				pr.setMessage("原始密码输入有误");
			}
		} else {
			pr.setSuccess(false);
			pr.setMessage("修改失败");
		}
		return pr;
	}
	
	/**
	 * 查询用户数量
	 * @Title: queryUserInfoCount 
	 * @Description:
	 * @param request
	 * @return int         
	 * @throws
	 */
	public int queryUserInfoCount(HttpServletRequest request) {
		int count = 0;
		try{
			String keyword = request.getParameter("keyword");
			count = systemUserDao.queryUserInfoCount(keyword);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return count;
	}
	
	/**
	 * 查询用户列表
	 * @Title: queryUserInfoList 
	 * @Description:
	 * @param request
	 * @return List<SystemUser>         
	 * @throws
	 */
	public List<SystemUser> queryUserInfoList(HttpServletRequest request) {
		List<SystemUser> list = null;
		try{
			String keyword = request.getParameter("keyword");
			int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
			int pageSize = Integer.parseInt(request.getParameter("pageSize"));
			list = systemUserDao.queryUserInfoList(keyword, pageIndex, pageSize);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
}