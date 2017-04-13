package com.yunrer.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yunrer.entity.Product;
import com.yunrer.entity.ProductSku;
import com.yunrer.entity.UserCompany;
/**
 * 子产品DAO
 * @ClassName ProductSkuDao
 * @Description 
 * @author rujun
 * @date 2016-12-16
 */
@Repository("ProductSkuDao")
public class ProductSkuDao {
	@Resource
	private JdbcTemplate jdbcTemplate;

	/**
	 * 查询子产品列表
	 * @Title: queryProductSkuListById 
	 * @Description:
	 * @param product_id
	 * @return List<ProductSku>         
	 * @throws
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(method=RequestMethod.POST,value="/skulist")
	public List<ProductSku> queryProductSkuListById(int product_id) {
		Object[] params = new Object[] { product_id };
		String sql = "select * from tbl_product_skus where product_id = ? order by id,attrVal1,attrVal2,attrVal3,attrVal4";
		List<ProductSku> list = jdbcTemplate.query(sql, params,
				new BeanPropertyRowMapper<ProductSku>(ProductSku.class));

		return list;

	}
	
	/**
	 * 对接时sku属性的查询
	 * @Title: queryProductSkuForSkuAttrs 
	 * @Description:
	 * @param product_id
	 * @param index
	 * @return List         
	 * @throws
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List queryProductSkuForSkuAttrs(int product_id,int index) {
		String attr = "";
		String group_by = "";
		Object[] params = new Object[] { product_id };
		if(index ==1){
			attr = "attrName1 attrName,attrVal1 attrVal";
			group_by = "attrVal1";
		}else if(index ==2){
			attr = "attrName2 attrName,attrVal2 attrVal";
			group_by = "attrVal2";
		}else if(index ==3){
			attr = "attrName3 attrName,attrVal3 attrVal";
			group_by = "attrVal3";
		}else if(index ==4){
			attr = "attrName4 attrName,attrVal4 attrVal";
			group_by = "attrVal4";
		}else if(index ==5){
			attr = "attrName5 attrName,attrVal5 attrVal";
			group_by = "attrVal5";
		}else if(index ==6){
			attr = "attrName6 attrName,attrVal6 attrVal";
			group_by = "attrVal6";
		}else if(index ==7){
			attr = "attrName7 attrName,attrVal7 attrVal";
			group_by = "attrVal7";
		}else if(index ==8){
			attr = "attrName8 attrName,attrVal8 attrVal";
			group_by = "attrVal8";
		}
		String sql = "select "+attr+" from tbl_product_skus where product_id = ? group by "+group_by;
		List list = jdbcTemplate.queryForList(sql, params);
			
		if (list == null)
			return null;
		else
			return list;

	}

	/**
	 * 根据sku_id查询子商品
	 * @Title: queryProductSkuById 
	 * @Description:
	 * @param id
	 * @return ProductSku         
	 * @throws
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ProductSku queryProductSkuById(int id) {
		Object[] params = new Object[] { id };
		String sql = "select * from tbl_product_skus where id = ? order by product_id,id";
		List<ProductSku> list = jdbcTemplate.query(sql, params,
				new BeanPropertyRowMapper<ProductSku>(ProductSku.class));
			
		if (list == null)
			return null;
		else
			return list.get(0);

	}



	/**
	 * 新增子产品
	 * @Title: addProductSku 
	 * @Description:
	 * @param productSku
	 * @return int         
	 * @throws
	 */

