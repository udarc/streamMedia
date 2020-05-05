function confirmDeletion() {
        var x = confirm("Are you sure you want to delete this item?");
        if (x)
            return true;
        else
            return false;
}

//https://cdnjs.com/libraries/AlertifyJS
function completeWithSuccess(object, action,url) {
    swal({
        title: "Successfully " + action + "ed " + object + "!",
        text: "Redirecting in 4 seconds.",
        type: "success",
        timer: 4000,
        showConfirmButton: false
    }, function(){
        window.location.href = url;
    });

}

