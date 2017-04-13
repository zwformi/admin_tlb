package com.yunrer.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.yunrer.common.ProcessResult;
import com.yunrer.dao.DmbDao;
import com.yunrer.entity.Dmb;

/**
 * 字典表Service
 * @ClassName DmbService
 * @Description 
 * @author rujun
 * @date 2016-12-19
 */
@Service("DmbService")
public class DmbService {

	@Resource
	private DmbDao dmbDao;
	
	/**
	 * 新增字典
	 * @Title: addDmb 
	 * @Description:
	 * @param request
	 * @return ProcessResult         
	 * @throws
	 */
	public ProcessResult addDmb(HttpServletRequest request) {
		ProcessResult pr = new ProcessResult();
		try{
			Dmb d = new Dmb();
			d.setName(request.getParameter("name"));
			d.setTypeid(Integer.parseInt(request.getParameter("typeid")));
			d.setSortid(Integer.parseInt(request.getParameter("sortid")));
			dmbDao.addDmb(d);
			
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
	 * 删除字典
	 * @Title: deleteDmb 
	 * @Description:
	 * @param request
	 * @return ProcessResult         
	 * @throws
	 */
	public ProcessResult deleteDmb(HttpServletRequest request) {
		ProcessResult pr = new ProcessResult();
		try{
			String id = request.getParameter("id");
			Object[] ids = id.split(",");
			
			dmbDao.deleteDmb(ids);
			
			pr.setSuccess(true);
			pr.setMessage("删除成功");
		} catch (Exception ex) {
			pr.setSuccess(false);
			pr.setMessage(ex.getMessage());
		}
		return pr;
	}
	
	/**
	 * 更新字典
	 * @Title: modifyDmb 
	 * @Description:
	 * @param request
	 * @return ProcessResult         
	 * @throws
	 */
	public ProcessResult modifyDmb(HttpServletRequest request) {
		ProcessResult pr = new ProcessResult();
		try{
			Dmb d = new Dmb();
			d.setId(Integer.parseInt(request.getParameter("id")));
			d.setName(request.getParameter("name"));
			d.setTypeid(Integer.parseInt(request.getParameter("typeid")));
			d.setSortid(Integer.parseInt(request.getParameter("sortid")));
			
			dmbDao.modifyDmb(d);
			
			pr.setSuccess(true);
			pr.setMessage("保存成功");
		} catch (Exception ex) {
			pr.setSuccess(false);
			pr.setMessage(ex.getMessage());
		}
		return pr;
	}
	
	/**
	 * 查询字典
	 * @Title: queryDmb 
	 * @Description:
	 * @param request
	 * @return Dmb         
	 * @throws
	 */
	public Dmb queryDmb(HttpServletRequest request) {
		Dmb d = null;
		try{
			int id = Integer.parseInt(request.getParameter("id"));
			d = dmbDao.queryDmb(id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return d;
	}
	
	/**
	 * 查询字典数量
	 * @Title: queryDmbCount 
	 * @Description:
	 * @param request
	 * @return int         
	 * @throws
	 */
	public int queryDmbCount(HttpServletRequest request) {
		int count = 0;
		try{
			String keyword = request.getParameter("keyword");
			count = dmbDao.queryDmbCount(keyword);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return count;
	}
	
	/**
	 * 关键字查询字典列表
	 * @Title: queryDmbList 
	 * @Description:
	 * @param request
	 * @return List<Dmb>         
	 * @throws
	 */
	public List<Dmb> queryDmbList(HttpServletRequest request) {
		List<Dmb> list = null;
		try{
			String keyword = request.getParameter("keyword");
			int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
			int pageSize = Integer.parseInt(request.getParameter("pageSize"));
			list = dmbDao.queryDmbList(keyword, pageIndex, pageSize);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
	
	/**
	 * id查询字典列表
	 * @Title: queryDmbList 
	 * @Description:
	 * @param typeid
	 * @return List<Dmb>         
	 * @throws
	 */
	public List<Dmb> queryDmbList(int typeid) {
		List<Dmb> list = null;
		try{
			list = dmbDao.queryDmbList(typeid);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
	
}