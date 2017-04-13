package com.yunrer.entity;

import java.util.Date;

import com.yunrer.common.DateUtils;
/**
 * 系统角色 实体类
 * @author wangpeng
 *
 */
public class SystemRole {

	private Integer role_id;// 角色ID
	private String role_name;// 角色名称
	private String remarks;// 备注
	private Date create_time;// 创建时间

	public Integer getRole_id() {
		return role_id;
	}

	public void setRole_id(Integer roleId) {
		role_id = roleId;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String roleName) {
		role_name = roleName;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date createTime) {
		create_time = createTime;
	}
	
	public String getFmt_create_time() {
		return DateUtils.date2str(create_time);
	}
}