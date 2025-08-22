package JuanAdolfo_20210297.JuanAdolfo_20210297.Services.Libros;

import JuanAdolfo_20210297.JuanAdolfo_20210297.Entities.Libros.LibrosEntity;
import JuanAdolfo_20210297.JuanAdolfo_20210297.Exceptions.Libros.LibroNoEncontradoException;
import JuanAdolfo_20210297.JuanAdolfo_20210297.Exceptions.Libros.LibroNoRegistradoException;
import JuanAdolfo_20210297.JuanAdolfo_20210297.Models.DTO.Libros.LibrosDTO;
import JuanAdolfo_20210297.JuanAdolfo_20210297.Repositories.Libros.LibrosRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class LibrosService {

    @Autowired
    LibrosRepository repository;

    public List<LibrosDTO> consultarLibros() {
        List<LibrosEntity> papuLista = repository.findAll();
        return papuLista.stream()
                .map(this::aDTO)
                .collect(Collectors.toList());
    }

    private LibrosDTO aDTO(LibrosEntity entity) {
        LibrosDTO dto = new LibrosDTO();
        dto.setId(entity.getId());
        dto.setTitulo(entity.getTitulo());
        dto.setIsbn(entity.getIsbn());
        dto.setAnioPublicacion(entity.getAnioPublicacion());
        dto.setGenero(entity.getGenero());
        dto.setAutorId(entity.getAutorId());
        return dto;
    }

    private LibrosEntity aEntity(
            @Valid LibrosDTO dto
    ) {
        LibrosEntity entity = new LibrosEntity();
        entity.setTitulo(dto.getTitulo());
        entity.setIsbn(dto.getIsbn());
        entity.setAnioPublicacion(dto.getAnioPublicacion());
        entity.setGenero(dto.getGenero());
        entity.setAutorId(dto.getAutorId());
        return entity;
    }

    public LibrosDTO registrarLibro(
            @Valid LibrosDTO dto
    ) {
        if (dto == null || dto.getTitulo() == null || dto.getTitulo().isEmpty() || dto.getIsbn() == null || dto.getIsbn().isBlank()) {
            throw new IllegalArgumentException("El titulo o el ISBN no pueden estar vacios :(");
        }
        try {
            return aDTO(repository.save(aEntity(dto)));
        } catch (Exception e) {
            log.error("Error al registrar el librito: " + e.getMessage());
            throw new LibroNoRegistradoException("Error al registrar");
        }
    }

    public void erradicarLibro(Long id) {
        if (!repository.existsById(id)) {
            log.warn("El libro que intenta erradicar no existe");
            throw new LibroNoEncontradoException("Libro con el id " + id + " no encontrado");
        }
        repository.deleteById(id);
        log.info("Libro erradicado 🙊");
    }

    public LibrosDTO modificarLibro(
            Long id,
            @Valid LibrosDTO dto
    ) {
        LibrosEntity existe = repository.findById(id).orElseThrow(() -> new LibroNoEncontradoException("Libro no encontrado xd"));
        existe.setTitulo(dto.getTitulo());
        existe.setIsbn(dto.getIsbn());
        existe.setAnioPublicacion(dto.getAnioPublicacion());
        existe.setGenero(dto.getGenero());
        existe.setAutorId(dto.getAutorId());
        return aDTO(repository.save(existe));
    }

    public LibrosDTO consultarLibroPorId(Long id){
        LibrosEntity encontrado = repository.findById(id).orElseThrow(() -> new LibroNoEncontradoException("Libro no encontrado xd"));
        return aDTO(encontrado);
    }

}
