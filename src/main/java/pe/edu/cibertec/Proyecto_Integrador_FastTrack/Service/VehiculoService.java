package pe.edu.cibertec.Proyecto_Integrador_FastTrack.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.Proyecto_Integrador_FastTrack.Model.bd.Vehiculo;
import pe.edu.cibertec.Proyecto_Integrador_FastTrack.Repository.VehiculoRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class VehiculoService {

    private VehiculoRepository vehiculoRepository;
    public List<Vehiculo> listarVehiculos() {
        return vehiculoRepository.findAll();
    }
}
