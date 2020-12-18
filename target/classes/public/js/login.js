$(document).ready(function(){
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