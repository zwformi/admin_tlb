package com.yunrer.control;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunrer.entity.ProductType;
import com.yunrer.entity.SelectTree;
import com.yunrer.entity.UserInfo;
import com.yunrer.entity.YunType;
import com.yunrer.service.UserCompanyService;
import com.yunrer.service.YunTypeService;

/**
 * 云模块分类
 * @ClassName YunTypeControl
 * @Description TODO 增删改查
 * @author rujun
 * @date 2016-11-24
 */
@Controller
@RequestMapping("/yunType")
public class YunTypeControl {

	@Resource
	private YunTypeService yunTypeService;
	
	/**
	 * 查询列表
	 * @Title: queryTypeList 
	 * @Description:
	 * @param request
	 * @return List<YunType>         
	 * @throws
	 */
	@RequestMapping(value = "/queryList", method = RequestMethod.POST)
	@ResponseBody
	public List<YunType> queryTypeList(YunType yunType,HttpServletRequest request) {
		return yunTypeService.queryYunTypeList(yunType,request);
	}
	
	/**
	 * 查询云模块类别
	 * @Title: queryType 
	 * @Description:
	 * @param request
	 * @return YunType         
	 * @throws
	 */
	@RequestMapping(value = "/query", method = RequestMethod.POST)
	@ResponseBody
	public YunType queryType(HttpServletRequest request) {
		return yunTypeService.queryYunType(request);
	}
	
	/**
	 * 查询列表条数
	 * @Title: queryTypeListCount 
	 * @Description:
	 * @param yunType
	 * @param request
	 * @return int         
	 * @throws
	 */
	@RequestMapping(value = "/queryCount", method = RequestMethod.POST)
	@ResponseBody
	public Map queryTypeListCount(YunType yunType,HttpServletRequest request) {
		return yunTypeService.queryYunTypeListCount(yunType,request);
	}
	
	/**
	 * 添加类别
	 * @Title: addType 
	 * @Description:
	 * @param request
	 * @return int         
	 * @throws
	 */
	@RequestMapping(value = "/addType", method = RequestMethod.POST)
	@ResponseBody
	public Map addType(HttpServletRequest request,YunType yunType) {
		return yunTypeService.addYunType(yunType);
	}

	/**
	 * 修改类别
	 * @Title: editType 
	 * @Description:
	 * @param request
	 * @return int         
	 * @throws
	 */
	@RequestMapping(value = "/editType", method = RequestMethod.POST)
	@ResponseBody
	public Map editType(YunType yunType) {
		return yunTypeService.editYunType(yunType);
	}
	
	/**
	 * 删除类别
	 * @Title: deleteType 
	 * @Description:
	 * @param request
	 * @return int         
	 * @throws
	 */
	@RequestMapping(value = "/deleteType", method = RequestMethod.POST)
	@ResponseBody
	public Map deleteType(HttpServletRequest request) {
		return yunTypeService.deleteYunType(request);
	}
	
	/**
	 * 查询商品类型列表-树形菜单
	 * @Title: queryTypesList 
	 * @Description:
	 * @param request
	 * @return List<SelectTree>         
	 * @throws
	 */
	@RequestMapping(value = "/types", method = RequestMethod.POST)
	@ResponseBody
	public List<SelectTree> queryTypesList(HttpServletRequest request) {
		List<SelectTree> list = yunTypeService.queryYunTypeList(request);
		return list;
	}
}
