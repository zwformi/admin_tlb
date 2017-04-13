package com.yunrer.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.yunrer.common.ProcessResult;
import com.yunrer.dao.AdDao;
import com.yunrer.entity.Ad;

/**
 * 广告Service
 * @ClassName AdService
 * @Description 
 * @author rujun
 * @date 2016-12-19
 */
@Service("AdService")
public class AdService {

	@Resource
	private AdDao adDao;
	
	/**
	 * 新增广告图片
	 * @Title: addAd 
	 * @Description:
	 * @param request
	 * @return ProcessResult         
	 * @throws
	 */
	public ProcessResult addAd(HttpServletRequest request) {
		ProcessResult pr = new ProcessResult();
		try{
			Ad a = new Ad();
			a.setTypeid(Integer.parseInt(request.getParameter("typeid")));
			a.setName(request.getParameter("name"));
			a.setImg_url(request.getParameter("img_url"));
			a.setUrl(request.getParameter("url"));
			a.setSortid(Integer.parseInt(request.getParameter("sortid")));
			adDao.addAd(a);
			
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
	 * 删除广告图片
	 * @Title: deleteAd 
	 * @Description:
	 * @param request
	 * @return ProcessResult         
	 * @throws
	 */
	public ProcessResult deleteAd(HttpServletRequest request) {
		ProcessResult pr = new ProcessResult();
		try{
			String id = request.getParameter("id");
			Object[] ids = id.split(",");
			
			adDao.deleteAd(ids);
			
			pr.setSuccess(true);
			pr.setMessage("删除成功");
		} catch (Exception ex) {
			pr.setSuccess(false);
			pr.setMessage(ex.getMessage());
		}
		return pr;
	}
	
	/**
	 * 更新广告图片
	 * @Title: modifyAd 
	 * @Description:
	 * @param request
	 * @return ProcessResult         
	 * @throws
	 */
	public ProcessResult modifyAd(HttpServletRequest request) {
		ProcessResult pr = new ProcessResult();
		try{
			Ad a = new Ad();
			a.setId(Integer.parseInt(request.getParameter("id")));
			a.setTypeid(Integer.parseInt(request.getParameter("typeid")));
			a.setName(request.getParameter("name"));
			a.setImg_url(request.getParameter("img_url"));
			a.setUrl(request.getParameter("url"));
			a.setSortid(Integer.parseInt(request.getParameter("sortid")));
			
			adDao.modifyAd(a);
			
			pr.setSuccess(true);
			pr.setMessage("保存成功");
		} catch (Exception ex) {
			pr.setSuccess(false);
			pr.setMessage(ex.getMessage());
		}
		return pr;
	}
	
	/**
	 * 查询广告图片
	 * @Title: queryAd 
	 * @Description:
	 * @param request
	 * @return Ad         
	 * @throws
	 */
	public Ad queryAd(HttpServletRequest request) {
		Ad a = null;
		try{
			int id = Integer.parseInt(request.getParameter("id"));
			a = adDao.queryAd(id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return a;
	}
	
	/**
	 * 查询广告图片数量
	 * @Title: queryAdCount 
	 * @Description:
	 * @param request
	 * @return int         
	 * @throws
	 */
	public int queryAdCount(HttpServletRequest request) {
		int count = 0;
		try{
			String keyword = request.getParameter("keyword");
			count = adDao.queryAdCount(keyword);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return count;
	}
	
	/**
	 * 查询广告图片列表
	 * @Title: queryAdList 
	 * @Description:
	 * @param request
	 * @return List<Ad>         
	 * @throws
	 */
	public List<Ad> queryAdList(HttpServletRequest request) {
		List<Ad> list = null;
		try{
			String keyword = request.getParameter("keyword");
			int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
			int pageSize = Integer.parseInt(request.getParameter("pageSize"));
			list = adDao.queryAdList(keyword, pageIndex, pageSize);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
	
}