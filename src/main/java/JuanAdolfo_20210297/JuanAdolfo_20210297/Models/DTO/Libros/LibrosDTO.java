package JuanAdolfo_20210297.JuanAdolfo_20210297.Models.DTO.Libros;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString @EqualsAndHashCode @Getter @Setter
public class LibrosDTO {

    private Long id;

    @NotBlank(message = "El titulo es obligatorio :(")
    @Size(max = 200, message = "El titulo no debe exceder los 200 caracteres :(")
    private String titulo;

    @NotBlank(message = "El ISBN es obligatorio :(")
    @Size(max = 20, message = "El ISBN no debe exceder los 20 caracteres :(")
    private String isbn;

    @Min(1) // Supongo. Nada más para que no sean negativos. Le iba a poner 2000 pero hay libros ya viejitos entonces pues sí ni modo.
    private Long anioPublicacion;

    @Size(max = 50, message = "El genero no debe exceder los 50 caracteres :(")
    private String genero;

    @NotNull(message = "El id del autor es obligatorio :(")
    private Long autorId;

}
