package com.yunrer.entity;
/**
 * 云模块类别 实体类
 * @ClassName YunType
 * @Description
 * @author rujun
 * @date 2016-11-23
 */
public class YunType {
	private Integer type_id;	//类别id
	private Integer parent_id;	//父类id
	private String name;	//名称
	private String icon;	//图标
	private String bgcolor;	//图标背景颜色
	private Integer delete_flag;	//是否上架（0：上架，1：下架）
	private Integer sort_code;	//排序id 
	private Integer module_id;	//对应集合id
	
	/*************扩展****************/
	private String parentName;	//父类名
	
	
	public Integer getType_id() {
		return type_id;
	}
	public void setType_id(Integer type_id) {
		this.type_id = type_id;
	}
	public Integer getParent_id() {
		return parent_id;
	}
	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Integer getDelete_flag() {
		return delete_flag;
	}
	public void setDelete_flag(Integer delete_flag) {
		this.delete_flag = delete_flag;
	}
	public String getBgcolor() {
		return bgcolor;
	}
	public void setBgcolor(String bgcolor) {
		this.bgcolor = bgcolor;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public Integer getSort_code() {
		return sort_code;
	}
	public void setSort_code(Integer sort_code) {
		this.sort_code = sort_code;
	}
	public Integer getModule_id() {
		return module_id;
	}
	public void setModule_id(Integer module_id) {
		this.module_id = module_id;
	}

	
}
