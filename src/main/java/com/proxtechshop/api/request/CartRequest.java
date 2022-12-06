package com.proxtechshop.api.request;

import java.math.BigDecimal;

public class CartRequest {
		
		public String productId;

		public BigDecimal price;

		public int quantity;

		public CartRequest() {
			super();
		}

		public String getProductId() {
			return productId;
		}

		public void setProductId(String productId) {
			this.productId = productId;
		}

		public BigDecimal getPrice() {
			return price;
		}

		public void setPrice(BigDecimal price) {
			this.price = price;
		}

		public int getQuantity() {
			return quantity;
		}

		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}

		@Override
		public String toString() {
			return "CartModel [productId=" + productId + ", price=" + price + ", quantity=" + quantity + "]";
		}
		
}
