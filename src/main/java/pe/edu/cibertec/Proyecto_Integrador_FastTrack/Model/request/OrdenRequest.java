package pe.edu.cibertec.Proyecto_Integrador_FastTrack.Model.request;

import lombok.Data;

import java.util.Date;

@Data
public class OrdenRequest {
    private Integer idOrden;
    private Integer idLocal;
    private Integer idEmisor;
    private Date fechaOrden;
    private Double kilos;
    private Double precioporkilo;
    private Integer idTipoEstadoOrden;
    private Integer idReceptor;
    private Integer idVehiculo;
    private String claveorden;
}
