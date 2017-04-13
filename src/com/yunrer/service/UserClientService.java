package com.yunrer.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.yunrer.common.ProcessResult;
import com.yunrer.dao.UserClientDao;
import com.yunrer.dao.UserInfoDao;
import com.yunrer.entity.UserClient;
import com.yunrer.entity.UserInfo;

/**
 * 客服人员Service
 * @ClassName UserClientService
 * @Description 
 * @author rujun
 * @date 2016-12-21
 */
@Service("UserClientService")
public class UserClientService {

	@Resource
	private UserClientDao userClientDao;
	
	@Resource
	private UserInfoDao userInfoDao;
	
	/**
	 * 新增客服人员
	 * @Title: addUserClient 
	 * @Description:
	 * @param request
	 * @return ProcessResult         
	 * @throws
	 */
	public ProcessResult addUserClient(HttpServletRequest request) {
		ProcessResult pr = new ProcessResult();
		try{
			UserClient uc = new UserClient();
			uc.setName(request.getParameter("name"));
			uc.setPhone(request.getParameter("phone"));
			uc.setQq(request.getParameter("qq"));
			uc.setTelphone(request.getParameter("telphone"));
			uc.setEmail(request.getParameter("email"));
			uc.setGh(request.getParameter("gh"));
			userClientDao.addUserClient(uc);
			
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
	 * 删除客服人员
	 * @Title: deleteUserClient 
	 * @Description:
	 * @param request
	 * @return ProcessResult         
	 * @throws
	 */
	public ProcessResult deleteUserClient(HttpServletRequest request) {
		ProcessResult pr = new ProcessResult();
		try{
			String id = request.getParameter("id");
			Object[] ids = id.split(",");
			
			userClientDao.deleteUserClient(ids);
			
			pr.setSuccess(true);
			pr.setMessage("删除成功");
		} catch (Exception ex) {
			pr.setSuccess(false);
			pr.setMessage(ex.getMessage());
		}
		return pr;
	}
	
	/**
	 * 更新客服人员
	 * @Title: modifyUserClient 
	 * @Description:
	 * @param request
	 * @return ProcessResult         
	 * @throws
	 */
	public ProcessResult modifyUserClient(HttpServletRequest request) {
		ProcessResult pr = new ProcessResult();
		try{
			UserClient uc = new UserClient();
			uc.setId(Integer.parseInt(request.getParameter("id")));
			uc.setName(request.getParameter("name"));
			uc.setPhone(request.getParameter("phone"));
			uc.setQq(request.getParameter("qq"));
			uc.setTelphone(request.getParameter("telphone"));
			uc.setEmail(request.getParameter("email"));
			uc.setGh(request.getParameter("gh"));
			userClientDao.modifyUserClient(uc);
			
			pr.setSuccess(true);
			pr.setMessage("保存成功");
		} catch (Exception ex) {
			pr.setSuccess(false);
			pr.setMessage(ex.getMessage());
		}
		return pr;
	}
	
	/**
	 * 查询客服人员
	 * @Title: queryUserClient 
	 * @Description:
	 * @param request
	 * @return UserClient         
	 * @throws
	 */
	public UserClient queryUserClient(HttpServletRequest request) {
		UserClient pt = null;
		try{
			int id = Integer.parseInt(request.getParameter("id"));
			pt = userClientDao.queryUserClient(id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return pt;
	}
	
	/**
	 * 查询客服人员
	 * @Title: queryUserClient2 
	 * @Description:
	 * @param request
	 * @return UserClient         
	 * @throws
	 */
	public UserClient queryUserClient2(HttpServletRequest request) {
		UserClient pt = null;
		try{
			int id = Integer.parseInt(request.getParameter("id"));
			
			UserInfo userinfo = userInfoDao.queryUserInfo(id);
			
			if (userinfo != null && userinfo.getClient_id() != 0) {
				pt = userClientDao.queryUserClient(userinfo.getClient_id());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return pt;
	}
	
	/**
	 * 查询客服人员数量
	 * @Title: queryUserClientCount 
	 * @Description:
	 * @param request
	 * @return int         
	 * @throws
	 */
	public int queryUserClientCount(HttpServletRequest request) {
		int count = 0;
		try{
			String keyword = request.getParameter("keyword");
			count = userClientDao.queryUserClientCount(keyword);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return count;
	}
	
	/**
	 * 查询客服人员列表
	 * @Title: queryUserClientList 
	 * @Description:
	 * @param request
	 * @return List<UserClient>         
	 * @throws
	 */
	public List<UserClient> queryUserClientList(HttpServletRequest request) {
		List<UserClient> list = null;
		try{
			String keyword = request.getParameter("keyword");
			int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
			int pageSize = Integer.parseInt(request.getParameter("pageSize"));
			list = userClientDao.queryUserClientList(keyword, pageIndex, pageSize);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
	
}