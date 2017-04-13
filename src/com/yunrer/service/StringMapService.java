package com.yunrer.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;

import com.yunrer.dao.StringMapDao;
import com.yunrer.entity.Column;
import com.yunrer.entity.StringMap;

/**
 * 字段表Service
 * @ClassName StringMapService
 * @Description 
 */
@Service("StringMapService")
public class StringMapService {

	@Resource
	private StringMapDao dao;
	
	/**
	 * 获取字段表list
	 * @Title: getStringMapList 
	 * @Description:
	 * @param request
	 * @return List<StringMap>         
	 * @throws
	 */
	public List<StringMap> getStringMapList(HttpServletRequest request) {
		String filedname = request.getParameter("filedname")==null?null:request.getParameter("filedname");
		String objecttypename = request.getParameter("objecttypename")==null?null:request.getParameter("objecttypename");
		if(filedname!=null && !filedname.equals("") && objecttypename!=null && !objecttypename.equals("")){
			return dao.getStringMap(objecttypename, filedname);
			
		}
			
		else
			return null;
	}
	
	/**
	 * 添加修改stringmap
	 * @Title: addStringMap 
	 * @Description:
	 * @param request
	 * @return Map<String,Object>         
	 * @throws
	 */
	public Map<String,Object> addStringMap(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String, Object>();
		try{
			int num = 0;
			StringMap stringmap = new StringMap();
			stringmap.setFiledName(request.getParameter("filedname"));
			stringmap.setName(request.getParameter("name"));
			stringmap.setObjectTypeName(request.getParameter("objecttypename"));
			stringmap.setPinyin(request.getParameter("pinyin"));
			stringmap.setValue(Integer.parseInt(request.getParameter("value")));
			//查重
			if(request.getParameter("id")!=null && !request.getParameter("id").equals("")){
				StringMap stringmap1 = new StringMap();
				stringmap1.setId(Long.parseLong(request.getParameter("id")));
				stringmap1.setFiledName(request.getParameter("filedname"));
				stringmap1.setName(request.getParameter("name"));
				stringmap1.setObjectTypeName(request.getParameter("objecttypename"));
				stringmap1.setPinyin(request.getParameter("pinyin"));
				stringmap1.setValue(Integer.parseInt(request.getParameter("value")));
				
				if(dao.checkRepeate(stringmap1,"update")>0){
					map.put("resCode", -1);
					map.put("resMsg", "保存数据与其他条目重复，请检查！");
					return map;
				}
				
				
			}else{
				if(dao.checkRepeate(stringmap,"insert")>0){
					map.put("resCode", -1);
					map.put("resMsg", "数据重复，请检查！");
					return map;
				}
				
			}

			if(request.getParameter("id")!=null && !request.getParameter("id").equals("")){
				stringmap.setId(Long.parseLong(request.getParameter("id")));
				num = dao.modifyStringmap(stringmap);	
				
			}else
				num = dao.addStringmap(stringmap);
			
			if(num >0)
				map.put("resCode", 1);
			else
				map.put("resCode", 0);
		}catch(Exception e){
			System.err.println("---------------------------------------");
			System.err.println(e.getMessage());
			System.err.println("---------------------------------------");
			map.put("resCode", -1);
			map.put("resMsg", e.getMessage());
			
		}
		return map;
	}
	
	
	/**
	 * 根据id获取stringmap
	 * @Title: queryStringMap 
	 * @Description:
	 * @param request
	 * @return Map<String,Object>         
	 * @throws
	 */
	public  Map<String,Object> queryStringMap(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String, Object>();
		try{
			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println(id);
			StringMap stringmap =  dao.queryStringmap(id);
			if(stringmap!=null){
				map.put("resCode", 1);
				map.put("data", stringmap);
			}
				
			else
				map.put("resCode", 0);
		}catch(Exception e){
			System.err.println("---------------------------------------");
			System.err.println(e.getMessage());
			System.err.println("---------------------------------------");
			map.put("resCode", -1);
			map.put("resMsg", e.getMessage());
			
		}
		return map;
	}
	
