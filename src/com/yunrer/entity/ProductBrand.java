package com.yunrer.entity;


/**
 * 品牌信息 实体类
 * @author wangpeng
 *
 */
public class ProductBrand {

	private Integer id;
	private String name;//类别名称
	private Integer sortid;//排序ID，（从大到小）
	private Integer delete_flag=0;//删除标记
	private String en_name;	//英文名（用于政采云对接）
	public Integer getSortid() {
		return sortid;
	}
	public void setSortid(Integer sortid) {
		this.sortid = sortid;
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
	public Integer getDelete_flag() {
		return delete_flag;
	}
	public void setDelete_flag(Integer delete_flag) {
		this.delete_flag = delete_flag;
	}
	public String getEn_name() {
		return en_name;
	}
	public void setEn_name(String en_name) {
		this.en_name = en_name;
	}
	
	
}