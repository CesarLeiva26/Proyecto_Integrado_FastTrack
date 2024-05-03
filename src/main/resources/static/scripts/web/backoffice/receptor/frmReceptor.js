$(document).on("click", "#btnagregar", function(){
    $("#txtdni").val("");
    $("#txtnombre").val("");
    $("#txtapellido").val("");
    $("#txtcelular").val("");
    $("#txtdistrito").val("");
    $("#hddcodreceptor").val("0");
    $("#modalNuevo").modal("show");
});

$(document).on("click", ".btnactualizar", function(){
    $("#txtdni").val($(this).attr("data-receptordni"));
    $("#txtnombre").val($(this).attr("data-receptornombre"));
    $("#txtapellido").val($(this).attr("data-receptorapellido"));
    $("#txtcelular").val($(this).attr("data-receptorcelular"));
    $("#txtdistrito").val($(this).attr("data-receptordistrito"))
    $("#hddcodreceptor").val($(this).attr("data-receptorid"));
    $("#modalNuevo").modal("show");
});

$(document).on("click", "#btnguardar", function(){
    if(validarCampos()){
        $.ajax({
            type: "POST",
            url: "/receptor/guardar",
            contentType: "application/json",
            data: JSON.stringify({
                idreceptor: $("#hddcodreceptor").val(),
                dnireceptor: $("#txtdni").val(),
                nombrereceptor: $("#txtnombre").val(),
                apellidoreceptor: $("#txtapellido").val(),
                celularreceptor: $("#txtcelular").val(),
                distritoreceptor: $("#txtdistrito").val()
            }),
            success: function(resultado){
                if(resultado.respuesta){
                    listarReceptores();
                    Swal.fire({
                        icon: 'success',
                        title: 'Éxito',
                        text: resultado.mensaje,
                        confirmButtonText: 'Aceptar'
                    }).then((result) => {
                        location.reload();
                    });
                } else {
                    Swal.fire({
                        icon: 'error',
                        title: 'Error',
                        text: resultado.mensaje,
                        confirmButtonText: 'Aceptar'
                    });
                }
                $("#modalNuevo").modal("hide");
            }
        });
    }
});

function validarCampos() {
    $(".error-message").remove();
    var isValid = true;

    if ($("#txtdni").val() === "") {
            $("#txtdni").after('<div class="error-message text-warning">El DNI del Receptor es obligatorio</div>');
            isValid = false;
    }

    if ($("#txtnombre").val() === "") {
           $("#txtnombre").after('<div class="error-message text-warning">El Nombre del Receptor es obligatorio</div>');
           isValid = false;
    }

    if ($("#txtapellido").val() === "") {
        $("#txtapellido").after('<div class="error-message text-warning">El Apellido del Receptor es obligatorio</div>');
        isValid = false;
    }
    if ($("#txtcelular").val() === "") {
        $("#txtcelular").after('<div class="error-message text-warning">El Celular del Receptor es obligatorio</div>');
        isValid = false;
    }
    if ($("#txtdistrito").val() === "") {
        $("#txtdistrito").after('<div class="error-message text-warning">El Distrito del Receptor es obligatorio</div>');
        isValid = false;
    }
    return isValid;
}

function listarReceptores(){
    $.ajax({
        type: "GET",
        url: "/receptor/listar",
        dataType: "json",
        success: function(resultado){
            $("#tablereceptor > tbody").html("");
            $.each(resultado, function(index, value){
                $("#tablereceptor > tbody").append("<tr>"+
                    "<td>"+value.dnireceptor+"</td>"+
                    "<td>"+value.nombrereceptor+"</td>"+
                    "<td>"+value.apellidoreceptor+"</td>"+
                    "<td>"+value.celularreceptor+"</td>"+
                    "<td>"+value.distritoreceptor+"</td>"+
                    "<td>"+
                        "<button type='button' class='btn btn-dark btnactualizar'"+
                        "data-receptordni='" + value.dnireceptor+"'"+
                        "data-receptornombre=' " + value.nombrereceptor+"'"+
                        "data-receptorapellido='"+value.apellidoreceptor+"'"+
                        "data-receptorcelular='"+value.celularreceptor+"'"+
                        "data-receptordistrito='"+value.distritoreceptor+"'"+
                        "><i class='fas fa-edit'></i></button></td>"+
                        "<td>"+
                        "<button type='button' class='btn btn-danger btneliminarreceptor'"+
                        "data-receptorid='"+value.idreceptor+"'"+
                        "><i class='fas fa-trash-alt'></i></button>"+
                        "</td></tr>");
            });
        }
    });
}

$(document).on("click", "#btneliminarreceptor", function(){
    $.ajax({
        type: "DELETE",
        contentType: 'application/json',
        url: "/receptor/eliminarReceptor",
        data: JSON.stringify({
            idreceptor: $("#hddideliminarreceptor").val()
        }),
        success: function(resultado){
            Swal.fire({
                icon: 'success',
                title: 'Éxito',
                text: resultado.mensaje,
                confirmButtonText: 'Aceptar'
            }).then((result) => {
                listarReceptores();
                location.reload();
            });
        },
        error: function(xhr, status, error) {
            console.error("Error al eliminar receptor:", error);
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'Error al eliminar receptor',
                confirmButtonText: 'Aceptar'
            });
        }
    });
    $("#modalEliminarReceptor").modal("hide");
});

$(document).on("click", ".btneliminarreceptor", function(){
    $("#hddideliminarreceptor").val($(this).attr("data-receptorid"));
    $("#mensajeeliminarreceptor").text("¿Seguro de eliminar el receptor " + $(this).attr("data-receptorid") + "?");
    $("#modalEliminarReceptor").modal("show");
});