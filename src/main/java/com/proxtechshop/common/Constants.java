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
	
	public static final String ADMIN_PATH="/admin";
	
	
	public static final String ADMIN_PRODUCTMNG_VIEW="admin/productmanagement/index";
		public static final String ADMIN_FOFMPRODUCT_VIEW="admin/productmanagement/formproduct";
	public static final String ADMIN_CUSTOMERSMNG_VIEW="admin/customermanagement/index";
	public static final String ADMIN_MEMBERSMNG_VIEW="admin/membermanagement/index";
	public static final String ADMIN_CATEGORIESMNG_VIEW="admin/categorymanagement/index";
	public static final String ADMIN_ORDERMNG_VIEW="admin/ordermanagement/index";
	public static final String ADMIN_PROFILE_VIEW="admin/adminprofile/index";
	public static final String ADMIN_BRAND_VIEW="admin/brandmanagement/index";
	
	public static final String ADMIN_PRODUCTMNG_PATH="/admin/productmanagement";
		public static final String ADMIN_FOFMPRODUCT_PATH="/admin/formproduct";
	public static final String ADMIN_CUSTOMERSMNG_PATH="/admin/customermanagement";
	public static final String ADMIN_MEMBERSMNG_PATH="/admin/membermanagement";
	public static final String ADMIN_CATEGORIESMNG_PATH="/admin/categorymanagement";
	public static final String ADMIN_ORDERMNG_PATH="/admin/ordermanagement";
	public static final String ADMIN_PROFILE_PATH="/admin/adminprofile";
	public static final String ADMIN_BRAND_PATH="/admin/brandmanagement";
	

	public static final String messageNotFound(String Id) {
		return "This data with id " + Id + " not found";
	}
}
