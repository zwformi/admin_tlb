package com.yunrer.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.yunrer.entity.MaintenanceStation;

/**
 * 维修站管理DAO
 * @ClassName MaintenanceStationDao
 * @Description 
 * @author rujun
 * @date 2016-12-16
 */
@Repository("MaintenanceStationDao")
public class MaintenanceStationDao {

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 新增维修站
	 * @Title: addMaintenanceStation 
	 * @Description:
	 * @param ms void         
	 * @throws
	 */
	public void addMaintenanceStation(MaintenanceStation ms) {
		String sql = "insert into tbl_maintenance_station (name,address,lxr,phone,sort_id,type_id) values (?, ?, ?, ?, ?, ?)";
		Object[] params = new Object[] { ms.getName(), ms.getAddress(), ms.getLxr(), ms.getPhone(), ms.getSort_id(), ms.getType_id() };
		jdbcTemplate.update(sql, params);
	}
	
	/**
	 * 删除维修站
	 * @Title: deleteMaintenanceStation 
	 * @Description:
	 * @param ids void         
	 * @throws
	 */
	public void deleteMaintenanceStation(Object[] ids) {
		StringBuffer sql= new StringBuffer("delete from tbl_maintenance_station where id in ("); 
		for (int i = 0; i < ids.length; i++) {
			if (i != 0)
				sql.append(",");
			sql.append("?");
		}
		sql.append(")");
		jdbcTemplate.update(sql.toString(), ids);
	}
	
	/**
	 * 更新维修站
	 * @Title: modifyMaintenanceStation 
	 * @Description:
	 * @param ms void         
	 * @throws
	 */
	public void modifyMaintenanceStation(MaintenanceStation ms) {
		String sql = "update tbl_maintenance_station set name = ?, address = ?, lxr = ?, phone = ?, sort_id = ?, type_id = ? where id = ?";
		Object[] params = new Object[] { ms.getName(), ms.getAddress(), ms.getLxr(), ms.getPhone(), ms.getSort_id(), ms.getType_id(), ms.getId() };
		jdbcTemplate.update(sql, params);
	}
	
	/**
	 * 查询维修站
	 * @Title: queryMaintenanceStation 
	 * @Description:
	 * @param id
	 * @return MaintenanceStation         
	 * @throws
	 */
	public MaintenanceStation queryMaintenanceStation(int id) {
		String sql = "select * from tbl_maintenance_station where id = ?";
		Object[] params = new Object[] { id };
		List<MaintenanceStation> list = jdbcTemplate.query(sql, params,
				new BeanPropertyRowMapper<MaintenanceStation>(MaintenanceStation.class));
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 查询维修站数量
	 * @Title: queryMaintenanceStationCount 
	 * @Description:
	 * @param keyword
	 * @return int         
	 * @throws
	 */
	public int queryMaintenanceStationCount(String keyword) {
		String sql = "select count(id) from tbl_maintenance_station where name like ? or address like ? or lxr like ? or phone like ?";
		keyword = "%" + keyword + "%";
		Object[] params = new Object[] { keyword, keyword, keyword, keyword };
		int count = jdbcTemplate.queryForObject(sql, Integer.class, params);
		return count;
	}
	
	/**
	 * 查询维修站列表
	 * @Title: queryMaintenanceStationList 
	 * @Description:
	 * @param keyword
	 * @param pageIndex
	 * @param pageSize
	 * @return List<MaintenanceStation>         
	 * @throws
	 */
	public List<MaintenanceStation> queryMaintenanceStationList(String keyword, int pageIndex, int pageSize) {
		String sql = "select tms.*,td.name as type_name from tbl_maintenance_station tms, tbl_dmb td where tms.type_id = td.id and (tms.name like ? or tms.address like ? or tms.lxr like ? or tms.phone like ?) order by tms.sort_id desc,tms.id desc limit ?,?";
		keyword = "%" + keyword + "%";
		int start = pageIndex * pageSize;
		Object[] params = new Object[] { keyword, keyword, keyword, keyword, start, pageSize };
		List<MaintenanceStation> list = jdbcTemplate.query(sql, params,
				new BeanPropertyRowMapper<MaintenanceStation>(MaintenanceStation.class));
		return list;
	}
	
}