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

    //Confirmar test
    $("#btnConfirmarTest").click(function () {
        corregirExamen();
    });

    //Clicks
    $("#myForm").submit(function () {
        if (validacionesAdicionales()) {
            $("#modalTest").modal("show");
        }
        return false;
    });

    $("#btnResulados").click(function () {
        window.location = "resultados.html";
    });
    $("#btnVolver").click(function () {
        window.location = "index.html";
    });
});
function comprobarDatos() {
    DNI = sessionStorage.getItem("_DNI");
    tipoExamen = sessionStorage.getItem("_tipoExamen");
    //Si no existen los datos, redireccionar al index
    if (DNI === null || tipoExamen === null) {
        location.replace("./index.html");
    }

    //Si existen los datos, borrarlos para la proxima vez
    sessionStorage.removeItem("_DNI");
    sessionStorage.removeItem("_tipoExamen");
}

function validacionesAdicionales() {
    var ok = true;
    $("form").find('.selectS').each(function () {
        if (($(this).prop('selectedIndex')) === 0) {
            $(this).focus();
            showToast("Formulario Incompleto", "Completa el Select", "error", "#D43721");
            ok = false;
            return false;
        }
    });
    if (ok === false) {
        return false;
    }

    $("form").find('.divCheckBox').each(function () {
        ok = false;
        var chk;
        var array = $(this).find('input:checkbox').get();
        chk = array[0];
        for (j = 0; j < array.length; j++) {
            if (array[j].checked) {
                ok = true;
            }
        }
        if (ok === false) {
            array[0].focus();
            showToast("Formulario Incompleto", "Completa el CheckBox", "error", "#D43721");
            return false;
        }
    });
    if (ok === false) {
        return false;
    } else {
        return true;
    }
}

