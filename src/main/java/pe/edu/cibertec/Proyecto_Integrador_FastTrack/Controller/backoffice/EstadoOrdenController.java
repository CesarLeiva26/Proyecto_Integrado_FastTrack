package pe.edu.cibertec.Proyecto_Integrador_FastTrack.Controller.backoffice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pe.edu.cibertec.Proyecto_Integrador_FastTrack.Model.bd.TipoEstadoOrden;
import pe.edu.cibertec.Proyecto_Integrador_FastTrack.Service.TipoEstadoOrdenService;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/estadoorden")
public class EstadoOrdenController {

    private TipoEstadoOrdenService tipoEstadoOrdenService;

    @GetMapping("/listaEstado")
    @ResponseBody
    public List<TipoEstadoOrden> tipoEstadoOrdens(){
        return tipoEstadoOrdenService.listarTiposEstadoOrden();
    }
}
