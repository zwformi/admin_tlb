package com.yunrer.entity;

/**
 * 客服表 实体类
 * 
 * @author wangpeng
 * 
 */
public class UserClient {

	private Integer id; // 客服ID
	private String name; // 客服名称
	private String phone;// 客服手机号码
	private String qq;// 客服QQ号码
	private String telphone;//固定电话
	private String email;//邮箱
	private String gh;//工号

	public String getGh() {
		return gh;
	}

	public void setGh(String gh) {
		this.gh = gh;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}