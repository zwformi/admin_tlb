package com.yunrer.AopHandler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;

import com.yunrer.dao.ProductDao;
import com.yunrer.entity.OperateLog;
import com.yunrer.entity.SystemUser;

public class MethodHandler implements MethodInterceptor {

	@Autowired
	private ProductDao productDao;
	
	private List<String> methodList;

	@Override
	public Object invoke(MethodInvocation mi) throws Throwable {
		OperateLog log = null;
		try {
			String methodName = mi.getMethod().getName();
			Object[] args = mi.getArguments();
			HttpServletRequest request = null;
			Object o = null;
			for (Object object : args) {
				if (object instanceof HttpServletRequest) {
					request = (HttpServletRequest) object;
					break;
				}
			}
			if (request != null && methodList.contains(methodName)) {
				log = new OperateLog();
				SystemUser user = (SystemUser) request.getSession().getAttribute("loginUser");
				log.setUserId(user.getId());
				log.setUserName(user.getUser_name());
				log.setOperateDate(new Date());
				o = mi.proceed();
				if (o != null && o instanceof int[] )
					log.setProductId(((int[])o)[1]);
				if ("modifyProduct".equals(methodName)) {
					log.setBehavior("modify");
					log.setProductId(Integer.parseInt(request.getParameter("id")));
				}
				if ("delProduct".equals(methodName)) {
					log.setBehavior("delete");
					log.setProductId(Integer.parseInt(request.getParameter("id")));
				}
				if ("addProduct".equals(methodName)) {
					log.setBehavior("create");
				}
				
				return o;
			}
			return mi.proceed();

		} catch (Exception e) {

			return null;
		}finally{
			//记录操作日志
			if(log != null )
			productDao.addOperateLog(log);
		}

	}

	public void setMethodList(List<String> methodList) {
		this.methodList = methodList;
	}

}
