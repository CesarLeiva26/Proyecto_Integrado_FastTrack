package pe.edu.cibertec.Proyecto_Integrador_FastTrack.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.Proyecto_Integrador_FastTrack.Model.bd.TipoEstadoOrden;
import pe.edu.cibertec.Proyecto_Integrador_FastTrack.Repository.TipoEstadoOrdenRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class TipoEstadoOrdenService {

    private TipoEstadoOrdenRepository tipoEstadoOrdenRepository;

    public List<TipoEstadoOrden> listarTiposEstadoOrden() {
        return tipoEstadoOrdenRepository.findAll();
    }
}
