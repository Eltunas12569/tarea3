package mx.ipn.escom.Recomendaciones.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PeliculasController {

    @GetMapping("/peliculas")
    public String peliculasPage() {
        return "peliculas";  // Nombre de la plantilla Thymeleaf
    }
}