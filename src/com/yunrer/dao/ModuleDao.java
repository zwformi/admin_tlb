package com.yunrer.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.yunrer.entity.SystemUser;
import com.yunrer.entity.YunModule;

/**
 * 云模块DAO
 * @ClassName ModuleDao
 * @Description 
 * @author rujun
 * @date 2016-11-28
 */
@Repository("ModuleDao")
public class ModuleDao {

	@Resource(name="yunTemplate")
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 查询模块数量
	 * @Title: queryModulecount 
	 * @Description:
	 * @param module
	 * @return int         
	 * @throws
	 */
	public int queryModulecount(YunModule module) {
		List<Object> paramlist = new ArrayList<Object>();
		String sql = "select count(1) from tbl_yun_module a left join tbl_yun_type b on a.type_id = b.type_id where 1=1 ";
		if(null!=module.getName()&&!"".equals(module.getName())){	//模块名查询
			sql += " and a.name like ? ";
			paramlist.add("%"+module.getName()+"%");
		}
		if(null!=module.getType_id()&&module.getType_id()!=0){	//模块类id查询
			sql += "and b.type_id = ? ";
			paramlist.add(module.getType_id());
		}
		if(null!=module.getDelete_flag()&&!"".equals(module.getDelete_flag())){	//上下架查询
			sql += "and a.delete_flag = ?";
			paramlist.add(module.getDelete_flag());
		}
		if(module.getIs_combination()!=null&&(module.getIs_combination()==1||module.getIs_combination()==0)){	//是否组合
			sql += " a.is_combination = ?  ";
			paramlist.add(module.getIs_combination());
		}
		sql+=" order by b.sort_code,a.sort_code ";
		int total_count = jdbcTemplate.queryForObject(sql, Integer.class,paramlist.toArray());
		return total_count;
	}
	
	/**
	 * 查询模块列表
	 * @Title: querModuleList 
	 * @Description:
	 * @param module
	 * @param pageIndex
	 * @param pageSize
	 * @return List<Module>         
	 * @throws
	 */
	public List<YunModule> queryModuleList(YunModule module,int pageIndex,int pageSize) {
			int start = pageIndex * pageSize;
		List<Object> paramlist = new ArrayList<Object>();
		String sql = "select a.*,b.name type_name from tbl_yun_module a left join tbl_yun_type b on a.type_id = b.type_id where 1=1 ";
		if(null!=module.getName()&&!"".equals(module.getName())){	//模块名查询
			sql += " and a.name like ? ";
			paramlist.add("%"+module.getName()+"%");
		}
		if(null!=module.getType_id()&&module.getType_id()!=0){	//模块类id查询
			sql += "and b.type_id = ? ";
			paramlist.add(module.getType_id());
		}
		if(null!=module.getDelete_flag()&&!"".equals(module.getDelete_flag())){	//上下架查询
			sql += "and a.delete_flag = ?";
			paramlist.add(module.getDelete_flag());
		}
		if(module.getIs_combination()!=null&&(module.getIs_combination()==1||module.getIs_combination()==0)){	//是否组合
			sql += " a.is_combination = ?  ";
			paramlist.add(module.getIs_combination());
		}
		paramlist.add(start);
		paramlist.add(pageSize);
		sql += " order by b.sort_code,a.sort_code  limit ?,?";
		List<YunModule> list = jdbcTemplate.query(sql,paramlist.toArray(),
		new BeanPropertyRowMapper<YunModule>(YunModule.class));
		System.out.println(list);
		return list;
	}
	

