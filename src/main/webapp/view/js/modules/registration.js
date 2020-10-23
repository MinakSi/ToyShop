console.log("entered file");

function validateCheck() {
    console.log("entered validate check");
    var bad = "";
    if ($("#pass1").val() !== $("#pass2").val()) {
        bad = "Passwords don't equal each other";
        $("#pass2").css("background", "#ffcab2");
        $("#pass1").css("background", "#ffcab2");
        alert(bad);
        return false;
    }
    return true;
}