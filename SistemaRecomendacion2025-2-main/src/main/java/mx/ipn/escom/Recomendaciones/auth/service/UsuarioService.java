package mx.ipn.escom.Recomendaciones.auth.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import mx.ipn.escom.Recomendaciones.auth.entity.Usuario;
import mx.ipn.escom.Recomendaciones.auth.entity.Rol;
import mx.ipn.escom.Recomendaciones.auth.repository.UsuarioRepository;
import mx.ipn.escom.Recomendaciones.auth.repository.RolRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;  // Inyecta el codificador

    @Autowired
    private RolRepository rolRepository;

    public void registrarUsuario(Usuario usuario) {
        // Codifica la contraseña antes de guardar
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        // Guarda el usuario en la base de datos
        usuarioRepository.save(usuario);
    }

    public void cambiarRolUsuario(Long usuarioId, String nuevoRol) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);
        if (usuario != null) {
            Rol rol = rolRepository.findByNombre(nuevoRol).orElse(null);
            if (rol != null) {
                usuario.getRoles().clear();
                usuario.getRoles().add(rol);
                usuarioRepository.save(usuario);
            }
        }
    }
}