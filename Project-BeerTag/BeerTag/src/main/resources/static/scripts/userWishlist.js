$(document).ready(function () {
    const beerDefaultImage = "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"

    console.log($(".role-pane").val());

    const beerUrl = "http://localhost:8080/api/beers";
    const ratingUrl = "http://localhost:8080/api/ratings";
    const userUrl = "http://localhost:8080/api/users";
    const tagsUrl = "http://localhost:8080/api/tags";

    let currentBeerModalId = null;

    let username = $("#get-username").text().trim();

    let urlToGet = userUrl + `/${username}/wishlist`;

    console.log(urlToGet);

    $.ajax({
        type: "GET",
        url: urlToGet,
        success: getBeers,
        error: "error get beers"
    });

    // Function to get all beers via BeerRestController , create a div for each
    // and append it to div container in allBeersOld.html
    function getBeers(beers) {
        $("#container").empty();
        let allBeers = Object.values(beers);
        console.log(allBeers);
        let beerArrSize = beers.length;

        for (var i = 0; i < allBeers.length; i++) {
            let beer = allBeers[i];
            let beerPic = beer.beerPicture;

            if (beerPic === null || beerPic === "") {
                beerPic = beerDefaultImage;
            }


            let beerObj = ` <div class="col-lg-4 col-md-4 mb-4" id="beer-card">
            <div onclick="location.href'#';" class="card h-100" style="cursor: pointer">
            <img class="card-img-top" src="data:image/jpeg;charset=utf-8;base64,` + beerPic + `"  
            height="350px"  value="${allBeers[i].beerId}" />
                                     
              <div class="card-body" style="background: rgba(255,252,85,0.21)">
                <h4 class="card-title">${beer.beerName}</h4>
                <label for ="style">Style: </label>
                <span id="style"><b>${beer.style.name}</b></span>
                <br>
                <label for ="style">Brewery: </label>
                <span id="brewery"><b>${beer.brewery.name}</b></span>
                <br>
                <label for ="abv">Alcohol by volume: </label>
                <span id="abv"><b>${beer.abv}</b></span>
                <br>
                 <label for ="avgRate">Average rating: </label>
                <span id="avgRate"><b>${beer.averageRating}</b></span>
                <br>
                 <label for ="card-text">Description: </label>
                 <p class="card-text">${beer.description}</p>
              </div>
              <div id = "footer" class="card-footer">
           
            <button id="drankBtn" type="button" class="btn btn-outline-warning"
              title="Mark as drank" value="${beer.beerId}">
              <i class="fa fa-beer" aria-hidden="true"></i>
            </button>
             </div>         
            </div>            
          </div>`;

            $("#container").append(beerObj);
        }

    }

    $("body").on("click", "#drankBtn", function () {
        let beerId = $(this).attr('value');
        console.log(beerId);
        let tempUrl = userUrl + `/${username}/markAsTested?beerId=${beerId}`;

        $.ajax({
            type: "PUT",
            url: tempUrl,
            success: () => {
                swal("Done!", "Cheers! You have added this beer in your list with drank beers!", "success");
            },
            error: errorFunction
        });

    });

    function errorFunction(data) {
        swal("Oops!", data.responseJSON.message, "error");
    }

    // ==============================================//
    //BEER DETAILS MODAL

    $("body").on("click", ".card-img-top", function () {

        console.log("GET DETAILS CLICKED");
        let beerId = $(this).attr('value');
        let tempUrl = beerUrl + `/${beerId}`;

        $.ajax({
            type: "GET",
            url: tempUrl,
            success: getBeerModalSuccess,
            error: getBeerModalFail
        });

    });

    function getBeerModalSuccess(res) {
        console.log("SUCCESS GET BEER BY ID");
        let beer = res;
        let beerPic = beer.beerPicture;

        if (beerPic === null || beerPic === "") {
            beerPic = beerDefaultImage;
        }

        console.log(res);
        $("#beer-modal-body").val(" ");
        currentBeerModalId = null;

        let beerObj = ` <div class="card mb-3" id="beer-card">
                 <div>
                  <button value="${beer.beerId}" id="btn-edit-beer" type="button" class=" btn btn-warning my-2 my-sm-0" style="margin-left: 672px" >Edit Beer</button>
                </div>
            <div class="row no-gutters">
                <div class="col-md-6">
            <img class="card-img-top" src="data:image/jpeg;charset=utf-8;base64,` + beerPic + `"  width="200px"/>
             </div>
                 <div class="col-md-6">
              <div class="card-body" style="background: rgba(255,252,85,0.21)">
                <h4 class="card-title">${beer.beerName}</h4>
                <label for ="style">Style: </label>
                <span id="style"><b>${beer.style.name}</b></span>
                <br>
                <label for ="style">Brewery: </label>
                <span id="brewery"><b>${beer.brewery.name}</b></span>
                <br>
                <label for ="abv">Alcohol by volume: </label>
                <span id="abv"><b>${beer.abv}</b></span>
                <br>
                 <label for ="avgRate">Average rating: </label>
                <span id="avgRate"><b>${beer.averageRating}</b></span>
                <br>
                <label for ="card-text">Description: </label>
                <span class="card-text">${beer.description}</span>
              </div>
              <div id = "footer" class="card-footer">
                            
              <section class='rating-widget'>
                <!-- Rating Stars Box -->
                  <div class='rating-stars text-center'>
                    <ul id='stars'>
                      <li class='star' title='Fair' data-value='1' value="${beer.beerId}">
                        <i class='fa fa-star fa-fw'></i>
                      </li>
                      <li class='star' title='Nice' data-value='2' value="${beer.beerId}">
                        <i class='fa fa-star fa-fw'></i>
                      </li>
                      <li class='star' title='Good' data-value='3' value="${beer.beerId}">
                        <i class='fa fa-star fa-fw'></i>
                      </li>
                      <li class='star' title='Excellent' data-value='4' value="${beer.beerId}">
                        <i class='fa fa-star fa-fw'></i>
                      </li>
                      <li class='star' title='WOW!!!' data-value='5' value="${beer.beerId}">
                        <i class='fa fa-star fa-fw'></i>
                      </li>
                    </ul>
                  </div>

              <button  id="remove-wishBtn" type="button" class="btn btn-outline-dark" 
              title="Remove from wishlist" value="${beer.beerId}">
              <span class="tooltiptext">Unmark this wish</span>
              <i class="fas fa-heart-broken" aria-hidden="true"></i>            
              </button>
             <br><br>
              <button id="drankBtn" type="button" class="btn btn-outline-warning"
              title="Mark as drank" value="${beer.beerId}">
               <span class="tooltiptext">Mark as drank</span>
             <i class="fa fa-beer" aria-hidden="true"></i>
             </button>
             <br><br>
              <button value="${beer.beerId}" id="add-tag-btn" type="button" class="btn btn-outline-warning" style="margin-left: 100px" >ADD TAG</button>
              </div> 
              <div id = "tag-footer" class="card-footer">
              <ul id="tag-body">
              </ul>
              </div>                
            </div>
          </div>
        </div>`;

        $("#beer-modal-body").html(beerObj);

        getAndShowTags(beer.beerId);

        console.log(beerObj);
        $("#beerModal").modal();
    }

    function getTagsSuccess(res) {
        console.log("SUCCESS GET TAGS BY ID");
        console.log(res);
        let tagsArr = Object.values(res);
        let tagsArrSize = Object.values(res).length;
        $("#tag-body").empty();

        for (let i = 0; i < tagsArrSize; i++) {
            let currTag = tagsArr[i];
            let tag = `<li >${currTag.tagBody}<span value="${currTag.tagId}" beerId="" class="close">x</span></li> `;
            $("#tag-body").append(tag);
        }
    }

    function getTagsFail(res) {
        console.log("FAIL GET TAGS BY ID");
        console.log(res);

    }

    function getBeerModalFail(res) {
        console.log("FAIL GET BEER BY ID");
        console.log(res);
    }


    // EDIT BEER JQUERY
    $("body").on("click", "#btn-edit-beer", function () {
        console.log("EDIT BEER DETAILS CLICKED");
        let beerId = $(this).attr('value');

        $("#edit-beer-modal-body").val(" ");
        currentBeerModalId = null;

        let beerObj = ` <div class="card mb-3" id="beer-card">
                 <div style="background: rgba(255,252,85,0.34)">
                  <button value="${beerId}" id="btn-edit-beer-submit" type="button" class=" btn btn-warning my-2 my-sm-0" 
                  style="margin-left: 170px" >SUBMIT</button>
                </div>
            <div class="row no-gutters" style="background: rgba(255,252,85,0.34)">
                 <div class="col-md12" style="background: rgba(255,252,85,0.34)" >
              <div class="card-body" style="background: rgba(255,252,85,0.34)">
                <h4 class="card-title">EDIT</h4>
                <br>
                
                <div class="row no-gutters" style="padding: 15px">
                <label for="newBeerName"> Beer name : </label>
                <span>
                <b>
                 <input type="text" class="form-control reg-form" placeholder="Enter new beer name "id="newBeerName" >
                 </b>
                 </span>
                 <br>
                 </div>
                 
               <div class="row no-gutters" style="padding: 15px">
                <label for ="newABV">Alcohol by volume: </label>
                <span>
                <b>
                 <input type="text" class="form-control reg-form" placeholder="Enter new ABV "id="newABV" >
                 </b>
                 </span>
                <br>
                </div>

                <div class="row no-gutters" style="padding: 15px">
                <label for ="newDESC">Description: </label>
                 <span>
                <b>
                 <input type="textarea" class="form-control reg-form" placeholder="Enter new description "id="newDESC" >
                 </b>
                 </span>
                <br>
                </div>  
                
\              </div>
              <div id = "footer" class="card-footer">
                       
              </div>  
            </div>
          </div>
        </div>`;

        $("#edit-beer-modal-body").html(beerObj);
        $("#editBeerModal").modal();
    });

    $("body").on("click", "#btn-edit-beer-submit", function () {

        let newBeerName = $("#newBeerName").val().trim();
        let newDescription = $("#newDESC").val().trim();
        let newABV = $("#newABV").val().trim();

        let data =
            {
                "beerName": newBeerName,
                "description": newDescription,
                "abv": newABV,
            };

        console.log(data)
        console.log("SUBMIT BEER DETAILS CLICKED");
        let beerId = $(this).attr('value');
        let tempUrl = beerUrl + `/${beerId}`;
        console.log(beerId);
        console.log(tempUrl);

        $.ajax({
            type: "PUT",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: 'json',
            url: tempUrl,
            success: submitEditDetailsSuccess,
            error: submitEditDetailsFail
        });

    });

    function submitEditDetailsSuccess(res) {
        console.log("SUBMIT BEER DETAILS SUCCESS")
        console.log(res)

        refreshBeers();
        swal("Success!", "You successfully edited that beer.", "success");

    }

    function submitEditDetailsFail(res) {
        console.log("SUBMIT BEER DETAILS FAIL");
        console.log(res);
        swal("Oops!", "Something bad happened, sorry.", "error");
    }


    $("body").on("click", ".close", function () {
        console.log("REMOVE TAG BTN CLICKED");
        let tagId = $(this).attr('value');
        console.log(tagId);
        let tempUrl = tagsUrl + `/${tagId}/beers/${currentBeerModalId}`;
        console.log(tempUrl);

        $.ajax({
            type: "DELETE",
            url: tempUrl,
            success: removeTagSuccess,
            error: removeTagFail
        });

    });

    function getAndShowTags(beerId) {
        // let beerId = beer.beerId;
        currentBeerModalId = beerId;
        let tempUrl = tagsUrl + `/?beerId=${beerId}`;
        $.ajax({
            type: "GET",
            url: tempUrl,
            success: getTagsSuccess,
            error: getTagsFail
        });

    }

    function removeTagSuccess(res) {
        console.log("SUCCESS DELETE TAG");
        console.log(res)

        getAndShowTags(currentBeerModalId);
        swal("Success!", "You successfully removed that tag.", "success");
    }

    function removeTagFail(res) {
        console.log("FAIL DELETE TAG");
        console.log(res)
        swal("Oops!", "Something bad happened, sorry.", "error");

    }

    $("body").on("click", "#add-tag-btn", function () {
        console.log("ADD TAG CLICKED");
        let beerId = $(this).attr('value');

        swal({
            text: 'Enter your tag:',
            content: "input",
            button: {
                text: "ADD",
                closeModal: false,
            },
        })
            .then(function (content) {
                let data =
                    {
                        "tagBody": content
                    }
                ;
                console.log(data);
                let url = tagsUrl + `/tagBeer?beerId=${currentBeerModalId}`
                console.log(url);
                $.ajax({
                    type: "POST",
                    url: url,
                    data: JSON.stringify(data),
                    contentType: "application/json; charset=utf-8",
                    dataType: 'json',
                    success: function (res) {
                        console.log(res)
                        swal("Done!", "Tag was successfully added!", "success");
                        getAndShowTags(currentBeerModalId)
                    },
                    error: function (res) {
                        console.log(res)
                        swal("Oops!", "Something bad happened, sorry.", "error");
                    },

                });
            })

    });

    //REMOVE FROM WISHES
    $("body").on("click", "#remove-wishBtn", function () {
        let beerId = $(this).attr('value');
        console.log(beerId);
        let temp = userUrl + `/${username}/wishlist?beerId=${beerId}`;

        swal("Are you sure? This will remove it from your wishlist!", {
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
                    url: temp,
                    success: removeWishSuccess,
                    error: removeWishFail
                });
            });

        function removeWishSuccess(res) {
            console.log("REMOVE WISH SUCCESS");
            console.log(res);
            refreshBeers();
            swal("Done!", "Beer was successfully removed!", "success");
        }

        function removeWishFail(res) {
            console.log("DELETE WISH FAIL");
            console.log(res);
            swal("Oops!", "Something went wrong, sorry.", "error")
        }
    });

    //REMOVE FROM DRANK BEERS
    $("body").on("click", "#remove-drankBtn", function () {
        let beerId = $(this).attr('value');
        console.log(beerId);
        let temp = userUrl + `/${username}/testedBeers?beerId=${beerId}`;

        swal("Are you sure? This will remove it from your list with tasted beers!", {
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
                    url: temp,
                    success: removeDrankBeerSuccess,
                    error: removeDrankBeerFail
                });
            });

        function removeDrankBeerSuccess(res) {
            console.log("REMOVE DRANK BEER SUCCESS");
            console.log(res);
            swal("Done!", "Beer was successfully removed!", "success");
        }

        function removeDrankBeerFail(res) {
            console.log("DELETE DRANK BEER FAIL");
            console.log(res);
            swal("Oops!", "Something went wrong, sorry.", "error")
        }
    });

    /* 1. Visualizing things on Hover - See next part for action on click */
    $('body').on('mouseover', '#stars li', function () {
        var onStar = parseInt($(this).data('value'), 10); // The star currently mouse on

        // Now highlight all the stars that's not after the current hovered star
        $(this).parent().children('li.star').each(function (e) {
            if (e < onStar) {
                $(this).addClass('hover');
            } else {
                $(this).removeClass('hover');
            }
        });

    }).on('mouseout', function () {
        $(this).parent().children('li.star').each(function (e) {
            $(this).removeClass('hover');
        });
    });

    /* 2. Action to perform on click */
    $('body').on('click', '#stars li', function () {
        var onStar = parseInt($(this).data('value'), 10); // The star currently selected
        var stars = $(this).parent().children('li.star');
        var beerId = $(this).attr('value');
        console.log("BBER ID ON RATING:");
        console.log(beerId);
        for (i = 0; i < stars.length; i++) {
            $(stars[i]).removeClass('selected');
        }

        for (i = 0; i < onStar; i++) {
            $(stars[i]).addClass('selected');
        }

        // RESPONSE
        var ratingValue = parseInt($('#stars li.selected').last().data('value'), 10);
        console.log(ratingValue);
        $.ajax({
            type: "POST",
            url: ratingUrl + `?username=${username}&beerId=${beerId}&rating=${ratingValue}`,
            success: getRatingsSuccess,
            error: getRatingsFail
        });

        function getRatingsSuccess() {
            swal("Done!", "Thanks for rating!", "success");
            refreshBeers();
        }

        function getRatingsFail() {
            swal("Oops!", "Something went wrong, sorry!", "error");
        }

    });

    function refreshBeers() {
        $.ajax({
            type: "GET",
            url: urlToGet,
            success: getBeers,
            error: "error get beers"
        });
    }

});
