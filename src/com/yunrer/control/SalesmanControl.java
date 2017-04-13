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
import com.yunrer.entity.Salesman;
import com.yunrer.service.SalesmanService;

/**
 * 销售人员管理
 * @author zhanglei
 */
@Controller
@RequestMapping("/salesman")
@SuppressWarnings("unchecked")
public class SalesmanControl {

	@Resource
	private SalesmanService salesmanService;
	
	/**
	 * 新增销售人员
	 * @Title: addSalesman 
	 * @Description:
	 * @param request
	 * @return ProcessResult         
	 * @throws
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public ProcessResult addSalesman(HttpServletRequest request) {
		return salesmanService.addSalesman(request);
	}
	
	/**
	 * 删除销售人员
	 * @Title: deleteSalesman 
	 * @Description:
	 * @param request
	 * @return ProcessResult         
	 * @throws
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public ProcessResult deleteSalesman(HttpServletRequest request) {
		return salesmanService.deleteSalesman(request);
	}
	
	/**
	 * 更新销售人员
	 * @Title: modifySalesman 
	 * @Description:
	 * @param request
	 * @return ProcessResult         
	 * @throws
	 */
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	@ResponseBody
	public ProcessResult modifySalesman(HttpServletRequest request) {
		return salesmanService.modifySalesman(request);
	}

	/**
	 * 根据id查询销售人员
	 * @Title: querySalesman 
	 * @Description:
	 * @param request
	 * @return Salesman         
	 * @throws
	 */
	@RequestMapping(value = "/query", method = RequestMethod.POST)
	@ResponseBody
	public Salesman querySalesman(HttpServletRequest request) {
		return salesmanService.querySalesman(request);
	}
	
	/**
	 * 根据当前用户查询销售人员
	 * @Title: querySalesman2 
	 * @Description:
	 * @param request
	 * @return Salesman         
	 * @throws
	 */
	@RequestMapping(value = "/query2", method = RequestMethod.POST)
	@ResponseBody
	public Salesman querySalesman2(HttpServletRequest request) {
		return salesmanService.querySalesman2(request);
	}
	
	/**
	 * 查询销售人员数量
	 * @Title: querySalesmanCount 
	 * @Description:
	 * @param request
	 * @return Map         
	 * @throws
	 */
	@RequestMapping(value = "/queryCount", method = RequestMethod.POST)
	@ResponseBody
	public Map querySalesmanCount(HttpServletRequest request) {
		Map map = new HashMap();
		map.put("count", salesmanService.querySalesmanCount(request));
		return map;
	}
	
	/**
	 * 查询销售人员列表
	 * @Title: querySalesmanList 
	 * @Description:
	 * @param request
	 * @return List<Salesman>         
	 * @throws
	 */
	@RequestMapping(value = "/queryList", method = RequestMethod.POST)
	@ResponseBody
	public List<Salesman> querySalesmanList(HttpServletRequest request) {
		List<Salesman> list = salesmanService.querySalesmanList(request);
		return list;
	}
	
}