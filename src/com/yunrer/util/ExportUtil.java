package com.yunrer.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * 导出Excel工具类
 * @ClassName ExportUtil
 * @Description TODO
 * @author maggieb
 * @date 2016-11-17
 */
public class ExportUtil {

	/**
	 * 导出excel通用方法(stringmap转码)
	 * @Title: excelExport 
	 * @Description:
	 * @param fileName 文件名
	 * @param sheetName 工作簿名
	 * @param names 表头数组
	 * @param keys 对应实体方法数组
	 * @param index 编号列 0 不需要 1 需要
	 * @param maplist 实体数据list
	 * @param response
	 * @return Map         
	 * @throws
	 */
	public static Map excelExport(String fileName,String sheetName,String[] names,String[] keys,int index,List<Map<String,Object>> maplist,HttpServletResponse response){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			 OutputStream out=response.getOutputStream();
			
			// 第一步，创建一个WebBook，对应一个Excel文件
			HSSFWorkbook wb = new HSSFWorkbook();
			// 第二步，在WebBook中添加一个sheet,对应Excel文件中的sheet
			HSSFSheet sheet = wb.createSheet(sheetName);		
			// 第三步，在sheet中添加表头第0行，注意老版本POI对Excel的行数列数有限制short
			HSSFRow row = sheet.createRow((int) 0);
			// 第四步，创建单元格，并设置值表头
			HSSFCellStyle style = wb.createCellStyle();
			style.setAlignment(HSSFCellStyle.ALIGN_LEFT); // 创建一个居左格式
			
			//填充表头
			HSSFCell cell = null;
			if(index ==1){
			cell = row.createCell(0);
			cell.setCellValue("序号");
			cell.setCellStyle(style);
			}
			for(int i=0;i<keys.length;i++){
				
				if(index==1){
					cell = row.createCell(i+1);
					cell.setCellValue(names[i]);
					cell.setCellStyle(style);
				
				}else{
					cell = row.createCell(i);
					cell.setCellValue(names[i]);
					cell.setCellStyle(style);
				}
			}

			for(int i=0;i<maplist.size();i++){
				row = sheet.createRow((int) i + 1);
				if(index==1) row.createCell(0).setCellValue(i+1);
				for(int j=0;j<keys.length;j++){
					Object obj = maplist.get(i).get(keys[j]);
					//取值
					obj = obj==null?"":obj;
					if(index==1)
					row.createCell(j+1).setCellValue(obj.toString());
					else
					row.createCell(j).setCellValue(obj.toString());
				}

			}
			
			//第六步，将文件存放到指定位置
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
			map.put("error", true);
			map.put("message","导出Excel失败！");
			
		}
		return map;
		
	}
	
	
	/**
	 * 导出excel通用方法2:直接遍历实体的情况(无法转换stringmap)
	 * @param fileName  文件名
	 * @param sheetName  工作簿名
	 * @param list  实体数据list
	 * @param names  表头数组
	 * @param keys  对应实体方法数组(首字母大写)
	 * @param index  编号列 0 不需要 1 需要
	 */
	public static Map excelExportforEntity(String fileName,String sheetName,String[] names,String[] keys,int index,List<?> list,HttpServletResponse response){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			 OutputStream out=response.getOutputStream();
			
			// 第一步，创建一个WebBook，对应一个Excel文件
			HSSFWorkbook wb = new HSSFWorkbook();
			// 第二步，在WebBook中添加一个sheet,对应Excel文件中的sheet
			HSSFSheet sheet = wb.createSheet(sheetName);		
			// 第三步，在sheet中添加表头第0行，注意老版本POI对Excel的行数列数有限制short
			HSSFRow row = sheet.createRow((int) 0);
			// 第四步，创建单元格，并设置值表头
			HSSFCellStyle style = wb.createCellStyle();
			style.setAlignment(HSSFCellStyle.ALIGN_LEFT); // 创建一个居左格式
			
			//填充表头
			HSSFCell cell = null;
			if(index ==1)
			cell = row.createCell(0);
			cell.setCellValue("序号");
			cell.setCellStyle(style);
			for(int i=0;i<keys.length;i++){
				
				if(index==1){
					cell = row.createCell(i+1);
					cell.setCellValue(names[i]);
					cell.setCellStyle(style);
				
				}else{
					cell = row.createCell(i);
					cell.setCellValue(names[i]);
					cell.setCellStyle(style);
				}
			}

			for(int i=0;i<list.size();i++){
				row = sheet.createRow((int) i + 1);
				if(index==1) row.createCell(0).setCellValue(i+1);
				for(int j=0;j<keys.length;j++){
				   //利用反射
					Method method;
						method = list.get(i).getClass().getMethod("get"+keys[j]);
							Object obj =  method.invoke(list.get(i));
					//取值
					obj = obj==null?"":obj;
					if(index==1)
					row.createCell(j+1).setCellValue(obj.toString());
					else
					row.createCell(j).setCellValue(obj.toString());
				}

			}
			
			//第六步，将文件存放到指定位置
			response.setCharacterEncoding("UTF-8");
	        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
   

            response.setContentType("application/x-msdownload");  
            response.setHeader("Content-Disposition", "attachment; filename="  
                                                    + URLEncoder.encode(fileName, "UTF-8"));  
			   
			   wb.write(out);
			   out.flush();
			   out.close();
			
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("error", true);
			map.put("message","导出Excel失败！");
			
		}
		return map;
		
	}
		
	
	/**
	 * 导出excel通用方法(stringmap转码)
	 * @Title: excelExport 
	 * @Description:
	 * @param fileName 文件名
	 * @param sheetName 工作簿名
	 * @param names 表头数组
	 * @param keys 对应实体方法数组
	 * @param index 编号列 0 不需要 1 需要
	 * @param maplist 实体数据list
	 * @param response
	 * @return Map         
	 * @throws
	 */
	public static void excelExportForStream(String path,String fileName,String sheetName,String[] names,String[] keys,int index,List<Map<String,Object>> maplist,HttpServletResponse response){
		Map<String,Object> map = new HashMap<String,Object>();
		File file = new File(path+"\\"+fileName); 
		FileOutputStream fos  = null;
		try {
			fos = new FileOutputStream(file);
			// 第一步，创建一个WebBook，对应一个Excel文件
			HSSFWorkbook wb = new HSSFWorkbook();
			// 第二步，在WebBook中添加一个sheet,对应Excel文件中的sheet
			HSSFSheet sheet = wb.createSheet(sheetName);		
			// 第三步，在sheet中添加表头第0行，注意老版本POI对Excel的行数列数有限制short
			HSSFRow row = sheet.createRow((int) 0);
			// 第四步，创建单元格，并设置值表头
			HSSFCellStyle style = wb.createCellStyle();
			style.setAlignment(HSSFCellStyle.ALIGN_LEFT); // 创建一个居左格式
			
			//填充表头
			HSSFCell cell = null;
			if(index ==1){
			cell = row.createCell(0);
			cell.setCellValue("序号");
			cell.setCellStyle(style);
			}
			for(int i=0;i<keys.length;i++){
				
				if(index==1){
					cell = row.createCell(i+1);
					cell.setCellValue(keys[i]);
					cell.setCellStyle(style);
				
				}else{
					cell = row.createCell(i);
					cell.setCellValue(keys[i]);
					cell.setCellStyle(style);
				}
			}

			for(int i=0;i<maplist.size();i++){
				row = sheet.createRow((int) i + 1);
				if(index==1) row.createCell(0).setCellValue(i+1);
				for(int j=0;j<keys.length;j++){
					Object obj = maplist.get(i).get(keys[j]);
					//取值
					obj = obj==null?"":obj;
					if(index==1)
					row.createCell(j+1).setCellValue(obj.toString());
					else
					row.createCell(j).setCellValue(obj.toString());
				}

			}
//			
//			//第六步，将文件存放到指定位置
//			response.setCharacterEncoding("UTF-8");
//	        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
//   
//
//            response.setContentType("application/x-msdownload");  
//            response.setHeader("Content-Disposition", "attachment; filename="  
//                                                    + URLEncoder.encode(fileName, "UTF-8"));  
			   
			   wb.write(fos);
			   fos.flush();
			 
	
		} catch (Exception e) {
			
			e.printStackTrace();
			map.put("error", true);
			map.put("message","导出Excel失败！");
			
		}finally{
			try{
				if(fos!=null)
				fos.close();
				
			}catch(Exception e){
				
				
			}
			
		}
		
	}
	
}
