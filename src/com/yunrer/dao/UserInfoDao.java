package com.yunrer.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.yunrer.entity.UserInfo;
/**
 * 用户信息DAO
 * @ClassName UserInfoDao
 * @Description 
 * @author rujun
 * @date 2016-12-19
 */
@Repository("UserInfoDao")
public class UserInfoDao {
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 更新用户状态
	 * @Title: modifyUserInfo 
	 * @Description:
	 * @param user void         
	 * @throws
	 */
	public void modifyUserInfo(UserInfo user) {
		String sql = "update tbl_user_info set status = ? where user_id = ?";
		Object[] params = new Object[] { user.getStatus(), user.getUser_id() };
		jdbcTemplate.update(sql, params);
	}
	
	/**
	 * 更新用户内购状态
	 * @Title: modifyUserInfo 
	 * @Description:
	 * @param user void         
	 * @throws
	 */
	public int operateUserInfoSpecialCode(UserInfo user) {
		String sql = "update tbl_user_info set special_code = ? where user_id = ? and "
				+ " (select special_code from tbl_user_company where id = tbl_user_info.owning_company ) = 1";
		Object[] params = new Object[] { user.getSpecial_code(), user.getUser_id() };
		return jdbcTemplate.update(sql, params);
	}
	
	/**
	 * 更新用户专属客服
	 * @Title: modifyUserInfo2 
	 * @Description:
	 * @param user_id
	 * @param client_id void         
	 * @throws
	 */
	public void modifyUserInfo2(int user_id, int client_id) {
		String sql = "update tbl_user_info set client_id = ? where user_id = ?";
		Object[] params = new Object[] { client_id, user_id };
		jdbcTemplate.update(sql, params);
	}
	
	/**
	 * 更新用户专属销售
	 * @Title: modifyUserInfo3 
	 * @Description:
	 * @param user_id
	 * @param sales_id void         
	 * @throws
	 */
	public void modifyUserInfo3(int user_id, int sales_id) {
		String sql = "update tbl_user_info set sales_id = ? where user_id = ?";
		Object[] params = new Object[] { sales_id, user_id };
		jdbcTemplate.update(sql, params);
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
		String sql = "select count(*) from tbl_user_info ";
		Object[] params = null;
		if (null != keyword && !"".equals(keyword)) {
			keyword = "%" + keyword + "%";
			sql += " where openid like ? or nick_name like ? or xm like ? or e_mail like ? or gsmc like ? or phone like ? ";
			params = new Object[] { keyword, keyword, keyword, keyword, keyword, keyword };
		}
		int count = jdbcTemplate.queryForObject(sql, Integer.class, params);
		return count;
	}
	
	/**
	 * 查询用户列表（分页）
	 * @Title: queryUserInfoList 
	 * @Description:
	 * @param keyword
	 * @param pageIndex
	 * @param pageSize
	 * @return List<UserInfo>         
	 * @throws
	 */
	public List<UserInfo> queryUserInfoList(String keyword, int pageIndex, int pageSize) {
		String sql = "select a.*,b.gsmc from tbl_user_info a left join tbl_user_company b on a.owning_company = b.id  ";
		int start = pageIndex * pageSize;
		Object[] params = null;
		if (null != keyword && !"".equals(keyword)) {
			keyword = "%" + keyword + "%";
			sql += " where a.openid like ? or a.nick_name like ? or a.xm like ? or a.e_mail like ? or b.gsmc like ? or a.phone like ? ";
			params = new Object[] { keyword, keyword, keyword, keyword,
					keyword, keyword, start, pageSize };
		} else {
			params = new Object[] { start, pageSize };
		}
		sql += " order by a.firstAddTime desc limit ?,?";
		List<UserInfo> list = jdbcTemplate.query(sql, params,
				new BeanPropertyRowMapper<UserInfo>(UserInfo.class));
		return list;
	}
	
	/**
	 * 查询用户列表（全部）
	 * @Title: queryUserInfoList 
	 * @Description:
	 * @param keyword
	 * @return List<UserInfo>         
	 * @throws
	 */
	public List<UserInfo> queryUserInfoList(String keyword) {
		String sql = "select * from tbl_user_info ";
		Object[] params = null;
		if (null != keyword && !"".equals(keyword)) {
			keyword = "%" + keyword + "%";
			sql += " where openid like ? or nick_name like ? or xm like ? or e_mail like ? or gsmc like ? or phone like ? ";
			params = new Object[] { keyword, keyword, keyword, keyword, keyword, keyword };
		}
		sql += " order by firstAddTime desc";
		List<UserInfo> list = jdbcTemplate.query(sql, params,
				new BeanPropertyRowMapper<UserInfo>(UserInfo.class));
		return list;
	}
	
	/**
	 * 查询用户实体
	 * @Title: queryUserInfo 
	 * @Description:
	 * @param user_id
	 * @return UserInfo         
	 * @throws
	 */
	public UserInfo queryUserInfo(int user_id) {
		String sql = "select * from tbl_user_info where user_id = ?";
		Object[] params = new Object[] { user_id };
		List<UserInfo> list = jdbcTemplate.query(sql, params, new BeanPropertyRowMapper<UserInfo>(UserInfo.class));
		if (null != list && !list.isEmpty()) {
			return list.get(0);
		} else {
			return null;
		}
	}
}