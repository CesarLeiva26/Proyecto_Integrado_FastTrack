$(document).on("click", "#btnagregaremisor", function(){
    $("#txtdni").val("");
    $("#txtnombre").val("");
    $("#txtapellido").val("");
    $("#txtcelular").val("");
    $("#txtdistrito").val("");
    $("#hddcodemisor").val("0");
    $("#modalNuevo").modal("show");
});

$(document).on("click", ".btnactualizaremisor", function(){
    $("#txtdni").val($(this).attr("data-emisordni"));
    $("#txtnombre").val($(this).attr("data-emisornombre"));
    $("#txtapellido").val($(this).attr("data-emisorapellido"));
    $("#txtcelular").val($(this).attr("data-emisorcelular"));
    $("#txtdistrito").val($(this).attr("data-emisordistrito"));
    $("#hddcodemisor").val($(this).attr("data-emisorid"));
    $("#modalNuevo").modal("show");
});

$(document).on("click", "#btnguardar", function(){
    if(validarCampos()){
        $.ajax({
            type: "POST",
            url: "/emisor/guardar",
            contentType: "application/json",
            data: JSON.stringify({
                idemisor: $("#hddcodemisor").val(),
                dniemisor: $("#txtdni").val(),
                nombreemisor: $("#txtnombre").val(),
                apellidoemisor: $("#txtapellido").val(),
                celularemisor: $("#txtcelular").val(),
                distritoemisor: $("#txtdistrito").val()
            }),
            success: function(resultado){
                if(resultado.respuesta){
                    listarEmisores();
                    Swal.fire({
                        icon: 'success',
                        title: 'Éxito',
                        text: resultado.mensaje,
                        confirmButtonColor: '#000000',
                        confirmButtonText: 'Aceptar'
                    }).then((result) => {
                        location.reload();
                    });
                } else {
                    Swal.fire({
                        icon: 'error',
                        title: 'Error',
                        text: resultado.mensaje,
                        confirmButtonColor: '#000000',
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
            $("#txtdni").after('<div class="error-message text-warning">El DNI del Emisor es obligatorio   <br> Longitud máxima de 8 caracteres  </div>');
            isValid = false;
    }

    if ($("#txtnombre").val() === "") {
           $("#txtnombre").after('<div class="error-message text-warning">El Nombre del Emisor es obligatorio   <br> Longitud máxima de 30 caracteres  </div>');
           isValid = false;
    }

    if ($("#txtapellido").val() === "") {
        $("#txtapellido").after('<div class="error-message text-warning">El Apellido del Emisor es obligatorio   <br> Longitud máxima de 30 caracteres  </div>');
        isValid = false;
    }
    if ($("#txtcelular").val() === "") {
        $("#txtcelular").after('<div class="error-message text-warning">El Celular del Emisor es obligatorio   <br> Longitud máxima de 30 caracteres  </div>');
        isValid = false;
    }
    if ($("#txtdistrito").val() === "") {
        $("#txtdistrito").after('<div class="error-message text-warning">El Distrito del Emisor es obligatorio <br> Longitud máxima de 30 caracteres </div>');
        isValid = false;
    }
    return isValid;
}

function listarEmisores(){
    $.ajax({
        type: "GET",
        url: "/emisor/listar",
        dataType: "json",
        success: function(resultado){
            $("#tablaemisor > tbody").html("");
            $.each(resultado, function(index, value){
                $("#tablaemisor > tbody").append("<tr>"+
                    "<td>"+value.dniemisor+"</td>"+
                    "<td>"+value.nombreemisor+"</td>"+
                    "<td>"+value.apellidoemisor+"</td>"+
                    "<td>"+value.celularemisor+"</td>"+
                    "<td>"+value.distritoemisor+"</td>"+
                    "<td>"+
                        "<button type='button' class='btn btn-dark btnactualizaremisor'"+
                        "data-emisordni='" + value.dniemisor+"'"+
                        "data-emisornombre=' " + value.nombreemisor+"'"+
                        "data-emisorapellido='" +value.apellidoemisor+"'"+
                        "data-emisorcelular='" +value.celularemisor+"'"+
                        "data-emisordistrito='" +value.distritoemisor+"'"+
                        "><i class='fas fa-edit'></i></button></td>"+
                        "<td>"+
                        "<button type='button' class='btn btn-danger btneliminaremisor'"+
                        "data-emisorid='"+value.idemisor+"'"+
                        "><i class='fas fa-trash-alt'></i></button>"+
                        "</td></tr>");
            });
        }
    });
}

$(document).on("click", "#btneliminaremisor", function(){
    $.ajax({
        type: "DELETE",
        contentType: 'application/json',
        url: "/emisor/eliminarEmisor",
        data: JSON.stringify({
            idemisor: $("#hddeliminaremisor").val()
        }),
        success: function(resultado){
            Swal.fire({
                icon: 'success',
                title: 'Éxito',
                text: resultado.mensaje,
                confirmButtonText: 'Aceptar'
            }).then((result) => {
                listarEmisores();
                location.reload();
            });
        },
        error: function(xhr, status, error) {
            console.error("Error al eliminar emisor:", error);
            Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'Error al eliminar emisor',
                confirmButtonText: 'Aceptar'
            });
        }
    });
    $("#modalEliminarEmisor").modal("hide");
});

$(document).on("click", ".btneliminaremisor", function(){
    $("#hddeliminaremisor").val($(this).attr("data-emisorid"));
    $("#mensajeeliminaremisor").text("¿Seguro de eliminar el emisor " + $(this).attr("data-emisorid") + "?");
    $("#modalEliminarEmisor").modal("show");
});