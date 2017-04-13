package com.yunrer.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.yunrer.util.zcy.openapi.ZcyOpenClient;
import com.yunrer.util.zcy.openapi.ZcyOpenRequest;
import com.yunrer.util.zcy.openapi.http.HttpMethod;
import com.yunrer.util.zcy.openapi.zoss.model.ZcyResponse;
import com.yunrer.util.zcy.openapi.zoss.util.JsonUtil;

public class ZcyCommonUtil {
	
	private final static int CONNECT_TIMEOUT = 5000; //等待延迟
    private final static String DEFAULT_ENCODING = "UTF-8";  

	/**
	 * 政采云网络请求工具（get）
	 * @param jsonObject
	 * @param path
	 * @return
	 */
	public static String doGet(JSONObject jsonObject,String path){
		String uri = ZcyConfig.instance().NAME_SPACE+path;

        ZcyOpenRequest zcyOpenRequest = new ZcyOpenRequest( ZcyConfig.instance().APP_KEY,
				ZcyConfig.instance().SECRET, 
				ZcyConfig.instance().API_GATEWAY);
        zcyOpenRequest.setUri(uri);
        zcyOpenRequest.setMethod(HttpMethod.GET);
        /*发送http request*/
        String result = null;
		try {
			result = ZcyOpenClient.excute(zcyOpenRequest);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	        return result;
		
	}
	/**
	 * 政采云网络请求工具（post）
	 * @param jsonObject
	 * @param path
	 * @return
	 */
	public static String doPost(JSONObject jsonObject,String path){
		String uri = ZcyConfig.instance().NAME_SPACE+path;
		
		ZcyOpenRequest zcyOpenRequest = new ZcyOpenRequest( ZcyConfig.instance().APP_KEY,
				ZcyConfig.instance().SECRET, 
				ZcyConfig.instance().API_GATEWAY);
		zcyOpenRequest.setUri(uri);
		zcyOpenRequest.setMethod(HttpMethod.POST);
		
		/*组装Body参数*/
		Map<String, Object> bodyMap = new HashMap<String, Object>();
		bodyMap.put("_data_", jsonObject.toString());
		zcyOpenRequest.setParamMap(bodyMap);
		
		/*发送http request*/
		String result ="";
		try {
			result = ZcyOpenClient.excute(zcyOpenRequest);
//		        System.out.println(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		
		return result;
		
	}

      
    public static String postData(String urlStr, String data){  
        return postData(urlStr, data, null);  
    }  
      
    public static String postData(String urlStr, String data, String contentType){  
        BufferedReader reader = null;  
        try {  
            URL url = new URL(urlStr);  
            URLConnection conn = url.openConnection();  
            conn.setDoOutput(true);  
            conn.setConnectTimeout(CONNECT_TIMEOUT);  
            conn.setReadTimeout(CONNECT_TIMEOUT);  
            if(contentType != null)  
                conn.setRequestProperty("content-type", contentType);  
            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream(), DEFAULT_ENCODING);  
            if(data == null)  
                data = "";  
            writer.write(data);   
            writer.flush();  
            writer.close();    
  
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), DEFAULT_ENCODING));  
            StringBuilder sb = new StringBuilder();  
            String line = null;  
            while ((line = reader.readLine()) != null) {  
                sb.append(line);  
                sb.append("\r\n");  
            }  
            return sb.toString();  
        } catch (IOException e) {  
            System.err.println("Error connecting to " + urlStr + ": " + e.getMessage());  
        } finally {  
            try {  
                if (reader != null)  
                    reader.close();  
            } catch (IOException e) {  
            }  
        }  
        return null;  
    }  
	
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
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
    
	@SuppressWarnings("rawtypes")
	public  static ZcyResponse<LinkedHashMap> getZcyResponse(JSONObject jsonObject,String path) throws Exception {
        String result = ZcyCommonUtil.doPost(jsonObject, path);
        ZcyResponse<LinkedHashMap> responsebody = JsonUtil.JSON_NON_EMPTY_MAPPER.fromJson(result, ZcyResponse.class);
        /*打印返回结果*/
        return responsebody;
    }
	
	
	public static String  scanJson( JSONObject json,String pid){
		JSONObject jsonNode = new JSONObject(json.get("node").toString());
		StringBuffer sb = new StringBuffer();
		if(Integer.parseInt(jsonNode.get("level").toString())>0){
			int level = Integer.parseInt(jsonNode.get("level").toString());
			int sub_parentId=0;
			int parentId=0;
			switch (level) {
			case 1:
				parentId = 0;
				sub_parentId = 0;
				break;
			case 2:
				parentId = jsonNode.getInt("pid");
				sub_parentId = 0;
				break;
			case 3:
				parentId = Integer.parseInt(pid);
				sub_parentId = jsonNode.getInt("pid");
				break;

			default:
				break;
			}
			//执行本层的业务流程
			
			String  str = jsonNode.put("ppid", pid).put("product_level", jsonNode.getInt("level")).put("sub_parentId", sub_parentId).put("parentId",parentId).toString()+",";
			int length = str.length();
			sb.append(str);
			
		}
		
		
		//递归子项
		if((Boolean)jsonNode.get("hasChildren")){
			JSONArray jsonSons = json.getJSONArray("children");
			for (Object object : jsonSons) {
				JSONObject j = new JSONObject(object.toString());
				sb.append(scanJson(j,jsonNode.get("pid").toString()));
			}
			
		}
		return sb.toString();
	}
}
