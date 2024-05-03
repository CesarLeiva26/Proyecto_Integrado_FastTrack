package pe.edu.cibertec.Proyecto_Integrador_FastTrack.Controller.frontoffice;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.edu.cibertec.Proyecto_Integrador_FastTrack.Model.bd.Usuario;
import pe.edu.cibertec.Proyecto_Integrador_FastTrack.Service.UsuarioService;

@AllArgsConstructor
@Controller
@RequestMapping("/auth")
public class LoginController {

    private final UsuarioService usuarioService;

    @GetMapping("/login")
    public String login() {
        return "frontoffice/auth/frmLogin";
    }

    @GetMapping("/registrar")
    public String registrar() {
        return "frontoffice/auth/frmRegistroUsuario";
    }

    @PostMapping("/guardarUsuario")
    public String guardarUsuario(@ModelAttribute Usuario usuario) {
        usuarioService.saveUser(usuario);
        return "frontoffice/auth/frmLogin";
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        new SecurityContextLogoutHandler().logout(request, response, authentication);
        return "redirect:/auth/login?logout";
    }
}