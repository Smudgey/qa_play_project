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

    function loginEmail(){

        var loginEmail = document.getElementById("checkEmail");

        if(email.val() == null || email.val() == 0){
            loginEmail.innerHTML = "Invalid Email Address";
            loginEmail.style.color = "#ff0000";

            bool = false;
        }
        else if(!/^[A-Za-z0-9._-]+@[A-Za-z0-9]+\.[A-Za-z]{2,4}$/.test(email.val())){
            loginEmail.innerHTML = "Enter a Valid Email address e.g. daniel47@hotmail.com";
            loginEmail.style.color = "#ff0000";
            bool = false;
        }
        else{
            loginEmail.innerHTML = null;
        }
    }

    function loginPWD() {
        var loginPWD = document.getElementById("checkPWD");
        if (password.val() == null || password.val().length == 0) {
            loginPWD.innerHTML = "Invalid Password";
            loginPWD.style.color = "#ff0000";
            bool = false;
        }
        else{
            loginPWD.innerHTML = null;
        }
    }

    loginEmail();
    loginPWD();
    if(bool){
        form.submit()
    }
    else{
        document.getElementsByName("validateLogin").innerHTML = "Please Enter Email Address and Password";
    }

}

function validateEditAccount(){

    var eForm = $('#editAccount');
    var efName = eForm.find("input[name=fname]");
    var elName = eForm.find("input[name=lname]")
    var eEmail = eForm.find("input[name=email]")
    var ePhone = eForm.find("input[name=phone]");

    var eBool = true;

    function editFirstName(){

        var checkEFN = document.getElementById("checkFN");

        if(efName.val() == null || efName.val() == 0){
            checkEFN.innerHTML = "Invalid First Name";
            checkEFN.style.color = "#ff0000";
            eBool = false;
        }
        else if(!/[A-Za-z]/.test(efName.val())){
            checkEFN.innerHTML = "Please Enter Text Only";
            checkEFN.style.color = "#ff0000";
            eBool = false;
        }
        else{
            checkEFN.innerHTML = "First Name";
            checkEFN.style.color = "#000000";
        }
    }

    function editLastName(){

        var checkELN = document.getElementById("checkLN");

        if(elName.val() == null || elName.val() == 0){
            checkELN.innerHTML = "Invalid Last Name";
            checkELN.style.color = "#ff0000";
            eBool = false;
        }
        else if(!/[A-Za-z]/.test(elName.val())){
            checkELN.innerHTML = "Please Enter Text Only";
            checkELN.style.color = "#ff0000";
            eBool = false;
        }
        else{
            checkELN.innerHTML = "Last Name";
            checkELN.style.color = "#000000";
        }
    }

    function editEmail(){

        var checkEmail = document.getElementById("checkEmail");

        if(eEmail.val() == null || eEmail.val() == 0){
            checkEmail.innerHTML = "Invalid Email Address";
            checkEmail.style.color = "#ff0000";
            eBool = false;
        }
        else if(!/^[A-Za-z0-9._-]+@[A-Za-z0-9]+\.[A-Za-z]{2,4}$/.test(eEmail.val())){
            checkEmail.innerHTML = "Enter a Valid Email address e.g. daniel47@hotmail.com";
            checkEmail.style.color = "#ff0000";
            eBool = false;
        }
        else{
            checkEmail.innerHTML = "Email";
            checkEmail.style.color = "#000000";
        }
    }

    function editPhone(){
        var checkEPH = document.getElementById("checkPhone");
        if (ePhone.val() == null || ePhone.val().length == 0 || ePhone.val().length != 11) {
            checkEPH.innerHTML = "Invalid Phone Number";
            checkEPH.style.color = "#ff0000";
            eBool = false;
        }
        else if(!/[0-9]/.test(ePhone.val())){
            checkEPH.innerHTML = "Enter Numbers Only e.g. 07899324592";
            checkEPH.style.color = "#ff0000";
            eBool = false;
        }
        else{
            checkEPH.innerHTML = "Phone";
            checkEPH.style.color = "#000000";
        }
    }

    editFirstName();
    editLastName();
    editEmail();
    editPhone();

    if(eBool){
        eForm.submit()
    }
    else{
        var failLogin = document.getElementById("failLogin");
        failLogin.innerHTML = "Login Failed";
        failLogin.style.color = "#ff0000";
    }
}

