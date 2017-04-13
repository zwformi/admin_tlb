package com.yunrer.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.yunrer.entity.SystemUser;
/**
 * 系统用户DAO
 * @ClassName SystemUserDao
 * @Description 
 * @author rujun
 * @date 2016-12-16
 */
@Repository("SystemUserDao")
public class SystemUserDao {
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 新增用户信息
	 * @Title: addUserInfo 
	 * @Description:
	 * @param user void         
	 * @throws
	 */
	public void addUserInfo(SystemUser user) {
		String sql = "insert into tbl_system_user (user_name,user_password,user_realname,user_sex,user_dept,user_phone,user_email,create_time,sso_id) values (?, ?, ?, ?, ?, ?, ?, now(),?)";
		Object[] params = new Object[] { user.getUser_name(), user.getUser_password(), user.getUser_realname(), user.getUser_sex(), user.getUser_dept(), user.getUser_phone(), user.getUser_email(),user.getSso_id() };
		jdbcTemplate.update(sql, params);
	}
	
	/**
	 * 删除用户信息
	 * @Title: deleteUserInfo 
	 * @Description:
	 * @param ids void         
	 * @throws
	 */
	public void deleteUserInfo(Object[] ids) {
		StringBuffer sql= new StringBuffer("delete from tbl_system_user where id in ("); 
		for (int i = 0; i < ids.length; i++) {
			if (i != 0)
				sql.append(",");
			sql.append("?");
		}
		sql.append(")");
		jdbcTemplate.update(sql.toString(), ids);
	}
	
	/**
	 * 更新用户信息
	 * @Title: modifyUserInfo 
	 * @Description:
	 * @param user void         
	 * @throws
	 */
	public void modifyUserInfo(SystemUser user) {
		String sql = "update tbl_system_user set user_realname = ?, user_sex = ?, user_dept = ?, user_phone = ?, user_email = ? where id = ?";
		Object[] params = new Object[] { user.getUser_realname(), user.getUser_sex(), user.getUser_dept(), user.getUser_phone(), user.getUser_email(), user.getId() };
		jdbcTemplate.update(sql, params);
	}
	
	/**
	 * 更新用户角色
	 * @Title: modifyUserInfo2 
	 * @Description:
	 * @param id
	 * @param role_id void         
	 * @throws
	 */
	public void modifyUserInfo2(int id, int role_id) {
		String sql = "update tbl_system_user set role_id = ? where id = ?";
		Object[] params = new Object[] { role_id, id };
		jdbcTemplate.update(sql, params);
	}
	
	/**
	 * 更新用户状态
	 * @Title: modifyUserStatus 
	 * @Description:
	 * @param user void         
	 * @throws
	 */
	public void modifyUserStatus(SystemUser user) {
		String sql = "update tbl_system_user set is_enable = ? where id = ?";
		Object[] params = new Object[] { user.getIs_enable(), user.getId() };
		jdbcTemplate.update(sql, params);
	}
	
	/**
	 * 更新登录信息
	 * @Title: modifyLoginInfo 
	 * @Description:
	 * @param user void         
	 * @throws
	 */
	public void modifyLoginInfo(SystemUser user) {
		String sql = "update tbl_system_user set login_ip = ?, last_logindate = now(), login_times = ? where id = ?";
		Object[] params = new Object[] { user.getLogin_ip(), user.getLogin_times(), user.getId() };
		jdbcTemplate.update(sql, params);
	}
	
	/**
	 * 更新用户密码
	 * @Title: modifyUserPassword 
	 * @Description:
	 * @param user void         
	 * @throws
	 */
	public void modifyUserPassword(SystemUser user) {
		String sql = "update tbl_system_user set user_password = ? where user_name = ?";
		Object[] params = new Object[] { user.getUser_password(), user.getUser_name() };
		jdbcTemplate.update(sql, params);
	}
	
	/**
	 * 根据用户ID查询用户信息
	 * @Title: queryUserInfo 
	 * @Description:
	 * @param id
	 * @return SystemUser         
	 * @throws
	 */
	public SystemUser queryUserInfo(int id) {
		String sql = "select * from tbl_system_user where id = ?";
		Object[] params = new Object[] { id };
		List<SystemUser> list = jdbcTemplate.query(sql, params,
				new BeanPropertyRowMapper<SystemUser>(SystemUser.class));
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}


	/**
	 * 根据SSO_ID查询用户信息
	 * @Title: queryUserInfoBySsoId 
	 * @Description:
	 * @param sso_id
	 * @return SystemUser         
	 * @throws
	 */
	public SystemUser queryUserInfoBySsoId(String sso_id) {
		String sql = "select * from tbl_system_user where sso_id = ? and is_enable != 1";
		Object[] params = new Object[] { sso_id };
		List<SystemUser> list = jdbcTemplate.query(sql, params,
				new BeanPropertyRowMapper<SystemUser>(SystemUser.class));
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 根据用户名查询用户信息
	 * @Title: queryUserInfo 
	 * @Description:
	 * @param user_name
	 * @param type
	 * @return SystemUser         
	 * @throws
	 */
	public SystemUser queryUserInfo(String user_name, int type) {
		String sql = "select * from tbl_system_user where user_name = ?";
		if (type == 0) {
			sql += " and is_enable = 0";
		}
		Object[] params = new Object[] { user_name };
		List<SystemUser> list = jdbcTemplate.query(sql, params, new BeanPropertyRowMapper<SystemUser>(SystemUser.class));
		SystemUser us = null;
		if (list != null && list.size() > 0) {
			us = list.get(0);
		}
		return us;
	}
	
	/**
	 * 查询用户数量
	 * @Title: queryUserInfoCount 
	 * @Description:
	 * @param keyword
	 * @return int         
	 * @throws
	 */
	public int queryUserInfoCount(String keyword) {
		String sql = "select count(id) from tbl_system_user where user_name like ? or user_realname like ? or user_dept like ? or user_phone like ? or user_email like ?";
		keyword = "%" + keyword + "%";
		Object[] params = new Object[] { keyword, keyword, keyword, keyword, keyword };
		int count = jdbcTemplate.queryForObject(sql, Integer.class, params);
		return count;
	}
	
	/**
	 * 查询用户列表
	 * @Title: queryUserInfoList 
	 * @Description:
	 * @param keyword
	 * @param pageIndex
	 * @param pageSize
	 * @return List<SystemUser>         
	 * @throws
	 */
	public List<SystemUser> queryUserInfoList(String keyword, int pageIndex, int pageSize) {
		String sql = "select tsu.*,tsr.role_name from tbl_system_user tsu left join tbl_system_role tsr on tsu.role_id = tsr.role_id where tsu.user_name like ? or tsu.user_realname like ? or tsu.user_dept like ? or tsu.user_phone like ? or tsu.user_email like ? order by tsu.create_time desc limit ?,?";
		keyword = "%" + keyword + "%";
		int start = pageIndex * pageSize;
		Object[] params = new Object[] { keyword, keyword, keyword, keyword, keyword, start, pageSize };
		List<SystemUser> list = jdbcTemplate.query(sql, params,
				new BeanPropertyRowMapper<SystemUser>(SystemUser.class));
		return list;
	}
	
}