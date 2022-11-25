package com.proxtechshop.serviceimpl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.proxtechshop.common.Constants;
import com.proxtechshop.entities.Customer;
import com.proxtechshop.entities.User;
import com.proxtechshop.repositories.CustomerRepository;
import com.proxtechshop.repositories.UserRepository;
import com.proxtechshop.services.UserService;
import com.proxtechshop.viewmodels.UserView;
@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private CustomerRepository customerRepo;
	
	@Override
	public boolean Register(UserView userv) {
		User user = userRepo.getByUsername(userv.getUsername());
		ModelAndView page = new ModelAndView();
		User userCheck;
		Customer customerCheck;
		if (user == null) {
			user = new User();
			user.setUsername(userv.getUsername());
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String encodedPassword = passwordEncoder.encode(userv.getPassword());
			user.setCreatedDate(new Date());
			user.setPassword(encodedPassword);
			userCheck=userRepo.save(user);
			if(userCheck==null) {
				return false;
			}
			Customer customer = new Customer();
			customer.setFullName(userv.getFullname());
			customer.setEmail(userv.getUsername());
			customer.setCreatedDate(new Date());
			User FKUser = userRepo.getByUsername(user.getUsername());
			customer.setUserId(FKUser.getId());
			customer.setUser(FKUser);
			customerCheck=customerRepo.save(customer);
			if(customerCheck==null) {
				return false;
			}
			return true;
		} 
		return false;
	}

}
