package com.yunrer.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * 规范时间工具类
 * @ClassName FormatTime
 * @Description 
 */
public class FormatTime {
	public static String getFormartDate(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
		return sdf.format(date);
	}
	
	public static String getFormartDateCN(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年 MM月 dd日");
		return sdf.format(date);
	}
	
	public static String getFormartDateCN(){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年 MM月 dd日");
		return sdf.format(date);
	}
	
	public static String getFormartDate(){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
		return sdf.format(date);
	}
	public static String getFormatTime(){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
	public static String getStartDate(){
	        //获取前月的第一天
	        Calendar   calendar =Calendar.getInstance();//获取当前日期 
	        calendar.add(Calendar.MONTH, 0);
	        calendar.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
	        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
			String date = sdf.format(calendar.getTime());
	       
		return date;
	}
	
	public static String getEndDate(){
        //获取前月的第一天
        Calendar   calendar =Calendar.getInstance();//获取当前日期 
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH,0);//设置为1号,当前日期既为本月第一天 
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
		String date = sdf.format(calendar.getTime());
       
	return date;
}
	public static int getDays(String current,String start){
		int num = Integer.parseInt(current.split("-")[2]) - Integer.parseInt(start.split("-")[2])-1;
		return num;
	}
	public static Date formatDate(String time){
		//字符串转标准化时间
			SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd hh:mm:ss");
			Date date = new Date() ;
			try {
				date = sdf.parse(time);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return date;
			}
}
