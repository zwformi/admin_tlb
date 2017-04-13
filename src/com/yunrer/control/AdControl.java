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
import com.yunrer.entity.Ad;
import com.yunrer.service.AdService;

/**
 * 广告图片管理
 * @author zhanglei
 */
@Controller
@RequestMapping("/ad")
@SuppressWarnings("unchecked")
public class AdControl {

	@Resource
	private AdService adService;
	

	/**
	 * 新增广告图片
	 * @Title: addAd 
	 * @Description:
	 * @param request
	 * @return ProcessResult         
	 * @throws
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public ProcessResult addAd(HttpServletRequest request) {
		return adService.addAd(request);
	}
	
	/**
	 * 删除广告图片
	 * @Title: deleteAd 
	 * @Description:
	 * @param request
	 * @return ProcessResult         
	 * @throws
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public ProcessResult deleteAd(HttpServletRequest request) {
		return adService.deleteAd(request);
	}
	
	/**
	 * 更新广告图片
	 * @Title: modifyAd 
	 * @Description:
	 * @param request
	 * @return ProcessResult         
	 * @throws
	 */
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	@ResponseBody
	public ProcessResult modifyAd(HttpServletRequest request) {
		return adService.modifyAd(request);
	}

	/**
	 * 查询广告图片
	 * @Title: queryAd 
	 * @Description:
	 * @param request
	 * @return Ad         
	 * @throws
	 */
	@RequestMapping(value = "/query", method = RequestMethod.POST)
	@ResponseBody
	public Ad queryAd(HttpServletRequest request) {
		return adService.queryAd(request);
	}
	
	/**
	 * 查询广告图片数量
	 * @Title: queryAdCount 
	 * @Description:
	 * @param request
	 * @return Map         
	 * @throws
	 */
	@RequestMapping(value = "/queryCount", method = RequestMethod.POST)
	@ResponseBody
	public Map queryAdCount(HttpServletRequest request) {
		Map map = new HashMap();
		map.put("count", adService.queryAdCount(request));
		return map;
	}
	
	/**
	 * 查询广告图片列表
	 * @Title: queryAdList 
	 * @Description:
	 * @param request
	 * @return List<Ad>         
	 * @throws
	 */
	@RequestMapping(value = "/queryList", method = RequestMethod.POST)
	@ResponseBody
	public List<Ad> queryAdList(HttpServletRequest request) {
		List<Ad> list = adService.queryAdList(request);
		return list;
	}
	
}