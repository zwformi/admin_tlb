package com.yunrer.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yunrer.common.Utils;
import com.yunrer.dao.UserCompanyDao;
import com.yunrer.entity.UserCompany;
import com.yunrer.entity.UserCompanyImg;
import com.yunrer.entity.UserInfo;
/**
 * 公司信息Service
 * @ClassName UserCompanyService
 * @Description 
 * @author rujun
 * @date 2016-12-21
 */
@Service("UserCompanyService")
public class UserCompanyService {
	
	@Resource
	private UserCompanyDao userCompanyDao;

	/**
	 * 获取用户的公司信息
	 * @Title: queryCompany 
	 * @Description:
	 * @param session
	 * @param request void         
	 * @throws
	 */
	public void queryCompany(HttpSession session,HttpServletRequest request) {
		UserInfo loginUser = (UserInfo)session.getAttribute("loginUser");
		//查询公司基本信息
			int owning_company = Integer.parseInt(request.getParameter("owning_company"));
			UserCompany userCompany = userCompanyDao.queryCompany(owning_company);
			request.setAttribute("USERCOMPANY", userCompany);	
			//查询公司资质图片
			List<UserCompanyImg> CompanyImgList = userCompanyDao.queryCompanyImgList(owning_company);
			request.setAttribute("COMPANYIMGLIST", CompanyImgList);

	}
	

