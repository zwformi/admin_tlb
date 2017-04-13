package com.yunrer.websocket;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;
import org.apache.catalina.websocket.WsOutbound;
import org.apache.log4j.Logger;

import com.yunrer.entity.SystemUser;

@SuppressWarnings("deprecation")
@WebServlet(urlPatterns = "/echo.ws")
// 处理WebSocket的Servlet需要继承自WebSocketServlet，这一点和7.0.27仍然一样
public class WebSocketServletInstance extends WebSocketServlet {
	// Log
	private Logger logger = Logger.getLogger(WebSocketServletInstance.class.getName());

	
	// 与7.0.27不同的，Tomcat改变了createWebSocketInbound方法的定义，增加了一个HttpServletRequest参数
	// 这样我们也可以从request参数中获取更多请求方的信息
	@Override
	protected StreamInbound createWebSocketInbound(String subProtocol, HttpServletRequest request) {
		// Log
		logger.info("request ws servlet");

		// 方法仍然是返回一个StreamInbound实例，这里采用实现他的子类MessageInbound
		// 只用实现下面四个事件处理函数(其实onClose和onOpen有缺省实现)
		return new MessageInboundInstance(request);
	}
}