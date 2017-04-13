package com.yunrer.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.yunrer.entity.RRoleNode;
import com.yunrer.entity.SystemNode;

/**
 * 角色节点DAO
 * @ClassName RoleNodeDao
 * @Description 
 * @author rujun
 * @date 2016-12-16
 */
@Repository("RoleNodeDao")
public class RoleNodeDao {

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 新增角色节点
	 * @Title: addRoleNode 
	 * @Description:
	 * @param list void         
	 * @throws
	 */
	public void addRoleNode(List<RRoleNode> list) {
		String[] sqlArr = new String[list.size() + 1];
		for (int i = 0; i < list.size(); i++) {
			RRoleNode rrn = list.get(i);
			String sql = "insert into r_role_node (role_id,node_id) values (" + rrn.getRole_id() + ", " + rrn.getNode_id() + ")";
			sqlArr[i] = sql;
		}
		jdbcTemplate.batchUpdate(sqlArr);
	}
	
	/**
	 * 删除角色相关节点
	 * @Title: deleteRoleNode 
	 * @Description:
	 * @param role_id void         
	 * @throws
	 */
	public void deleteRoleNode(int role_id){
		String sql = "delete from r_role_node where role_id = ?";
		Object[] params = new Object[] { role_id };
		jdbcTemplate.update(sql, params);
	}
	
	/**
	 * 查询角色相关节点列表
	 * @Title: queryRoleNode 
	 * @Description:
	 * @param role_id
	 * @return List<SystemNode>         
	 * @throws
	 */
	public List<SystemNode> queryRoleNode(int role_id) {
		String sql = "select n.node_id as id, n.node_name as name, n.parent_id as pId, (case when r.role_id = ? then true else false end) as checked from tbl_system_node n left join r_role_node r on r.node_id = n.node_id and r.role_id = ?";
		Object[] params = new Object[] { role_id, role_id };
		List<SystemNode> list = jdbcTemplate.query(sql, params,
				new BeanPropertyRowMapper<SystemNode>(SystemNode.class));
		return list;
	}
	
	/**
	 * 查询角色相关权限列表
	 * @Title: queryRoleAuth 
	 * @Description:
	 * @param role_id
	 * @return List<SystemNode>         
	 * @throws
	 */
	public List<SystemNode> queryRoleAuth(int role_id) {
		String sql = "select n.key_name from tbl_system_node n, r_role_node r where n.node_id = r.node_id and r.role_id = ?";
		Object[] params = new Object[] { role_id };
		List<SystemNode> list = jdbcTemplate.query(sql, params,
				new BeanPropertyRowMapper<SystemNode>(SystemNode.class));
		return list;
	}
}