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

	let products = [
		{
			name: 'JBL E55BT KEY BLACK',
			image1: '/assets/images/JBL_E55BT_KEY_BLACK_6175_FS_x1-1605x1605px.png',
			image2: '/assets/images/JBL_LIVE650BTNC_Product Image_Folded_Black.webp',
			old_price: '400',
			curr_price: '300'
		},
		{
			name: 'JBL JR 310BT',
			image1: '/assets/images/JBL_JR 310BT_Product Image_Hero_Skyblue.png',
			image2: '/assets/images/JBL_JR 310BT_Product Image_Detail_Skyblue.png',
			old_price: '400',
			curr_price: '300'
		},
		{
			name: 'JBL TUNE 750BTNC',
			image1: '/assets/images/kisspng-beats-electronics-headphones-apple-beats-studio-red-headphones.png',
			image2: '/assets/images/JBL_E55BT_KEY_RED_6063_FS_x1-1605x1605px.webp',
			old_price: '400',
			curr_price: '300'
		},
		{
			name: 'JBL Horizon',
			image1: '/assets/images/JBLHorizon_001_dvHAMaster.png',
			image2: '/assets/images/JBLHorizon_BLK_002_dvHAMaster.webp',
			old_price: '400',
			curr_price: '300'
		},
		{
			name: 'JBL Tune 220TWS',
			image1: '/assets/images/JBL_TUNE220TWS_ProductImage_Pink_ChargingCaseOpen.png',
			image2: '/assets/images/JBL_TUNE220TWS_ProductImage_Pink_Back.png',
			old_price: '400',
			curr_price: '300'
		},
		{
			name: 'UA Project Rock',
			image1: '/assets/images/190402_E1_FW19_EarbudsWCase_S13_0033-1_1605x1605_HERO.png',
			image2: '/assets/images/190402_E1_FW19_EarbudsWCase_S13_0033-1_1605x1605_BACK.png',
			old_price: '400',
			curr_price: '300'
		},
	]

	let product_list = document.querySelector('#related-products')

	renderProducts = (products) => {
		products.forEach(e => {
			let prod = `
            <div class="col-4 col-md-6 col-sm-12">
                <div class="product-card">
                    <div class="product-card-img">
                        <img src="${e.image1}" alt="">
                        <img src="${e.image2}" alt="">
                    </div>
                    <div class="product-card-info">
                        <div class="product-btn">
                            <a href="./product-detail.html" class="btn-flat btn-hover btn-shop-now">shop now</a>
                            <button class="btn-flat btn-hover btn-cart-add">
                                <i class='bx bxs-cart-add'></i>
                            </button>
                            <button class="btn-flat btn-hover btn-cart-add">
                                <i class='bx bxs-heart'></i>
                            </button>
                        </div>
                        <div class="product-card-name">
                            ${e.name}
                        </div>
                        <div class="product-card-price">
                            <span><del>${e.old_price}</del></span>
                            <span class="curr-price">${e.curr_price}</span>
                        </div>
                    </div>
                </div>
            </div>
        `
			product_list.insertAdjacentHTML("beforeend", prod)
		})
	}

	renderProducts(products)

})()

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
