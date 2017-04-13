package com.yunrer.entity;

import java.util.Date;
/**
 * 用户信息 实体类
 * @author wangpeng
 *
 */
public class UserMessage {

	private Integer id;// 主键ID
	private Integer user_id;// 用户ID
	private String title;// 标题
	private String content;// 内容
	private Date add_time;// 新增时间
	private Integer is_read;// 是否已读

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer userId) {
		user_id = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getAdd_time() {
		return add_time;
	}

	public void setAdd_time(Date addTime) {
		add_time = addTime;
	}

	public Integer getIs_read() {
		return is_read;
	}

	public void setIs_read(Integer isRead) {
		is_read = isRead;
	}
}