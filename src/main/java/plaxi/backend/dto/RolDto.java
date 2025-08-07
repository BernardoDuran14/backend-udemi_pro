package plaxi.backend.dto;

public class RolDto {
    private Long id_rol;
    private String nombre;
    private boolean status;
    
    public RolDto() {
    }

    public RolDto(Long id_rol, String nombre, boolean status) {
        this.id_rol = id_rol;
        this.nombre = nombre;
        this.status = status;
    }

    public Long getId_rol() {
        return id_rol;
    }

    public void setId_rol(Long id_rol) {
        this.id_rol = id_rol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RolDto{" + "id_rol=" + id_rol + ", nombre=" + nombre + ", status=" + status + '}';
    }
}
