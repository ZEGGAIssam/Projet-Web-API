$(document).ready(function() {
    $("#updateuser").click(function () {

        var firstname = $("#firstnameRegister").val().trim();
        var name = $("#nameRegister").val().trim();

        let db;
        db.collection = function (users) {
            const userRef = db.collection("users");
            userRef.update({name: true});
            userRef.update({firstname: true});
        }
        url:"http://localhost:8080/updateuser",
            type: POST,
            data: JSON.stringify({name: name, firstname: this.firstname}),
            success: function (response) {
                var msg = "";
                console.log(response)
                if (response == 1) {
                    alert("User updated successfully !");
                } else {
                    alert(response);
                }
            }
        }
    });
});