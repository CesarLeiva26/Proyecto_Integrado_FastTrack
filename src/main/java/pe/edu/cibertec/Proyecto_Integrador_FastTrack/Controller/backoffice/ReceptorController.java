package pe.edu.cibertec.Proyecto_Integrador_FastTrack.Controller.backoffice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.Proyecto_Integrador_FastTrack.Model.bd.Receptor;
import pe.edu.cibertec.Proyecto_Integrador_FastTrack.Model.request.ReceptorRequest;
import pe.edu.cibertec.Proyecto_Integrador_FastTrack.Model.response.ResultadoResponse;
import pe.edu.cibertec.Proyecto_Integrador_FastTrack.Service.ReceptorService;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/receptor")
public class ReceptorController {

    private ReceptorService receptorService;

    @GetMapping("/buscarPorNombre")
    public String buscarPorNombre(@RequestParam(required = false) String nombre, Model model) {
        List<Receptor> receptores;
        if (nombre == null || nombre.isEmpty() || nombre.equalsIgnoreCase("todos")) {
            receptores = receptorService.listarReceptores();
        } else {
            receptores = receptorService.buscarPorLetra(nombre);
        }
        model.addAttribute("listaReceptor", receptores);
        return "backoffice/receptor/frmReceptor";
    }

    @GetMapping("")
    public String frmReceptor(Model model){
        model.addAttribute("listaReceptor", receptorService.listarReceptores());
        return "backoffice/receptor/frmReceptor";
    }

    @GetMapping("/listar")
    @ResponseBody
    public List<Receptor> listarReceptores(){
        return receptorService.listarReceptores();
    }

    @PostMapping("/guardar")
    @ResponseBody
    public ResultadoResponse guardarReceptor(@RequestBody ReceptorRequest receptorRequest){
        return receptorService.guardarReceptor(receptorRequest);
    }

    @DeleteMapping("/eliminarReceptor")
    @ResponseBody
    public ResultadoResponse eliminarReceptor(@RequestBody ReceptorRequest receptorRequest) {
        String mensaje = "Eliminación de Receptor Exitosa";
        Boolean respuesta = true;
        try {
            receptorService.eliminarReceptor(receptorRequest.getIdreceptor());
        } catch (Exception e) {
            mensaje = "Eliminación de Receptor sin Éxito";
            respuesta = false;
        }
        return ResultadoResponse.builder().mensaje(mensaje).respuesta(respuesta).build();
    }
}
