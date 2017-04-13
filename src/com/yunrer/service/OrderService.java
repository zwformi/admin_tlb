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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yunrer.common.DateUtils;
import com.yunrer.common.HTTPUtils;
import com.yunrer.common.SmsSenderUtils;
import com.yunrer.common.Utils;
import com.yunrer.dao.OrderDao;
import com.yunrer.dao.OrderXuqiuDao;
import com.yunrer.dao.UserInfoDao;
import com.yunrer.dao.UserMessageDao;
import com.yunrer.entity.OrderDetails;
import com.yunrer.entity.OrderEvaluate;
import com.yunrer.entity.OrderFile;
import com.yunrer.entity.OrderInfo;
import com.yunrer.entity.OrderInfoXuqiu;
import com.yunrer.entity.OrderSm;
import com.yunrer.entity.UserInfo;
import com.yunrer.entity.SystemUser;
import com.yunrer.entity.UserMessage;
import com.yunrer.entity.YunDetails;
import com.yunrer.util.FormatTime;
import com.yunrer.util.ExportUtil;

/**
 * 合同Service
 * @ClassName OrderService
 * @Description 
 * @author rujun
 * @date 2016-12-19
 */
@Service("OrderService")
public class OrderService {
	
	@Resource
	private OrderDao orderDao;
	@Resource
	private OrderXuqiuDao orderXuqiuDao;
	@Resource
	private UserMessageDao userMessageDao;
	@Resource
	private UserInfoDao userInfoDao;


	
	/**
	 * 查询合同数量
	 * @Title: queryOrderCount 
	 * @Description:
	 * @param request
	 * @param session
	 * @return int         
	 * @throws
	 */
	public int queryOrderCount(HttpServletRequest request,HttpSession session) {
		int count = 0;
		try{
			String order_number = request.getParameter("order_number");
			String order_source_str = request.getParameter("order_source");//合同来源合同来源（0：采购，1：秒杀,2:立即购买）
			String order_state_str = request.getParameter("order_state");//合同状态(4：配货中 5：已发货，待签收 61：已签收，待实施  62：实施中  71：已签收，待评价   72：已实施、待评价，8已完成)
			int order_source = order_source_str==null||"".equals(order_source_str)?-1:Integer.parseInt(order_source_str);
			int order_state = order_state_str==null||"".equals(order_state_str)?-1:Integer.parseInt(order_state_str);
			String ywyxm = request.getParameter("ywyxm");//业务员姓名
			String gsmc = request.getParameter("gsmc");//公司名称
			
			//权限控制用的工号
			SystemUser user = (SystemUser) session.getAttribute("loginUser");
			count = orderDao.queryOrdercount(order_number,order_source,order_state,ywyxm,gsmc,user);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return count;
	}
	
	/**
	 * 查询合同列表
	 * @Title: queryOrderList 
	 * @Description:
	 * @param request
	 * @param session
	 * @return List<OrderInfo>         
	 * @throws
	 */
	public List<OrderInfo> queryOrderList(HttpServletRequest request,HttpSession session) {
		List<OrderInfo> list = null;
		try{
			String order_number = request.getParameter("order_number");
			String order_source_str = request.getParameter("order_source");//合同来源（0：采购，1：秒杀,2:立即购买）
			String order_state_str = request.getParameter("order_state");//合同状态(4：配货中 5：已发货，待签收 61：已签收，待实施  62：实施中  71：已签收，待评价   72：已实施、待评价，8已完成)
			String ywyxm = request.getParameter("ywyxm");//业务员姓名
			String gsmc = request.getParameter("gsmc");//公司名称
			
			//权限控制用的工号
			SystemUser user = (SystemUser) session.getAttribute("loginUser");
			
			int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
			int pageSize = Integer.parseInt(request.getParameter("pageSize"));
			int order_source = order_source_str==null||"".equals(order_source_str)?-1:Integer.parseInt(order_source_str);
			int order_state = order_state_str==null||"".equals(order_state_str)?-1:Integer.parseInt(order_state_str);
			list = orderDao.queryOrderList(order_number,order_source,order_state,ywyxm,gsmc,user, pageIndex, pageSize);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 生成订单合同
	 * @Title: addOrder 
	 * @Description:
	 * @param request
	 * @param session
	 * @return String         
	 * @throws
	 */
	@Transactional
	public String addOrder(HttpServletRequest request,HttpSession session){
		
		String order_state = request.getParameter("order_state");

		Map<String,Object> resultMap = new HashMap<String,Object>();
		//合同号
		String order_number = Utils.GetOrderNumber();
		order_number = "H_"+order_number;
		// 订单详细信息
		List<OrderDetails> od_list = new ArrayList<OrderDetails>();
			try{
				String[] product_names = request.getParameterValues("product_name");
				String[] type_strs = request.getParameterValues("type_str");
				String[] brand_strs = request.getParameterValues("brand_str");
				String[] xhs = request.getParameterValues("xh");
				String[] pzs = request.getParameterValues("pz");
				String[] product_counts = request.getParameterValues("product_count");
				String[] product_unit_prices = request.getParameterValues("product_unit_price");
				String[] product_unit_price_bjs = request.getParameterValues("product_unit_price_bj");
				
				double total=0;
				for(int i=0;i<product_names.length;i++){
					int product_id =Integer.parseInt(request.getParameterValues("product_id")[i]);
					OrderDetails orderDetails = new OrderDetails();
					orderDetails.setOrder_number(order_number);
					orderDetails.setProduct_id(product_id);
					orderDetails.setType_str(type_strs[i]);
					orderDetails.setBrand_str(brand_strs[i]);
					orderDetails.setXh(xhs[i]);
					orderDetails.setPz(pzs[i]);
					orderDetails.setProduct_name(product_names[i]);
					orderDetails.setProduct_count(Integer.parseInt(product_counts[i]));
					orderDetails.setProduct_unit_price(Double.parseDouble(product_unit_prices[i]));
					orderDetails.setProduct_unit_price_bj(Double.parseDouble(product_unit_price_bjs[i]));
					orderDetails.setCreate_time(new Date());
					od_list.add(orderDetails);
					total += Double.parseDouble(product_unit_prices[i])*Integer.parseInt(product_counts[i]);
					
					
				}
				
				//订单信息
				String userid_ = request.getParameter("user_id");
				
				
				//订单总金额
				String xuqiu_ordernumber = request.getParameter("xuqiu_ordernumber");
				String order_source = request.getParameter("order_source");
				
				//查询需求单信息
				OrderInfoXuqiu od_xuqiu = orderXuqiuDao.queryOrder_Xuqiu_Detail(xuqiu_ordernumber);
			
				OrderInfo o = new OrderInfo();
				o.setOrder_number(order_number);
				o.setUser_id(Integer.parseInt(userid_));
				o.setOrder_state(Integer.parseInt(order_state));
				o.setOrder_time(new Date());
				o.setXuqiu_ordernumber(xuqiu_ordernumber);
				o.setOrder_source(0);//（0：采购，1：秒杀或立即购买）
				o.setDemand_file(request.getParameter("demand_file"));
				o.setShr_yb(request.getParameter("shr_yb"));
				o.setShr_dz(request.getParameter("shr_dz"));
				o.setShr_xm(request.getParameter("shr_xm"));
				o.setShr_dh(request.getParameter("shr_dh"));
				o.setFkfs(request.getParameter("fkfs"));
				o.setShipping_methods((request.getParameter("shipping_methods")==null||request.getParameter("shipping_methods")=="")?0:Integer.parseInt(request.getParameter("shipping_methods")));
				o.setInstallation_service((request.getParameter("installation_service")==null||request.getParameter("installation_service)")=="")?0:Integer.parseInt(request.getParameter("installation_service")));
				o.setErp_number(request.getParameter("erp_number"));
				o.setPayment_money(total);
				o.setIp(HTTPUtils.getIpAddr(request));
				o.setRec_paycondition(Integer.parseInt((request.getParameter("rec_paycondition")==""||request.getParameter("rec_paycondition")==null)?"0":request.getParameter("rec_paycondition")));
				o.setGsmc_final(request.getParameter("gsmc_final"));
				o.setOrder_type(Integer.parseInt((request.getParameter("order_type")==""||request.getParameter("order_type")==null)?"0":request.getParameter("order_type")));
				o.setReception_date(request.getParameter("reception_date")==""?null:DateUtils.str2date(request.getParameter("reception_date")));
				o.setSigning_date(request.getParameter("signing_date")==""?null:DateUtils.str2date(request.getParameter("signing_date")));
				o.setRemark(request.getParameter("remark"));
				
				o.setGsmc_final(request.getParameter("gsmc_final"));
				o.setOrder_type(Integer.parseInt((request.getParameter("order_type")==""||request.getParameter("order_type")==null)?"0":request.getParameter("order_type")));
				o.setOwning_company(od_xuqiu.getOwning_company());
				
				orderDao.addOrder(o);//新增订单主表
				orderDao.addOrderDetail(od_list);//新增订单详情
				orderXuqiuDao.updateOrderXuqiu_ht(xuqiu_ordernumber);//修改需求状态和生成合同时间
				
				//发送消息
				this.addUserMessage(Integer.parseInt(userid_), "您有需求已生成合同", "您的需求【"+xuqiu_ordernumber+"】已生成合同，订单合同单号【"+order_number+"】", xuqiu_ordernumber+","+order_number, "19963",0);
				
				//返回订单成功页面
				return "order/list";
			}catch(Exception ex){
				System.err.println("---------------------------------------------");
				System.err.println(ex.getMessage());
				System.err.println("---------------------------------------------");
				throw new RuntimeException("生成【订单合同 】<br/>出现错误！");
			}
		}
	
	public Map<String,Object> queryYunDetail(HttpServletRequest request,HttpSession session){
		Map<String,Object> map = new HashMap<String,Object>();
		int id = Integer.parseInt(request.getParameter("id"));
		OrderInfo order = orderDao.queryOrder_Detail(id);
		List<YunDetails> yun = orderDao.queryYunDetail(order.getOrder_number(),0);
		map.put("MODULELIST", yun);
		return map;
	}

	/**
	 * 订单合同详情页
	 * @Title: queryOrder_Detail 
	 * @Description:
	 * @param request
	 * @param session
	 * @return Map<String,Object>         
	 * @throws
	 */
	public Map<String,Object> queryOrder_Detail(HttpServletRequest request,HttpSession session){
		Map<String,Object> map = new HashMap<String,Object>();
		int id = request.getParameter("id")==null?0:Integer.parseInt(request.getParameter("id"));
		int od_source = request.getParameter("order_source")==null?0:Integer.parseInt(request.getParameter("order_source"));

		OrderInfo order = orderDao.queryOrder_Detail(id);
		map.put("ORDER", order);
		if(order!=null){
			//查询合同商品清单
			if(od_source==4){
				//云的情况
				
				List<YunDetails> yun = orderDao.queryYunDetail(order.getOrder_number(),0);
				map.put("MODULELIST", yun);
				
			}else{
			//普通状况详情
			
			List<OrderDetails> OrderDetailsList = orderDao.queryOrderDetail(order.getOrder_number());
			map.put("ORDERDETAILSLIST", OrderDetailsList);
			}
			//查询合同进度
			List<OrderSm> OrderSmList = orderDao.queryOrderSm(order.getId());
			OrderSm os = new OrderSm();
			os.setAdd_time(order.getOrder_time());
			os.setText_sm("合同产生，并开始配货");
			os.setType("开始");
			OrderSmList.add(os);
			map.put("ORDERSMLIST", OrderSmList);
			
		}
		//订单确认页
		return map;
	}
	
	/**
	 * 修改订单合同
	 * @Title: updateOrder 
	 * @Description:
	 * @param orderinfo
	 * @return int         
	 * @throws
	 */
	@Transactional
	public int updateOrder(OrderInfo orderinfo) {
		int count = 0;
		try{

				count = orderDao.updateOrder(orderinfo);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return count;
	}
	
	/**
	 * 删除订单合同
	 * @Title: deleteOrder 
	 * @Description:
	 * @param request
	 * @param session
	 * @return int         
	 * @throws
	 */
	@Transactional
	public int deleteOrder(HttpServletRequest request,HttpSession session) {
		int count = 0;
		try{
			int id = Integer.parseInt(request.getParameter("id"));
			OrderInfo order = orderDao.queryOrder_Detail(id);
			if(order!=null){
				//删除订单合同主表
				count = orderDao.deleteOrder(id);
				if(count>0){
					//删除订单合同详细表
					count = orderDao.deleteOrderDetail(order.getOrder_number());
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
	 * 订单合同---配货   -弃用
	 */
	/**
	public int siteOrder(HttpServletRequest request,HttpSession session) {
		int count = 0;
		try{
			int id = Integer.parseInt(request.getParameter("id"));
			count = orderDao.sendOrder(4,id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return count;
	}
	**/
	
	/**
	 * 订单合同--发货
	 * @Title: sendOrder 
	 * @Description:
	 * @param request
	 * @param session
	 * @return int         
	 * @throws
	 */
	@Transactional
	public int sendOrder(HttpServletRequest request,HttpSession session) {
		int count = 0;
		try{
			int postType = Integer.parseInt(request.getParameter("postType"));
			int id = Integer.parseInt(request.getParameter("id"));
			String text_sm = request.getParameter("text_sm");//说明
			int post_company = Integer.parseInt(request.getParameter("post_company"));
			String post_number = request.getParameter("post_number");
			String post_company_str=request.getParameter("post_company_str");
			OrderInfo orderinfo = orderDao.queryOrder_Detail(id);
			String textMsg="您的订单【"+orderinfo.getOrder_number()+"】，已发货。配送方式为";
			count = orderDao.sendOrder(5,id);
			if(count>0){
				OrderSm os = new OrderSm();
				os.setOrder_id(id);
				os.setType("发货");
				//判断发货类型 1、快递 2、自送
				if(postType==1){
					text_sm="已发货【"+ post_company_str+"】，快递单号为："+post_number;
					textMsg+= "【"+post_company_str+"】，快递单号为：【"+post_number+"】";
					orderinfo.setPost_company(post_company)	;
					orderinfo.setPost_number(post_number);//将快递信息写入数据库
					count=orderDao.updatePost(orderinfo);
				}else{
					textMsg+="【自配送】";
				}
				os.setText_sm(text_sm);
				count = orderDao.addOrderSm(os);
				//发送消息
				this.addUserMessage(orderinfo.getUser_id(), "您有订单已发货",textMsg, orderinfo.getOrder_number(), "19966",1);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return count;
	}
	
	/**
	 * 订单合同---维护应收款状态
	 * @Title: yskztOrder 
	 * @Description:
	 * @param request
	 * @param session
	 * @return int         
	 * @throws
	 */
	@Transactional
	public int yskztOrder(HttpServletRequest request,HttpSession session) {
		int count = 0;
		try{
			int id = Integer.parseInt(request.getParameter("id"));
			String text_sm = request.getParameter("text_sm");//应收款状态
			OrderInfo orderinfo = orderDao.queryOrder_Detail(id);
			count = orderDao.updateYskzt(text_sm,id);
			if(count>0){
				OrderSm os = new OrderSm();
				os.setOrder_id(id);
				os.setType("应收款状态维护");
				os.setText_sm("变更应收款状态为["+text_sm+"]");
				count = orderDao.addOrderSm(os);
				//发送消息
				this.addUserMessage(orderinfo.getUser_id(), "您的订单应收款状态有变更", "您的订单【"+orderinfo.getOrder_number()+"】应收款状态有变更", orderinfo.getOrder_number(), "",0);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return count;
	}
	
	/**
	 * 订单合同---合同附件上传
	 * @Title: htfjscOrder 
	 * @Description:
	 * @param request
	 * @param demand_file
	 * @return int         
	 * @throws
	 */
	@Transactional
	public int htfjscOrder(HttpServletRequest request,String demand_file) {
		int count = 0;
		try{
			int id = Integer.parseInt(request.getParameter("order_id"));
			OrderInfo orderinfo = orderDao.queryOrder_Detail(id);
			count = orderDao.updateHtfj(demand_file,id);
			if(count>0){
				OrderSm os = new OrderSm();
				os.setOrder_id(id);
				os.setType("合同上传");
				os.setText_sm("合同附件上传。");
				count = orderDao.addOrderSm(os);
				//发送消息
				this.addUserMessage(orderinfo.getUser_id(), "您订单有合同附件上传", "您的订单【"+orderinfo.getOrder_number()+"】合同附件已上传", orderinfo.getOrder_number(), "",0);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return count;
	}
	
	/**
	 * 订单合同---实施
	 * @Title: ssOrder 
	 * @Description:
	 * @param request
	 * @param session
	 * @return int         
	 * @throws
	 */
	@Transactional
	public int ssOrder(HttpServletRequest request,HttpSession session) {
		int count = 0;
		try{
			int id = Integer.parseInt(request.getParameter("id"));
			String text_sm = request.getParameter("text_sm");//说明
			count = orderDao.sendOrder(62,id);
			if(count>0){
				OrderSm os = new OrderSm();
				os.setOrder_id(id);
				os.setType("实施");
				os.setText_sm(text_sm);
				count = orderDao.addOrderSm(os);
				OrderInfo orderinfo = orderDao.queryOrder_Detail(id);
				//发送消息
				this.addUserMessage(orderinfo.getUser_id(), "您有订单正在分配实施人员", "您的订单【"+orderinfo.getOrder_number()+"】，正在分配实施人员", orderinfo.getOrder_number(), "19967",1);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return count;
	}
	
	/**
	 * 备货情况维护
	 * @Title: order_Bh 
	 * @Description:
	 * @param request
	 * @return int         
	 * @throws
	 */
	public int order_Bh(HttpServletRequest request) {
		int count = 0;
		try{
			String order_number = request.getParameter("order_number");//合同编号
			String[] bhsmarr = request.getParameter("bhsmarr").split(":");
			List<OrderDetails> list = new ArrayList<OrderDetails>();
			OrderDetails o ;
			for(int i=0;i<bhsmarr.length;i++){
				if(!bhsmarr[i].equals("")){
					o = new OrderDetails();
					o.setOrder_number(order_number);
					o.setId(Integer.parseInt(bhsmarr[i].split("-")[0]));
					o.setBhqk(bhsmarr[i].split("-")[1]);
					list.add(o);
				}
			}
			//修改备货情况
			orderDao.order_Bh(list);
			OrderInfo orderinfo = orderDao.queryOrder_Detail(order_number);
			count = 1;
			//发送消息
			this.addUserMessage(orderinfo.getUser_id(), "您有订单正在配货", "您的订单【"+order_number+"】，正在配货", order_number, "19965",0);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return count;
	}
	
	/**
	 * 订单合同---添加物流
	 * @Title: addPost 
	 * @Description:
	 * @param request
	 * @param session
	 * @return int         
	 * @throws
	 */
	@Transactional
	public int addPost(HttpServletRequest request,HttpSession session) {
		int count = 0;
		try{
			String type = request.getParameter("type");
			int order_id = Integer.parseInt(request.getParameter("id"));
			String text_sm = request.getParameter("text_sm");//说明
			Date add_time=FormatTime.formatDate(request.getParameter("optime"));
			OrderSm os = new OrderSm();
			os.setOrder_id(order_id);
				os.setType(type);
				os.setText_sm(text_sm);
				os.setAdd_time(add_time);
				count = orderDao.addOrderSm(os);
				} catch (Exception ex) {
			ex.printStackTrace();
		}
		return count;
	}
	
	/**
	 * 订单合同---删除物流
	 * @Title: delSm 
	 * @Description:
	 * @param request
	 * @return int         
	 * @throws
	 */
	@Transactional
	public int delSm(HttpServletRequest request) {
		int count = 0;
		try{
			if(request.getParameter("id")==""||request.getParameter("id")==null){
				count = 0;
			}else{
				count = orderDao.delSm(Integer.parseInt(request.getParameter("id")));
			}
		
				} catch (Exception ex) {
			ex.printStackTrace();
		}
		return count;
	}
	
	/**
	 * 订单合同评价信息
	 * @Title: queryOrder_Pj 
	 * @Description:
	 * @param request
	 * @param session
	 * @return Map<String,Object>         
	 * @throws
	 */
	public Map<String,Object> queryOrder_Pj(HttpServletRequest request,HttpSession session){
		Map<String,Object> map = new HashMap<String,Object>();
		int order_id = Integer.parseInt(request.getParameter("id"));
		//评价信息
		OrderEvaluate evaluate = orderDao.queryOrder_Pj(order_id);
		map.put("EVALUATE", evaluate);
		
		//订单确认页
		return map;
	}
	
	
	/**
	 * 订单合同---代客户操作
	 * @Title: dkhczOrder 
	 * @Description:
	 * @param request
	 * @param session
	 * @return Map<String,Object>         
	 * @throws
	 */
	@Transactional
	public Map<String,Object> dkhczOrder(HttpServletRequest request,HttpSession session) {
		Map<String,Object> map = new HashMap<String,Object>();
		int count = 0;
		try{
			int order_id = Integer.parseInt(request.getParameter("order_id"));//订单ID
			int order_state = Integer.parseInt(request.getParameter("order_state"));//操作类型
			String file_url = request.getParameter("file_url");//附件路径
			//查询合同信息
			OrderInfo order = orderDao.queryOrder_Detail(order_id);
			
			
			if(order!=null){
				
				if(!(order.getOrder_state()==5&&(order.getShipping_methods()==1||order.getShipping_methods()==3))&&order.getOrder_state()!=62&&!(order.getOrder_state()==4&&order.getShipping_methods()==2)){
					map.put("error", true);
					map.put("message","您的操作不当，请确定订单合同的状态是否为【已发货，待签收】或【实施中】！");
				}else{
					if(order.getOrder_state()==4&&order.getShipping_methods()==2&&(order_state!=61&&order_state!=71)){
						map.put("error", true);
						map.put("message","您的操作不当，本合同订单该阶段只能执行【签收】或【签收并发起实施】操作！");
					}else if(order.getOrder_state()==5&&(order.getShipping_methods()==1||order.getShipping_methods()==3)&&(order_state!=61&&order_state!=71)){
						map.put("error", true);
						map.put("message","您的操作不当，本合同订单该阶段只能执行【签收】或【签收并发起实施】操作！");
					}else if(order.getOrder_state()==62&&order_state!=72){
						map.put("error", true);
						map.put("message","您的操作不当，本合同订单该阶段只能执行【确认实施完成】操作！");
					}else{
						String type="";
						String text_sm="";
						String ckfj_str ="";
						String dscfj="";
						if(file_url!=null&&!file_url.equals("")){//判读是否有附件
							if(order_state==61){ckfj_str = "&nbsp;<a style='color:#3cb9f3' target='_blank' href='http://img.tulingbuy.com"+file_url+"'>查看签收单附件</a>";}
							if(order_state==71){ckfj_str = "&nbsp;<a style='color:#3cb9f3' target='_blank' href='http://img.tulingbuy.com"+file_url+"'>查看签收单附件</a>";}
							if(order_state==72){ckfj_str = "&nbsp;<a style='color:#3cb9f3' target='_blank' href='http://img.tulingbuy.com"+file_url+"'>查看实施单附件</a>";}
						}else{
							if(order_state==61){dscfj = "，待上传签收单附件";}
							if(order_state==71){dscfj = "，待上传签收单附件";}
							if(order_state==72){dscfj = "，待上传实施单附件";}
						}
						if(order_state==61){type="签收";text_sm="系统代客户自动签收，并发起实施"+ckfj_str+dscfj;}
						if(order_state==71){type="签收";text_sm="系统代客户自动签收"+ckfj_str+dscfj;}
						if(order_state==72){type="实施完成";text_sm="系统代客户确认实施完成"+ckfj_str+dscfj;}
						//修改合同状态
						count = orderDao.sendOrder(order_state,order_id);
						if(count>0){
							OrderSm os = new OrderSm();
							os.setOrder_id(order_id);
							os.setType(type);
							os.setText_sm(text_sm);
							//新增合同操作日志
							count = orderDao.addOrderSm(os);
							if(count>0){
								OrderFile of = new OrderFile();
								of.setFile_url(file_url);
								of.setOrder_id(order_id);
								//保存附件
								count = orderDao.addOrderFile(of);
								if(count<=0){
									throw new RuntimeException("代客户操作合同出现错误！");
								}
							}else{
								throw new RuntimeException("新增合同日志出现错误！");
							}
						}
						map.put("count", count);
					}
				}
			}else{
				map.put("error", true);
				map.put("message","当前合同订单已删除，无法完成操作！");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 订单合同---上传合同相关附件
	 * @Title: orderFileUpdate 
	 * @Description:
	 * @param request
	 * @param session
	 * @return Map<String,Object>         
	 * @throws
	 */
	@Transactional
	public Map<String,Object> orderFileUpdate(HttpServletRequest request,HttpSession session) {
		Map<String,Object> map = new HashMap<String,Object>();
		int count = 0;
		try{
			int order_id = Integer.parseInt(request.getParameter("order_id"));//订单ID
			int file_type = Integer.parseInt(request.getParameter("file_type"));//附件类型
			String file_url = request.getParameter("file_url");//附件路径
			//查询合同信息
			OrderInfo order = orderDao.queryOrder_Detail(order_id);
			if(order!=null){
				
						String type="";
						String text_sm="";
						String ckfj_str ="";
						if(file_url!=null&&!file_url.equals("")){//判读是否有附件
							if(file_type==1){ckfj_str = "&nbsp;<a style='color:#3cb9f3' target='_blank' href='http://img.tulingbuy.com"+file_url+"'>查看签收单附件</a>";}
							if(file_type==2){ckfj_str = "&nbsp;<a style='color:#3cb9f3' target='_blank' href='http://img.tulingbuy.com"+file_url+"'>查看实施单附件</a>";}
						}
						if(file_type==1){type="签收单上传";text_sm="系统上传签收单附件"+ckfj_str;}
						if(file_type==2){type="实施单上传";text_sm="系统上传实施单附件"+ckfj_str;}
						OrderSm os = new OrderSm();
						os.setOrder_id(order_id);
						os.setType(type);
						os.setText_sm(text_sm);
						//新增合同操作日志
						count = orderDao.addOrderSm(os);
						if(count>0){
							OrderFile of = new OrderFile();
							of.setFile_url(file_url);
							of.setOrder_id(order_id);
							//保存附件
							count = orderDao.addOrderFile(of);
							if(count<=0){
								throw new RuntimeException("合同附件上传出现错误！");
							}
						}else{
							throw new RuntimeException("新增合同日志出现错误！");
						}
						map.put("count", count);
			}else{
				map.put("error", true);
				map.put("message","当前合同订单已删除，无法完成操作！");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 订单合同---代客户开通
	 * @Title: activateOrder 
	 * @Description:
	 * @param yundetails
	 * @param session
	 * @return int         
	 * @throws
	 */
	public int activateOrder(YunDetails yundetails,HttpSession session) {
		int count = 0;
		try{
			//开通
			count = orderDao.updateYun_Detail(yundetails);
			
			//调用接口
			
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
	 * @param sfdx 是否发送短信 0不发送   1发送
	 */
	public void addUserMessage(int user_id, String title,
			String content, String param, String templateId,int sfdx) {
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
				sfdx =0;
				System.out.println("手机号"+mobile+"不合法！短信不发送!");
			}
			
			if(sfdx==1){
				//发送通知短信
				SmsSenderUtils.sendSms(mobile, param, templateId);
			}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * 导出订单
	 * @Title: exportOrder 
	 * @Description:
	 * @param request
	 * @param response
	 * @param session
	 * @return Map         
	 * @throws
	 */
	public Map exportOrder(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		String[] names = {"合同编号","合同签订日期","客户名称","最终用户名称","合同金额","合同类型","销售人员","验收时间","回款状况","备注"};
		String[] keys = {"order_number","signing_date","gsmc","gsmc_final","payment_money","order_typeTrans","ywyxm","reception_date","rec_payconditionTrans","remark"};
		String order_number = request.getParameter("order_number");
		String order_source_str = request.getParameter("order_source");//合同来源合同来源（0：采购，1：秒杀,2:立即购买）
		String order_state_str = request.getParameter("order_state");//合同状态(4：配货中 5：已发货，待签收 61：已签收，待实施  62：实施中  71：已签收，待评价   72：已实施、待评价，8已完成)
		int order_source = order_source_str==null||"".equals(order_source_str)?-1:Integer.parseInt(order_source_str);
		int order_state = order_state_str==null||"".equals(order_state_str)?-1:Integer.parseInt(order_state_str);
		String ywyxm = request.getParameter("ywyxm");//业务员姓名
		String gsmc = request.getParameter("gsmc");//公司名称
		
		//权限控制用的工号
		SystemUser user = (SystemUser) session.getAttribute("loginUser");
		
		List<Map<String,Object>> maplist =  orderDao.queryOrderList(order_number,order_source,order_state,ywyxm,gsmc,user);
		Map map =new HashMap();
		map = ExportUtil.excelExport("order.xls","合同表",names,keys,0,maplist,response);	
		return map;
	}
	
	
	
}