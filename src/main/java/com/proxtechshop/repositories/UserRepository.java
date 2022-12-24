package com.proxtechshop.repositories;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.proxtechshop.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	User getByUsername(String username);

	List<User> findByUsernameContains(String username);
	
	//Search by username and role with pagination
	Page<User> findByUsernameContains(String username,Pageable pageable);
	
	
	
}
