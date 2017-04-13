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

import com.yunrer.entity.Product;
import com.yunrer.entity.ProductItem;
/**
 * 子产品DAO
 * @ClassName ProductItemDao
 * @Description 
 * @author rujun
 * @date 2016-12-16
 */
@Repository("ProductItemDao")
public class ProductItemDao {
	@Resource
	private JdbcTemplate jdbcTemplate;

	/**
	 * 查询子产品列表
	 * @Title: queryProductItemListById 
	 * @Description:
	 * @param product_id
	 * @return List<ProductItem>         
	 * @throws
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ProductItem> queryProductItemListById(int product_id) {
		Object[] params = new Object[] { product_id };
		String sql = "select * from tbl_product_items where product_id = ? order by Product_Size,Product_Color,Product_Versions,Product_Memories";
		List<ProductItem> list = jdbcTemplate.query(sql, params,
				new RowMapper() {
					public Object mapRow(ResultSet rs, int paramInt)
							throws SQLException {
						ProductItem item = new ProductItem();
						item.setProductItemsId(rs.getLong("product_items_id"));
						item.setProductId(rs.getInt("product_id"));
						item.setProductVersions(rs.getInt("Product_Versions"));
						item.setProductColor(rs.getInt("Product_Color"));
						item.setProductMemories(rs.getInt("Product_Memories"));
						item.setProductSize(rs.getInt("Product_Size"));
						item.setProductSalePrice(rs
								.getDouble("Product_SalePrice"));
						item.setProductMarketPrice(rs
								.getDouble("Product_MarketPrice"));
						item.setProductCostPrice(rs
								.getDouble("Product_CostPrice"));
						item.setModifiedOn(rs.getTimestamp("ModifiedOn"));
						item.setStocks(rs.getInt("Stocks"));
						return item;
					}
				});

		return list;

	}

	/**
	 * 根据item_id查询子商品
	 * @Title: queryProductItemById 
	 * @Description:
	 * @param id
	 * @return ProductItem         
	 * @throws
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ProductItem queryProductItemById(int id) {
		Object[] params = new Object[] { id };
		String sql = "select * from tbl_product_items where product_items_id = ? order by product_id,product_items_id";
		List<ProductItem> list = jdbcTemplate.query(sql, params,
				new RowMapper() {
					public Object mapRow(ResultSet rs, int paramInt)
							throws SQLException {
						ProductItem item = new ProductItem();
						item.setProductItemsId(rs.getLong("product_items_id"));
						item.setProductId(rs.getInt("product_id"));
						item.setProductVersions(rs.getInt("Product_Versions"));
						item.setProductColor(rs.getInt("Product_Color"));
						item.setProductMemories(rs.getInt("Product_Memories"));
						item.setProductSize(rs.getInt("Product_Size"));
						item.setImg_url(rs.getString("img_url"));
						item.setProductSalePrice(rs
								.getDouble("Product_SalePrice"));
						item.setProductMarketPrice(rs
								.getDouble("Product_MarketPrice"));
						item.setProductCostPrice(rs
								.getDouble("Product_CostPrice"));
						item.setModifiedOn(rs.getTimestamp("ModifiedOn"));
						item.setStocks(rs.getInt("Stocks"));
						return item;
					}
				});
		if (list == null)
			return null;
		else
			return list.get(0);

	}

	/**
	 * 根据子产品信息查询单个子产品全部信息
	 * @Title: queryProductItemByItem 
	 * @Description:
	 * @param item
	 * @return ProductItem         
	 * @throws
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ProductItem queryProductItemByItem(ProductItem item) {
		Object[] params = new Object[] { item.getProductId(),
				item.getProductColor(), item.getProductMemories(),
				item.getProductSize(), item.getProductVersions() };
		String sql = "select * from tbl_product_items where product_id=? and product_color = ? and product_memories=? and product_size=? and product_versions=? limit 1";
		List<ProductItem> list = jdbcTemplate.query(sql, params,
				new RowMapper() {
					public Object mapRow(ResultSet rs, int paramInt)
							throws SQLException {
						ProductItem item = new ProductItem();
						item.setProductItemsId(rs.getLong("product_items_id"));
						item.setProductId(rs.getInt("product_id"));
						item.setProductVersions(rs.getInt("Product_Versions"));
						item.setProductColor(rs.getInt("Product_Color"));
						item.setProductMemories(rs.getInt("Product_Memories"));
						item.setProductSize(rs.getInt("Product_Size"));
						item.setProductSalePrice(rs
								.getDouble("Product_SalePrice"));
						item.setProductMarketPrice(rs
								.getDouble("Product_MarketPrice"));
						item.setProductCostPrice(rs
								.getDouble("Product_CostPrice"));
						item.setModifiedOn(rs.getTimestamp("ModifiedOn"));
						item.setStocks(rs.getInt("Stocks"));
						return item;
					}
				});
		if (list.size() > 0)
			return list.get(0);
		else
			return null;

	}

	/**
	 * 新增子产品
	 * @Title: addProductItem 
	 * @Description:
	 * @param productItem
	 * @return int         
	 * @throws
	 */

