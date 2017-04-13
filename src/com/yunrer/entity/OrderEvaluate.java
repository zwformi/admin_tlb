package com.yunrer.entity;

import java.util.Date;

import com.yunrer.common.DateUtils;


/**
 * 订单评价 实体类
 * @author wangpeng
 *
 */
public class OrderEvaluate {

	private Integer id;//ID
	private Integer order_id;//订单ID
	private Integer user_id;//用户ID
	private Integer score;//评分
	private String content;//评价内容
	private Date add_time;//评价时间
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getOrder_id() {
		return order_id;
	}
	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
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
	public void setAdd_time(Date add_time) {
		this.add_time = add_time;
	}
	public String getFmt_add_time() {
		if(add_time!=null){
			return DateUtils.date2str(add_time);
		}else{return "未评价";}
	}
}