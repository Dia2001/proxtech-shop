package com.proxtechshop.api.response;

public class NotificationResponse {
	
	String msg;

	public NotificationResponse() {
		super();
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "CartResponse [msg=" + msg + ", getMsg()=" + getMsg() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
	
}
