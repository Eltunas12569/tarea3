package mx.ipn.escom.Recomendaciones.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import mx.ipn.escom.Recomendaciones.auth.entity.Rol;
import mx.ipn.escom.Recomendaciones.auth.entity.Usuario;
import mx.ipn.escom.Recomendaciones.auth.repository.RolRepository;
import mx.ipn.escom.Recomendaciones.auth.repository.UsuarioRepository;

@Controller
public class AdminController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @GetMapping("/admin")
    public String adminPage(Authentication authentication) {
        System.out.println("Usuario autenticado: " + authentication.getName());
        System.out.println("Roles del usuario: " + authentication.getAuthorities());

        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            System.out.println("Acceso concedido: Usuario es admin");
            return "admin";  // Vista para administrador
        } else {
            System.out.println("Acceso denegado: Usuario no es admin");
            return "accessDenied";  // Vista si no es admin
        }
    }

    @PostMapping("/admin/changeRole")
    public String changeUserRole(@RequestParam Long usuarioId, @RequestParam String nuevoRol) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);
        if (usuario != null) {
            Rol rol = rolRepository.findByNombre(nuevoRol).orElse(null);
            if (rol != null) {
                usuario.getRoles().clear();
                usuario.getRoles().add(rol);
                usuarioRepository.save(usuario);
            }
        }
        return "redirect:/admin";
    }
}