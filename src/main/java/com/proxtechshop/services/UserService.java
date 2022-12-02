package com.proxtechshop.services;
import com.proxtechshop.viewmodels.CustomUserModelView;
import com.proxtechshop.viewmodels.UserView;

public interface UserService {
	boolean Register(UserView userv);
	boolean UpdateProfile(CustomUserModelView userv);
	CustomUserModelView loadProfile();
	boolean changePassword(String oldPass,String newPass);
}
