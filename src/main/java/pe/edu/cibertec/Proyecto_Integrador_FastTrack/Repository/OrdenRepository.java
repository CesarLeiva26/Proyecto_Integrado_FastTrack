package pe.edu.cibertec.Proyecto_Integrador_FastTrack.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.cibertec.Proyecto_Integrador_FastTrack.Model.bd.Orden;

import java.util.List;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, Integer> {
    List<Orden> findByIdordenStartingWith(Integer idorden);

}
