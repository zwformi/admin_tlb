package com.yunrer.entity;

/**
 * 销售人员表 实体类
 * 
 * @author wangpeng
 * 
 */
public class Salesman {

	private Integer id; // 销售人员ID
	private String name; // 销售人员名称
	private String phone;// 销售人员手机号码
	private String qq;// 销售人员QQ号码
	private String telphone;// 销售人员固定电话
	private String email;// 销售人员邮箱
	private String gh;//工号
	
	private String opt_empno; //运营人员工号

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

	public String getOpt_empno() {
		return opt_empno;
	}

	public void setOpt_empno(String opt_empno) {
		this.opt_empno = opt_empno;
	}
	
}