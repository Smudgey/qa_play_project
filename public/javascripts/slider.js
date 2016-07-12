console.log();
$('#currentImage :first-child').attr('src', $('#sliderImages :first-child').attr('src'));


setInterval(function () {
    $('#sliderImages').append($('#sliderImages :first-child'));
    $('#currentImage :first-child').attr('src', $('#sliderImages :first-child').attr('src')).fadeOut(0).fadeIn(0);
}, 5000);
