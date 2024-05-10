package pe.edu.cibertec.Proyecto_Integrador_FastTrack.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.cibertec.Proyecto_Integrador_FastTrack.Model.bd.Vehiculo;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, Integer> {
}
