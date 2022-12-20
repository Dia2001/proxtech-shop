package com.proxtechshop.models;

import java.util.Arrays;

public class ProductFilter {

	// Tu khoa tim kiem
	private String q;
	
	// Id nhan hieu
	private int[] b;
	
	// Id danh muc
	private int c;
	
	// Gia tu
	private long sp;
	
	// Gia den
	private long ep;
	
	// Sap xep theo
	private String s;
	
	// So trang
	private int p;

	public String getSearch() {
		return q;
	}
	
	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}

	public int[] getBrandIds() {
		return b;
	}
	
	public int[] getB() {
		return b;
	}

	public void setB(int[] b) {
		this.b = b;
	}

	public int getCategoryId() {
		return c;
	}
	
	public int getC() {
		return c;
	}

	public void setC(int c) {
		this.c = c;
	}

	public long getStartPrice() {
		return sp;
	}
	
	public long getSp() {
		return sp;
	}

	public void setSp(long sp) {
		this.sp = sp;
	}

	public long getEndPrice() {
		return ep;
	}
	
	public long getEp() {
		return ep;
	}

	public void setEp(long ep) {
		this.ep = ep;
	}

	public String getSort() {
		return s;
	}
	
	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}

	public int getPage() {
		return p;
	}
	
	public int getP() {
		return p;
	}

	public void setP(int p) {
		this.p = p;
	}

	@Override
	public String toString() {
		return "ProductFilter [q=" + q + ", b=" + Arrays.toString(b) + ", c=" + c + ", sp=" + sp + ", ep=" + ep + ", s="
				+ s + ", p=" + p + "]";
	}
}
