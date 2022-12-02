package com.proxtechshop.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.proxtechshop.entities.Customer;

@Component
public interface CustomerRepository extends JpaRepository<Customer, String> {
	List<Customer> findByUserId( String userId);
}
