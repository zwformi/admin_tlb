package com.yunrer.entity;

import java.util.List;
/**
 * 快递信息 实体类
 * @author zgf
 *
 */
public class PostData
{
	/**  */
	private String message;
	public String getMessage(){
 		return this.message;
	}

	public void setMessage(String message){
 		this.message = message;
	}
	/**  */
	private String nu;
	public String getNu(){
 		return this.nu;
	}

	public void setNu(String nu){
 		this.nu = nu;
	}
	/**  */
	private String ischeck;
	public String getIscheck(){
 		return this.ischeck;
	}

	public void setIscheck(String ischeck){
 		this.ischeck = ischeck;
	}
	/**  */
	private String com;
	public String getCom(){
 		return this.com;
	}

	public void setCom(String com){
 		this.com = com;
	}
	/**  */
	private String status;
	public String getStatus(){
 		return this.status;
	}

	public void setStatus(String status){
 		this.status = status;
	}
	/**  */
	private String condition;
	public String getCondition(){
 		return this.condition;
	}

	public void setCondition(String condition){
 		this.condition = condition;
	}
	/**  */
	private String state;
	public String getState(){
 		return this.state;
	}

	public void setState(String state){
 		this.state = state;
	}
	/**  */
	private List<DataItem> data;
	public List<DataItem> getData(){
 		return this.data;
	}

	public void setData(List<DataItem> data){
 		this.data = data;
	}

	
	@Override
	public String toString() {
		return "PostData [message=" + message + ", nu=" + nu + ", ischeck="
				+ ischeck + ", com=" + com + ", status=" + status
				+ ", condition=" + condition + ", state=" + state + ", data="
				+ data + ", getMessage()=" + getMessage() + ", getNu()="
				+ getNu() + ", getIscheck()=" + getIscheck() + ", getCom()="
				+ getCom() + ", getStatus()=" + getStatus()
				+ ", getCondition()=" + getCondition() + ", getState()="
				+ getState() + ", getData()=" + getData() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

}
