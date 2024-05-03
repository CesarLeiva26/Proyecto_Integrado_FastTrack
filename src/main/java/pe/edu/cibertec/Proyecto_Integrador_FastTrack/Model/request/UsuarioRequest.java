package pe.edu.cibertec.Proyecto_Integrador_FastTrack.Model.request;

import lombok.Data;

@Data
public class UsuarioRequest {


    private Integer idusuario;
    private String nombreusuario;
    private String contra;
    private Integer idempleado;
}