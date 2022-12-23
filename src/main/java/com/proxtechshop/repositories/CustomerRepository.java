package com.proxtechshop.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import com.proxtechshop.entities.Customer;


@Component
public interface CustomerRepository extends JpaRepository<Customer, String> {
	
//	@Query("SELECT c FROM Customer c WHERE userId = ?1 limit 1")
	Customer findOneByUserId(String userId);
	
	Page<Customer> findByFullNameOrEmailContains(String username,String email,Pageable pageable);
}
