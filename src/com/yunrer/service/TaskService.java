package com.yunrer.service;

import java.io.IOException;
import java.nio.CharBuffer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.tomcat.util.net.AbstractEndpoint.Handler.SocketState;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yunrer.entity.Product;
import com.yunrer.entity.SystemUser;
import com.yunrer.websocket.MessageInboundInstance;

@Service("TaskService")
public class TaskService {
	@Resource
	private ZcyService zcyService;
	@Resource
	private ProductService productService;
	@Resource
	private ZcyOrderService zcyOrderService;

	private static Logger log = Logger.getLogger(TaskService.class);
	private static Logger statusLogger = Logger.getLogger("StatusLog");

	/**
	 * 自动同步商品状态 @Title: synsProductStatus @Description: void @throws
	 */
	@Scheduled(cron = "00 00 00 * * ?")
	public void synsProductStatus() {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String id_str = "";
			int[] status = new int[] { 2, 3, 4, 5, 6, 7, 8 };
			List<Product> list = productService.getProductByStatus(status);
			for (int i = 0; i < list.size(); i++) {
				id_str = id_str + list.get(i).getId() + ",";
			}
			if (id_str != "") {
				id_str = id_str.substring(0, id_str.length() - 1);
				map = zcyService.getProductStatus(id_str);

			}
			statusLogger.info("同步状态操作成功！");
			statusLogger.info(map.get("resultMsg"));
			log.info("同步状态操作成功！");
			log.info(map.get("resultMsg"));

		} catch (Exception e) {
			e.getStackTrace();
			map.put("resultCode", 0);
			map.put("resultMsg", map.get("resultMsg") + "同步失败!");
			statusLogger.info("同步操作失败！");
			statusLogger.error("同步操作失败！", e);
		}

	}

	@SuppressWarnings("deprecation")
	@Scheduled(cron = "0/30 * *  * * ? ")
	public void postInfo2User() {
		System.out.println("人数 "+MessageInboundInstance.getCurrentUserCount());
		Map<String, Object> orderMap = zcyOrderService.getOrderList("200001010000", null, 0, null, null);
		System.out.println(orderMap.toString());
		if (MessageInboundInstance.getCurrentUserCount() > 0) {
			Set<Entry<SystemUser, MessageInboundInstance>> userSet = MessageInboundInstance.currentUserMap.entrySet();
			Iterator<Entry<SystemUser, MessageInboundInstance>>  iterator = userSet.iterator();
			while(iterator.hasNext()){
				Entry<SystemUser, MessageInboundInstance> entry = iterator.next();
				MessageInboundInstance msgBound = entry.getValue();
				CharBuffer charBuffer = null;
				try {
					
					if ((Integer) orderMap.get("resultCode") == 1) {
						String data = orderMap.get("data").toString();
						charBuffer = CharBuffer.wrap(data.toCharArray());
//						SocketState state = msgBound.onData();
//						if(state!=SocketState.CLOSED)
						msgBound.getWsOutbound().writeTextMessage(charBuffer);
					}
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				;
			
			}
		}
	}

}
