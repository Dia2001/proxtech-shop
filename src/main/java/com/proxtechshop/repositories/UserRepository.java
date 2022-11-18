package com.proxtechshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.proxtechshop.entities.User;

@Component
public interface UserRepository extends JpaRepository<User, String> {
	User getByUsername(String username);
}
