function changePage(page) {
    $('#navbar').find("li[class='active']").removeClass('active');
    switch (page) {
        case "home":
            $('#homePageCategory').addClass('active');
            break;
        case "catalogue":
            $('#catalogueCategory').addClass('active');
            break;
    }
}