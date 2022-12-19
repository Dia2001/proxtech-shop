package com.proxtechshop.api.request;

import java.util.ArrayList;
import java.util.List;

public class ProductsPaymentRequest {
	List<String> listProductId;

	

	public ProductsPaymentRequest() {
		super();
	}

	public List<String> getListProductId() {
		return listProductId;
	}

	public void setListProductId(List<String> listProductId) {
		this.listProductId = listProductId;
	}

	@Override
	public String toString() {
		return "ProductsPaymentRequest [listProductId=" + listProductId + "]";
	}
	
}
