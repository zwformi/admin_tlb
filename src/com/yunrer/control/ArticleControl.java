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
import com.yunrer.entity.Article;
import com.yunrer.service.ArticleService;

/**
 * 单页管理
 * @author zhanglei
 */
@Controller
@RequestMapping("/article")
@SuppressWarnings("unchecked")
public class ArticleControl {

	@Resource
	private ArticleService articleService;
	
	/**
	 * 新增单页
	 * @Title: addArticle 
	 * @Description:
	 * @param request
	 * @return ProcessResult         
	 * @throws
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public ProcessResult addArticle(HttpServletRequest request) {
		return articleService.addArticle(request);
	}
	
	/**
	 * 删除单页
	 * @Title: deleteArticle 
	 * @Description:
	 * @param request
	 * @return ProcessResult         
	 * @throws
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public ProcessResult deleteArticle(HttpServletRequest request) {
		return articleService.deleteArticle(request);
	}
	
	/**
	 * 更新单页
	 * @Title: modifyArticle 
	 * @Description:
	 * @param request
	 * @return ProcessResult         
	 * @throws
	 */
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	@ResponseBody
	public ProcessResult modifyArticle(HttpServletRequest request) {
		return articleService.modifyArticle(request);
	}

	/**
	 * 查询单页
	 * @Title: queryArticle 
	 * @Description:
	 * @param request
	 * @return Article         
	 * @throws
	 */
	@RequestMapping(value = "/query", method = RequestMethod.POST)
	@ResponseBody
	public Article queryArticle(HttpServletRequest request) {
		return articleService.queryArticle(request);
	}
	
	/**
	 * 查询单页数量
	 * @Title: queryArticleCount 
	 * @Description:
	 * @param request
	 * @return Map         
	 * @throws
	 */
	@RequestMapping(value = "/queryCount", method = RequestMethod.POST)
	@ResponseBody
	public Map queryArticleCount(HttpServletRequest request) {
		Map map = new HashMap();
		map.put("count", articleService.queryArticleCount(request));
		return map;
	}
	
	/**
	 * 查询单页列表
	 * @Title: queryArticleList 
	 * @Description:
	 * @param request
	 * @return List<Article>         
	 * @throws
	 */
	@RequestMapping(value = "/queryList", method = RequestMethod.POST)
	@ResponseBody
	public List<Article> queryArticleList(HttpServletRequest request) {
		List<Article> list = articleService.queryArticleList(request);
		return list;
	}
	
}