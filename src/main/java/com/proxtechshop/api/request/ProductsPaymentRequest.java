package com.proxtechshop.api.request;

import java.util.ArrayList;
import java.util.List;

public class ProductsPaymentRequest {
	private List<String> listProductId;

	private String status;

	public ProductsPaymentRequest() {
		super();
	}

	public List<String> getListProductId() {
		return listProductId;
	}

	public void setListProductId(List<String> listProductId) {
		this.listProductId = listProductId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ProductsPaymentRequest [listProductId=" + listProductId + ", status=" + status + "]";
	}

	
	
}