	public int addProductSku(final ProductSku productSku) {
		int id = 0;
		try {
			final String sql = "insert into tbl_product_skus(product_id,attrName1,attrName2,attrName3,attrName4,attrVal1,attrVal2,attrVal3,attrVal4,price,platformPrice,originPrice,stocks,attrName5,attrName6,attrName7,attrName8,attrVal5,attrVal6,attrVal7,attrVal8) "
					+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			KeyHolder keyHolder = new GeneratedKeyHolder();
			int result = jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection conn)
						throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql,
							new String[] { "id" });// 返回主键id
					ps.setLong(1, productSku.getProductId()==null?0:productSku.getProductId());
					ps.setString(2, productSku.getAttrName1());
					ps.setString(3, productSku.getAttrName2());
					ps.setString(4, productSku.getAttrName3());
					ps.setString(5, productSku.getAttrName4());
					ps.setString(6, productSku.getAttrVal1());
					ps.setString(7, productSku.getAttrVal2());
					ps.setString(8, productSku.getAttrVal3());
					ps.setString(9, productSku.getAttrVal4());
					ps.setDouble(10, productSku.getPrice()==null?0:productSku.getPrice());
					ps.setDouble(11, productSku.getPlatformPrice()==null?0:productSku.getPlatformPrice());
					ps.setDouble(12, productSku.getOriginPrice()==null?0:productSku.getOriginPrice());
					ps.setInt(13, productSku.getStocks());
					ps.setString(14, productSku.getAttrName5());
					ps.setString(15, productSku.getAttrName6());
					ps.setString(16, productSku.getAttrName7());
					ps.setString(17, productSku.getAttrName8());
					ps.setString(18, productSku.getAttrVal5());
					ps.setString(19, productSku.getAttrVal6());
					ps.setString(20, productSku.getAttrVal7());
					ps.setString(21, productSku.getAttrVal8());
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
	 * 修改子商品
	 * @Title: updateProductSku 
	 * @Description:
	 * @param productSku
	 * @return int         
	 * @throws
	 */
	public int updateProductSku(ProductSku productSku) {
		int count = 0;
		try {
			Object[] params = new Object[] { 
					productSku.getPlatformPrice(),
					productSku.getPrice(),
					productSku.getOriginPrice(),
					productSku.getStocks(),
					productSku.getId() };
			String sql = "update tbl_product_skus set platformPrice = ?,price=?,originPrice=?,stocks=? where id = ? ";
			count = jdbcTemplate.update(sql, params);
		} catch (Exception ex) {
			System.err.println("---------------------------------------------");
			System.err.println(ex.getMessage());
			System.err.println("---------------------------------------------");
		}
		return count;
	}

	/**
	 * 根据product_id删除子商品
	 * @Title: delProductSku 
	 * @Description:
	 * @param product_skus_id
	 * @return int         
	 * @throws
	 */
	public int delProductSkuByProduct(int product_id) {
		int count = 0;
		try {
			Object[] params = new Object[] {  product_id };
			StringBuffer sql = new StringBuffer(
					"delete from tbl_product_skus where product_id =?");
			count = jdbcTemplate.update(sql.toString(), params);
		} catch (Exception ex) {
			System.err.println("---------------------------------------------");
			System.err.println(ex.getMessage());
			System.err.println("---------------------------------------------");
		}
		return count;
	}
	
	/**
	 * 删除商品
	 * @Title: delProduct 
	 * @Description:
	 * @param ids
	 * @return int         
	 * @throws
	 */
	public int delProductSku(Object[] ids) {
		int count =0;
			StringBuffer sql= new StringBuffer("delete from tbl_product_skus where product_id in ("); 
			for (int i = 0; i < ids.length; i++) {
				if (i != 0)
					sql.append(",");
				sql.append("?");
			}
			sql.append(")");
			count = jdbcTemplate.update(sql.toString(),ids);
		return count;
	}
	
	public int delProductSkuById(int product_sku_id) {
		int count = 0;
		try {
			Object[] params = new Object[] {  product_sku_id };
			StringBuffer sql = new StringBuffer(
					"delete from tbl_product_skus where id =?");
			count = jdbcTemplate.update(sql.toString(), params);
		} catch (Exception ex) {
			System.err.println("---------------------------------------------");
			System.err.println(ex.getMessage());
			System.err.println("---------------------------------------------");
		}
		return count;
	}

}
