package com.proxtechshop.serviceimpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.proxtechshop.entities.Customer;
import com.proxtechshop.entities.User;
import com.proxtechshop.repositories.CustomerRepository;
import com.proxtechshop.repositories.UserRepository;
import com.proxtechshop.services.CustomerService;


@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	CustomerRepository repo;
	@Autowired
	UserRepository urepo;
	
	@Override
	public void registerCustomer() {
	
	}
	
	@Override
	public boolean remove(String id) {
		Customer customer=repo.getReferenceById(id);
		User user=urepo.getReferenceById(customer.getUserId());
		try {
			repo.delete(customer);
			urepo.delete(user);
		}catch(Exception e) {
			return false;
		}
		return true;
	}
	
	@Override
	public Page<Customer> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();

		PageRequest pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.repo.findAll(pageable);
	}
	@Override
	public Page<Customer> FilterAndPaginated(String search, int pageNo, int pageSize, String sortField,
			String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();

		PageRequest pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		Page<Customer> findByName=repo.findByFullNameOrEmailContains(search,search, pageable);
		return findByName;
	}
	

}