function generarExamen() {
    var url = "getExamenServlet";
    $.ajax({
        method: "POST",
        url: url,
        data: {tipoExamen: tipoExamen},
        success: function (rsp) {
            $("#divCargando").fadeOut(400);
            showToast(rsp[0].Nombre, "Cargado correctamente", "success", "#36B62D");

            $.each(rsp[0], function (i, item) {
                if (i !== "_id" && i !== "Nombre") {
                    soluciones.push(item.correcta);
                    tipo.push(item.tipo);
                    switch (item.tipo) {
                        case "radio":
                            escribirRadio(i, item);
                            break;
                        case "text":
                            escribirText(i, item);
                            break;
                        case "checkbox":
                            escribirCheckBox(i, item);
                            break;
                        case "selectS":
                            escribirSelectS(i, item);
                            break;
                        case "multiple":
                            escribirMultiple(i, item);
                            break;
                    }
                }
            });
            //Controlar eventos
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
    var x = parseFloat(i) + parseFloat(1);
    return $("<h3>" + x + ") " + titulo + "</h3>");
}

function corregirExamen() {
    var i = 0;
    $("#divFormulario").click(false);
    $("#btnCorregir").fadeOut(400);
    $("#divBotones").fadeIn(400);
    $("form").find('input:text, select, .divRadio, .divCheckBox')
            .each(function () {
                switch (tipo[i]) {
                    case "radio":
                        //corregirRadio
                        var array = $(this).find('input:radio').get();
                        if (array[soluciones[i]].checked) {
                            nota += (10 / tipo.length);
                        }

                        for (j = 0; j < array.length; j++) {
                            if (array[j].checked) {
                                array[j].parentElement.style.color = "red";
                                array[j].parentElement.style.fontWeight = "bold";
                            }
                        }

                        array[soluciones[i]].parentElement.style.color = "green";
                        array[soluciones[i]].parentElement.style.fontWeight = "bold";
                        break;
                    case "text":
                        $(this).prop('readonly', true);
                        //Corregir text
                        if ($(this).val().toLowerCase() === soluciones[i].toLowerCase()) {
                            nota += 10 / tipo.length;
                            $(this)[0].style.color = "green";
                            $(this)[0].style.fontWeight = "bold";
                            $(this)[0].value = "Correcta: " + soluciones[i];
                        } else {
                            $(this)[0].style.color = "red";
                            $(this)[0].style.fontWeight = "bold";
                            $(this)[0].value = "Error: " + $(this)[0].value + " -> Correcta: " + soluciones[i];
                        }

                        break;
                    case "checkbox":
                        //Recorrer el checkBox - Length de checkBox hijos
                        var array = $(this).find('input:checkbox').get();
                        var punt = 0;
                        var check = 0;
                        var corr = 0;
                        for (j = 0; j < array.length; j++) {
                            if (array[j].checked) {
                                check++;
                                array[j].parentElement.style.color = "red";
                                array[j].parentElement.style.fontWeight = "bold";
                            }
                        }
                        for (x = 0; x < soluciones[i].tam; x++) {
                            array[soluciones[i][x]].parentElement.style.color = "green";
                            array[soluciones[i][x]].parentElement.style.fontWeight = "bold";
                            if (array[soluciones[i][x]].checked) {
                                corr++;
                                punt += (10 / tipo.length) / soluciones[i].tam;
                            }
                        }
                        var t = check - corr;
                        if (check - corr > 0) {
                            for (x = 0; x < t; x++) {
                                punt -= (10 / tipo.length) / soluciones[i].tam;
                            }
                        }
                        if (punt < 0) {
                            punt = 0;
                        }
                        nota += punt;
                        break;
                    case "selectS":
                        $(this).attr("disabled", true);
                        if ($(this)[0].selectedIndex - 1 === soluciones[i]) {
                            nota += 10 / tipo.length;
                            $(this)[0].style.color = "green";
                            $(this)[0].style.fontWeight = "bold";
                            $(this)[0].options[$(this)[0].selectedIndex].innerHTML = "Correcta: " + $(this)[0].options[$(this)[0].selectedIndex].value;
                        } else {
                            $(this)[0].style.color = "red";
                            $(this)[0].style.fontWeight = "bold";
                            $(this)[0].options[$(this)[0].selectedIndex].innerHTML = "Error: " + $(this)[0].options[$(this)[0].selectedIndex].value
                                    + " -> Correcta: " + $(this)[0].options[soluciones[i] + 1].value;
                        }


                        break;
                    case "multiple":
                        $(this).attr("disabled", true);
                        var array = $(this).find('option').get();
                        var punt = 0;
                        var check = 0;
                        var corr = 0;
                        var corr = 0;
                        for (j = 0; j < array.length; j++) {
                            if (array[j].selected) {
                                check++;
                            }
                        }
                        for (x = 0; x < soluciones[i].tam; x++) {
                            if (array[soluciones[i][x]].selected) {
                                corr++;
                                punt += (10 / tipo.length) / soluciones[i].tam;
                            }
                        }

                        //Seleccionar las correctas
                        for (x = 0; x < soluciones[i].tam; x++) {
                            array[soluciones[i][x]].selected = true;
                        }

                        var t = check - corr;
                        if (check - corr > 0) {
                            for (x = 0; x < t; x++) {
                                punt -= (10 / tipo.length) / soluciones[i].tam;
                            }
                        }
                        if (punt < 0) {
                            punt = 0;
                        }
                        nota += punt;
                        break;
                }
                i++;
            });
    nota = Math.round(nota * 100) / 100; //Esto redondea a 2 dec
    showToast("NOTA: " + nota, "", "success", "#36B62D");


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

function escribirRadio(i, pregunta) {
    var $div = $("<div />").addClass("pregunta divRadio");
    $div.append(escribirTitulo(i, pregunta.titulo));
    $.each(pregunta.respuesta, function (i, item) {
        $div.append($("<label><input type='radio' name='" + pregunta.titulo + "'required> " + item + "</label><br>"));
    });
    $("#divFormulario").append($div);
}

function escribirCheckBox(i, pregunta) {
    var $div = $("<div />").addClass("pregunta divCheckBox");
    $div.append(escribirTitulo(i, pregunta.titulo));
    $.each(pregunta.respuesta, function (i, item) {
        $div.append($("<label><input type='checkbox' name='" + pregunta.titulo + "'> " + item + "</label><br>"));
    });
    $("#divFormulario").append($div);
}

function escribirSelectS(i, pregunta) {
    var $div = $("<div />").addClass("pregunta");
    $div.append(escribirTitulo(i, pregunta.titulo));
    var $sel = $("<select class='selectS'/>");
    $sel.append($("<option>Selecciona..</option>"));
    $.each(pregunta.respuesta, function (i, item) {
        $sel.append($("<option>" + item + "</option>"));
    });
    $div.append($sel);
    $("#divFormulario").append($div);
}

function escribirMultiple(i, pregunta) {
    var $div = $("<div />").addClass("pregunta");
    $div.append(escribirTitulo(i, pregunta.titulo));
    var $sel = $("<select multiple required/>");
    $.each(pregunta.respuesta, function (i, item) {
        $sel.append($("<option>" + item + "</option>"));
    });
    $div.append($sel);
    $("#divFormulario").append($div);
}

function escribirText(i, pregunta) {
    var $div = $("<div />").addClass("pregunta");
    $div.append(escribirTitulo(i, pregunta.titulo));
    var $tex = $("<input class='form-control' type='text' name='" + pregunta.titulo + "' required>");
    $div.append($tex);
    $("#divFormulario").append($div);
}
