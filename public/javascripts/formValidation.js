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