package pe.edu.cibertec.Proyecto_Integrador_FastTrack.Model.request;

import lombok.Data;

@Data
public class OrdenRequest {

    private Integer idOrden;
    private Integer idCliente;
    private String quienRecepciona;
    private String track;
    private Integer idVehiculo;
    private Integer idTipoEstadoOrden;
}