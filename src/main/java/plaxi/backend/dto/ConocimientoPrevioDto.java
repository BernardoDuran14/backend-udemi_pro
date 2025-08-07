package plaxi.backend.dto;

public class ConocimientoPrevioDto {

    private Long idConocimiento;
    private String descripcion;
    private Long cursoId;  // Solo el ID del curso al que pertenece

    public ConocimientoPrevioDto() {}

    public ConocimientoPrevioDto(Long idConocimiento, String descripcion, Long cursoId) {
        this.idConocimiento = idConocimiento;
        this.descripcion = descripcion;
        this.cursoId = cursoId;
    }

    // Getters y Setters
    public Long getIdConocimiento() {
        return idConocimiento;
    }

    public void setIdConocimiento(Long idConocimiento) {
        this.idConocimiento = idConocimiento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getCursoId() {
        return cursoId;
    }

    public void setCursoId(Long cursoId) {
        this.cursoId = cursoId;
    }
}

