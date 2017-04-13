package com.yunrer.entity;

import java.util.Date;

import com.yunrer.common.DateUtils;


/**
 * 订单进度说明及操作时间 实体类
 * @author wangpeng
 *
 */
public class OrderSm {

	private Integer id;   				//主键ID
	private Integer order_id;//订单ID
	private String type;//类型
	private String text_sm;//情况说明
	private Date add_time;//操作时间
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getText_sm() {
		return text_sm;
	}
	public void setText_sm(String text_sm) {
		this.text_sm = text_sm;
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
		}else{
			return "--";
		}
	}
}