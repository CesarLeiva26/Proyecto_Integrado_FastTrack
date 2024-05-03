package pe.edu.cibertec.Proyecto_Integrador_FastTrack.Model.request;

import lombok.Data;

@Data
public class EmisorRequest {
    private Integer idemisor;
    private Integer dniemisor;
    private String nombreemisor;
    private String apellidoemisor;
    private String celularemisor;
    private String distritoemisor;
}
