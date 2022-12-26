package com.proxtechshop.bean;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.proxtechshop.common.Constants;
import com.proxtechshop.functionalinterface.IUserLogin;
import com.proxtechshop.repositories.UserRepository;
import com.proxtechshop.utils.OptionFilterProduct;

@Configuration
public class ApplicationBean {

	@Autowired
	private UserRepository userRepo;

	@Bean(name = "uploadImageURL")
	public String uploadImageURL() {
		return Constants.UPLOAD_PATH_URL;
	}
	
	@Bean(name = "homePath")
	public String homePath() {
		return Constants.HOME_PATH;
	}

	@Bean(name = "loginPath")
	public String loginPath() {
		return Constants.LOGIN_PATH;
	}

	@Bean(name = "signUpPath")
	public String signUpPath() {
		return Constants.SIGNUP_PATH;
	}

	@Bean(name = "logoutPath")
	public String logoutPath() {
		return Constants.LOGOUT_PATH;
	}

	@Bean(name = "productPath")
	public String productPath() {
		return Constants.PRODUCT_PATH;
	}

	@Bean(name = "profilePath")
	public String profilePath() {
		return Constants.PROFILE_PATH;
	}

	@Bean(name = "phoneShop")
	public String phoneShop() {
		return "+840123456789";
	}

	@Bean(name = "emailShop")
	public String emailShop() {
		return "contact@prox.tech";
	}
	// admin beans area
	@Bean(name="productMngPath")
	public String productMngPath() {
		return Constants.ADMIN_PRODUCTMNG_PATH;
	}
	
	@Bean(name="formProduct")
	public String formProduct() {
		return Constants.ADMIN_FOFMPRODUCT_PATH;
	}
	
	@Bean(name="formMemberPath")
	public String formMemberPath() {
		return Constants.ADMIN_FORMMEMBER_PATH;
	}
	
	@Bean(name="memberPath")
	public String memberPath() {
		return Constants.ADMIN_MEMBERSMNG_PATH;
	}
	
	@Bean(name="customerPath")
	public String customerPath() {
		return Constants.ADMIN_CUSTOMERSMNG_PATH;
	}
	@Bean(name="customerProfile")
	public String customerProfile() {
		return Constants.ADMIN_FORMCUSTOMER_PATH;
	}

	@Bean(name="deleteImageProduct")
	public String deleteImageProduct() {
		return Constants.ADMIN_FOFMPRODUCT_DELETE_IMAGE_PATH;
	}

	@Bean(name="deleteThumbnailProduct")
	public String deleteThumbnailProduct() {
		return Constants.ADMIN_FOFMPRODUCT_DELETE_THUMBNAIL_PATH;
	}

	@Bean(name = "optionFilterProducts")
	public OptionFilterProduct[] optionFilter() {
		return new OptionFilterProduct[] {
				new OptionFilterProduct("Sản phẩm mới", "new"),
				new OptionFilterProduct("Giá cao tới thấp", "high"),
				new OptionFilterProduct("Giá thấp tới cao", "short"),
				new OptionFilterProduct("Tên A-Z", "az"),
				new OptionFilterProduct("Tên Z-A", "za"),
				new OptionFilterProduct("Top bán chạy", "sell")
		};
	}

	@Bean(name="specificationPath")
	public String specificationPath() {
		return Constants.ADMIN_FOFMATTRIBUTE_PATH;
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		return modelMapper;
	}

	@Bean(name = "userLogin")
	public IUserLogin userLogin() {
		return () -> {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();

			if (!(auth instanceof AnonymousAuthenticationToken)) {
				UserDetails userDetails = (UserDetails) auth.getPrincipal();
				return userRepo.getByUsername(userDetails.getUsername());
			} else {
				return null;
			}
		};
	}
}
