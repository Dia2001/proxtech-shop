function paymentHandle() {
	var inputElement = document.querySelector("#idAddress");
	var addressDefault = document.getElementById("adressShip").textContent;
	console.log(inputElement);
	inputElement.oninput = function(e) {
		console.log(e.target.value);
		adress(e.target.value, addressDefault);
	}

	let listProductId = localStorage.getItem("listIdProduct");
	console.log(listProductId);
	productsDetail(listProductId);
	function productsDetail(listProductId) {
		var status;
		if (localStorage.getItem("check") === null) {
			// This is cart
			 status="cart";
		}else{
			 status=localStorage.getItem("check");
		}
		console.log(status);
		debugger
		var infoListIdProduct = {
			listProductId: Array.from(listProductId.split(',')).map(function(item) {
				return item;
			}),
			status: status
		}
		$.ajax({
			type: "POST",
			contentType: "application/json",
			url: "/api/v1/customer/productDetailpayment",
			data: JSON.stringify(infoListIdProduct),
			dataType: 'json',
			timeout: 100000,
			success: function(data) {
				document.getElementById('listproduct').innerHTML = createListProduct(data);
				localStorage.removeItem("check");
				checkPayment(listProductId,status);
			},
			error: function(e) {
				alert("Lỗi không lấy được sản phẩm");
				console.log("ERROR: ", e);
			}
		});
	}


	document.getElementById("ipFullname").readOnly = true;
	document.getElementById("ipPhone").readOnly = true;
};
$(document).ready(paymentHandle);

function adress(keyword, addressDefault) {

	if (keyword != "") {
		document.getElementById('adressShip').innerHTML = keyword;
	} else {
		document.getElementById('adressShip').innerHTML = addressDefault;
	}
}

function checkPayment(listProductId,status) {
	// check button payment
	//console.log(checkPayment!=null?'aaaa'+checkPayment:bbb+checkPayment);
	let checkPayment = document.getElementById('btnPayment');
	console.log(document.getElementById('btnPayment'))
	console.log(checkPayment);
	checkPayment.onclick = function() {
		var infoListIdProduct = {
				listProductId: Array.from(listProductId.split(',')).map(function(item) {
					return item;
				}),
				note: $("#ipNote").val(),
				addressShip: $("#adressShip").text(),
				status: status
			}
		if(status==="cart"){
			$.ajax({
				type: "POST",
				contentType: "application/json",
				url: "/api/v1/customer/payment",
				data: JSON.stringify(infoListIdProduct),
				dataType: 'json',
				timeout: 100000,
				success: function(data) {
					console.log(data.msg);
					alert(data.msg);
					location.href = "/don-hang"
					//checkPayment();
				},
				error: function(e) {
					alert("Lỗi không thanh toán được");
					console.log("ERROR: ", e);
				}
			});
		}else{
			$.ajax({
				type: "POST",
				contentType: "application/json",
				url: "/api/v1/customer/payment/Repurchase",
				data: JSON.stringify(infoListIdProduct),
				dataType: 'json',
				timeout: 100000,
				success: function(data) {
					console.log(data.msg);
					alert(data.msg);
					location.href = "/don-hang"
				},
				error: function(e) {
					alert("Lỗi không thanh toán được");
					console.log("ERROR: ", e);
				}
			});
		}
	}
}

function createListProduct(data) {
	let rows = [];
	var totalPayment = 0;
	for (var i = 0; i < data.length; i++) {
		totalPayment += data[i]["price"] * data[i]["quantity"];
		let row = `<div class="card_item">
					<div class="product_img">
						<img
							src="./uploads/${data[i]["thumbnail"]}"
							alt="" />
					</div>
					<div class="product_info">
						<h1>${data[i]["name"]}</h1>
						<p>${data[i]["description"]}</p>
						<div class="close-btn">
							<i class="fa fa-close"></i>
						</div>
						<div class="product_rate_info">
							<h1>${data[i]["price"].toString().replace(/\B(?=(\d{3})+(?!\d))/g, '.')}</h1>
							 <span class="pqt">${data[i]["quantity"]}</span> 
						</div>
					</div>
				</div> `;

		rows.push(row);
	}

	var rowTotalPayment = `<div class="order_price">
					<p>Tổng thanh toán</p>
					<h4>${totalPayment.toString().replace(/\B(?=(\d{3})+(?!\d))/g, '.')} VNĐ</h4>
				</div>
				<div class="order_service">
					<p>Khuyến mãi</p>
					<h4>0 VNĐ</h4>
				</div>
				<div class="order_total">
					<p>Tổng tiền</p>
					<h4>${totalPayment.toString().replace(/\B(?=(\d{3})+(?!\d))/g, '.')} VNĐ</h4>
				</div>
				<button id="btnPayment" class="btn-flat btn-hover">Thanh toán</button>`;
	rows.push(rowTotalPayment);
	return rows;

}