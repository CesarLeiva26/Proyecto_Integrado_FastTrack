package pe.edu.cibertec.Proyecto_Integrador_FastTrack.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.Proyecto_Integrador_FastTrack.Model.bd.Locales;
import pe.edu.cibertec.Proyecto_Integrador_FastTrack.Model.request.LocalRequest;
import pe.edu.cibertec.Proyecto_Integrador_FastTrack.Model.response.ResultadoResponse;
import pe.edu.cibertec.Proyecto_Integrador_FastTrack.Repository.LocalesRepository;
import pe.edu.cibertec.Proyecto_Integrador_FastTrack.Repository.LocalesRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class LocalesService {

    public LocalesRepository localesRepository;

    public List<Locales> listarLocales(){
        return localesRepository.findAll();
    }

    public List<Locales> buscarPorLetra(String letra) {
        return localesRepository.findByNombrelocalStartingWith(letra);
    }

    public void eliminarLocales(Integer idlocal){
         localesRepository.deleteById(idlocal);
    }

    public ResultadoResponse guardarLocales(LocalRequest local) {
        String mensaje = "Local registrado correctamente";
        Boolean respuesta = true;
        try {
            Locales objLocal = new Locales();
            if (local.getIdlocal() != null && local.getIdlocal() > 0) {
                objLocal.setIdlocal(local.getIdlocal());
            }
            objLocal.setNombrelocal(local.getNombrelocal());
            objLocal.setDireccionlocal(local.getDireccionlocal());
            objLocal.setCiudadlocal(local.getCiudadlocal());
            localesRepository.save(objLocal);
        } catch (Exception e) {
            mensaje = "Usuario no registrado";
            respuesta = false;
        }
        return ResultadoResponse.builder().mensaje(mensaje).respuesta(respuesta).build();
    }

}
