/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function() {
      cargarNotas();
});

function cargarNotas(){
    var url = "notasServlet";
    $.ajax({
        method: "GET",
        url: url,
        data: {},
        success: function (jsn) {
            //Clear the table:
            $("#tablaNotas > tbody").empty();
            //Put the rankings
            $.each(jsn, function (i, item) {
                var DNI = "* * * * "+(item.DNI).substring(4,item.DNI.length);
                var tipo = "Tipo "+(parseInt(item.tipoExamen) + 1);
                var nota = item.nota; 
                var row = "<tr><td>" + DNI + "</td><td>" + tipo + "</td><td>" + nota + "</td></tr>";
                $("#tablaNotas > tbody").append(row);
            });
        },
        error: function (e) {
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

