package com.yunrer.entity;

import java.util.Date;

import com.yunrer.common.DateUtils;


/**
 * 订单信息 实体类
 * @author wangpeng
 *
 */
public class OrderInfoXuqiu {

	private Integer id;//主键ID
	private String order_number;    //需求单号
	private Integer user_id;//用户ID
	private Integer order_state;//需求状态(4：待报价  5：完成报价，6：已关闭，7：客户接收报价，8：已生成合同)
	private Date order_time; //需求发布时间
	private Integer order_source;//需求来源（0：PC，1：微信，2：一键需求）
	private double payment_money;//需求报价标准总金额
	private String ip;//需求发布ip
	private String demand_file;//其他需求文件路径
	private String demand_file2;//其他需求文件路径2
	private String demand_file3;//其他需求文件路径3
	private String title;//需求标题
	private String content;//需求内容
	private Date bj_time;//报价时间
	private Date khqr_time;//客户确认时间
	private Date htsc_time;//合同生成时间

	private String xm;   //姓名
	private String dz;//地址
	private String dh;//电话
	private String yb;//邮编
	
	private String gsmc; //公司名称
	private String ywyxm; //业务员姓名
	
	private String offer_file; //报价附件
	
	private Integer owning_company;//所属公司id
	
	public String getGsmc() {
		return gsmc;
	}
	public void setGsmc(String gsmc) {
		this.gsmc = gsmc;
	}
	
	public String getYwyxm() {
		return ywyxm;
	}
	public void setYwyxm(String ywyxm) {
		this.ywyxm = ywyxm;
	}
	
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
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Integer getOrder_state() {
		return order_state;
	}
	public void setOrder_state(Integer order_state) {
		this.order_state = order_state;
	}
	public Date getOrder_time() {
		return order_time;
	}
	public void setOrder_time(Date order_time) {
		this.order_time = order_time;
	}
	public Integer getOrder_source() {
		return order_source;
	}
	public void setOrder_source(int order_source) {
		this.order_source = order_source;
	}
	public double getPayment_money() {
		return payment_money;
	}
	public void setPayment_money(double payment_money) {
		this.payment_money = payment_money;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getDemand_file() {
		return demand_file;
	}
	public void setDemand_file(String demand_file) {
		this.demand_file = demand_file;
	}
	
	public String getDemand_file2() {
		return demand_file2;
	}
	public void setDemand_file2(String demand_file2) {
		this.demand_file2 = demand_file2;
	}
	public String getDemand_file3() {
		return demand_file3;
	}
	public void setDemand_file3(String demand_file3) {
		this.demand_file3 = demand_file3;
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
	public String getFmt_order_time() {
		return DateUtils.date2str(order_time);
	}
	public Date getBj_time() {
		return bj_time;
	}
	public void setBj_time(Date bj_time) {
		this.bj_time = bj_time;
	}
	public Date getKhqr_time() {
		return khqr_time;
	}
	public void setKhqr_time(Date khqr_time) {
		this.khqr_time = khqr_time;
	}
	public Date getHtsc_time() {
		return htsc_time;
	}
	public void setHtsc_time(Date htsc_time) {
		this.htsc_time = htsc_time;
	}
	public String getFmt_bj_time() {
		if(bj_time!=null){
			return DateUtils.date2str(bj_time);
		}else{return "暂无";}
	}
	public String getFmt_khqr_time() {
		if(khqr_time!=null){
			return DateUtils.date2str(khqr_time);
		}else{return "暂无";}
	}
	public String getFmt_htsc_time() {
		if(htsc_time!=null){
			return DateUtils.date2str(htsc_time);
		}else{return "暂无";}
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getDz() {
		return dz;
	}
	public void setDz(String dz) {
		this.dz = dz;
	}
	public String getDh() {
		return dh;
	}
	public void setDh(String dh) {
		this.dh = dh;
	}
	public String getYb() {
		return yb;
	}
	public void setYb(String yb) {
		this.yb = yb;
	}
	
	public String getOffer_file() {
		return offer_file;
	}
	public void setOffer_file(String offer_file) {
		this.offer_file = offer_file;
	}
	public Integer getOwning_company() {
		return owning_company;
	}
	public void setOwning_company(Integer owning_company) {
		this.owning_company = owning_company;
	}
	
}