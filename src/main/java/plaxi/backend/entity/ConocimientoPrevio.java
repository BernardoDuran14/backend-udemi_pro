package plaxi.backend.entity;

import java.io.Serializable;
import jakarta.persistence.*;

@Entity
@Table(name = "ConocimientoPrevio", catalog = "PlaxiDB", schema = "public")
public class ConocimientoPrevio implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_conocimiento")
    private Long idConocimiento;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "curso_id_curso", referencedColumnName = "id_curso", nullable = false)
    private Curso curso;

    public ConocimientoPrevio() {
    }

    public ConocimientoPrevio(String descripcion, Curso curso) {
        this.descripcion = descripcion;
        this.curso = curso;
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

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}
