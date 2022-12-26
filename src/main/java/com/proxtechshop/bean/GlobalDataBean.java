package com.proxtechshop.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;

import com.proxtechshop.entities.Brand;
import com.proxtechshop.entities.Category;
import com.proxtechshop.entities.Product;
import com.proxtechshop.entities.ProductAttributeValue;
import com.proxtechshop.entities.Role;
import com.proxtechshop.entities.ProductAttribute;
import com.proxtechshop.functionalinterface.IAttribute;
import com.proxtechshop.functionalinterface.IBrands;
import com.proxtechshop.functionalinterface.ICategories;
import com.proxtechshop.functionalinterface.INameAttr;
import com.proxtechshop.functionalinterface.IRandomProduct;
import com.proxtechshop.functionalinterface.ITop3Brand;
import com.proxtechshop.functionalinterface.ITop3Category;
import com.proxtechshop.functionalinterface.ITop6Product;
import com.proxtechshop.functionalinterface.ITop8SellingProduct;
import com.proxtechshop.functionalinterface.ITopProductTheSame;
import com.proxtechshop.functionalinterface.IUserLoginProfile;
import com.proxtechshop.repositories.BrandRepository;
import com.proxtechshop.repositories.CategoryRepository;
import com.proxtechshop.repositories.CustomProductRepository;
import com.proxtechshop.repositories.ProductRepository;
import com.proxtechshop.repositories.RoleRepository;
import com.proxtechshop.repositories.ProductAttributeRepository;
import com.proxtechshop.repositories.ProductAttributeValueRepository;
import com.proxtechshop.services.UserService;
import com.proxtechshop.viewmodels.ProductAttributeViewModel;

@Configuration
public class GlobalDataBean {

	@Autowired
	private CategoryRepository cr;

	@Autowired
	private BrandRepository br;

	@Autowired
	private ProductRepository pr;

	@Autowired
	private CustomProductRepository cpr;

	@Autowired
	private UserService userService;

	@Autowired
	private ProductAttributeRepository par;

	@Autowired
	private ProductAttributeValueRepository pavr;

	@Autowired
	private RoleRepository roleRepo;

	@Bean(name = "userLoginProfile")
	public IUserLoginProfile userLoginProfile() {
		return () -> userService.loadProfile();
	}

	@Bean(name = "brands")
	public IBrands brands() {
		return () -> {
			List<Brand> brands = new ArrayList<>();
			br.findAll().forEach(brands::add);
			return brands;
		};
	}

	// admin area
	@Bean(name = "roles") // without customer role
	List<Role> roles() {
		List<Role> getRoles = roleRepo.findAll();
		for (int i = 0; i < getRoles.size(); i++) {
			if (getRoles.get(i).roleKey.equalsIgnoreCase("customer")) {
				getRoles.remove(i);
				break;
			}
		}
		return getRoles;
		// end
	}

	@Bean(name = "customerRole")
	Role customerRole() {
		return roleRepo.getReferenceById("customer");
	}

	@Bean(name = "getAttributeName")
	public INameAttr getAttributeName() {
		return (id) -> {
			ProductAttribute pa = par.getReferenceById(id);
			if (pa != null) {
				return pa.getName();
			}
			return "";
		};
	}

	@Bean(name = "top3Brand")
	public ITop3Brand top3brand() {
		return () -> br.findTop3ByOrderByIdDesc();
	}

	@Bean(name = "top3Category")
	public ITop3Category top3Category() {
		return () -> cr.findTop3ByOrderByIdDesc();
	}

	@Bean(name = "categories")
	public ICategories categories() {
		return () -> {
			List<Category> categories = new ArrayList<>();
			cr.findAll().forEach(categories::add);
			return categories;
		};
	}

	@Bean(name = "top6Product")
	public ITop6Product top6Product() {
		return () -> pr.findTop6ByOrderByCreatedDateDesc();
	}
	
	@Bean(name = "topProductTheSame")
	public ITopProductTheSame topProductTheSame() {
		return (productId) -> cpr.getProductTheSame(productId);
	}

	@Bean(name = "randomProduct")
	public IRandomProduct randomProduct(@Autowired EntityManager em) {
		return () -> pr.getPaging(PageRequest.of((new Random()).nextInt((int) pr.count()), 1)).get().findFirst()
				.orElse(new Product());
	}

	@Bean(name = "top8SellingProduct")
	public ITop8SellingProduct top8SellingProduct() {
		return () -> pr.getTopSellingProduct(PageRequest.of(0, 8));
	}
	@Bean(name="allAttribute")
	public List<ProductAttribute> allAttribute(){
		return par.findAll();
	}
	@Bean(name = "attributeAndValues")
	public IAttribute attributeAndValues() {
		return () -> {
			List<ProductAttributeViewModel> attributeValues = new ArrayList<>();
			List<ProductAttributeValue> values = pavr.findAll();
			List<ProductAttribute> attributes = par.findAll();

			attributes.forEach(attribute -> {
				if(attribute.isStatus()) {
					List<String> valueActives = new ArrayList<>();
					values.forEach(value -> {
						if (attribute.getId() == value.getAttributeId()) {
							valueActives.add(value.getValue());
						}
					});
					ProductAttributeViewModel pavm
						= new ProductAttributeViewModel(attribute.getId(), attribute.getName(), valueActives.toArray(new String[valueActives.size()]));
					attributeValues.add(pavm);
				}
			});
			return attributeValues;
		};
	}
}
