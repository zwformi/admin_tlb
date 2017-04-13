package com.yunrer.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.yunrer.entity.ProductType;
/**
 * 商品分类DAO
 * @ClassName ProductTypeDao
 * @Description 
 * @author rujun
 * @date 2016-12-16
 */
@Repository("ProductTypeDao")
public class ProductTypeDao {
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 新增商品分类
	 * @Title: addProductType 
	 * @Description:
	 * @param pt void         
	 * @throws
	 */
	public void addProductType(ProductType pt) {
		String sql = "insert into tbl_product_type (name,parentId,img_url,sortId,sub_parentId,product_level) values (?, ?, ?, ?,?,?)";
		Object[] params = new Object[] { pt.getName(), pt.getParentId(), pt.getImg_url(), pt.getSortId(),pt.getSub_parentId(),pt.getProduct_level() };
		jdbcTemplate.update(sql, params);
	}
	
	public void addProductTypeByZct(ProductType pt) {
		String sql = "insert into tbl_product_type (id,name,parentId,img_url,sortId,sub_parentId,product_level) values (?, ?, ?, ?, ?, ?, ?)";
		Object[] params = new Object[] {pt.getId(), pt.getName(), pt.getParentId(), pt.getImg_url(), pt.getSortId(),pt.getSub_parentId(),pt.getProduct_level() };
		jdbcTemplate.update(sql, params);
	}
	
	/**
	 * 商品分类软删除
	 * @Title: deleteProductType 
	 * @Description:
	 * @param ids void         
	 * @throws
	 */
	public void deleteProductType(Object[] ids) {
		StringBuffer sql= new StringBuffer("update tbl_product_type set delete_flag = 1 where id in ("); 
		for (int i = 0; i < ids.length; i++) {
			if (i != 0)
				sql.append(",");
			sql.append("?");
		}
		sql.append(")");
		jdbcTemplate.update(sql.toString(), ids);
	}
	
	/**
	 * 更新商品分类
	 * @Title: modifyProductType 
	 * @Description:
	 * @param pt void         
	 * @throws
	 */
	public void modifyProductType(ProductType pt) {
		String sql = "update tbl_product_type set name = ?, parentId = ?,sub_parentId = ? ,product_level =?, img_url = ?, sortId = ? where id = ?";
		Object[] params = new Object[] { pt.getName(), pt.getParentId(), pt.getSub_parentId(),pt.getProduct_level(),pt.getImg_url(), pt.getSortId(), pt.getId() };
		jdbcTemplate.update(sql, params);
	}
	
