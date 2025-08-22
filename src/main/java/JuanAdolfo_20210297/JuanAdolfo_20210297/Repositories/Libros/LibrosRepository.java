package JuanAdolfo_20210297.JuanAdolfo_20210297.Repositories.Libros;

import JuanAdolfo_20210297.JuanAdolfo_20210297.Entities.Libros.LibrosEntity;
import JuanAdolfo_20210297.JuanAdolfo_20210297.Models.DTO.Libros.LibrosDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibrosRepository extends JpaRepository<LibrosEntity, Long> {
}
