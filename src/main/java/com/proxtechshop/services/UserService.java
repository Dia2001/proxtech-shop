package com.proxtechshop.services;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.proxtechshop.viewmodels.CustomUserModelView;
import com.proxtechshop.viewmodels.UserView;

public interface UserService {
	boolean Register(UserView userv);
	boolean UpdateProfile(CustomUserModelView userv,MultipartFile file) throws IOException;
	CustomUserModelView loadProfile();
	CustomUserModelView loadProfile(String idUser);
	boolean changePassword(String oldPass,String newPass);
	boolean changePassword(String idUser,String oldPass,String newPass);
	//admin area
}