	/**
	 * 获取用户的公司信息
	 * @Title: queryCompany 
	 * @Description:
	 * @param request
	 * @return UserCompany         
	 * @throws
	 */
	public UserCompany queryCompany(HttpServletRequest request) {
		UserCompany userCompany = null;
		try {
			int user_id = Integer.parseInt(request.getParameter("user_id"));
			userCompany = userCompanyDao.queryCompany(user_id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return userCompany;
	}
	
	/**
	 * 获取用户的公司资质
	 * @Title: queryCompanyImgList 
	 * @Description:
	 * @param request
	 * @return List<UserCompanyImg>         
	 * @throws
	 */
	public List<UserCompanyImg> queryCompanyImgList(HttpServletRequest request) {
		List<UserCompanyImg> list = null;
		try {
			int user_id = Integer.parseInt(request.getParameter("user_id"));
			list = userCompanyDao.queryCompanyImgList(user_id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 查询内购公司
	 * @Title: querySpecialCompany 
	 * @Description:
	 * @param keyword
	 * @return List         
	 * @throws
	 */
	public List<UserCompany> querySpecialCompany(HttpServletRequest request){
		List<UserCompany> list = null;
		try {
			String keyword = request.getParameter("keyword")==""?"有限":request.getParameter("keyword");
			
			//keyword过滤
			
			if(keyword!=""&&(keyword.length()>6||(keyword.indexOf("有")==-1&&keyword.indexOf("限")==-1&&keyword.indexOf("公")==-1&&keyword.indexOf("司")==-1))){
				System.out.println(keyword);
				list = userCompanyDao.querySpecialCompany(keyword);
				
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 保存公司信息
	 * @Title: saveCompany 
	 * @Description:
	 * @param userCompany
	 * @return Map<String,Object>         
	 * @throws
	 */
	@Transactional
	public Map<String,Object> saveCompany(UserCompany userCompany){
		Map<String,Object> map = new HashMap<String, Object>();
		
		//判断是否存在公司信息
		List<UserCompany> list = userCompanyDao.queryCriteriaCompany("gsmc ='"+userCompany.getGsmc()+"'");
		int id=0;
		if(list==null||list.size()==0){
			//新增公司信息
			id = userCompanyDao.addCompany(userCompany);
				map.put("count", 1);
				map.put("id", id);
		}else{
			map.put("error", true);
			map.put("message", "公司名称已存在..");
		}
		
		return map;
	}
	
	
	/**
	 * 修改公司信息
	 * @Title: saveCompany 
	 * @Description:
	 * @param userCompany
	 * @return Map<String,Object>         
	 * @throws
	 */
	@Transactional
	public Map<String,Object> modifyCompany(UserCompany userCompany){
		Map<String,Object> map = new HashMap<String, Object>();
		//判断是否存在公司信息
		List<UserCompany> list = userCompanyDao.queryCriteriaCompany("id ='"+userCompany.getId()+"'");
		if(list!=null && list.size()>0){
			UserCompany company = list.get(0);
			company.setSpecial_code(userCompany.getSpecial_code());
			if(userCompanyDao.updateCompany(company)>0){
				
				map.put("success", true);
				map.put("message", "修改成功");
			}else{
				
				map.put("error", true);
				map.put("message", "修改失败");
			}

			
		}else{
			
			map.put("error", true);
			map.put("message", "公司不存在");
		}
		
		return map;
	}
	
	
	/**
	 * 查询公司数量
	 * @Title: querycompanyCount 
	 * @Description:
	 * @param request
	 * @return int         
	 * @throws
	 */
	public int  querycompanyCount(HttpServletRequest request){
		String gsmc = request.getParameter("gsmc");
		String fddbr = request.getParameter("fddbr");
		String bgdz = request.getParameter("bgdz");
		
 		int count = userCompanyDao.queryUserCompanycount(gsmc,fddbr,bgdz);
		return count;
	}
	
	/**
	 * 按条件查询公司信息
	 * @Title: queryCompanyList 
	 * @Description:
	 * @param request
	 * @return List<UserCompany>         
	 * @throws
	 */
	public List<UserCompany>   queryCompanyList(HttpServletRequest request){
		List<UserCompany>  list = null;
		String gsmc = request.getParameter("gsmc");
		String fddbr = request.getParameter("fddbr");
		String bgdz = request.getParameter("bgdz");
		try{
			int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
			int pageSize = Integer.parseInt(request.getParameter("pageSize"));
			list = userCompanyDao.queryUserCompanyList(gsmc,fddbr,bgdz,pageIndex,pageSize);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 导出公司信息Excel
	 * @Title: exportCompanyInfo 
	 * @Description:
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception String         
	 * @throws
	 */
	public String exportCompanyInfo(HttpServletRequest request,HttpServletResponse response) throws Exception{
	    OutputStream out=response.getOutputStream();
        String fileName = "Company.xls";// 文件名  
		
		// 第一步，创建一个WebBook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在WebBook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("公司信息");		
		// 第三步，在sheet中添加表头第0行，注意老版本POI对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow((int) 0);
		// 第四步，创建单元格，并设置值表头
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_LEFT); // 创建一个居左格式
		
		String[] names = {"公司名称","营业执照注册号","注册资金","法定代表人","办公地址","员工人数"};
		String[] keys = {"Gsmc","Yyzzzch","Zczj","Fddbr","Bgdz","Staffs_number"};
		
		HSSFCell cell = null;
		for(int i=0;i<keys.length;i++){
			cell = row.createCell(i);
			cell.setCellValue(names[i]);
			cell.setCellStyle(style);
			
		}
		
		
		// 第五步，写入实体数据
		//有查询条件的情况
		String gsmc = request.getParameter("gsmc");
		String fddbr = request.getParameter("fddbr");
		String bgdz = request.getParameter("bgdz");
		List<UserCompany> list = userCompanyDao.queryUserCompanyList(gsmc,fddbr,bgdz,-1,0);

		for (int i = 0; i < list.size(); i++) {
			row = sheet.createRow((int) i + 1);
			UserCompany company = (UserCompany) list.get(i);
			// 第四步，创建单元格，并设置值
			row.createCell(0).setCellValue(company.getGsmc()==null?"":company.getGsmc());
			row.createCell(1).setCellValue(company.getYyzzzch()==null?"":company.getYyzzzch());
			row.createCell(2).setCellValue(company.getZczj()==null?"":company.getZczj());
			row.createCell(3).setCellValue(company.getFddbr()==null?"":company.getFddbr());
			row.createCell(4).setCellValue(company.getBgdz()==null?"":company.getBgdz());
			row.createCell(5).setCellValue(company.getStaffs_number()==null?0:company.getStaffs_number());
		}
		// 第六步，将文件存到指定位置
		try {
			
		    
		    response.setCharacterEncoding("UTF-8");
	        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
   

            response.setContentType("application/x-msdownload");  
            response.setHeader("Content-Disposition", "attachment; filename="  
                                                    + URLEncoder.encode(fileName, "UTF-8"));  
			   
			   wb.write(out);
			   out.flush();
			   out.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileName;
	}
}