package com.yunrer.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yunrer.common.SmsSenderUtils;
import com.yunrer.dao.OrderXuqiuDao;
import com.yunrer.dao.UserAddressDao;
import com.yunrer.dao.UserInfoDao;
import com.yunrer.dao.UserMessageDao;
import com.yunrer.entity.OrderDetailsXuqiu;
import com.yunrer.entity.OrderInfoXuqiu;
import com.yunrer.entity.SystemUser;
import com.yunrer.entity.UserAddress;
import com.yunrer.entity.UserInfo;
import com.yunrer.entity.UserMessage;
/**
 * 需求Service
 * @ClassName OrderXuqiuService
 * @Description 
 * @author rujun
 * @date 2016-12-19
 */
@Service("OrderXuqiuService")
public class OrderXuqiuService {
	
	@Resource
	private OrderXuqiuDao orderXuqiuDao;
	@Resource
	private UserInfoDao userInfoDao;
	@Resource
	private UserMessageDao userMessageDao;
	@Resource
	private UserAddressDao userAddressDao;
	
	/**
	 * 查询需求数量
	 * @Title: queryOrderXuqiuCount 
	 * @Description:
	 * @param request
	 * @param session
	 * @return int         
	 * @throws
	 */
	public int queryOrderXuqiuCount(HttpServletRequest request,HttpSession session) {
		int count = 0;
		try{
			String order_number = request.getParameter("order_number");
			String order_source_str = request.getParameter("order_source");//合同来源（0：PC，1：微信，2：一键需求）
			String order_state_str = request.getParameter("order_state");//合同状态(4：待报价  5：完成报价，6：已关闭，7：客户接收报价，8：已生成合同)
			int order_source = order_source_str==null||"".equals(order_source_str)?-1:Integer.parseInt(order_source_str);
			int order_state = order_state_str==null||"".equals(order_state_str)?0:Integer.parseInt(order_state_str);
			String ywyxm = request.getParameter("ywyxm");//业务员姓名
			String gsmc = request.getParameter("gsmc");//公司名称
			
			//权限控制用的工号
			SystemUser user = (SystemUser) session.getAttribute("loginUser");
			
			count = orderXuqiuDao.queryOrderXuqiucount(order_number,order_source,order_state,ywyxm,gsmc,user);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return count;
	}
	
	/**
	 * 查询需求列表
	 * @Title: queryOrderXuqiuList 
	 * @Description:
	 * @param request
	 * @param session
	 * @return List         
	 * @throws
	 */
	public List queryOrderXuqiuList(HttpServletRequest request,HttpSession session) {
		List list = null;
		try{
			String order_number = request.getParameter("order_number");
			String order_source_str = request.getParameter("order_source");//合同来源（0：PC，1：微信，2：一键需求）
			String order_state_str = request.getParameter("order_state");//合同状态(4：待报价  5：完成报价，6：已关闭，7：客户接收报价，8：已生成合同)
			String xm = request.getParameter("ywyxm"); //业务员姓名
			String gsmc = request.getParameter("gsmc"); //公司名称
			int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
			int pageSize = Integer.parseInt(request.getParameter("pageSize"));
			int order_source = order_source_str==null||"".equals(order_source_str)?-1:Integer.parseInt(order_source_str);
			int order_state = order_state_str==null||"".equals(order_state_str)?0:Integer.parseInt(order_state_str);
			
			//权限控制用的工号
			SystemUser user = (SystemUser) session.getAttribute("loginUser");
			list = orderXuqiuDao.queryOrderXuqiuList(order_number,order_source,order_state,xm,gsmc,user,pageIndex, pageSize);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 需求单详情页
	 * @Title: queryOrder_Xuqiu_Detail 
	 * @Description:
	 * @param request
	 * @param session
	 * @return Map<String,Object>         
	 * @throws
	 */
	public Map<String,Object> queryOrder_Xuqiu_Detail(HttpServletRequest request,HttpSession session){
		Map<String,Object> map = new HashMap<String,Object>();
		int id = Integer.parseInt(request.getParameter("id"));
		//需求详情
		OrderInfoXuqiu xuqiu = orderXuqiuDao.queryOrder_Xuqiu_Detail(id);
		map.put("XUQIU", xuqiu);
		if(xuqiu!=null){
			//查询人员收货地址信息
			UserAddress userAddress = new UserAddress();
			List<UserAddress> list =  userAddressDao.queryAddressList(xuqiu.getUser_id());
			if(list.size()!=0){
				userAddress = userAddressDao.queryAddressList(xuqiu.getUser_id()).get(0);
			}else{
				
				UserInfo userinfo = userInfoDao.queryUserInfo(xuqiu.getUser_id());
				userAddress = new UserAddress();
				userAddress.setPhone(userinfo.getPhone());
				userAddress.setConsignee_name(userinfo.getXm());
				userAddress.setAddress(userinfo.getGsmc());
				
				
			}
		
			
			UserInfo userinfo = userInfoDao.queryUserInfo(xuqiu.getUser_id());
			map.put("USERADDRESS", userAddress);
			map.put("XUQIU", xuqiu);
			//查询商品清单信息
			List<OrderDetailsXuqiu> OrderDetailsXuqiuList = orderXuqiuDao.queryOrderDetail_Xuqiu(xuqiu.getOrder_number());
			map.put("ORDERDETAILSXUQIULIST", OrderDetailsXuqiuList);
		}
		//订单确认页
		return map;
	}
	
	/**
	 * 新增需求产品信息
	 * @Title: addOrderXuqiuOffer 
	 * @Description:
	 * @param request
	 * @return int         
	 * @throws
	 */
	public int addOrderXuqiuOffer(HttpServletRequest request) {
		int id = 0;
		try{
			//查询报价附件是否已经有值，如果已经有值，则不会添加
			String order_number = request.getParameter("order_number");
			OrderInfoXuqiu ox = orderXuqiuDao.queryOrder_Xuqiu_Detail(order_number);
			if(ox.getOffer_file()==""||ox.getOffer_file()==null){
			OrderDetailsXuqiu orderdetailsxuqiu = new OrderDetailsXuqiu();
			orderdetailsxuqiu.setOrder_number(request.getParameter("order_number")); 
			orderdetailsxuqiu.setProduct_name(request.getAttribute("product_name").toString()); 
			orderdetailsxuqiu.setType_str(request.getParameter("type_str"));
			orderdetailsxuqiu.setBrand_str(request.getParameter("brand_str"));
			orderdetailsxuqiu.setXh(request.getParameter("xh"));
			orderdetailsxuqiu.setPz(request.getParameter("pz"));
			String count =request.getParameter("product_count")==null?"1":request.getParameter("product_count");
			orderdetailsxuqiu.setProduct_count(Integer.parseInt(count));
			orderdetailsxuqiu.setCreate_time(new Date());
			id = orderXuqiuDao.addOrderDetail_Xuqiu(orderdetailsxuqiu);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return 1;
	}
	
	
	/**
	 * 新增需求产品信息
	 * @Title: addOrderXuqiu 
	 * @Description:
	 * @param request
	 * @return int         
	 * @throws
	 */
	public int addOrderXuqiu(HttpServletRequest request) {
		int id = 0;
		try{
			OrderDetailsXuqiu orderdetailsxuqiu = new OrderDetailsXuqiu();
			orderdetailsxuqiu.setOrder_number(request.getParameter("order_number")); 
			orderdetailsxuqiu.setProduct_name(request.getParameter("product_name")); 
			orderdetailsxuqiu.setType_str(request.getParameter("type_str"));
			orderdetailsxuqiu.setBrand_str(request.getParameter("brand_str"));
			orderdetailsxuqiu.setXh(request.getParameter("xh"));
			orderdetailsxuqiu.setPz(request.getParameter("pz"));
			String count =request.getParameter("product_count")==null?"1":request.getParameter("product_count");
			orderdetailsxuqiu.setProduct_count(Integer.parseInt(count));
			orderdetailsxuqiu.setCreate_time(new Date());
			id = orderXuqiuDao.addOrderDetail_Xuqiu(orderdetailsxuqiu);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return id;
	}
	
	/**
	 * 删除需求产品信息
	 * @Title: delOrderXuqiu 
	 * @Description:
	 * @param request
	 * @return int         
	 * @throws
	 */
	public int delOrderXuqiu(HttpServletRequest request) {
		int count = 0;
		try{
			int id = Integer.parseInt(request.getParameter("id"));
			String order_number = request.getParameter("order_number");
			count = orderXuqiuDao.delOrderXuqiu(order_number,id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return count;
	}


	/**
	 * 报价附件上传-修改需求单
	 * @Title: changeOrderXuqiuOffer 
	 * @Description:
	 * @param request
	 * @return int         
	 * @throws
	 */
	public int changeOrderXuqiuOffer(HttpServletRequest request) {
		int id = 0;
		try{
			String order_number = request.getParameter("order_number").substring(0, 26);
			OrderInfoXuqiu ox = orderXuqiuDao.queryOrder_Xuqiu_Detail(order_number);
			
			
			
			//为空时才添加detail商品
			if(ox.getOffer_file()==""||ox.getOffer_file()==null){
	
			OrderDetailsXuqiu orderdetailsxuqiu = new OrderDetailsXuqiu();
			orderdetailsxuqiu.setOrder_number(order_number); 
			orderdetailsxuqiu.setProduct_name("需求单"+order_number); 
			orderdetailsxuqiu.setProduct_count(1);
			orderdetailsxuqiu.setCreate_time(new Date());
			id = orderXuqiuDao.addOrderDetail_Xuqiu(orderdetailsxuqiu);
			
			
			
			}else{
				id=1;
			}
			
			
			String url = request.getAttribute("offer_file").toString();
			id = orderXuqiuDao.changeOrderXuqiu("offer_file",url,order_number);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return id;
		
	}
	
	/**
	 * 需求报价
	 * @Title: orderXuqiu_Bj 
	 * @Description:
	 * @param request
	 * @return int         
	 * @throws
	 */
	@Transactional
	public int orderXuqiu_Bj(HttpServletRequest request) {
		int count = 0;
		try{
			String order_number = request.getParameter("order_number");
			String total_str = request.getParameter("total");
			double total_client = Double.parseDouble(total_str);
			String[] bjarr = request.getParameter("bjarr").split(":");
			List<OrderDetailsXuqiu> list = new ArrayList<OrderDetailsXuqiu>();
			OrderDetailsXuqiu o ;
			double total =0;
			for(int i=0;i<bjarr.length;i++){
				if(!bjarr[i].equals("")){
					o = new OrderDetailsXuqiu();
					o.setOrder_number(order_number);
					o.setProduct_unit_price_bj(Double.parseDouble(bjarr[i].split("-")[1]));
					o.setId(Integer.parseInt(bjarr[i].split("-")[0]));
					total += (Double.parseDouble(bjarr[i].split("-")[1])*Integer.parseInt(bjarr[i].split("-")[2]));
					list.add(o);
				}
			}
			if(total_client==total){
				//修改报价
				orderXuqiuDao.orderXuqiu_Bj(list);
				//修改报价状态和总价、报价时间
				
				count = orderXuqiuDao.updateOrderXuqiu(order_number,total);
				if(count>0){
					OrderInfoXuqiu oix = orderXuqiuDao.queryOrder_Xuqiu_Detail(order_number);
					//发送消息
					this.addUserMessage(oix.getUser_id(), "您有需求单已报价", "您的需求单【"+oix.getOrder_number()+"】已报价", oix.getOrder_number(), "19962");
				}
			}else{
				count = 0 ;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return count;
	}
	
	/**
	 * 删除需求单
	 * @Title: deleteOrderXuqiu 
	 * @Description:
	 * @param request
	 * @param session
	 * @return int         
	 * @throws
	 */
	@Transactional
	public int deleteOrderXuqiu(HttpServletRequest request,HttpSession session) {
		int count = 0;
		try{
			int id = Integer.parseInt(request.getParameter("id"));
			OrderInfoXuqiu xuqiu = orderXuqiuDao.queryOrder_Xuqiu_Detail(id);
			if(xuqiu!=null){
				//删除需求单主表
				count = orderXuqiuDao.deleteOrderXuqiu(id);
				if(count>0){
					//删除需求单详细表
					count = orderXuqiuDao.deleteOrderXuqiuDetail(xuqiu.getOrder_number());
				}
			}else{
				count = 1;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return count;
	}
	
	/**
	 * 关闭需求单
	 * @Title: cancelOrderXuqiu 
	 * @Description:
	 * @param request
	 * @param session
	 * @return int         
	 * @throws
	 */
	public int cancelOrderXuqiu(HttpServletRequest request,HttpSession session) {
		int count = 0;
		try{
			int id = Integer.parseInt(request.getParameter("id"));
			count = orderXuqiuDao.cancelOrderXuqiu(id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return count;
	}
	
	/**
	 * 新增用户消息
	 * @param user_id  用户ID
	 * @param title  标题
	 * @param content  内容
	 * @param param  短信内容
	 * @param templateId  模板ID
	 */
	public void addUserMessage(int user_id, String title,
			String content, String param, String templateId) {
		try{
			//存储至数据库
			UserMessage um = new UserMessage();
			um.setUser_id(user_id);
			um.setTitle(title);
			um.setContent(content);
			userMessageDao.addUserMessage(um);
			
			UserInfo user = userInfoDao.queryUserInfo(user_id);
			String mobile = user.getPhone();
			Integer send_msg = user.getSend_msg();
			if(send_msg==1){ //标志位为0时默认不发送短信
			//发送短信时，判断手机号是否为合法的11位,若不合法不发送短信
			Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");  

			Matcher m = p.matcher(mobile);  
			if(!m.matches()){ 
				
				System.out.println("手机号"+mobile+"不合法！短信不发送!");
			}else{
				//发送通知短信
				SmsSenderUtils.sendSms(mobile, param, templateId);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}