package com.yunrer.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import com.yunrer.common.ProcessResult;
import com.yunrer.common.Utils;
import com.yunrer.dao.UserInfoDao;
import com.yunrer.entity.UserInfo;

/**
 * 用户Service
 * @ClassName UserInfoService
 * @Description 
 * @author rujun
 * @date 2016-12-21
 */
@Service("UserInfoService")
public class UserInfoService {
	
	@Resource
	private UserInfoDao userInfoDao;
	
	/**
	 * 更新用户状态
	 * @Title: modifyUserInfo 
	 * @Description:
	 * @param request
	 * @return ProcessResult         
	 * @throws
	 */
	public ProcessResult modifyUserInfo(HttpServletRequest request) {
		ProcessResult pr = new ProcessResult();
		try{
			int user_id = Integer.parseInt(request.getParameter("user_id"));
			UserInfo user = userInfoDao.queryUserInfo(user_id);
			
			int typeid = Integer.parseInt(request.getParameter("typeid"));
			
			if (typeid == 1) {
				// 启用
				if (user.getStatus() == 2 && user.getOpenid()==null) {
					user.setStatus(0);
				}
				if (user.getStatus() == 2 && user.getOpenid()!=null ) {
					user.setStatus(1);
				}
			} else {
				// 禁用
				if (user.getStatus() == 0 || user.getStatus() == 1) {
					user.setStatus(2);
				}
			}
			
			userInfoDao.modifyUserInfo(user);
			
			pr.setSuccess(true);
			pr.setMessage("保存成功");
		} catch (Exception ex) {
			pr.setSuccess(false);
			pr.setMessage(ex.getMessage());
		}
		return pr;
	}
	
	/**
	 * 更新用户状态
	 * @Title: modifyUserInfo 
	 * @Description:
	 * @param request
	 * @return ProcessResult         
	 * @throws
	 */
	public ProcessResult operateUserInfo(HttpServletRequest request,UserInfo postUser) {
		ProcessResult pr = new ProcessResult();
		try{
			int user_id = Integer.parseInt(request.getParameter("user_id"));
			UserInfo user = userInfoDao.queryUserInfo(user_id);
			
			int special_code = Integer.parseInt(request.getParameter("special_code"));
			
			if(special_code != user.getSpecial_code()){
				if(userInfoDao.operateUserInfoSpecialCode(postUser)>0){
					pr.setSuccess(true);
					pr.setMessage("操作成功");
				}else{
					pr.setSuccess(false);
					pr.setMessage("用户所属公司不具有内购权限！");	
				}
				
			}else{
				pr.setSuccess(true);
				pr.setMessage("操作成功");
			}
		
			

		} catch (Exception ex) {
			pr.setSuccess(false);
			pr.setMessage(ex.getMessage());
		}
		return pr;
	}
	
	
	/**
	 * 更新用户信息,专属客服
	 * @Title: modifyUserInfo2 
	 * @Description:
	 * @param request
	 * @return ProcessResult         
	 * @throws
	 */
	public ProcessResult modifyUserInfo2(HttpServletRequest request) {
		ProcessResult pr = new ProcessResult();
		try{
			int user_id = Integer.parseInt(request.getParameter("user_id"));
			int client_id = Integer.parseInt(request.getParameter("client_id"));
			
			userInfoDao.modifyUserInfo2(user_id, client_id);
			
			pr.setSuccess(true);
			pr.setMessage("保存成功");
		} catch (Exception ex) {
			pr.setSuccess(false);
			pr.setMessage(ex.getMessage());
		}
		return pr;
	}
	
	/**
	 * 更新用户信息，专属销售
	 * @Title: modifyUserInfo3 
	 * @Description:
	 * @param request
	 * @return ProcessResult         
	 * @throws
	 */
	public ProcessResult modifyUserInfo3(HttpServletRequest request) {
		ProcessResult pr = new ProcessResult();
		try{
			int user_id = Integer.parseInt(request.getParameter("user_id"));
			int sales_id = Integer.parseInt(request.getParameter("sales_id"));
			
			userInfoDao.modifyUserInfo3(user_id, sales_id);
			
			pr.setSuccess(true);
			pr.setMessage("保存成功");
		} catch (Exception ex) {
			pr.setSuccess(false);
			pr.setMessage(ex.getMessage());
		}
		return pr;
	}
	
