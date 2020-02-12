<script>
function validateSignUp()
{
    var firstname = document.form.firstname.value;
    var lastname = document.form.lastname.value;
    var email = document.form.email.value;
    var username = document.form.username.value;
    var password = document.form.password.value;
    var conpassword= document.form.confirmPassword.value;

    if (firstname==null || firstname=="")
    {
        alert("Full Name can't be blank");
        return false;
    }
    else if (email==null || email=="")
    {
        alert("Email can't be blank");
        return false;
    }
    else if (email==null || email=="")
    {
        alert("Email can't be blank");
        return false;
    }
    else if (username==null || username=="")
    {
        alert("Username can't be blank");
        return false;
    }
    else if(password.length<6)
    {
        alert("Password must be at least 6 characters long.");
        return false;
    }
    else if (password!=conpassword)
    {
        alert("Confirm Password should match with the Password");
        return false;
    }
}
</script>