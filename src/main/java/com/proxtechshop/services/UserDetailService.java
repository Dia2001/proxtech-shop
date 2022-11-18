package com.proxtechshop.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.proxtechshop.repositories.UserRepository;

@Service
@Component
public class UserDetailService implements UserDetailsService {

	@Autowired
	private UserRepository ur;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final com.proxtechshop.entities.User user = ur.getByUsername(username);
		List<GrantedAuthority> authorities = new ArrayList<>();

//		Set<Role> roles = user.getRoles();
//		for (Role role : roles) {
//			authorities.add(new SimpleGrantedAuthority(role.getRoleName().trim()));
//		}
		
		return new User(username, user.getPassword(), authorities);
	}
}
