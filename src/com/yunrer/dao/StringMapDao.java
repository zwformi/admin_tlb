package com.yunrer.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.yunrer.entity.Column;
import com.yunrer.entity.Dmb;
import com.yunrer.entity.StringMap;
import com.yunrer.entity.Table;

/**
 * 字段表DAO
 * @ClassName StringMapDao
 * @Description 
 * @author rujun
 * @date 2016-12-16
 */
@Repository("StringMapDao")
public class StringMapDao {

	@Resource 
	private JdbcTemplate template;
	/**
	 * 根据表和字段获取look up
	 * @Title: getStringMap 
	 * @Description:
	 * @param objecttypename
	 * @param filedname
	 * @return List<StringMap>         
	 * @throws
	 */
	public List<StringMap> getStringMap(String objecttypename , String filedname){
		 List<StringMap> list = new ArrayList<StringMap>();
		String sql ="select id,value,name,pinyin,objecttypename,filedname from sys_stringmap where ObjectTypeName = ? and FiledName = ? order by value,name";
		Object[] params = new Object[]{objecttypename,filedname};
		list = template.query(sql, params,new BeanPropertyRowMapper<StringMap>(StringMap.class));
		return list;
		
	}
	
	/**
	 * 根据表字段value获取某条look up
	 * @Title: getName 
	 * @Description:
	 * @param objecttypename
	 * @param filedname
	 * @param value
	 * @return StringMap         
	 * @throws
	 */
	public StringMap getName(String objecttypename , String filedname,int value){
		List<StringMap> list = new ArrayList<StringMap>();
		String sql ="select id,value,name,pinyin,objecttypename,filedname from sys_stringmap where ObjectTypeName = ? and FiledName = ? and Value= ?";
		Object[] params = new Object[]{objecttypename,filedname,value};
		list = template.query(sql, params,new BeanPropertyRowMapper<StringMap>(StringMap.class));
		if(list!=null && list.size()>0)
			return list.get(0);
	 return null;
	}
	
	/**
	 * 查重
	 * @Title: checkRepeate 
	 * @Description:
	 * @param stringmap
	 * @param type
	 * @return int         
	 * @throws
	 */
	public int  checkRepeate(StringMap stringmap,String type){
		String  sql = "";
		Object[] params = null;
		if (type.equals("insert")){
			sql = "select count(1) from sys_stringmap where ( name=? or value =? ) and objecttypename=? and filedname=?";
			params = new Object[]{
					stringmap.getName(),stringmap.getValue(),
					stringmap.getObjectTypeName(),stringmap.getFiledName()
					
			};
		}

		if(type.equals("update")){
			sql = "select count(1) from sys_stringmap where ( name=? or value =? )  and objecttypename=? and filedname=? and id!=?";
			params = new Object[]{
					stringmap.getName(),stringmap.getValue(),
					stringmap.getObjectTypeName(),stringmap.getFiledName(),stringmap.getId()
					
			};
		}
		
		return	template.queryForObject(sql,params,Integer.class);
	}
	
	/**
	 * 新增stringmap
	 * @Title: addStringmap 
	 * @Description:
	 * @param stringmap
	 * @return int         
	 * @throws
	 */
	public int addStringmap(StringMap stringmap) {
		String sql = "insert into sys_stringmap (name,pinyin,value,objecttypename,filedname) values (?, ?, ?,?,?)";
		Object[] params = new Object[] { stringmap.getName(),stringmap.getPinyin(),stringmap.getValue(),stringmap.getObjectTypeName(),stringmap.getFiledName()};
		return template.update(sql, params);
	}
	
	/**
	 * 批量删除stringmap
	 * @Title: deleteStringmap 
	 * @Description:
	 * @param ids
	 * @return int         
	 * @throws
	 */
	public int deleteStringmap(Object[] ids) {
		StringBuffer sql= new StringBuffer("delete from sys_stringmap where id in ("); 
		for (int i = 0; i < ids.length; i++) {
			if (i != 0)
				sql.append(",");
			sql.append("?");
		}
		sql.append(")");
		return template.update(sql.toString(), ids);
	}
	
