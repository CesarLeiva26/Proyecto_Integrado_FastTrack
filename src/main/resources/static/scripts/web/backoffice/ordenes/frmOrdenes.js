function listarCboEmisores(idEmisor) {
    $.ajax({
        type: "GET",
        url: "/emisor/listar",
        dataType: "json",
        success: function (resultado) {
            $("#cboEmisor").empty();
            $.each(resultado, function (index, value) {
                $("#cboEmisor").append(
                    `<option value="${value.idemisor}">${value.nombreemisor}</option>`
                );
            });
            if (idEmisor > 0) {
                $("#cboEmisor").val(idEmisor);
            }
        }
    });
}

function listarCboLocales(idLocal) {
    $.ajax({
        type: "GET",
        url: "/locales/listar",
        dataType: "json",
        success: function (resultado) {
            $("#cboLocal").empty();
            $.each(resultado, function (index, value) {
                $("#cboLocal").append(
                    `<option value="${value.idlocal}">${value.ciudadlocal}</option>`
                );
            });
            if (idLocal > 0) {
                $("#cboLocal").val(idLocal);
            }
        }
    });
}

function listarCboReceptores(idReceptor) {
    $.ajax({
        type: "GET",
        url: "/receptor/listar",
        dataType: "json",
        success: function (resultado) {
            $("#cboReceptor").empty();
            $.each(resultado, function (index, value) {
                $("#cboReceptor").append(
                    `<option value="${value.idreceptor}">${value.nombrereceptor}</option>`
                );
            });
            if (idReceptor > 0) {
                $("#cboReceptor").val(idReceptor);
            }
        }
    });
}

function listarCboVehiculos(idVehiculo) {
    $.ajax({
        type: "GET",
        url: "/vehiculo/listar",
        dataType: "json",
        success: function (resultado) {
            $("#cboVehiculo").empty();
            $.each(resultado, function (index, value) {
                $("#cboVehiculo").append(
                    `<option value="${value.idvehiculo}">${value.placa}</option>`
                );
            });
            if (idVehiculo > 0) {
                $("#cboVehiculo").val(idVehiculo);
            }
        }
    });
}

function listarCboEstadosOrden(idEstadoOrden) {
    $.ajax({
        type: "GET",
        url: "/estadoorden/listaEstado",
        dataType: "json",
        success: function (resultado) {
            $("#cboEstadoOrden").empty();
            $.each(resultado, function (index, value) {
                $("#cboEstadoOrden").append(
                    `<option value="${value.idestadoorden}">${value.nombreestado}</option>`
                );
            });
            if (idEstadoOrden > 0) {
                $("#cboEstadoOrden").val(idEstadoOrden);
            }
        }
    });
}

$(document).on("click", "#btnagregarorden", function () {
    $("#cboLocal").empty();
    listarCboLocales(0);
    $("#cboEmisor").empty();
    listarCboEmisores(0);
    $("#txtFechaOrden").val("");
    $("#txtKilos").val("");
    $("#txtPrecioPorKilo").val("");
    $("#cboEstadoOrden").empty();
    listarCboEstadosOrden(0);
    $("#cboReceptor").empty();
    listarCboReceptores(0);
    $("#cboVehiculo").empty();
    listarCboVehiculos(0);
    $("#txtClave").val("");
    $("#hddidorden").val("0");
    $("#modalNuevoOrden").modal("show");
});

$(document).on("click", "#btnguardarorden", function () {
    if (validarCamposOrden()) {
        $.ajax({
            type: "POST",
            url: "/orden/guardarorden",
            contentType: "application/json",
            data: JSON.stringify({
                idLocal: $("#cboLocal").val(),
                idEmisor: $("#cboEmisor").val(),
                fechaOrden: $("#txtFechaOrden").val(),
                kilos: $("#txtKilos").val(),
                precioporkilo: $("#txtPrecioPorKilo").val(),
                idTipoEstadoOrden: $("#cboEstadoOrden").val(),
                idReceptor: $("#cboReceptor").val(),
                idVehiculo: $("#cboVehiculo").val(),
                claveorden: $("#txtClave").val(),
            }),
            success: function (resultado) {
                Swal.fire({
                    icon: 'success',
                    title: 'Orden Realizado Correctamente',
                    text: resultado.mensaje,
                    confirmButtonText: 'Aceptar'
                }).then((result) => {
                    location.reload();
                });
            },
            error: function (error) {
                Swal.fire({
                    icon: 'error',
                    title: 'Error al guardar',
                    text: 'Ha ocurrido un error al intentar guardar la orden. Por favor, inténtelo de nuevo.',
                    confirmButtonText: 'Aceptar'
                });
            }
        });

        $("#modalNuevoOrden").modal("hide");
    }
});

function validarCamposOrden() {
    $(".error-message").remove();
    var isValid = true;

    if ($("#txtFechaOrden").val() === "") {
        $("#txtFechaOrden").after('<div class="error-message text-danger">La Fecha de Orden es obligatoria </div>');
        isValid = false;
    }

    if ($("#txtKilos").val() === "") {
        $("#txtKilos").after('<div class="error-message text-danger">El campo Kilos es obligatorio</div>');
        isValid = false;
    }

    if ($("#txtPrecioPorKilo").val() === "") {
        $("#txtPrecioPorKilo").after('<div class="error-message text-danger">El campo Precio por Kilo es obligatorio</div>');
        isValid = false;
    }
    if ($("#txtClave").val() === "") {
        $("#txtClave").after('<div class="error-message text-danger">El campo Clave es obligatorio</div>');
        isValid = false;
    }
    return isValid;
}