package io.jdbc.app.response;

public class BaseResponse {
	
	private String msg;
	private boolean success;
	
	public BaseResponse() {
		super();
	}
	public BaseResponse(String msg, boolean success) {
		super();
		this.msg = msg;
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	

}
