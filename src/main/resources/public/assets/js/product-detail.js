(() => {

	document.querySelectorAll('.product-img-item').forEach(e => {
		e.addEventListener('click', () => {
			let img = e.querySelector('img').getAttribute('src')
			document.querySelector('#product-img > img').setAttribute('src', img)
		})
	})

	document.querySelector('#view-all-description').addEventListener('click', () => {
		let content = document.querySelector('.product-detail-description-content')
		content.classList.toggle('active')
		document.querySelector('#view-all-description').innerHTML = content.classList.contains('active') ? 'view less' : 'view all'
	})

	var $btnIncrementNumnber = $('#btnIncNumber');
	var $btnReduceNumnber = $('#btnReduceNumber');
	var $quantity = $('.product-quantity');

	$btnIncrementNumnber.click(function() {
		var quantity = parseInt($quantity.text());
		var number = 1;
		handleAmount(quantity, number);
	});

	$btnReduceNumnber.click(function() {
		var quantity = parseInt($quantity.text());
		var number = -1;
		handleAmount(quantity, number);
	});

	function handleAmount(quantity, number) {

		if (number != 1 && number != -1) {
			return;
		}

		if (quantity < 2) {
			if (number === 1) {
				$quantity.text(quantity + number);
			} else {
				return;
			}
		} else {
			$quantity.text(quantity + number);
		}
	}
	$(document).on('click', '#btnAddCart', function() {
		addProuctToCart();
		console.log("aaaaaaaa");
	});
	function addProuctToCart() {

		debugger;
		var infoDataCart = {
			productId: $("#productId").text(),
			price: $("#price").val(),
			quantity: $("#quantity").text()
		}
		console.log(infoDataCart);
		$.ajax({
			type: "POST",
			contentType: "application/json",
			url: "/api/v1/customer/addProductToCart",
			data: JSON.stringify(infoDataCart),
			dataType: 'json',
			timeout: 100000,
			success: function(data) {
				alert(data.msg);
				console.log(data.msg);
				$quantity.text(1);
			},
			error: function(e) {
				alert("Lỗi không thêm được sản phẩm");
				console.log("ERROR: ", e);
			}
		});
	}

})()
