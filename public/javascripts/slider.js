console.log();
$('#currentImage :first-child').attr('src', $('#sliderImages :first-child').attr('src'));


setInterval(function () {
    $('#sliderImages').append($('#sliderImages :first-child'));
    $('#currentImage :first-child').attr('src', $('#sliderImages :first-child').attr('src')).fadeOut(1800).fadeIn(1500);
}, 3000);
