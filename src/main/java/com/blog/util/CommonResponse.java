package com.blog.util;

import java.io.Serializable;


public class CommonResponse implements Serializable{
	
	 
	private static final long serialVersionUID = 1L;
	 
      private Integer status;
      
	  private String message;
	
	  private Object data;
	  
	  
	public CommonResponse() {
		super();
	}


	public CommonResponse(Integer status, Object data) {
		super();
		this.status = status;
		this.data = data;
	}
	


	public CommonResponse(Integer status, String message) {
		super();
		this.status = status;
		this.message = message;
	}


	public CommonResponse(Integer status, String message, Object data) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
	}
	
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "CommonResponse [status=" + status 
				+ ", message=" + message 
				+ ", data=" + data + "]";
	}
	  
	
	  
	

}
