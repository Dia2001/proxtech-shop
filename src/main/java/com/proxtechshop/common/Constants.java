package com.proxtechshop.common;

public class Constants {

	public static final String STATIC_RESOURCE_PATH_CONFIG = "/assets/**";

	public static final String UPLOAD_PATH_CONFIG = "src/main/resources/public/uploads";

	public static final String UPLOAD_RESOURCE_PATH_CONFIG = "/uploads/**";

	public static final String HOME_VIEW = "home/index";

	public static final String HOME_PATH = "/";

	public static final String HOME_PATH_2 = "/home";

	public static final String LOGIN_VIEW = "formlogin/login";

	public static final String LOGIN_PATH = "/login";

	public static final String SIGNUP_VIEW = "formsignup/signup";

	public static final String SIGNUP_PATH = "/signup";

	public static final String LOGOUT_PATH = "/logout";

	public static final String LOGOUT_PATH_SUCCESS = "/home";

	public static final String PRODUCT_VIEW = "product/index";

	public static final String PROFILE_PATH = "/profile";

	public static final String PROFILE_VIEW = "profile/index";

	public static final String PRODUCT_PATH = "/san-pham";

	public static final String ERROR_404_VIEW = "error/404";

	public static final String PRODUCT_DETAIL_VIEW = "product/detail";

	public static final String PRODUCT_DETAIL_PATH = "/san-pham/**";

	public static final String PRODUCT_DETAIL_URL_PATH = "/san-pham/{id}";

	public static final String REGISTER_URL_PATH = "/register";
	
	public static final String CHANGEPASS_URL_PATH = "/change-password";

	public static final String messageNotFound(String Id) {
		return "This data with id " + Id + " not found";
	}
}
