package com.proxtechshop.serviceimpl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.proxtechshop.entities.Customer;
import com.proxtechshop.entities.User;
import com.proxtechshop.functionalinterface.IUserLogin;
import com.proxtechshop.repositories.CustomerRepository;
import com.proxtechshop.repositories.UserRepository;
import com.proxtechshop.services.UserService;
import com.proxtechshop.viewmodels.CustomUserModelView;
import com.proxtechshop.viewmodels.UserView;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private IUserLogin userLogin;

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
		User user = userLogin.get();
		Customer customer = customerRepo.findOneByUserId(user.getId());
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
		User user = userLogin.get();
		if (user != null) {			
			CustomUserModelView profile;
			Customer customer = customerRepo.findOneByUserId(user.getId());
			if (customer != null) {
				profile = new CustomUserModelView(user, customer, true);
			} else {
				profile = new CustomUserModelView(user, new Customer(), false);
			}
			return profile;
		} else {
			return null;
		}
	}

	@Override
	public boolean changePassword(String oldPass,String newPass) {
		User user = userLogin.get();
		if (user == null) {
			return false;
		}
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
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
