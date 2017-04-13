package com.yunrer.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.yunrer.dao.ProductDao;
import com.yunrer.entity.Product;
import com.yunrer.entity.ProductType;
import com.yunrer.service.OrderService;
import com.yunrer.service.ZcyService;
import com.yunrer.util.ExportUtil;
import com.yunrer.util.ZcyCommonUtil;
import com.yunrer.util.ZcyConfig;
import com.yunrer.util.zcy.openapi.ZcyOpenClient;
import com.yunrer.util.zcy.openapi.ZcyOpenRequest;
import com.yunrer.util.zcy.openapi.http.HttpMethod;
import com.yunrer.util.zcy.openapi.zoss.model.ZcyDataResponse;
import com.yunrer.util.zcy.openapi.zoss.model.ZcyResponse;
import com.yunrer.util.zcy.openapi.zoss.util.JsonUtil;

public class ZcyServiceDemo {
//	private static StringBuffer sb =new StringBuffer();
	
	
	
	
	@SuppressWarnings("rawtypes")
	public  static ZcyResponse<LinkedHashMap> getZcyResponse(JSONObject jsonObject,String path) throws Exception {
        String result = ZcyCommonUtil.doPost(jsonObject, path);
        ZcyResponse<LinkedHashMap> responsebody = JsonUtil.JSON_NON_EMPTY_MAPPER.fromJson(result, ZcyResponse.class);
        /*打印返回结果*/
        return responsebody;
    }
	
	@SuppressWarnings("rawtypes")
	public  static ZcyDataResponse<LinkedHashMap> getZcyDataResponse(JSONObject jsonObject,String path) throws Exception {
        String result = ZcyCommonUtil.doPost(jsonObject, path);
        ZcyDataResponse<LinkedHashMap> responsebody = JsonUtil.JSON_NON_EMPTY_MAPPER.fromJson(result, ZcyDataResponse.class);
        /*打印返回结果*/
        return responsebody;
    }

	@SuppressWarnings("rawtypes")
	public static Map<String,Object> getFullMenu(String path,String fileName,String sheetName) {
		Map<String,Object> map = new HashMap<String, Object>();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("root", 0);
        jsonObject.put("depth",4);
		try {
			ZcyResponse<LinkedHashMap> responsebody = getZcyResponse(jsonObject, "zcy.category.get");
			if(responsebody.isSuccess()){
				String data =JSONUtils.toJSONString( responsebody.getResult());
				JSONObject jsondata = new JSONObject(data);
				String str = scanJson(jsondata,"0");
				str = "["+str.substring(0, str.length()-1)+"]";
				//模板
				String jsonStr = "{\"id\":1,\"parentId\":0,\"sub_parentId\":0,\"product_level\":1,\"name\":\"办公家具\"}";
				JSONObject jsonObj = new JSONObject(jsonStr);
				Iterator<String> it = jsonObj.keys();
				List<String> list = new ArrayList<String>(); 
				while(it.hasNext()){
					String aaa = it.next();
					list.add(aaa);
				}
					String[] arr = new String[list.size()];
					list.toArray(arr);
				JSONArray jsonArr = new JSONArray(str);
				List<Map<String,Object>> maplist = new ArrayList<Map<String,Object>>();
				
				for (Object object : jsonArr) {
					Map<String,Object> map1 = new HashMap<String, Object>();
					JSONObject j = new JSONObject(object.toString());
					Iterator<String> it1 = j.keys();
					while(it1.hasNext()){
						String key = it1.next();
						map1.put(key, j.get(key));
						
					}
					maplist.add(map1);
				}
				
				ExportUtil.excelExportForStream(path,fileName,sheetName, null, arr, 1, maplist, null);
				map.put("resultCode", 1);
				map.put("json", new JSONArray(str));
			}else {
				map.put("resultCode", 0);
				map.put("resultMsg", responsebody.getError());
				System.out.println(responsebody.getError());
			}
		} catch (Exception e) {
			map.put("resultCode", 0);
			map.put("resultMsg", e.getMessage());
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		return map;
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

	@SuppressWarnings("rawtypes")
	@Transactional
	public  void getFullMenuToLocal(int pid) {
		Map<String,Object> map = new HashMap<String, Object>();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("root", 0);
        jsonObject.put("depth",4);
			ZcyResponse<LinkedHashMap> responsebody = null;
			try {
				responsebody = getZcyResponse(jsonObject, "zcy.category.get");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(responsebody.isSuccess()){
				String data =JSONUtils.toJSONString( responsebody.getResult());
				JSONObject jsondata = new JSONObject(data);
				String str = scanJson(jsondata,"0");
				str = "["+str.substring(0, str.length()-1)+"]";
				File file = new File("f:\\desktop\\fullmenu_"+new SimpleDateFormat("yyyy_mm_dd").format(new Date())+".json");
				try {
					@SuppressWarnings("resource")
					FileOutputStream fos = new FileOutputStream(file);
					fos.write(str.getBytes());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				
	}

	
	
	
	
	
	public static void main(String[] args) {
//1.....更改库存
		JSONObject 	json = new JSONObject();
		JSONObject 	stock = new JSONObject();
		stock.put("skuCode", "tulingbuy-sku-2088");
		JSONArray operations = new JSONArray();
		JSONObject item = new JSONObject().put("quantity", 10).put("warehouseCode","1");
		operations.put(item);
		stock.put("operations", operations);
		json.put("stock", stock);
		System.out.println(json);
		System.out.println(ZcyCommonUtil.doPost(json, "zcy.item.stock.update"));
		
//		ZcyService z = new ZcyService();
//		ProductDao dao = new ProductDao();
//		Product p = dao.queryProduct(19178);
//		z.addProduct(p, 0);
		
		
		
//		getFullMenu("f:\\desktop", "zcymenu_"+new SimpleDateFormat("yyyy_MM_dd").format(new Date())+".xls", "menu");
		
		
//		
//		JSONObject json = new JSONObject().put("itemCode", "tulingbuy-spu-19187");
//		System.out.println(ZcyCommonUtil.doPost(json, "zcy.item.detail.get"));
		
	}
}
