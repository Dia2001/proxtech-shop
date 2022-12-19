package com.proxtechshop.serviceimpl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proxtechshop.common.Constants;
import com.proxtechshop.converter.ProductConverter;
import com.proxtechshop.entities.Cart;
import com.proxtechshop.entities.Product;
import com.proxtechshop.entities.ProductAttribute;
import com.proxtechshop.entities.ProductAttributeValue;
import com.proxtechshop.entities.User;
import com.proxtechshop.exception.DataNotFoundException;
import com.proxtechshop.models.ProductFilter;
import com.proxtechshop.repositories.BrandRepository;
import com.proxtechshop.repositories.CartRepository;
import com.proxtechshop.repositories.CustomProductRepository;
import com.proxtechshop.repositories.ProductAttributeRepository;
import com.proxtechshop.repositories.ProductAttributeValueRepository;
import com.proxtechshop.repositories.ProductRepository;
import com.proxtechshop.repositories.UserRepository;
import com.proxtechshop.services.ProductService;
import com.proxtechshop.utils.GetUserUtil;
import com.proxtechshop.viewmodels.ProductDetailViewModel;
import com.proxtechshop.viewmodels.ProductPagingViewModel;

@Service
public class ProductServicelmpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CustomProductRepository customProductRepository;
	
	@Autowired
	private ProductAttributeRepository par;
	
	@Autowired
	private ProductAttributeValueRepository pavr;
	
	@Autowired
	private BrandRepository br;
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProductConverter productConverter;

	@Override
	public ProductDetailViewModel getOneProductDeTail(String productId) {

		Product product = productRepository.findById(productId).get();

		if (Objects.isNull(product)) {
			throw new DataNotFoundException(Constants.messageNotFound(productId));
		}
		//getCartByCustomerId();
		//createCart();
		
//		createProduct();
		
		/*
		 * productConverter=new ProductConverter(ProductDetailViewModel.class, product);
		 * ProductDetailViewModel
		 * productDetailVM=(ProductDetailViewModel)productConverter.converTo();
		 */
		ProductDetailViewModel productDetailVM = productConverter.converToModel(product);
		return productDetailVM;

	}

	@Override
	public ProductPagingViewModel getFilter(ProductFilter filter, Map<Integer, String[]> attribute) {
		int pageSize = Constants.PRODUCT_PAGE_SIZE;
		int totalRecord = customProductRepository.getTotalRecordFilter(pageSize, filter);
		int currentPage = filter.getPage() > 1 ? filter.getPage() : 1;
		List<Product> products = customProductRepository.getFilter(pageSize, filter);
		int totalPage = totalRecord % pageSize == 0? (totalRecord / pageSize) : (totalRecord / pageSize) + 1;
		ProductPagingViewModel productPage = new ProductPagingViewModel(products, totalPage, pageSize, currentPage, productConverter);
		return productPage;
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
	
	public void getCartByCustomerId(){
		System.out.println("Data cart by one customer");
		System.out.println(cartRepository.findByCustomer_id("bb606eb2-a00c-4550-9632-d7d4cd5faa33"));
	}
	
	public void createCart() {
		User user = userRepository.getByUsername(GetUserUtil.GetUserName());
	    Cart cart=new Cart(); 
	    cart.setPrice(new BigDecimal(218000));
		cart.setQuantity(3);
		cart.setProduct(productRepository.findById("600c2ebc-648f-4404-bb81-41ee39acc3d8").get());
		cart.setCustomer(user.getCustomer());
		cartRepository.save(cart);
		 
	}

}
