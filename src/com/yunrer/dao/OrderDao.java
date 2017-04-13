package com.yunrer.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.yunrer.entity.OrderDetails;
import com.yunrer.entity.OrderEvaluate;
import com.yunrer.entity.OrderFile;
import com.yunrer.entity.OrderInfo;
import com.yunrer.entity.OrderSm;
import com.yunrer.entity.SystemUser;
import com.yunrer.entity.YunDetails;

/**
 * 合同DAO
 * @ClassName OrderDao
 * @Description 
 * @author rujun
 * @date 2016-12-16
 */
@Repository("OrderDao")
public class OrderDao {

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Resource(name="yunTemplate")
	private JdbcTemplate jdbcYunTemplate;
	
	/**
	 * 查询合同数量
	 * @param order_number
	 * @param order_source
	 * @param order_state
	 * @param ywyxm
	 * @param gsmc
	 * @param gh
	 * @return total_count
	 */
	public int queryOrdercount(String order_number,int order_source,int order_state,String ywyxm,String gsmc,SystemUser user) {
		List<Object> paramlist = new ArrayList<Object>();
		String sql = "select count(1) from tbl_order_info a left join tbl_user_info b on a.user_id =b.user_id left join tbl_user_client c on b.client_id = c.id LEFT JOIN tbl_user_company d on d.id=a.owning_company left join tbl_salesman e on e.id = b.sales_id where 1=1  ";
		if(null!=order_number&&!"".equals(order_number)){
			sql += " and order_number like ? ";
			paramlist.add("%"+order_number);
		}
		if(null!=ywyxm&&!"".equals(ywyxm)){
			sql += "and e.name like ? ";
			paramlist.add("%"+ywyxm+"%");
		}
		if(null!=gsmc&&!"".equals(gsmc)){
			sql += "and d.gsmc like ?";
			paramlist.add("%"+gsmc+"%");
		}
		if(order_source!=-1){
			sql += " and a.order_source = ? ";
			paramlist.add(order_source);
		}
		if(order_state!=-1){
			sql += " and a.order_state = ? ";
			paramlist.add(order_state);
		}
		//权限控制
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
	 * 查询合同列表（分页）
	 * @param order_number
	 * @param order_source
	 * @param order_state
	 * @param ywyxm
	 * @param gsmc
	 * @param gh
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public List<OrderInfo> queryOrderList(String order_number,int order_source,int order_state,String ywyxm,String gsmc,SystemUser user,int pageIndex,int pageSize) {
		List<Object> paramlist = new ArrayList<Object>();
		String sql = "select a.*,e.name ywyxm,d.gsmc,CONCAT(c.name,'[',c.gh,']') kfxx from tbl_order_info a left join tbl_user_info b on a.user_id =b.user_id left join tbl_user_client c on b.client_id = c.id LEFT JOIN tbl_user_company d on d.id=a.owning_company left join tbl_salesman e on e.id = b.sales_id where 1=1 ";
		int start = pageIndex * pageSize;
		if(null!=order_number&&!"".equals(order_number)){
			sql += " and a.order_number like ? ";
			paramlist.add("%"+order_number+"%");
		}
		if(null!=ywyxm&&!"".equals(ywyxm)){
			sql += "and e.name like ? ";
			paramlist.add("%"+ywyxm+"%");
		}
		if(null!=gsmc&&!"".equals(gsmc)){
			sql += "and d.gsmc like ?";
			paramlist.add("%"+gsmc+"%");
		}
		if(order_source!=-1){
			sql += " and a.order_source = ? ";
			paramlist.add(order_source);
		}
		if(order_state!=-1){
			sql += " and a.order_state = ? ";
			paramlist.add(order_state);
		}
		//权限控制
		if("000000".equals(user.getEmpno())){ //管理员
			
		}else{
			sql +=" AND e.opt_area =? ";
			paramlist.add(user.getOpt_area());
		}
		paramlist.add(start);
		paramlist.add(pageSize);
		Object[] params = paramlist.toArray();
		sql += " order by a.order_time desc limit ?,?";
		List<OrderInfo> list = jdbcTemplate.query(sql,params,
		new BeanPropertyRowMapper<OrderInfo>(OrderInfo.class));
		System.out.println(list);
		return list;
	}
	

	/**
	 * 查询合同列表<Map>（灵活导出使用）
	 * @param order_number
	 * @param order_source
	 * @param order_state
	 * @param ywyxm
	 * @param gsmc
	 * @param gh
	 * @return list 
	 */
	public List<Map<String,Object>> queryOrderList(String order_number,int order_source,int order_state,String ywyxm,String gsmc,SystemUser user) {
		List<Object> paramlist = new ArrayList<Object>();
		String sql = "select a.*,(select Name from sys_stringmap where value=a.order_type and FiledName='order_type' and ObjectTypeName = 'order_info') order_typeTrans,(select Name from sys_stringmap where value=a.rec_paycondition and FiledName='rec_paycondition' and ObjectTypeName = 'order_info') rec_payconditionTrans,e.name ywyxm,d.gsmc,CONCAT(c.name,'[',c.gh,']') kfxx from tbl_order_info a left join tbl_user_info b on a.user_id =b.user_id left join tbl_user_client c on b.client_id = c.id LEFT JOIN tbl_user_company d on d.id=b.owning_company left join tbl_salesman e on e.id = b.sales_id where 1=1 ";
		if(null!=order_number&&!"".equals(order_number)){
			sql += " and a.order_number like ? ";
			paramlist.add("%"+order_number+"%");
		}
		if(null!=ywyxm&&!"".equals(ywyxm)){
			sql += "and e.name like ? ";
			paramlist.add("%"+ywyxm+"%");
		}
		if(null!=gsmc&&!"".equals(gsmc)){
			sql += "and d.gsmc like ?";
			paramlist.add("%"+gsmc+"%");
		}
		if(order_source!=-1){
			sql += " and a.order_source = ? ";
			paramlist.add(order_source);
		}
		if(order_state!=-1){
			sql += " and a.order_state = ? ";
			paramlist.add(order_state);
		}
		//权限控制
				if("000000".equals(user.getEmpno())){ //管理员
					
				}else{
					sql +=" AND e.opt_area =? ";
					paramlist.add(user.getOpt_area());
				}
		sql += " order by a.order_time";
		Object[] params = paramlist.toArray();
		List<Map<String,Object>> list = jdbcTemplate.queryForList(sql,params);
		return list;
	}
	
	
	/**
	 * 新增数据--订单合同表
	 * @Title: addOrder 
	 * @Description:
	 * @param o void         
	 * @throws
	 */
	public void addOrder(OrderInfo o){
		try{
			String sql = "insert into tbl_order_info (order_number,user_id,order_state,order_time,order_source,payment_money,ip,demand_file,shr_dz,shr_xm,shr_dh,shr_yb,fkfs,xuqiu_ordernumber,shipping_methods,installation_service,erp_number,rec_paycondition,gsmc_final,order_type,reception_date,signing_date,remark,owning_company) values (?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			Object[] params = new Object[] { o.getOrder_number(), o.getUser_id(), o.getOrder_state(), o.getOrder_time(), o.getOrder_source(), o.getPayment_money(), o.getIp(),o.getDemand_file(),o.getShr_dz(),o.getShr_xm(),o.getShr_dh(),o.getShr_yb(),o.getFkfs(),o.getXuqiu_ordernumber(),o.getShipping_methods(),o.getInstallation_service(),o.getErp_number(),o.getRec_paycondition(),o.getGsmc_final(),o.getOrder_type(),o.getReception_date(),o.getSigning_date(),o.getRemark(),o.getOwning_company() };
			jdbcTemplate.update(sql, params);
		}catch (Exception ex) {
			System.err.println("---------------------------------------------");
			System.err.println(ex.getMessage());
			System.err.println("---------------------------------------------");
			throw new RuntimeException("新增订单合同主表出现错误！");
		}
	}
		

	/**
	 * 更新订单
	 * @Title: updateOrder 
	 * @Description:
	 * @param od
	 * @return int         
	 * @throws
	 */
	public int updateOrder(OrderInfo od){
		int count =0;
		String sql = "update tbl_order_info set yskzt=?,erp_number=?,shr_xm=?,shr_dh=?,shr_yb=?,shr_dz=?,fkfs=?,signing_date=?,gsmc_final=?,order_type=?,reception_date=?,rec_paycondition=?,remark=?,payment_money=?,edit_time=now() where id=? ";
		try {
			Object[] params = new Object[] {od.getYskzt(),od.getErp_number(),od.getShr_xm(),od.getShr_dh(),od.getShr_yb(),od.getShr_dz(),od.getFkfs(),od.getSigning_date(),od.getGsmc_final(),od.getOrder_type(),od.getReception_date(),od.getRec_paycondition(),od.getRemark(),od.getPayment_money(),od.getId()};
			return count = jdbcTemplate.update(sql.toString(),params);
		} catch (Exception ex) {
			System.err.println("---------------------------------------------");
			System.err.println(ex.getMessage());
			System.err.println("---------------------------------------------");
			throw new RuntimeException("新增订单合同明细错误！");
		}
	}
	
	
	/**
	 * 新增数据--订单合同商品明细表
	 * @Title: addOrderDetail 
	 * @Description:
	 * @param list void         
	 * @throws
	 */
	public void addOrderDetail(final List<OrderDetails> list){
		String sql = "insert into tbl_order_details (order_number,type_str,brand_str,xh,pz,product_id,product_name,product_count,product_unit_price,product_unit_price_bj,create_time,product_imgurl) select ?, ?, ?, ? ,?, ?, ?, ?, ?, ?, ?,max(img_url) from tbl_product where id=?";
		try {
			BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter() {
				public void setValues(PreparedStatement ps, int index)
						throws SQLException {
					OrderDetails od = list.get(index);
					ps.setString(1, od.getOrder_number());
					
					ps.setString(2, od.getType_str());
					ps.setString(3, od.getBrand_str());
					ps.setString(4, od.getXh());
					ps.setString(5, od.getPz());
					
					ps.setInt(6, od.getProduct_id());
					ps.setString(7, od.getProduct_name());
					ps.setInt(8, od.getProduct_count());
					ps.setDouble(9, od.getProduct_unit_price());
					ps.setDouble(10, od.getProduct_unit_price_bj());
					ps.setDate(11, new Date(od.getCreate_time().getTime()));
					ps.setInt(12, od.getProduct_id());
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
			throw new RuntimeException("新增订单合同明细错误！");
		}
	}


	/**
	 * 查询订单合同详情
	 * @Title: queryOrder_Detail 
	 * @Description:
	 * @param id
	 * @return OrderInfo         
	 * @throws
	 */
	public OrderInfo queryOrder_Detail(int id) {
		String sql = "select * from tbl_order_info where id = ?";
		Object[] params = new Object[] { id};
		List<OrderInfo> list = jdbcTemplate.query(sql, params,
		new BeanPropertyRowMapper<OrderInfo>(OrderInfo.class));
		if(null!=list&&!list.isEmpty()){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	/**
	 * 开通云Detial
	 * @Title: updateYun_Detail 
	 * @Description:
	 * @param yundetails
	 * @return int         
	 * @throws
	 */
	public int updateYun_Detail(YunDetails yundetails) {
		int count = 0;
		String sql = "update tbl_yun_details set modify_on = NOW() ";
		List<Object> paramlist = new ArrayList<Object>();
		if(yundetails.getIs_open()!=null&&yundetails.getIs_open()!=0){
			sql += ",is_open = 1 ,open_on = NOW()";	
		}
		if(yundetails.getUrl()!=null&&yundetails.getUrl()!=""){
			sql += ",url = ?";
			paramlist.add(yundetails.getUrl());	
		}
		if(yundetails.getDelete_flag()!=null){
			if(yundetails.getDelete_flag()==0){
				sql += ",delete_flag = ?";
				paramlist.add(1);	
			}else if(yundetails.getDelete_flag()==1){
				sql += ",delete_flag = ?";
				paramlist.add(0);	
			}
			
		}
		sql += " where id=? ";
		paramlist.add(yundetails.getId());
		Object[] params = paramlist.toArray();
		count = jdbcYunTemplate.update(sql, params);
		return count;
	}
	

	/**
	 * 查询订单合同详情
	 * @Title: queryOrder_Detail 
	 * @Description:
	 * @param order_number
	 * @return OrderInfo         
	 * @throws
	 */
	public OrderInfo queryOrder_Detail(String order_number) {
		String sql = "select * from tbl_order_info where order_number = ?";
		Object[] params = new Object[] { order_number};
		List<OrderInfo> list = jdbcTemplate.query(sql, params,
		new BeanPropertyRowMapper<OrderInfo>(OrderInfo.class));
		if(null!=list&&!list.isEmpty()){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	
	
	

	/**
	 * 查询订单合同中的商品详情
	 * @Title: queryOrderDetail 
	 * @Description:
	 * @param id
	 * @return OrderDetails         
	 * @throws
	 */
	public OrderDetails queryOrderDetail(int id) {
		String sql = "select * from tbl_order_details where id = ?";
		Object[] params = new Object[] { id};
		List<OrderDetails> list = jdbcTemplate.query(sql, params,
		new BeanPropertyRowMapper<OrderDetails>(OrderDetails.class));
		if(null!=list&&!list.isEmpty()){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	/**
	 * 查询云商品detail表
	 * @Title: queryYunDetail 
	 * @Description:
	 * @param id
	 * @return List<YunDetails>         
	 * @throws
	 */
	public List<YunDetails> queryYunDetail(String order_number,int is_open) {
		String sql = "select a.*,b.name type_name from tbl_yun_details a left join tbl_yun_type b on a.type_id = b.type_id where order_number = ? and is_open in (1,2) and b.delete_flag=0 and b.type_id>-1 order by id asc";
		Object[] params = new Object[] { order_number};
		List<YunDetails> list = jdbcYunTemplate.query(sql, params,
		new BeanPropertyRowMapper<YunDetails>(YunDetails.class));
		return list;
	}

	/**
	 * 查询订单合同详情列表
	 * @Title: queryOrderDetail 
	 * @Description:
	 * @param order_number
	 * @return List<OrderDetails>         
	 * @throws
	 */
	public List<OrderDetails> queryOrderDetail(String order_number) {
		String sql = "select * from tbl_order_details where order_number = ? order by id asc";
		Object[] params = new Object[] { order_number};
		List<OrderDetails> list = jdbcTemplate.query(sql, params,
		new BeanPropertyRowMapper<OrderDetails>(OrderDetails.class));
		return list;
	}
	
	/**
	 * 删除合同主表
	 * @Title: deleteOrder 
	 * @Description:
	 * @param id
	 * @return int         
	 * @throws
	 */
	public int deleteOrder(int id) {
		int count =0;
		try {
			StringBuffer sql= new StringBuffer("delete from tbl_order_info where id = ?"); 
			Object[] params = new Object[] { id};
			count = jdbcTemplate.update(sql.toString(),params);
		} catch (Exception ex) {
			System.err.println("---------------------------------------------");
			System.err.println(ex.getMessage());
			System.err.println("---------------------------------------------");
			throw new RuntimeException("删除订单合同主表出现错误！");
		}
		return count;
	}
	
	/**
	 * 删除合同详情清单
	 * @Title: deleteOrderDetail 
	 * @Description:
	 * @param order_number
	 * @return int         
	 * @throws
	 */
	public int deleteOrderDetail(String  order_number) {
		int count =0;
		try {
			StringBuffer sql= new StringBuffer("delete from tbl_order_details where order_number = ?"); 
			Object[] params = new Object[] { order_number};
			count = jdbcTemplate.update(sql.toString(),params);
		} catch (Exception ex) {
			System.err.println("---------------------------------------------");
			System.err.println(ex.getMessage());
			System.err.println("---------------------------------------------");
			throw new RuntimeException("删除订单合同中商品清单表出现错误！");
		}
		return count;
	}
	
	/**
	 * 修改订单状态
	 * @Title: sendOrder 
	 * @Description:
	 * @param order_state
	 * @param id
	 * @return int         
	 * @throws
	 */
	public int sendOrder(int order_state,int  id) {
		int count =0;
		try {
			StringBuffer sql= new StringBuffer("update tbl_order_info set order_state = ? where id = ?"); 
			Object[] params = new Object[] { order_state,id};
			count = jdbcTemplate.update(sql.toString(),params);
		} catch (Exception ex) {
			System.err.println("---------------------------------------------");
			System.err.println(ex.getMessage());
			System.err.println("---------------------------------------------");
			throw new RuntimeException("修改合同状态出现错误！");
		}
		return count;
	}
	
	/**
	 * 订单合同修改快递信息
	 * @Title: updatePost 
	 * @Description:
	 * @param od
	 * @return int         
	 * @throws
	 */
	public int updatePost(OrderInfo od) {
		int count =0;
		try {
			StringBuffer sql= new StringBuffer("update tbl_order_info set post_company = ? ,post_number=? where id = ?"); 
			Object[] params = new Object[] { od.getPost_company(),od.getPost_number(),od.getId()};
			count = jdbcTemplate.update(sql.toString(),params);
		} catch (Exception ex) {
			System.err.println("---------------------------------------------");
			System.err.println(ex.getMessage());
			System.err.println("---------------------------------------------");
			throw new RuntimeException("修改快递信息出现错误！");
		}
		return count;
	}
	
	/**
	 * 修改订单应收款状态
	 * @Title: updateYskzt 
	 * @Description:
	 * @param yskzt
	 * @param id
	 * @return int         
	 * @throws
	 */
	public int updateYskzt(String yskzt,int id) {
		int count =0;
		try {
			StringBuffer sql= new StringBuffer("update tbl_order_info set yskzt = ? where id = ?"); 
			Object[] params = new Object[] { yskzt,id};
			count = jdbcTemplate.update(sql.toString(),params);
		} catch (Exception ex) {
			System.err.println("---------------------------------------------");
			System.err.println(ex.getMessage());
			System.err.println("---------------------------------------------");
			throw new RuntimeException("维护应收款状态出现错误！");
		}
		return count;
	}
	
	/**
	 * 订单合同附件上传
	 * @Title: updateHtfj 
	 * @Description:
	 * @param demand_file
	 * @param id
	 * @return int         
	 * @throws
	 */
	public int updateHtfj(String demand_file,int id) {
		int count =0;
		try {
			StringBuffer sql= new StringBuffer("update tbl_order_info set demand_file = ? where id = ?"); 
			Object[] params = new Object[] { demand_file,id};
			count = jdbcTemplate.update(sql.toString(),params);
		} catch (Exception ex) {
			System.err.println("---------------------------------------------");
			System.err.println(ex.getMessage());
			System.err.println("---------------------------------------------");
			throw new RuntimeException("合同附件上传出现错误！");
		}
		return count;
	}
	
	/**
	 * 新增合同操作日志
	 * @Title: addOrderSm 
	 * @Description:
	 * @param os
	 * @return int         
	 * @throws
	 */
	public int addOrderSm(OrderSm os){
		int count=0;
		String sql ="";
		Object[] params = new Object[]{};
		try{
			if(os.getAdd_time()==null){
				sql = "insert into tbl_order_sm (order_id,type,text_sm,add_time) values (?, ?, ?,now())";
				params = new Object[] {os.getOrder_id(),os.getType(),os.getText_sm() };
			}else{
			sql = "insert into tbl_order_sm (order_id,type,text_sm,add_time) values (?, ?, ?,?)";
				params = new Object[] {os.getOrder_id(),os.getType(),os.getText_sm(),os.getAdd_time() };
			}
			count = jdbcTemplate.update(sql, params);
		}catch (Exception ex) {
			System.err.println("---------------------------------------------");
			System.err.println(ex.getMessage());
			System.err.println("---------------------------------------------");
			throw new RuntimeException("新增订单合同操作日志出现错误！");
		}
		return count;
	}
	
	/**
	 * 修改数据--备货状况
	 * @Title: order_Bh 
	 * @Description:
	 * @param list void         
	 * @throws
	 */
	public void order_Bh(final List<OrderDetails> list){
		String sql = "update tbl_order_details set bhqk = ? where order_number = ? and id = ?";
		try {
			BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter() {
				public void setValues(PreparedStatement ps, int index)
						throws SQLException {
					OrderDetails od = list.get(index);
					ps.setString(1, od.getBhqk());
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
			throw new RuntimeException("修改备货情况出现错误！");
		}
	}
	

	/**
	 * 查询合同进度
	 * @Title: queryOrderSm 
	 * @Description:
	 * @param order_id
	 * @return List<OrderSm>         
	 * @throws
	 */
	public List<OrderSm> queryOrderSm(int order_id) {
		String sql = "select * from tbl_order_sm where order_id = ? order by add_time desc";
		Object[] params = new Object[] { order_id};
		List<OrderSm> list = jdbcTemplate.query(sql, params,
		new BeanPropertyRowMapper<OrderSm>(OrderSm.class));
		return list;
	}
	
	/**
	 * 查询订单合同评价
	 * @Title: queryOrder_Pj 
	 * @Description:
	 * @param order_id
	 * @return OrderEvaluate         
	 * @throws
	 */
	public OrderEvaluate queryOrder_Pj(int order_id) {
		String sql = "select * from tbl_order_evaluate where order_id = ?";
		Object[] params = new Object[] { order_id};
		List<OrderEvaluate> list = jdbcTemplate.query(sql, params,
		new BeanPropertyRowMapper<OrderEvaluate>(OrderEvaluate.class));
		if(null!=list&&!list.isEmpty()){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	/**
	 * 新增合同附件
	 * @Title: addOrderFile 
	 * @Description:
	 * @param of
	 * @return int         
	 * @throws
	 */
	public int addOrderFile(OrderFile of){
		int count=0;
		try{
			String sql = "insert into tbl_order_file (order_id,file_url,add_time) values (?, ?, now())";
			Object[] params = new Object[] {of.getOrder_id(),of.getFile_url() };
			count = jdbcTemplate.update(sql, params);
		}catch (Exception ex) {
			System.err.println("---------------------------------------------");
			System.err.println(ex.getMessage());
			System.err.println("---------------------------------------------");
			throw new RuntimeException("新增订单合同附件出现错误！");
		}
		return count;
	}
	
	/**
	 * 删除物流信息
	 * @Title: delSm 
	 * @Description:
	 * @param id
	 * @return int         
	 * @throws
	 */
	public int delSm(int id){
		int count=0;
		try{
			String sql = "delete from tbl_order_sm where id = ?";
			Object[] params = new Object[] {id };
			count = jdbcTemplate.update(sql, params);
		}catch (Exception ex) {
			System.err.println("---------------------------------------------");
			System.err.println(ex.getMessage());
			System.err.println("---------------------------------------------");
			throw new RuntimeException("删除物流出现错误！");
		}
		return count;
	}
}