package com.yunrer.entity;

import java.util.Date;


/**
 * 订单合同详情商品清单 实体类
 * @author wangpeng
 *
 */
public class OrderDetails {

	private Integer id;   				//主键ID
	private String order_number;//合同单号-------管理订单表主键
	private String type_str;     //商品类型
	private String brand_str;    //品牌
	private String xh;           //型号
	private String pz;           //配置
	private Integer product_id;//商品ID
	private String product_name;//商品名称
	private String product_imgurl;//商品图片
	private Integer product_count;//订单数量
	private double product_unit_price;//商品单价-合同价
	private double product_unit_price_bj;//商品单价-报价
	private Date create_time = new Date();//时间
	private String bhqk;//备货情况
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrder_number() {
		return order_number;
	}
	public void setOrder_number(String order_number) {
		this.order_number = order_number;
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
	public String getProduct_imgurl() {
		return product_imgurl;
	}
	public void setProduct_imgurl(String product_imgurl) {
		this.product_imgurl = product_imgurl;
	}
	public Integer getProduct_count() {
		return product_count;
	}
	public void setProduct_count(Integer product_count) {
		this.product_count = product_count;
	}
	public double getProduct_unit_price() {
		return product_unit_price;
	}
	public void setProduct_unit_price(double product_unit_price) {
		this.product_unit_price = product_unit_price;
	}
	
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public double getProduct_unit_price_bj() {
		return product_unit_price_bj;
	}
	public void setProduct_unit_price_bj(double product_unit_price_bj) {
		this.product_unit_price_bj = product_unit_price_bj;
	}
	public String getType_str() {
		return type_str;
	}
	public void setType_str(String type_str) {
		this.type_str = type_str;
	}
	public String getBrand_str() {
		return brand_str;
	}
	public void setBrand_str(String brand_str) {
		this.brand_str = brand_str;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getPz() {
		return pz;
	}
	public void setPz(String pz) {
		this.pz = pz;
	}
	public String getBhqk() {
		return bhqk;
	}
	public void setBhqk(String bhqk) {
		this.bhqk = bhqk;
	}
	
}