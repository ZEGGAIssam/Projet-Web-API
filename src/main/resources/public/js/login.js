$(document).ready(function(){
    const params= new URLSearchParams(window.location.search)
    const registerSuccessParam=params.get("register_success")
    if (registerSuccessParam) {
        window.Toastify({
            text: "User has been created successfully !"
        }).showToast()
        // 1) import Toastify library Js sur notre page index.html = page de login
        // importer le style de cet library .js - on a rediriger l'user sur la page index.html avec une query registersuccess (qui permet de verif que l'user a bien été crée)
        // à l'arrivé de l'user sur cette page on vérifie que cette query existe, et si query=exist alors on a utilisé la library toastify pour afficher un msg
        // query = valeur à une page pour que la page
        // Ce sont des valeurs qu'on attribue à notre page HTML pour notre javascript
        //$.notify("User has been updated successfully !")

    }
    $("#loginSend").click(function(){
        var username = $("#usernameLogin").val().trim();
        var password = $("#passwordLogin").val().trim();

        if( username != "" && password != ""){
            $.ajax({
                url:"http://localhost:8080/login",
                type:POST,
                data:JSON.stringify({username:username, password:password}),
                success:function(response){
                    if(response == 1){
                        window.location.href='meetings.html';
                    }else{
                        alert(response);
                    }
                }
            });
        }
    });
});

$(document).ready(function(){
    $("#registerPage").click(function(){
        window.location.href='register.html';
    });
});