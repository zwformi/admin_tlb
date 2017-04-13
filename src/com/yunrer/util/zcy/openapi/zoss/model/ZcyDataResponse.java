package com.yunrer.util.zcy.openapi.zoss.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ZcyDataResponse<T> implements Serializable {
	
    private T data_response ;
    private T error_response ;
    private String errorMessage ;
	public T getData_response() {
		return data_response;
	}
	public void setData_response(T data_response) {
		this.error_response=null;
		this.errorMessage=null;
		this.data_response = data_response;
	}
	public T getError_response() {
		return error_response;
	}
	public void setError_response(T error_response) {
		this.data_response=null;
		this.errorMessage=null;
		this.error_response = error_response;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.data_response=null;
		this.error_response=null;
		this.errorMessage = errorMessage;
	}

}
