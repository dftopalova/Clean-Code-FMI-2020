$(document).ready(function () {
    const apiUrl = "http://localhost:8080/api/";


    $(function () {
        $('.form-holder').delegate("input", "focus", function () {
            $('.form-holder').removeClass("active");
            $(this).parent().addClass("active");
        })
    });


    console.log("registration page opened")


});