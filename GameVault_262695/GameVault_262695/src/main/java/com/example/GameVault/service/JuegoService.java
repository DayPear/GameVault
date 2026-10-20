package com.example.GameVault.service;

import com.example.GameVault.models.Juego;
import com.example.GameVault.repository.JuegoRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class JuegoService{
    @Autowired
    JuegoRepository juegoRepository;
    private static final String UPLOAD_DIR = "src/main/resources/static/uploads/";

    public List<Juego> obtenerTodos(){
        return juegoRepository.findAll();
    }

    public void guardarJugo(Juego juego, MultipartFile portada){
        String nombreArchivo = juego.getPortadaUrl() != null ? juego.getPortadaUrl():"default.png";

        if(!portada.isEmpty()){
            nombreArchivo = guardarImagenLocal(portada);
            //alternativa en la nube: Cloudinary o Storage de firebase
        }
        juego.setPortadaUrl(nombreArchivo);
        juegoRepository.save(juego);
    }

    public Juego obtenerJuegoPorId(Long id){
        return juegoRepository.findById(id).orElse(null);
    }

    public void eliminarJuego(Long id){
        juegoRepository.deleteById(id);
    }

    public List<Juego> obtenerJuegoPorUsuario(Long usuarioId){
        return juegoRepository.findByUsuarioId(usuarioId);
    }



    public String guardarImagenLocal(MultipartFile portada){
        try{
                Path uploadPath = Paths.get(UPLOAD_DIR);
                if(!Files.exists(uploadPath)){
                    Files.createDirectories(uploadPath);
                }

                String nombreArchivo = UUID.randomUUID().toString() + "_" + portada.getOriginalFilename();
                Path filePath = uploadPath.resolve(nombreArchivo);

                Files.copy(portada.getInputStream(), filePath);

                return nombreArchivo;
            } catch (IOException e){
                e.printStackTrace();
                return "default.png";
            }
    }

    public List<Juego> buscarPorTitulo(String palabra){
        if(palabra == null || palabra.trim().isEmpty()){
            return listarTodos();
        }
        return juegoRepository.findByTituloContainingIgnoreCase(palabra);
    }
}