	/**
	 * 查询商品分类
	 * @Title: queryProductType 
	 * @Description:
	 * @param id
	 * @return ProductType         
	 * @throws
	 */
	public ProductType queryProductType(int id) {
		String sql = "select * from tbl_product_type where id = ? and delete_flag = 0";
		Object[] params = new Object[] { id };
		List<ProductType> list = jdbcTemplate.query(sql, params,
				new BeanPropertyRowMapper<ProductType>(ProductType.class));
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 查询分类数量
	 * @Title: queryProductTypeCount 
	 * @Description:
	 * @param keyword
	 * @return int         
	 * @throws
	 */
	public int queryProductTypeCount(String keyword) {
		String sql = "select count(id) from tbl_product_type where delete_flag = 0 and name like ? order by sortId desc,id desc";
		keyword = "%" + keyword + "%";
		Object[] params = new Object[] { keyword };
		int count = jdbcTemplate.queryForObject(sql, Integer.class, params);
		return count;
	}
	
	/**
	 * 查询分类列表
	 * @Title: queryProductTypeList 
	 * @Description:
	 * @param keyword
	 * @param pageIndex
	 * @param pageSize
	 * @return List<ProductType>         
	 * @throws
	 */
	public List<ProductType> queryProductTypeList(String keyword, int pageIndex, int pageSize) {
		String sql = "select pt.*,(select name from tbl_product_type pt2 where pt2.id = pt.parentId) as parentName,(select name from tbl_product_type pt3 where pt3.id = pt.sub_parentId) subParentName  from tbl_product_type pt where delete_flag = 0 and name like ? order by sortId desc,id desc";
		//String sql = "select * from tbl_product_type where delete_flag = 0 and name like ? order by sortId desc,id desc limit ?,?";
		keyword = "%" + keyword + "%";
		Object[] params = new Object[]{};
		if(pageIndex!=0||pageSize!=0){
			int start = pageIndex * pageSize;
			params = new Object[] { keyword, start, pageSize };
			sql+= " limit ?,?";
		}else{
			params = new Object[] { keyword};
		}
		
		List<ProductType> list = jdbcTemplate.query(sql, params,
				new BeanPropertyRowMapper<ProductType>(ProductType.class));
		return list;
	}
	
	/**
	 * 查询分类列表
	 * @Title: queryProductTypeList 
	 * @Description:
	 * @param keyword
	 * @param pageIndex
	 * @param pageSize
	 * @return List<ProductType>         
	 * @throws
	 */
	public List<ProductType> queryProductTypeListForTree(String keyword, int pageIndex, int pageSize) {
		String sql = "select pt.zcy_id id,parentId,sub_parentId,product_level,name,(select name from tbl_zcy_menu pt2 where pt2.zcy_id = pt.parentId) as parentName,(select name from tbl_zcy_menu pt3 where pt3.zcy_id = pt.sub_parentId) subParentName  from tbl_product_type pt where name like ? order by id desc ";
			keyword = "%" + keyword + "%";
		Object[] params = new Object[]{};
		if(pageIndex!=0||pageSize!=0){
			int start = pageIndex * pageSize;
			params = new Object[] { keyword, start, pageSize };
			sql+= " limit ?,?";
		}else{
			params = new Object[] { keyword};
		}
		
		List<ProductType> list = jdbcTemplate.query(sql, params,
				new BeanPropertyRowMapper<ProductType>(ProductType.class));
		return list;
	}
	
	/**
	 * 根据level查询
	 * @Title: queryProductByLevel 
	 * @Description:
	 * @param id
	 * @param level
	 * @return List<ProductType>         
	 * @throws
	 */
	public List<ProductType> queryProductByLevel(int level) {
		String sql = "";
		if(level==1){	//一级
			sql = "select id,product_level,name,parentId from tbl_product_type where delete_flag = 0 and product_level = 1";
		}else if(level==2){	//
			sql = "select id,parentId,product_level,name from tbl_product_type where delete_flag = 0 and product_level = 2";
		}else if(level==3){
			sql = "select id,sub_parentId parentId,product_level,name from tbl_product_type where delete_flag = 0 and product_level = 3";
		}
			sql += " order by sortid desc,id desc";
		List<ProductType> list = jdbcTemplate.query(sql, 
		new BeanPropertyRowMapper<ProductType>(ProductType.class));
		return list;
	}
	
	
	/**
	 * 根据大类id获取列表（更新商品结构后弃用）
	 * @Title: queryProductTypeList 
	 * @Description:
	 * @param parentid
	 * @return List<ProductType>         
	 * @throws
	 */
	public List<ProductType> queryProductTypeList(int parentid) {
		String sql = "select * from tbl_product_type where parentId = ? and delete_flag = 0 order by sortid desc,id desc";
		Object[] params = new Object[] { parentid };
		List<ProductType> list = jdbcTemplate.query(sql, params,
		new BeanPropertyRowMapper<ProductType>(ProductType.class));
		return list;
	}
	
	
	/**
	 * 删除某大类相关的纪录
	 * @param parentid
	 * @return
	 */
	public int deleteTypesById(int parentid){
		String sql = "delete from tbl_product_type where id = ? or parentId = ? or sub_parentId= ? ;";
		Object[] params = new Object[]{parentid,parentid,parentid};
		return jdbcTemplate.update(sql, params);
 		
	}
	
	/**
	 * 获取某一大类下的三级类
	 * @param parentid
	 * @return
	 */
	public List<ProductType> getCategoryIds(int id){
		String sql = "select * from tbl_product_type where  parentId = ? or sub_parentId = ?  or id=? and product_level=3 ;";
		Object[] params = new Object[]{id,id,id};
		return jdbcTemplate.query(sql, params,new BeanPropertyRowMapper<ProductType>(ProductType.class));
		
	}
	
	/**
	 * 获取所有三级类
	 * @param parentid
	 * @return
	 */
	public List<ProductType> getAllCategoryIds(){
		String sql = "select * from tbl_product_type where  product_level=3 ;";
		return jdbcTemplate.query(sql,new BeanPropertyRowMapper<ProductType>(ProductType.class));
		
	}
	
}