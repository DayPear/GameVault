import com.example.GameVault.model. Juego;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JuegoRepository extends JpaRepository<Juego,Long>{
    //Metodos gratis como insert(save), update
    List<Juego> findByTitleContainingIgnoreCase(String titulo);
    List<Juego> findByPrecioLessThan(Double precio);
    
    @Query("Select j from Juegos j order by i.id desc")
    List<Juego> obtenerJuegosRecientes();

    @Query("select j from Juegos j where lower(j.descripcion) like lower(concat('%', :palabra, '%'))")
    List<Juego> buscarPorDescripcion(String palabra);
}