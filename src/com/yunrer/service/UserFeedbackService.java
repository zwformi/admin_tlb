package com.yunrer.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.yunrer.common.ProcessResult;
import com.yunrer.dao.UserFeedbackDao;
import com.yunrer.entity.UserFeedback;
/**
 * 留言Service
 * @ClassName UserFeedbackService
 * @Description 
 * @author rujun
 * @date 2016-12-21
 */
@Service("UserFeedbackService")
public class UserFeedbackService {

	@Resource
	private UserFeedbackDao userFeedbackDao;
	
	/**
	 * 删除留言
	 * @Title: deleteUserFeedback 
	 * @Description:
	 * @param request
	 * @return ProcessResult         
	 * @throws
	 */
	public ProcessResult deleteUserFeedback(HttpServletRequest request) {
		ProcessResult pr = new ProcessResult();
		try{
			String id = request.getParameter("id");
			Object[] ids = id.split(",");
			
			userFeedbackDao.deleteUserFeedback(ids);
			
			pr.setSuccess(true);
			pr.setMessage("删除成功");
		} catch (Exception ex) {
			pr.setSuccess(false);
			pr.setMessage(ex.getMessage());
		}
		return pr;
	}

	/**
	 * 查询留言数量
	 * @Title: queryUserFeedbackCount 
	 * @Description:
	 * @param request
	 * @return int         
	 * @throws
	 */
	public int queryUserFeedbackCount(HttpServletRequest request) {
		int count = 0;
		try{
			String keyword = request.getParameter("keyword");
			count = userFeedbackDao.queryUserFeedbackCount(keyword);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return count;
	}
	
	/**
	 * 查询客服人员列表
	 * @Title: queryUserFeedbackList 
	 * @Description:
	 * @param request
	 * @return List<UserFeedback>         
	 * @throws
	 */
	public List<UserFeedback> queryUserFeedbackList(HttpServletRequest request) {
		List<UserFeedback> list = null;
		try{
			String keyword = request.getParameter("keyword");
			int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
			int pageSize = Integer.parseInt(request.getParameter("pageSize"));
			list = userFeedbackDao.queryUserFeedbackList(keyword, pageIndex, pageSize);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
	
}