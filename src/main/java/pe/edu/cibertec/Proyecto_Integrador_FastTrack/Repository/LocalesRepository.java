package pe.edu.cibertec.Proyecto_Integrador_FastTrack.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.cibertec.Proyecto_Integrador_FastTrack.Model.bd.Locales;

import java.util.List;

@Repository
public interface LocalesRepository extends JpaRepository<Locales, Integer> {
    List<Locales> findByNombrelocalStartingWith(String letra);
}
