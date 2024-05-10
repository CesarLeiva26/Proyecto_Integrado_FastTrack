package pe.edu.cibertec.Proyecto_Integrador_FastTrack.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.cibertec.Proyecto_Integrador_FastTrack.Model.bd.*;
import pe.edu.cibertec.Proyecto_Integrador_FastTrack.Model.request.OrdenRequest;
import pe.edu.cibertec.Proyecto_Integrador_FastTrack.Repository.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrdenService {

    private OrdenRepository ordenRepository;
    private EmisorRepository emisorRepository;
    private LocalesRepository localRepository;
    private TipoEstadoOrdenRepository tipoEstadoOrdenRepository;
    private VehiculoRepository vehiculoRepository;
    private ReceptorRepository receptorRepository;

    @Transactional
    public Boolean guardarOrden(OrdenRequest ordenRequest) {
        try {
            Locales locales = localRepository.findById(ordenRequest.getIdLocal())
                    .orElseThrow(() -> new RuntimeException("Local no encontrado"));

            Emisor emisor = emisorRepository.findById(ordenRequest.getIdEmisor())
                    .orElseThrow(() -> new RuntimeException("Emisor no encontrado"));

            TipoEstadoOrden tipoEstadoOrden = tipoEstadoOrdenRepository.findById(ordenRequest.getIdTipoEstadoOrden())
                    .orElseThrow(() -> new RuntimeException("Tipo de estado de orden no encontrado"));

            Receptor receptor = receptorRepository.findById(ordenRequest.getIdReceptor())
                    .orElseThrow(() -> new RuntimeException("Receptor no encontrado"));

            Vehiculo vehiculo = vehiculoRepository.findById(ordenRequest.getIdVehiculo())
                    .orElseThrow(() -> new RuntimeException("Vehículo no encontrado"));
            Orden orden = new Orden();
            orden.setLocal(locales);
            orden.setEmisor(emisor);
            orden.setFechaorden(new Timestamp(System.currentTimeMillis()));
            orden.setKilos(ordenRequest.getKilos());
            orden.setPrecioporkilo(ordenRequest.getPrecioporkilo());
            orden.setTipoEstadoOrden(tipoEstadoOrden);
            orden.setReceptor(receptor);
            orden.setVehiculo(vehiculo);
            orden.setClaveorden(ordenRequest.getClaveorden());
            Orden nuevaOrden = ordenRepository.save(orden);
            return nuevaOrden.getIdorden() != null && nuevaOrden.getIdorden() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al guardar la orden");
        }
    }

    public List<Orden> listarOrdenes() {
        return ordenRepository.findAll();
    }

    public List<Orden> listarOrdenes(Integer idOrden) {
        if (idOrden != null) {
            Optional<Orden> ordenOptional = ordenRepository.findById(idOrden);
            return ordenOptional.map(List::of).orElse(List.of());
        } else {
            return ordenRepository.findAll();
        }
    }
}
