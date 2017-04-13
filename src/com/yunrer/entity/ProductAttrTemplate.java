package com.yunrer.entity;

public class ProductAttrTemplate {
	private Integer id = 0;
	private Integer category_id = 0;
	private String category_name;
	private String sub_name;
	private String root_name;
	private Integer zcy_attr_id = 0;
	private String attrName;
	private String group;
	private Integer isRequired = 0;
	private Integer isImportant = 0;
	private Integer isSukCandidate = 0;
	private Integer isUserDefined = 0;
	private String valueType;
	private String attrVals;
	private Integer local_type_id =0;
	private Integer delete_flag = 0;	//0:未删除 1:已删除
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the category_id
	 */
	public Integer getCategory_id() {
		return category_id;
	}
	/**
	 * @param category_id the category_id to set
	 */
	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}
	/**
	 * @return the category_name
	 */
	public String getCategory_name() {
		return category_name;
	}
	/**
	 * @param category_name the category_name to set
	 */
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	/**
	 * @return the sub_name
	 */
	public String getSub_name() {
		return sub_name;
	}
	/**
	 * @param sub_name the sub_name to set
	 */
	public void setSub_name(String sub_name) {
		this.sub_name = sub_name;
	}
	/**
	 * @return the root_name
	 */
	public String getRoot_name() {
		return root_name;
	}
	/**
	 * @param root_name the root_name to set
	 */
	public void setRoot_name(String root_name) {
		this.root_name = root_name;
	}
	/**
	 * @return the zcy_attr_id
	 */
	public Integer getZcy_attr_id() {
		return zcy_attr_id;
	}
	/**
	 * @param zcy_attr_id the zcy_attr_id to set
	 */
	public void setZcy_attr_id(Integer zcy_attr_id) {
		this.zcy_attr_id = zcy_attr_id;
	}
	/**
	 * @return the attrName
	 */
	public String getAttrName() {
		return attrName;
	}
	/**
	 * @param attrName the attrName to set
	 */
	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}
	/**
	 * @return the group
	 */
	public String getGroup() {
		return group;
	}
	/**
	 * @param group the group to set
	 */
	public void setGroup(String group) {
		this.group = group;
	}
	/**
	 * @return the isRequired
	 */
	public Integer getIsRequired() {
		return isRequired;
	}
	/**
	 * @param isRequired the isRequired to set
	 */
	public void setIsRequired(Integer isRequired) {
		this.isRequired = isRequired;
	}
	/**
	 * @return the isImportant
	 */
	public Integer getIsImportant() {
		return isImportant;
	}
	/**
	 * @param isImportant the isImportant to set
	 */
	public void setIsImportant(Integer isImportant) {
		this.isImportant = isImportant;
	}
	/**
	 * @return the isSukCandidate
	 */
	public Integer getIsSukCandidate() {
		return isSukCandidate;
	}
	/**
	 * @param isSukCandidate the isSukCandidate to set
	 */
	public void setIsSukCandidate(Integer isSukCandidate) {
		this.isSukCandidate = isSukCandidate;
	}
	/**
	 * @return the isUserDefined
	 */
	public Integer getIsUserDefined() {
		return isUserDefined;
	}
	/**
	 * @param isUserDefined the isUserDefined to set
	 */
	public void setIsUserDefined(Integer isUserDefined) {
		this.isUserDefined = isUserDefined;
	}
	/**
	 * @return the valueType
	 */
	public String getValueType() {
		return valueType;
	}
	/**
	 * @param valueType the valueType to set
	 */
	public void setValueType(String valueType) {
		this.valueType = valueType;
	}
	/**
	 * @return the attrVals
	 */
	public String getAttrVals() {
		return attrVals;
	}
	/**
	 * @param attrVals the attrVals to set
	 */
	public void setAttrVals(String attrVals) {
		this.attrVals = attrVals;
	}
	/**
	 * @return the local_type_id
	 */
	public Integer getLocal_type_id() {
		return local_type_id;
	}
	/**
	 * @param local_type_id the local_type_id to set
	 */
	public void setLocal_type_id(Integer local_type_id) {
		this.local_type_id = local_type_id;
	}
	/**
	 * @return the delete_flag
	 */
	public Integer getDelete_flag() {
		return delete_flag;
	}
	/**
	 * @param delete_flag the delete_flag to set
	 */
	public void setDelete_flag(Integer delete_flag) {
		this.delete_flag = delete_flag;
	}
	
}
