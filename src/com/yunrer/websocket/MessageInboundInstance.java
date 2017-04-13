package com.yunrer.websocket;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.WsOutbound;
import com.yunrer.entity.SystemUser;

@SuppressWarnings("deprecation")
public class MessageInboundInstance extends MessageInbound {

	public static  ConcurrentHashMap<SystemUser, MessageInboundInstance> currentUserMap = new ConcurrentHashMap<SystemUser, MessageInboundInstance>();

	public static int currentUserCount = 0;

	private SystemUser user;

	@Override
	protected void onClose(int status) {
		// Log
		System.out.println("Web Socket Closed: " + status);
		if (user!=null && currentUserMap.containsKey(user)) {
				currentUserMap.remove(user);
				subCurrentUserCount();
		}
	}

	// WebSocket握手完成，创建完毕，WsOutbound用于向客户端发送数据
	@Override
	protected void onOpen(WsOutbound outbound) {
		// Log
		System.out.println("Web Socket Open!");
		if (!currentUserMap.containsKey(user)) {
				currentUserMap.put(user, this);
				addCurrentUserCount();
		}

	}

	@Override
	protected void onBinaryMessage(ByteBuffer buffer) throws IOException {
		// Log
		System.out.println("Binary Message Receive: " + buffer.remaining());
		// Nothing
	}

	// 有文本消息数据到达
	@Override
	protected void onTextMessage(CharBuffer buffer) throws IOException {
		// Log
		System.out.println("Text Message Receive: " + buffer.remaining());
		// getWsOutbound可以返回当前的WsOutbound，通过他向客户端回传数据，这里采用的是nio的CharBuffer

	}
	
	public static synchronized int getCurrentUserCount() {
		return currentUserCount;
	}

	public static synchronized void addCurrentUserCount() {
		currentUserCount++;
	}

	public static synchronized void subCurrentUserCount() {
		currentUserCount--;
	}

	public MessageInboundInstance(HttpServletRequest rerquest) {

		init(rerquest);

	}

	public void init(HttpServletRequest request) {

		HttpSession session = request.getSession(true);
		user = session.getAttribute("loginUser") == null ? null : (SystemUser) session.getAttribute("loginUser");

	}
}
