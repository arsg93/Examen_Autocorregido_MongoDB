
$(document).ready(function () {
    cargarNombresExamenes();
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
        var examen = $("#selectExamenes :selected").text();
        var DNI = $("#inpDNI").val();

        sessionStorage.setItem("_DNI", DNI);
        sessionStorage.setItem("_tipoExamen", examen);

        window.location = "examen.html";
    });
});

function cargarNombresExamenes() {
    var url = "getNameExamen";
    $.ajax({
        method: "GET",
        url: url,
        data: {},
        success: function (jsn) {
            $("#divCargando").fadeOut(400);
            $.each(jsn, function (i, item) {
                var option = "<option>" + item + "</option>";
                $("#selectExamenes").append(option);
            });
        },
        error: function (e) {
            $("#divCargando").fadeOut(400);
            if (e["responseJSON"] === undefined) {
                showToast("ERROR DESCONOCIDO", "Inténtelo más tarde", "error", "#D43721");
            } else {
                showToast(e["responseJSON"]["error"], "Ups, algo malo ha ocurrido", "error", "#D43721");
            }
        }
    });
}


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