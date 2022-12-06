package com.proxtechshop.serviceimpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.proxtechshop.converter.UserConverter;
import com.proxtechshop.entities.Customer;
import com.proxtechshop.entities.User;
import com.proxtechshop.repositories.CustomerRepository;
import com.proxtechshop.repositories.UserRepository;
import com.proxtechshop.services.UserService;
import com.proxtechshop.utils.GetUserUtil;
import com.proxtechshop.viewmodels.CustomUserModelView;
import com.proxtechshop.viewmodels.UserView;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private CustomerRepository customerRepo;
	@Autowired
	private UserConverter converter;

	@Override
	public boolean Register(UserView userv) {
		User user = userRepo.getByUsername(userv.getUsername());
		User userCheck;
		Customer customerCheck;
		if (user == null) {
			user = new User();
			user.setUsername(userv.getUsername());
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String encodedPassword = passwordEncoder.encode(userv.getPassword());
			user.setCreatedDate(new Date());
			user.setPassword(encodedPassword);
			userCheck = userRepo.save(user);
			if (userCheck == null) {
				return false;
			}
			Customer customer = new Customer();
			customer.setFullName(userv.getFullname());
			customer.setEmail(userv.getUsername());
			customer.setCreatedDate(new Date());
			User FKUser = userRepo.getByUsername(user.getUsername());
			customer.setUserId(FKUser.getId());
			customer.setUser(FKUser);
			customerCheck = customerRepo.save(customer);
			if (customerCheck == null) {
				return false;
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean UpdateProfile(CustomUserModelView userv) {
		System.out.println(userv.getUsername());
		User user = userRepo.getByUsername(userv.getUsername());
		List<Customer> customers = customerRepo.findByUserId(user.getId());
		Customer customer = customers.get(0);
		customer.setFullName(userv.getFullName());
		customer.setAddress(userv.getAddress());
		customer.setPhone(userv.getPhone());
		Customer checkCustomer = customerRepo.save(customer);
		if (checkCustomer!=null)
			return true;
		else
			return false;
	}

	@Override
	public CustomUserModelView loadProfile() {
		// TODO Auto-generated method stub
		CustomUserModelView profile = new CustomUserModelView();
		User user = userRepo.getByUsername(GetUserUtil.GetUserName());
		List<Customer> customers = customerRepo.findByUserId(user.getId());
		Customer customer = customers.get(0);
		profile = converter.convertToUserViewModel(user, customer);
		return profile;
	}

	@Override
	public boolean changePassword(String oldPass,String newPass) {
		// TODO Auto-generated method stub
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		//get user password
		User user = userRepo.getByUsername(GetUserUtil.GetUserName());
		String currentPass=user.getPassword();
		if(passwordEncoder.matches(oldPass, currentPass))
		{
			String encodedNewPassword = passwordEncoder.encode(newPass);
			user.setPassword(encodedNewPassword);
			userRepo.save(user);
			return true;
		}
		return false;
	}

}
