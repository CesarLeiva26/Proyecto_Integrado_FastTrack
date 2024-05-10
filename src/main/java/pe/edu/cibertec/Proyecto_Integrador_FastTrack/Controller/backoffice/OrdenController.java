package pe.edu.cibertec.Proyecto_Integrador_FastTrack.Controller.backoffice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.Proyecto_Integrador_FastTrack.Model.bd.Orden;
import pe.edu.cibertec.Proyecto_Integrador_FastTrack.Model.request.OrdenRequest;
import pe.edu.cibertec.Proyecto_Integrador_FastTrack.Model.response.ResultadoResponse;
import pe.edu.cibertec.Proyecto_Integrador_FastTrack.Service.OrdenService;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/orden")
public class OrdenController {

    private final OrdenService ordenService;

    @GetMapping("")
    public String listarOrden(@RequestParam(required = false) Integer idOrden, Model model) {
        List<Orden> listaOrdenes = ordenService.listarOrdenes(idOrden);
        model.addAttribute("listaOrdenes", listaOrdenes);
        return "backoffice/orden/frmOrden";
    }

    @PostMapping("/guardarorden")
    @ResponseBody
    public ResultadoResponse guardarOrden(@RequestBody OrdenRequest ordenRequest) {
        String mensaje = "";
        Boolean respuesta;
        respuesta = ordenService.guardarOrden(ordenRequest);
        mensaje = respuesta ? "Transacción Realizada" : "Transacción Falló";
        return ResultadoResponse.builder().mensaje(mensaje).respuesta(respuesta).build();
    }

}