$(document).ready(function() {
        const params= new URLSearchParams(window.location.search)
        const updateSuccessParam=params.get("update_success")
        if (updateSuccessParam) {
            window.Toastify({
                text: "User has been updated successfully !"
            }).showToast()
        }

    $("#updateUser").click(function () {
        var firstname = $("#firstnameUpdate").val().trim();
        var name = $("#nameUpdate").val().trim();

        if( firstname != "" && name != ""){
            $.ajax({
                url: "http://localhost:8080/updateUser",
                type: POST,
                data: JSON.stringify({name:name, firstname:firstname}),
                success: function (response) {
                    if (response == 1) {
                        return window.location.href="http://localhost:8080/updateuser.html?update_success=true";
                        //alert("User updated successfully !");
                    } else {
                        alert(response);
                    }
                }
            });
        }
    });
});