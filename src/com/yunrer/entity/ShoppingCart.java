package com.yunrer.entity;

import java.util.Date;


/**
 * 购物车 实体类
 * @author wangpeng
 *
 */
public class ShoppingCart {

	private Integer id;   				//主键ID
	private Integer user_id;   				//用户ID
	private Integer product_id;			//商品ID
	private String product_name;			//商品名称
	private String product_img_url;			//商品图片
	private Integer product_count;				//拟购买数量
	private Integer is_wx;                      //是否微信购物车   1：微信购物车    0：PC购物车
	private Date create_time;               //加入购物车时间
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Integer getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getProduct_img_url() {
		return product_img_url;
	}
	public void setProduct_img_url(String product_img_url) {
		this.product_img_url = product_img_url;
	}
	public Integer getProduct_count() {
		return product_count;
	}
	public void setProduct_count(Integer product_count) {
		this.product_count = product_count;
	}
	public Integer getIs_wx() {
		return is_wx;
	}
	public void setIs_wx(Integer is_wx) {
		this.is_wx = is_wx;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
}