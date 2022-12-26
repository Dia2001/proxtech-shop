package com.proxtechshop.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proxtechshop.api.request.CartRequest;
import com.proxtechshop.converter.CartConverter;
import com.proxtechshop.entities.Cart;
import com.proxtechshop.entities.Product;
import com.proxtechshop.entities.User;
import com.proxtechshop.repositories.CartRepository;
import com.proxtechshop.repositories.ProductRepository;
import com.proxtechshop.repositories.UserRepository;
import com.proxtechshop.services.CartService;
import com.proxtechshop.utils.GetUserUtil;
import com.proxtechshop.viewmodels.CartViewModel;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CartConverter cartConverter;

	@Override
	public boolean addProductToCart(CartRequest cartRequest) {
        System.out.println(cartRequest.toString());
		Cart check = null;
		User user = userRepository.getByUsername(GetUserUtil.GetUserName());
		Product product = productRepository.findById(cartRequest.getProductId()).get();
		Cart cartCheck = cartRepository.findByCustomer_idAndProduct_id(user.getCustomer().getId(),
				cartRequest.getProductId());

		if (Objects.isNull(cartCheck)) {
			// If the product added to the cart exceeds the number of products available,
			// error
			if (product.getQuantity() >= cartRequest.getQuantity()) {
				Cart cart = new Cart();
				cart.setPrice(cartRequest.getPrice());
				cart.setQuantity(cartRequest.quantity);
				cart.setProduct(productRepository.findById(cartRequest.getProductId()).get());
				cart.setCustomer(user.getCustomer());
				check = cartRepository.save(cart);
			}
		} else {
			Cart inforCart = cartRepository.findByCustomer_idAndProduct_id(user.getCustomer().getId(),
					cartRequest.getProductId());
			// If the product added to the cart exceeds the number of products available,
			// error
			if (product.getQuantity() >= inforCart.getQuantity() + cartRequest.getQuantity()) {
				Cart cart = inforCart;
				cart.setPrice(cartRequest.getPrice());
				cart.setQuantity(inforCart.getQuantity() + cartRequest.getQuantity());
				cart.setProduct(inforCart.getProduct());
				cart.setCustomer(inforCart.getCustomer());
				check = cartRepository.save(cart);
			}
		}

		return check != null ? true : false;
	}

	@Override
	public List<CartViewModel> getAllProductToCustomer() {

		User user = userRepository.getByUsername(GetUserUtil.GetUserName());
		System.out.println(user.toString());
		System.out.println(user.getCustomer().getId());
		List<Cart> lpToCart = cartRepository.findByCustomer_id(user.getCustomer().getId());
		System.out.println(lpToCart.toString());
		List<CartViewModel> carts = new ArrayList<>();
		for (Cart cart : lpToCart) {
			System.out.println("---------------" + cart.toString());
			carts.add(cartConverter.converToModel(cart));
		}
		return carts;
	}

	@Override
	public boolean irProduct(String productId, int number) {
		System.out.println(productId+"aaaaaaaaa"+number);
		Cart check = null;
		User user = userRepository.getByUsername(GetUserUtil.GetUserName());
		Product product = productRepository.findById(productId).get();
		if(number==-1) {
			Cart inforCart = cartRepository.findByCustomer_idAndProduct_id(user.getCustomer().getId(),
					productId);
			// If the product added to the cart exceeds the number of products available,
			// error
			if (product.getQuantity() >= inforCart.getQuantity() +(-1)) {
				Cart cart = inforCart;
				cart.setQuantity(inforCart.getQuantity() + (-1));
				check = cartRepository.save(cart);
			}
		}else {
			Cart inforCart = cartRepository.findByCustomer_idAndProduct_id(user.getCustomer().getId(),
					productId);
			// If the product added to the cart exceeds the number of products available,
			// error
			if (product.getQuantity() >= inforCart.getQuantity() +1) {
				Cart cart = inforCart;
				cart.setQuantity(inforCart.getQuantity() +1);
				check = cartRepository.save(cart);
			}
		}
		return check != null ? true : false;
	}

	@Override
	public boolean removeProduct(String productId) {
		
		boolean check = false;
		
		User user = userRepository.getByUsername(GetUserUtil.GetUserName());
		Cart cart=cartRepository.findByCustomer_idAndProduct_id(user.getCustomer().getId(),productId);
		System.out.println(cart.toString());
		try {
			cartRepository.delete(cart);
			check=true;
		}catch (Exception e) {
		}
		
		return check;
	}

	

}
