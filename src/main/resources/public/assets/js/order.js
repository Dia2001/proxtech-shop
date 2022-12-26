function orderHandle() {

	let keyword = "";

	let statusId = "";

	var inputElement = document.querySelector("#search");
	console.log(inputElement);
	inputElement.oninput = function(e) {
		console.log(e.target.value);

		// Load dữ liệu theo input tìm kiếm
		filterOrder(e.target.value, statusId);
		keyword = e.target.value;
	}


	$('#select-status').on('change', function() {
		statusId = this.value;
		console.log(statusId);
		filterOrder(keyword, statusId);
	});

}
$(document).ready(orderHandle)
function detailProduct(orderId) {
	window.location.href = `/don-hang/${orderId}`;
	console.log(idProducts)
}

function repurchase(orderId) {
	$.ajax({
		type: "POST",
		url: "/api/v1/customer/getRepurchase/getOrder",
		data: {
			orderId: orderId
		},
		success: function(data) {
			let idProducts = data
			localStorage.removeItem("listIdProduct");
			localStorage.setItem("listIdProduct", idProducts);
			localStorage.setItem("check", orderId);
			window.location.href = "/thanh-toan";
			console.log(idProducts)
		},
		error: function(e) {
			alert("Lỗi");
			console.log("ERROR: ", e);
		}
	})
}
function filterOrder(keyword, statusId) {
	var where;


	if (keyword === "" && statusId === "") {

	} else {
		if (keyword != "" && statusId != "") {
			where = "keyword=" + keyword;
			where += "&statusId=" + statusId;
		} else {
			if (keyword === "") {
				where = "statusId=" + statusId;
			} else {
				where = "keyword=" + keyword;
			}
		}
	}

	console.log(where);
	$.ajax({
		type: "GET",
		url: "/api/v1/customer/getFilterOrder?" + where,
		success: function(data) {
			console.log("Hello word");
			console.log(data);
			var dataFilter = createListOrder(data);
			document.getElementById('listOrder').innerHTML = dataFilter;
			console.log("aaaaa");
			console.log(dataFilter);
		},
		error: function(e) {
			alert("Lỗi");
			console.log("ERROR: ", e);
		}
	})
}

function createListOrder(data) {
	let path = document.getElementById('path').value;
	let rows = [];
	for (var i = 0; i < data.length; i++) {


		let row = `<div class="w-[80%] mx-auto my-[24px] bg-white">
				<div class="flex justify-between items-center p-4">
					<h4 class="text-[18px] text-black/50"
						>${data[i]["orderStatusName"]}</h4>
					<h4 class="text-right" >Mã hóa đơn: ${data[i]["id"]}  </h4>
				</div>`;

		let products = data[i]["products"];
		console.log(products);
		for (var j = 0; j < products.length; j++) {
			row += `<!-- order -->
				<div>
					<hr class="w-[95%] text-black/50 mx-auto my-[8px]" />
					<div class="flex justify-between items-center p-4">
						<div class="flex gap-4 basis-9/12">
							<div class="relative w-[90px] h-[90px]">
								<img class="w-[85px] h-[85px] object-cover object-center"
									src="${path + products[j]["thumbnail"]}" />
								<h6
									class="w-fit h-fit px-1 rounded-[3px] absolute right-1 bottom-1 bg-black/75 text-white">X${products[j]["number"]}</h6>
							</div>
							<div>
								<h6 >${products[j]["description"]}</h6>
								<h6 >${products[j]["name"]}</h6>
							</div>
						</div>
						<h4 class="basis-3/12 text-right" >${products[j]["price"].toString().replace(/\B(?=(\d{3})+(?!\d))/g, '.')} VNĐ</h4>
					</div>
				</div>
				<!-- order -->`
		}

		row += `<div class="p-4 flex justify-end">
					<div>
						<h5 class="text-right text-[18px] py-2">
							Tổng tiền: <b >${data[i]["checkoutPrice"].toString().replace(/\B(?=(\d{3})+(?!\d))/g, '.')} VNĐ</b>
						</h5>
						<div class="flex justify-between">
							<button
								class="relative inline-flex items-center justify-center p-0.5 mb-2 mr-2 overflow-hidden text-sm font-medium text-gray-900 rounded-lg group bg-gradient-to-br from-cyan-500 to-blue-500 group-hover:from-cyan-500 group-hover:to-blue-500 hover:text-white dark:text-white focus:ring-4 focus:outline-none focus:ring-cyan-200 dark:focus:ring-cyan-800">
								<span
									class="relative px-5 py-2.5 transition-all ease-in duration-75 bg-white dark:bg-gray-900 rounded-md group-hover:bg-opacity-0">
									Mua lại </span>
							</button>
							<button 
								onclick="detailProduct(${data[i]["id"]} )"
								class="relative inline-flex items-center justify-center p-0.5 mb-2 mr-2 overflow-hidden text-sm font-medium text-gray-900 rounded-lg group bg-gradient-to-br from-green-400 to-blue-600 group-hover:from-green-400 group-hover:to-blue-600 hover:text-white dark:text-white focus:ring-4 focus:outline-none focus:ring-green-200 dark:focus:ring-green-800">
								<span
									class="relative px-5 py-2.5 transition-all ease-in duration-75 bg-white dark:bg-gray-900 rounded-md group-hover:bg-opacity-0">
									Xem chi tiết </span>
							</button>
						</div>
					</div>
				</div>
			</div>`;
		rows.push(row);
	}

	return rows;

}