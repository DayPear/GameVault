package com.example.GameVault.repository;

import com.example.GameVault.models.Usuario;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    Optional<Usuario> findByEmail(String email);

}