package com.yunrer.control;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.alibaba.druid.support.json.JSONUtils;
import com.yunrer.common.HTTPUtils;
import com.yunrer.common.ProcessResult;
import com.yunrer.entity.SystemNode;
import com.yunrer.entity.SystemUser;
import com.yunrer.service.SSOService;
import com.yunrer.service.SystemUserService;

/**
 * sso管理
 * @author zhanggaofeng
 */
@Controller
@RequestMapping("/sso")
public class SsoControl {
	
	@Resource
	private SystemUserService systemUserService;
	@Resource
	private SSOService sService;
	

	/**
	 * sso查询
	 * @Title: doCheck 
	 * @Description:
	 * @param request
	 * @param response
	 * @param session
	 * @return String         
	 * @throws
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String doCheck(HttpServletRequest request, HttpServletResponse response,HttpSession session){
		String result = "";	
		String target = request.getParameter("target");
		if (session.getAttribute("loginUser") !=null){
			try {
				response.sendRedirect(target);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}else{
				try {
				if(request.getParameter("authtocken")==null){
						response.sendRedirect(request.getParameter("crmhost")+"?target="
							+request.getParameter("target")+"&tlmhost="+request.getRequestURL());
				}else{
					boolean flag =sService.createUser(request,session);
					if(flag)
						response.sendRedirect(target);
					else 
						response.sendRedirect(request.getParameter("crmhost")+"?target="
								+request.getParameter("target")+"&tlmhost="+request.getRequestURL());
					return null;
					//当key查询失败时候执行
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		
		return null;

	}
	
	/**
	 * sso退出
	 * @Title: loginOut 
	 * @Description:
	 * @param request
	 * @param response
	 * @param session void         
	 * @throws
	 */
	@RequestMapping(value = "/ssoout")
	public void loginOut(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		try {
		session.removeAttribute("loginUser");
		Map<String,Object> map = new HashMap<String, Object>();
		if(session.getAttribute("loginUser")==null){
			map.put("resMsg", "success");
			map.put("message", "退出成功");	
		}else{
			map.put("resMsg", "failure");
			map.put("message", "退出失败");	
		}
			response.setContentType("text/html;charset=utf-8"); 
			response.getWriter().write(JSONUtils.toJSONString(map));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
}