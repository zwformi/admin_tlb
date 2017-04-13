package com.yunrer.entity;

import java.util.Date;

/**
 * 文章表 实体类
 * @author wangpeng
 *
 */
public class Article {

	private Integer id;
	private Integer typeid;//类型ID
	private String title;//文章标题
	private String content;//文章内容
	private Date create_time;
	private Integer sortid;//排序（从大到小）
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Integer getSortid() {
		return sortid;
	}
	public void setSortid(Integer sortid) {
		this.sortid = sortid;
	}
	
	
}