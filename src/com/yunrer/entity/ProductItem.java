package com.yunrer.entity;

import java.sql.Date;
import java.sql.Timestamp;



/**
 * AbstractTblProductItems entity provides the base persistence definition of
 * the TblProductItems entity. @author MyEclipse Persistence Tools
 */
/**
 * 子商品 实体类
 * @author wangpeng
 *
 */
public class ProductItem {
	
	private String img_url;
	private Long productItemsId;
	private Integer productId;
	private Integer productVersions;
	private Integer productColor;
	private Integer productMemories;
	private Integer productSize;
	private Double productSalePrice;
	private Double productMarketPrice;
	private Double productCostPrice;
	private Timestamp modifiedOn;
	private Integer stocks;
	public Long getProductItemsId() {
		return productItemsId;
	}
	public void setProductItemsId(Long productItemsId) {
		this.productItemsId = productItemsId;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getProductVersions() {
		return productVersions;
	}
	public void setProductVersions(Integer productVersions) {
		this.productVersions = productVersions;
	}
	public Integer getProductColor() {
		return productColor;
	}
	public void setProductColor(Integer productColor) {
		this.productColor = productColor;
	}
	public Integer getProductMemories() {
		return productMemories;
	}
	public void setProductMemories(Integer productMemories) {
		this.productMemories = productMemories;
	}
	public Integer getProductSize() {
		return productSize;
	}
	public void setProductSize(Integer productSize) {
		this.productSize = productSize;
	}
	public Double getProductSalePrice() {
		return productSalePrice;
	}
	public void setProductSalePrice(Double productSalePrice) {
		this.productSalePrice = productSalePrice;
	}
	public Double getProductMarketPrice() {
		return productMarketPrice;
	}
	public void setProductMarketPrice(Double productMarketPrice) {
		this.productMarketPrice = productMarketPrice;
	}
	public Double getProductCostPrice() {
		return productCostPrice;
	}
	public void setProductCostPrice(Double productCostPrice) {
		this.productCostPrice = productCostPrice;
	}
	public Timestamp getModifiedOn() {
		return modifiedOn;
	}
	public void setModifiedOn(Timestamp modifiedOn) {
		this.modifiedOn = modifiedOn;
	}
	public Integer getStocks() {
		return stocks;
	}
	public void setStocks(Integer stocks) {
		this.stocks = stocks;
	}
	@Override
	public String toString() {
		return "ProductItem [productItemsId=" + productItemsId + ", productId="
				+ productId + ", productVersions=" + productVersions
				+ ", productColor=" + productColor + ", productMemories="
				+ productMemories + ", productSize=" + productSize
				+ ", productSalePrice=" + productSalePrice
				+ ", productMarketPrice=" + productMarketPrice
				+ ", productCostPrice=" + productCostPrice + ", modifiedOn="
				+ modifiedOn + ", stocks=" + stocks + "]";
	}
	public String getImg_url() {
		return img_url;
	}
	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}
	
	

}