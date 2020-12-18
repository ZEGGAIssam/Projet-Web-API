$(document).ready(function(){
    $.ajax({
        url:"http://localhost:8080/getMyMeeting",
        type:GET,
        success:function(response){
            console.log(response)
            if(response == 0)
            {
                alert("token problem");
            }
            else
            {
                response.forEach((row) => {
                        console.log(row);
                });

                jQuery.each(response, function() {
                    var divMeeting = document.createElement("div");
                    divMeeting.classList.add("meetingDiv");
                    divMeeting.classList.add("mainDiv");
                    var divName = document.createElement("div");
                    divName.classList.add("meetingName");
                    divName.appendChild(document.createTextNode(this["name"]));
                    divMeeting.appendChild(divName);
                    var divLocation = document.createElement("div");
                    divLocation.classList.add("meetingLocation");
                    divLocation.appendChild(document.createTextNode("Location :"));
                    var br =  document.createElement("br");
                    divLocation.appendChild(br);
                    jQuery.each(this["choixLocation"], function(key, value) {
                        var divInputLoc = document.createElement("div");
                        var inputLoc = document.createElement("input");
                        inputLoc.type = "radio";
                        inputLoc.id = key
                        inputLoc.value = key
                        inputLoc.name = "choixLocation";
                        var labelLoc = document.createElement("label");
                        labelLoc.appendChild(document.createTextNode(key));
                        divInputLoc.appendChild(inputLoc);
                        divInputLoc.appendChild(labelLoc);
                        divLocation.appendChild(divInputLoc);
                    });
                    divMeeting.appendChild(divLocation);

                    var divDate = document.createElement("div");
                    divDate.classList.add("meetingDate");
                    divDate.appendChild(document.createTextNode("Date :"));
                    divDate.appendChild(br);
                    jQuery.each(this["choixDate"], function(key, value) {
                        var divInputDate = document.createElement("div");
                        var inputDate = document.createElement("input");
                        inputDate.type = "radio";
                        inputDate.id = key;
                        inputDate.value = key;
                        inputDate.name = "choixDate";
                        var labelDate = document.createElement("label");
                        labelDate.appendChild(document.createTextNode(key));
                        divInputDate.appendChild(inputDate);
                        divInputDate.appendChild(labelDate);
                        divDate.appendChild(divInputDate);
                    });
                    divMeeting.appendChild(divDate);
                    var deletebtn = document.createElement("input");
                    deletebtn.type="submit";
                    deletebtn.value = "Delete";
                    deletebtn.classList.add("buttonSubmit");
                    var id = this["id"];
                    deletebtn.onclick = function(){
                        $.ajax({
                            url:"http://localhost:8080/deleteAMeeting",
                            type:POST,
                            data:JSON.stringify({id:id}),
                            success:function(response){
                                console.log(response)
                                if(response == 1){
                                    return window.location.href="http://localhost:8080/mymeetings.html";
                                }else{
                                    alert("can't delete");
                                }
                            }
                        });
                    };
                    divMeeting.appendChild(deletebtn);
                    document.body.appendChild(divMeeting);
                });
            }
        }
    })

});
