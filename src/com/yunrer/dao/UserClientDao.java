package com.yunrer.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.yunrer.entity.UserClient;
/**
 * 客服人员DAO
 * @ClassName UserClientDao
 * @Description 
 * @author rujun
 * @date 2016-12-16
 */
@Repository("UserClientDao")
public class UserClientDao {

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 新增客服人员
	 * @Title: addUserClient 
	 * @Description:
	 * @param uc void         
	 * @throws
	 */
	public void addUserClient(UserClient uc) {
		String sql = "insert into tbl_user_client (name,phone,qq,telphone,email,gh) values (?, ?, ?,?,?,?)";
		Object[] params = new Object[] { uc.getName(), uc.getPhone(), uc.getQq(),uc.getTelphone(),uc.getEmail(),uc.getGh() };
		jdbcTemplate.update(sql, params);
	}
	
	/**
	 * 删除客服人员
	 * @Title: deleteUserClient 
	 * @Description:
	 * @param ids void         
	 * @throws
	 */
	public void deleteUserClient(Object[] ids) {
		StringBuffer sql= new StringBuffer("delete from tbl_user_client where id in ("); 
		for (int i = 0; i < ids.length; i++) {
			if (i != 0)
				sql.append(",");
			sql.append("?");
		}
		sql.append(")");
		jdbcTemplate.update(sql.toString(), ids);
	}
	
	/**
	 * 更新客服人员
	 * @Title: modifyUserClient 
	 * @Description:
	 * @param uc void         
	 * @throws
	 */
	public void modifyUserClient(UserClient uc) {
		String sql = "update tbl_user_client set name = ?, phone = ?, qq = ?,telphone = ?,email = ?,gh=? where id = ?";
		Object[] params = new Object[] { uc.getName(), uc.getPhone(), uc.getQq(),uc.getTelphone(),uc.getEmail(),uc.getGh(), uc.getId() };
		jdbcTemplate.update(sql, params);
	}
	
	/**
	 * 查询客服人员
	 * @Title: queryUserClient 
	 * @Description:
	 * @param id
	 * @return UserClient         
	 * @throws
	 */
	public UserClient queryUserClient(int id) {
		String sql = "select * from tbl_user_client where id = ?";
		Object[] params = new Object[] { id };
		List<UserClient> list = jdbcTemplate.query(sql, params,
				new BeanPropertyRowMapper<UserClient>(UserClient.class));
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 查询客服人员数量
	 * @Title: queryUserClientCount 
	 * @Description:
	 * @param keyword
	 * @return int         
	 * @throws
	 */
	public int queryUserClientCount(String keyword) {
		String sql = "select count(id) from tbl_user_client where name like ? or phone like ? or qq like ?";
		keyword = "%" + keyword + "%";
		Object[] params = new Object[] { keyword, keyword, keyword };
		int count = jdbcTemplate.queryForObject(sql, Integer.class, params);
		return count;
	}
	
	/**
	 * 查询客服人员列表
	 * @Title: queryUserClientList 
	 * @Description:
	 * @param keyword
	 * @param pageIndex
	 * @param pageSize
	 * @return List<UserClient>         
	 * @throws
	 */
	public List<UserClient> queryUserClientList(String keyword, int pageIndex, int pageSize) {
		String sql = "select * from tbl_user_client where name like ? or phone like ? or qq like ? order by id desc limit ?,?";
		keyword = "%" + keyword + "%";
		int start = pageIndex * pageSize;
		Object[] params = new Object[] { keyword, keyword, keyword, start, pageSize };
		List<UserClient> list = jdbcTemplate.query(sql, params,
				new BeanPropertyRowMapper<UserClient>(UserClient.class));
		return list;
	}
	
}