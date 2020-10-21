console.log("entered file");
function validateCheck(){
    console.log("entered check");
    var bad = "";

    if ($("#pass2").val() === "") {
        bad = "No password";
        $("#pass2").css("background", "#ffcab2");
    }
    else {
        if ($("#pass1").val() !== $("#pass2").val()) {
            bad = "Passwords don't equal each other";
            $("#pass2").css("background", "#ffcab2");
            $("#pass1").css("background", "#ffcab2");
        }
    }
    if (bad !== "") {
        alert(bad);
        return false;
    }
}