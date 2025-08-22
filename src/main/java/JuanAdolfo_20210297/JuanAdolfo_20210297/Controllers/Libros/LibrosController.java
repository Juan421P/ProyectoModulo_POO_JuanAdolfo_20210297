package JuanAdolfo_20210297.JuanAdolfo_20210297.Controllers.Libros;

import JuanAdolfo_20210297.JuanAdolfo_20210297.Models.DTO.Libros.LibrosDTO;
import JuanAdolfo_20210297.JuanAdolfo_20210297.Services.Libros.LibrosService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/libros")
public class LibrosController {

    @Autowired
    LibrosService service;

    @GetMapping("/consultarLibros")
    public List<LibrosDTO> consultarLibros() {
        return service.consultarLibros();
    }

    @PostMapping("/registrarLibro")
    public ResponseEntity<?> registrarLibro(
            @Valid @RequestBody LibrosDTO dto,
            HttpServletRequest request
    ) {
        try {
            LibrosDTO respuesta = service.registrarLibro(dto);
            if(respuesta == null){
                return ResponseEntity.badRequest().body(Map.of(
                        "status", "Inserción fallida :(",
                        "errorType", "VALIDATION_ERROR",
                        "message", "El libro no pudo ser registrado"
                ));
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
               "status", "Inserción realizada exitosamente :)",
               "data", respuesta
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
               "status", "Error :(",
               "message", "Error no muy bien controlado a la hora de registrar un libro :(",
               "detail", e.getMessage()
            ));
        }
    }

    @PutMapping("/modificarLibro/{id}")
    public ResponseEntity<?> modificarLibro(
        @PathVariable Long id,
        @Valid @RequestBody LibrosDTO dto,
        BindingResult bindingResult
    ){

    }

}
