package com.yunrer.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.mysql.jdbc.CallableStatement;
import com.yunrer.entity.ProductAttrTemplate;
import com.yunrer.entity.UserCompany;
/**
 * 更新属性模板DAO
 * @ClassName ProductAttrTemplateDao
 * @Description 
 * @author rujun
 * @date 2017-1-16
 */
@Repository("ProductAttrTemplateDao")
public class ProductAttrTemplateDao {
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	

	public int addAttrTemp(final ProductAttrTemplate temp){
		
		int id =0;
		int result =0;
		
		try {
			final  String sql = "INSERT INTO `tbl_product_attr_template` (category_id,category_name,sub_name,root_name,zcy_attr_id,attrName,`group`,isRequired,isImportant,isSukCandidate,isUserDefined,valueType,attrVals,local_type_id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
			KeyHolder keyHolder = new GeneratedKeyHolder();   
			result = jdbcTemplate.update(new PreparedStatementCreator(){   
	            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {   
	                PreparedStatement ps =  conn.prepareStatement(sql, new String[]{"id"});//返回主键id   
	                ps.setInt(1, temp.getCategory_id());
	                ps.setString(2,temp.getCategory_name());
	                ps.setString(3, temp.getSub_name());
	                ps.setString(4, temp.getRoot_name());
	                ps.setInt(5, temp.getZcy_attr_id());
	                ps.setString(6, temp.getAttrName());
	                ps.setString(7, temp.getGroup());
	                ps.setInt(8, temp.getIsRequired());
	                ps.setInt(9,temp.getIsImportant());
	                ps.setInt(10, temp.getIsSukCandidate());
	                ps.setInt(11, temp.getIsUserDefined());
	                ps.setString(12,temp.getValueType());
	                ps.setString(13, temp.getAttrVals());
	                ps.setInt(14, temp.getLocal_type_id());
	               
	                return ps;   
	            }
	        }, keyHolder);   

		} catch (Exception ex) {
			System.err.println("---------------------------------------------");
			System.err.println(ex.getMessage());
			System.err.println("---------------------------------------------");
		}

		
		return result;
	}
	
	public int delTemp(int category_id) {
		int count =0;
		try {
			StringBuffer sql= new StringBuffer("delete from tbl_product_attr_template where category_id = ? "); 

			count = jdbcTemplate.update(sql.toString(),category_id);
		} catch (Exception ex) {
			System.err.println("---------------------------------------------");
			System.err.println(ex.getMessage());
			System.err.println("---------------------------------------------");
		}
		return count;
	}
	

	public List getSukAttr(int product_id) {
		List list = null;
		try {
			StringBuffer sql= new StringBuffer("call getLimitedSkuAttrs(?)"); 

			list = jdbcTemplate.queryForList(sql.toString(),product_id);
		} catch (Exception ex) {
			System.err.println("---------------------------------------------");
			System.err.println(ex.getMessage());
			System.err.println("---------------------------------------------");
		}
		if(list!=null && list.size()>0)
		return list;
		else return null;
	}
	
	/**
	 * 调接口同步匹配
	 * @Title: updateTemp 
	 * @Description:
	 * @param category_id
	 * @return int         
	 * @throws
	 */
	public int updateTemp(int category_id,ProductAttrTemplate productAttrTemplate,int edit_flag) {
		int count =0;
		List<Object> paramlist = new ArrayList<Object>();
		StringBuffer sql= new StringBuffer("update tbl_product_attr_template set `group` = ?,isRequired = ?,isImportant = ?,isSukCandidate = ?,isUserDefined = ?,valueType = ?,delete_flag = 0 ");
		try {
				paramlist.add(productAttrTemplate.getGroup());
				paramlist.add(productAttrTemplate.getIsRequired());
				paramlist.add(productAttrTemplate.getIsImportant());
				paramlist.add(productAttrTemplate.getIsSukCandidate());
				paramlist.add(productAttrTemplate.getIsUserDefined());
				paramlist.add(productAttrTemplate.getValueType());
				if(edit_flag==1){
					paramlist.add(productAttrTemplate.getAttrVals());
					sql.append(",attrVals = ? ");
				}
				sql.append(" where  category_id = ? and attrName = ?");
				paramlist.add(productAttrTemplate.getCategory_id());
				paramlist.add(productAttrTemplate.getAttrName());
				
			
			count = jdbcTemplate.update(sql.toString(),paramlist.toArray());
		} catch (Exception ex) {
			System.err.println("---------------------------------------------");
			System.err.println(ex.getMessage());
			System.err.println("---------------------------------------------");
		}
		return count;
	}
	
