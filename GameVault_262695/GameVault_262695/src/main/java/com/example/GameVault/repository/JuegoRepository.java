package com.example.GameVault.repository;

import com.example.GameVault.models. Juego;
import com.example.GameVault.models.Juego;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JuegoRepository extends JpaRepository<Juego,Long>{
    //Metodos gratis como insert(save), update
    List<Juego> findByTitleContainingIgnoreCase(String titulo);
    List<Juego> findByPrecioLessThan(Double precio);
    List<Juego> findByUsuarioId(Long usuarioId);

    @Query("Select j from Juegos j order by i.id desc")
    List<Juego> obtenerJuegosRecientes();

    @Query("select j from Juegos j where lower(j.descripcion) like lower(concat('%', :palabra, '%'))")
    List<Juego> buscarPorDescripcion(String palabra);
}