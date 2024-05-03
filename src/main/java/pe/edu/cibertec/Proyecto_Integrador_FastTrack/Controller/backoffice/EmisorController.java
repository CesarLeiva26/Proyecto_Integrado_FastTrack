package pe.edu.cibertec.Proyecto_Integrador_FastTrack.Controller.backoffice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.Proyecto_Integrador_FastTrack.Model.bd.Emisor;
import pe.edu.cibertec.Proyecto_Integrador_FastTrack.Model.request.EmisorRequest;
import pe.edu.cibertec.Proyecto_Integrador_FastTrack.Model.response.ResultadoResponse;
import pe.edu.cibertec.Proyecto_Integrador_FastTrack.Service.EmisorService;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/emisor")
public class EmisorController {

    private EmisorService emisorService;

    @GetMapping("/buscarPorNombre")
    public String buscarPorNombre(@RequestParam(required = false) String nombre, Model model) {
        List<Emisor> emisores;
        if (nombre == null || nombre.isEmpty() || nombre.equalsIgnoreCase("todos")) {
            emisores = emisorService.listarEmisores();
        } else {
            emisores = emisorService.buscarPorLetra(nombre);
        }
        model.addAttribute("listaEmisor", emisores);
        return "backoffice/emisor/frmEmisor";
    }

    @GetMapping("")
    public String frmEmisor(Model model){
        model.addAttribute("listaEmisor", emisorService.listarEmisores());
        return "backoffice/emisor/frmEmisor";
    }

    @GetMapping("/listar")
    @ResponseBody
    public List<Emisor> listarEmisores(){
        return emisorService.listarEmisores();
    }

    @PostMapping("/guardar")
    @ResponseBody
    public ResultadoResponse guardarEmisor(@RequestBody EmisorRequest emisorRequest){
        return emisorService.guardarEmisor(emisorRequest);
    }

    @DeleteMapping("/eliminarEmisor")
    @ResponseBody
    public ResultadoResponse eliminarEmisor(@RequestBody EmisorRequest emisorRequest) {
        String mensaje = "Eliminación de Emisor Exitosa";
        Boolean respuesta = true;
        try {
            emisorService.eliminarEmisor(emisorRequest.getIdemisor());
        } catch (Exception e) {
            mensaje = "Eliminación de Emisor sin Éxito";
            respuesta = false;
        }
        return ResultadoResponse.builder().mensaje(mensaje).respuesta(respuesta).build();
    }
}