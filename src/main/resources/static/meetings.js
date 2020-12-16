$(document).ready(function(){
    $.ajax({
        url:"http://localhost:8080/getAllMeeting",
        type:GET,
        success:function(response){
            console.log(response)
            if(response == ''){
                alert("no meetings");
            }else{
                jQuery.each(response, function() {
                    var body = document.body
                    var a = document.createElement("a");
                    a.appendChild(document.createTextNode(this["name"]));
                    document.body.appendChild(a);
                    document.body.appendChild(document.createElement("p"));
                });
            }
        }
    });
});