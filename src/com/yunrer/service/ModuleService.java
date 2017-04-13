package com.yunrer.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yunrer.dao.ModuleDao;
import com.yunrer.entity.YunModule;


/**
 * 云模块Service
 * @ClassName ModuleService
 * @Description 
 * @author rujun
 * @date 2016-11-28
 */
@Service("ModuleService")
public class ModuleService {
	
	@Resource
	private ModuleDao moduleDao;

	/**
	 * 查询模块数量
	 * @Title: queryModuleCount 
	 * @Description:
	 * @param request
	 * @param session
	 * @return int         
	 * @throws
	 */
	public int queryModuleCount(HttpServletRequest request,YunModule module) {
		int count = 0;
		try{
				count = moduleDao.queryModulecount(module);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return count;
	}
	
	/**
	 * 查询模块列表
	 * @Title: queryModuleList 
	 * @Description:
	 * @param request
	 * @param session
	 * @return List<YunModule>         
	 * @throws
	 */
	public List<YunModule> queryModuleList(HttpServletRequest request,YunModule module) {
		List<YunModule> list = null;
		try{
	
			int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
			int pageSize = Integer.parseInt(request.getParameter("pageSize"));
				list = moduleDao.queryModuleList(module, pageIndex, pageSize);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 云模块详情页
	 * @Title: queryModuleDetails 
	 * @Description:
	 * @param request
	 * @param session
	 * @return YunModule         
	 * @throws
	 */
	public YunModule queryModuleDetails(HttpServletRequest request){
		int id = request.getParameter("module_id")==null?0:Integer.parseInt(request.getParameter("module_id"));
		YunModule module = moduleDao.queryModule_Detail(id);
		
		//订单确认页
		return module;
	}
	
	/**
	 * 云模块修改
	 * @Title: updateModule 
	 * @Description:
	 * @param module
	 * @return int         
	 * @throws
	 */
	@Transactional
	public int updateModule(YunModule module) {
		int count = 0;
		try{

				count = moduleDao.updateModule(module);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return count;
	}
	
	/**
	 * 云模块添加
	 * @Title: addModule 
	 * @Description:
	 * @param module
	 * @return int         
	 * @throws
	 */
	@Transactional
	public int addModule(YunModule module) {
		int count = 0;
		try{

				count = moduleDao.addModule(module);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return count;
	}
	
	/**
	 * 删除模块
	 * @Title: deleteModule 
	 * @Description:并不是删除，只是delete_flag置1
	 * @param request
	 * @param session
	 * @return int         
	 * @throws
	 */
	@Transactional
	public int deleteModule(HttpServletRequest request,HttpSession session) {
		int count = 0;
		try{
			Map map = new HashMap();
			String id = request.getParameter("module_id");
			
			Object[] ids = id.split(",");
			count = moduleDao.deleteModule(ids);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return count;
	}
	
}