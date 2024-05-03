package pe.edu.cibertec.Proyecto_Integrador_FastTrack.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.Proyecto_Integrador_FastTrack.Model.bd.Receptor;
import pe.edu.cibertec.Proyecto_Integrador_FastTrack.Model.request.ReceptorRequest;
import pe.edu.cibertec.Proyecto_Integrador_FastTrack.Model.response.ResultadoResponse;
import pe.edu.cibertec.Proyecto_Integrador_FastTrack.Repository.ReceptorRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class ReceptorService {

    private final ReceptorRepository receptorRepository;

    public List<Receptor> buscarPorLetra(String letra) {
        return receptorRepository.findByNombrereceptorStartingWith(letra);
    }

    public List<Receptor> listarReceptores() {
        return receptorRepository.findAll();
    }

    public ResultadoResponse guardarReceptor(ReceptorRequest receptorRequest) {
        String mensaje = "Receptor registrado correctamente";
        Boolean respuesta = true;
        try {
            Receptor receptorExistente = receptorRepository.findById(receptorRequest.getIdreceptor()).orElse(null);

            if (receptorExistente != null) {
                receptorExistente.setDnireceptor(receptorRequest.getDnireceptor());
                receptorExistente.setNombrereceptor(receptorRequest.getNombrereceptor());
                receptorExistente.setApellidoreceptor(receptorRequest.getApellidoreceptor());
                receptorRepository.save(receptorExistente);
            } else {
                Receptor nuevoReceptor = new Receptor();
                nuevoReceptor.setDnireceptor(receptorRequest.getDnireceptor());
                nuevoReceptor.setNombrereceptor(receptorRequest.getNombrereceptor());
                nuevoReceptor.setApellidoreceptor(receptorRequest.getApellidoreceptor());
                receptorRepository.save(nuevoReceptor);
            }
        } catch (Exception e) {
            mensaje = "Receptor no registrado";
            respuesta = false;
        }
        return ResultadoResponse.builder().mensaje(mensaje).respuesta(respuesta).build();
    }

    public void eliminarReceptor(Integer idReceptor) {
        receptorRepository.deleteById(idReceptor);
    }
}