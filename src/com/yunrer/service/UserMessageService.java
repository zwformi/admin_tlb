package com.yunrer.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yunrer.common.ProcessResult;
import com.yunrer.common.SmsSenderUtils;
import com.yunrer.dao.UserMessageDao;
import com.yunrer.entity.UserMessage;
/**
 * 用户信息Service
 * @ClassName UserMessageService
 * @Description 
 * @author rujun
 * @date 2016-12-21
 */
@Service("UserMessageService")
public class UserMessageService {

	@Resource
	private UserMessageDao userMessageDao;

	/**
	 * 模板ID
	 * 
	 * 1. 报价，19962
	 * 2. 生成合同，19963
	 * 3. 开始配货，19965
	 * 4. 发货，19966
	 * 5. 分派实施人员，19967
	 * 6. 分派服务人员，19968
	 */
	
	/**
	 * 内容
	 *  
	 * 1. 报价
	 * 		您的需求单{0}已报价，请登录图灵买网站查看详细信息。
	 * 2. 生成合同
	 * 		您的需求单{0}已生成合同，订单合同{1}，请登录图灵买网站查看详细信息。
	 * 3. 开始配货
	 * 		您的订单合同{0}已开始配货，请登录图灵买网站查看详细信息。
	 * 4. 发货
	 * 		您的订单合同{0}已发货，请登录图灵买网站查看详细信息。
	 * 5. 分派实施人员
	 * 		您的订单合同{0}已分派实施人员，请登录图灵买网站查看详细信息。
	 * 6. 分派服务人员
	 * 		您的服务单{0}已分派服务人员，请登录图灵买网站查看详细信息。
	 */
	
//	/**
//	 * 新增用户消息
//	 * @param user_id  用户ID
//	 * @param title  标题
//	 * @param content  内容
//	 * @param mobile  接收的手机号码
//	 * @param param  短信内容
//	 * @param templateId  模板ID
//	 */
//	public ProcessResult addUserMessage(int user_id, String title,
//			String content, String mobile, String param, String templateId) {
//		ProcessResult pr = new ProcessResult();
//		try{
//			//存储至数据库
//			UserMessage um = new UserMessage();
//			um.setUser_id(user_id);
//			um.setTitle(title);
//			um.setContent(content);
//			userMessageDao.addUserMessage(um);
//			//判断手机号是否为合法的11位,若不合法不发送短信
//			Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");  
//
//			Matcher m = p.matcher(mobile);  
//			if(!m.matches()){ 
//				
//				System.out.println("手机号"+mobile+"不合法！短信发送失败!");
//			}else{
//			//发送通知短信
//			SmsSenderUtils.sendSms(mobile, param, templateId);
//			
//			}
//			pr.setSuccess(true);
//			pr.setMessage("保存成功");
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			pr.setSuccess(false);
//			pr.setMessage(ex.getMessage());
//		}
//		return pr;
//	}
}