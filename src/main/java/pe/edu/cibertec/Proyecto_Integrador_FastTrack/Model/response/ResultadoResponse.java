package pe.edu.cibertec.Proyecto_Integrador_FastTrack.Model.response;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResultadoResponse {
    private String mensaje;
    private Boolean respuesta;
}
