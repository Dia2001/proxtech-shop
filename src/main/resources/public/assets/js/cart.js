function cartHandle() {
	let cartCheckItems = document.querySelectorAll('.cart-check-item')
	let totalItemCart = cartCheckItems.length
	let totalItemCartChecked= 0
	let checkAll = document.getElementById('checkAll')
	let btnCheckout = document.getElementById('btnCheckout')
	
	checkAll.onchange = function() {
		cartCheckItems.forEach(function(cartCheckItem) {
			cartCheckItem.checked = checkAll.checked
			if (checkAll) {
				totalItemCartChecked = totalItemCart
			} else {
				totalItemCartChecked = 0
			}
		})
	}
	
	cartCheckItems.forEach(function(cartCheckItem) {
		cartCheckItem.onchange = function() {
			if (cartCheckItem.checked) {
				totalItemCartChecked ++
			} else {
				totalItemCartChecked --
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
		console.log(idProducts)
	}
	
	function getIdProductCartChecked() {
		return idProducts = Array.from(document.querySelectorAll('.cart-check-item:checked')).map(function(item) {
			return item.id.split('check-')[1]
		})
	}
}	

$(document).ready(cartHandle)