	/**
	 * 根据三级类目查询模板
	 * @Title: queryTempListByCategory 
	 * @Description:
	 * @param categoryId
	 * @return List<ProductAttrTemplate>         
	 * @throws
	 */
	public List<ProductAttrTemplate> queryTempListByCategory(int categoryId){
		StringBuffer sql = new StringBuffer("select * from tbl_product_attr_template where category_id = ? and delete_flag = 0");
		Object[] params = new Object[] { categoryId};
		List<ProductAttrTemplate> list = jdbcTemplate.query(sql.toString(),params,
				new BeanPropertyRowMapper<ProductAttrTemplate>(ProductAttrTemplate.class));
			if(list.isEmpty()){
				return null;
			}else{
				return list;
			}
	}
	
	/**
	 * 根据id删除标记删除template
	 * @Title: deleteTemplateWithCode 
	 * @Description:
	 * @param id
	 * @param flag
	 * @return int         
	 * @throws
	 */
	public int deleteTemplateWithCode(int id,int flag){
		int count =0;
		try {
			StringBuffer sql= new StringBuffer("update tbl_product_attr_template set delete_flag = ? where id = ? "); 
			Object[] params = new Object[] { flag , id};
			count = jdbcTemplate.update(sql.toString(),params);
		} catch (Exception ex) {
			System.err.println("---------------------------------------------");
			System.err.println(ex.getMessage());
			System.err.println("---------------------------------------------");
		}
		return count;
	
	}
	
	/**
	 * 根据id删除标记删除template
	 * @Title: deleteTemplateWithCodeByCate
	 * @Description:
	 * @param id
	 * @param flag
	 * @return int         
	 * @throws
	 */
	public int deleteTemplateWithCodeByCate(int id,int flag){
		int count =0;
		try {
			StringBuffer sql= new StringBuffer("update tbl_product_attr_template set delete_flag = ? where category_id = ? "); 
			Object[] params = new Object[] { flag , id};
			count = jdbcTemplate.update(sql.toString(),params);
		} catch (Exception ex) {
			System.err.println("---------------------------------------------");
			System.err.println(ex.getMessage());
			System.err.println("---------------------------------------------");
		}
		return count;
	
	}
	
	/**
	 * 更新attrVals的值
	 * @Title: updateAttrVals 
	 * @Description:
	 * @param attr_id
	 * @param vals
	 * @return int         
	 * @throws
	 */
	public int updateAttrVals(int attr_id,String vals) {
		int count =0;
			Object[] params = new Object[] {vals,attr_id};
			String sql = "update tbl_product_attr_template set attrVals = ? where id = ?";
			count = jdbcTemplate.update(sql,params);
		return count;
	}
	

	/**
	 * 根据id查询模板
	 * @Title: queryTempById 
	 * @Description:
	 * @param id
	 * @return ProductAttrTemplate         
	 * @throws
	 */
	public ProductAttrTemplate queryTempById(int id){
		StringBuffer sql = new StringBuffer("select * from tbl_product_attr_template where id = ?");
		Object[] params = new Object[] { id};
		List<ProductAttrTemplate> list = jdbcTemplate.query(sql.toString(),params,
				new BeanPropertyRowMapper<ProductAttrTemplate>(ProductAttrTemplate.class));
			if(list.isEmpty()){
				return null;
			}else{
				return list.get(0);
			}
	}
	
