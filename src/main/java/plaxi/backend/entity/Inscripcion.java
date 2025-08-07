package plaxi.backend.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Inscripcion")
public class Inscripcion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inscripcion")
    private Long idInscripcion;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_inscripcion", nullable = false, updatable = false)
    private Date fechaInscripcion;

    @Column(name = "estado_inscripcion", nullable = false)
    private Boolean estadoInscripcion;

    @ManyToOne
    @JoinColumn(name = "Usuario_id_usuario", referencedColumnName = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "Curso_id_curso", referencedColumnName = "id_curso", nullable = false)
    private Curso curso;

    // Constructor por defecto
    public Inscripcion() {
        this.fechaInscripcion = new Date(); // Fecha actual al momento de creación
        this.estadoInscripcion = true;      // Estado por defecto como true
    }

    // Constructor con parámetros
    public Inscripcion(Long idInscripcion, Usuario usuario, Curso curso) {
        this.idInscripcion = idInscripcion;
        this.fechaInscripcion = new Date(); // Fecha actual al momento de creación
        this.estadoInscripcion = true;      // Estado por defecto como true
        this.usuario = usuario;
        this.curso = curso;
    }

    // Getters y Setters
    public Long getIdInscripcion() {
        return idInscripcion;
    }

    public void setIdInscripcion(Long idInscripcion) {
        this.idInscripcion = idInscripcion;
    }

    public Date getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(Date fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public Boolean getEstadoInscripcion() {
        return estadoInscripcion;
    }

    public void setEstadoInscripcion(Boolean estadoInscripcion) {
        this.estadoInscripcion = estadoInscripcion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}
