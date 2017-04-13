package com.yunrer.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunrer.common.ProcessResult;
import com.yunrer.entity.UserAddress;
import com.yunrer.entity.UserCompany;
import com.yunrer.entity.UserCompanyImg;
import com.yunrer.entity.UserInfo;
import com.yunrer.entity.UserInvoices;
import com.yunrer.service.UserAddressService;
import com.yunrer.service.UserCompanyService;
import com.yunrer.service.UserInfoService;
import com.yunrer.service.UserInvoicesService;

/**
 * 用户管理
 * @author wangpeng
 */
@Controller
@RequestMapping("/user")
@SuppressWarnings("unchecked")
public class UserInfoControl {
	
	@Resource
	private UserInfoService userInfoService;
	
	@Resource
	private UserCompanyService userCompanyService;
	
	@Resource
	private UserAddressService userAddressService;
	
	@Resource
	private UserInvoicesService userInvoicesService;
	
	/**
	 * 查询用户详细信息
	 * @Title: queryUserInfo 
	 * @Description:
	 * @param request
	 * @return UserInfo         
	 * @throws
	 */
	@RequestMapping(value = "/query", method = RequestMethod.POST)
	@ResponseBody
	public UserInfo queryUserInfo(HttpServletRequest request) {
		
		return userInfoService.queryUserInfo(request);
		
		
	}
	
	/**
	 * 获取用户的公司信息
	 * @Title: queryCompany 
	 * @Description:
	 * @param request
	 * @return UserCompany         
	 * @throws
	 */
	@RequestMapping(value = "/queryCompany", method = RequestMethod.POST)
	@ResponseBody
	public UserCompany queryCompany(HttpServletRequest request) {
		return userCompanyService.queryCompany(request);
	}
	
	/**
	 * 获取用户的公司资质
	 * @Title: queryCompanyImgList 
	 * @Description:
	 * @param request
	 * @return List<UserCompanyImg>         
	 * @throws
	 */
	@RequestMapping(value = "/queryCompanyImgList", method = RequestMethod.POST)
	@ResponseBody
	public List<UserCompanyImg> queryCompanyImgList(HttpServletRequest request) {
		return userCompanyService.queryCompanyImgList(request);
	}

	/**
	 * 获取用户的收货地址信息
	 * @Title: queryAddress 
	 * @Description:
	 * @param request
	 * @return List<UserAddress>         
	 * @throws
	 */
	@RequestMapping(value = "/queryAddress", method = RequestMethod.POST)
	@ResponseBody
	public List<UserAddress> queryAddress(HttpServletRequest request) {
		return userAddressService.queryAddressList(request);
	}
	
	/**
	 * 获取用户的发票信息
	 * @Title: queryInvoices 
	 * @Description:
	 * @param request
	 * @return UserInvoices         
	 * @throws
	 */
	@RequestMapping(value = "/queryInvoices", method = RequestMethod.POST)
	@ResponseBody
	public UserInvoices queryInvoices(HttpServletRequest request) {
		return userInvoicesService.queryInvoices(request);
	}
	
	/**
	 * 更新用户状态
	 * @Title: modifyUserInfo 
	 * @Description:
	 * @param request
	 * @return ProcessResult         
	 * @throws
	 */
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	@ResponseBody
	public ProcessResult modifyUserInfo(HttpServletRequest request) {
		return userInfoService.modifyUserInfo(request);
	}
	
	/**
	 * 更新用户状态
	 * @Title: modifyUserInfo 
	 * @Description:
	 * @param request
	 * @return ProcessResult         
	 * @throws
	 */
	@RequestMapping(value = "/operate_sepcial_code", method = RequestMethod.POST)
	@ResponseBody
	public ProcessResult operateSepcialCode(HttpServletRequest request,UserInfo userinfo) {
		return userInfoService.operateUserInfo(request, userinfo);
	}
	
	/**
	 * 更新用户专属客服
	 * @Title: modifyUserInfo2 
	 * @Description:
	 * @param request
	 * @return ProcessResult         
	 * @throws
	 */
	@RequestMapping(value = "/modify2", method = RequestMethod.POST)
	@ResponseBody
	public ProcessResult modifyUserInfo2(HttpServletRequest request) {
		return userInfoService.modifyUserInfo2(request);
	}
	
	/**
	 * 更新用户专属销售
	 * @Title: modifyUserInfo3 
	 * @Description:
	 * @param request
	 * @return ProcessResult         
	 * @throws
	 */
	@RequestMapping(value = "/modify3", method = RequestMethod.POST)
	@ResponseBody
	public ProcessResult modifyUserInfo3(HttpServletRequest request) {
		return userInfoService.modifyUserInfo3(request);
	}
	
