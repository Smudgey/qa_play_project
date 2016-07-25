function validateLogin() {
    var form = $('#loginForm');
    var email = form.find("input[type=text]");
    var password = form.find("input[type=password]");
    var bool = true;

    var emailRegex = new RegExp("^[A-Za-z0-9._-]+@[A-Za-z0-9]+\.[A-Za-z]{2,4}$");
    var passwordRegex = new RegExp("[A-Za-z0-9]");

    if (!emailRegex.test(email.val()) || email.val() == null || email.val().length == 0) {
        email.parent().addClass("has-error");
        bool = false;
        alert("Incorrect email");
        console.log("this is a test");
    }
    if (!passwordRegex.test(password.val()) || password.val() == null || password.val().length == 0) {
        password.parent().addClass("has-error");
        alert("Incorrect password");
        bool = false;
    }
    if (bool) {
        form.submit()
    }
    else{
        alert("Incorrect username or password");
    }
}

function validateRegister(){

    var rForm = $('#registerForm');
    var rFullname = rForm.find("input[name=fullName]");
    var rEmail = rForm.find("input[name=email]");
    var rPassword = rForm.find("input[name=password]");
    var rConfirm = rForm.find("input[name=confirm]");
    var rBool = true;

    var rNameRegex = new RegExp("[A-Za-z]");
    var rEmailRegex = new RegExp("^[A-Za-z0-9._-]+@[A-Za-z0-9]+\.[A-Za-z]{2,4}$");
    var rPasswordRegex = new RegExp("[A-Za-z0-9]");


    if(!rNameRegex.test(rFullname.val()) || rFullname.val() == null || rFullname.val().length == 0){

        rFullname.parent().addClass("has-error");
        alert("Incorrect Name");
        rBool = false;

    }
    if(!rEmailRegex.test(rEmail.val()) || rEmail.val() == null || rEmail.val().length == 0){

        rEmail.parent().addClass("has-error");
        alert("Incorrect Email");
        rBool = false;

    }
    if(!rPasswordRegex.test(rPassword.val()) || rPassword.val() == null || rPassword.val().length == 0){

        rPassword.parent().addClass("has-error");
        rBool = false;

    }
    if(!rPasswordRegex.test(rConfirm.val()) || rConfirm.val() == null || rConfirm.val().length == 0){

        rConfirm.parent().addClass("has-error");
        rBool = false;

    }
    if(rBool){

        rForm.submit()

    }
    else{

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

        var cNameRegex = new RegExp("[A-Za-z]");
        var cNumber = new RegExp("[0-9]");


        if(!cNameRegex.test(cardHolder.val()) || cardHolder.val() == null || cardHolder.val().length == 0){

            cardHolder.parent().addClass("has-error");
            alert("Incorrect Name for Card Holder");
            cBool = false;

        }
        if(!cNumber.test(cardNumber.val()) || cardNumber.val() == null || cardNumber.val().length == 0){

            cardNumber.parent().addClass("has-error");
            alert("Incorrect Card Number");
            cBool = false;

        }
        if(!cNumber.test(cV.val()) || cV.val() == null || cV.val().length == 0){

            cV.parent().addClass("has-error");
            alert("Incorrect cv");
            cBool = false;

        }
        if(!cNumber.test(expirationMonth.val()) || expirationMonth.val() == null || expirationMonth.val().length == 0){

            expirationMonth.parent().addClass("has-error");
            alert("Incorrect Expiration Month");
            cBool = false;

        }
        if(!cNumber.test(expirationYear.val()) || expirationYear.val() == null || expirationYear.val().length == 0){

            expirationYear.parent().addClass("has-error");
            alert("Incorrect Expiration Year");
            cBool = false;

        }

        if(cBool){

            cForm.submit()

        }

}