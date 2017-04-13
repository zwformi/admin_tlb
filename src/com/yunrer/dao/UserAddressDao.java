package com.yunrer.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.yunrer.entity.UserAddress;

/**
 * 收货地址DAO
 * @ClassName UserAddressDao
 * @Description 
 * @author rujun
 * @date 2016-12-16
 */
@Repository("UserAddressDao")
public class UserAddressDao {
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 查询收货地址
	 * @Title: queryAddressList 
	 * @Description:
	 * @param user_id
	 * @return List<UserAddress>         
	 * @throws
	 */
	public List<UserAddress> queryAddressList(int user_id) {
		StringBuffer sql = new StringBuffer("select * from tbl_user_address where user_id = ?");
		Object[] params = new Object[] { user_id };
		List<UserAddress> list = jdbcTemplate.query(sql.toString(), params,
				new BeanPropertyRowMapper<UserAddress>(UserAddress.class));
		return list;
	}
}