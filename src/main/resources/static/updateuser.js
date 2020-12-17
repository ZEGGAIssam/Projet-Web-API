$(document).ready(function() {
    $("#updateuser").click(function () {
        $.ajaxSetup({
            contentType: "application/json; charset=utf-8"
        });

        var firstname = $("#firstnameUpdate").val().trim();
        var name = $("#nameUpdate").val().trim();

        $.ajax({
            url: "http://localhost:8080/updateUser",
            type: POST,
            data: JSON.stringify({name:name, firstname:firstname}),
            success: function (response) {
                if (response == 1) {
                    alert("User updated successfully !");
                } else {

                }
            }
        })
    });
});