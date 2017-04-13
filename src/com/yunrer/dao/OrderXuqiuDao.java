package com.yunrer.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.yunrer.entity.OrderDetailsXuqiu;
import com.yunrer.entity.OrderInfoXuqiu;
import com.yunrer.entity.SystemUser;

/**
 * 需求DAO
 * @ClassName OrderXuqiuDao
 * @Description 
 * @author rujun
 * @date 2016-12-16
 */
@Repository("OrderXuqiuDao")
public class OrderXuqiuDao {
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 查询需求数量
	 * @Title: queryOrderXuqiucount 
	 * @Description:
	 * @param order_number
	 * @param order_source
	 * @param order_state
	 * @param ywyxm
	 * @param gsmc
	 * @param user
	 * @return int         
	 * @throws
	 */
	public int queryOrderXuqiucount(String order_number,int order_source,int order_state,String ywyxm,String gsmc,SystemUser user) {
		List<Object> paramlist = new ArrayList<Object>();
		String sql = "select count(1) from tbl_order_info_xuqiu  a left join tbl_user_info b on a.user_id = b.user_id LEFT JOIN tbl_user_company d on d.id=a.owning_company left join tbl_salesman e on e.id = b.sales_id where 1=1  ";
		if(null!=gsmc&&!"".equals(gsmc)){
			sql += "and d.gsmc  like ? ";
			paramlist.add("%"+gsmc+"%");
		}
		if(null!=ywyxm&&!"".equals(ywyxm)){
			sql += "and b.xm  like ? ";
			paramlist.add("%"+ywyxm+"%");
		}
		if(null!=order_number&&!"".equals(order_number)){
			sql += " and order_number like ? ";
			paramlist.add(order_number+"%");
		}
		if(order_source!=-1){
			sql += " and order_source = ? ";
			paramlist.add(order_source);
		}
		if(order_state!=0){
			sql += " and order_state = ? ";
			paramlist.add(order_state);
		}//权限控制
		if("000000".equals(user.getEmpno())){ //管理员
			
		}else{
			sql +=" AND e.opt_area =? ";
			paramlist.add(user.getOpt_area());
		}
		
		Object[] params = paramlist.toArray();
		int total_count = jdbcTemplate.queryForObject(sql, Integer.class,params);
		return total_count;
	}
	
