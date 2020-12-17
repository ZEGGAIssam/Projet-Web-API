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
                        alert(response);
                    }else{
                        alert(response);
                    }
                }
            });
        }
    });
});

$(document).ready(function(){
    $("#test").click(function(){
            $.ajax({
                url:"http://localhost:8080/getAllMeeting",
                type:GET,
                success:function(response){
                   console.log(response)
                   if(response == '')
                   {
                       alert("no meetings");
                   }
                   else
                   {
                       response.forEach((row) => {
                               console.log(row);
                       });

                       jQuery.each(response, function() {
                           var body = document.body

                           console.log(this["name"])
                           console.log(this["choixDate"])
                           console.log(this["id"])
                           console.log(this["choixLocation"])

                           var a = document.createElement("p");
                           a.appendChild(document.createTextNode(this["name"]));
                           document.body.appendChild(a);
                           document.body.appendChild(document.createElement("p"));
                       });
                   }
               }
           })
   });
})

$(document).ready(function(){
    $("#registerPage").click(function(){
        window.location.href='register.html';
    });
});