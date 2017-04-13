package com.yunrer.control;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunrer.service.PostService;

/**
 *  快递信息控制
 * @ClassName PostControl
 * @Description
 * @author zgf
 * @date 2016-11-17
 */

@Controller
@RequestMapping("/postinfo")
public class PostControl {

	@Resource
	private PostService postService;
	
	/**
	 * 获取订单下快递信息
	 * @Title: freshData 
	 * @Description:
	 * @param request
	 * @return List<?>         
	 * @throws
	 */
	@RequestMapping(value="/fresh")
	public @ResponseBody List<?> freshData(HttpServletRequest request){
		
		String order_number = request.getParameter("order_number");
		if(order_number!=null && order_number.length()>0){
			 return postService.getPostData(order_number);
			
		}
		return null;
	}
	
	/**
	 * 验证快递单号
	 * @Title: checkPost 
	 * @Description:
	 * @param request
	 * @return Map<String,Object>         
	 * @throws
	 */
	@RequestMapping(value="/check")
	public @ResponseBody Map<String,Object> checkPost(HttpServletRequest request){
		
		return postService.checkPostNumber(request);
	}
}
