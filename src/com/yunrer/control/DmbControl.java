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
import com.yunrer.entity.Dmb;
import com.yunrer.service.DmbService;

/**
 * 字典管理
 * @author zhanglei
 */
@Controller
@RequestMapping("/dmb")
@SuppressWarnings("unchecked")
public class DmbControl {

	@Resource
	private DmbService dmbService;
	
	/**
	 * 新增字典
	 * @Title: addDmb 
	 * @Description:
	 * @param request
	 * @return ProcessResult         
	 * @throws
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public ProcessResult addDmb(HttpServletRequest request) {
		return dmbService.addDmb(request);
	}
	
	/**
	 * 删除字典
	 * @Title: deleteDmb 
	 * @Description:
	 * @param request
	 * @return ProcessResult         
	 * @throws
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public ProcessResult deleteDmb(HttpServletRequest request) {
		return dmbService.deleteDmb(request);
	}
	
	/**
	 * 更新字典
	 * @Title: modifyDmb 
	 * @Description:
	 * @param request
	 * @return ProcessResult         
	 * @throws
	 */
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	@ResponseBody
	public ProcessResult modifyDmb(HttpServletRequest request) {
		return dmbService.modifyDmb(request);
	}

	/**
	 * 查询字典
	 * @Title: queryDmb 
	 * @Description:
	 * @param request
	 * @return Dmb         
	 * @throws
	 */
	@RequestMapping(value = "/query", method = RequestMethod.POST)
	@ResponseBody
	public Dmb queryDmb(HttpServletRequest request) {
		return dmbService.queryDmb(request);
	}
	
	/**
	 * 查询字典数量
	 * @Title: queryDmbCount 
	 * @Description:
	 * @param request
	 * @return Map         
	 * @throws
	 */
	@RequestMapping(value = "/queryCount", method = RequestMethod.POST)
	@ResponseBody
	public Map queryDmbCount(HttpServletRequest request) {
		Map map = new HashMap();
		map.put("count", dmbService.queryDmbCount(request));
		return map;
	}
	
	/**
	 * 查询字典列表
	 * @Title: queryDmbList 
	 * @Description:
	 * @param request
	 * @return List<Dmb>         
	 * @throws
	 */
	@RequestMapping(value = "/queryList", method = RequestMethod.POST)
	@ResponseBody
	public List<Dmb> queryDmbList(HttpServletRequest request) {
		List<Dmb> list = dmbService.queryDmbList(request);
		return list;
	}
	
}