	/**
	 * 查询用户详细信息
	 * @Title: queryUserInfo 
	 * @Description:
	 * @param request
	 * @return UserInfo         
	 * @throws
	 */
	public UserInfo queryUserInfo(HttpServletRequest request) {
		UserInfo userinfo = null;
		try {
			int user_id = Integer.parseInt(request.getParameter("user_id"));
			userinfo = userInfoDao.queryUserInfo(user_id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return userinfo;
	}
	
	/**
	 * 查询用户数量
	 * @Title: queryUserInfoCount 
	 * @Description:
	 * @param request
	 * @return int         
	 * @throws
	 */
	public int queryUserInfoCount(HttpServletRequest request) {
		int count = 0;
		try {
			String keyword = request.getParameter("keyword");
			count = userInfoDao.queryUserInfoCount(keyword);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return count;
	}
	
	/**
	 * 查询用户列表
	 * @Title: queryUserInfoList 
	 * @Description:
	 * @param request
	 * @return List<UserInfo>         
	 * @throws
	 */
	public List<UserInfo> queryUserInfoList(HttpServletRequest request) {
		List<UserInfo> list = null;
		try {
			String keyword = request.getParameter("keyword");
			int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
			int pageSize = Integer.parseInt(request.getParameter("pageSize"));
			list = userInfoDao.queryUserInfoList(keyword, pageIndex, pageSize);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 导出用户信息Excel
	 * @Title: exportUserInfo 
	 * @Description:
	 * @param request
	 * @return
	 * @throws Exception String         
	 * @throws
	 */
	public String exportUserInfo(HttpServletRequest request) throws Exception{
		String fileName = "";
		
		// 第一步，创建一个WebBook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在WebBook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("用户信息");		
		// 第三步，在sheet中添加表头第0行，注意老版本POI对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow((int) 0);
		// 第四步，创建单元格，并设置值表头
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_LEFT); // 创建一个居左格式
		
		HSSFCell cell = row.createCell(0);
		cell.setCellValue("姓名");
		cell.setCellStyle(style);
		
		cell = row.createCell(1);
		cell.setCellValue("邮箱");
		cell.setCellStyle(style);
		
		cell = row.createCell(2);
		cell.setCellValue("公司名称");
		cell.setCellStyle(style);
		
		cell = row.createCell(3);
		cell.setCellValue("行业属性");
		cell.setCellStyle(style);
		
		cell = row.createCell(4);
		cell.setCellValue("所属部门");
		cell.setCellStyle(style);

		cell = row.createCell(5);
		cell.setCellValue("电话");
		cell.setCellStyle(style);

		cell = row.createCell(6);
		cell.setCellValue("注册时间");
		cell.setCellStyle(style);
		
		// 第五步，写入实体数据
		String keyword = request.getParameter("keyword");
		List<UserInfo> list = userInfoDao.queryUserInfoList(keyword);

		for (int i = 0; i < list.size(); i++) {
			row = sheet.createRow((int) i + 1);
			UserInfo user = (UserInfo) list.get(i);
			// 第四步，创建单元格，并设置值
			row.createCell(0).setCellValue(user.getXm());
			row.createCell(1).setCellValue(user.getE_mail());
			row.createCell(2).setCellValue(user.getGsmc());
			row.createCell(3).setCellValue(user.getHysx());
			row.createCell(4).setCellValue(user.getSsbm());
			row.createCell(5).setCellValue(user.getPhone());
			row.createCell(6).setCellValue(user.getFmt_create_time());
		}
		// 第六步，将文件存到指定位置
		try {
			String path = request.getContextPath();
			String basePath = request.getScheme() + "://" + request.getServerName()
					+ ":" + request.getServerPort() + path;
			
			String realPath = request.getSession().getServletContext().getRealPath("/");
			String destDir = "/export/";
			String suffix = "xls";
			
			File destFile = new File(realPath + destDir);
			if (!destFile.exists()) {
				destFile.mkdirs();
			}
			String fileNameNew = Utils.getFileNameNew() + "." + suffix;

			FileOutputStream fout = new FileOutputStream(destFile.getAbsoluteFile() + "/" + fileNameNew);
			wb.write(fout);
			fout.close();
			
			fileName = basePath + destDir + fileNameNew;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileName;
	}
}