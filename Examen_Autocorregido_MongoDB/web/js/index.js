
$(document).ready(function () {

    //Ver resultados
    $("#btnResulados").click(function () {
        window.location = "resultados.html";
    });

    //Mostrar confirmacion
    $("form").submit(function () {
        $("#modalTest").modal("show");
        return false;
    });

    //Confirmar test
    $("#btnConfirmarTest").click(function () {
        var examen = $("#selectExamenes")[0].selectedIndex;
        var DNI = $("#inpDNI").val();

        sessionStorage.setItem("_DNI", DNI);
        sessionStorage.setItem("_tipoExamen", examen);

        location.replace("./examen.html");
    });
});


function showToast(head, text, icon, bgColor) {
    $.toast({
        text: text, // Text that is to be shown in the toast
        heading: head, // Optional heading to be shown on the toast
        icon: icon, // Type of toast icon: warning | success | error | info
        showHideTransition: 'fade', // fade, slide or plain
        allowToastClose: false, // Boolean value true or false
        hideAfter: 3000, // false to make it sticky or number representing the miliseconds as time after which toast needs to be hidden
        position: 'top-center', // bottom-left or bottom-right or bottom-center or top-left or top-right or top-center or mid-center or an object representing the left, right, top, bottom values
        textAlign: 'left', // Text alignment i.e. left, right or center
        loader: true, // Whether to show loader or not. True by default
        loaderBg: '#9EC600', // Background color of the toast loader
        bgColor: bgColor
    });
}