	/**
	 * 更新stringmap
	 * @Title: modifyStringmap 
	 * @Description:
	 * @param stringmap
	 * @return int         
	 * @throws
	 */
	public int  modifyStringmap(StringMap stringmap) {
		String sql = "update sys_stringmap set name = ?, pinyin = ?,value=?, objecttypename = ?,filedname=? where id = ?";
		Object[] params = new Object[] { stringmap.getName(),stringmap.getPinyin(),stringmap.getValue(),
				stringmap.getObjectTypeName(),stringmap.getFiledName(),stringmap.getId()};
		return template.update(sql, params);
	}
	
	/**
	 * 根据id查询stringmap
	 * @Title: queryStringmap 
	 * @Description:
	 * @param id
	 * @return StringMap         
	 * @throws
	 */
	public StringMap queryStringmap(int id) {
		String sql = "select * from sys_stringmap where id = ?";
		Object[] params = new Object[] { id };
		List<StringMap> list = template.query(sql, params,
				new BeanPropertyRowMapper<StringMap>(StringMap.class));
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 查询stringmap数量（搜索）
	 * @Title: queryStringmapCount 
	 * @Description:
	 * @param keyword
	 * @return int         
	 * @throws
	 */
	public int queryStringmapCount(String keyword) {
		String sql = "select count(id) from sys_stringmap where "
				+ " name like ? "
				+ " or filedname like ? "
				+ " or objecttypename like ? "
				+ " or pinyin like ? "
				+ " or value like ? ";
		keyword = "%" + keyword + "%";
		Object[] params = new Object[] { keyword,keyword,keyword,keyword,keyword };
		int count = template.queryForObject(sql, Integer.class, params);
		return count;
	}
	
	/**
	 * 查询stringmap列表-搜索/分页
	 * @Title: queryPagingStringMapList 
	 * @Description:
	 * @param keyword
	 * @param pageIndex
	 * @param pageSize
	 * @param orderParam
	 * @return List<StringMap>         
	 * @throws
	 */
	public List<StringMap> queryPagingStringMapList(String keyword, int pageIndex, int pageSize,String orderParam ) {
		String sql   = "select * from sys_stringmap where  name like ? "
				+ " or filedname like ? "
				+ " or objecttypename like ? "
				+ " or pinyin like ? "
				+ " or value like ? ";
				 if(orderParam!=null && !orderParam.equals(""))
					 sql +=" order by "+orderParam;
				 else 
					 sql += " order by id ";

				sql += " limit ?,?";
		keyword = "%" + keyword + "%";
		int start = pageIndex * pageSize;
		Object[] params = new Object[] { keyword, keyword,keyword,keyword,keyword,start, pageSize };
		List<StringMap> list = template.query(sql, params,
				new BeanPropertyRowMapper<StringMap>(StringMap.class));
		return list;
	}

	/**
	 * 查询非系统表
	 * @Title: queryTables 
	 * @Description:
	 * @return List<?>         
	 * @throws
	 */
	public List<?> queryTables() {
				 String  sql =  " select table_name,table_comment from information_schema.TABLES where table_name LIKE 'tbl_%'; ";
		List<?> list = template.query(sql,new BeanPropertyRowMapper<Table>(Table.class));
		if(list!=null && list.size()>0)
			return list ;
		else return null;
	}

	
	/**
	 * 查询表的所有lookup相关字段（除id）
	 * @Title: queryColumns 
	 * @Description:
	 * @param tablename
	 * @return List<?>         
	 * @throws
	 */
	public List<?> queryColumns(String tablename) {
		String sql = "select COLUMN_NAME , COLUMN_COMMENT ,COLUMN_TYPE,COLUMN_KEY,IS_NULLABLE  from  "
				+ " information_schema.COLUMNS where table_name = ?  and COLUMN_KEY not in( 'PRI' ) and COLUMN_TYPE like '%int%'";
		Object[] params  =new Object[]{tablename};
		List<?> list = template.query(sql, params, new BeanPropertyRowMapper<Column>(Column.class));
		if(list!=null && list.size()>0)
			return list ;
		else return null;
	}

	
}
