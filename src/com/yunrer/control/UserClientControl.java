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
import com.yunrer.entity.UserClient;
import com.yunrer.service.UserClientService;

/**
 * 专属客服管理
 * @author zhanglei
 */
@Controller
@RequestMapping("/user_client")
@SuppressWarnings("unchecked")
public class UserClientControl {

	@Resource
	private UserClientService userClientService;
	
	/**
	 * 新增客服人员
	 * @Title: addUserClient 
	 * @Description:
	 * @param request
	 * @return ProcessResult         
	 * @throws
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public ProcessResult addUserClient(HttpServletRequest request) {
		return userClientService.addUserClient(request);
	}
	
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
		return userClientService.deleteUserClient(request);
	}
	
	/**
	 * 更新客服人员
	 * @Title: modifyUserClient 
	 * @Description:
	 * @param request
	 * @return ProcessResult         
	 * @throws
	 */
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	@ResponseBody
	public ProcessResult modifyUserClient(HttpServletRequest request) {
		return userClientService.modifyUserClient(request);
	}

	/**
	 * 根据客服id查询客服人员
	 * @Title: queryUserClient 
	 * @Description:
	 * @param request
	 * @return UserClient         
	 * @throws
	 */
	@RequestMapping(value = "/query", method = RequestMethod.POST)
	@ResponseBody
	public UserClient queryUserClient(HttpServletRequest request) {
		return userClientService.queryUserClient(request);
	}
	
	/**
	 * 根据用户查询客服人员
	 * @Title: queryUserClient2 
	 * @Description:
	 * @param request
	 * @return UserClient         
	 * @throws
	 */
	@RequestMapping(value = "/query2", method = RequestMethod.POST)
	@ResponseBody
	public UserClient queryUserClient2(HttpServletRequest request) {
		return userClientService.queryUserClient2(request);
	}
	
	/**
	 * 查询客服人员数量
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
		map.put("count", userClientService.queryUserClientCount(request));
		return map;
	}
	
	/**
	 * 查询客服人员列表
	 * @Title: queryUserClientList 
	 * @Description:
	 * @param request
	 * @return List<UserClient>         
	 * @throws
	 */
	@RequestMapping(value = "/queryList", method = RequestMethod.POST)
	@ResponseBody
	public List<UserClient> queryUserClientList(HttpServletRequest request) {
		List<UserClient> list = userClientService.queryUserClientList(request);
		return list;
	}
	
}