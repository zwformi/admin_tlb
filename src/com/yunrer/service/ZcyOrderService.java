package com.yunrer.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.yunrer.util.BaseLogger;
import com.yunrer.util.ZcyCommonUtil;

@Service("ZcyOrderService")
public class ZcyOrderService extends BaseLogger {

	
	/**
	 * 从政采云获取订单列表
	 * @param start
	 * @param end
	 * @param status
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Map<String,Object> getOrderList(String start,String end,Integer status,Integer pageNo,Integer pageSize){
		JSONObject json = new JSONObject();
		Map<String,Object> map = new HashMap<String, Object>();
		try{
			json.put("start", start);//必填
			json.put("status", status);//必填
			if(end!=null&&!end.isEmpty())
			json.put("end", end);//非必填
			if(pageNo!=null)
			json.put("pageNo", pageNo);//非必填
			if(pageSize!=null)
			json.put("pageSize", pageSize);//非必填
			
			String result = ZcyCommonUtil.doPost(json, "zcy.order.search");
			
			json = new JSONObject(result);
			if(json.has("success")&&!json.getBoolean("success")||json.has("error")){//失败
				
				map.put("resultCode", 0);
				if(json.has("error"))
				map.put("resultMsg", json.getString("error"));
			}else{//成功
				map.put("resultCode", 1);
				json = json.getJSONObject("result");
				if(json.has("data"))
				map.put("data", json.getJSONArray("data").toString());
				if(json.has("total"))
					map.put("total", json.getInt("total"));
				
				
			}
			
		}catch(Exception e){
			map.put("resultCode", 0);
			map.put("resultMsg", "获取订单列表出现错误..");
			logger.error(e);
		}
		
		
		return map;
	}
	/**
	 * 从政采云获取订单详情
	 * @param start
	 * @param end
	 * @param status
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Map<String,Object> getOrderDetail(Long id){
		JSONObject json = new JSONObject();
		Map<String,Object> map = new HashMap<String, Object>();
		try{
			json.put("id", id);//必填
			
			String result = ZcyCommonUtil.doPost(json, "zcy.order.get");
			json = new JSONObject(result);
			if(json.has("success")&&!json.getBoolean("success")||json.has("error")){//失败
				
				map.put("resuleCode", 0);
				if(json.has("error"))
					map.put("resultMsg", json.getString("error"));
			}else{//成功
				map.put("resuleCode", 1);
				if(json.has("result")){
					
					json = json.getJSONObject("result");
					map.put("result",json.toString());
					
				}
				
				
			}
			
		}catch(Exception e){
			map.put("resultCode", 0);
			map.put("resultMsg", "获取订单列表出现错误..");
			logger.error(e);
		}
		
		
		return map;
	}
	
	/**
	 * 接受/确认政采云订单
	 * @param start
	 * @param end
	 * @param status
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Map<String,Object> acceptOrder(Long id){
		JSONObject json = new JSONObject();
		Map<String,Object> map = new HashMap<String, Object>();
		try{
			json.put("id", id);//必填
			
			String result = ZcyCommonUtil.doPost(json, "zcy.order.accept");
			json = new JSONObject(result);
			if(json.has("success")&&!json.getBoolean("success")||json.has("error")){//失败
				
				map.put("resuleCode", 0);
				if(json.has("error"))
					map.put("resultMsg", json.getString("error"));
			}else{//成功
				map.put("resuleCode", 1);
				if(json.has("result")){
					
					json = json.getJSONObject("result");
					map.put("result",json.toString());
					
				}
				
				
			}
			
		}catch(Exception e){
			map.put("resultCode", 0);
			map.put("resultMsg", "获取订单列表出现错误..");
			logger.error(e);
		}
		
		
		return map;
	}
	
	
	
	/**
	 * 查询已经确认的发货单ID
	 * @param start
	 * @param end
	 * @return
	 */
	public Map<String,Object> getConfirmWayBill(String start ,String end){
		Map<String,Object> map = new HashMap<String, Object>();
		try{
		JSONObject json = new JSONObject().put("start", start).put("end", end);
		String result = ZcyCommonUtil.doPost(json, "zcy.order.waybill.confirm.search");
		json = new JSONObject(result);
		if(json.has("success")&&!json.getBoolean("success")||json.has("error")){//失败
			
			map.put("resuleCode", 0);
			if(json.has("error"))
				map.put("resultMsg", json.getString("error"));
		}else{//成功
			map.put("resuleCode", 1);
			if(json.has("result")){
				 json =  json.getJSONObject("result");
				map.put("total",json.getInt("total"));
				map.put("data",json.getJSONArray("data").toString());
				
			}
			
			
		}
		
	}catch(Exception e){
		map.put("resultCode", 0);
		map.put("resultMsg", "获取已确认发货单列表出现错误..");
		logger.error(e);
	}
	
	
	return map;
	}
	
	
	/**
	 * 查询发货单详情detail
	 * @param start
	 * @param end
	 * @return
	 */
	public Map<String,Object> getWayBillDetail(Long id){
		Map<String,Object> map = new HashMap<String, Object>();
		try{
			JSONObject json = new JSONObject().put("id", id);
			String result = ZcyCommonUtil.doPost(json, "zcy.order.waybill.get");
			json = new JSONObject(result);
			if(json.has("success")&&!json.getBoolean("success")||json.has("error")){//失败
				
				map.put("resuleCode", 0);
				if(json.has("error"))
					map.put("resultMsg", json.getString("error"));
			}else{//成功
				map.put("resuleCode", 1);
				System.out.println(json);
				if(json.has("result")){
					json =  json.getJSONObject("result");
					map.put("data",json.toString());
					
				}
				
				
			}
			
		}catch(Exception e){
			map.put("resultCode", 0);
			map.put("resultMsg", "获取已确认发货单详情出现错误..");
			logger.error(e);
		}
		
		
		return map;
	}
}
