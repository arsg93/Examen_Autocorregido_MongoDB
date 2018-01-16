/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var DNI = null;
var tipoExamen = null;
var nota = null;

$(document).ready(function () {
    comprobarDatos();
    generarExamen();

    //Clicks
    $("#myForm").submit(function () {
        corregirExamen();
        return false;
    });


});

function comprobarDatos() {
    DNI = sessionStorage.getItem("_DNI");
    tipoExamen = sessionStorage.getItem("_tipoExamen");

    //Si no existen los datos, redireccionar al index
    if (DNI === null || tipoExamen === null) {
        //location.replace("./index.html");
    }

    //Si existen los datos, borrarlos para la proxima vez
    sessionStorage.removeItem("_DNI");
    sessionStorage.removeItem("_tipoExamen");
}

function generarExamen() {
    var url = "getExamenServlet";
    $.ajax({
        method: "POST",
        url: url,
        data: {tipoExamen: tipoExamen},
        success: function (rsp) {
            showToast("Exámen " + (parseInt(tipoExamen) + 1), "Cargado correctamente", "success", "#36B62D");
            // AQUI SE GENERARA:
            //    ....
            //    ....
            //    ....
            $.each(rsp, function (i, item) {
                escribirTitulo(i, item.titulo);
            });

        },
        error: function (e) {
            if (e["responseJSON"] === undefined) {
                showToast("ERROR DESCONOCIDO", "Inténtelo más tarde", "error", "#D43721");
            } else {
                showToast(e["responseJSON"]["error"], "", "error", "#D43721");
            }
        }
    });
}

function escribirTitulo(i, titulo) {
    $("#divFormulario").append("<h3>" + i + ") " + titulo + "</h3>");
}

function corregirExamen() {
    //   ....
    //   ....
    //   ....

    //Al final guardamos la nota
    guardarNota();
}

function guardarNota() {
    var url = "notasServlet";
    $.ajax({
        method: "POST",
        url: url,
        data: {DNI: DNI, tipoExamen: tipoExamen, nota: nota},
        success: function (rsp) {
            showToast("Guardado Correctamente", "Nota: " + nota, "success", "#36B62D");
        },
        error: function (e) {
            if (e["responseJSON"] === undefined) {
                showToast("ERROR DESCONOCIDO", "Inténtelo más tarde", "error", "#D43721");
            } else {
                showToast(e["responseJSON"]["error"], "", "error", "#D43721");
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
