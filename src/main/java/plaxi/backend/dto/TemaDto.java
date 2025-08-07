package plaxi.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TemaDto {

    private Long idTema;
    private String titulo;
    private int orden;
    private String descripcion;
    private String recursoMultimedia;
    private Boolean estado;
    private Long leccionId;  // Solo el ID de la lección a la que pertenece el tema
}