function validateRegister(){

    var rForm = $('#registerForm');
    var rfirstname = rForm.find("input[name=firstName]");
    var rlastname = rForm.find("input[name=lastName]");
    var rPhone = rForm.find("input[name=phone]");
    var rEmail = rForm.find("input[name=email]");
    var rPassword = rForm.find("input[name=password]");
    var rConfirm = rForm.find("input[name=confirm]");
    var rBool = true;

    function validateFirstName(){
        var checkFN = document.getElementById("checkFN");
        if (rfirstname.val() == null || rfirstname.val().length == 0) {

            checkFN.innerHTML = "Invalid First Name";
            checkFN.style.color = "#ff0000";
            rBool = false;
        }
        else if(!/[A-Za-z]/.test(rfirstname.val())){
            checkFN.innerHTML = "Please Text Only";
            checkFN.style.color = "#ff0000";

            rBool= false;

        }
        else{
            checkFN.innerHTML = "First name";
            checkFN.style.color = "#000000";
        }
    }

    function validateLastName(){
        var checkLN = document.getElementById("checkLN");
        if (rlastname.val() == null || rlastname.val().length == 0) {
            checkLN.innerHTML = "Invalid Last Name";
            checkLN.style.color = "#ff0000";
            rBool = false;
        }
        else if(!/[A-Za-z]/.test(rlastname.val())){
            checkLN.innerHTML = "Please Text Only";
            checkLN.style.color = "#ff0000";
            rBool = false;

        }
        else{
            checkLN.innerHTML = "Last name";
            checkLN.style.color = "#000000";
        }
    }

    function validatePhone() {
        var checkPH = document.getElementById("checkPhone");
        if (rPhone.val() == null || rPhone.val().length == 0 || rPhone.val().length != 11) {
            checkPH.innerHTML = "Invalid Phone Number";
            checkPH.style.color = "#ff0000";
            rBool = false;
        }
        else if(!/[0-9]/.test(rPhone.val())){
            checkPH.innerHTML = "Enter Numbers Only e.g. 07899324592";
            checkPH.style.color = "#ff0000";
            rBool = false;
        }
        else{
            checkPH.innerHTML = "Expiration Month";
            checkPH.style.color = "#000000";
        }
    }

    function validateEmail() {
        var checkE = document.getElementById("checkEmail");
        if (rEmail.val() == null || rEmail.val().length == 0) {
            checkE.innerHTML = "Invalid Email Address";
            checkE.style.color = "#ff0000";
            rBool = false;
        }
        else if(!/^[A-Za-z0-9._-]+@[A-Za-z0-9]+\.[A-Za-z]{2,4}$/.test(rEmail.val())){
            checkE.innerHTML = "Enter a Valid Email address e.g. daniel47@hotmail.com";
            checkE.style.color = "#ff0000";
            rBool = false;
        }
        else{
            checkE.innerHTML = "Expiration Year";
            checkE.style.color = "#000000";
        }
    }

    function validatePWD() {
        var checkPWD = document.getElementById("checkPWD");
        if (rPassword.val() == null || rPassword.val().length == 0) {
            checkPWD.innerHTML = "Enter a Password";
            checkPWD.style.color = "#ff0000";
            rBool = false;
        }
        else{
            checkPWD.innerHTML = "Password";
            checkPWD.style.color = "#000000";
        }
    }

    function validateCPWD() {
        var checkCPWD = document.getElementById("checkCPWD");
        if (rConfirm.val() == null || rConfirm.val().length == 0) {
            checkCPWD.innerHTML = "Invalid Expiry Year";
            checkCPWD.style.color = "#ff0000";
            rBool = false;
        }

        else if(rConfirm != rPassword){
            checkCPWD.innerHTML = "Passwords do not Match";
            checkCPWD.style.color = "#ff0000";
        }

        else{
            checkCPWD.innerHTML = "Confirm Password";
            checkCPWD.style.color = "#000000";
        }
    }

    validateFirstName();
    validateLastName();
    validatePhone();
    validateEmail();
    validatePWD();
    validateCPWD();

    if (rBool) {
        rForm.submit()
    }
    else{
        var registerFail = document.getElementById("registerFail");
        registerFail.innerHTML = "Please Enter Missing Details";
        registerFail.style.color = "#ff0000";
    }
}

