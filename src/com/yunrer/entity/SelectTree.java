package com.yunrer.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *树形菜单VO
 */
public class SelectTree implements Serializable{
	private Integer id;
	private String name;
	private Integer parentid;
	public Integer getParentid() {
		return parentid;
	}
	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}
	private List<SelectTree> child = new ArrayList<SelectTree>();
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
	public List<SelectTree> getChild() {
		return child;
	}
	public void setChild(List<SelectTree> child) {
		this.child = child;
	}
           
}

