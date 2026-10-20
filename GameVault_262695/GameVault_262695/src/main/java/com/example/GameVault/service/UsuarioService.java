package com.example.GameVault.service;

import com.example.GameVault.models.Usuario;
import com.example.GameVault.repository.UsuarioRepository;

import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    public Usuario obtenerUsuarioLogeado(){
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(1L);
        if(usuarioOpt.isPresent()){
            return usuarioOpt.get();
        }else{
            Usuario usuario = new Usuario();
            usuario.setNombre("Itson");
            usuario.setEmail("itson@potros.itson.edu.mx");
            usuario.setContrasenia("1234");

            return usuarioRepository.save(usuario);
        }
    }
}