	/**
	 * 根据条件查询 搜索 、分页
	 * @Title: queryPagingStringMap 
	 * @Description:
	 * @param request
	 * @return Map<String,Object>         
	 * @throws
	 */
	public Map<String,Object> queryPagingStringMap(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String, Object>();
		try{
				String keyword = request.getParameter("keyword");
				int pageIndex = Integer.parseInt(request.getParameter("pageindex"));
				int  pageSize = Integer.parseInt(request.getParameter("pagesize"));
				String orderParam = request.getParameter("orderparam");

			
				List<StringMap> list = dao.queryPagingStringMapList(keyword, pageIndex, pageSize, orderParam);
			if(list!=null && list.size()>0){
				map.put("resCode", 1);
				map.put("data", list);
			}
				
			else
				map.put("resCode", 0);
		}catch(Exception e){
			System.err.println("---------------------------------------");
			System.err.println(e.getMessage());
			System.err.println("---------------------------------------");
			map.put("resCode", -1);
			map.put("resMsg", e.getMessage());
			
		}
		return map;
	}
	
	/**
	 * 删除
	 * @Title: deleteStringMap 
	 * @Description:
	 * @param request
	 * @return Map<String,Object>         
	 * @throws
	 */
	public Map<String,Object> deleteStringMap(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String, Object>();
		try{
			String[] ids = request.getParameter("ids").indexOf(',')!=-1?
					request.getParameter("ids").split(","):new String[]{request.getParameter("ids")};
			int num = dao.deleteStringmap(ids);
			if(num >0)
				map.put("resCode", 1);
			else
				map.put("resCode", 0);
		}catch(Exception e){
			System.err.println("---------------------------------------");
			System.err.println(e.getMessage());
			System.err.println("---------------------------------------");
			map.put("resCode", -1);
			map.put("resMsg", e.getMessage());
			
		}
		return map;
	}
	
	/**
	 *  根据条件查询 搜索 、分页
	 * @Title: queryPagingCount 
	 * @Description:
	 * @param request
	 * @return Map<String,Object>         
	 * @throws
	 */
	public Map<String,Object> queryPagingCount(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String, Object>();
		try{
			String keyword = request.getParameter("keyword");
			int num = dao.queryStringmapCount(keyword);
			if(num >= 0){
				map.put("resCode",1);
				map.put("count",num );
				
			}else{
				map.put("resCode", 0);
			}
		}catch(Exception e){
			System.err.println("---------------------------------------");
			System.err.println(e.getMessage());
			System.err.println("---------------------------------------");
			map.put("resCode", -1);
			map.put("resMsg", e.getMessage());
			
		}
		return map;
	}

	/**
	 * 查询表
	 * @Title: queryTables 
	 * @Description:
	 * @return Map<String,Object>         
	 * @throws
	 */
	public Map<String,Object> queryTables(){
		Map<String,Object> map = new HashMap<String, Object>();
		try{
			List<?> tables = dao.queryTables();
			if(tables!=null && tables.size() > 0){
				map.put("resCode",1);
				map.put("data",tables );
				
			}else{
				map.put("resCode", 0);
			}
		}catch(Exception e){
			System.err.println("---------------------------------------");
			System.err.println(e.getMessage());
			System.err.println("---------------------------------------");
			map.put("resCode", -1);
			map.put("resMsg", e.getMessage());
			
		}
		return map;
	}

	/**
	 * 获取字段列表
	 * @Title: queryColumns 
	 * @Description:
	 * @param request
	 * @return Map<String,Object>         
	 * @throws
	 */
	public Map<String,Object> queryColumns(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String, Object>();
		try{
			String tablename = request.getParameter("tablename");
			List<?> columns = dao.queryColumns(tablename);
			if(columns!=null && columns.size() > 0){
				map.put("resCode",1);
				map.put("data",columns );
				
			}else{
				map.put("resCode", 0);
			}
		}catch(Exception e){
			System.err.println("---------------------------------------");
			System.err.println(e.getMessage());
			System.err.println("---------------------------------------");
			map.put("resCode", -1);
			map.put("resMsg", e.getMessage());
			
		}
		return map;
	}
}
