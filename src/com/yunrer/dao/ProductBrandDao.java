package com.yunrer.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.yunrer.entity.ProductBrand;

/**
 * 品牌DAO
 * @ClassName ProductBrandDao
 * @Description 
 * @author rujun
 * @date 2016-12-16
 */
@Repository("ProductBrandDao")
public class ProductBrandDao {
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 新增品牌
	 * @Title: addProductBrand 
	 * @Description:
	 * @param pb void         
	 * @throws
	 */
	public void addProductBrand(ProductBrand pb) {
		String sql = "insert into tbl_product_brand (name,sortid) values (?, ?)";
		Object[] params = new Object[] { pb.getName(), pb.getSortid() };
		jdbcTemplate.update(sql, params);
	}
	
	/**
	 * 软删除品牌，标记为1
	 * @Title: deleteProductBrand 
	 * @Description:
	 * @param ids void         
	 * @throws
	 */
	public void deleteProductBrand(Object[] ids) {
		StringBuffer sql= new StringBuffer("update tbl_product_brand set delete_flag = 1 where id in ("); 
		for (int i = 0; i < ids.length; i++) {
			if (i != 0)
				sql.append(",");
			sql.append("?");
		}
		sql.append(")");
		jdbcTemplate.update(sql.toString(), ids);
	}
	
	/**
	 * 修改品牌
	 * @Title: modifyProductBrand 
	 * @Description:
	 * @param pb void         
	 * @throws
	 */
	public void modifyProductBrand(ProductBrand pb) {
		String sql = "update tbl_product_brand set name = ?, sortid = ? where id = ?";
		Object[] params = new Object[] { pb.getName(), pb.getSortid(), pb.getId() };
		jdbcTemplate.update(sql, params);
	}
	
	/**
	 * 查询品牌
	 * @Title: queryProductBrand 
	 * @Description:
	 * @param id
	 * @return ProductBrand         
	 * @throws
	 */
	public ProductBrand queryProductBrand(int id) {
		String sql = "select * from tbl_product_brand where id = ? and delete_flag = 0";
		Object[] params = new Object[] { id };
		List<ProductBrand> list = jdbcTemplate.query(sql, params,
				new BeanPropertyRowMapper<ProductBrand>(ProductBrand.class));
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 查询品牌数量
	 * @Title: queryProductBrandCount 
	 * @Description:
	 * @param keyword
	 * @return int         
	 * @throws
	 */
	public int queryProductBrandCount(String keyword) {
		String sql = "select count(id) from tbl_product_brand where delete_flag = 0 and name like ? order by sortid desc,id desc";
		keyword = "%" + keyword + "%";
		Object[] params = new Object[] { keyword };
		int count = jdbcTemplate.queryForObject(sql, Integer.class, params);
		return count;
	}
	
	/**
	 * 查询品牌列表（分页）
	 * @Title: queryProductBrandList 
	 * @Description:
	 * @param keyword
	 * @param pageIndex
	 * @param pageSize
	 * @return List<ProductBrand>         
	 * @throws
	 */
	public List<ProductBrand> queryProductBrandList(String keyword, int pageIndex, int pageSize) {
		String sql = "select * from tbl_product_brand where delete_flag = 0 and name like ? order by sortid desc,id desc limit ?,?";
		keyword = "%" + keyword + "%";
		int start = pageIndex * pageSize;
		Object[] params = new Object[] { keyword, start, pageSize };
		List<ProductBrand> list = jdbcTemplate.query(sql, params,
				new BeanPropertyRowMapper<ProductBrand>(ProductBrand.class));
		return list;
	}
	
	/**
	 * 查询全部品牌列表
	 * @Title: queryProductBrandList 
	 * @Description:
	 * @return List<ProductBrand>         
	 * @throws
	 */
	public List<ProductBrand> queryProductBrandList() {
		String sql = "select * from tbl_product_brand where delete_flag = 0 order by sortid desc,id desc";
		List<ProductBrand> list = jdbcTemplate.query(sql,
		new BeanPropertyRowMapper<ProductBrand>(ProductBrand.class));
		return list;
	}
	
}