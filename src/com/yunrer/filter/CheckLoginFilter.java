package com.yunrer.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yunrer.entity.SystemUser;

/**
 * 检查登录过滤器
 * @ClassName CheckLoginFilter
 * @Description 
 */
@SuppressWarnings("serial")
public class CheckLoginFilter extends HttpServlet implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession(true);

		String path = req.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

//		if (basePath.indexOf("admin.tulingbuy.com") == -1) {
//			res.sendRedirect("http://www.tulingbuy.com");
//			res.setHeader("Cache-Control", "no-store");
//			res.setDateHeader("Expires", 0);
//			res.setHeader("Pragma", "no-cache");
//		} else {
			String uri = req.getRequestURI();
			if( uri.indexOf("login.html") != -1 && uri.indexOf("sso.html") != -1){
				// 验证码页面,继续此次请求
				chain.doFilter(request, response);
			} else {
				// 从session里取的用户名信息
				SystemUser loginUser = (SystemUser) session.getAttribute("loginUser");
				
				if (loginUser != null && loginUser.getUser_name() != null){
					// 已经登陆,继续此次请求
					chain.doFilter(request, response);
				} else {
					// 跳转到登陆页面
					session.setAttribute("requestUrl", uri);
					res.sendRedirect(basePath + "login.html");
					res.setHeader("Cache-Control", "no-store");
					res.setDateHeader("Expires", 0);
					res.setHeader("Pragma", "no-cache");
				}
			}
//		}
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
	}
}