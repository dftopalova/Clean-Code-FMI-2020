$(document).ready(function () {
    const url = "http://localhost:8080/api/users/username/";
    let username = $("#get-username").text().trim();
    console.log(username);
    let userpass = null;


    $.ajax({
        type: "GET",
        url: url + username,
        success: getInfo,
        error: getInfoFail
    });

    function getInfo(user) {
        console.log(user);
        let firstName = user.firstName;
        let lastName = user.lastName;
        let picture = user.picture;
        userpass = user.password;

        let names = ` <h5> ${firstName} ${lastName}  </h5> `;
        $("#name-container").append(names);

        let homeInfo =
            `
                                       </div>
<div class="row">
    <div class="col-md-6">
        <div class="row">
            <label>First name - </label>
            <p style="margin-left: 10px"> ${firstName} </p>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-md-6">
        <div class="row">
            <label>Last name -</label>
            <p style="margin-left: 10px">${lastName} </p>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-md-6">
        <div class="row">
            <label>Username -</label>
            <p style="margin-left: 10px">${username} </p>
        </div>
    </div>
</div>
            `;
        $("#home").append(homeInfo);
    }

    $("#profile-update-btn").click(function () {

        let newFirstName = $("#newfirstName").val().trim();
        let newLastName = $("#newlastName").val().trim();
        let newUsername = $("#newUsername").val().trim();

        console.log(newFirstName);
        console.log(newLastName);
        console.log(newUsername);

        let data =
            {
                "firstName": newFirstName,
                "lastName": newLastName,
                "userName": newUsername,
            };
        console.log(data);
        console.log(url + username);
        $.ajax({
            type: "PUT",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: 'json',
            url: url + username,
            success: updateInfoSuccess,
            error: updateInfoFail
        });


    });

    function updateInfoSuccess(res) {
        console.log("UPDATE USER SUCCESS");
        console.log(res);
        alert("Successfully changed your information!");
        location.href = "http://localhost:8080/users/profile"

    }

    function updateInfoFail(res) {
        console.log("UPDATE USER FAIL");
        console.log(res);
        alert("Oops.. something bad happened.");
        location.href = "http://localhost:8080/users/editProfile"
    }


    function getInfoFail(res) {
        console.log("error in user object retrieval");
        console.log(res);
    }


});