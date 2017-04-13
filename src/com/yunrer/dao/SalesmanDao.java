package com.yunrer.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.yunrer.entity.Salesman;
/**
 * 销售人员DAO
 * @ClassName SalesmanDao
 * @Description 
 * @author rujun
 * @date 2016-12-16
 */
@Repository("SalesmanDao")
public class SalesmanDao {

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 新增销售人员
	 * @Title: addSalesman 
	 * @Description:
	 * @param s void         
	 * @throws
	 */
	public void addSalesman(Salesman s) {
		String sql = "insert into tbl_salesman (name,phone,qq,telphone,email,gh) values (?, ?, ?, ?, ?,?)";
		Object[] params = new Object[] { s.getName(), s.getPhone(), s.getQq(),s.getTelphone(),s.getEmail(),s.getGh() };
		jdbcTemplate.update(sql, params);
	}
	
	/**
	 * 删除销售人员
	 * @Title: deleteSalesman 
	 * @Description:
	 * @param ids void         
	 * @throws
	 */
	public void deleteSalesman(Object[] ids) {
		StringBuffer sql= new StringBuffer("delete from tbl_salesman where id in ("); 
		for (int i = 0; i < ids.length; i++) {
			if (i != 0)
				sql.append(",");
			sql.append("?");
		}
		sql.append(")");
		jdbcTemplate.update(sql.toString(), ids);
	}
	
	/**
	 * 更新销售人员
	 * @Title: modifySalesman 
	 * @Description:
	 * @param s void         
	 * @throws
	 */
	public void modifySalesman(Salesman s) {
		String sql = "update tbl_salesman set name = ?, phone = ?, qq = ?,telphone = ?,email = ?,gh=? where id = ?";
		Object[] params = new Object[] { s.getName(), s.getPhone(), s.getQq(), s.getTelphone(), s.getEmail(),s.getGh(), s.getId() };
		jdbcTemplate.update(sql, params);
	}
	
	/**
	 * 查询销售人员
	 * @Title: querySalesman 
	 * @Description:
	 * @param id
	 * @return Salesman         
	 * @throws
	 */
	public Salesman querySalesman(int id) {
		String sql = "select * from tbl_salesman where id = ?";
		Object[] params = new Object[] { id };
		List<Salesman> list = jdbcTemplate.query(sql, params,
				new BeanPropertyRowMapper<Salesman>(Salesman.class));
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 查询销售人员数量
	 * @Title: querySalesmanCount 
	 * @Description:
	 * @param keyword
	 * @return int         
	 * @throws
	 */
	public int querySalesmanCount(String keyword) {
		String sql = "select count(id) from tbl_salesman where name like ? or phone like ? or qq like ?";
		keyword = "%" + keyword + "%";
		Object[] params = new Object[] { keyword, keyword, keyword };
		int count = jdbcTemplate.queryForObject(sql, Integer.class, params);
		return count;
	}
	
	/**
	 * 查询销售人员列表
	 * @Title: querySalesmanList 
	 * @Description:
	 * @param keyword
	 * @param pageIndex
	 * @param pageSize
	 * @return List<Salesman>         
	 * @throws
	 */
	public List<Salesman> querySalesmanList(String keyword, int pageIndex, int pageSize) {
		String sql = "select * from tbl_salesman where name like ? or phone like ? or qq like ? order by id desc limit ?,?";
		keyword = "%" + keyword + "%";
		int start = pageIndex * pageSize;
		Object[] params = new Object[] { keyword, keyword, keyword, start, pageSize };
		List<Salesman> list = jdbcTemplate.query(sql, params,
				new BeanPropertyRowMapper<Salesman>(Salesman.class));
		return list;
	}
	
}