$(document).ready(function(){
    $.ajax({
        url:"http://localhost:8080/logout",
        type:POST,
        success:function(response){
            if(response == 1){
                window.location.href='index.html';
            }else{
                alert(response);
            }
        }
    });
});