package com.yunrer.control;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunrer.entity.StringMap;
import com.yunrer.service.StringMapService;

/**
 * 字段表管理
 * @ClassName StringMapControl
 * @Description 
 * @author rujun
 * @date 2016-12-16
 */
@Controller
@RequestMapping("stringmap")
public class StringMapControl {

	@Resource
	private StringMapService service;
	
	/**
	 * 根据表和字段获取lookup
	 * @Title: getStringMap 
	 * @Description:
	 * @param req
	 * @return List<StringMap>         
	 * @throws
	 */
	@RequestMapping
	@ResponseBody
	public List<StringMap> getStringMap(HttpServletRequest req){
		return service.getStringMapList(req);
	}
	
	/**
	 * 添加/修改
	 * @Title: addStringMap 
	 * @Description:
	 * @param request
	 * @return Map<String,Object>         
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/modify",method=RequestMethod.POST)
	public  Map<String, Object> addStringMap(HttpServletRequest request){
		return service.addStringMap(request);
		
	}
	/**
	 * 删除
	 * @Title: deleteStringMap 
	 * @Description:
	 * @param request
	 * @return Map<String,Object>         
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/delete",method=RequestMethod.POST)
	public  Map<String, Object> deleteStringMap(HttpServletRequest request){
		return service.deleteStringMap(request);
		
	}
	/**
	 * 分页/根据条件查询
	 * @Title: queryPagingStringMap 
	 * @Description:
	 * @param request
	 * @return Map<String,Object>         
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/paginglist",method=RequestMethod.POST)
	public  Map<String, Object> queryPagingStringMap(HttpServletRequest request){
		return service.queryPagingStringMap(request);
		
	}

	/**
	 * 根据id查询单条记录
	 * @Title: queryStringMap 
	 * @Description:
	 * @param request
	 * @return Map<String,Object>         
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/query",method=RequestMethod.POST)
	public  Map<String, Object> queryStringMap(HttpServletRequest request){
		return service.queryStringMap(request);
		
	}

	/**
	 * 查询记录数量
	 * @Title: queryCount 
	 * @Description:
	 * @param request
	 * @return Map<String,Object>         
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/count",method=RequestMethod.POST)
	public  Map<String, Object> queryCount(HttpServletRequest request){
		return service.queryPagingCount(request);
		
	}

	/**
	 * 查询表
	 * @Title: queryTables 
	 * @Description:
	 * @return Map<String,Object>         
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/table",method=RequestMethod.POST)
	public  Map<String, Object> queryTables(){
		return service.queryTables();
		
	}

	/**
	 * 查询字段
	 * @Title: queryColumn 
	 * @Description:
	 * @param request
	 * @return Map<String,Object>         
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/column",method=RequestMethod.POST)
	public  Map<String, Object> queryColumn(HttpServletRequest request){
		return service.queryColumns(request);
		
	}
}
