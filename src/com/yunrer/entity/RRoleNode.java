package com.yunrer.entity;
/**
 * 角色节点 实体类
 * @author wangpeng
 *
 */
public class RRoleNode {

	private Integer r_id;
	private Integer role_id;//角色ID
	private Integer node_id;//节点ID

	public Integer getR_id() {
		return r_id;
	}

	public void setR_id(Integer rId) {
		r_id = rId;
	}

	public Integer getRole_id() {
		return role_id;
	}

	public void setRole_id(Integer roleId) {
		role_id = roleId;
	}

	public Integer getNode_id() {
		return node_id;
	}

	public void setNode_id(Integer nodeId) {
		node_id = nodeId;
	}
}