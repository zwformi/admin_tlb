package com.yunrer.service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.yunrer.dao.UserInvoicesDao;
import com.yunrer.entity.UserInvoices;
/**
 * 用户发票service
 * @ClassName UserInvoicesService
 * @Description 
 * @author rujun
 * @date 2016-12-21
 */
@Service("UserInvoicesService")
public class UserInvoicesService {
	
	@Resource
	private UserInvoicesDao userInvoicesDao;

	/**
	 * 获取用户的发票信息
	 * @Title: queryInvoices 
	 * @Description:
	 * @param request
	 * @return UserInvoices         
	 * @throws
	 */
	public UserInvoices queryInvoices(HttpServletRequest request) {
		UserInvoices userInvoices = null;
		try {
			int user_id = Integer.parseInt(request.getParameter("user_id"));
			userInvoices = userInvoicesDao.queryInvoices(user_id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return userInvoices;
	}
}