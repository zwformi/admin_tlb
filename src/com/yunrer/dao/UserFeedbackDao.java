package com.yunrer.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.yunrer.entity.UserFeedback;
/**
 * 留言DAO
 * @ClassName UserFeedbackDao
 * @Description 
 * @author rujun
 * @date 2016-12-19
 */
@Repository("UserFeedbackDao")
public class UserFeedbackDao {

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 删除留言
	 * @Title: deleteUserFeedback 
	 * @Description:
	 * @param ids void         
	 * @throws
	 */
	public void deleteUserFeedback(Object[] ids) {
		StringBuffer sql= new StringBuffer("delete from tbl_user_feedback where id in ("); 
		for (int i = 0; i < ids.length; i++) {
			if (i != 0)
				sql.append(",");
			sql.append("?");
		}
		sql.append(")");
		jdbcTemplate.update(sql.toString(), ids);
	}
	
	/**
	 * 查询留言信息数量
	 * @Title: queryUserFeedbackCount 
	 * @Description:
	 * @param keyword
	 * @return int         
	 * @throws
	 */
	public int queryUserFeedbackCount(String keyword) {
		String sql = "select count(id) from tbl_user_feedback where message like ?";
		keyword = "%" + keyword + "%";
		Object[] params = new Object[] { keyword};
		int count = jdbcTemplate.queryForObject(sql, Integer.class, params);
		return count;
	}
	
	/**
	 * 查询留言信息列表
	 * @Title: queryUserFeedbackList 
	 * @Description:
	 * @param keyword
	 * @param pageIndex
	 * @param pageSize
	 * @return List<UserFeedback>         
	 * @throws
	 */
	public List<UserFeedback> queryUserFeedbackList(String keyword, int pageIndex, int pageSize) {
		String sql = "select a.*,b.* from tbl_user_feedback a,tbl_user_info b where a.user_id = b.user_id and message like ? order by create_time desc limit ?,?";
		keyword = "%" + keyword + "%";
		int start = pageIndex * pageSize;
		Object[] params = new Object[] { keyword, start, pageSize };
		List<UserFeedback> list = jdbcTemplate.query(sql, params,
				new BeanPropertyRowMapper<UserFeedback>(UserFeedback.class));
		return list;
	}
	
}