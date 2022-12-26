package com.proxtechshop.dto;

import java.util.List;

import com.proxtechshop.entities.ProductAttributeValue;

public class AttrValueDto {
	private List<ProductAttributeValue> attributeValues;
	public List<ProductAttributeValue> getAttributeValues() {
		return attributeValues;
	}
	
	public void setAttributeValues(List<ProductAttributeValue> attributeValues) {
		this.attributeValues = attributeValues;
	}
	
	void AddAttrValue(ProductAttributeValue attrValue) {
		this.attributeValues.add(attrValue);
	}

	@Override
	public String toString() {
		return "AttrValueDto [attributeValues=" + attributeValues + "]";
	}
	
}
