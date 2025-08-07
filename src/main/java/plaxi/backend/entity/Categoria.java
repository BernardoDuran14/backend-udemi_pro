package plaxi.backend.entity;

import java.io.Serializable;
import jakarta.persistence.*;

@Entity
@Table(name = "Categoria", catalog = "PlaxiDB", schema = "public")
public class Categoria implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_categoria")
    private Long idCategoria;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "decripcion", nullable = false)
    private String decripcion;

    public Categoria() {
    }

    public Categoria(String nombre, String decripcion) {
        this.nombre = nombre;
        this.decripcion = decripcion;
    }

    // Getters y Setters
    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDecripcion() {
        return decripcion;
    }

    public void setDecripcion(String decripcion) {
        this.decripcion = decripcion;
    }
}
