package com.proxtechshop.api.response;

public class NotificationResponse {
	
	private String msg;

	private boolean check;
	public NotificationResponse() {
		super();
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

	@Override
	public String toString() {
		return "CartResponse [msg=" + msg + ", getMsg()=" + getMsg() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
	
}
