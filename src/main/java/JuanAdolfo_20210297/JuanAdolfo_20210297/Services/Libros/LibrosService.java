package JuanAdolfo_20210297.JuanAdolfo_20210297.Services.Libros;

import JuanAdolfo_20210297.JuanAdolfo_20210297.Entities.Libros.LibrosEntity;
import JuanAdolfo_20210297.JuanAdolfo_20210297.Models.DTO.Libros.LibrosDTO;
import JuanAdolfo_20210297.JuanAdolfo_20210297.Repositories.Libros.LibrosRepository;
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

    public List<LibrosDTO> consultarLibros(){
        List<LibrosEntity> papuLista = repository.findAll();
        return papuLista.stream()
                .map(this::aDTO)
                .collect(Collectors.toList());
    }

    private LibrosDTO aDTO(LibrosEntity entity){
        LibrosDTO dto = new LibrosDTO();
        dto.setId(entity.getId());
        dto.setTitulo(entity.getTitulo());
        dto.setIsbn(entity.getIsbn());
        dto.setAnioPublicacion(entity.getAnioPublicacion());
        dto.setGenero(entity.getGenero());
        dto.setAutorId(entity.);
    }

}
