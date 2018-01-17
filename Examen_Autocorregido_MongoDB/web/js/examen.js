/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var DNI = null;
var tipoExamen = null;
var nota = 0;
var tipo = [];
var soluciones = [];
var numSoluciones = [];

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
            $("#divCargando").fadeOut(400);
            showToast("Exámen " + (parseInt(tipoExamen) + 1), "Cargado correctamente", "success", "#36B62D");

            $.each(rsp[0], function (i, item) {
                if (i !== "_id" && i !== "Nombre") {
                    escribirTitulo(i, item.titulo);
                    soluciones.push(item.correcta);
                    tipo.push(item.tipo);
                    switch (item.tipo) {
                        case "radio":
                            escribirRadio(item);
                            break;
                        case "text":
                            escribirText(item);
                            break;
                        case "checkbox":
                            escribirCheckBox(item);
                            break;
                        case "selectS":
                            escribirSelectS(item);
                            break;
                        case "multiple":
                            escribirMultiple(item);
                            break;
                    }
                }
            });
        },
        error: function (e) {
            $("#divCargando").fadeOut(400);
            if (e["responseJSON"] === undefined) {
                showToast("ERROR DESCONOCIDO", "Inténtelo más tarde", "error", "#D43721");
            } else {
                showToast(e["responseJSON"]["error"], "", "error", "#D43721");
            }
        }
    });
}

function escribirTitulo(i, titulo) {
    var x = (i + 1);
    $("#divFormulario").append("<h3>" + x + ") " + titulo + "</h3>");
}

function corregirExamen() {
    var i = 0;
    $("form").find('input:text, select, .divRadio, .divCheckBox')
            .each(function () {
                switch (tipo[i]) {
                    case "radio":
                        //corregirRadio
                        var array = $(this).find('input:radio').get();
                        if (array[soluciones[i]].checked) {
                            nota += (10 / tipo.length);
                        }
                        break;
                    case "text":
                        //Corregir text
                        if ($(this).val().toLowerCase() === soluciones[i].toLowerCase()) {
                            nota += 10 / tipo.length;
                        }
                        break;
                    case "checkbox":
                        //Recorrer el checkBox - Length de checkBox hijos
                        var array = $(this).find('input:checkbox').get();
                        for (x = 0; x < soluciones[i].tam; x++) {
                            if (array[soluciones[i][x]].checked) {
                                nota += (10 / tipo.length) / soluciones[i].tam;
                            }
                        }
                        break;
                    case "selectS":
                        if ($(this)[0].selectedIndex === soluciones[i]) {
                            nota += 10 / tipo.length;
                        }
                        break;
                    case "multiple":
                        var array = $(this).find('option').get();
                        for (x = 0; x < soluciones[i].tam; x++) {
                            if (array[soluciones[i][x]].selected) {
                                nota += (10 / tipo.length) / soluciones[i].tam;
                            }
                        }
                        break;
                }
                i++;
            });
    nota = Math.round(nota * 100) / 100; //Esto redondea a 2 dec
    alert(nota);

    //Al final guardamos la nota
    //guardarNota();
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

function escribirRadio(pregunta) {
    var $div = $("<div />").addClass("divRadio");
    $.each(pregunta.respuesta, function (i, item) {
        $div.append($("<input type='radio' name='" + pregunta.titulo + "'> <p style='display:inline'>" + item + " </p>"))
    });
    $("#divFormulario").append($div);
}

function escribirCheckBox(pregunta) {
    var $div = $("<div />").addClass("divCheckBox");
    $.each(pregunta.respuesta, function (i, item) {
        $div.append($("<input type='checkbox' name='" + pregunta.titulo + "'> <p style='display:inline'>" + item + " </p>"))
    });
    $("#divFormulario").append($div);
}

function escribirSelectS(pregunta) {
    var $sel = $("<select />");
    $.each(pregunta.respuesta, function (i, item) {
        $sel.append($("<option>" + item + "</option>"))
    });
    $("#divFormulario").append($sel);
}

function escribirMultiple(pregunta) {
    var $sel = $("<select multiple />");
    $.each(pregunta.respuesta, function (i, item) {
        $sel.append($("<option>" + item + "</option>"))
    });
    $("#divFormulario").append($sel);
}

function escribirText(pregunta) {
    var $tex = $("<input class='form-control' type='text' name='" + pregunta.titulo + "' required>");
    $("#divFormulario").append($tex);
}
