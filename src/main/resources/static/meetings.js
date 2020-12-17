    $(document).ready(function(){
        $.ajax({
            url:"http://localhost:8080/getAllMeeting",
            type:GET,
            success:function(response){
                console.log(response)
                if(response == ''){
                    alert("no meetings");
                }else{
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
        });
    });