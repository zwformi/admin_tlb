package com.yunrer.entity;
/**
 * 云模块 实体类
 * @ClassName YunModule
 * @Description
 * @author rujun
 * @date 2016-11-23
 */
public class YunModule {
	private Integer module_id;	//模块id
	private Integer type_id;	//类别id
	private String name;	//名称
	private String introduction;	//模块介绍
	private double price_old;	//原价
	private double price_new;	//现价
	private String icon;	//图标
	private String bgcolor;	//图标背景颜色
	private String url;	//目标路径
	private Integer delete_flag;	//是否上架（0：上架，1：下架）
	private Integer default_flag;	//是否默认开通（0：不开通，1：开通）
	private Integer is_combination;	//是否组合（0：不是组合，1：是组合）
	private Integer sort_code;	//排序code
	
	/**********查询扩展字段***********************/
	private String type_name;	//所属大类类名
	public Integer getModule_id() {
		return module_id;
	}
	public void setModule_id(Integer module_id) {
		this.module_id = module_id;
	}
	public Integer getType_id() {
		return type_id;
	}
	public void setType_id(Integer type_id) {
		this.type_id = type_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public double getPrice_old() {
		return price_old;
	}
	public void setPrice_old(double price_old) {
		this.price_old = price_old;
	}
	public double getPrice_new() {
		return price_new;
	}
	public void setPrice_new(double price_new) {
		this.price_new = price_new;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getDelete_flag() {
		return delete_flag;
	}
	public void setDelete_flag(Integer delete_flag) {
		this.delete_flag = delete_flag;
	}

	public Integer getIs_combination() {
		return is_combination;
	}
	public void setIs_combination(Integer is_combination) {
		this.is_combination = is_combination;
	}
	public String getBgcolor() {
		return bgcolor;
	}
	public void setBgcolor(String bgcolor) {
		this.bgcolor = bgcolor;
	}
	public Integer getDefault_flag() {
		return default_flag;
	}
	public void setDefault_flag(Integer default_flag) {
		this.default_flag = default_flag;
	}
	public Integer getSort_code() {
		return sort_code;
	}
	public void setSort_code(Integer sort_code) {
		this.sort_code = sort_code;
	}
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	
	

}
