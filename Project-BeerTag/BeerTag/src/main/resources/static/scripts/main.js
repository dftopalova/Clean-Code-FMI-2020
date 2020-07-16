$(document).ready(function () {
    const userUrl = "http://localhost:8080/api/users";
    const countryUrl = "http://localhost:8080/api";



    $("h1").click(function () {
        console.log("create user");
        let userId = 4;
        let beerId = 5;
        let rating = 10;

        $.ajax({
            type: "POST",
            url: userUrl + `${userId}/rate?beerID=${beerId}&rating=${rating}`,
            // data: data,
            success: createUser,
            error: printErrorRatingBeer
        });

    });

    function printErrorRatingBeer() {
        console.log("ERROR IN RATING BEER")
    }

    function createUser() {
        console.log("SUCCESS POST")
    }


    $("#btn1").click(function () {
        let userID = 2;

        console.log("get userbyid");
        console.log(userUrl + `/${userID}`);

        $.ajax({
            type: "GET",
            url: userUrl + `/${userID}`,
            success: getUserByID,
            error: printErrorGetUserByID
        });

    });

    function printErrorGetUserByID() {
        console.log("ERROR IN GetUserByID")
    }

    function getUserByID(user) {

        console.log("SUCCESS get")
        console.log(user)
    }




    $("#countryList").click(function () {
        console.log("select clicked")

        $.ajax({
            type: "GET",
            url: userUrl + "/countries",
            success: getCountries,
            error: "error get countries"
        });

    });

    function getCountries(countries) {
        let selectOptions = Object.values(countries);

        for (i = 0; i < selectOptions.length; i++) {

            let optionValue = `<option value="${selectOptions[i].country_id}"> ${selectOptions[i].name} </option>`;
            $("#countryList").append(optionValue);
        }
    }


    console.log("Ready!");
});