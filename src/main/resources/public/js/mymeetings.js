$(document).ready(function(){
    $.ajax({
        url:"http://localhost:8080/getMyMeeting",
        type:GET,
        success:function(response){
            if(response == "invalid token")
            {
                alert(response);
            }
            else
            {
                response.forEach((row) => {
                        console.log(row);
                });
                var divBox = document.createElement("div");
                divBox.classList.add("box");
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
                    var ttVoteLoc = 0;
                    jQuery.each(this["choixLocation"], function(key, value) {
                        ttVoteLoc = ttVoteLoc + value;
                    });
                    jQuery.each(this["choixLocation"], function(key, value) {
                        var divInputLoc = document.createElement("div");
                        var labelLoc = document.createElement("label");
                        labelLoc.classList.add("resLabel");
                        if (ttVoteLoc == 0)
                        {
                            ttVoteLoc = 1;
                        }
                        labelLoc.appendChild(document.createTextNode(key + " : " + Math.trunc(value/ttVoteLoc*100) + "%"));
                        divInputLoc.appendChild(labelLoc);
                        divLocation.appendChild(divInputLoc);
                    });
                    divMeeting.appendChild(divLocation);
                    var divDate = document.createElement("div");
                    divDate.classList.add("meetingDate");
                    divDate.appendChild(document.createTextNode("Date :"));
                    divDate.appendChild(br);
                    var ttVoteDate = 0;
                    jQuery.each(this["choixLocation"], function(key, value) {
                       ttVoteDate = ttVoteDate + value;
                    });

                    jQuery.each(this["choixDate"], function(key, value) {
                        var divInputDate = document.createElement("div");
                        var labelDate = document.createElement("label");
                        labelDate.classList.add("resLabel");
                        if (ttVoteDate == 0)
                        {
                            ttVoteDate = 1;
                        }
                        labelDate.appendChild(document.createTextNode(key + " : " + Math.trunc(value/ttVoteDate*100) + "%"));
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
                                    alert(response);
                                }
                            }
                        });
                    };
                    divMeeting.appendChild(deletebtn);
                    divBox.appendChild(divMeeting);

                });
                document.body.appendChild(divBox);
            }
        }
    })

});
