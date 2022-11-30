// Wait for the DOM to be ready
$(document).ready(function() {
	// Initialize form validation on the registration form.
	// It has the name attribute "registration"
	$("form[name='changePassword']").validate({
		// Specify validation rules
		rules: {
			"oldpass":{
				required:true
			},		
			"newpass": {
				minlength: 5
			},
			"confirmpassword":{
				minlength: 5,
				equalTo: "#newpass"
			}
		},
		// Specify validation error messages
		messages: {
			"oldpass":{
				required:"Hãy nhập đầy đủ"
			},
			"newpass": {
				required: "Hãy nhập mật khẩu",
				minlength: "Mật khẩu của bạn phải dài hơn 5 ký tự"
			},
			"confirmpassword":{
				minlength:"Mật khẩu của bạn phải dài hơn 5 ký tự",
				equalTo:"Mật khẩu không trùng khớp"
			}
			
		},
		// Make sure the form is submitted to the destination defined
		// in the "action" attribute of the form when valid
		submitHandler: function(form) {
			form.submit();
		}
	});
});