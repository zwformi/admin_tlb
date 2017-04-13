package com.yunrer.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.yunrer.common.ProcessResult;
import com.yunrer.dao.ArticleDao;
import com.yunrer.entity.Article;

/**
 * 广告单页Service
 * @ClassName ArticleService
 * @Description 
 * @author rujun
 * @date 2016-12-19
 */
@Service("ArticleService")
public class ArticleService {

	@Resource
	private ArticleDao articleDao;
	
	/**
	 * 新增单页
	 * @Title: addArticle 
	 * @Description:
	 * @param request
	 * @return ProcessResult         
	 * @throws
	 */
	public ProcessResult addArticle(HttpServletRequest request) {
		ProcessResult pr = new ProcessResult();
		try{
			Article a = new Article();
			a.setTitle(request.getParameter("title"));
			a.setTypeid(Integer.parseInt(request.getParameter("typeid")));
			a.setSortid(Integer.parseInt(request.getParameter("sortid")));
			a.setContent(request.getParameter("content"));
			articleDao.addArticle(a);
			
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
	 * 删除单页
	 * @Title: deleteArticle 
	 * @Description:
	 * @param request
	 * @return ProcessResult         
	 * @throws
	 */
	public ProcessResult deleteArticle(HttpServletRequest request) {
		ProcessResult pr = new ProcessResult();
		try{
			String id = request.getParameter("id");
			Object[] ids = id.split(",");
			
			articleDao.deleteArticle(ids);
			
			pr.setSuccess(true);
			pr.setMessage("删除成功");
		} catch (Exception ex) {
			pr.setSuccess(false);
			pr.setMessage(ex.getMessage());
		}
		return pr;
	}
	
	/**
	 * 更新单页
	 * @Title: modifyArticle 
	 * @Description:
	 * @param request
	 * @return ProcessResult         
	 * @throws
	 */
	public ProcessResult modifyArticle(HttpServletRequest request) {
		ProcessResult pr = new ProcessResult();
		try{
			Article a = new Article();
			a.setId(Integer.parseInt(request.getParameter("id")));
			a.setTitle(request.getParameter("title"));
			a.setTypeid(Integer.parseInt(request.getParameter("typeid")));
			a.setSortid(Integer.parseInt(request.getParameter("sortid")));
			a.setContent(request.getParameter("content"));
			
			articleDao.modifyArticle(a);
			
			pr.setSuccess(true);
			pr.setMessage("保存成功");
		} catch (Exception ex) {
			pr.setSuccess(false);
			pr.setMessage(ex.getMessage());
		}
		return pr;
	}
	
	/**
	 * 查询单页
	 * @Title: queryArticle 
	 * @Description:
	 * @param request
	 * @return Article         
	 * @throws
	 */
	public Article queryArticle(HttpServletRequest request) {
		Article pt = null;
		try{
			int id = Integer.parseInt(request.getParameter("id"));
			pt = articleDao.queryArticle(id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return pt;
	}
	
	/**
	 * 查询单页数量
	 * @Title: queryArticleCount 
	 * @Description:
	 * @param request
	 * @return int         
	 * @throws
	 */
	public int queryArticleCount(HttpServletRequest request) {
		int count = 0;
		try{
			String keyword = request.getParameter("keyword");
			count = articleDao.queryArticleCount(keyword);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return count;
	}
	
	/**
	 * 查询单页列表
	 * @Title: queryArticleList 
	 * @Description:
	 * @param request
	 * @return List<Article>         
	 * @throws
	 */
	public List<Article> queryArticleList(HttpServletRequest request) {
		List<Article> list = null;
		try{
			String keyword = request.getParameter("keyword");
			int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
			int pageSize = Integer.parseInt(request.getParameter("pageSize"));
			list = articleDao.queryArticleList(keyword, pageIndex, pageSize);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
	
}