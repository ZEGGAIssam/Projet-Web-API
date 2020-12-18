$(document).ready(function(){
    $.ajax({
        url:"http://localhost:8080/getAllMeeting",
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
                    jQuery.each(this["choixLocation"], function() {
                        var divInputLoc = document.createElement("div");
                        var inputLoc = document.createElement("input");
                        inputLoc.type = "radio";
                        inputLoc.id = this
                        inputLoc.value = this
                        var labelLoc = document.createElement("label");
                        labelLoc.appendChild(document.createTextNode(this));
                        divInputLoc.appendChild(inputLoc);
                        divInputLoc.appendChild(labelLoc);
                        divLocation.appendChild(divInputLoc);
                    });
                    divMeeting.appendChild(divLocation);

                    var divDate = document.createElement("div");
                    divDate.classList.add("meetingDate");
                    divDate.appendChild(document.createTextNode("Date :"));
                    var divInputDate = document.createElement("div");
                    var inputDate = document.createElement("input");
                    inputDate.type = "radio";
                    inputDate.id = this["name"]
                    inputDate.value = this["name"]
                    var labelDate = document.createElement("label");
                    labelDate.appendChild(document.createTextNode(this["name"]));
                    divInputDate.appendChild(inputDate);
                    divInputDate.appendChild(labelDate);
                    divDate.appendChild(br);
                    divDate.appendChild(divInputDate);
                    divMeeting.appendChild(divDate);
                    var vote = document.createElement("input");
                    vote.type = "submit";
                    vote.name = "Vote;"
                    vote.classList.add("buttonSubmit");
                    divMeeting.appendChild(vote);
                    document.body.appendChild(divMeeting);
                });
            }
        }
    })
});
