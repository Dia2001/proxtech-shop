package com.proxtechshop.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proxtechshop.entities.Brand;
import com.proxtechshop.repositories.BrandRepository;
import com.proxtechshop.services.BrandService;

@Service
public class BrandServiceImpl implements BrandService{

	@Autowired
	BrandRepository brandRepository;
	@Override
	public boolean create(Brand brand) {
		
		try {
			brand.setDescription("");
			brandRepository.save(brand);
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	@Override
	public boolean updateBrand(int id,String data) {
		
		try {
			Brand brand=brandRepository.getReferenceById(id);
			if(data!=""&&data!=null&&brandRepository.findByNameLike(data).size()==0)
			brand.setName(data);
			else
				return false;
			brandRepository.save(brand);
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	@Override
	public List<Brand> loadAll() {
		
		return brandRepository.findAll();
	}

	@Override
	public List<Brand> searchBrand(String search) {
		
		return brandRepository.findByNameContains(search);
	}

}
