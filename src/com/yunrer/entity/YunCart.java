package com.yunrer.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.yunrer.common.DateUtils;


/**
 * 云模块购物车 实体类
 * @ClassName YunCart
 * @Description
 * @author rujun
 * @date 2016-11-23
 */
public class YunCart {
	private Integer cart_id;	//购物车id
	private Integer user_id;	//操作人id
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") 
	private Date create_time;	//产生时间
	private Integer owning_company;	//所属公司
	private Integer module_id;	//模块id
	private Integer is_combination;	//是否组合（0：不是组合，1：是组合）

	public Integer getCart_id() {
		return cart_id;
	}
	public void setCart_id(Integer cart_id) {
		this.cart_id = cart_id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getFmt_create_time() {
		if(create_time!=null){
			return DateUtils.date2str(create_time);
		}else{return "暂无";}
	}
	
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Integer getOwning_company() {
		return owning_company;
	}
	public void setOwning_company(Integer owning_company) {
		this.owning_company = owning_company;
	}
	public Integer getModule_id() {
		return module_id;
	}
	public void setModule_id(Integer module_id) {
		this.module_id = module_id;
	}
	public Integer getIs_combination() {
		return is_combination;
	}
	public void setIs_combination(Integer is_combination) {
		this.is_combination = is_combination;
	}

}
