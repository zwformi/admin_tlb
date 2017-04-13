package com.yunrer.entity;

import java.util.Date;

import com.yunrer.common.DateUtils;

/**
 * 用户公司信息 实体类
 * @author wangpeng
 *
 */
public class UserCompany {

	private Integer id;     //注册ID
	private Integer user_id;//用户ID
	private String gsmc;//公司名称
	private String zczj;//注册资金
	private String yyzzzch;//营业执照注册号
	private String fddbr;//法定代表人
	private String jyms;//经营模式
	private String ygrs;//员工人数
	private String bgdz;//办公地址
	private String gsjs;//公司介绍
	private Integer staffs_number;//职工人数（picklist）
	private String request_url;//图灵云服务器地址
	private Integer yun_service;//是否购买图灵云基础服务（0:否；1：是）
	private Integer special_code;//内购字段（0：否；1:是）
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getGsmc() {
		return gsmc;
	}
	public void setGsmc(String gsmc) {
		this.gsmc = gsmc;
	}
	public String getZczj() {
		return zczj;
	}
	public void setZczj(String zczj) {
		this.zczj = zczj;
	}
	public String getYyzzzch() {
		return yyzzzch;
	}
	public void setYyzzzch(String yyzzzch) {
		this.yyzzzch = yyzzzch;
	}
	public String getFddbr() {
		return fddbr;
	}
	public void setFddbr(String fddbr) {
		this.fddbr = fddbr;
	}
	public String getJyms() {
		return jyms;
	}
	public void setJyms(String jyms) {
		this.jyms = jyms;
	}
	public String getYgrs() {
		return ygrs;
	}
	public void setYgrs(String ygrs) {
		this.ygrs = ygrs;
	}
	public String getBgdz() {
		return bgdz;
	}
	public void setBgdz(String bgdz) {
		this.bgdz = bgdz;
	}
	public Integer getStaffs_number() {
		return staffs_number;
	}
	public void setStaffs_number(Integer staffs_number) {
		this.staffs_number = staffs_number;
	}
	public String getGsjs() {
		return gsjs;
	}
	public void setGsjs(String gsjs) {
		this.gsjs = gsjs;
	}
	public String getRequest_url() {
		return request_url;
	}
	public void setRequest_url(String request_url) {
		this.request_url = request_url;
	}
	public Integer getYun_service() {
		return yun_service;
	}
	public void setYun_service(Integer yun_service) {
		this.yun_service = yun_service;
	}
	public Integer getSpecial_code() {
		return special_code;
	}
	public void setSpecial_code(Integer special_code) {
		this.special_code = special_code;
	}
	
}