$(document).ready(function(){

    $.ajax({
        url:"http://localhost:8080/getAllMeeting",
        type:GET,
        success:function(response){
            if(response == 0) {
                alert("token problem");
            }
            else
            {
                var i=0;
                var position=["leftbox", "middlebox", "rightbox"];
                jQuery.each(response, function() {
                    var rdLoc = [];
                    var rdDate = [];
                    var divMeeting = document.createElement("div");
                    divMeeting.classList.add("meetingDiv");
                    divMeeting.classList.add("mainDiv");
                    divMeeting.classList.add(position[i%3]);
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
                        rdLoc.push(inputLoc);
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
                        rdDate.push(inputDate);
                        var labelDate = document.createElement("label");
                        labelDate.appendChild(document.createTextNode(key));
                        divInputDate.appendChild(inputDate);
                        divInputDate.appendChild(labelDate);
                        divDate.appendChild(divInputDate);
                        });
                    divMeeting.appendChild(divDate);
                    var vote = document.createElement("input");
                    vote.type = "submit";
                    vote.value = "Vote"
                    vote.classList.add("buttonSubmit");
                    var id = this["id"];
                    vote.onclick = function(){
                        var voteLocation = "";
                        var voteDate = "";
                        rdLoc.forEach(function(item){
                            if(item.checked){
                                voteLocation = item.id;
                            }
                        });
                        rdDate.forEach(function(item){
                            if(item.checked){
                                voteDate = item.id;
                            }
                        });
                        $.ajax({
                            url:"http://localhost:8080/saveVote",
                            type:POST,
                            data:JSON.stringify({id:id, voteLocation:voteLocation, voteDate:voteDate}),
                            success:function(response){
                                if(response == 1){
                                    window.location.href='meetings.html';
                                }
                                else{
                                    alert(response);
                                }
                            }
                        });

                    }
                    divMeeting.appendChild(vote);
                    document.body.appendChild(divMeeting);

                    i++;
                });
            }
        }
    });
});
