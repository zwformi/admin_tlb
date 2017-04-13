package com.yunrer.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.yunrer.dao.YunTypeDao;
import com.yunrer.entity.ProductType;
import com.yunrer.entity.SelectTree;
import com.yunrer.entity.YunType;

/**
 * 云分类Service
 * @ClassName YunTypeService
 * @Description 
 * @author rujun
 * @date 2016-12-21
 */
@Service("YunTypeService")
public class YunTypeService {
	@Resource
	private YunTypeDao yunTypeDao;
	
	/**
	 * 查询云模块数
	 * @Title: queryYunTypeListCount 
	 * @Description:
	 * @param yunType
	 * @param request
	 * @return Map         
	 * @throws
	 */
	public Map queryYunTypeListCount(YunType yunType,HttpServletRequest request) {
			Map map = new HashMap();
			map.put("count",yunTypeDao.queryYunTypeCount(yunType));
	return map;
}
	/**
	 * 查询云模块列表
	 * @Title: queryYunTypeList 
	 * @Description:
	 * @param yunType
	 * @param request
	 * @return List<YunType>         
	 * @throws
	 */
	public List<YunType> queryYunTypeList(YunType yunType,HttpServletRequest request) {
			int pageIndex = request.getParameter("pageIndex")==null?0:Integer.parseInt(request.getParameter("pageIndex"));
			int pageSize = request.getParameter("pageSize")==null?0:Integer.parseInt(request.getParameter("pageSize"));
			List<YunType> list = yunTypeDao.queryYunTypeList(yunType,pageIndex,pageSize);
		return list;
	}
	
	/**
	 * 查询云分类
	 * @Title: queryYunType 
	 * @Description:
	 * @param request
	 * @return YunType         
	 * @throws
	 */
	public YunType queryYunType(HttpServletRequest request) {
			int id = request.getParameter("id")==null?0:Integer.parseInt(request.getParameter("id"));
			YunType yunType = yunTypeDao.queryYunType(id);
	return yunType;
}
	
	/**
	 * 新增云模块类别
	 * @Title: addYunType 
	 * @Description:
	 * @param yunType
	 * @return Map         
	 * @throws
	 */
	public Map addYunType(YunType yunType) {
		Map map = new HashMap();
		int count = yunTypeDao.addYunType(yunType);
		map.put("count", count);
	return map;
}
	/**
	 * 修改云模块类别
	 * @Title: editYunType 
	 * @Description:
	 * @param yunType
	 * @return Map         
	 * @throws
	 */
	public Map editYunType(YunType yunType) {
		Map map = new HashMap();
		map.put("count",yunTypeDao.updateYunType(yunType)); 
	return map;
}
	/**
	 * 删除云模块类别
	 * @Title: deleteYunType 
	 * @Description:
	 * @param request
	 * @return Map         
	 * @throws
	 */
	public Map deleteYunType(HttpServletRequest request) {
		Map map = new HashMap();
		String id = request.getParameter("type_id");
		Object[] ids = id.split(",");
		int count = yunTypeDao.deleteYunType(ids);
	return map;
}
	
	/**
	 * 查询商品类型列表-树形菜单
	 */
	public List<SelectTree> queryYunTypeList(HttpServletRequest request) {
		List<YunType> YunTypelList =yunTypeDao.queryYunTypeList();
		List<SelectTree> trees = new ArrayList<SelectTree>();
		for(YunType yc:YunTypelList){
			if(0 == yc.getParent_id()){
				SelectTree t = new SelectTree();
				t.setId(yc.getType_id());
				t.setName(yc.getName());
				t.setParentid(yc.getParent_id());
				t.setChild(buildNode(t.getId(),YunTypelList));
				trees.add(t);
			}
		}
		return trees;
	}
	
	/**
	 * 构建树型菜单数据
	 */
	public List<SelectTree> buildNode(int pid,List<YunType> yunTypeList){
		List<SelectTree> result = new ArrayList<SelectTree>();
		for(YunType pro:yunTypeList){
			SelectTree node = new SelectTree();
			if(0 != pro.getParent_id() && pro.getParent_id()==pid){
				node.setId(pro.getType_id());
				node.setName(pro.getName());
				node.setParentid(pro.getParent_id());
				List<SelectTree> children = buildNode(pro.getType_id(),yunTypeList);
				if(null != children && 0 < children.size()){
					node.setChild(children);
				}
				result.add(node); 
			}
		}
		return result;
	}
	
}
