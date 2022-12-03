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
import com.proxtechshop.functionalinterface.IBrands;
import com.proxtechshop.functionalinterface.ICategories;
import com.proxtechshop.functionalinterface.IRandomProduct;
import com.proxtechshop.functionalinterface.ITop3Brand;
import com.proxtechshop.functionalinterface.ITop3Category;
import com.proxtechshop.functionalinterface.ITop6Product;
import com.proxtechshop.functionalinterface.ITop8SellingProduct;
import com.proxtechshop.functionalinterface.IUserLoginProfile;
import com.proxtechshop.repositories.BrandRepository;
import com.proxtechshop.repositories.CategoryRepository;
import com.proxtechshop.repositories.ProductRepository;
import com.proxtechshop.services.UserService;

@Configuration
public class GlobalDataBean {

	@Autowired
	private CategoryRepository cr;

	@Autowired
	private BrandRepository br;

	@Autowired
	private ProductRepository pr;
	
	@Autowired
	private UserService userService;
	
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

	@Bean(name = "randomProduct")
	public IRandomProduct randomProduct(@Autowired EntityManager em) {
		return () -> pr.getPaging(PageRequest.of((new Random()).nextInt((int) pr.count()), 1)).get().findFirst()
				.orElse(new Product());
	}

	@Bean(name = "top8SellingProduct")
	public ITop8SellingProduct top8SellingProduct() {
		return () -> pr.getTopSellingProduct(PageRequest.of(0, 8));
	}
}
