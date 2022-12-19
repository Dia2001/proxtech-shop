package com.proxtechshop.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proxtechshop.entities.Brand;
import com.proxtechshop.entities.Category;
import com.proxtechshop.repositories.BrandRepository;
import com.proxtechshop.repositories.CategoryRepository;
import com.proxtechshop.services.TypesProductService;

@Service
public class TypesProductServiceImp implements TypesProductService {

	@Autowired
	private BrandRepository brandRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Override
	public List<Brand> getAllBrand() {
		return brandRepository.findAll();
	}

	@Override
	public List<Category> getAllCategory() {
		// TODO Auto-generated method stub
		return categoryRepository.findAll();
	}

}
