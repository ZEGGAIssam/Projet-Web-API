    $(document).ready(function(){
        $("#registerSend").click(function(){
            var username = $("#loginRegister").val().trim();
            var password = $("#passwordRegister").val().trim();
            var firstname = $("#firstnameRegister").val().trim();
            var name = $("#nameRegister").val().trim();

            if( username != "" && password != ""  && firstname != "" && name  != "" ){
                $.ajax({
                    url:"http://localhost:8080/register",
                    type:POST,
                    data:JSON.stringify({name:name,firstname:firstname, username:username, password:password}),
                    success:function(response){
                        console.log(response)
                        if(response == 1){
                            return window.location.href="http://localhost:8080/index.html?register_success=true";
                        }else{
                            alert("The user created already exists !");
                        }
                    }
                });
            }
        });
    });