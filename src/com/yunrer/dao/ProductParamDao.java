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

import com.yunrer.entity.ProductParam;

/**
 * 产品参数DAO
 * @ClassName ProductParamDao
 * @Description 
 * @author rujun
 * @date 2016-12-16
 */
@Repository("ProductParamDao")
public class ProductParamDao {
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 查询产品参数列表
	 * @Title: queryProductParamList 
	 * @Description:
	 * @param product_id
	 * @return List<ProductParam>         
	 * @throws
	 */
	public List<ProductParam> queryProductParamList(int product_id) {
		Object[] params = new Object[] {product_id};
		String sql = "select * from tbl_product_param where product_id = ? order by sortid desc,id desc";
		List<ProductParam> list = jdbcTemplate.query(sql,params,
		new BeanPropertyRowMapper<ProductParam>(ProductParam.class));
		return list;
	}
	
	/**
	 * 新增产品参数
	 * @Title: addProductParam 
	 * @Description:
	 * @param productParam
	 * @return int         
	 * @throws
	 */
	public int addProductParam(final ProductParam productParam) {
		int id =0;
		try {
			final String sql = "insert into tbl_product_param(product_id,par_name,par_value,sortid) values(?,?,?,?)";

			KeyHolder keyHolder = new GeneratedKeyHolder();   
	        int result = jdbcTemplate.update(new PreparedStatementCreator(){   
	            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {   
	                PreparedStatement ps =  conn.prepareStatement(sql, new String[]{"id"});//返回主键id   
	                ps.setInt(1, productParam.getProduct_id());
	                ps.setString(2, productParam.getPar_name());
	                ps.setString(3, productParam.getPar_value());
	                ps.setInt(4, productParam.getSortid());
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
	 * 删除产品参数
	 * @Title: delProductParam 
	 * @Description:
	 * @param product_id
	 * @param id
	 * @return int         
	 * @throws
	 */
	public int delProductParam(int product_id,int id) {
		int count =0;
		try {
			Object[] params = new Object[] {id,product_id};
			StringBuffer sql= new StringBuffer("delete from tbl_product_param where id = ? and product_id = ?"); 
			count = jdbcTemplate.update(sql.toString(),params);
		} catch (Exception ex) {
			System.err.println("---------------------------------------------");
			System.err.println(ex.getMessage());
			System.err.println("---------------------------------------------");
		}
		return count;
	}
	
	
	
}