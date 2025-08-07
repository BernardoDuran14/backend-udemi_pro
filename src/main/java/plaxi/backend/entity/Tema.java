package plaxi.backend.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "Tema", catalog = "PlaxiDB", schema = "public")
public class Tema implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tema")
    private Long idTema;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "orden", nullable = false)
    private int orden;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "estado", nullable = false)
    private Boolean estado;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "contenido", referencedColumnName = "s3_object_id", nullable = false)
    private S3Object recursoMultimedia;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "leccion_id_leccion", referencedColumnName = "id_leccion", nullable = false)
    private Leccion leccion;

    public Tema() {
    }

    public Tema(String titulo, int orden, String descripcion, S3Object recursoMultimedia, Leccion leccion, Boolean estado) {
        this.titulo = titulo;
        this.orden = orden;
        this.descripcion = descripcion;
        this.recursoMultimedia = recursoMultimedia;
        this.leccion = leccion;
        this.estado = estado;
    }

    // Getters y Setters
    public Long getIdTema() {
        return idTema;
    }

    public void setIdTema(Long idTema) {
        this.idTema = idTema;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getOrden() {
        return orden;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public S3Object getRecursoMultimedia() {
        return recursoMultimedia;
    }

    public void setRecursoMultimedia(S3Object recursoMultimedia) {
        this.recursoMultimedia = recursoMultimedia;
    }

    public Leccion getLeccion() {
        return leccion;
    }

    public void setLeccion(Leccion leccion) {
        this.leccion = leccion;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
