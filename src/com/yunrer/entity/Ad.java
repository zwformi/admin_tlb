package com.yunrer.entity;

/**
 * 广告 实体类
 * @author wangpeng
 *
 */
public class Ad {

	private Integer id;
	private Integer typeid;// 类型ID
	private String name;// 广告名称
	private String img_url;// 图片地址
	private String url;//图片链接
	private Integer sortid;// 排序ID（越大越靠前）

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTypeid() {
		return typeid;
	}

	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImg_url() {
		return img_url;
	}

	public void setImg_url(String imgUrl) {
		img_url = imgUrl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getSortid() {
		return sortid;
	}

	public void setSortid(Integer sortid) {
		this.sortid = sortid;
	}
}