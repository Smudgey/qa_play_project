function validateLogin() {
    var form = $('#loginForm');
    var email = form.find("input[type=text]");
    var password = form.find("input[type=password]");
    var bool = true;


    if (email.val() == null || email.val().length == 0) {
        email.parent().addClass("has-error");
        bool = false;
    }

    if (password.val() == null || password.val().length == 0) {
        password.parent().addClass("has-error");
        bool = false;
    }


    if (bool) {
        form.submit()
    }
}

function validateRegister(){

    var rForm = $('#registerForm');
    var rFullname = rForm.find("input[name=fullName]");
    var rEmail = rForm.find("input[name=email]");
    var rPassword = rForm.find("input[name=password]");
    var rConfirm = rForm.find("input[name=confirm]");
    var rBool = true;


    if(rFullname.val() == null || rFullname.val().length == 0){
        console.log("fullname")

        rFullname.parent().addClass("has-error");
        rBool = false;

    }
    if(rEmail.val() == null || rEmail.val().length == 0){

        console.log("email")

        rEmail.parent().addClass("has-error");
        rBool = false;

    }
    if(rPassword.val() == null || rPassword.val().length == 0){

        console.log("password")

        rPassword.parent().addClass("has-error");
        rBool = false;

    }
    if(rConfirm.val() == null || rConfirm.val().length == 0){

        console.log("confirm")

        rConfirm.parent().addClass("has-error");
        rBool = false;

    }
    if(rBool){

        rForm.submit()

    }
}