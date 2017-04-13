package com.yunrer.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.yunrer.entity.Article;

/**
 * 单页管理DAO
 * @ClassName ArticleDao
 * @Description 
 * @author rujun
 * @date 2016-12-16
 */
@Repository("ArticleDao")
public class ArticleDao {

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 新增单页
	 * @Title: addArticle 
	 * @Description:
	 * @param a void         
	 * @throws
	 */
	public void addArticle(Article a) {
		String sql = "insert into tbl_article (typeid,title,content,sortid,create_time) values (?, ?, ?, ?,now())";
		Object[] params = new Object[] { a.getTypeid(), a.getTitle(), a.getContent(), a.getSortid() };
		jdbcTemplate.update(sql, params);
	}
	
	/**
	 * 删除单页
	 * @Title: deleteArticle 
	 * @Description:
	 * @param ids void         
	 * @throws
	 */
	public void deleteArticle(Object[] ids) {
		StringBuffer sql= new StringBuffer("delete from tbl_article where id in ("); 
		for (int i = 0; i < ids.length; i++) {
			if (i != 0)
				sql.append(",");
			sql.append("?");
		}
		sql.append(")");
		jdbcTemplate.update(sql.toString(), ids);
	}
	
	/**
	 * 更新单页
	 * @Title: modifyArticle 
	 * @Description:
	 * @param a void         
	 * @throws
	 */
	public void modifyArticle(Article a) {
		String sql = "update tbl_article set typeid = ?, title = ?, content = ?, sortid = ? where id = ?";
		Object[] params = new Object[] { a.getTypeid(), a.getTitle(), a.getContent(), a.getSortid(), a.getId() };
		jdbcTemplate.update(sql, params);
	}
	
	/**
	 * 查询单页
	 * @Title: queryArticle 
	 * @Description:
	 * @param id
	 * @return Article         
	 * @throws
	 */
	public Article queryArticle(int id) {
		String sql = "select * from tbl_article where id = ?";
		Object[] params = new Object[] { id };
		List<Article> list = jdbcTemplate.query(sql, params,
				new BeanPropertyRowMapper<Article>(Article.class));
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 查询单页数量
	 * @Title: queryArticleCount 
	 * @Description:
	 * @param keyword
	 * @return int         
	 * @throws
	 */
	public int queryArticleCount(String keyword) {
		String sql = "select count(id) from tbl_article where title like ?";
		keyword = "%" + keyword + "%";
		Object[] params = new Object[] { keyword };
		int count = jdbcTemplate.queryForObject(sql, Integer.class, params);
		return count;
	}
	
	/**
	 * 查询单页列表
	 * @Title: queryArticleList 
	 * @Description:
	 * @param keyword
	 * @param pageIndex
	 * @param pageSize
	 * @return List<Article>         
	 * @throws
	 */
	public List<Article> queryArticleList(String keyword, int pageIndex, int pageSize) {
		String sql = "select * from tbl_article where title like ? order by sortid desc,typeid desc limit ?,?";
		keyword = "%" + keyword + "%";
		int start = pageIndex * pageSize;
		Object[] params = new Object[] { keyword, start, pageSize };
		List<Article> list = jdbcTemplate.query(sql, params,
				new BeanPropertyRowMapper<Article>(Article.class));
		return list;
	}
	
}