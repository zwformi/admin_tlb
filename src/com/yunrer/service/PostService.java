package com.yunrer.service;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunrer.dao.OrderDao;
import com.yunrer.dao.StringMapDao;
import com.yunrer.entity.DataItem;
import com.yunrer.entity.OrderInfo;
import com.yunrer.entity.OrderSm;
import com.yunrer.entity.PostData;

/**
 * 快递Service
 * @ClassName PostService
 * @Description 
 * @author rujun
 * @date 2016-12-19
 */
@Service("PostService")
public class PostService {

	@Resource
	private OrderDao orderDao;
	@Resource
	private StringMapDao stringmapDao;

	/**
	 * 获取快递数据
	 * @Title: getPostData 
	 * @Description:
	 * @param order_number
	 * @return List<?>         
	 * @throws
	 */
	@SuppressWarnings({ "deprecation", "finally" })
	public List<?> getPostData(String order_number) {

		String post_company = "";
		String post_number = "";
		Date lastTime = new Date();
		OrderInfo order = orderDao.queryOrder_Detail(order_number);
		
		if (order!=null && order.getPost_company() >0 && order.getPost_number()!=null) {
			post_company = stringmapDao.getName("order_info", "post_company",
					order.getPost_company())==null?"":stringmapDao.getName("order_info", "post_company",
							order.getPost_company()).getPinyin();
			post_number = order.getPost_number();

		}
		if (post_number != null && post_company != null
				&& post_number.length() > 5 && post_company.length() > 1) {
			String path = "http://www.kuaidi100.com/query?type=" + post_company
					+ "&postid=" + post_number;
			System.out.println(path);
			try {
				URL url = new URL(path);
				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();
				conn.setConnectTimeout(10 * 1000);
				conn.connect();
				if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
					System.out.println("网络错误");

				} else {
					InputStreamReader inputReader = new InputStreamReader(
							conn.getInputStream());
					ObjectMapper mapper = new ObjectMapper();
					PostData data = mapper.readValue(inputReader,
							PostData.class);
					List<DataItem> items = data.getData();
					OrderSm sm = null;
					List<OrderSm> listsm = orderDao.queryOrderSm(order.getId());
					if (listsm != null && listsm.size() > 0) {
						sm = listsm.get(0);
						lastTime = sm.getAdd_time();

					}
					if (items != null && items.size() > 0) {
						for (int i = items.size()-1; i > 0; i--) {
							DataItem item = items.get(i);
							SimpleDateFormat sim = new SimpleDateFormat(
									"yyyy-MM-dd hh:mm:ss");
							Date d = sim.parse(item.getTime());
							if (d.after(lastTime)
									|| !sm.getType().equals("快递信息")) {
								OrderSm os = new OrderSm();
								os.setType("快递信息");
								os.setAdd_time(d);
								os.setOrder_id(order.getId());
								os.setText_sm(item.getContext());
								orderDao.addOrderSm(os);

							}
						}
					}
				}
				
			} catch (Exception e) {
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
				System.out.println(e.getMessage());
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			}finally{
				
					List<OrderSm> odsmList=orderDao.queryOrderSm(order.getId());
					OrderSm ossm = new OrderSm();
					if(order.getOrder_state() > 0){
						ossm.setAdd_time(order.getOrder_time());
						ossm.setText_sm("合同产生，并开始配货");
						ossm.setType("开始");
					}else{
						ossm.setAdd_time(order.getOrder_time());
						ossm.setText_sm("订单已提交，等待支付");
						ossm.setType("开始");
					}
						odsmList.add(ossm);
						return odsmList;
			}
		} 
		return null;
	}
	
	/**
	 * 检查快递单号
	 * @Title: checkPostNumber 
	 * @Description:
	 * @param request
	 * @return Map<String,Object>         
	 * @throws
	 */
	@SuppressWarnings("finally")
	public Map<String,Object> checkPostNumber(HttpServletRequest request) {
		Map<String,Object> resultMap = new HashMap<String, Object>();
		String post_company = request.getParameter("post_company");
		String post_number = request.getParameter("post_number");
		try {
			int company = request.getParameter("post_company") == null ? -1
					: Integer.parseInt(request.getParameter("post_company"));
			post_company = company > 0 ? stringmapDao.getName("order_info",
					"post_company", company).getPinyin(): "";
			String path = "http://www.kuaidi100.com/query?type=" + post_company
					+ "&postid=" + post_number;
			if (post_number != null && post_company != null
					&& !post_number.equals("") && !post_company.equals("")) {

				URL url = new URL(path);
				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();
				conn.setConnectTimeout(10 * 1000);
				conn.connect();
				if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
					System.out.println("网络错误");

				} else {
					InputStreamReader inputReader = new InputStreamReader(
							conn.getInputStream(),"UTF-8");
					ObjectMapper mapper = new ObjectMapper();
					PostData data = mapper.readValue(inputReader,
							PostData.class);
					if(data.getStatus().equals("200")){
						resultMap.put("rescode", 1);
						resultMap.put("resMsg", data.getMessage());
					}else{
						resultMap.put("rescode", 0);
						resultMap.put("resMsg", data.getMessage());
					}
						
				}
			}
		} catch (Exception e) {
			resultMap.put("rescode", 0);
			resultMap.put("resMsg", "网络异常");
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			System.out.println(e.getMessage());
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		}finally{
			
			return resultMap;
		}

	}

}