	/**
	 * 查询需求列表
	 * @Title: queryOrderXuqiuList 
	 * @Description:
	 * @param order_number
	 * @param order_source
	 * @param order_state
	 * @param ywyxm
	 * @param gsmc
	 * @param user
	 * @param pageIndex
	 * @param pageSize
	 * @return List         
	 * @throws
	 */
	public List queryOrderXuqiuList(String order_number,int order_source,int order_state,String ywyxm,String gsmc,SystemUser user,int pageIndex,int pageSize) {
		List<Object> paramlist = new ArrayList<Object>();
		String sql = "select a.*,b.xm as ywyxm,d.gsmc from tbl_order_info_xuqiu a left join tbl_user_info b on a.user_id = b.user_id LEFT JOIN tbl_user_company d on d.id=a.owning_company left join tbl_salesman e on e.id = b.sales_id where 1=1 ";
		int start = pageIndex * pageSize;
		if(null!=gsmc&&!"".equals(gsmc)){
			sql += "and d.gsmc  like ? ";
			paramlist.add("%"+gsmc+"%");
		}
		if(null!=ywyxm&&!"".equals(ywyxm)){
			sql += "and b.xm  like ? ";
			paramlist.add("%"+ywyxm+"%");
		}
		if(null!=order_number&&!"".equals(order_number)){
			sql += " and a.order_number like ? ";
			paramlist.add(order_number+"%");
		}
		if(order_source!=-1){
			sql += " and a.order_source = ? ";
			paramlist.add(order_source);
		}
		if(order_state!=0){
			sql += " and a.order_state = ? ";
			paramlist.add(order_state);
		}//权限控制
		if("000000".equals(user.getEmpno())){ //管理员
			
		}else{
			sql +=" AND e.opt_area =? ";
			paramlist.add(user.getOpt_area());
		}
		paramlist.add(start);
		paramlist.add(pageSize);
		Object[] params = paramlist.toArray();
		sql += " order by a.order_time desc limit ?,?";
		List list = jdbcTemplate.query(sql,params,
		new BeanPropertyRowMapper<OrderInfoXuqiu>(OrderInfoXuqiu.class));

		return list;
	}
	
	
	/**
	 * 通过id查询需求单详情
	 * @Title: queryOrder_Xuqiu_Detail 
	 * @Description:
	 * @param id
	 * @return OrderInfoXuqiu         
	 * @throws
	 */
	public OrderInfoXuqiu queryOrder_Xuqiu_Detail(int id) {
		String sql = "select * from tbl_order_info_xuqiu where id = ?";
		Object[] params = new Object[] { id};
		List<OrderInfoXuqiu> list = jdbcTemplate.query(sql, params,
		new BeanPropertyRowMapper<OrderInfoXuqiu>(OrderInfoXuqiu.class));
		if(null!=list&&!list.isEmpty()){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	/**
	 * 通过订单号查询需求单详情
	 * @Title: queryOrder_Xuqiu_Detail 
	 * @Description:
	 * @param order_number
	 * @return OrderInfoXuqiu         
	 * @throws
	 */
	public OrderInfoXuqiu queryOrder_Xuqiu_Detail(String order_number) {
		String sql = "select * from tbl_order_info_xuqiu where order_number = ?";
		Object[] params = new Object[] { order_number};
		List<OrderInfoXuqiu> list = jdbcTemplate.query(sql, params,
		new BeanPropertyRowMapper<OrderInfoXuqiu>(OrderInfoXuqiu.class));
		if(null!=list&&!list.isEmpty()){
			return list.get(0);
		}else{
			return null;
		}
	}
	

	/**
	 * 查询需求单详情列表
	 * @Title: queryOrderDetail_Xuqiu 
	 * @Description:
	 * @param order_number
	 * @return List<OrderDetailsXuqiu>         
	 * @throws
	 */
	public List<OrderDetailsXuqiu> queryOrderDetail_Xuqiu(String order_number) {
		String sql = "select * from tbl_order_details_xuqiu where order_number = ? order by id asc";
		Object[] params = new Object[] { order_number};
		List<OrderDetailsXuqiu> list = jdbcTemplate.query(sql, params,
		new BeanPropertyRowMapper<OrderDetailsXuqiu>(OrderDetailsXuqiu.class));
		return list;
	}
	
	/**
	 * 新增数据--需求单商品明细表
	 * @Title: addOrderDetail_Xuqiu 
	 * @Description:
	 * @param od 商品明细
	 * @return int   主键ID       
	 * @throws
	 */
	public int addOrderDetail_Xuqiu(final OrderDetailsXuqiu od) {
		int id =0;
		try {
			final String sql = "insert into tbl_order_details_xuqiu (order_number,type_str,brand_str,xh,pz,product_name,product_count,create_time) values (?, ?, ?, ?, ?, ?, ?, ?)";

			KeyHolder keyHolder = new GeneratedKeyHolder();   
	        int result = jdbcTemplate.update(new PreparedStatementCreator(){   
	            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {   
	                PreparedStatement ps =  conn.prepareStatement(sql, new String[]{"id"});//返回主键id   
					ps.setString(1, od.getOrder_number()==null?"":od.getOrder_number());
					
					ps.setString(2, od.getType_str()==null?"":od.getType_str());
					ps.setString(3, od.getBrand_str()==null?"":od.getBrand_str());
					ps.setString(4, od.getXh()==null?"":od.getXh());
					ps.setString(5, od.getPz()==null?"":od.getPz());
					
					ps.setString(6, od.getProduct_name()==null?"":od.getProduct_name());
					ps.setInt(7, od.getProduct_count());
					ps.setDate(8, new Date(od.getCreate_time().getTime()));
	                return ps;   
	            }
	        }, keyHolder);   
			if (result > 0) {
				id = keyHolder.getKey().intValue();
			}
		} catch (Exception ex) {
			System.err.println("---------------------------------------------");
			System.err.println(ex.getMessage());
			System.err.println("---------------------------------------------");
		}
		return id;
	}
	
	/**
	 * 删除需求详情
	 * @Title: delOrderXuqiu 
	 * @Description:
	 * @param order_number
	 * @param id
	 * @return int         
	 * @throws
	 */
	public int delOrderXuqiu(String order_number,int id) {
		int count =0;
		try {
			Object[] params = new Object[] {id,order_number};
			StringBuffer sql= new StringBuffer("delete from tbl_order_details_xuqiu where id = ? and order_number = ?"); 
			count = jdbcTemplate.update(sql.toString(),params);
		} catch (Exception ex) {
			System.err.println("---------------------------------------------");
			System.err.println(ex.getMessage());
			System.err.println("---------------------------------------------");
		}
		return count;
	}
	
	/**
	 * 报价更新价格与状态
	 * @Title: updateOrderXuqiu 
	 * @Description:
	 * @param order_number
	 * @param total
	 * @return int         
	 * @throws
	 */
	public int updateOrderXuqiu(String order_number,double total) {
		int count =0;
		try {
			Object[] params = new Object[] {total,order_number};
			StringBuffer sql= new StringBuffer("update tbl_order_info_xuqiu set order_state = 5,payment_money = ?,bj_time = now() where order_number = ?"); 
			count = jdbcTemplate.update(sql.toString(),params);
		} catch (Exception ex) {
			System.err.println("---------------------------------------------");
			System.err.println(ex.getMessage());
			System.err.println("---------------------------------------------");
			throw new RuntimeException("修改报价状态出现错误-已报价状态！");
		}
		return count;
	}
	
	/**
	 * 生成合同状态修改
	 * @Title: updateOrderXuqiu_ht 
	 * @Description:
	 * @param order_number
	 * @return int         
	 * @throws
	 */
	public int updateOrderXuqiu_ht(String order_number) {
		int count =0;
		try {
			Object[] params = new Object[] {order_number};
			StringBuffer sql= new StringBuffer("update tbl_order_info_xuqiu set order_state = 8,htsc_time = now() where order_number = ?"); 
			count = jdbcTemplate.update(sql.toString(),params);
		} catch (Exception ex) {
			System.err.println("---------------------------------------------");
			System.err.println(ex.getMessage());
			System.err.println("---------------------------------------------");
			throw new RuntimeException("修改需求状态出现错误-生成合同状态！");
		}
		return count;
	}
	
	
	/**
	 * 关闭需求单
	 * @Title: cancelOrderXuqiu 
	 * @Description:
	 * @param id
	 * @return int         
	 * @throws
	 */
	public int cancelOrderXuqiu(int id) {
		int count =0;
		try {
			Object[] params = new Object[] {id};
			StringBuffer sql= new StringBuffer("update tbl_order_info_xuqiu set order_state = 6 where id = ?"); 
			count = jdbcTemplate.update(sql.toString(),params);
		} catch (Exception ex) {
			System.err.println("---------------------------------------------");
			System.err.println(ex.getMessage());
			System.err.println("---------------------------------------------");
		}
		return count;
	}
	
	
	/**
	 * 修改数据--需求报价
	 * @Title: orderXuqiu_Bj 
	 * @Description:
	 * @param list void         
	 * @throws
	 */
	public void orderXuqiu_Bj(final List<OrderDetailsXuqiu> list){
		String sql = "update tbl_order_details_xuqiu set product_unit_price_bj = ? where order_number = ? and id = ?";
		try {
			BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter() {
				public void setValues(PreparedStatement ps, int index)
						throws SQLException {
					OrderDetailsXuqiu od = list.get(index);
					ps.setDouble(1, od.getProduct_unit_price_bj());
					ps.setString(2, od.getOrder_number());
					ps.setInt(3, od.getId());
				}
				public int getBatchSize() {
					return list.size();
				}
			};
			jdbcTemplate.batchUpdate(sql, setter);
		} catch (Exception ex) {
			System.err.println("---------------------------------------------");
			System.err.println(ex.getMessage());
			System.err.println("---------------------------------------------");
			throw new RuntimeException("需求报价出现错误！");
		}
	}
	
	/**
	 * 删除需求单主表
	 * @Title: deleteOrderXuqiu 
	 * @Description:
	 * @param id
	 * @return int         
	 * @throws
	 */
	public int deleteOrderXuqiu(int id) {
		int count =0;
		try {
			StringBuffer sql= new StringBuffer("delete from tbl_order_info_xuqiu where id = ?"); 
			Object[] params = new Object[] { id};
			count = jdbcTemplate.update(sql.toString(),params);
		} catch (Exception ex) {
			System.err.println("---------------------------------------------");
			System.err.println(ex.getMessage());
			System.err.println("---------------------------------------------");
			throw new RuntimeException("删除需求单主表出现错误！");
		}
		return count;
	}
	
	/**
	 * 删除需求单详情清单
	 * @Title: deleteOrderXuqiuDetail 
	 * @Description:
	 * @param order_number
	 * @return int         
	 * @throws
	 */
	public int deleteOrderXuqiuDetail(String  order_number) {
		int count =0;
		try {
			StringBuffer sql= new StringBuffer("delete from tbl_order_details_xuqiu where order_number = ?"); 
			Object[] params = new Object[] { order_number};
			count = jdbcTemplate.update(sql.toString(),params);
		} catch (Exception ex) {
			System.err.println("---------------------------------------------");
			System.err.println(ex.getMessage());
			System.err.println("---------------------------------------------");
			throw new RuntimeException("删除需求单中商品清单表出现错误！");
		}
		return count;
	}

	/**
	 * 修改需求
	 * @Title: changeOrderXuqiu 
	 * @Description:
	 * @param key
	 * @param value
	 * @param order_number
	 * @return int         
	 * @throws
	 */
	public int changeOrderXuqiu(String key,String value,String order_number) {
		int count =0;
		try {
			StringBuffer sql= new StringBuffer("update tbl_order_info_xuqiu set "+key+"=? where order_number = ?"); 
			Object[] params = new Object[] {value,order_number};
			count = jdbcTemplate.update(sql.toString(),params);
		} catch (Exception ex) {
			System.err.println("---------------------------------------------");
			System.err.println(ex.getMessage());
			System.err.println("---------------------------------------------");
			throw new RuntimeException("修改订单"+key+"字段出现错误！");
		}
		return count;
	}


}