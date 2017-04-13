package com.yunrer.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


	public class Test {  
		  
	    /** 
	     * 使用Get方式获取数据 
	     *  
	     * @param url 
	     *            URL包括参数，http://HOST/XX?XX=XX&XXX=XXX 
	     * @param charset 
	     * @return 
	     */  
	    public static String sendGet(String url, String charset) {  
	        String result = "";  
	        BufferedReader in = null;  
	        try {  
	            URL realUrl = new URL(url);  
	            // 打开和URL之间的连接  
	            URLConnection connection = realUrl.openConnection();  
	            // 设置通用的请求属性  
	            connection.setRequestProperty("accept", "*/*");  
	            connection.setRequestProperty("connection", "Keep-Alive");  
	            connection.setRequestProperty("user-agent",  
	                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");  
	            // 建立实际的连接  
	            connection.connect();  
	            // 定义 BufferedReader输入流来读取URL的响应  
	            in = new BufferedReader(new InputStreamReader(  
	                    connection.getInputStream(), charset));  
	            String line;  
	            while ((line = in.readLine()) != null) {  
	                result += line;  
	            }  
	        } catch (Exception e) {  
	            System.out.println("发送GET请求出现异常！" + e);  
	            e.printStackTrace();  
	        }  
	        // 使用finally块来关闭输入流  
	        finally {  
	            try {  
	                if (in != null) {  
	                    in.close();  
	                }  
	            } catch (Exception e2) {  
	                e2.printStackTrace();  
	            }  
	        }  
	        return result;  
	    }  
	  
	    /**  
	     * POST请求，字符串形式数据  
	     * @param url 请求地址  
	     * @param param 请求数据  
	     * @param charset 编码方式  
	     */  
	    public static String sendPostUrl(String url, String param, String charset) {  
	  
	        PrintWriter out = null;  
	        BufferedReader in = null;  
	        String result = "";  
	        try {  
	            URL realUrl = new URL(url);  
	            // 打开和URL之间的连接  
	            URLConnection conn = realUrl.openConnection();  
	            // 设置通用的请求属性  
	            conn.setRequestProperty("accept", "*/*");  
	            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); 
	            conn.setRequestProperty("connection", "Keep-Alive"); 
	            conn.setRequestProperty("Origin", "http://login.staging.zcy.gov.cn"); 
	            conn.setRequestProperty("Referer", "http://login.staging.zcy.gov.cn/login"); 
	            conn.setRequestProperty("Upgrade-Insecure-Requests", "1"); 
	            conn.setRequestProperty("user-agent",  
	                    "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.10 Safari/537.36");  
	            // 发送POST请求必须设置如下两行  
	            conn.setDoOutput(true);  
	            conn.setDoInput(true);  
	            // 获取URLConnection对象对应的输出流  
	            out = new PrintWriter(conn.getOutputStream());  
	            // 发送请求参数  
	            out.print(param);  
	            // flush输出流的缓冲  
	            out.flush();  
	            // 定义BufferedReader输入流来读取URL的响应  
	            in = new BufferedReader(new InputStreamReader(  
	                    conn.getInputStream(), charset));  
	            String line;  
	            while ((line = in.readLine()) != null) {  
	                result += line;  
	                System.out.println(line);
	            }  
	        } catch (Exception e) {  
	            System.out.println("发送 POST 请求出现异常！" + e);  
	            e.printStackTrace();  
	        }  
	        // 使用finally块来关闭输出流、输入流  
	        finally {  
	            try {  
	                if (out != null) {  
	                    out.close();  
	                }  
	                if (in != null) {  
	                    in.close();  
	                }  
	            } catch (IOException ex) {  
	                ex.printStackTrace();  
	            }  
	        }  
	        return result;  
	    }  
	    /**  
	     * POST请求，Map形式数据  
	     * @param url 请求地址  
	     * @param param 请求数据  
	     * @param charset 编码方式  
	     */  
	    public static String sendPost(String url, Map<String, String> param,  
	            String charset) {  
	  
	        StringBuffer buffer = new StringBuffer();  
	        if (param != null && !param.isEmpty()) {  
	            for (Map.Entry<String, String> entry : param.entrySet()) {  
	                buffer.append(entry.getKey()).append("=")  
	                        .append(URLEncoder.encode(entry.getValue()))  
	                        .append("&");  
	  
	            }  
	        }  
	        buffer.deleteCharAt(buffer.length() - 1);  
	  
	        PrintWriter out = null;  
	        BufferedReader in = null;  
	        String result = "";  
	        try {  
	            URL realUrl = new URL(url);  
	            // 打开和URL之间的连接  
	            URLConnection conn = realUrl.openConnection();  
	            // 设置通用的请求属性  
	            conn.setRequestProperty("accept", "*/*");  
	            conn.setRequestProperty("connection", "Keep-Alive");  
	            conn.setRequestProperty("user-agent",  
	                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");  
	            // 发送POST请求必须设置如下两行  
	            conn.setDoOutput(true);  
	            conn.setDoInput(true);  
	            // 获取URLConnection对象对应的输出流  
	            out = new PrintWriter(conn.getOutputStream());  
	            // 发送请求参数  
	            out.print(buffer);  
	            // flush输出流的缓冲  
	            out.flush();  
	            // 定义BufferedReader输入流来读取URL的响应  
	            in = new BufferedReader(new InputStreamReader(  
	                    conn.getInputStream(), charset));  
	            String line;  
	            while ((line = in.readLine()) != null) {  
	                result += line;  
	            }  
	        } catch (Exception e) {  
	            System.out.println("发送 POST 请求出现异常！" + e);  
	            e.printStackTrace();  
	        }  
	        // 使用finally块来关闭输出流、输入流  
	        finally {  
	            try {  
	                if (out != null) {  
	                    out.close();  
	                }  
	                if (in != null) {  
	                    in.close();  
	                }  
	            } catch (IOException ex) {  
	                ex.printStackTrace();  
	            }  
	        }  
	        return result;  
	    }  
	  
	    public static void main(String[] args) { 
	    	Map<String,String> param = new HashMap<String, String>();
	    	param.put("username","ZJZDWXTLXX");
	    	param.put("password","zcy@test");
	    	System.out.println(Test.sendPost("http://login.staging.zcy.gov.cn/login", param, "utf-8"));
	    }  
	}  
	