function validateAddress() {

    var addressForm = $('#addressForm');
    var a1 = addressForm.find("input[name=addressLine1]");
    var a2 = addressForm.find("input[name=addressLine2]");
    var city = addressForm.find("input[name=city]");
    var county = addressForm.find("input[name=county]");
    var postcode = addressForm.find("input[name=postcode]");

    var aBool = true;


    function validateA1(){
        var checka1 = document.getElementById("checkA1");
        if (a1.val() == null || a1.val().length == 0) {
            checka1.innerHTML = "Please Enter Address Line 1";
            checka1.style.color = "#ff0000";
            aBool = false;
        }
        else{
            checka1.innerHTML = null;
        }
    }

    function validateA2(){
        var checka2 = document.getElementById("checkA2");
        if (a2.val() == null || a2.val().length == 0) {
            checka2.innerHTML = "Please Enter Address Line 2";
            checka2.style.color = "#ff0000";
            aBool = false;
        }
        else{
            checka2.innerHTML = null;
        }
    }

    function validateCity(){
        var checkcity = document.getElementById("checkCity");
        if (city.val() == null || city.val().length == 0) {
            checkcity.innerHTML = "Please Enter City";
            checkcity.style.color = "#ff0000";
            aBool = false;
        }
        else if(!/[A-Za-z]/.test(city.val())){
            checkcity.innerHTML = "Invalid City";
            checkcity.style.color = "#ff0000";
            aBool = false;
        }
        else{
            checkcity.innerHTML = null;
        }
    }
    function validateCounty(){
        var checkcounty = document.getElementById("checkCounty");
        if (county.val() == null || county.val().length == 0) {
            checkcounty.innerHTML = "Please Enter County";
            checkcounty.style.color = "#ff0000";
            aBool = false;
        }
        else if(!/[A-Za-z]/.test(county.val())){
            checkcounty.innerHTML = "Invalid City";
            checkcounty.style.color = "#ff0000";
            aBool = false;
        }
        else{
            checkcounty.innerHTML = null;
        }
    }
    function validatePostCode(){
        var checkpostcode = document.getElementById("checkPostCode");
        if (postcode.val() == null || postcode.val().length == 0) {
            checkpostcode.innerHTML = "Please Enter Post Code";
            checkpostcode.style.color = "#ff0000";
            aBool = false;
        }
        else if(!/^([A-PR-UWYZ0-9][A-HK-Y0-9][AEHMNPRTVXY0-9]?[ABEHMNPRVWXY0-9]? {1,2}[0-9][ABD-HJLN-UW-Z]{2}|GIR 0AA)$/.test(postcode.val())){
            checkpostcode.innerHTML = "Invalid Post Code";
            checkpostcode.style.color = "#ff0000";
            aBool = false;
        }
        else{
            checkpostcode.innerHTML = null;
        }
    }

    validateA1();
    validateA2();
    validateCity();
    validateCounty();
    validatePostCode();

    if (aBool) {
        addressForm.submit()
    }
    else{
        var addressFail = document.getElementById("addressFail");
        addressFail.innerHTML = "Please Enter Missing Details";
        addressFail.style.color = "#ff000000";
    }

}

function validateCard(){

        var cardForm = $('#card');
        var cardHolder = cardForm.find("input[name=cardholder]");
        var cardNumber = cardForm.find("input[name=cardnumber]");
        var expirationMonth = cardForm.find("input[name=expirationMonth]");
        var expirationYear = cardForm.find("input[name=expirationYear]");
        var cBool = true;

    function validateCardHolder(){
        var checkCH = document.getElementById("checkCardH");
        if (cardHolder.val() == null || cardHolder.val().length == 0) {
            checkCH.innerHTML = "Invalid Card Holder";
            checkCH.style.color = "#ff0000";
            cBool = false;
        }
        else if(!/[A-Za-z]/.test(cardHolder.val())){
            checkCH.innerHTML = "Please Enter Text Only";
            checkCH.style.color = "#ff0000";
            cBool= false;
        }
        else{
            checkCH.innerHTML = "Cardholder Name";
            checkCH.style.color = "#000000";
        }
    }

    function validateCardNumber(){
        var checkCN = document.getElementById("checkCardN");
        if (cardNumber.val() == null || cardNumber.val().length == 0 || cardNumber.val().length != 16) {
            checkCN.innerHTML = "Invalid Card Number";
            checkCN.style.color = "#ff0000";
            cBool = false;
        }
        else if(!/[0-9]/.test(cardNumber.val()) ){
            checkCN.innerHTML = "Please Enter Numbers Only e.g. 1234567812345678";
            checkCN.style.color = "#ff0000";
            cBool = false;
        }
        else{
            checkCN.innerHTML = "Card Number";
            checkCN.style.color = "#000000";
        }
    }

    function validateMonth() {
        var checkM = document.getElementById("checkMonth");
        if (expirationMonth.val() == null || expirationMonth.val().length == 0 || expirationMonth.val() > 12 || expirationMonth.val().length != 2) {
            checkM.innerHTML = "Invalid Expiry Month";
            checkM.style.color = "#ff0000";
            cBool = false;
        }
        else if(!/[0-9]/.test(expirationMonth.val())){
            checkM.innerHTML = "Enter only Numbers e.g. 02 for February";
            checkM.style.color = "#ff0000";
            cBool = false;
        }
        else{
            checkM.innerHTML = "Expiration Month";
            checkM.style.color = "#000000";
        }
    }

    function validateYear() {
        var checkY = document.getElementById("checkYear");
        if (expirationYear.val() == null || expirationYear.val().length == 0 || expirationYear.val().length != 2) {
            checkY.innerHTML = "Invalid Expiry Year";
            checkY.style.color = "#ff0000";
            cBool = false;
        }
        else if(!/[0-9]/.test(expirationYear.val()) ){
            checkY.innerHTML = "Enter only Numbers e.g. 21 for 2021";
            checkY.style.color = "#ff0000";
            cBool = false;
        }
        else{
            checkY.innerHTML = "Expiration Year";
            checkY.style.color = "#000000";
        }
    }

    validateCardHolder();
    validateCardNumber();
    validateMonth();
    validateYear();

    if (cBool) {
        cardForm.submit()
    }
    else{
        var cardFail = document.getElementById("cardFail");
        cardFail.innerHTML = "Please Enter Missing Details";
        cardFail.style.color = "#ff0000";

    }


}