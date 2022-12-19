package com.proxtechshop.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proxtechshop.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	User getByUsername(String username);
	//Search by username and role
	List<User> findByUsernameContains(String username);
	//may be defect
	List<User> findByRolesContains(String role);
	
}
