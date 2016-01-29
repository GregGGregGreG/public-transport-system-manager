$(document).ready(function () {
    $(function () {
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(document).ajaxSend(function (e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
    });

    $('#find_op_cost').on('click', function () {
        var start = $('#startStation').val();
        var end = $('#stopStation').val();


        var find = {
            start: start,
            end: end
        };
        console.log(find);

        $.ajax({
            type: 'post',
            url: window.location.origin + "/find_optimal_cost",
            headers: {
                'Content-Type': 'application/json'
            },
            dataType: "json",
            data: JSON.stringify(find),
            success: function (findResult) {
                console.log(findResult);

                $("<li>" + findResult.path + "</li>");
                $("<li>" + findResult.cost + "</li>");
            },
            error: function (error) {
                console.log(error);
            }

        })
    })

});
