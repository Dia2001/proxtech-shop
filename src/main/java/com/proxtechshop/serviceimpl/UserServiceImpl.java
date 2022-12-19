package com.proxtechshop.serviceimpl;

import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.proxtechshop.converter.UserConverter;
import com.proxtechshop.entities.Customer;
import com.proxtechshop.entities.User;
import com.proxtechshop.functionalinterface.IUserLogin;
import com.proxtechshop.repositories.CustomerRepository;
import com.proxtechshop.repositories.UserRepository;
import com.proxtechshop.services.InforCustomerService;
import com.proxtechshop.services.UserService;
import com.proxtechshop.utils.FileUploadUtil;
import com.proxtechshop.utils.GetUserUtil;
import com.proxtechshop.viewmodels.CustomUserModelView;
import com.proxtechshop.viewmodels.PaymentCustomerDetailViewModel;
import com.proxtechshop.viewmodels.UserView;

@Service
public class UserServiceImpl implements UserService, InforCustomerService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private CustomerRepository customerRepo;

	@Autowired
	private IUserLogin userLogin;

	@Autowired
	private UserConverter userConverter;

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
	public boolean UpdateProfile(CustomUserModelView userv, MultipartFile file) throws IOException {
		System.out.println(userv.getUsername());
		User user = userRepo.getByUsername(userv.getUsername());
		Customer customer = customerRepo.findOneByUserId(user.getId());
		customer.setFullName(userv.getUsername());
		customer.setAddress(userv.getAddress());
		customer.setPhone(userv.getPhone());
		// handle avatar
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		customer.setImage(fileName);
		String uploadDir = "/user";
		FileUploadUtil.saveFile(uploadDir, fileName, file);
		Customer checkCustomer = customerRepo.save(customer);
		if (checkCustomer != null)
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
	public boolean changePassword(String oldPass, String newPass) {
		User user = userLogin.get();
		if (user == null) {
			return false;
		}
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String currentPass = user.getPassword();
		if (passwordEncoder.matches(oldPass, currentPass)) {
			String encodedNewPassword = passwordEncoder.encode(newPass);
			user.setPassword(encodedNewPassword);
			userRepo.save(user);
			return true;
		}
		return false;
	}

	@Override
	public PaymentCustomerDetailViewModel inforCustomerPayment() {
		User user = userRepo.getByUsername(GetUserUtil.GetUserName());
		PaymentCustomerDetailViewModel infoCustomerPayment = userConverter
				.covertToCustomerViewModel(user.getCustomer());
		return infoCustomerPayment;
	}
}
