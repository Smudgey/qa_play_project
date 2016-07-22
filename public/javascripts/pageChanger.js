function changePage(page) {
    $('#navbar').find("li[class='active']").removeClass('active');
    switch (page) {
        case "home":
            $('#homePageCategory').addClass('active');
            break;
        case "catalogue":
            $('#catalogueCategory').addClass('active');
            break;
        case "aboutcontact":
            $('#aboutUsPageCategory').addClass('active');
            break;
        case _:
            break;
    }
}

function changeTab(tab) {
    $('#tabGroup').find("a[class='active']").removeClass('active');
    switch (tab) {
        case "editAccount":
            $('#editAccountTab').addClass("active");
            break;
        case "viewAccount":
            $('#viewAccountTab').addClass("active");
            break;
        case "viewPurchaseHistory":
            $('#viewPurchaseHistoryTab').addClass("active");
            break;

    }
}