	/**
	 * 导出用户信息的excel
	 * @Title: exportUserInfo 
	 * @Description:
	 * @param request
	 * @return String         
	 * @throws
	 */
	@RequestMapping(value = "/export", method = RequestMethod.GET)
	public String exportUserInfo(HttpServletRequest request) {
		String fileName = "/";
		try {
			fileName = userInfoService.exportUserInfo(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:" + fileName;
	}
	
	/**
	 * 查询用户数量
	 * @Title: queryUserInfoCount 
	 * @Description:
	 * @param request
	 * @return Map         
	 * @throws
	 */
	@RequestMapping(value = "/queryCount", method = RequestMethod.POST)
	@ResponseBody
	public Map queryUserInfoCount(HttpServletRequest request) {
		Map map = new HashMap();
		map.put("count", userInfoService.queryUserInfoCount(request));
		return map;
	}
	
	/**
	 * 查询用户列表
	 * @Title: queryUserInfoList 
	 * @Description:
	 * @param request
	 * @return List<UserInfo>         
	 * @throws
	 */
	@RequestMapping(value = "/queryList", method = RequestMethod.POST)
	@ResponseBody
	public List<UserInfo> queryUserInfoList(HttpServletRequest request) {
		List<UserInfo> list = userInfoService.queryUserInfoList(request);
		return list;
	}
	

	/**
	 * 查询公司列表信息
	 * @Title: queryCompanyList 
	 * @Description:
	 * @param request
	 * @return Map<String,Object>         
	 * @throws
	 */
	@RequestMapping(value = "/querycompanyList",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> queryCompanyList(HttpServletRequest request) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			List<UserCompany> list = userCompanyService.queryCompanyList(request);
				if(list!=null && list.size()>0){//保存成功
					resultMap.put("rescode", 1);
					resultMap.put("list", list);
				}else {
					resultMap.put("rescode", 0);
					resultMap.put("resMsg", "公司不存在");
				}
			
		}catch(Exception e){
			resultMap.put("rescode", 0);
			resultMap.put("resMsg", "操作出现错误...");
		}
		return resultMap;
	}
	
	
	/**
	 * 查询公司数量
	 * @Title: queryCount 
	 * @Description:
	 * @param request
	 * @return Map         
	 * @throws
	 */
	@RequestMapping(value = "/querycompanyCount", method = RequestMethod.POST)
	@ResponseBody
	public Map queryCount(HttpServletRequest request) {
		Map map = new HashMap();
		map.put("count", userCompanyService.querycompanyCount(request));
		return map;
	}
	
	/**
	 * 跳转到账户信息-公司信息页
	 * @Title: toUserInfoCompany 
	 * @Description:
	 * @param session
	 * @param request
	 * @return String         
	 * @throws
	 */
	@RequestMapping(value = "/member_info_company",method = RequestMethod.GET)
	public String toUserInfoCompany(HttpSession session,HttpServletRequest request) {
		userCompanyService.queryCompany(session,request);
		return "member_info_company";
	}
	
	/**
	 * 保存用户公司信息
	 * @Title: dosaveCompany 
	 * @Description:
	 * @param session
	 * @param request
	 * @param userCompany
	 * @return Map<String,Object>         
	 * @throws
	 */
	@RequestMapping(value = "/savecompany",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> dosaveCompany(HttpSession session,HttpServletRequest request,UserCompany userCompany) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			
				resultMap =userCompanyService.saveCompany(userCompany);
		}catch(Exception e){
			resultMap.put("error", true);
			resultMap.put("message", "操作出现错误...");
		}
		return resultMap;
	}
	
	/**
	 * 查询内购公司
	 * @Title: querySpecialCompany 
	 * @Description:
	 * @param request
	 * @return List<UserCompany>         
	 * @throws
	 */
	@RequestMapping(value = "/querySpecialCompany", method = RequestMethod.POST)
	@ResponseBody
	public List<UserCompany> querySpecialCompany(HttpServletRequest request) {
		return userCompanyService.querySpecialCompany(request);
	}
	
	/**
	 * 修改用户公司信息
	 * @Title: dosaveCompany 
	 * @Description:
	 * @param session
	 * @param request
	 * @param userCompany
	 * @return Map<String,Object>         
	 * @throws
	 */
	@RequestMapping(value = "/modifycompany",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> domodifyCompany(HttpSession session,HttpServletRequest request,UserCompany userCompany) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			
				resultMap =userCompanyService.modifyCompany(userCompany);
		}catch(Exception e){
			resultMap.put("error", true);
			resultMap.put("message", "操作出现错误...");
		}
		return resultMap;
	}
	
	/**
	 * 导出公司信息excel
	 * @Title: exportCompanyInfo 
	 * @Description:
	 * @param request
	 * @param response void         
	 * @throws
	 */
	@RequestMapping(value = "/exportCompany", method = RequestMethod.GET)
	public void exportCompanyInfo(HttpServletRequest request,HttpServletResponse response) {
		String fileName = "/";
		try {
			fileName = userCompanyService.exportCompanyInfo(request,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}