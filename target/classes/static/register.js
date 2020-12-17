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
                    var msg = "";
                    console.log(response)
                    if(response == 1){
                        alert("let's go");
                    }else{
                        alert(response);
                    }
                }
            });
        }
    });
});
$("#registerPage").click(function(){
  window.location.href='meetingForm.html';
  window.location.href='register.html';
});