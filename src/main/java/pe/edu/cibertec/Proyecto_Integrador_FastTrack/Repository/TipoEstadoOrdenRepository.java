package pe.edu.cibertec.Proyecto_Integrador_FastTrack.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.cibertec.Proyecto_Integrador_FastTrack.Model.bd.TipoEstadoOrden;

@Repository
public interface TipoEstadoOrdenRepository extends JpaRepository<TipoEstadoOrden, Integer> {
}
