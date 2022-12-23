package com.proxtechshop.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.proxtechshop.entities.Role;

@Component
public interface RoleRepository extends JpaRepository<Role, String> {
	List<Role> findByRoleNameContains(String rolename);
}
