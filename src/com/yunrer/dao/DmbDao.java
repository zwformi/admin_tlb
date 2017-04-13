package com.yunrer.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.yunrer.entity.Dmb;
/**
 * 代码表DAO
 * @ClassName DmbDao
 * @Description 
 */
@Repository("DmbDao")
public class DmbDao {

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 新增字典
	 * @Title: addDmb 
	 * @Description:
	 * @param d void         
	 * @throws
	 */
	public void addDmb(Dmb d) {
		String sql = "insert into tbl_dmb (name,sortid,typeid) values (?, ?, ?)";
		Object[] params = new Object[] { d.getName(), d.getSortid(), d.getTypeid() };
		jdbcTemplate.update(sql, params);
	}
	
	/**
	 * 删除字典
	 * @Title: deleteDmb 
	 * @Description:
	 * @param ids void         
	 * @throws
	 */
	public void deleteDmb(Object[] ids) {
		StringBuffer sql= new StringBuffer("update tbl_dmb set isdelete = 1 where id in ("); 
		for (int i = 0; i < ids.length; i++) {
			if (i != 0)
				sql.append(",");
			sql.append("?");
		}
		sql.append(")");
		jdbcTemplate.update(sql.toString(), ids);
	}
	
	/**
	 * 更新字典
	 * @Title: modifyDmb 
	 * @Description:
	 * @param d void         
	 * @throws
	 */
	public void modifyDmb(Dmb d) {
		String sql = "update tbl_dmb set name = ?, sortid = ?, typeid = ? where id = ?";
		Object[] params = new Object[] { d.getName(), d.getSortid(), d.getTypeid(), d.getId() };
		jdbcTemplate.update(sql, params);
	}
	
	/**
	 * 查询字典
	 * @Title: queryDmb 
	 * @Description:
	 * @param id
	 * @return Dmb         
	 * @throws
	 */
	public Dmb queryDmb(int id) {
		String sql = "select * from tbl_dmb where id = ?";
		Object[] params = new Object[] { id };
		List<Dmb> list = jdbcTemplate.query(sql, params,
				new BeanPropertyRowMapper<Dmb>(Dmb.class));
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 查询字典数量
	 * @Title: queryDmbCount 
	 * @Description:
	 * @param keyword
	 * @return int         
	 * @throws
	 */
	public int queryDmbCount(String keyword) {
		String sql = "select count(id) from tbl_dmb where isdelete = 0 and name like ?";
		keyword = "%" + keyword + "%";
		Object[] params = new Object[] { keyword };
		int count = jdbcTemplate.queryForObject(sql, Integer.class, params);
		return count;
	}
	
	/**
	 * 根据关键字查询字典列表
	 * @Title: queryDmbList 
	 * @Description:
	 * @param keyword
	 * @param pageIndex
	 * @param pageSize
	 * @return List<Dmb>         
	 * @throws
	 */
	public List<Dmb> queryDmbList(String keyword, int pageIndex, int pageSize) {
		String sql = "select * from tbl_dmb where isdelete = 0 and name like ? order by sortid desc,typeid desc limit ?,?";
		keyword = "%" + keyword + "%";
		int start = pageIndex * pageSize;
		Object[] params = new Object[] { keyword, start, pageSize };
		List<Dmb> list = jdbcTemplate.query(sql, params,
				new BeanPropertyRowMapper<Dmb>(Dmb.class));
		return list;
	}
	
	/**
	 * 根据类型查询字典列表
	 * @Title: queryDmbList 
	 * @Description:
	 * @param typeid
	 * @return List<Dmb>         
	 * @throws
	 */
	public List<Dmb> queryDmbList(int typeid) {
		String sql = "select * from tbl_dmb where typeid = ? order by sortid desc,id desc";
		Object[] params = new Object[] { typeid };
		List<Dmb> list = jdbcTemplate.query(sql, params,
				new BeanPropertyRowMapper<Dmb>(Dmb.class));
		return list;
	}
	
}