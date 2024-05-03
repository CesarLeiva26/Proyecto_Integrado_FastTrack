package pe.edu.cibertec.Proyecto_Integrador_FastTrack.Configuration;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import pe.edu.cibertec.Proyecto_Integrador_FastTrack.Service.DetalleUsuarioService;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private final DetalleUsuarioService detalleUsuarioService;

    @Bean
    public SecurityFilterChain config (HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .authorizeHttpRequests(
                        auth ->
                                auth.requestMatchers("/auth/login",
                                                "/auth/registrar",
                                                "/auth/guardarUsuario",
                                                "/resources/**",
                                                "/static/**",
                                                "/styles/**",
                                                "/scripts/**")
                                        .permitAll()
                                        .anyRequest()
                                        .authenticated()
                ) .formLogin(formLogin ->
                        formLogin
                                .loginPage("/auth/login")
                                .defaultSuccessUrl("/home")
                                .usernameParameter("nomusuario")
                                .passwordParameter("password")
                )
                                .authenticationProvider(authenticationProvider())
                                .logout()
                                .logoutUrl("/auth/logout")
                                .logoutSuccessUrl("/auth/login")
                                .invalidateHttpSession(true)
                                .deleteCookies("JSESSIONID");

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(detalleUsuarioService);
        authenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return authenticationProvider;
    }

}
