function cartHandle() {
	let cartCheckItems = document.querySelectorAll('.cart-check-item')
	let totalItemCart = cartCheckItems.length
	let totalItemCartChecked = 0
	let checkAll = document.getElementById('checkAll')
	let btnCheckout = document.getElementById('btnCheckout')
	let totalPrice = 0;
	let ifPayment = document.getElementById('sumPayment')


	checkPriceTotal();

	checkAll.onchange = function() {
		console.log("aaaaaaaaaaaaaaaa");
		totalPrice = 0;
		cartCheckItems.forEach(function(cartCheckItem) {
			cartCheckItem.checked = checkAll.checked
			if (checkAll.checked === true) {
				totalItemCartChecked = totalItemCart
				//
				var productId = cartCheckItem.id.split('check-')[1];
				let numberProduct = document.getElementById('numberProduct-' + productId).value;
				console.log(numberProduct);
				let priceProduct = document.getElementById('hidenPrice-' + productId).textContent;
				let price = Number(numberProduct) * Number(priceProduct);
				totalPrice += price;
			} else {
				totalItemCartChecked = 0
				//
				console.log("bbbbbbbbbbbbbbbbbbb");
				totalPrice = 0;
			}
		})
		ifPayment.innerHTML = totalPrice.toString().replace(/\B(?=(\d{3})+(?!\d))/g, '.') + " VNĐ";
		document.getElementById('totalProuct').innerHTML = "Sản phẩm (" + totalItemCartChecked + ")";
	}


	cartCheckItems.forEach(function(cartCheckItem) {
		cartCheckItem.onchange = function() {
			if (cartCheckItem.checked) {
				totalItemCartChecked++
				//
				var productId = cartCheckItem.id.split('check-')[1];
				let numberProduct = document.getElementById('numberProduct-' + productId).value;
				console.log(numberProduct);
				let priceProduct = document.getElementById('hidenPrice-' + productId).textContent;
				let price = numberProduct * Number(priceProduct);
				totalPrice = totalPrice + price;
				ifPayment.innerHTML = totalPrice.toString().replace(/\B(?=(\d{3})+(?!\d))/g, '.') + " VNĐ";
				document.getElementById('totalProuct').innerHTML = "Sản phẩm (" + totalItemCartChecked + ")";
			} else {
				//
				var productId = cartCheckItem.id.split('check-')[1];
				let numberProduct = document.getElementById('numberProduct-' + productId).value;
				console.log(numberProduct);
				let priceProduct = document.getElementById('hidenPrice-' + productId).textContent;
				let price = numberProduct * Number(priceProduct);
				totalPrice = totalPrice - price;
				ifPayment.innerHTML = totalPrice.toString().replace(/\B(?=(\d{3})+(?!\d))/g, '.') + " VNĐ";
				totalItemCartChecked--
				document.getElementById('totalProuct').innerHTML = "Sản phẩm (" + totalItemCartChecked + ")";
			}
			if (totalItemCartChecked == totalItemCart) {
				checkAll.checked = true
			} else {
				checkAll.checked = false
			}
		}
	})

	btnCheckout.onclick = function() {
		let idProducts = getIdProductCartChecked()
		localStorage.removeItem("listIdProduct");
		localStorage.setItem("listIdProduct", idProducts);
		window.location.href = "/thanh-toan";
		console.log(idProducts)
	}

	function getIdProductCartChecked() {
		return idProducts = Array.from(document.querySelectorAll('.cart-check-item:checked')).map(function(item) {
			return item.id.split('check-')[1]
		})
	}
}

$(document).ready(cartHandle)

function quantity(id, num) {
	let numberProduct = document.getElementById('numberProduct-' + id).value;
	if (Number(numberProduct) === 1) {
		if (Number(num) === -1) {
			return;
		}
	}
	var ircartRequest = {
		idProduct: id,
		number: num
	}
	$.ajax({

		type: "POST",
		contentType: "application/json",
		url: "/api/v1/cart/irProduct",
		data: JSON.stringify(ircartRequest),
		dataType: 'json',
		success: function(result) {
			if (result.check === true) {
				if (num === -1) {
					var numberProduct = Number(document.getElementById('numberProduct-' + id).value);
					numberProduct = numberProduct - 1;
					document.getElementById('numberProduct-' + id).value = numberProduct;
					console.log(result.msg);
				} else {
					var numberProduct = Number(document.getElementById('numberProduct-' + id).value);
					console.log("a" + numberProduct);
					numberProduct = numberProduct + 1;
					document.getElementById('numberProduct-' + id).value = numberProduct;
					console.log(result.msg);
				}
				checkPriceTotal();
			}
		},
		error: function() {
			alert('Failed to receive the Data');
			return;
		}
	});
}
function checkPriceTotal() {
	let totalPrice = 0;
	let ifPayment = document.getElementById('sumPayment')
	let cartCheckItems = document.querySelectorAll('.cart-check-item')
	console.log(cartCheckItems);
	cartCheckItems.forEach(function(cartCheckItem) {
		if (cartCheckItem.checked) {
			//
			var productId = cartCheckItem.id.split('check-')[1];
			console.log(productId);
			let numberProduct = document.getElementById('numberProduct-' + productId).value;
			console.log(numberProduct + "bbbbb");
			let priceProduct = document.getElementById('hidenPrice-' + productId).textContent;
			let price = numberProduct * Number(priceProduct);
			totalPrice = totalPrice + price;
		}

		console.log(totalPrice + "aaaaa");
		ifPayment.innerHTML = totalPrice.toString().replace(/\B(?=(\d{3})+(?!\d))/g, '.') + " VNĐ";
	})

}

function removeProduct(id) {
	$.ajax({
		type: "POST",
		url: "/api/v1/cart/removeProduct",
		data: {
			idProduct: id
		},
		success: function(result) {
			console.log(result.msg);
			var parent = document.getElementById("father-card");
			var chillrent = document.getElementById('cart-' + id);
			parent.removeChild(chillrent);
			checkPriceTotal();
		},
		error: function() {
			alert('Failed to receive the Data');
			return;
		}
	});
}

if (performance.navigation.type == 2) {
	location.reload(true);
}