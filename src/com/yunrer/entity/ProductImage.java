package com.yunrer.entity;



/**
 * 商品相册信息 实体类
 * @author wangpeng
 *
 */
public class ProductImage {

	private Integer id;   				//主键ID
	private Integer product_id;			//商品ID
	private String img_url;			//图片路径
	private Integer product_items_id; //商品子ID
	private Integer cover_image; //是否为默认图片 1:是 2:否
	
	public Integer getcoverImage() {
		return cover_image;
	}
	public void setcoverImage(Integer cover_image) {
		this.cover_image = cover_image;
	}
	
	public Integer getProduct_items_id() {
		return product_items_id;
	}
	public void setProduct_items_id(Integer product_items_id) {
		this.product_items_id = product_items_id;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}
	public String getImg_url() {
		return img_url;
	}
	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}
		
}