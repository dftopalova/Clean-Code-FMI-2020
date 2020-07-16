$(document).ready(function () {

    const beerUrl = "http://localhost:8080/api/beers";
    const ratingUrl = "http://localhost:8080/api/ratings";
    const userUrl = "http://localhost:8080/api/users";
    const tagsUrl = "http://localhost:8080/api/tags";
    const stylesUrl = "http://localhost:8080/api/styles";
    var currRating = null;

    let username = $("#get-username").text().trim();

    $.ajax({
        type: "GET",
        url: stylesUrl,
        success: getStyles,
        error: "error get styles"
    });

    function getStyles(tags) {
        $("#container").empty();

        let allStyles = Object.values(tags);
        console.log(allStyles);

        let addStyle = `
        <div class="col-lg-4 col-md-4 mb-4" ">

            <div class="card h-100">

                <div class="card-header text-center">
                    <button id="btn-add-brew" type="button" class="btn btn-success" style="padding-inline: 60px">
                        ADD NEW STYLE
                    </button>
                </div>

                <div class="card-body" style="background: rgba(255,252,85,0.21);">
 
                    <label for="styleName">Beer style name : </label>
                    <span>
                    <input id="styleName" class="form-control " type="text" placeholder="Enter beer style">
                    </span>
                    <br>
                    <br>
                </div>
            </div>
            </div>
            `

        $("#container").append(addStyle);

        for (var i = 0; i < allStyles.length; i++) {
            let style = allStyles[i];

            let styleObj = ` <div class="col-lg-4 col-md-4 mb-4" id="beer-card">
            <div class="card h-100" >
              
              <div class="card-header text-center">
              <button value="${allStyles[i].id}" id="btn-del-beer" type="button" class="btn btn-danger" style="padding-inline: 60px">DELETE</button>
              </div>
            
              <div class="card-body" style="background: rgba(255,252,85,0.21); padding-bottom: 0">
                <label for ="style">Style id : </label>
                <span id="style"><b>${allStyles[i].id}</b></span>
                <br>
                <label for ="style">Style name : </label>
                <span id="brewery"><b>${allStyles[i].name}</b></span>
                <br>
                <br>
              </div>     
            </div>
          </div>`;


            $("#container").append(styleObj);
        }
    }



    $("body").on("click", "#btn-del-beer", function () {
        console.log("REMOVE STYLE CLICKED");
        let styleId = $(this).attr('value');
        let tempUrl = stylesUrl + `/${styleId}`;
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
                    success: deleteStyleSuccess,
                    error: deleteStyleFail
                });
            });

        function deleteStyleSuccess(res) {
            console.log("DELETE STYLE SUCCESS");
            console.log(res);
            swal("Done!", "Style was successfully removed!", "success");
            refreshStyles()
        }

        function deleteStyleFail(res) {
            console.log("DELETE STYLE FAIL");
            console.log(res);
            swal("Oops!", "Something bad happened, sorry.", "error")
        }

    })

    function refreshStyles() {
        $.ajax({
            type: "GET",
            url: stylesUrl,
            success: getStyles,
            error: "error get styles"
        });
    }

    $("body").on("click", "#btn-add-brew", function () {
        console.log("ADD BREWERY CLICKED");

        let brewName = $("#styleName").val();

        let data =
            {
                "name": brewName
            } ;
        console.log(data);

        $.ajax({
            type: "POST",
            url: stylesUrl,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: 'json',
            success: function (res) {
                console.log(res)
                swal("Done!", "Beer style was successfully added!", "success");
                refreshStyles()
            },
            error: function (res) {
                console.log(res)
                swal("Oops!", "Something bad happened, sorry.", "error");
            },

        })
    });


});