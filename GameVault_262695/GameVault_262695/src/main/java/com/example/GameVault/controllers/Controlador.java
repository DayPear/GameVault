package com.example.GameVault.controllers;

import com.example.GameVault.models.Juego;
import com.example.GameVault.models.Usuario;
import com.example.GameVault.service.JuegoService;
import com.example.GameVault.service.UsuarioService;

public class Controlador {
    @Autowired
    private JuegoService juegoService;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/juegos/eliminar/{id}")
    public String eliminarJuego(@PathVariable Long id) {
        Juego juego = juegoService.obtenerJuegoPorId(id);
        Usuario logueado = usuarioService.obtenerUsuarioLogueado();

        // Solo procedemos a borrar si el usuario logueado es el dueño
        if (juego != null && juego.getUsuario().getId().equals(logueado.getId())) {
            juegoService.eliminarJuego(id);
        }

        return "redirect:/mis-juegos";
    }

}
