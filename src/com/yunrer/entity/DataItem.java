package com.yunrer.entity;

/**
 * 快递接口信息 实体类
 * @author wangpeng
 *
 */
public class DataItem
{
	/**  */
	private String time;
	public String getTime(){
 		return this.time;
	}

	public void setTime(String time){
 		this.time = time;
	}
	/** 已签收,感谢使用顺丰,期待再次为您服务 */
	private String context;
	public String getContext(){
 		return this.context;
	}

	public void setContext(String context){
 		this.context = context;
	}
	/**  */
	private String ftime;
	public String getFtime(){
 		return this.ftime;
	}

	public void setFtime(String ftime){
 		this.ftime = ftime;
	}

}


