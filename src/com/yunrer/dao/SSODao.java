package com.yunrer.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.yunrer.entity.RRoleNode;
import com.yunrer.entity.SystemNode;
import com.yunrer.entity.SystemUser;

/**
 * SSO的DAO
 * @ClassName SSODao
 * @Description 
 * @author rujun
 * @date 2016-12-16
 */
@Repository("SSODao")
public class SSODao {

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 更新KEY获得系统用户信息
	 * @Title: getSystemUser 
	 * @Description:
	 * @param authtocken
	 * @return SystemUser         
	 * @throws
	 */
	public SystemUser getSystemUser(String authtocken){
		String sql = "select role_id,user_name from tbl_system_user where sso_key = ? limit 1;";
		Object[] params = new Object[] { authtocken };
		List<SystemUser> list = jdbcTemplate.query(sql, params,
				new BeanPropertyRowMapper<SystemUser>(SystemUser.class));
		if(list.size()>0)	{
			String sql2 = "update tbl_system_user set sso_key = null where sso_key = ?";
			jdbcTemplate.update(sql2,params);
			return (SystemUser)list.get(0);
		}
		
		return null;
	}
	
	/**
	 * 更新信息
	 * @Title: updateInfo 
	 * @Description:
	 * @param id
	 * @param role_id 
	 * @return void         
	 * @throws
	 */
	public void updateInfo(int id, int role_id) {
		String sql = "update tbl_system_user set role_id = ? where id = ?";
		Object[] params = new Object[] { role_id, id };
		jdbcTemplate.update(sql, params);
	}
	
	
}