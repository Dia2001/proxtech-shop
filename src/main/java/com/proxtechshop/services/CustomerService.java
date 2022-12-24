package com.proxtechshop.services;
import org.springframework.data.domain.Page;
import com.proxtechshop.entities.Customer;

public interface CustomerService {

	void registerCustomer();
	boolean remove(String id);
	Page<Customer> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
	Page<Customer> FilterAndPaginated(String search,int pageNo, int pageSize, String sortField, String sortDirection);
}
