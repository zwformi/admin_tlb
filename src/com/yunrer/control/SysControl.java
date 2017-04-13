package com.yunrer.control;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yunrer.common.FileUtils;
import com.yunrer.service.OrderService;
import com.yunrer.service.OrderXuqiuService;
import com.yunrer.service.ProductImageService;

/**
 * 系统功能
 * @author zhanglei
 */
@Controller
@Scope(value = "prototype")
@RequestMapping(value = "/sys")
@SuppressWarnings("unchecked")
public class SysControl extends UploadControl {

	@Resource
	private ProductImageService productImageService;
	@Resource
	private OrderService orderService;
	@Resource
	private OrderXuqiuService orderXuqiuService;
	
	/**
	 * 普通图片上传
	 * @Title: uploadPic 
	 * @Description:
	 * @param file
	 * @param request
	 * @param response
	 * @return Map         
	 * @throws
	 */
	@RequestMapping("/uploadPic")
	@ResponseBody
	public Map uploadPic(@RequestParam("filedata") MultipartFile file,
			HttpServletRequest request, HttpServletResponse response) {
		Map map = null;
		try {
			map = super.upload(file, "/upload/images/", request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 编辑器上传
	 * @Title: editorUpload 
	 * @Description:
	 * @param file
	 * @param request
	 * @param response
	 * @return Map         
	 * @throws
	 */
	@RequestMapping("/editorUpload")
	@ResponseBody
	public Map editorUpload(@RequestParam("imgFile") MultipartFile file,
			HttpServletRequest request, HttpServletResponse response) {
		Map map = null;
		try {
			String dirName = request.getParameter("dir");
			if (dirName == null) {
				dirName = "image";
			}
			map = super.upload(file, "/upload/"+dirName+"/", request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 编辑器文件管理
	 * @Title: editorFileManage 
	 * @Description:
	 * @param request
	 * @return Map         
	 * @throws
	 */
	@RequestMapping("/editorFileManage")
	@ResponseBody
	public Map editorFileManage(HttpServletRequest request) {
		Map map = null;
		try {
			map = super.fileManage(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 商品图片附件上传
	 * @Title: uploadPic_product 
	 * @Description:
	 * @param file
	 * @param request
	 * @param response
	 * @return Map         
	 * @throws
	 */
	@RequestMapping("/uploadPic_product")
	@ResponseBody
	public Map uploadPic_product(@RequestParam("filedata") MultipartFile file,
			HttpServletRequest request, HttpServletResponse response) {
		Map map = null;
		try {
			map = super.upload(file, "/upload/images/product/", request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 商品图片相册上传-上传及保存数据
	 * @Title: uploadPic_product_save 
	 * @Description:
	 * @param file
	 * @param request
	 * @param response
	 * @return Map         
	 * @throws
	 */
	@RequestMapping("/uploadPic_product_save")
	@ResponseBody
	public Map uploadPic_product_save(@RequestParam("filedata") MultipartFile file,
			HttpServletRequest request, HttpServletResponse response) {
		Map map = null;
		try {
			map = super.upload(file, "/upload/images/product/", request);
			if(map.get("status").toString().equals("1")){//上传图片成功
				request.setAttribute("img_url", map.get("path").toString());
				//将图片保存到数据库
				int id = productImageService.addProductImage(request);
				if(id<=0){//如果保存数据库不成功，则删除已上传的图片
					FileUtils.deleteFile(map.get("ylj").toString());
					map.put("status", "0");
				}else{
					map.put("id", id);
				}
				map.remove("ylj");//将实际路径移除，以免带到前台，泄露计算机存储位置
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	
	/**
	 *  合同附件上传
	 * @Title: uploadPic_orderfile 
	 * @Description:
	 * @param file
	 * @param request
	 * @param response
	 * @return Map         
	 * @throws
	 */
	@RequestMapping("/uploadPic_orderfile")
	@ResponseBody
	public Map uploadPic_orderfile(@RequestParam("filedata") MultipartFile file,
			HttpServletRequest request, HttpServletResponse response) {
		Map map = null;
		try {
			map = super.upload(file, "/upload/file/order/", request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	
	/**
	 * 合同附件上传--直接保存
	 * @Title: uploadPic_orderfile_save 
	 * @Description:
	 * @param file
	 * @param request
	 * @param response
	 * @return Map         
	 * @throws
	 */
	@RequestMapping("/uploadPic_orderfile_save")
	@ResponseBody
	public Map uploadPic_orderfile_save(@RequestParam("filedata") MultipartFile file,
			HttpServletRequest request, HttpServletResponse response) {
		Map map = null;
		try {
			map = super.upload(file, "/upload/file/order/", request);
			if(map.get("status").toString().equals("1")){//附件成功
				//将合同附件保存到数据库
				int count = orderService.htfjscOrder(request, map.get("path").toString());
				if(count<=0){//如果保存数据库不成功，则删除已上传的附件
					FileUtils.deleteFile(map.get("ylj").toString());
					map.put("status", "0");
				}else{
					map.put("count", count);
				}
				map.remove("ylj");//将实际路径移除，以免带到前台，泄露计算机存储位置
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 *  上传需求报价附件
	 * @Title: uploadOffer_file 
	 * @Description:
	 * @param file
	 * @param request
	 * @param response
	 * @return Map         
	 * @throws
	 */
	@RequestMapping("/uploadOffer")
	@ResponseBody
	public Map uploadOffer_file(@RequestParam("filedata") MultipartFile file,
			HttpServletRequest request, HttpServletResponse response) {
		Map map = null;
		try {
			map = super.upload(file, "/upload/bj/", request);
			if(map.get("status").toString().equals("1")){//附件成功
				request.setAttribute("offer_file", map.get("path").toString());
				//将合同附件保存到数据库
				int count = orderXuqiuService.changeOrderXuqiuOffer(request);
				
				if(count<=0){//如果保存数据库不成功，则删除已上传的附件
					FileUtils.deleteFile(map.get("ylj").toString());
					map.put("status", "0");
				}else{
					map.put("count", count);
				}
				map.remove("ylj");//将实际路径移除，以免带到前台，泄露计算机存储位置
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
}