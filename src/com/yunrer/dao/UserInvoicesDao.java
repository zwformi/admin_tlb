package com.yunrer.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.yunrer.entity.UserInvoices;
/**
 * 用户发票DAO
 * @ClassName UserInvoicesDao
 * @Description 
 * @author rujun
 * @date 2016-12-19
 */
@Repository("UserInvoicesDao")
public class UserInvoicesDao {
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 查询用户发票
	 * @Title: queryInvoices 
	 * @Description:
	 * @param user_id
	 * @return UserInvoices         
	 * @throws
	 */
	public UserInvoices queryInvoices(int user_id) {
		StringBuffer sql = new StringBuffer("select * from tbl_user_invoices where user_id = ?");
		Object[] params = new Object[] { user_id };
		List<UserInvoices> list = jdbcTemplate.query(sql.toString(), params,
				new BeanPropertyRowMapper<UserInvoices>(UserInvoices.class));
		if(null!=list&&!list.isEmpty()){
			return list.get(0);
		}else{
			return null;
		}
	}
}