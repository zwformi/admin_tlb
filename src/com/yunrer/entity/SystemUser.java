package com.yunrer.entity;

import java.util.Date;

import com.yunrer.common.DateUtils;
/**
 * 系统用户 实体类
 * @author wangpeng
 *
 */
public class SystemUser {
	private Integer id; // 用户ID
	private String user_name;// 用户名
	private String user_password;// 密码
	private String user_realname;// 真实姓名
	private Integer user_sex; // 性别 0.女 1.男
	private String user_dept; // 所属部门
	private String user_phone;// 电话
	private String user_email;// 邮箱
	private Integer is_enable;// 是否启用 0：启用 1:不启用
	private Date create_time;// 创建时间
	private String login_ip;// 上次登陆IP
	private Integer login_times;// 登陆次数
	private Date last_logindate;// 上次登陆时间
	private Integer role_id;// 角色ID
	private String role_name;// 角色名称
	private String empno;//员工号
	private String sso_key;//sso密钥
	private String sso_id;//sso_id
	
	private String opt_area;//管理地域
	
	public String getOpt_area() {
		return opt_area;
	}

	public void setOpt_area(String opt_area) {
		this.opt_area = opt_area;
	}

	public String getEmpno() {
		return empno;
	}

	public void setEmpno(String empno) {
		this.empno = empno;
	}

	public String getSso_key() {
		return sso_key;
	}

	public void setSso_key(String sso_key) {
		this.sso_key = sso_key;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String userName) {
		user_name = userName;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String userPassword) {
		user_password = userPassword;
	}

	public String getUser_realname() {
		return user_realname;
	}

	public void setUser_realname(String userRealname) {
		user_realname = userRealname;
	}

	public Integer getUser_sex() {
		return user_sex;
	}

	public void setUser_sex(Integer userSex) {
		user_sex = userSex;
	}

	public String getUser_dept() {
		return user_dept;
	}

	public void setUser_dept(String userDept) {
		user_dept = userDept;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String userPhone) {
		user_phone = userPhone;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String userEmail) {
		user_email = userEmail;
	}

	public Integer getIs_enable() {
		return is_enable;
	}

	public void setIs_enable(Integer isEnable) {
		is_enable = isEnable;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date createTime) {
		create_time = createTime;
	}
	
	public String getFmt_create_time() {
		return DateUtils.date2str(create_time);
	}

	public String getLogin_ip() {
		return login_ip;
	}

	public void setLogin_ip(String loginIp) {
		login_ip = loginIp;
	}

	public Integer getLogin_times() {
		return login_times;
	}

	public void setLogin_times(Integer logIntegerimes) {
		login_times = logIntegerimes;
	}

	public Date getLast_logindate() {
		return last_logindate;
	}

	public void setLast_logindate(Date lastLogindate) {
		last_logindate = lastLogindate;
	}
	
	public Integer getRole_id() {
		return role_id;
	}

	public void setRole_id(Integer roleId) {
		role_id = roleId;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String roleName) {
		role_name = roleName;
	}

	public String getSso_id() {
		return sso_id;
	}

	public void setSso_id(String sso_id) {
		this.sso_id = sso_id;
	}
	
}