package com.yunrer.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunrer.common.ProcessResult;
import com.yunrer.entity.UserFeedback;
import com.yunrer.service.UserFeedbackService;

/**
 * 用户留言管理
 * @author wangpeng
 */
@Controller
@RequestMapping("/user_feedback")
@SuppressWarnings("unchecked")
public class UserFeedbackControl {

	@Resource
	private UserFeedbackService userFeedbackService;
	
	/**
	 * 删除客服人员
	 * @Title: deleteUserClient 
	 * @Description:
	 * @param request
	 * @return ProcessResult         
	 * @throws
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public ProcessResult deleteUserClient(HttpServletRequest request) {
		return userFeedbackService.deleteUserFeedback(request);
	}
	
	/**
	 * 查询用户留言数量
	 * @Title: queryUserClientCount 
	 * @Description:
	 * @param request
	 * @return Map         
	 * @throws
	 */
	@RequestMapping(value = "/queryCount", method = RequestMethod.POST)
	@ResponseBody
	public Map queryUserClientCount(HttpServletRequest request) {
		Map map = new HashMap();
		map.put("count", userFeedbackService.queryUserFeedbackCount(request));
		return map;
	}
	
	/**
	 * 查询用户留言列表
	 * @Title: queryUserClientList 
	 * @Description:
	 * @param request
	 * @return List<UserFeedback>         
	 * @throws
	 */
	@RequestMapping(value = "/queryList", method = RequestMethod.POST)
	@ResponseBody
	public List<UserFeedback> queryUserClientList(HttpServletRequest request) {
		List<UserFeedback> list = userFeedbackService.queryUserFeedbackList(request);
		return list;
	}
	
}