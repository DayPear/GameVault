package com.example.GameVault.models;

import com.example.GameVault.models.Juego;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String contrasenia;

    @OneToMany(mappedBy="usuario", cascade = CascadeType.All)
    List<Juego> juegos;
}