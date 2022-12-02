// Wait for the DOM to be ready
$(document).ready(function() {
	// Initialize form validation on the registration form.
	// It has the name attribute "registration"
	$("form[name='registration']").validate({
		// Specify validation rules
		rules: {
			// The key name on the left side is the name attribute
			// of an input field. Validation rules are defined
			// on the right side
			fullname: "required",
			username: {
				required: true,
				// Specify that email should be validated
				// by the built-in "email" rule
				email: true
			},
			password: {
				minlength: 5
			},
			confirmpassword:{
				minlength: 5,
				equalTo: "#password"
			}
		},
		// Specify validation error messages
		messages: {
			fullname: "*không để trống tên bạn",
			username: {
				required: "Hãy nhập tên tài khoản",
				email: "Bạn không nhập đúng định dạng email"
			},
			password: {
				required: "Hãy nhập mật khẩu",
				minlength: "Mật khẩu của bạn phải dài hơn 5 ký tự"
			},
			confirmpassword:{
				minlength:"Mật khẩu của bạn phải dài hơn 5 ký tự",
				equalTo:"Mật khẩu không trùng khớp"
			}
			//lacked confirm password
		},
		// Make sure the form is submitted to the destination defined
		// in the "action" attribute of the form when valid
		submitHandler: function(form) {
			form.submit();
		}
	});
});