package pe.edu.cibertec.Proyecto_Integrador_FastTrack.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.cibertec.Proyecto_Integrador_FastTrack.Model.bd.Receptor;

import java.util.List;

@Repository
public interface ReceptorRepository extends JpaRepository<Receptor,Integer> {
    List<Receptor> findByNombrereceptorStartingWith(String letra);

}
