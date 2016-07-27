/*

    Validation Functions for the following forms:
        Login (Both Page and Dropdown)
        Entering Credit Card
        Entering Address
        Registering User Details

    Created by Paul on 27/07/2016


 */

function validateLogin() {
    var form = $('#loginForm');
    var email = form.find("input[name=email]");
    var password = form.find("input[name=password]");
    var bool = true;

    if (!/^[A-Za-z0-9._-]+@[A-Za-z0-9]+\.[A-Za-z]{2,4}$/.test(email.val()) || email.val() == null || email.val().length == 0) {
        email.parent().addClass("has-error");
        bool = false;
        alert("Incorrect email");
        console.log("this is a test");
    }
    if (!/[A-Za-z0-9]/.test(password.val()) || password.val() == null || password.val().length == 0) {
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

function validateEditAccount(){

    var eAForm = $('#editAccount');
    var eAName = eAForm.find("input[name=name]");
    var eAEmail = eAForm.find("input[name=email]")
    var eAPhone = eAForm.find("input[name=phone]");

    var eABool = true;

    if(!/[A-Za-z]/.test(eAName.val()) || eAName.val() == null || eAName.val() == 0){
        eAName.parent().addClass("has-error");
        alert("Invalid Name");
        eABool = false;

    }
    if(!/^[A-Za-z0-9._-]+@[A-Za-z0-9]+\.[A-Za-z]{2,4}$/.test(eAEmail.val()) || eAEmail.val() == null || eAEmail.val() == 0){
        eAEmail.parent().addClass("has-error");
        alert("Invalid Email");
        eABool = false;
    }
    if(!/[0-9]/.test(eAPhone.val()) || eAPhone.val() == null || eAPhone.val().length == 0 || eAPhone.val().length != 11){
        eAPhone.parent().addClass("has-error");
        alert("Invalid Phone Number");
        eABool = false;
    }
    if(eABool){
        eAForm.submit()
    }


}

function validateRegister(){

    var rForm = $('#registerForm');
    var rFullname = rForm.find("input[name=fullName]");
    var rEmail = rForm.find("input[name=email]");
    var rPassword = rForm.find("input[name=password]");
    var rConfirm = rForm.find("input[name=confirm]");
    var rBool = true;


    if(!/[A-Za-z]/.test(rFullname.val()) || rFullname.val() == null || rFullname.val().length == 0){

        rFullname.parent().addClass("has-error");
        alert("Incorrect Name");
        rBool = false;

    }
    if(!/^[A-Za-z0-9._-]+@[A-Za-z0-9]+\.[A-Za-z]{2,4}$/.test(rEmail.val()) || rEmail.val() == null || rEmail.val().length == 0){

        rEmail.parent().addClass("has-error");
        alert("Incorrect Email");
        rBool = false;

    }
    if(!/[A-Za-z0-9]/.test(rPassword.val()) || rPassword.val() == null || rPassword.val().length == 0){

        rPassword.parent().addClass("has-error");
        alert("Enter Password")
        rBool = false;

    }
    if(!/[A-Za-z0-9]/.test(rConfirm.val()) || rConfirm.val() == null || rConfirm.val().length == 0){
        if(rPassword != rConfirm){
            rConfirm.parent().addClass("has-error");
            alert("Password does not match");
        }

        rConfirm.parent().addClass("has-error");
        alert("Password does not match or not entered")
        rBool = false;

    }
    if(rBool){

        rForm.submit()

    }

}

function validateAddress() {

    var addressForm = $('#addressForm');
    var houseNumber = addressForm.find("input[name=houseNumber]");
    var streetName = addressForm.find("input[name=streetName]");
    var town = addressForm.find("input[name=town]");
    var city = addressForm.find("input[name=city]");
    var county = addressForm.find("input[name=county]");
    var postcode = addressForm.find("input[name=postcode]");

    var aBool = true;

    if (!/[A-Za-z0-9]/.test(houseNumber.val()) || houseNumber.val() == null || houseNumber.val().length == 0) {

        houseNumber.parent().addClass("has-error");
        alert("Incorrect HouseNumber/Name");
        aBool = false;

    }
    if (!/[A-Za-z]/.test(streetName.val()) || streetName.val() == null || streetName.val().length == 0) {

        streetName.parent().addClass("has-error");
        alert("Incorrect Street Name");
        aBool = false;

    }
    if (!/[A-Za-z]/.test(town.val()) || town.val() == null || town.val().length == 0) {

        town.parent().addClass("has-error");
        alert("Incorrect Town");
        aBool = false;

    }

    if (!/[A-Za-z]/.test(city.val()) || city.val() == null || city.val().length == 0) {

        city.parent().addClass("has-error");
        alert("Incorrect City");
        aBool = false;

    }
    if (!/[A-Za-z]/.test(county.val()) || county.val() == null || county.val().length == 0) {

        county.parent().addClass("has-error");
        alert("Incorrect County");
        aBool = false;

    }
    if (!/[A-Za-z0-9]/.test(postcode.val()) || postcode.val() == null || postcode.val().length == 0) {

        postcode.parent().addClass("has-error");
        alert("Incorrect Post Code");
        aBool = false;

    }

    if (aBool) {

        addressForm.submit()

    }

}

function validateCard(){

        var cardForm = $('#card');
        var cardHolder = cardForm.find("input[name=cardholder]");
        var cardNumber = cardForm.find("input[name=cardnumber]");
        var cV = cardForm.find("input[name=cv]");
        var expirationMonth = cardForm.find("input[name=expirationMonth]");
        var expirationYear = cardForm.find("input[name=expirationYear]");
        var cBool = true;

        if(!/[A-Za-z]/.test(cardHolder.val()) || cardHolder.val() == null || cardHolder.val().length == 0){

            cardHolder.parent().addClass("has-error");
            alert("Incorrect Name for Card Holder");
            cBool = false;

        }
        if(!/[0-9]/.test(cardNumber.val()) || cardNumber.val() == null || (cardNumber.val().length == 0 || cardNumber.val().length != 16)){

            cardNumber.parent().addClass("has-error");
            alert("Incorrect Card Number");
            cBool = false;

        }
        if(!/[0-9]/.test(cV.val()) || cV.val() == null || cV.val().length == 0 && cV.val().length != 3){
            cV.parent().addClass("has-error");
            alert("Incorrect cv");
            cBool = false;

        }
        if(!/[0-9]/.test(expirationMonth.val()) || expirationMonth.val() == null || expirationMonth.val().length == 0 || expirationMonth.val().length != 2){

            expirationMonth.parent().addClass("has-error");
            alert("Incorrect Expiration Month");
            cBool = false;

        }
        if(!/[0-9]/.test(expirationYear.val()) || expirationYear.val() == null || expirationYear.val().length == 0 || expirationYear.val().length != 4){

            expirationYear.parent().addClass("has-error");
            alert("Incorrect Expiration Year");
            cBool = false;

        }

        if(cBool){

            cardForm.submit()

        }

}