	/**
	 * 查询temp表
	 * @Title: queryAttrTempList
	 * @Description:
	 * @param temp
	 * @param pageIndex
	 * @param pageSize
	 * @return List         
	 * @throws
	 */
	@SuppressWarnings("rawtypes")
	public  List  queryAttrTempList(ProductAttrTemplate temp,int pageIndex,int pageSize){
		String sql ="select * from tbl_product_attr_template where  delete_flag=0 ";
		
		int start = pageIndex * pageSize;
		List<Object> paramlist = new ArrayList<Object>();
		if(temp.getAttrName()!=null&&temp.getAttrName().length()>0){
			sql +="  and (attrName like ? or `group` like ?) ";
			paramlist.add("%"+temp.getAttrName()+"%");
			paramlist.add("%"+temp.getAttrName()+"%");
		}
		if(temp.getIsRequired()!=null){
			sql +=" and isRequired = ? ";
			paramlist.add(temp.getIsRequired());
		}
		if(temp.getIsSukCandidate()!=null){
			sql +=" and isSukCandidate = ? ";
			paramlist.add(temp.getIsSukCandidate());
		}
		if(temp.getIsImportant()!=null){
			sql += "and isImportant = ? ";
			paramlist.add(temp.getIsImportant());	
		}
		if(temp.getIsUserDefined()!=null){
			sql += " and isUserDefined = ? ";
			paramlist.add(temp.getIsUserDefined());
		}
		if(temp.getCategory_id()!=null){
			sql += " and category_id = ? ";
			paramlist.add(temp.getCategory_id());
		}
		if(temp.getGroup()!=null&&temp.getGroup().length()>0){
			sql +=" and group like ? ";
			paramlist.add("%"+temp.getGroup()+"%");
		}
		sql +=" ORDER BY CONVERT( `group` USING gbk ) COLLATE gbk_chinese_ci asc  , isRequired desc , id asc limit ?,?";
		paramlist.add(start);
		paramlist.add(pageSize);
		List<ProductAttrTemplate> list = jdbcTemplate.query(sql,paramlist.toArray(),
				new BeanPropertyRowMapper<ProductAttrTemplate>(ProductAttrTemplate.class));
		return list;
		
	}
	
	/**
	 * 查询attr表
	 * @Title: queryAttrTempList
	 * @Description:
	 * @param temp
	 * @param pageIndex
	 * @param pageSize
	 * @return List         
	 * @throws
	 */
	@SuppressWarnings("rawtypes")
	public  int  queryAttrTempForCount(ProductAttrTemplate temp){
		String sql ="select count(1) from tbl_product_attr_template where  delete_flag=0 ";

		List<Object> paramlist = new ArrayList<Object>();
		if(temp.getIsRequired()!=null){
			sql +=" and isRequired = ? ";
			paramlist.add(temp.getIsRequired());
		}
		if(temp.getIsSukCandidate()!=null){
			sql +=" and isSukCandidate = ? ";
			paramlist.add(temp.getIsSukCandidate());
		}
		if(temp.getIsImportant()!=null){
			sql += "and isImportant = ? ";
			paramlist.add(temp.getIsImportant());	
		}
		if(temp.getIsUserDefined()!=null){
			sql += " and isUserDefined = ? ";
			paramlist.add(temp.getIsUserDefined());
		}
		if(temp.getCategory_id()!=null){
			sql += " and category_id = ? ";
			paramlist.add(temp.getCategory_id());
		}
		if(temp.getGroup()!=null&&temp.getGroup().length()>0){
			sql +=" and group like ? ";
			paramlist.add("%"+temp.getGroup()+"%");
		}
		if(temp.getAttrName()!=null&&temp.getAttrName().length()>0){
			sql +="  and (attrName like ? or `group` like ?) ";
			paramlist.add("%"+temp.getAttrName()+"%");
			paramlist.add("%"+temp.getAttrName()+"%");
		}
			int count = jdbcTemplate.queryForObject(sql, Integer.class,paramlist.toArray());
		return count;
		
	}
	
}
