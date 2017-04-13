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
import com.yunrer.entity.MaintenanceStation;
import com.yunrer.service.DmbService;
import com.yunrer.service.MaintenanceStationService;

/**
 * 维修站管理
 * @author zhanglei
 */
@Controller
@RequestMapping("/maintenance_station")
@SuppressWarnings("unchecked")
public class MaintenanceStationControl {

	@Resource
	private MaintenanceStationService maintenanceStationService;
	
	@Resource
	private DmbService dmbService;
	
	/**
	 * 新增维修站
	 * @Title: addMaintenanceStation 
	 * @Description:
	 * @param request
	 * @return ProcessResult         
	 * @throws
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public ProcessResult addMaintenanceStation(HttpServletRequest request) {
		return maintenanceStationService.addMaintenanceStation(request);
	}
	
	/**
	 * 删除维修站
	 * @Title: deleteMaintenanceStation 
	 * @Description:
	 * @param request
	 * @return ProcessResult         
	 * @throws
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public ProcessResult deleteMaintenanceStation(HttpServletRequest request) {
		return maintenanceStationService.deleteMaintenanceStation(request);
	}
	
	/**
	 * 更新维修站
	 * @Title: modifyMaintenanceStation 
	 * @Description:
	 * @param request
	 * @return ProcessResult         
	 * @throws
	 */
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	@ResponseBody
	public ProcessResult modifyMaintenanceStation(HttpServletRequest request) {
		return maintenanceStationService.modifyMaintenanceStation(request);
	}

	/**
	 * 查询维修站
	 * @Title: queryMaintenanceStation 
	 * @Description:
	 * @param request
	 * @return MaintenanceStation         
	 * @throws
	 */
	@RequestMapping(value = "/query", method = RequestMethod.POST)
	@ResponseBody
	public MaintenanceStation queryMaintenanceStation(HttpServletRequest request) {
		return maintenanceStationService.queryMaintenanceStation(request);
	}
	
	/**
	 * 查询维修站数量
	 * @Title: queryMaintenanceStationCount 
	 * @Description:
	 * @param request
	 * @return Map         
	 * @throws
	 */
	@RequestMapping(value = "/queryCount", method = RequestMethod.POST)
	@ResponseBody
	public Map queryMaintenanceStationCount(HttpServletRequest request) {
		Map map = new HashMap();
		map.put("count", maintenanceStationService.queryMaintenanceStationCount(request));
		return map;
	}
	
	/**
	 * 查询维修站列表
	 * @Title: queryMaintenanceStationList 
	 * @Description:
	 * @param request
	 * @return List<MaintenanceStation>         
	 * @throws
	 */
	@RequestMapping(value = "/queryList", method = RequestMethod.POST)
	@ResponseBody
	public List<MaintenanceStation> queryMaintenanceStationList(HttpServletRequest request) {
		List<MaintenanceStation> list = maintenanceStationService.queryMaintenanceStationList(request);
		return list;
	}
	
	/**
	 * 查询维修站类型列表
	 * @Title: queryMaintenanceStationTypeList 
	 * @Description:
	 * @param request
	 * @return List<Dmb>         
	 * @throws
	 */
	@RequestMapping(value = "/queryListBySelect", method = RequestMethod.POST)
	@ResponseBody
	public List<Dmb> queryMaintenanceStationTypeList(HttpServletRequest request) {
		List<Dmb> list = dmbService.queryDmbList(3);
		return list;
	}
}