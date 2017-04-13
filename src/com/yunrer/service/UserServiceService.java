package com.yunrer.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.alibaba.druid.support.json.JSONUtils;
import com.yunrer.dao.OrderDao;
import com.yunrer.dao.UserServiceDao;
import com.yunrer.entity.OrderDetails;
import com.yunrer.entity.UserService;

/**
 * 服务单service
 * @ClassName UserServiceService
 * @Description 
 * @author rujun
 * @date 2016-12-21
 */
@Service("UserServiceService")
public class UserServiceService {
	
	@Resource
	private UserServiceDao userServiceDao;
	@Resource
	private OrderDao orderDao;
	
	/**
	 * 查询服务单数量
	 * @Title: queryServicecount 
	 * @Description:
	 * @param request
	 * @return int         
	 * @throws
	 */
	public int queryServicecount(HttpServletRequest request) {
		int count = 0;
		try{
			String keyword = request.getParameter("keyword");
			String state_str = request.getParameter("state");//状态 0：等待服务   1：已服务
			int state = state_str==null||"".equals(state_str)?-1:Integer.parseInt(state_str);
			count = userServiceDao.queryServicecount(keyword,state);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return count;
	}
	
	/**
	 * 查询服务单列表
	 * @Title: queryServiceList 
	 * @Description:
	 * @param request
	 * @return List<UserService>         
	 * @throws
	 */
	public List<UserService> queryServiceList(HttpServletRequest request) {
		List<UserService> list = null;
		try{
			String keyword = request.getParameter("keyword");
			String state_str = request.getParameter("state");//状态 0：等待服务   1：已服务
			int state = state_str==null||"".equals(state_str)?-1:Integer.parseInt(state_str);
			int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
			int pageSize = Integer.parseInt(request.getParameter("pageSize"));
			list = userServiceDao.queryServiceList(keyword,state, pageIndex, pageSize);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 服务单详情页
	 * @Title: queryService 
	 * @Description:
	 * @param request
	 * @param session
	 * @return Map<String,Object>         
	 * @throws
	 */
	public Map<String,Object> queryService(HttpServletRequest request,HttpSession session){
		Map<String,Object> map = new HashMap<String,Object>();
		String service_number = request.getParameter("service_number");
		//服务详情
		UserService userservice = userServiceDao.queryService(service_number);
		map.put("USERSERVICE", userservice);
		if(userservice!=null&&userservice.getOrder_detail_id()!=0){
			//查询商品信息
			OrderDetails orderDetail = orderDao.queryOrderDetail(userservice.getOrder_detail_id());
			map.put("ORDERDETAILS", orderDetail);
		}
		//订单确认页
		return map;
	}
	
	/**
	 * 删除服务单
	 * @Title: deleteService 
	 * @Description:
	 * @param request
	 * @param session
	 * @return int         
	 * @throws
	 */
	public int deleteService(HttpServletRequest request,HttpSession session) {
		int count = 0;
		try{
			String service_number = request.getParameter("service_number");
			//删除服务单
			count = userServiceDao.deleteService(service_number);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return count;
	}
	
	/**
	 * 修改服务单的服务状态---0：等待服务   1：已服务
	 * @Title: updateState 
	 * @Description:
	 * @param request
	 * @param session
	 * @return int         
	 * @throws
	 */
	public int updateState(HttpServletRequest request,HttpSession session) {
		int count = 0;
		try{
			String service_number = request.getParameter("service_number");
			count = userServiceDao.updateState(1,service_number);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return count;
	}
	
	/**
	 * 修改服务单备注
	 * @Title: updateComment 
	 * @Description:
	 * @param request
	 * @param session
	 * @return int         
	 * @throws
	 */
	public int updateComment(HttpServletRequest request,HttpSession session) {
		int count = 0;
		try{
			String service_number = request.getParameter("service_number");
			String comment = request.getParameter("comment");
			count = userServiceDao.updateComment(comment,service_number);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return count;
	}
	
}