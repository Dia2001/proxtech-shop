package com.proxtechshop.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.proxtechshop.entities.Customer;
import com.proxtechshop.entities.User;
import com.proxtechshop.viewmodels.CustomUserModelView;
@Component
public class UserConverter {
	@Autowired
	private  ModelMapper modelMapper;
	public CustomUserModelView convertToUserViewModel(User user,Customer customer) {
		CustomUserModelView infoUser=modelMapper.map(customer, CustomUserModelView.class);
		infoUser=modelMapper.map(user, CustomUserModelView.class);
		return infoUser;
	}
}
