

function contactFromValidation () {
    $(function () {
        $("#contactForm").validate({
            rules: {
                firstName: {
                    required: true,
                    minlength: 2,
                    maxlength: 30
                },
                lastName: {
                    required: true,
                    minlength: 3,
                    maxlength: 30
                },
                email: {
                    required: true,
                    minlength: 10,
                    maxlength: 100,
                    email: true
                },
                subject: {
                    required: true,
                    minlength: 5,
                    maxlength: 50
                },
                message: {
                    required: true,
                    minlength: 10,
                    maxlength: 1500
                }
            },
            messages: {
                firstName: {
                    required: "Please Enter your first name!",
                    minlength: "First name must be at least 3 characters!",
                    maxlength: "First name must not exceed 30 characters!",
                },
                lastName: {
                    required: "Please Enter your last name!",
                    minlength: "Last name must be at least 3 characters!",
                    maxlength: "Last name must not exceed 30 characters!",
                },
                email: {
                    required: "Please Enter your email!",
                    minlength: "Email must be at least 10 characters!",
                    maxlength: "Email must not exceed 100 characters!",
                    email: true
                },
                subject: {
                    required: "Please Enter a subject!",
                    minlength: "Subject must be at least 5 characters!",
                    maxlength: "Subject must not exceed 50 characters!"
                },
                message: {
                    required: "Please Enter a message!",
                    minlength: "message must be at least 10 characters!",
                    maxlength: "Subject must not exceed 1500 characters!"
                }
            },
            submitHandler: function (form) {
                form.submit();
            }
        });
    });
}
contactFromValidation ();