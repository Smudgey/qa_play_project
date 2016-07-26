/*
 * Created By Rytis
 *
 * Last worked on by Rytis on 26/07/2016
 */

/**
 * this function will highlight selected links in navbar
 * @param page
 */
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

/**
 * this function will change highlight of the items in account sidebar
 * @param tab
 */
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
        case "viewCard":
            $('#viewCardTab').addClass("active");
            break;
        case "registerCard":
            $('#registerCardTab').addClass("active");
            break;
        case "viewAddress":
            $('#viewAddressTab').addClass("active");
            break;
        case "registerAddress":
            $('#registerAddressTab').addClass("active");
            break;

    }
}

/**
 * this interacts with customer satisfaction star rating on purchase history
 */
$(document).ready(function () {

    $('.star').on('click', function () {
        clearStars($(this).parent());
        var index = $(this).index() + 1;
        var form = $(this).parent().parent();

        $(this).parent().children().each(function () {
            if ($(this).index() == index) {
                return false;
            } else {
                $(this).removeClass('glyphicon-star-empty').addClass('glyphicon-star')
            }
        });

        form.find('input[name="rating"]').val(index);
        form.submit()
    });


    function clearStars(parent) {
        parent.children().each(function () {
            if ($(this).hasClass('glyphicon-star')) {
                $(this).removeClass('glyphicon-star').addClass('glyphicon-star-empty')
            }
        })
    }
});