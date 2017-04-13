package com.yunrer.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.yunrer.entity.ProductItem;
import com.yunrer.entity.UserService;
/**
 * 服务单DAO
 * @ClassName UserServiceDao
 * @Description 
 * @author rujun
 * @date 2016-12-19
 */
@Repository("UserServiceDao")
public class UserServiceDao {
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 查询服务单数量
	 * @param keyword 
	 * @param state
	 * @return
	 */
	public int queryServicecount(String keyword,int state) {
		List<Object> paramlist = new ArrayList<Object>();
		String sql = "select count(1) from tbl_service where 1=1 ";
		if(null!=keyword&&!"".equals(keyword)){
			sql += " and (lxr like ? or phone like ? or address like ? or comment like ?)";
			paramlist.add("%"+keyword+"%");
			paramlist.add("%"+keyword+"%");
			paramlist.add("%"+keyword+"%");
			paramlist.add("%"+keyword+"%");
		}
		if(state!=-1){
			sql += " and state = ? ";
			paramlist.add(state);
		}
		
		Object[] params = paramlist.toArray();
		int total_count = jdbcTemplate.queryForObject(sql, Integer.class,params);
		return total_count;
	}
	
	/**
	 * 查询服务单列表
	 * @param begin_index 
	 * @param page_size
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<UserService> queryServiceList(String keyword,int state,int pageIndex,int pageSize) {
		List<Object> paramlist = new ArrayList<Object>();
		String sql = "select * from tbl_service where 1=1 ";
		int start = pageIndex * pageSize;
		if(null!=keyword&&!"".equals(keyword)){
			sql += " and (lxr like ? or phone like ? or address like ? or comment like ?)";
			paramlist.add("%"+keyword+"%");
			paramlist.add("%"+keyword+"%");
			paramlist.add("%"+keyword+"%");
			paramlist.add("%"+keyword+"%");
		}
		if(state!=-1){
			sql += " and state = ? ";
			paramlist.add(state);
		}
		paramlist.add(start);
		paramlist.add(pageSize);
		Object[] params = paramlist.toArray();
		sql += " order by add_time desc limit ?,?";
		List<UserService> list = jdbcTemplate.query(sql,params,
				new RowMapper() {
			public Object mapRow(ResultSet rs, int paramInt)
					throws SQLException {
				UserService service = new UserService();
				service.setAdd_time(rs.getDate("add_time"));
				service.setAddress(rs.getString("address"));
				service.setComment(rs.getString("comment"));
				service.setContent(rs.getString("content"));
				service.setLxr(rs.getString("lxr"));
				if(rs.getObject("order_detail_id")!=null)
				service.setOrder_detail_id(rs.getInt("order_detail_id"));
				service.setOrder_number(rs.getString("order_number"));
				service.setPhone(rs.getString("phone"));
				service.setService_number(rs.getString("service_number"));
				if(rs.getObject("state")!=null)
				service.setState(rs.getInt("state"));
				if(rs.getObject("user_id")!=null)
				service.setUser_id(rs.getInt("user_id"));
				return service;
				
			}
		});
		return list;
	}
	
	
	/**
	 * 查询服务单详情
	 * @Title: queryService 
	 * @Description:
	 * @param service_number
	 * @return UserService         
	 * @throws
	 */
	public UserService queryService(String service_number) {
		String sql = "select * from tbl_service where service_number = ?";
		Object[] params = new Object[] { service_number};
		List<UserService> list = jdbcTemplate.query(sql, params,
		new BeanPropertyRowMapper<UserService>(UserService.class));
		if(null!=list&&!list.isEmpty()){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	/**
	 * 删除服务单
	 * @Title: deleteService 
	 * @Description:
	 * @param service_number
	 * @return int         
	 * @throws
	 */
	public int deleteService(String service_number) {
		int count =0;
		try {
			StringBuffer sql= new StringBuffer("delete from tbl_service where service_number = ?"); 
			Object[] params = new Object[] { service_number};
			count = jdbcTemplate.update(sql.toString(),params);
		} catch (Exception ex) {
			System.err.println("---------------------------------------------");
			System.err.println(ex.getMessage());
			System.err.println("---------------------------------------------");
		}
		return count;
	}
	
	/**
	 * 修改服务单的服务状态---0：等待服务   1：已服务
	 * @Title: updateState 
	 * @Description:
	 * @param state
	 * @param service_number
	 * @return int         
	 * @throws
	 */
	public int updateState(int state,String  service_number) {
		int count =0;
		try {
			StringBuffer sql= new StringBuffer("update tbl_service set state = ? where service_number = ?"); 
			Object[] params = new Object[] { state,service_number};
			count = jdbcTemplate.update(sql.toString(),params);
		} catch (Exception ex) {
			System.err.println("---------------------------------------------");
			System.err.println(ex.getMessage());
			System.err.println("---------------------------------------------");
		}
		return count;
	}
	
	/**
	 * 修改服务单的备注
	 * @Title: updateComment 
	 * @Description:
	 * @param comment
	 * @param service_number
	 * @return int         
	 * @throws
	 */
	public int updateComment(String comment,String  service_number) {
		int count =0;
		try {
			StringBuffer sql= new StringBuffer("update tbl_service set comment = ? where service_number = ?"); 
			Object[] params = new Object[] { comment,service_number};
			count = jdbcTemplate.update(sql.toString(),params);
		} catch (Exception ex) {
			System.err.println("---------------------------------------------");
			System.err.println(ex.getMessage());
			System.err.println("---------------------------------------------");
		}
		return count;
	}
}