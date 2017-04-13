package com.yunrer.entity;


/**
 * 发票信息 实体类
 * @author wangpeng
 *
 */
public class UserInvoices {

	private Integer id;
	private Integer user_id;//用户ID
	private String type;//发票类型
	private String dwmc;//单位名称
	private String nsrsbh;//纳税人识别号
	private String zcdz;//注册地址
	private String zcdh;//注册电话
	private String khh;//开户行
	private String yhdgzh;//银行对公账号
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDwmc() {
		return dwmc;
	}
	public void setDwmc(String dwmc) {
		this.dwmc = dwmc;
	}
	public String getNsrsbh() {
		return nsrsbh;
	}
	public void setNsrsbh(String nsrsbh) {
		this.nsrsbh = nsrsbh;
	}
	public String getZcdz() {
		return zcdz;
	}
	public void setZcdz(String zcdz) {
		this.zcdz = zcdz;
	}
	public String getZcdh() {
		return zcdh;
	}
	public void setZcdh(String zcdh) {
		this.zcdh = zcdh;
	}
	public String getKhh() {
		return khh;
	}
	public void setKhh(String khh) {
		this.khh = khh;
	}
	public String getYhdgzh() {
		return yhdgzh;
	}
	public void setYhdgzh(String yhdgzh) {
		this.yhdgzh = yhdgzh;
	}
	
}