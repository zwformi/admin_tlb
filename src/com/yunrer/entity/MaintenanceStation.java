package com.yunrer.entity;

/**
 * 维修站 实体类
 * @author wangpeng
 *
 */
public class MaintenanceStation {

	private Integer id;// 编号
	private String name;// 维修站名称
	private String address;// 维修站地址
	private String lxr;// 联系人
	private String phone;// 联系电话
	private Integer sort_id;// 排序号
	private Integer type_id;// 类型
	private String type_name;// 类型名称

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public Integer getSort_id() {
		return sort_id;
	}

	public void setSort_id(Integer sortId) {
		sort_id = sortId;
	}
	
	public Integer getType_id() {
		return type_id;
	}

	public void setType_id(Integer typeId) {
		type_id = typeId;
	}
	
	public String getType_name() {
		return type_name;
	}

	public void setType_name(String typeName) {
		type_name = typeName;
	}
}