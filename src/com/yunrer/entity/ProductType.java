package com.yunrer.entity;


/**
 * 商品类别信息 实体类
 * @author wangpeng
 *
 */
public class ProductType {

	private Integer id;
	private String name;//类别名称
	private Integer parentId;//一级type
	private String img_url;//类别图片
	private Integer sortId=1; //排序ID（从大到小）
	private Integer delete_flag=0;//删除标记
	private Integer sub_parentId;	//二级type
	private Integer product_level;	//层级：1,2,3
	private Integer zcy_id;
	
	private String parentName;
	private String subParentName;
	
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
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Integer getSortId() {
		return sortId;
	}
	public void setSortId(Integer sortId) {
		this.sortId = sortId;
	}
	public String getImg_url() {
		return img_url;
	}
	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}
	public Integer getDelete_flag() {
		return delete_flag;
	}
	public void setDelete_flag(Integer delete_flag) {
		this.delete_flag = delete_flag;
	}
	public Integer getSub_parentId() {
		return sub_parentId;
	}
	public void setSub_parentId(Integer sub_parentId) {
		this.sub_parentId = sub_parentId;
	}
	public Integer getProduct_level() {
		return product_level;
	}
	public void setProduct_level(Integer product_level) {
		this.product_level = product_level;
	}
	public String getSubParentName() {
		return subParentName;
	}
	public void setSubParentName(String subParentName) {
		this.subParentName = subParentName;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	/**
	 * @return the zcy_id
	 */
	public Integer getZcy_id() {
		return zcy_id;
	}
	/**
	 * @param zcy_id the zcy_id to set
	 */
	public void setZcy_id(Integer zcy_id) {
		this.zcy_id = zcy_id;
	}
}