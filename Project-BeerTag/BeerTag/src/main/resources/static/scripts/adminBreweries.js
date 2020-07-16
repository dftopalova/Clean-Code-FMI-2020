$(document).ready(function () {

    const beerUrl = "http://localhost:8080/api/beers";
    const ratingUrl = "http://localhost:8080/api/ratings";
    const userUrl = "http://localhost:8080/api/users";
    const tagsUrl = "http://localhost:8080/api/tags";
    const breweriesUrl = "http://localhost:8080/api/breweries";
    var currRating = null;

    let username = $("#get-username").text().trim();

    $.ajax({
        type: "GET",
        url: breweriesUrl,
        success: getBreweries,
        error: "error get breweries"
    });

    function getBreweries(tags) {
        $("#container").empty();

        let AllBreweries = Object.values(tags);
        console.log(AllBreweries);
        let beerArrSize = tags.length;

        let addBrew = `
        <div class="col-lg-4 col-md-4 mb-4" ">

            <div class="card h-100">

                <div class="card-header text-center">
                    <button id="btn-add-brew" type="button" class="btn btn-success" style="padding-inline: 60px">
                        ADD NEW BREWERY
                    </button>
                </div>

                <div class="card-body" style="background: rgba(255,252,85,0.21);">
                    <label for="name">Brewery name : </label>
                    <span>
                    <input id="name" class="form-control" type="text" placeholder="Enter brewery name">
                    <label for="address">Brewery address : </label>
                    <span>
                    <input id="address" class="form-control " type="text" placeholder="Enter brewery address">
                    </span>
                    <br>
                    <br>
                </div>
            </div>
            </div>
            `
        $("#container").append(addBrew);


        for (var i = 0; i < AllBreweries.length; i++) {
            let tag = AllBreweries[i];



            let brewerieObj = ` 
        <div class="col-lg-4 col-md-4 mb-4" id="beer-card">
            <div class="card h-100" >
              
              <div class="card-header text-center">
              <button value="${AllBreweries[i].id}" id="btn-del-beer" type="button" class="btn btn-danger" style="padding-inline: 60px">DELETE</button>
              </div>
            
              <div class="card-body" style="background: rgba(255,252,85,0.21); padding-bottom: 0">
              <h4 class="card-title">${AllBreweries[i].name}</h4>
                <label for ="style">Brewery address : </label>
                <span id="style"><b>${AllBreweries[i].address}</b></span>
              </div>     
            </div>
          </div>`;


            $("#container").append(brewerieObj);
        }
    }



    $("body").on("click", "#btn-del-beer", function () {
        console.log("REMOVE BREWERY CLICKED");
        let brewId = $(this).attr('value');
        let tempUrl = breweriesUrl + `/${brewId}`;
        console.log(tempUrl);

        swal("Are you sure? This will remove it from all records!", {
            icon: "warning",
            buttons: {
                cancel: true,
                confirm: {
                    text: "YES",
                    value: true,
                    visible: true,
                    className: "",
                    closeModal: false
                },
                closeModal: false,
            }
        })
            .then(val => {
                if (!val) throw null;
            })
            .then(function () {
                $.ajax({
                    type: "DELETE",
                    url: tempUrl,
                    success: deleteBrewerySuccess,
                    error: deleteBreweryFail
                });
            });

        function deleteBrewerySuccess(res) {
            console.log("DELETE TAG SUCCESS");
            console.log(res);
            swal("Done!", "Brewery was successfully removed!", "success");
            refreshBreweries()
        }

        function deleteBreweryFail(res) {
            console.log("DELETE TAG FAIL");
            console.log(res);
            swal("Oops!", "Something bad happened, sorry.", "error")
        }

    })

    function refreshBreweries() {
        $.ajax({
            type: "GET",
            url: breweriesUrl,
            success: getBreweries,
            error: "error get tags"
        });
    }


    $("body").on("click", "#btn-add-brew", function () {
        console.log("ADD BREWERY CLICKED");

        let brewName = $("#name").val();
        let brewAddr = $("#address").val();

        let data =
        {
        "name": brewName ,
        "address" : brewAddr
        } ;
        console.log(data);

            $.ajax({
                type: "POST",
                url: breweriesUrl,
                data: JSON.stringify(data),
                contentType: "application/json; charset=utf-8",
                dataType: 'json',
                success: function (res) {
                    console.log(res)
                    swal("Done!", "Brewery was successfully added!", "success");
                    refreshBreweries()
                },
                error: function (res) {
                    console.log(res)
                    swal("Oops!", "Something bad happened, sorry.", "error");
                },
            
        })



    });
});