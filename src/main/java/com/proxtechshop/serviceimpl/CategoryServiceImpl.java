package com.proxtechshop.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proxtechshop.entities.Brand;
import com.proxtechshop.entities.Category;
import com.proxtechshop.repositories.CategoryRepository;
import com.proxtechshop.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepository ctgRepo;

	@Override
	public boolean create(Category ctg) {

		try {
			ctg.setDescription("");
			ctg.setThumbnail("");
			ctgRepo.save(ctg);
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	@Override
	public boolean updateCtg(int id,String data) {
		try {
			Category ctg=ctgRepo.getReferenceById(id);
			if(data!=""&&data!=null&&ctgRepo.findByNameContains(data).size()==0)
			ctg.setName(data);
			else
				return false;
			ctgRepo.save(ctg);
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	@Override
	public List<Category> loadAll() {
		// TODO Auto-generated method stub
		return ctgRepo.findAll();
	}

	@Override
	public List<Category> searchCtg(String search) {

		return ctgRepo.findByNameContains(search);
	}

}
