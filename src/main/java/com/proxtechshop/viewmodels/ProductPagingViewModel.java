package com.proxtechshop.viewmodels;

import java.util.ArrayList;
import java.util.List;

import com.proxtechshop.converter.ProductConverter;
import com.proxtechshop.entities.Product;

public class ProductPagingViewModel {

	private List<ProductDetailViewModel> products;
	
	private int totalPage;
	
	private int currentPage;
	
	private int pageSize;
	
	private int totalRecord;
	
	public ProductPagingViewModel() {}
	
	public ProductPagingViewModel(List<Product> products, int totalPage, int pageSize, int currentPage, ProductConverter productConverter) {
		this.totalPage = totalPage;
		this.totalRecord = products.size();
		this.pageSize = pageSize;
		this.products = new ArrayList<>();
		this.currentPage = currentPage;
		products.forEach((p) -> {
			this.products.add(productConverter.converToModel(p));
		});
	}

	public List<ProductDetailViewModel> getProducts() {
		return products;
	}

	public void setProducts(List<ProductDetailViewModel> products) {
		this.products = products;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	@Override
	public String toString() {
		return "ProductPagingViewModel [products=" + ", totalPage=" + totalPage + ", currentPage="
				+ currentPage + ", pageSize=" + pageSize + ", totalRecord=" + totalRecord + "]";
	}
}
