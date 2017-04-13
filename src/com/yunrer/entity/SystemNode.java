package com.yunrer.entity;
/**
 * 系统节点 实体类
 * @author wangpeng
 *
 */
public class SystemNode {

	private Integer id;// 节点ID
	private String name;// 节点名称
	private String pId;// 父节点ID
	private boolean checked;// 是否选中
	private String key_name;// 变量名

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

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public String getKey_name() {
		return key_name;
	}

	public void setKey_name(String keyName) {
		key_name = keyName;
	}
}