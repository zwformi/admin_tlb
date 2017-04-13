package com.yunrer.entity;

import java.util.Date;

import com.yunrer.common.DateUtils;


/**
 * 商品信息 实体类
 * @author wangpeng
 *
 */
public class Product {

	private Integer id;   				//主键ID
	private String type_ids;		//商品类型（以,分割）
	
	private Integer parentId;
	private Integer sub_parentId;
	private Integer category_id;

	private Integer brand_id;			//品牌ID
	private String xh;              //型号
	private String pz;              //配置
	private String name;			//商品名称
	private String sub_title;		//商品副标题
	private String img_url;			//商品图片
	private Integer count;				//商品总库存
	
	private Integer overplus=0;   				//库存余量
	private Integer out_count=0;				//商品售出数量
	
	private double price_old;		//商品原价
	private double price_new;		//商品现价
	private String content;			//商品描述
	private Integer sortid;				//排序ID（从大到小）
	private Integer is_yj = 0;				//是否允许议价（0不允许，1允许）
	private Integer is_cx;				//是否促销（0不是，1是）
	private Integer is_ms;				//是否秒杀（0不是，1是）
	private Date ms_begintime;		//秒杀开始时间
	private Integer is_wx;				//是否推荐到微信（0不推荐，1推荐）
	private Integer is_pc;              //PC是否可见（0不可见，1可见）
	private Integer is_red;				//是否热卖推荐（0不推荐，1推荐）
	private Integer delete_flag=0;		//是否上架（0上架状态，1下架状态）
	private String ms_kl;		//秒杀口令
	private Integer is_yun=0;		//是否云产品
	private Integer special_code;	//是否内购
	
	/***********政采云 begin******************/
	private String needInstall;	//是否需要安装
	private String limit;	//境内或境外
	private String countryId;	//国家id
	private String provinceId;	//省id
	private String cityId;	//城市id
	private String regionId;	//区id
	private String firm;	//生产厂商
	private String service;	//售后服务
	private Integer zcy_id;
	private Integer is_zcy;	//是否需要同步（0：未添加，未同步；1：已添加，修改未同步；2：已同步）
	private Integer is_zcysj;	//政采云是否上架（0：否 ；1 ：是）
	private String zcy_price_area;//本地商城价格区间	
	private String platform_price_area;//政采云价格区间
	/***********政采云 end*********************/
	public Integer getOverplus() {
		return this.count-this.out_count;
	}
	public Integer getOut_count() {
		return out_count;
	}
	public void setOut_count(Object out_count) {
		if(out_count==null||out_count.equals("")){
			this.out_count=0;
		}else{
			this.out_count = Integer.parseInt(out_count.toString());
		}
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getType_ids() {
		return type_ids;
	}
	public void setType_ids(String type_ids) {
		this.type_ids = type_ids;
	}
	public Integer getBrand_id() {
		return brand_id;
	}
	public void setBrand_id(Integer brand_id) {
		this.brand_id = brand_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSub_title() {
		return sub_title;
	}
	public void setSub_title(String sub_title) {
		this.sub_title = sub_title;
	}
	public String getImg_url() {
//		if(img_url==null||img_url.equals("")){
//			return "/images/no_pic.png";
//		}else{
			return img_url;
//		}
	}
	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public double getPrice_old() {
		return price_old;
	}
	public void setPrice_old(double price_old) {
		this.price_old = price_old;
	}
	public double getPrice_new() {
		return price_new;
	}
	public void setPrice_new(double price_new) {
		this.price_new = price_new;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getSortid() {
		return sortid;
	}
	public void setSortid(Integer sortid) {
		this.sortid = sortid;
	}
	public Integer getIs_yj() {
		return is_yj;
	}
	public void setIs_yj(Integer is_yj) {
		this.is_yj = is_yj;
	}
	public Integer getIs_cx() {
		return is_cx;
	}
	public void setIs_cx(Integer is_cx) {
		this.is_cx = is_cx;
	}
	public int getIs_ms() {
		return is_ms;
	}
	public void setIs_ms(int is_ms) {
		this.is_ms = is_ms;
	}
	public boolean getFlag_begin() {
		try{
			return this.ms_begintime.getTime()<=new Date().getTime();
		}catch(Exception ex){
			return false;
		}
	}
	public long getLeftTime() {
		long lefttime = 0;
		try{
			lefttime = this.ms_begintime.getTime()-new Date().getTime();
		}catch(Exception ex){		}
		return lefttime;
	}
	public Date getMs_begintime() {
		return ms_begintime;
	}
	public void setMs_begintime(Date ms_begintime) {
		this.ms_begintime = ms_begintime;
	}
	public String getFmt_ms_begintime() {
		try{
			return DateUtils.date2str(ms_begintime);
		}catch(Exception ex){
			return "";
		}
	}
	public Integer getIs_wx() {
		return is_wx;
	}
	public void setIs_wx(Integer is_wx) {
		this.is_wx = is_wx;
	}
	
	public Integer getIs_pc() {
		return is_pc;
	}
	public void setIs_pc(Integer is_pc) {
		this.is_pc = is_pc;
	}
	public Integer getIs_red() {
		return is_red;
	}
	public void setIs_red(Integer is_red) {
		this.is_red = is_red;
	}
	public Integer getDelete_flag() {
		return delete_flag;
	}
	public void setDelete_flag(Integer delete_flag) {
		this.delete_flag = delete_flag;
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
	public String getMs_kl() {
		return ms_kl;
	}
	public void setMs_kl(String ms_kl) {
		this.ms_kl = ms_kl;
	}
	public Integer getIs_yun() {
		return is_yun;
	}
	public void setIs_yun(Integer is_yun) {
		this.is_yun = is_yun;
	}
	public String getNeedInstall() {
		return needInstall;
	}
	public void setNeedInstall(String needInstall) {
		this.needInstall = needInstall;
	}
	public String getLimit() {
		return limit;
	}
	public void setLimit(String limit) {
		this.limit = limit;
	}
	public String getCountryId() {
		return countryId;
	}
	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}
	public String getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	public String getRegionId() {
		return regionId;
	}
	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getFirm() {
		return firm;
	}
	public void setFirm(String firm) {
		this.firm = firm;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public Integer getIs_zcy() {
		return is_zcy;
	}
	public void setIs_zcy(Integer is_zcy) {
		this.is_zcy = is_zcy;
	}
	public Integer getSpecial_code() {
		return special_code;
	}
	public void setSpecial_code(Integer special_code) {
		this.special_code = special_code;
	}
	public Integer getIs_zcysj() {
		return is_zcysj;
	}
	public void setIs_zcysj(Integer is_zcysj) {
		this.is_zcysj = is_zcysj;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Integer getSub_parentId() {
		return sub_parentId;
	}
	public void setSub_parentId(Integer sub_parentId) {
		this.sub_parentId = sub_parentId;
	}
	public Integer getCategory_id() {
		return category_id;
	}
	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}
	public Integer getZcy_id() {
		return zcy_id;
	}
	public void setZcy_id(Integer zcy_id) {
		this.zcy_id = zcy_id;
	}
	public String getZcy_price_area() {
		return zcy_price_area;
	}
	public void setZcy_price_area(String zcy_price_area) {
		this.zcy_price_area = zcy_price_area;
	}
	public String getPlatform_price_area() {
		return platform_price_area;
	}
	public void setPlatform_price_area(String platform_price_area) {
		this.platform_price_area = platform_price_area;
	}
		
}