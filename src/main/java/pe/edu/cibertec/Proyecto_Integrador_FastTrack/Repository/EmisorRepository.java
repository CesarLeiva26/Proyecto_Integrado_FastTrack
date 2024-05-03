package pe.edu.cibertec.Proyecto_Integrador_FastTrack.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.cibertec.Proyecto_Integrador_FastTrack.Model.bd.Emisor;

import java.util.List;

@Repository
public interface EmisorRepository extends JpaRepository<Emisor, Integer> {
   List<Emisor> findByNombreemisorStartingWith(String letra);
}
