package com.proxtechshop.services;

import java.util.Map;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.proxtechshop.api.response.ProductStatisticsMonthResponse;
import com.proxtechshop.dto.AttrValueDto;
import com.proxtechshop.entities.Product;
import com.proxtechshop.entities.ProductAttributeValue;
import com.proxtechshop.viewmodels.ProductDetailViewModel;
import com.proxtechshop.viewmodels.ProductPagingViewModel;
import com.proxtechshop.models.ProductFilter;

public interface ProductService {
	
	ProductDetailViewModel getOneProductDeTail(String productId);
	
	ProductPagingViewModel getFilter(ProductFilter filter, Map<Integer, String[]> attribute);
	
	List<Product> getAllProduct();
	
	void DeleteProduct(String id);
	
	public Page<Product> FilterAndPaginated(String search,int category,int brand, int pageNo, int pageSize, String sortField,
			String sortDirection);
	
	Page<Product> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
	
	Product getProductById(String id);
	
	boolean updateProduct(Product product);
	
	//Belong to Attribute value
	
	HashMap<String, String> showAtrsAndValues(String productId);
	
	void setAttributeValues(Product product);

	List<ProductStatisticsMonthResponse> getProductPStatisticsDateStartDateEnd(Date start, Date end);

	List<ProductAttributeValue> loadAllAttrValue(String idProduct);
	
	public boolean addAttrAndValue(String idProduct,String attr, String value);
	
	public boolean deleteValueOfAttr(String idProduct,int idAttr);
	
	public boolean modifyListOfAttrValue(AttrValueDto attrValueDto,String idproduct);

	boolean deleteImage(int imageId);

	boolean uploadListImage(String productId, MultipartFile[] files);

	Product createProduct(Product product, MultipartFile file);
}
