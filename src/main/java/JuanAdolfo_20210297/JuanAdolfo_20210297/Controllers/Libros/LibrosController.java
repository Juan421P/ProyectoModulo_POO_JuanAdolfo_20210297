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

import java.util.HashMap;
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
            if (respuesta == null) {
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
            BindingResult bindingResult // Es mi mayor entendimiento que esto valida el objeto de tipo LibrosDTO con respecto a las propias validaciones definidas en LibrosDTO
    ) {
        // Se chequea se verifica se observa se averigua si hay errores de validación, como campos que superan el límite de caracteres o algo por el estilo
        if (bindingResult.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->
                    errores.put(error.getField(), error.getDefaultMessage())
            );
        }
        try {
            LibrosDTO registroActualizado = service.modificarLibro(id, dto);
            return ResponseEntity.ok(registroActualizado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "status", "Error :(",
                    "message", "Error no muy bien controlado a la hora de modificar un libro :(",
                    "detail", e.getMessage()
            ));
        }
    }

    @DeleteMapping("/erradicarLibro/{id}")
    public ResponseEntity<?> erradicarLibro(
            @PathVariable Long id
    ) {
        try {
            service.erradicarLibro(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/consultarLibro/{id}")
    public ResponseEntity<?> consultarLibroPorId(
            @PathVariable Long id
    ) {
        try {
            LibrosDTO libroEnCuestion = service.consultarLibroPorId(id);
            return ResponseEntity.ok(libroEnCuestion);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
