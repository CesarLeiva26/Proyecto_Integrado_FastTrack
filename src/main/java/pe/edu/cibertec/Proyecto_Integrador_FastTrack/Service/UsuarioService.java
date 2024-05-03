package pe.edu.cibertec.Proyecto_Integrador_FastTrack.Service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.Proyecto_Integrador_FastTrack.Model.bd.Rol;
import pe.edu.cibertec.Proyecto_Integrador_FastTrack.Model.bd.Usuario;
import pe.edu.cibertec.Proyecto_Integrador_FastTrack.Repository.RolRepository;
import pe.edu.cibertec.Proyecto_Integrador_FastTrack.Repository.UsuarioRepository;

import java.util.Arrays;
import java.util.HashSet;

@Service
@AllArgsConstructor
public class UsuarioService {

    private UsuarioRepository usuarioRepository;
    private RolRepository rolRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder=
            new BCryptPasswordEncoder();

    public Usuario findUserByEmail(String email){
        return usuarioRepository.findByEmail(email);
    }

    public Usuario findUserByUserName(String username){
        return usuarioRepository.findByNomusuario(username);
    }

    public Usuario saveUser(Usuario usuario){
        usuario.setPassword(bCryptPasswordEncoder.encode(
                usuario.getPassword()));
        usuario.setActivo(true);
        Rol usuarioRol = rolRepository.findByNomrol("ADMIN");
        usuario.setRoles(new HashSet<>(Arrays.asList(usuarioRol)));
        return usuarioRepository.save(usuario);
    }

    public void updatePassword(Usuario usuario, String newPassword) {
        usuario.setPassword(bCryptPasswordEncoder.encode(newPassword));
        usuarioRepository.save(usuario);
    }
}
