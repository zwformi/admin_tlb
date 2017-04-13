package com.yunrer.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;

public class ConnectionUtil {	
	public static String postRequest(String url, Map<String,String> paramsMap) throws Exception{
		int count=(paramsMap==null?0:paramsMap.size());
		int mapSize=count;
		String param="";
		if(count>0)
		for(Map.Entry<String, String> entry:paramsMap.entrySet()){    
			if(count>0){
				
				if(count<mapSize)
					param += "&" ;
					param += entry.getKey() +"=" + URLEncoder.encode(entry.getValue(),"UTF-8");
			}
		     count--;
		} 

        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
        	System.out.println(param);
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            out = new PrintWriter(conn.getOutputStream());
            out.print(param);
            out.flush();
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(),"utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
            throw new Exception();
        }
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        System.out.println(result); 	
		return result;
	}
}
