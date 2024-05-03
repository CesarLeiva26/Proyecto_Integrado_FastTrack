package pe.edu.cibertec.Proyecto_Integrador_FastTrack.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.Proyecto_Integrador_FastTrack.Model.bd.Emisor;
import pe.edu.cibertec.Proyecto_Integrador_FastTrack.Model.request.EmisorRequest;
import pe.edu.cibertec.Proyecto_Integrador_FastTrack.Model.response.ResultadoResponse;
import pe.edu.cibertec.Proyecto_Integrador_FastTrack.Repository.EmisorRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class EmisorService {

    private final EmisorRepository emisorRepository;

    public List<Emisor> buscarPorLetra(String letra) {
        return emisorRepository.findByNombreemisorStartingWith(letra);
    }

    public List<Emisor> listarEmisores() {
        return emisorRepository.findAll();
    }

    public ResultadoResponse guardarEmisor(EmisorRequest emisorRequest) {
        String mensaje = "Emisor registrado correctamente";
        Boolean respuesta = true;
        try {
            Emisor emisorExistente = emisorRepository.findById(emisorRequest.getIdemisor()).orElse(null);

            if (emisorExistente != null) {
                emisorExistente.setDniemisor(emisorRequest.getDniemisor());
                emisorExistente.setNombreemisor(emisorRequest.getNombreemisor());
                emisorExistente.setApellidoemisor(emisorRequest.getApellidoemisor());
                emisorRepository.save(emisorExistente);
            } else {
                Emisor nuevoEmisor = new Emisor();
                nuevoEmisor.setDniemisor(emisorRequest.getDniemisor());
                nuevoEmisor.setNombreemisor(emisorRequest.getNombreemisor());
                nuevoEmisor.setApellidoemisor(emisorRequest.getApellidoemisor());
                emisorRepository.save(nuevoEmisor);
            }
        } catch (Exception e) {
            mensaje = "Emisor no registrado";
            respuesta = false;
        }
        return ResultadoResponse.builder().mensaje(mensaje).respuesta(respuesta).build();
    }

    public void eliminarEmisor(Integer idEmisor) {
        emisorRepository.deleteById(idEmisor);
    }
}