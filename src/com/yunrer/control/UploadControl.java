package com.yunrer.control;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.yunrer.common.FileUtils;

/**
 * 上传管理
 * @ClassName UploadControl
 * @Description 
 * @author rujun
 * @date 2016-12-16
 */
@SuppressWarnings("unchecked")
public class UploadControl {
	// 允许文件格式
	private String imageSuffix = "jpg,png,gif,jpeg";
	private String flashSuffix = "swf,flv";
	private String mediaSuffix = "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb";
	private String fileSuffix = "jpg,png,gif,jpeg,doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2,pdf";
	
	private long allowSize = 100L;// 允许文件大小
	private String fileName;
	private String[] fileNames;

	public String getAllowSuffix(String dirName) {
		if (dirName.equals("file")) {
			return fileSuffix;
		} else if (dirName.equals("media")) {
			return mediaSuffix;
		} else if (dirName.equals("flash")) {
			return flashSuffix;
		} else {
			return imageSuffix;
		}
	}

	public long getAllowSize() {
		return allowSize * 1024 * 1024;
	}

	public void setAllowSize(long allowSize) {
		this.allowSize = allowSize;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String[] getFileNames() {
		return fileNames;
	}

	public void setFileNames(String[] fileNames) {
		this.fileNames = fileNames;
	}

	/**
	 * 重新命名文件
	 * @Title: getFileNameNew 
	 * @Description:
	 * @return String         
	 * @throws
	 */
	private String getFileNameNew() {
		
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmm-ssSSS-");
		String result = fmt.format(new Date())+UUID.randomUUID();
		return result;
	}

	/**
	 * 文件上传
	 * @Title: upload 
	 * @Description:
	 * @param file
	 * @param destDir
	 * @param request
	 * @return
	 * @throws Exception Map         
	 * @throws
	 */
	public Map upload(MultipartFile file, String destDir,
			HttpServletRequest request) throws Exception {
		Map map = new HashMap();
		
		String dirName = request.getParameter("dir");
		if (dirName == null) {
			dirName = "image";
		}
		
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path;
		try {
			String suffix = file.getOriginalFilename().substring(
					file.getOriginalFilename().lastIndexOf(".") + 1);
			int length = getAllowSuffix(dirName).indexOf(suffix);
			if (length == -1) {
				map.put("status", 0);
				map.put("msg", "不允许上传" + suffix + "类型的文件！");
				//编辑器上传
				map.put("error", 1);
				map.put("message", "不允许上传" + suffix + "类型的文件！");
				return map;
			}
			
			if (file.getSize() > getAllowSize()) {
				map.put("status", 0);
				map.put("msg", "文件超过限制的大小！");
				//编辑器上传
				map.put("error", 1);
				map.put("message", "文件超过限制的大小！");
				return map;
			}

			String realPath = request.getSession().getServletContext()
					.getRealPath("/");
			File destFile = new File(realPath + destDir);
			if (!destFile.exists()) {
				destFile.mkdirs();
			}
			String fileNameNew = getFileNameNew() + "." + suffix;
			File f = new File(destFile.getAbsoluteFile() + "/" + fileNameNew);
			file.transferTo(f);
			f.createNewFile();
			fileName = basePath + destDir + fileNameNew;
			
			map.put("status", 1);
			map.put("msg", "上传文件成功！");
			map.put("name", fileNameNew);
			map.put("path", destDir + fileNameNew);
			map.put("size", file.getSize());
			map.put("ext", suffix);
			//编辑器上传
			map.put("error", 0);
			map.put("url", destDir + fileNameNew);
			
			//删除原文件
			String DelFilePath = request.getParameter("DelFilePath");
			if(DelFilePath != null&&!"undefined".equals(DelFilePath)){
				
				System.out.println(DelFilePath);
				File file_old = new File(realPath + DelFilePath);
				if(file_old.exists()){

				FileUtils.deleteFile(realPath + DelFilePath);
				}
			}
		} catch (Exception e) {
			map.put("status", 0);
			map.put("msg", "上传过程中发生意外错误！");
			//编辑器上传
			map.put("error", 1);
			map.put("message", "上传过程中发生意外错误！");
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 文件管理
	 * @Title: fileManage 
	 * @Description:
	 * @param request
	 * @return Map         
	 * @throws
	 */
	public Map fileManage(HttpServletRequest request){
		//根目录路径，可以指定绝对路径，比如 /var/www/upload/
		String rootPath = request.getSession().getServletContext().getRealPath("/") + "upload/";
		//根目录URL，可以指定绝对路径，比如 http://www.xxx.com/upload/
		String rootUrl  = request.getContextPath() + "/upload/";
		//图片扩展名
		String[] fileTypes = new String[]{"jpg", "png", "jpeg", "gif"};

		String dirName = request.getParameter("dir");
		if (dirName != null) {
			if(!Arrays.<String>asList(new String[]{"image", "flash", "media", "file"}).contains(dirName)){
				System.out.println("Invalid Directory name.");
				return null;
			}
			rootPath += dirName + "/";
			rootUrl += dirName + "/";
			File saveDirFile = new File(rootPath);
			if (!saveDirFile.exists()) {
				saveDirFile.mkdirs();
			}
		}
		//根据path参数，设置各路径和URL
		String path = request.getParameter("path") != null ? request.getParameter("path") : "";
		String currentPath = rootPath + path;
		String currentUrl = rootUrl + path;
		String currentDirPath = path;
		String moveupDirPath = "";
		if (!"".equals(path)) {
			String str = currentDirPath.substring(0, currentDirPath.length() - 1);
			moveupDirPath = str.lastIndexOf("/") >= 0 ? str.substring(0, str.lastIndexOf("/") + 1) : "";
		}

		//排序形式，name or size or type
		String order = request.getParameter("order") != null ? request.getParameter("order").toLowerCase() : "name";

		//不允许使用..移动到上一级目录
		if (path.indexOf("..") >= 0) {
			System.out.println("Access is not allowed.");
			return null;
		}
		//最后一个字符不是/
		if (!"".equals(path) && !path.endsWith("/")) {
			System.out.println("Parameter is not valid.");
			return null;
		}
		//目录不存在或不是目录
		File currentPathFile = new File(currentPath);
		if(!currentPathFile.isDirectory()){
			System.out.println("Directory does not exist.");
			return null;
		}

		//遍历目录取的文件信息
		List<Hashtable> fileList = new ArrayList<Hashtable>();
		if(currentPathFile.listFiles() != null) {
			for (File file : currentPathFile.listFiles()) {
				Hashtable<String, Object> hash = new Hashtable<String, Object>();
				String fileName = file.getName();
				if(file.isDirectory()) {
					hash.put("is_dir", true);
					hash.put("has_file", (file.listFiles() != null));
					hash.put("filesize", 0L);
					hash.put("is_photo", false);
					hash.put("filetype", "");
				} else if(file.isFile()){
					String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
					hash.put("is_dir", false);
					hash.put("has_file", false);
					hash.put("filesize", file.length());
					hash.put("is_photo", Arrays.<String>asList(fileTypes).contains(fileExt));
					hash.put("filetype", fileExt);
				}
				hash.put("filename", fileName);
				hash.put("datetime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(file.lastModified()));
				fileList.add(hash);
			}
		}

		if ("size".equals(order)) {
			Collections.sort(fileList, new SizeComparator());
		} else if ("type".equals(order)) {
			Collections.sort(fileList, new TypeComparator());
		} else {
			Collections.sort(fileList, new NameComparator());
		}
		Map map = new HashMap();
		map.put("moveup_dir_path", moveupDirPath);
		map.put("current_dir_path", currentDirPath);
		map.put("current_url", currentUrl);
		map.put("total_count", fileList.size());
		map.put("file_list", fileList);
		return map;
	}
	

	public class NameComparator implements Comparator {
		public int compare(Object a, Object b) {
			Hashtable hashA = (Hashtable)a;
			Hashtable hashB = (Hashtable)b;
			if (((Boolean)hashA.get("is_dir")) && !((Boolean)hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean)hashA.get("is_dir")) && ((Boolean)hashB.get("is_dir"))) {
				return 1;
			} else {
				return ((String)hashA.get("filename")).compareTo((String)hashB.get("filename"));
			}
		}
	}

	public class SizeComparator implements Comparator {
		public int compare(Object a, Object b) {
			Hashtable hashA = (Hashtable)a;
			Hashtable hashB = (Hashtable)b;
			if (((Boolean)hashA.get("is_dir")) && !((Boolean)hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean)hashA.get("is_dir")) && ((Boolean)hashB.get("is_dir"))) {
				return 1;
			} else {
				if (((Long)hashA.get("filesize")) > ((Long)hashB.get("filesize"))) {
					return 1;
				} else if (((Long)hashA.get("filesize")) < ((Long)hashB.get("filesize"))) {
					return -1;
				} else {
					return 0;
				}
			}
		}
	}

	public class TypeComparator implements Comparator {
		public int compare(Object a, Object b) {
			Hashtable hashA = (Hashtable)a;
			Hashtable hashB = (Hashtable)b;
			if (((Boolean)hashA.get("is_dir")) && !((Boolean)hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean)hashA.get("is_dir")) && ((Boolean)hashB.get("is_dir"))) {
				return 1;
			} else {
				return ((String)hashA.get("filetype")).compareTo((String)hashB.get("filetype"));
			}
		}
	}
}