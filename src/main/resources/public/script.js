function onCreate() {
    var data = "{\"checkIn\":\"2014-01-01T19:28:56.782Z\",\"checkOut\":\"2014-01-01T23:28:56.782Z\"}";

    $.ajax({
        contentType: "application/json",
        type:"POST",
        url:"http://localhost:8081/entries",
        data:data,
        dataType:"json"

        /*
        success: function (response) {

        }
        */
    });

}

function onShow() {

}

function onDelete() {

}