package com.yunrer.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.yunrer.entity.Ad;

/**
 * 广告管理DAO
 * @ClassName AdDao
 * @Description 
 * @author rujun
 * @date 2016-12-16
 */
@Repository("AdDao")
public class AdDao {

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 新增广告图片
	 * @Title: addAd 
	 * @Description:
	 * @param a void         
	 * @throws
	 */
	public void addAd(Ad a) {
		String sql = "insert into tbl_ad (typeid,name,img_url,url,sortid) values (?, ?, ?, ?, ?)";
		Object[] params = new Object[] { a.getTypeid(), a.getName(), a.getImg_url(),a.getUrl(), a.getSortid() };
		jdbcTemplate.update(sql, params);
	}
	
	/**
	 * 删除广告图片
	 * @Title: deleteAd 
	 * @Description:
	 * @param ids void         
	 * @throws
	 */
	public void deleteAd(Object[] ids) {
		StringBuffer sql= new StringBuffer("delete from tbl_ad where id in ("); 
		for (int i = 0; i < ids.length; i++) {
			if (i != 0)
				sql.append(",");
			sql.append("?");
		}
		sql.append(")");
		jdbcTemplate.update(sql.toString(), ids);
	}
	
	/**
	 * 更新广告图片
	 * @Title: modifyAd 
	 * @Description:
	 * @param a void         
	 * @throws
	 */
	public void modifyAd(Ad a) {
		String sql = "update tbl_ad set typeid = ?, name = ?, img_url = ?,url=?, sortid = ? where id = ?";
		Object[] params = new Object[] { a.getTypeid(), a.getName(), a.getImg_url(), a.getUrl(),a.getSortid(), a.getId() };
		jdbcTemplate.update(sql, params);
	}
	
	/**
	 * 查询广告图片
	 * @Title: queryAd 
	 * @Description:
	 * @param id
	 * @return Ad         
	 * @throws
	 */
	public Ad queryAd(int id) {
		String sql = "select * from tbl_ad where id = ?";
		Object[] params = new Object[] { id };
		List<Ad> list = jdbcTemplate.query(sql, params,
				new BeanPropertyRowMapper<Ad>(Ad.class));
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 查询广告图片数量
	 * @Title: queryAdCount 
	 * @Description:
	 * @param keyword
	 * @return int         
	 * @throws
	 */
	public int queryAdCount(String keyword) {
		String sql = "select count(id) from tbl_ad where name like ?";
		keyword = "%" + keyword + "%";
		Object[] params = new Object[] { keyword };
		int count = jdbcTemplate.queryForObject(sql, Integer.class, params);
		return count;
	}
	
	/**
	 * 查询广告图片列表
	 * @Title: queryAdList 
	 * @Description:
	 * @param keyword
	 * @param pageIndex
	 * @param pageSize
	 * @return List<Ad>         
	 * @throws
	 */
	public List<Ad> queryAdList(String keyword, int pageIndex, int pageSize) {
		String sql = "select * from tbl_ad where name like ? order by sortid desc,typeid desc limit ?,?";
		keyword = "%" + keyword + "%";
		int start = pageIndex * pageSize;
		Object[] params = new Object[] { keyword, start, pageSize };
		List<Ad> list = jdbcTemplate.query(sql, params,
				new BeanPropertyRowMapper<Ad>(Ad.class));
		return list;
	}
	
}