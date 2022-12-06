package com.proxtechshop.api.response;

public class CartResponse {
	
	String msg;

	public CartResponse() {
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