	public int addProductItem(final ProductItem productItem) {
		int id = 0;
		try {
			final String sql = "insert into tbl_product_items(product_id,product_Versions,product_Color,product_Memories,product_Size,product_SalePrice,product_MarketPrice,product_CostPrice,"
					+ "stocks,modifiedOn) values(?,?,?,?,?,?,?,?,?,?)";
			KeyHolder keyHolder = new GeneratedKeyHolder();
			int result = jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection conn)
						throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql,
							new String[] { "id" });// 返回主键id
					ps.setLong(1, productItem.getProductId());
					ps.setInt(2, productItem.getProductVersions());
					ps.setInt(3, productItem.getProductColor());
					ps.setInt(4, productItem.getProductMemories());
					ps.setInt(5, productItem.getProductSize());
					ps.setDouble(6, productItem.getProductSalePrice());
					ps.setDouble(7, productItem.getProductMarketPrice());
					ps.setDouble(8, productItem.getProductCostPrice());
					ps.setInt(9, productItem.getStocks());
					ps.setTimestamp(10, new Timestamp(System.currentTimeMillis()));
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
	 * 修改子商品默认图片
	 * @Title: updateProductItemImg 
	 * @Description:
	 * @param id
	 * @param url
	 * @return int         
	 * @throws
	 */
	public int updateProductItemImg(int id,String url) {
		int count = 0;
		Object[] params = new Object[] {url,id};
		try {
		String sql = "update tbl_product_items set img_url = ? where product_items_id = ?";
		count = jdbcTemplate.update(sql, params);
		} catch (Exception ex) {
			System.err.println("---------------------------------------------");
			System.err.println(ex.getMessage());
			System.err.println("---------------------------------------------");
		}
		return count;
	}

	/**
	 * 修改子商品
	 * @Title: updateProductItem 
	 * @Description:
	 * @param productItem
	 * @return int         
	 * @throws
	 */
	public int updateProductItem(ProductItem productItem) {
		int count = 0;
		try {
			Object[] params = new Object[] { 
					productItem.getProductSalePrice(),
					productItem.getProductMarketPrice(),
					productItem.getProductCostPrice(),
					productItem.getStocks(),
					new Timestamp(System.currentTimeMillis()),
					productItem.getProductItemsId() };
			String sql = "update tbl_product_items set product_SalePrice=?,product_MarketPrice=?,product_CostPrice=?,stocks=?,modifiedOn=? where product_items_id = ? ";
			count = jdbcTemplate.update(sql, params);
		} catch (Exception ex) {
			System.err.println("---------------------------------------------");
			System.err.println(ex.getMessage());
			System.err.println("---------------------------------------------");
		}
		return count;
	}

	/**
	 * 删除子商品
	 * @Title: delProductItem 
	 * @Description:
	 * @param product_items_id
	 * @return int         
	 * @throws
	 */
	public int delProductItem(int product_items_id) {
		int count = 0;
		try {
			Object[] params = new Object[] {  product_items_id };
			StringBuffer sql = new StringBuffer(
					"delete from tbl_product_items where product_items_id =?");
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
			StringBuffer sql= new StringBuffer("delete from tbl_product_items where product_id in ("); 
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
