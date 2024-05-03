$(document).on("click", "#btnagregar", function() {
    $("#txtnombrelocal").val("");
    $("#txtdireccionlocal").val("");
    $("#txtciudadlocal").val("");
    $("#hddcodlocal").val("0");
    $("#modalNuevo").modal("show");
});

$(document).on("click", ".btnactualizar", function() {
    $("#txtnombrelocal").val($(this).attr("data-localnombre"));
    $("#txtdireccionlocal").val($(this).attr("data-localdireccion"));
    $("#txtciudadlocal").val($(this).attr("data-localciudad"));
    $("#hddcodlocal").val($(this).attr("data-localid"));
    $("#modalNuevo").modal("show");
});

$(document).on("click", "#btnguardar", function() {
    if (validarCamposLocales()) {
        $.ajax({
            type: "POST",
            url: "/locales/guardar",
            contentType: "application/json",
            data: JSON.stringify({
                idlocal: $("#hddcodlocal").val(),
                nombrelocal: $("#txtnombrelocal").val(),
                direccionlocal: $("#txtdireccionlocal").val(),
                ciudadlocal: $("#txtciudadlocal").val()
            }),
            success: function(resultado) {
                if (resultado.respuesta) {
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
                        title: 'Error al guardar',
                        text: 'Ha ocurrido un error al intentar guardar el local. Por favor, inténtelo de nuevo.',
                        confirmButtonText: 'Aceptar'
                    });
                }
            },
            error: function (xhr, status, error) {
                Swal.fire({
                    icon: 'error',
                    title: 'Error de red',
                    text: 'Ha ocurrido un error de red. Por favor, verifique su conexión e inténtelo de nuevo.',
                    confirmButtonText: 'Aceptar'
                });
            }
        });
        $("#modalNuevo").modal("hide");
    }
});

function validarCamposLocales() {
    $(".error-message").remove();
    var isValid = true;

    if ($("#txtnombrelocal").val() === "") {
        $("#txtnombrelocal").after('<div class="error-message text-warning">El Nombre del Local es obligatorio</div>');
        isValid = false;
    }

    if ($("#txtdireccionlocal").val() === "") {
        $("#txtdireccionlocal").after('<div class="error-message text-warning">La Dirección del Local es obligatoria</div>');
        isValid = false;
    }

    if ($("#txtciudadlocal").val() === "") {
        $("#txtciudadlocal").after('<div class="error-message text-warning">La Ciudad del Local es obligatoria</div>');
        isValid = false;
    }
    return isValid;
}

function listarLocales() {
    $.ajax({
        type: "GET",
        url: "/local/listar",
        dataType: "json",
        success: function(resultado) {
            $("#tbllocales > tbody").html("");
            $.each(resultado, function(index, value) {
                $("#tbllocales > tbody").append("<tr>" +
                    "<td>" + value.nombrelocal + "</td>" +
                    "<td>" + value.direccionlocal + "</td>" +
                    "<td>" + value.ciudadlocal + "</td>" +
                    "<td>" +
                    "<button type='button' class='btn btn-dark btnactualizar'" +
                    "data-localnombre='" + value.nombrelocal + "'" +
                    "data-localdireccion='" + value.direccionlocal + "'" +
                    "data-localciudad='" + value.ciudadlocal + "'" +
                    "><i class='fas fa-edit'></i></button></td>"+
                    "<td>"+
                    "<button type='button' class='btn btn-danger btneliminarlocales'"+
                    "data-localid='"+value.idlocal+"'"+
                    "><i class='fas fa-trash-alt'></i></button>"+
                    "</td></tr>");
            });
        }
    });
}

$(document).on("click", "#btneliminarlocales", function() {
    $.ajax({
        type: "DELETE",
        contentType: 'application/json',
        url: "/locales/eliminarLocal",
        data: JSON.stringify({
            idlocal: $("#hddideliminarlocales").val()
        }),
        success: function(resultado) {
            Swal.fire({
                icon: 'success',
                title: 'Eliminado exitosamente',
                text: 'El local se ha eliminado correctamente.',
                confirmButtonText: 'Aceptar'
            }).then((result) => {
                location.reload();
            });
        },
        error: function(xhr, status, error) {
            Swal.fire({
                icon: 'error',
                title: 'Error al eliminar',
                text: 'Ha ocurrido un error al intentar eliminar el local. Por favor, inténtelo de nuevo.',
                confirmButtonText: 'Aceptar'
            });
        }
    });
    $("#modalEliminarLocales").modal("hide");
});

$(document).on("click", ".btneliminarlocales", function(){
   $("#hddideliminarlocales").val($(this).attr("data-localid"));
   $("#mensajeeliminar").text("¿Seguro de eliminar el " + $(this).attr("data-localid") + "?");
   $("#modalEliminarLocales").modal("show");
});
