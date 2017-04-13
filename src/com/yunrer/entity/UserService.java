package com.yunrer.entity;

import java.util.Date;

import com.yunrer.common.DateUtils;


/**
 * 服务单表 实体类
 * @author wangpeng
 *
 */
public class UserService {

	private String service_number;//服务单单号
	private String order_number;//合同编号
	private Integer order_detail_id;//合同详细表ID
	private Integer user_id;//用户ID
	private String lxr;//联系人
	private String phone;//联系电话
	private String content;//服务内容，问题
	private Date add_time;//发布时间
	private Integer state;//状态 0：等待服务   1：已服务
	private String address;//服务地址
	private String comment;//服务说明
	public String getService_number() {
		return service_number;
	}
	public void setService_number(String service_number) {
		this.service_number = service_number;
	}
	public String getOrder_number() {
		return order_number;
	}
	public void setOrder_number(String order_number) {
		this.order_number = order_number;
	}
	public Integer getOrder_detail_id() {
		return order_detail_id;
	}
	public void setOrder_detail_id(Integer order_detail_id) {
		this.order_detail_id = order_detail_id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getLxr() {
		return lxr;
	}
	public void setLxr(String lxr) {
		this.lxr = lxr;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getFmt_addr_time() {
		return DateUtils.date2str(add_time);
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
}