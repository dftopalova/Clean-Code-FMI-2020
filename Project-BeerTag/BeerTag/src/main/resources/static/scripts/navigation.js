

$(document).ready(function () {
    const userUrl = "http://localhost:8080/api/users/filter";
    const apiUrl = "http://localhost:8080/api/";
    const newUserUrl = "http://localhost:8080/users/new";
    let targetUrl =apiUrl;

    jQuery(document).ready(function() {
        jQuery('#loading').slideUp('1100');
    });


    console.log("Navigation js loaded!");
});