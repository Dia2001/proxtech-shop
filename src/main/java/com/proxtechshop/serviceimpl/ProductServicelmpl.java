package com.proxtechshop.serviceimpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.proxtechshop.api.response.ProductStatisticsMonthResponse;
import com.proxtechshop.common.Constants;
import com.proxtechshop.converter.ProductConverter;
import com.proxtechshop.dto.AttrValueDto;
import com.proxtechshop.entities.Cart;
import com.proxtechshop.entities.Category;
import com.proxtechshop.entities.Image;
import com.proxtechshop.entities.Order;
import com.proxtechshop.entities.OrderDetail;
import com.proxtechshop.entities.Product;
import com.proxtechshop.entities.ProductAttribute;
import com.proxtechshop.entities.ProductAttributeValue;
import com.proxtechshop.entities.User;
import com.proxtechshop.exception.DataNotFoundException;
import com.proxtechshop.functionalinterface.INameAttr;
import com.proxtechshop.models.ProductFilter;
import com.proxtechshop.repositories.BrandRepository;
import com.proxtechshop.repositories.CartRepository;
import com.proxtechshop.repositories.CustomOrderRepository;
import com.proxtechshop.repositories.CustomProductRepository;
import com.proxtechshop.repositories.ImageRepository;
import com.proxtechshop.repositories.OrderDetailRepository;
import com.proxtechshop.repositories.ProductAttributeRepository;
import com.proxtechshop.repositories.ProductAttributeValueRepository;
import com.proxtechshop.repositories.ProductRepository;
import com.proxtechshop.repositories.UserRepository;
import com.proxtechshop.services.ProductService;
import com.proxtechshop.utils.FileUploadUtil;
import com.proxtechshop.utils.GetUserUtil;
import com.proxtechshop.utils.Utils;
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
	@Autowired
	private INameAttr attr;

	@Autowired
	private CustomOrderRepository or;

	@Autowired
	private OrderDetailRepository odr;

	@Autowired
	private ImageRepository ir;

	@Override
	public ProductDetailViewModel getOneProductDeTail(String productId) {

		Product product = productRepository.findById(productId).get();

		if (Objects.isNull(product)) {
			throw new DataNotFoundException(Constants.messageNotFound(productId));
		}
		// getCartByCustomerId();
		// createCart();

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
		int totalRecord = customProductRepository.getTotalRecordFilter(pageSize, filter, attribute);
		int currentPage = filter.getPage() > 1 ? filter.getPage() : 1;
		List<Product> products = customProductRepository.getFilter(pageSize, filter, attribute);
		int totalPage = totalRecord % pageSize == 0 ? (totalRecord / pageSize) : (totalRecord / pageSize) + 1;
		ProductPagingViewModel productPage = new ProductPagingViewModel(products, totalPage, pageSize, currentPage,
				productConverter);
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

	public void getCartByCustomerId() {
		System.out.println("Data cart by one customer");
		System.out.println(cartRepository.findByCustomer_id("bb606eb2-a00c-4550-9632-d7d4cd5faa33"));
	}

	public void createCart() {
		User user = userRepository.getByUsername(GetUserUtil.GetUserName());
		Cart cart = new Cart();
		cart.setPrice(new BigDecimal(218000));
		cart.setQuantity(3);
		cart.setProduct(productRepository.findById("600c2ebc-648f-4404-bb81-41ee39acc3d8").get());
		cart.setCustomer(user.getCustomer());
		cartRepository.save(cart);

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

	// filter by category,brand,name and id
	public Page<Product> FilterAndPaginated(String search, int category, int brand, int pageNo, int pageSize,
			String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();

		PageRequest pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		// if search empty, then load all product and find base on category and brand
		List<Product> products = new ArrayList<>();
		if (search == "") {
			products = productRepository.findAll();
		} else {
			products = productRepository.findAllByNameOrIdContains(search, search);
		}
		List<Product> filter1 = findByBrand(products, brand);
		List<Product> filter2 = findByCtg(filter1, category);
		Page<Product> result = (Page<Product>) Utils.toPage(filter2, pageable);
		return result;
	}

	private List<Product> findByBrand(List<Product> products, int brand) {
		List<Product> tmp = new ArrayList<>();
		if (brand == 0) {
			tmp = products;
		} else {
			for (Product product : products) {
				if (product.getBrand().getId() == brand) {
					tmp.add(product);
				}
			}
		}
		return tmp;
	}

	private List<Product> findByCtg(List<Product> products, int ctg) {
		List<Product> tmp = new ArrayList<>();
		if (ctg == 0) {
			tmp = products;
		} else {
			for (Product product : products) {
				for (Category ctgitem : product.getCategories()) {
					if (ctgitem.getId() == ctg) {
						tmp.add(product);
						break;
					}
				}
			}
		}
		return tmp;
	}

	@Override
	public Product getProductById(String id) {
		return productRepository.findById(id).get();
	}

	@Override
	public Product createProduct(Product product, MultipartFile file) {
		product.setCreatedDate(new Date());
		product.setUpdatedDate(new Date());
		if (file != null) {
			String fileType = Utils.getFileType(file.getContentType());
			if (!"".equals(fileType)) {
				try {
					String fileName = "product-" + new Date().getTime() + fileType;
					FileUploadUtil.saveFile("", fileName, file);
					product.setThumbnail(fileName);
					return productRepository.save(product);
				} catch (Exception e) {
				}
			}
		}
		product.setThumbnail("");
		return productRepository.save(product);
	}

	@Override
	public boolean updateProduct(Product product) {
		try {
			Product productTmp = productRepository.getById(product.getId());
			if (productTmp != null) {
				productTmp.setUpdatedDate(new Date());
				productTmp.setBrand(product.getBrand());
				productTmp.setCategories(product.getCategories());
				productTmp.setName(product.getName());
				productTmp.setImages(product.getImages());
				productTmp.setDescription(product.getDescription());
				productTmp.setDescriptioned(product.getDescriptioned());
				productTmp.setPrice(product.getPrice());
				productTmp.setQuantity(product.getQuantity());
				productTmp.setRate(product.getRate());
				productTmp.setThumbnail(product.getThumbnail());
				productTmp.setSlug(product.getSlug());
				productRepository.save(productTmp);
				return true;
			}
		} catch (Exception e) {
		}
		return false;
	}

	// error
	@Override
	public void setAttributeValues(Product product) {
		List<ProductAttribute> productAttributes = par.findAll();
		// repo
		HashSet<ProductAttributeValue> attrValueStore = new HashSet<>();
		// attrValue of Product
		List<ProductAttributeValue> attrValueOfProduct = pavr.findAllByProductId(product.getId());

		for (ProductAttribute attr : productAttributes) {
			boolean flag = true;
			ProductAttributeValue tmp = new ProductAttributeValue();
			tmp.setProductAttribute(attr);
			for (ProductAttributeValue AttrValue : attrValueOfProduct) {
				if (tmp.getAttributeId() == AttrValue.getAttributeId()) {
					tmp.setValue(AttrValue.getValue());
					flag = false;
					break;
				}
			}
			if (flag)
				tmp.setValue("");
			tmp.setProduct(product);
			attrValueStore.add(tmp);
		}
		product.setProductAttributeValues(attrValueStore);
	}

	@Override
	public HashMap<String, String> showAtrsAndValues(String productId) {
		HashMap<String, String> data = new HashMap<String, String>();
		List<ProductAttribute> productAttributes = par.findAll();
		if (productId != null) {
			List<ProductAttributeValue> attributeValues = pavr.findAllByProductId(productId);
			for (ProductAttribute attr : productAttributes) {
				String value = "";
				for (ProductAttributeValue productAttributeValue : attributeValues) {
					if (attr.getId() == productAttributeValue.getProductAttribute().getId()) {
						value = productAttributeValue.getValue();
						break;
					}
				}
				if(value!="")
				data.put(attr.getName(), value);
			}
		}
		return data;
	}

	@Override
	public List<ProductStatisticsMonthResponse> getProductPStatisticsDateStartDateEnd(Date start, Date end) {
		List<ProductStatisticsMonthResponse> productsInMonth = new ArrayList<>();
		List<Order> orders = or.listOrderInStartAndEndDate(start, end);
		for (Product product : productRepository.findAll()) {
			for (Order order : orders) {
				List<OrderDetail> orderDetails = odr.findByOrderId(order.getId());
				for (OrderDetail orderDetail : orderDetails) {
					if (orderDetail.getProductId().equals(product.getId())) {
						boolean flag = true;
						for (ProductStatisticsMonthResponse productTmp : productsInMonth) {
							if (productTmp.getId().equals(product.getId())) {
								productTmp.setQuantityPrice(productTmp.getQuantityPrice() + orderDetail.getQuantity());
								productTmp.setTotalPrice(productTmp.getPrice() * productTmp.getQuantityPrice());
								flag = false;
								break;
							}
						}
						if (flag) {
							productsInMonth.add(new ProductStatisticsMonthResponse(product, orderDetail.getQuantity(),
									order.getCreatedDate(), order.getUpdatedDate(), order.getOrderStatus().getName()));
						}
						break;
					}
				}
			}
		}
		return productsInMonth;
	}

	// load all attributeValue with empty value if it didnt create before
	@Override
	public List<ProductAttributeValue> loadAllAttrValue(String idProduct) {
		List<ProductAttributeValue> attributeValues = pavr.findAllByProductId(idProduct);
		List<ProductAttribute> attributes = par.findAll();
		for (int i = 0; i < attributes.size(); i++) {
			boolean flag = true;
			for (ProductAttributeValue productAttribute : attributeValues) {
				if (attributes.get(i).getId() == productAttribute.getAttributeId()) {
					flag = false;
					break;
				}
			}
			// if it already not in attributeValues, creating it
			if (flag) {
				ProductAttributeValue tmp = new ProductAttributeValue();
				tmp.setAttributeId(attributes.get(i).getId());
				tmp.setProductAttribute(attributes.get(i));
				tmp.setValue("");
				tmp.setCreatedDate(new Date());
				tmp.setUpdatedDate(new Date());
				tmp.setProductId(idProduct);
				tmp.setProduct(productRepository.getReferenceById(idProduct));
				attributeValues.add(tmp);
			}
		}
		return attributeValues;
	}
	// note: it dont have id in attribute, be careful to add its into database

	@Override
	public boolean addAttrAndValue(String idProduct, String attr, String value) {
		Product product = productRepository.getReferenceById(idProduct);
		// kiểm tra xem attribute value có trong product id đó chưa.
		// Nếu có thì update, nếu không thì tạo mới(tim kiem tuong doi).

		boolean flag = false;
		int attrid = -1;
		for (ProductAttributeValue pr : product.getProductAttributeValues()) {
			if (this.attr.get(pr.getAttributeId()).equalsIgnoreCase(attr)) {
				flag = true;
				attrid = pr.getAttributeId();
				break;
			}
		}

		if (flag) {
			if (attrid > 0) {// no error
				// update
				ProductAttributeValue attributeValue = pavr.getReferenceByAttributeIdAndProductId(attrid, idProduct);
				attributeValue.setValue(value);
				pavr.save(attributeValue);
			}
		} else {// errors may be in there
			List<ProductAttribute> attributes = par.findAll();
			int attridtmp = -1;
			for (ProductAttribute item : attributes) {
				if (item.getName().equalsIgnoreCase(attr)) {
					attridtmp = item.getId();
					break;
				}
			}
			// if it existed in attribute, create value rely on that attribute
			if (attridtmp > 0) {
				ProductAttribute attribute = par.getReferenceById(attridtmp);
				ProductAttributeValue attributeValue = new ProductAttributeValue();
				attributeValue.setProductAttribute(attribute);
				attributeValue.setProduct(product);
				attributeValue.setValue(value);
				attributeValue.setCreatedDate(new Date());
				attributeValue.setUpdatedDate(new Date());
				if (pavr.save(attributeValue) != null) {
					pavr.flush();
					return true;
				} else
					return false;

			} else {
				// else create new both attribute and attribute value
				ProductAttribute attribute = new ProductAttribute();
				attribute.setCreatedDate(new Date());
				attribute.setDescription("");
				attribute.setName(attr);
				attribute.setUpdatedDate(new Date());

				if (par.save(attribute) != null) {
					par.flush();
					ProductAttributeValue attributeValue = new ProductAttributeValue();
					attributeValue.setProductAttribute(par.getReferenceByName(attr));
					attributeValue.setProduct(product);
					attributeValue.setValue(value);
					attributeValue.setCreatedDate(new Date());
					attributeValue.setUpdatedDate(new Date());
					if (pavr.save(attributeValue) != null) {
						pavr.flush();
						return true;
					} else
						return false;
				} else
					return false;
			}
		}

		return true;
	}

	@Override
	public boolean deleteValueOfAttr(String idProduct, int idAttr) {
		try {
			ProductAttributeValue value = pavr.getReferenceByAttributeIdAndProductId(idAttr, idProduct);
			String v = value.getValue();
			if (v != "" || v != null)
				pavr.delete(value);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean modifyListOfAttrValue(AttrValueDto attrValueDto, String idproduct) {// error
		List<ProductAttributeValue> list = attrValueDto.getAttributeValues();
		// remove all item which has none value
		for (int i = 0; i < list.size(); i++) {
			// fillAttrValue(list.get(i),idproduct,list.get(i).getAttributeId());
			String tmp = list.get(i).getValue();
			if (tmp != "" && tmp != null)
				try {
					addAttrAndValue(idproduct, attr.get(list.get(i).getAttributeId()), list.get(i).getValue());

				} catch (Exception e) {
					return false;
				}

		}
		return true;
	}

	@Override
	public boolean uploadListImage(String productId, MultipartFile[] files) {
		try {
			Product product = productRepository.getById(productId);
			if (product == null) {
				throw new Exception();
			}
			for (MultipartFile file : files) {
				if (file == null) {
					continue;
				}
				String fileType = Utils.getFileType(file.getContentType());
				if ("".equals(fileType)) {
					throw new Exception();
				}
				String fileName = productId + "-" + new Date().getTime() + fileType;
				FileUploadUtil.saveFile("", fileName, file);
				Image image = new Image();
				image.setProduct(product);
				image.setThumbnail(fileName);
				ir.save(image);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteImage(int imageId) {
		try {
			Image img = ir.getById(imageId);
			FileUploadUtil.removeFile(img.getThumbnail());
			ir.delete(img);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
