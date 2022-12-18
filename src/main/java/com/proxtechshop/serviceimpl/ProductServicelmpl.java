package com.proxtechshop.serviceimpl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.proxtechshop.common.Constants;
import com.proxtechshop.converter.ProductConverter;
import com.proxtechshop.entities.Product;
import com.proxtechshop.entities.ProductAttribute;
import com.proxtechshop.entities.ProductAttributeValue;
import com.proxtechshop.exception.DataNotFoundException;
import com.proxtechshop.repositories.BrandRepository;
import com.proxtechshop.repositories.ProductAttributeRepository;
import com.proxtechshop.repositories.ProductAttributeValueRepository;
import com.proxtechshop.repositories.ProductRepository;
import com.proxtechshop.services.ProductService;
import com.proxtechshop.viewmodels.ProductDetailViewModel;

@Service
public class ProductServicelmpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductAttributeRepository par;

	@Autowired
	private ProductAttributeValueRepository pavr;

	@Autowired
	private BrandRepository br;

	@Autowired
	private ProductConverter productConverter;

	@Override
	public ProductDetailViewModel getOneProductDeTail(String productId) {

		Product product = productRepository.findById(productId).get();

		if (Objects.isNull(product)) {
			throw new DataNotFoundException(Constants.messageNotFound(productId));
		}

//		createProduct();

		/*
		 * productConverter=new ProductConverter(ProductDetailViewModel.class, product);
		 * ProductDetailViewModel
		 * productDetailVM=(ProductDetailViewModel)productConverter.converTo();
		 */
		ProductDetailViewModel productDetailVM = productConverter.converToModel(product);
		System.out.println(productDetailVM.toString());
		return productDetailVM;

	}

	/**
	 * Test create product and product attribute values
	 */
	public void createProduct() {
		Product product = new Product();
		product.setName("hihi");
		product.setDescription("1");
		product.setDescriptioned("1");
		product.setPrice(new BigDecimal(1));
		product.setSlug("1");
		product.setThumbnail("hafs");
		product.setBrand(br.findById(1).get());
		product.setCreatedDate(new Date());
		productRepository.save(product);

		ProductAttribute attribute = par.findById(1).get();

		ProductAttributeValue attributeValue = new ProductAttributeValue();
		attributeValue.setProduct(product);
		attributeValue.setProductAttribute(attribute);
		attributeValue.setValue("16 GB");
		pavr.save(attributeValue);
	}

	@Override
	public List<Product> getAllProduct() {

		return productRepository.findAll();
	}

	@Override
	public void DeleteProduct(String id) {
		productRepository.deleteById(id);
	}

	@Override
	public Page<Product> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();

		PageRequest pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.productRepository.findAll(pageable);
	}

	@Override
	public Product getProductById(String id) {
		return productRepository.findById(id).get();
	}

	@Override
	public boolean updateProduct(Product product) {
		product.setCreatedDate(new Date());
		product.setUpdatedDate(new Date());
		product.setThumbnail("demo");//what is thumbnail
		productRepository.save(product);
		return true;
	}
	/*
	 * public HashMap<String, String> showAtrsAndValues(String productId){
	 * HashMap<String, String> data=new HashMap<String, String>(); //get list
	 * attributes value of product List<ProductAttributeValue>
	 * attributeValues=pavr.findAllByProductId(productId); //get list attributes of
	 * product for (ProductAttributeValue attrvalue : attributeValues) {
	 * ProductAttribute
	 * attribute=par.getReferenceById(attrvalue.getProductAttribute().getId());
	 * data.put(attribute.getName(), attrvalue.getValue()); } return data; }
	 */

	@Override
	public HashMap<String, String> showAtrsAndValues(String productId) {
		HashMap<String, String> data=new HashMap<String, String>();
		List<ProductAttribute> productAttributes=par.findAll();
		
		if(productId!=null)
		{
			List<ProductAttributeValue> attributeValues=pavr.findAllByProductId(productId);
			for (ProductAttribute attr : productAttributes) {
				String value="";
				for (ProductAttributeValue productAttributeValue : attributeValues) {
					if(attr.getId()==productAttributeValue.getProductAttribute().getId())
					{
						value=productAttributeValue.getValue();
						break;
					}
				}
				data.put(attr.getName(), value);
			}
		}
		else {
			for (ProductAttribute attr : productAttributes) {
				data.put(attr.getName(), "");
			}
		}
		return data;
	}

}
