function updateStatus(ordeId){
	var statusId=document.getElementById("statusvalue").value;
	console.log(statusId+"aaaa")
$.ajax({
		type: "POST",
		url: "/api/v1/admin/updateStatus",
		data: {
			orderId: orderId,
			statusId: statusId
		},
		success: function(data) {
			location.reload();
		},
		error: function(e) {
			alert("Lỗi");
			console.log("ERROR: ", e);
		}
	})
	
}