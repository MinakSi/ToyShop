console.log("entered js");
$(document).ready(function () {
    $('.plusminus').click(function () {
        var amount = $('#amount').text();
        console.log("clicked");
        console.log("amount = " + amount);
        $.ajax({
            type: 'POST',
            data: {
                amount: amount,
                operation: this.id
            },
            url: 'plusminus',
            success: function (result) {
                $('#amount').text(result);
                console.log("success");
            }
        });
    });
});