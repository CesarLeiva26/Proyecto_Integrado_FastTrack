package pe.edu.cibertec.Proyecto_Integrador_FastTrack.Controller.backoffice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.Proyecto_Integrador_FastTrack.Model.bd.Locales;
import pe.edu.cibertec.Proyecto_Integrador_FastTrack.Model.request.LocalRequest;
import pe.edu.cibertec.Proyecto_Integrador_FastTrack.Model.response.ResultadoResponse;
import pe.edu.cibertec.Proyecto_Integrador_FastTrack.Service.LocalesService;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/locales")
public class LocalesController {

    private LocalesService localesService;

    @GetMapping("")
    public String frmLocales(Model model){
        model.addAttribute("listaLocales", localesService.listarLocales());
        return "backoffice/locales/frmLocales";
    }

    @GetMapping("/listar")
    @ResponseBody
    public List<Locales> listarLocales(){
        return localesService.listarLocales();
    }


    @GetMapping("/buscarPorLetra")
    public String buscarPorLetra(@RequestParam(required = false) String letra, Model model) {
        List<Locales> locales;
        if (letra == null || letra.isEmpty() || letra.equalsIgnoreCase("todos")) {
            locales = localesService.listarLocales();
        } else {
            locales = localesService.buscarPorLetra(letra);
        }
        model.addAttribute("listaLocales", locales);
        return "backoffice/locales/frmLocales";
    }
    @PostMapping("/guardar")
    @ResponseBody
    public ResultadoResponse guardarLocales(@RequestBody LocalRequest localRequest) {
        return localesService.guardarLocales(localRequest);
    }

    @DeleteMapping("/eliminarLocal")
    @ResponseBody
    public ResultadoResponse eliminarLocal(@RequestBody LocalRequest localRequest) {
        String mensaje = "Eliminación de Locales Exitoso";
        Boolean respuesta = true;
        try {
            localesService.eliminarLocales(localRequest.getIdlocal());
        } catch (Exception e) {
            mensaje = "Eliminación de Locales sin Éxito";
            respuesta = false;
        }
        return ResultadoResponse.builder().mensaje(mensaje).respuesta(respuesta).build();
    }
}
