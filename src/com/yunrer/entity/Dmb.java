package com.yunrer.entity;

import java.util.Date;

/**
 * 代码表 实体类
 * @author wangpeng
 *
 */
public class Dmb {

	private Integer id;
	private String name;//代码内容
	private Integer sortid;//排序（从大到小）
	private Integer typeid;//类型ID
	private Integer is_delete;//删除标记   0正常  1已删除
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
	public Integer getSortid() {
		return sortid;
	}
	public void setSortid(Integer sortid) {
		this.sortid = sortid;
	}
	public Integer getTypeid() {
		return typeid;
	}
	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}
	public Integer getIs_delete() {
		return is_delete;
	}
	public void setIs_delete(Integer is_delete) {
		this.is_delete = is_delete;
	}
	
	
}