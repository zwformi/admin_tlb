package com.yunrer.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.yunrer.dao.UserAddressDao;
import com.yunrer.entity.UserAddress;
/**
 * 用户收货地址Service
 * @ClassName UserAddressService
 * @Description 
 * @author rujun
 * @date 2016-12-21
 */
@Service("UserAddressService")
public class UserAddressService {
	
	@Resource
	private UserAddressDao userAddressDao;

	/**
	 * 获取用户收货地址信息
	 * @Title: queryAddressList 
	 * @Description:
	 * @param request
	 * @return List<UserAddress>         
	 * @throws
	 */
	public List<UserAddress> queryAddressList(HttpServletRequest request) {
		List<UserAddress> userAddressList = null;
		try {
			int user_id = Integer.parseInt(request.getParameter("user_id"));
			userAddressList = userAddressDao.queryAddressList(user_id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return userAddressList;
	}
}