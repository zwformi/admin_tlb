package com.yunrer.entity;

import java.util.Date;

import com.yunrer.common.DateUtils;
/**
 * 用户留言实体类
 * @author lenovo
 *
 */
public class UserFeedback extends UserInfo {
	private Integer id;           //留言ID
	private String message;   //留言内容
	private Date create_time; //留言时间
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public String getFmt_create_time() {
		return DateUtils.date2str(create_time);
	}
}
