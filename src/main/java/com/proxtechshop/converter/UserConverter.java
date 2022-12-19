package com.proxtechshop.converter;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.proxtechshop.entities.Customer;
import com.proxtechshop.entities.User;
import com.proxtechshop.viewmodels.CustomUserModelView;
import com.proxtechshop.viewmodels.PaymentCustomerDetailViewModel;

@Component
public class UserConverter {
	@Autowired
	private  ModelMapper modelMapper;
	
	public CustomUserModelView convertToUserViewModel(User user,Customer customer) {
		try {
			CustomUserModelView infoUser =modelMapper.map(customer, CustomUserModelView.class);
			infoUser=modelMapper.map(user, CustomUserModelView.class);
			return infoUser;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public PaymentCustomerDetailViewModel covertToCustomerViewModel(Customer customer) {
		
		try {
			PaymentCustomerDetailViewModel infoCustomer =modelMapper.map(customer, PaymentCustomerDetailViewModel.class);
			System.out.println(infoCustomer.toString());
			return infoCustomer;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return null;
	}
}
