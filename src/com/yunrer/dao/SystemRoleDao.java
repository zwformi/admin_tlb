package com.yunrer.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.yunrer.entity.SystemRole;
/**
 * 系统角色DAO
 * @ClassName SystemRoleDao
 * @Description 
 * @author rujun
 * @date 2016-12-16
 */
@Repository("SystemRoleDao")
public class SystemRoleDao {

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 新增系统角色
	 * @Title: addSystemRole 
	 * @Description:
	 * @param sr void         
	 * @throws
	 */
	public void addSystemRole(SystemRole sr) {
		String sql = "insert into tbl_system_role (role_id,role_name,remarks,create_time) values (?, ?, ?, now())";
		Object[] params = new Object[] { sr.getRole_id(), sr.getRole_name(), sr.getRemarks() };
		jdbcTemplate.update(sql, params);
	}
	
	/**
	 * 删除系统角色
	 * @Title: deleteSystemRole 
	 * @Description:
	 * @param ids void         
	 * @throws
	 */
	public void deleteSystemRole(Object[] ids) {
		StringBuffer sql = new StringBuffer("delete from tbl_system_role where role_id in ("); 
		for (int i = 0; i < ids.length; i++) {
			if (i != 0)
				sql.append(",");
			sql.append("?");
		}
		sql.append(")");
		jdbcTemplate.update(sql.toString(), ids);
		
		sql = new StringBuffer("delete from r_role_node where role_id in ("); 
		for (int i = 0; i < ids.length; i++) {
			if (i != 0)
				sql.append(",");
			sql.append("?");
		}
		sql.append(")");
		jdbcTemplate.update(sql.toString(), ids);
	}
	
	/**
	 * 更新系统角色
	 * @Title: modifySystemRole 
	 * @Description:
	 * @param sr void         
	 * @throws
	 */
	public void modifySystemRole(SystemRole sr) {
		String sql = "update tbl_system_role set role_name = ?, remarks = ? where role_id = ?";
		Object[] params = new Object[] { sr.getRole_name(), sr.getRemarks(), sr.getRole_id() };
		jdbcTemplate.update(sql, params);
	}
	
	/**
	 * 查询系统角色
	 * @Title: querySystemRole 
	 * @Description:
	 * @param id
	 * @return SystemRole         
	 * @throws
	 */
	public SystemRole querySystemRole(int id) {
		String sql = "select * from tbl_system_role where role_id = ?";
		Object[] params = new Object[] { id };
		List<SystemRole> list = jdbcTemplate.query(sql, params,
				new BeanPropertyRowMapper<SystemRole>(SystemRole.class));
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 查询系统角色数量
	 * @Title: querySystemRoleCount 
	 * @Description:
	 * @param keyword
	 * @return int         
	 * @throws
	 */
	public int querySystemRoleCount(String keyword) {
		String sql = "select count(role_id) from tbl_system_role where role_name like ? or remarks like ?";
		keyword = "%" + keyword + "%";
		Object[] params = new Object[] { keyword, keyword };
		int count = jdbcTemplate.queryForObject(sql, Integer.class, params);
		return count;
	}
	
	/**
	 * 查询系统角色列表
	 * @Title: querySystemRoleList 
	 * @Description:
	 * @param keyword
	 * @param pageIndex
	 * @param pageSize
	 * @return List<SystemRole>         
	 * @throws
	 */
	public List<SystemRole> querySystemRoleList(String keyword, int pageIndex, int pageSize) {
		String sql = "select * from tbl_system_role where role_name like ? or remarks like ? order by create_time desc limit ?,?";
		keyword = "%" + keyword + "%";
		int start = pageIndex * pageSize;
		Object[] params = new Object[] { keyword, keyword, start, pageSize };
		List<SystemRole> list = jdbcTemplate.query(sql, params,
				new BeanPropertyRowMapper<SystemRole>(SystemRole.class));
		return list;
	}
	
}