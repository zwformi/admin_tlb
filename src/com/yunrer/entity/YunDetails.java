package com.yunrer.entity;
/**
 * 云模块订单商品细节 实体类
 * @ClassName YunDetails
 * @Description
 * @author rujun
 * @date 2016-11-23
 */
public class YunDetails {
	private Integer id;	//细节id
	private String order_number;	//订单id
	private Integer type_id;	//类别id
	private Integer module_id;	//模块id
	private String name;	//名称
	private double module_price_bj;	//报价金额
	private double module_price;	//合同价
	private Integer is_open;	//是否开通（0：未开通，1：开通）
	private String guid;	//同一批次标识符
	private Integer is_combination;	//是否组合（1：组合模块，0：否 ;2:组合头）
	private Integer owning_company;	//所属公司id
	private Integer user_id;	//操作人id
	private String url;		//目标路径
	private Integer delete_flag;	//删除标记（0：上架，1：下架）
	
	private String type_name;
	
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
	public Integer getType_id() {
		return type_id;
	}
	public void setType_id(Integer type_id) {
		this.type_id = type_id;
	}
	public Integer getModule_id() {
		return module_id;
	}
	public void setModule_id(Integer module_id) {
		this.module_id = module_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getModule_price_bj() {
		return module_price_bj;
	}
	public void setModule_price_bj(double module_price_bj) {
		this.module_price_bj = module_price_bj;
	}
	public double getModule_price() {
		return module_price;
	}
	public void setModule_price(double module_price) {
		this.module_price = module_price;
	}
	public Integer getIs_open() {
		return is_open;
	}
	public void setIs_open(Integer is_open) {
		this.is_open = is_open;
	}
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public Integer getIs_combination() {
		return is_combination;
	}
	public void setIs_combination(Integer is_combination) {
		this.is_combination = is_combination;
	}
	public Integer getOwning_company() {
		return owning_company;
	}
	public void setOwning_company(Integer owning_company) {
		this.owning_company = owning_company;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getDelete_flag() {
		return delete_flag;
	}
	public void setDelete_flag(Integer delete_flag) {
		this.delete_flag = delete_flag;
	}
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	
	
	
}
