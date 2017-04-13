package com.yunrer.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.yunrer.common.ProcessResult;
import com.yunrer.dao.MaintenanceStationDao;
import com.yunrer.entity.MaintenanceStation;
/**
 * 维修站Service
 * @ClassName MaintenanceStationService
 * @Description 
 * @author rujun
 * @date 2016-12-19
 */
@Service("MaintenanceStationService")
public class MaintenanceStationService {
	
	@Resource
	private MaintenanceStationDao maintenanceStationDao;
	
	/**
	 * 新增维修站
	 * @Title: addMaintenanceStation 
	 * @Description:
	 * @param request
	 * @return ProcessResult         
	 * @throws
	 */
	public ProcessResult addMaintenanceStation(HttpServletRequest request) {
		ProcessResult pr = new ProcessResult();
		try{
			MaintenanceStation ms = new MaintenanceStation();
			ms.setName(request.getParameter("name"));
			ms.setAddress(request.getParameter("address"));
			ms.setLxr(request.getParameter("lxr"));
			ms.setPhone(request.getParameter("phone"));
			ms.setSort_id(Integer.parseInt(request.getParameter("sort_id")));
			ms.setType_id(Integer.parseInt(request.getParameter("type_id")));
			
			maintenanceStationDao.addMaintenanceStation(ms);
			
			pr.setSuccess(true);
			pr.setMessage("保存成功");
		} catch (Exception ex) {
			ex.printStackTrace();
			pr.setSuccess(false);
			pr.setMessage(ex.getMessage());
		}
		return pr;
	}
	
	/**
	 * 删除维修站
	 * @Title: deleteMaintenanceStation 
	 * @Description:
	 * @param request
	 * @return ProcessResult         
	 * @throws
	 */
	public ProcessResult deleteMaintenanceStation(HttpServletRequest request) {
		ProcessResult pr = new ProcessResult();
		try{
			String id = request.getParameter("id");
			Object[] ids = id.split(",");
			
			maintenanceStationDao.deleteMaintenanceStation(ids);
			
			pr.setSuccess(true);
			pr.setMessage("删除成功");
		} catch (Exception ex) {
			pr.setSuccess(false);
			pr.setMessage(ex.getMessage());
		}
		return pr;
	}
	
	/**
	 * 更新维修站
	 * @Title: modifyMaintenanceStation 
	 * @Description:
	 * @param request
	 * @return ProcessResult         
	 * @throws
	 */
	public ProcessResult modifyMaintenanceStation(HttpServletRequest request) {
		ProcessResult pr = new ProcessResult();
		try{
			MaintenanceStation ms = new MaintenanceStation();
			ms.setId(Integer.parseInt(request.getParameter("id")));
			ms.setName(request.getParameter("name"));
			ms.setAddress(request.getParameter("address"));
			ms.setLxr(request.getParameter("lxr"));
			ms.setPhone(request.getParameter("phone"));
			ms.setSort_id(Integer.parseInt(request.getParameter("sort_id")));
			ms.setType_id(Integer.parseInt(request.getParameter("type_id")));
			
			maintenanceStationDao.modifyMaintenanceStation(ms);
			
			pr.setSuccess(true);
			pr.setMessage("保存成功");
		} catch (Exception ex) {
			pr.setSuccess(false);
			pr.setMessage(ex.getMessage());
		}
		return pr;
	}
	
	/**
	 * 查询维修站
	 * @Title: queryMaintenanceStation 
	 * @Description:
	 * @param request
	 * @return MaintenanceStation         
	 * @throws
	 */
	public MaintenanceStation queryMaintenanceStation(HttpServletRequest request) {
		MaintenanceStation ms = null;
		try{
			int id = Integer.parseInt(request.getParameter("id"));
			ms = maintenanceStationDao.queryMaintenanceStation(id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ms;
	}
	
	/**
	 * 查询维修站数量
	 * @Title: queryMaintenanceStationCount 
	 * @Description:
	 * @param request
	 * @return int         
	 * @throws
	 */
	public int queryMaintenanceStationCount(HttpServletRequest request) {
		int count = 0;
		try{
			String keyword = request.getParameter("keyword");
			count = maintenanceStationDao.queryMaintenanceStationCount(keyword);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return count;
	}
	
	/**
	 * 查询维修站列表
	 * @Title: queryMaintenanceStationList 
	 * @Description:
	 * @param request
	 * @return List<MaintenanceStation>         
	 * @throws
	 */
	public List<MaintenanceStation> queryMaintenanceStationList(HttpServletRequest request) {
		List<MaintenanceStation> list = null;
		try{
			String keyword = request.getParameter("keyword");
			int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
			int pageSize = Integer.parseInt(request.getParameter("pageSize"));
			list = maintenanceStationDao.queryMaintenanceStationList(keyword, pageIndex, pageSize);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
}