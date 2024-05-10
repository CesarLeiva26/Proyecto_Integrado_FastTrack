package pe.edu.cibertec.Proyecto_Integrador_FastTrack.Controller.backoffice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pe.edu.cibertec.Proyecto_Integrador_FastTrack.Model.bd.Vehiculo;
import pe.edu.cibertec.Proyecto_Integrador_FastTrack.Service.VehiculoService;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/vehiculo")
public class VehiculoController {


    private VehiculoService vehiculoService;

    @GetMapping("/listar")
    @ResponseBody
    public List<Vehiculo> listaVehiculo(){
        return vehiculoService.listarVehiculos();
    }
}
