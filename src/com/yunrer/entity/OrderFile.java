package com.yunrer.entity;

import java.util.Date;

import com.yunrer.common.DateUtils;



/**
 * 订单附件 实体类
 * @author wangpeng
 *
 */
public class OrderFile {

	private Integer id;   				//主键ID
	private Integer order_id;			//商品ID
	private String file_url;			//附件路径
	private Date add_time;           //添加时间
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getOrder_id() {
		return order_id;
	}
	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}
	public String getFile_url() {
		return file_url;
	}
	public void setFile_url(String file_url) {
		this.file_url = file_url;
	}
	public Date getAdd_time() {
		return add_time;
	}
	public void setAdd_time(Date add_time) {
		this.add_time = add_time;
	}
	public String getFmt_add_time() {
		if(add_time!=null){
			return DateUtils.date2str(add_time);
		}else{return "--";}
	}
}