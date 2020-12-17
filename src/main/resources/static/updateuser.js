let db;
db.collection = function (users) {

    }
        $(document).ready(function () {
            $("#updateuser").click(function () {

                const userRef = db.collection("users");
                 userRef.update({name: true});
                 userRef.update({firstname: true});

            });
        });
