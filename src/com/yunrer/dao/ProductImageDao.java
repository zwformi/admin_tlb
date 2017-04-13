package com.yunrer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.yunrer.entity.ProductImage;
/**
 * 产品图片DAO
 * @ClassName ProductImageDao
 * @Description 
 * @author rujun
 * @date 2016-12-16
 */
@Repository("ProductImageDao")
public class ProductImageDao {
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 根据产品id和子产品id查询图片列表
	 * @Title: queryProductImageList 
	 * @Description:
	 * @param product_id
	 * @param product_items_id
	 * @return List<ProductImage>         
	 * @throws
	 */
	public List<ProductImage> queryProductImageList(int product_id,int product_items_id) {
		Object[] params = new Object[] {product_id};
		String sql = "select * from tbl_product_image where product_id = ? ";
		
		if(product_items_id!=-1){
			sql+=" and product_items_id = ? ";
			params = new Object[] {product_id,product_items_id};
		}
		
		sql+=" order by id asc";
		List<ProductImage> list = jdbcTemplate.query(sql,params,
		new BeanPropertyRowMapper<ProductImage>(ProductImage.class));
		return list;
	}
	
	/**
	 * 新增产品图片
	 * @Title: addProductImage 
	 * @Description:
	 * @param productImage
	 * @return int         
	 * @throws
	 */
	public int addProductImage(final ProductImage productImage) {
		int id =0;
		try {
			final String sql = "insert into tbl_product_image(product_id,img_url,product_items_id) values(?,?,?)";

			KeyHolder keyHolder = new GeneratedKeyHolder();   
	        int result = jdbcTemplate.update(new PreparedStatementCreator(){   
	            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {   
	                PreparedStatement ps =  conn.prepareStatement(sql, new String[]{"id"});//返回主键id   
	                ps.setInt(1, productImage.getProduct_id());
	                ps.setString(2, productImage.getImg_url());
	                ps.setInt(3,productImage.getProduct_items_id());
	                return ps;   
	            }
	        }, keyHolder);   
			if (result > 0) {
				id = keyHolder.getKey().intValue();
			}
		} catch (Exception ex) {
			System.err.println("---------------------------------------------");
			System.err.println(ex.getMessage());
			System.err.println("---------------------------------------------");
		}
		return id;
	}
	
	/**
	 * 设置为默认图片
	 * @Title: setAsDefaultImg 
	 * @Description:
	 * @param product_items_id
	 * @param id
	 * @param state
	 * @return int         
	 * @throws
	 */
	public int setAsDefaultImg(int product_items_id,int id,int state) {
		int count = 0;
		String msg ="";
		try {
			Object[] params = new Object[] {state,product_items_id,id};
			StringBuffer sql= new StringBuffer("update tbl_product_image set cover_image=? where product_items_id=? and id=?  "); 
			count = jdbcTemplate.update(sql.toString(),params);
		} catch (Exception ex) {
			System.err.println("---------------------------------------------");
			System.err.println(ex.getMessage());
			System.err.println("---------------------------------------------");
		}
		return count;
	}
	
	/**
	 * 删除产品图片
	 * @Title: delProductImage 
	 * @Description:
	 * @param product_id
	 * @param id
	 * @return int         
	 * @throws
	 */
	public int delProductImage(int product_id,int id) {
		int count =0;
		try {
			Object[] params = new Object[] {id,product_id};
			StringBuffer sql= new StringBuffer("delete from tbl_product_image where id = ? and product_id = ? "); 
			count = jdbcTemplate.update(sql.toString(),params);
		} catch (Exception ex) {
			System.err.println("---------------------------------------------");
			System.err.println(ex.getMessage());
			System.err.println("---------------------------------------------");
		}
		return count;
	}
	
	/**
	 * 根据id查询图片
	 * @Title: queryImage 
	 * @Description:
	 * @param id
	 * @return List<ProductImage>         
	 * @throws
	 */
	public List<ProductImage> queryImage(int id) {
		int count =0;
			Object[] params = new Object[] {id};
			StringBuffer sql= new StringBuffer("select * from tbl_product_image where id = ? "); 
			List<ProductImage> list = jdbcTemplate.query(sql.toString(),params,
					new BeanPropertyRowMapper<ProductImage>(ProductImage.class));
					return list;

	}
	
	/**
	 * 删除商品
	 * @Title: delProduct 
	 * @Description:
	 * @param ids
	 * @return int         
	 * @throws
	 */
	public int delProductImage(Object[] ids) {
		int count =0;
			StringBuffer sql= new StringBuffer("delete from tbl_product_image where product_id in ("); 
			for (int i = 0; i < ids.length; i++) {
				if (i != 0)
					sql.append(",");
				sql.append("?");
			}
			sql.append(")");
			count = jdbcTemplate.update(sql.toString(),ids);
		return count;
	}
}