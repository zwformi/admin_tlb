package com.yunrer.dao;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.yunrer.entity.UserMessage;
/**
 * 用户消息DAO
 * @ClassName UserMessageDao
 * @Description 
 * @author rujun
 * @date 2016-12-19
 */
@Repository("UserMessageDao")
public class UserMessageDao {
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 新增用户消息
	 * @Title: addUserMessage 
	 * @Description:
	 * @param um void         
	 * @throws
	 */
	public void addUserMessage(UserMessage um) {
		String sql = "insert into tbl_user_message (user_id,title,content,add_time) values (?, ?, ?, now())";
		Object[] params = new Object[] { um.getUser_id(), um.getTitle(), um.getContent() };
		jdbcTemplate.update(sql, params);
	}
}