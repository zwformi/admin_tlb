package com.yunrer.control;

import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.support.json.JSONUtils;
import com.yunrer.common.HTTPUtils;
import com.yunrer.common.ProcessResult;
import com.yunrer.dao.RoleNodeDao;
import com.yunrer.dao.SystemUserDao;
import com.yunrer.entity.SystemNode;
import com.yunrer.entity.SystemUser;
import com.yunrer.entity.UserInfo;
import com.yunrer.service.SystemUserService;
import com.yunrer.util.ConnectionUtil;
import com.yunrer.util.XmlUtil;

/**
 * 用户登录
 * @author zhanglei
 */
@Controller
@RequestMapping("/login")
public class LoginControl {

	@Resource
	private SystemUserService systemUserService;
	
	@Resource
	private RoleNodeDao roleNodeDao;
	
	@Resource
	private SystemUserDao systemUserDao;
	
	/**
	 * sso登录
	 * @Title: toLogin 
	 * @Description:
	 * @param session
	 * @param request
	 * @return String         
	 * @throws
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String toLogin(HttpSession session,HttpServletRequest request) {
		String requestUrl="";
		if(session.getAttribute("loginUser")!=null){
			return "redirect:/jsp/index.jsp";//如果已登录跳转到主页面
		}else{
			ModelMap mmap = new ModelMap();
			try {
				
				Map map = XmlUtil.paserXmlByDOM4J("\\resources\\common.xml");
				System.out.println(map);
				UUID uuid = UUID.randomUUID();
				session.setAttribute("tmpToken", uuid.toString());
				String returnUrl = map.get("returnUrl").toString();
			    requestUrl = map.get("path")+(map.get("port")==null?"":":"+map.get("port")+"/"+map.get("ssoInterface"));
			    requestUrl += "?tmpToken="+ uuid.toString();
			    requestUrl += "&returnUrl=http://"+ returnUrl;
			    ConnectionUtil.postRequest("http://"+map.get("path")+(map.get("port")==null?"":":"+map.get("port")+"/"+map.get("checkAliveInterface")), null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				
				System.out.println(e.getMessage());
				return "login";
				///return "redirect:/jsp/index.jsp";
			}
			//return "redirect://"+requestUrl;
			
			return "redirect:/jsp/index.jsp";//如果已登录跳转到主页面
			
		}
	}
	
	/**
	 * 登录操作
	 * @Title: doLogin 
	 * @Description:
	 * @param request
	 * @param response
	 * @param session
	 * @return ProcessResult         
	 * @throws
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ProcessResult doLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		SystemUser user = new SystemUser();
		user.setUser_name(request.getParameter("userName"));
		user.setUser_password(request.getParameter("userPassword"));
		ProcessResult pr = systemUserService.userLogin(user, request, session);
		return pr;
	}
	
	/**
	 * 登录回调
	 * @Title: callBack 
	 * @Description:
	 * @param request
	 * @param response
	 * @param session void         
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/callback",method=RequestMethod.GET)
	public void  callBack(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		String authedtoken = request.getParameter("authedtoken");
		String tmptoken = request.getParameter("tmptoken");
		String requestUrl="";
		Map<String,String> paramsMap;
		String result="";
		try {
			Map map = XmlUtil.paserXmlByDOM4J("\\resources\\common.xml");
			requestUrl = "http://"+map.get("path")+(map.get("port")==null?"":":"+map.get("port")+"/"+map.get("CheckAuthedToken"));
			paramsMap = new HashMap<String, String>();
			paramsMap.put("authedtoken", authedtoken);
			paramsMap.put("tmptoken", tmptoken);
			result = ConnectionUtil.postRequest(requestUrl, paramsMap);
			Map<String,Object> resMap  = (Map<String,Object>)JSONUtils.parse(result);
			if((Boolean)resMap.get("ValidationState"))
			{
				String sso_id = resMap.get("SystemUserId").toString();
				SystemUser uinfo = systemUserService.queryUserInfoBySsoId(sso_id.toLowerCase());
				if(uinfo!=null){
					String return_url = session.getAttribute("requestUrl")==null?"/":session.getAttribute("requestUrl").toString();
					session.setAttribute("loginUser", uinfo);
					
					// 查询用户权限
					Map<String, Boolean> authMap = new HashMap<String, Boolean>();
					List<SystemNode> auth_list = roleNodeDao.queryRoleAuth(uinfo.getRole_id());
					for (SystemNode sn : auth_list) {
						if (sn.getKey_name() != null && !"".equals(sn.getKey_name())) {
							authMap.put(sn.getKey_name(), true);
						}
					}
					session.setAttribute("authMap", authMap);
					// 更新用户信息
					// 登录IP，登录次数，登录时间
					SystemUser changeUser = new SystemUser();
					changeUser.setId(uinfo.getId());
					String login_ip = HTTPUtils.getIpAddr(request);
					changeUser.setLogin_ip(login_ip);
					changeUser.setLogin_times(uinfo.getLogin_times() + 1);
					systemUserDao.modifyLoginInfo(changeUser);
					response.sendRedirect(return_url);
				}else{					
					UUID uuid = UUID.randomUUID();
					session.setAttribute("tmpToken", uuid.toString());
//					String returnUrl = URLEncoder.encode(request.getLocalAddr()+":"+request.getLocalPort()+"/login/callback.json","utf-8");
					String returnUrl = map.get("returnUrl").toString();
				    requestUrl ="http://" + map.get("path")+(map.get("port")==null?"":":"+map.get("port")+"/"+map.get("ssoInterface"));
				    requestUrl += "?tmpToken="+ uuid.toString();
				    requestUrl += "&returnUrl=http://"+ returnUrl;
				    requestUrl += "&logout=1";
				    PrintWriter pw = response.getWriter();
				    pw.println("<!DOCTYPE html>");
				    pw.println("<html>");
				    pw.println("<head>");
				    pw.println("<title>页面跳转中...</title>");
				    pw.println("<meta charset=\"UTF-8\">");
				    pw.println("</head>");
				    pw.println("<body>");
				    pw.println("<script src=\"../js/jquery.min.js\"></script>");
				    pw.println("<script src=\"../js/layer/layer.js\"></script>");
				    pw.println("<script>");
				    pw.println("layer.msg('您的账号尚未在图灵买后台注册,即将跳转...',{time: 1000,shade:[1,'#09c']},function(){window.location.href='"+requestUrl+"'})");
				    pw.println("</script>");
				    pw.println("</body>");
				    pw.println("</html>");
					pw.flush();
					pw.close();
				}
			
				
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
	
	/**
	 * 退出登录操作
	 * @Title: doLoginOut 
	 * @Description:
	 * @param session
	 * @param request
	 * @return String         
	 * @throws
	 */
	@RequestMapping(value = "/out", method = RequestMethod.GET)
	public String doLoginOut(HttpSession session,HttpServletRequest request) {
		String requestUrl="";
		if(session.getAttribute("loginUser")!=null){
			session.removeAttribute("loginUser");
		}
		ModelMap mmap = new ModelMap();
		try {
			
			Map map = XmlUtil.paserXmlByDOM4J("\\resources\\common.xml");
			System.out.println(map);
			UUID uuid = UUID.randomUUID();
			session.setAttribute("tmpToken", uuid.toString());
//			String returnUrl = URLEncoder.encode(request.getLocalAddr()+":"+request.getLocalPort()+"/login/callback.json","utf-8");
			String returnUrl = map.get("returnUrl").toString();
		    requestUrl = map.get("path")+(map.get("port")==null?"":":"+map.get("port")+"/"+map.get("ssoInterface"));
		    requestUrl += "?tmpToken="+ uuid.toString();
		    requestUrl += "&returnUrl=http://"+ returnUrl;
		    requestUrl += "&logout=1";
		    System.out.println(requestUrl);
		    ConnectionUtil.postRequest("http://"+map.get("path")+(map.get("port")==null?"":":"+map.get("port")+"/"+map.get("checkAliveInterface")), null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "redirect:/";
		}	
		return "redirect://"+requestUrl;
	}
	
	/**
	 * sso退出登录操作
	 * @Title: loginOut 
	 * @Description:
	 * @param session
	 * @return String         
	 * @throws
	 */
	@RequestMapping(value = "/ssoout", method = RequestMethod.GET)
	public String loginOut(HttpSession session) {
		session.removeAttribute("loginUser");
		return null;
	}
}