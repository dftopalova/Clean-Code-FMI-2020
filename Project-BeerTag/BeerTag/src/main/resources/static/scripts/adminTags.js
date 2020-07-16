$(document).ready(function () {

    const beerUrl = "http://localhost:8080/api/beers";
    const ratingUrl = "http://localhost:8080/api/ratings";
    const userUrl = "http://localhost:8080/api/users";
    const tagsUrl = "http://localhost:8080/api/tags";
    var currRating = null;

    let username = $("#get-username").text().trim();

    $.ajax({
        type: "GET",
        url: tagsUrl,
        success: getTags,
        error: "error get tags"
    });

    function getTags(tags) {
        $("#container").empty();

        let allTags = Object.values(tags);
        console.log(allTags);
        let beerArrSize = tags.length;

        for (var i = 0; i < allTags.length; i++) {
            let tag = allTags[i];

            let userObj = ` <div class="col-lg-4 col-md-4 mb-4" id="beer-card">
            <div class="card h-100" >
              
              <div class="card-header text-center">
              <button value="${allTags[i].tagId}" id="btn-del-beer" type="button" class="btn btn-danger" style="padding-inline: 60px">DELETE</button>
              </div>
            
              <div class="card-body" style="background: rgba(255,252,85,0.21); padding-bottom: 0">
                <label for ="style">Tag id : </label>
                <span id="style"><b>${allTags[i].tagId}</b></span>
                <br>
                <label for ="style">Tag body : </label>
                <span id="brewery"><b>${allTags[i].tagBody}</b></span>
                <br>
                <br>
              </div>     
            </div>
          </div>`;


            $("#container").append(userObj);
        }
    }



    $("body").on("click", "#btn-del-beer", function () {
        console.log("REMOVE TAG CLICKED");
        let tagId = $(this).attr('value');
        let tempUrl = tagsUrl + `/delete/${tagId}`;
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
                    success: deleteTagSuccess,
                    error: deleteTagFail
                });
            });

        function deleteTagSuccess(res) {
            console.log("DELETE TAG SUCCESS");
            console.log(res);
            swal("Done!", "Tag was successfully removed!", "success");
            refreshTags()
        }

        function deleteTagFail(res) {
            console.log("DELETE TAG FAIL");
            console.log(res);
            swal("Oops!", "Something bad happened, sorry.", "error")
        }

    })

    function refreshTags() {
        $.ajax({
            type: "GET",
            url: tagsUrl,
            success: getTags,
            error: "error get tags"
        });
    }
});