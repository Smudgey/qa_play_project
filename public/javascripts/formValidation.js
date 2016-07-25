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

        rFullname.parent().addClass("has-error");
        rBool = false;

    }
    if(rEmail.val() == null || rEmail.val().length == 0){

        rEmail.parent().addClass("has-error");
        rBool = false;

    }
    if(rPassword.val() == null || rPassword.val().length == 0){

        rPassword.parent().addClass("has-error");
        rBool = false;

    }
    if(rConfirm.val() == null || rConfirm.val().length == 0){

        rConfirm.parent().addClass("has-error");
        rBool = false;

    }
    if(rBool){

        rForm.submit()

    }
}

function validateCard(){

        var cForm = $('#card');
        var cardHolder = cForm.find("input[name=cardholder]");
        var cardNumber = cForm.find("input[name=cardNumber]");
        var cV = cForm.find("input[name=cv]");
        var expirationMonth = cForm.find("input[name=expirationMonth]");
        var expirationYear = cForm.find("input[name=expirationYear]");
        var cBool = true;


        if(cardHolder.val() == null || cardHolder.val().length == 0){

            cardHolder.parent().addClass("has-error");
            cBool = false;

        }
        if(cardNumber.val() == null || cardNumber.val().length == 0){

            cardNumber.parent().addClass("has-error");
            cBool = false;

        }
        if(cV.val() == null || cV.val().length == 0){

            cV.parent().addClass("has-error");
            cBool = false;

        }
        if(expirationMonth.val() == null || expirationMonth.val().length == 0){

            expirationMonth.parent().addClass("has-error");
            cBool = false;

        }
        if(expirationYear.val() == null || expirationYear.val().length == 0){

            expirationYear.parent().addClass("has-error")
            cBool = false;

        }

        if(cBool){

            cForm.submit()

        }
}