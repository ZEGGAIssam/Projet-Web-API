$(document).ready(function(){
    $("#meetingSend").click(function(){
        var name = $("#nameMeeting").val().trim();
        var location1 = $("#location1").val().trim();
        var location2 = $("#location2").val().trim();
        var location3 = $("#location3").val().trim();
        var date1 = $("#date1").val().trim();
        var date2 = $("#date2").val().trim();
        var date3 = $("#date3").val().trim();

        if( name != "" && location1 != "" && location2 != "" && location3 != "" && date1 != "" && date2 != "" && date3 != ""){
            $.ajax({
                url:"http://localhost:8080/createMeetingPoll",
                type:POST,
                data:JSON.stringify({name:name, location1:location1, location2:location2, location3:location3, date1:date1, date2:date2, date3:date3}),
                success:function(response){
                    console.log(response)
                    if(response == 1){
                        alert("meeting added");
                        window.location.href='meetings.html';
                    }else{
                        alert(response);
                    }
                }
            });
        }
    });
});