	/**
	 * 查询模块列表（Map）
	 * @Title: queryOrderList 
	 * @Description:
	 * @param module
	 * @return List<Map<String,Object>>         
	 * @throws
	 */
	public List<Map<String,Object>> queryOrderList(YunModule module) {
		List<Object> paramlist = new ArrayList<Object>();
		String sql = "select a.*,b.name type_name from tbl_yun_module a left join tbl_yun_type b on a.type_id = b.type_id where 1=1 ";
		if(null!=module.getName()&&!"".equals(module.getName())){	//模块名查询
			sql += " and a.name like ? ";
			paramlist.add("%"+module.getName()+"%");
		}
		if(null!=module.getType_id()&&module.getType_id()!=0){	//模块类id查询
			sql += "and b.type_id = ? ";
			paramlist.add(module.getType_id());
		}
		if(null!=module.getDelete_flag()&&!"".equals(module.getDelete_flag())){	//上下架查询
			sql += "and a.delete_flag = ?";
			paramlist.add(module.getDelete_flag());
		}
		if(module.getIs_combination()!=null&&(module.getIs_combination()==1||module.getIs_combination()==0)){	//是否组合
			sql += " a.is_combination = ?  ";
			paramlist.add(module.getIs_combination());
		}
		sql+=" order by b.sort_code,a.sort_code ";
		List<Map<String,Object>> list = jdbcTemplate.queryForList(sql,paramlist.toArray());
		return list;
	}
	
	
	/**
	 * 新增模块
	 * @Title: addModule 
	 * @Description:
	 * @param m 
	 * @return int         
	 * @throws
	 */
	public int addModule(YunModule m){
		try{
			String sql = "insert into tbl_yun_module (type_id,name,introduction,price_old,price_new,icon,bgcolor,url,delete_flag,default_flag,is_combination,sort_code) values (?,?,?,?,?,?,?,?,?,?,?,?)";
			Object[] params = new Object[] { m.getType_id(),m.getName(),m.getIntroduction(),m.getPrice_old(),m.getPrice_new(),m.getIcon(),m.getBgcolor(),m.getUrl(),m.getDelete_flag(),m.getDefault_flag(),m.getIs_combination(),m.getSort_code() };
			int count = jdbcTemplate.update(sql, params);
			return count;
		}catch (Exception ex) {
			System.err.println("---------------------------------------------");
			System.err.println(ex.getMessage());
			System.err.println("---------------------------------------------");
			throw new RuntimeException("新增模块出现错误！");
		}
	}
		

	/**
	 * 更新模块
	 * @Title: updateModule 
	 * @Description:
	 * @param m
	 * @return int         
	 * @throws
	 */
	public int updateModule(YunModule m){
		int count =0;
		String sql = "update tbl_yun_module set type_id =?,name=?,introduction=?,price_old=?,price_new=?,icon=?,bgcolor=?,url=?,delete_flag=?,default_flag=?,is_combination=?,sort_code=? where module_id=? ";
		try {
			Object[] params = new Object[] { m.getType_id(),m.getName(),m.getIntroduction(),m.getPrice_old(),m.getPrice_new(),m.getIcon(),m.getBgcolor(),m.getUrl(),m.getDelete_flag(),m.getDefault_flag(),m.getIs_combination(),m.getSort_code(),m.getModule_id()};
			return count = jdbcTemplate.update(sql.toString(),params);
		} catch (Exception ex) {
			System.err.println("---------------------------------------------");
			System.err.println(ex.getMessage());
			System.err.println("---------------------------------------------");
			throw new RuntimeException("新增模块错误！");
		}
	}
	
	
	
	/**
	 * 查询模块详情
	 * @Title: queryModule_Detail 
	 * @Description:
	 * @param id
	 * @return YunModule         
	 * @throws
	 */
	public YunModule queryModule_Detail(int id) {
		String sql = "select a.* from tbl_yun_module a left join tbl_yun_type b on a.type_id = b.type_id where module_id = ?";
		Object[] params = new Object[] { id};
		List<YunModule> list = jdbcTemplate.query(sql, params,
		new BeanPropertyRowMapper<YunModule>(YunModule.class));
		if(null!=list&&!list.isEmpty()){
			return list.get(0);
		}else{
			return null;
		}
	}
	

	/**
	 * 删除模块
	 * @Title: deleteModule 
	 * @Description:只是delete_flag置1
	 * @param ids
	 * @return int         
	 * @throws
	 */
	public int deleteModule(Object[] ids) {
		StringBuffer sql= new StringBuffer("update tbl_yun_module set delete_flag = 1 where module_id in ("); 
		for (int i = 0; i < ids.length; i++) {
			if (i != 0)
				sql.append(",");
			sql.append("?");
		}
		sql.append(")");
		int i = jdbcTemplate.update(sql.toString(), ids);
		return i;
	}
	

}