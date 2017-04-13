package com.yunrer.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.yunrer.common.ProcessResult;
import com.yunrer.dao.SalesmanDao;
import com.yunrer.dao.UserInfoDao;
import com.yunrer.entity.Salesman;
import com.yunrer.entity.UserInfo;

/**
 * 销售人员Service
 * @ClassName SalesmanService
 * @Description 
 * @author rujun
 * @date 2016-12-21
 */
@Service("SalesmanService")
public class SalesmanService {

	@Resource
	private SalesmanDao salesmanDao;
	
	@Resource
	private UserInfoDao userInfoDao;
	
	/**
	 * 新增销售人员
	 * @Title: addSalesman 
	 * @Description:
	 * @param request
	 * @return ProcessResult         
	 * @throws
	 */
	public ProcessResult addSalesman(HttpServletRequest request) {
		ProcessResult pr = new ProcessResult();
		try{
			Salesman s = new Salesman();
			s.setName(request.getParameter("name"));
			s.setPhone(request.getParameter("phone"));
			s.setQq(request.getParameter("qq"));
			s.setTelphone(request.getParameter("telphone"));
			s.setEmail(request.getParameter("email"));
			s.setGh(request.getParameter("gh"));
			salesmanDao.addSalesman(s);
			
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
	 * 删除销售人员
	 * @Title: deleteSalesman 
	 * @Description:
	 * @param request
	 * @return ProcessResult         
	 * @throws
	 */
	public ProcessResult deleteSalesman(HttpServletRequest request) {
		ProcessResult pr = new ProcessResult();
		try{
			String id = request.getParameter("id");
			Object[] ids = id.split(",");
			
			salesmanDao.deleteSalesman(ids);
			
			pr.setSuccess(true);
			pr.setMessage("删除成功");
		} catch (Exception ex) {
			pr.setSuccess(false);
			pr.setMessage(ex.getMessage());
		}
		return pr;
	}
	
	/**
	 * 更新销售人员
	 * @Title: modifySalesman 
	 * @Description:
	 * @param request
	 * @return ProcessResult         
	 * @throws
	 */
	public ProcessResult modifySalesman(HttpServletRequest request) {
		ProcessResult pr = new ProcessResult();
		try{
			Salesman s = new Salesman();
			s.setId(Integer.parseInt(request.getParameter("id")));
			s.setName(request.getParameter("name"));
			s.setPhone(request.getParameter("phone"));
			s.setQq(request.getParameter("qq"));
			s.setTelphone(request.getParameter("telphone"));
			s.setEmail(request.getParameter("email"));
			s.setGh(request.getParameter("gh"));
			salesmanDao.modifySalesman(s);
			
			pr.setSuccess(true);
			pr.setMessage("保存成功");
		} catch (Exception ex) {
			pr.setSuccess(false);
			pr.setMessage(ex.getMessage());
		}
		return pr;
	}
	
	/**
	 * 根据id查询销售人员
	 * @Title: querySalesman 
	 * @Description:
	 * @param request
	 * @return Salesman         
	 * @throws
	 */
	public Salesman querySalesman(HttpServletRequest request) {
		Salesman pt = null;
		try{
			int id = Integer.parseInt(request.getParameter("id"));
			pt = salesmanDao.querySalesman(id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return pt;
	}
	
	/**
	 * 根据用户id查询销售人员
	 * @Title: querySalesman2 
	 * @Description:
	 * @param request
	 * @return Salesman         
	 * @throws
	 */
	public Salesman querySalesman2(HttpServletRequest request) {
		Salesman pt = null;
		try{
			int id = Integer.parseInt(request.getParameter("id"));
			
			UserInfo userinfo = userInfoDao.queryUserInfo(id);
			
			if (userinfo != null && userinfo.getSales_id() != 0) {
				pt = salesmanDao.querySalesman(userinfo.getSales_id());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return pt;
	}
	
	/**
	 * 查询销售人员数量
	 * @Title: querySalesmanCount 
	 * @Description:
	 * @param request
	 * @return int         
	 * @throws
	 */
	public int querySalesmanCount(HttpServletRequest request) {
		int count = 0;
		try{
			String keyword = request.getParameter("keyword");
			count = salesmanDao.querySalesmanCount(keyword);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return count;
	}
	
	/**
	 * 查询销售人员列表
	 * @Title: querySalesmanList 
	 * @Description:
	 * @param request
	 * @return List<Salesman>         
	 * @throws
	 */
	public List<Salesman> querySalesmanList(HttpServletRequest request) {
		List<Salesman> list = null;
		try{
			String keyword = request.getParameter("keyword");
			int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
			int pageSize = Integer.parseInt(request.getParameter("pageSize"));
			list = salesmanDao.querySalesmanList(keyword, pageIndex, pageSize);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
	
}