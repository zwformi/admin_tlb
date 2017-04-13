package com.yunrer.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.yunrer.entity.ProductType;
import com.yunrer.entity.YunType;
/**
 * 云模块分类DAO
 * @ClassName YunTypeDao
 * @Description
 * @author rujun
 * @date 2016-11-24
 */
@Repository("YunTypeDao")
public class YunTypeDao {
	
	@Resource(name="yunTemplate")
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 新增云模块类别
	 * @Title: addYunType 
	 * @Description:
	 * @param yt
	 * @return int         
	 * @throws
	 */
	public int addYunType(YunType yt) {
		String sql = "insert into tbl_yun_type (name,parent_id,bgcolor,icon,delete_flag) values (?, ?, ?, ?,?)";
		Object[] params = new Object[] { yt.getName(), yt.getParent_id(),yt.getBgcolor(),yt.getIcon(),yt.getDelete_flag()};
		int i = jdbcTemplate.update(sql, params);
		return i;
	}
	
	/**
	 * 删除云模块分类，标记为1(下架)
	 * @Title: deleteYunType 
	 * @Description:
	 * @param ids
	 * @return int         
	 * @throws
	 */
	public int deleteYunType(Object[] ids) {
		StringBuffer sql= new StringBuffer("update tbl_yun_type set delete_flag = 1 where type_id in ("); 
		for (int i = 0; i < ids.length; i++) {
			if (i != 0)
				sql.append(",");
			sql.append("?");
		}
		sql.append(")");
		int i = jdbcTemplate.update(sql.toString(), ids);
		return i;
	}
	
	/**
	 * 更新云模块分类
	 * @Title: updateYunType 
	 * @Description:
	 * @param yt
	 * @return int         
	 * @throws
	 */
	public int updateYunType(YunType yt) {
		String sql = "update tbl_yun_type set name = ?,parent_id =?,bgcolor =?,icon =?,delete_flag=?,sort_code=? where type_id = ?";
		Object[] params = new Object[] { yt.getName(), yt.getParent_id(), yt.getBgcolor(),yt.getIcon(),yt.getDelete_flag(),yt.getSort_code(),yt.getType_id()};
		int i = jdbcTemplate.update(sql, params);
		return i;
	}
	
	/**
	 * 根据type_id查询云模块分类详情
	 * @Title: queryYunType 
	 * @Description:
	 * @param id
	 * @return YunType         
	 * @throws
	 */
	public YunType queryYunType(int id) {
		String sql = "select * from tbl_yun_type where type_id = ?";
		Object[] params = new Object[] { id };
		List<YunType> list = jdbcTemplate.query(sql, params,
				new BeanPropertyRowMapper<YunType>(YunType.class));
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 查询云模块分类数量
	 * @Title: queryYunTypeCount 
	 * @Description:
	 * @param keyword
	 * @return int         
	 * @throws
	 */
	public int queryYunTypeCount(YunType yuntype) {
		String sql = "select count(type_id) from tbl_yun_type where name like ? order by sort_code desc,type_id asc";
		Object[] params = new Object[] { "%"+yuntype.getName()+"%"};
		int count = jdbcTemplate.queryForObject(sql, Integer.class, params);
		return count;
	}
	
	/**
	 * 查询云分类列表
	 * @Title: queryYunTypeList 
	 * @Description:
	 * @param name
	 * @param pageIndex
	 * @param pageSize
	 * @return List<YunType>         
	 * @throws
	 */
	public List<YunType> queryYunTypeList(YunType yunType, int pageIndex, int pageSize) {
		String sql = "select pt.*,(select name from tbl_yun_type pt2 where pt2.type_id = pt.parent_id) as parentName from tbl_yun_type pt where name like ? order by pt.parent_id,pt.sort_code desc limit ?,?";
		int start = pageIndex * pageSize;
		Object[] params = new Object[] { "%"+yunType.getName()+"%", start, pageSize };
		List<YunType> list = jdbcTemplate.query(sql, params,
				new BeanPropertyRowMapper<YunType>(YunType.class));
		return list;
	}
	
	/**
	 * 根据上级ID获取下级分类列表
	 * @Title: queryYunTypeList 
	 * @Description:
	 * @param parentid
	 * @return List<YunType>         
	 * @throws
	 */
	public List<YunType> queryYunTypeList(int parentid) {
		String sql = "select * from tbl_yun_type where parent_id = ? and delete_flag = 0 order by sort_code desc,type_id asc";
		Object[] params = new Object[] { parentid };
		List<YunType> list = jdbcTemplate.query(sql, params,
		new BeanPropertyRowMapper<YunType>(YunType.class));
		return list;
	}
	/**
	 * 根据所有分类信息
	 * @Title: queryYunTypeList 
	 * @Description:
	 * @return List<YunType>         
	 * @throws
	 */
	public List<YunType> queryYunTypeList() {
		String sql = "select * from tbl_yun_type  where delete_flag = 0 order by sort_code desc,type_id asc";
		List<YunType> list = jdbcTemplate.query(sql,
		new BeanPropertyRowMapper<YunType>(YunType.class));
		return list;
	}
}