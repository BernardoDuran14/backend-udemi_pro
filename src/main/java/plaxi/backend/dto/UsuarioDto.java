package plaxi.backend.dto;

public class UsuarioDto {
    private Long id_usuario;
    private String username;
    private String password;
    private String gmail;
    private boolean status;
    private Long id_persona;
    private Long id_rol;

    public UsuarioDto() {
    }

    public UsuarioDto(Long id_usuario, String username, String password, String gmail, boolean status, Long id_persona, Long id_rol) {
        this.id_usuario = id_usuario;
        this.username = username;
        this.password = password;
        this.gmail = gmail;
        this.status = status;
        this.id_persona = id_persona;
        this.id_rol = id_rol;
    }

    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Long getId_persona() {
        return id_persona;
    }

    public void setId_persona(Long id_persona) {
        this.id_persona = id_persona;
    }

    public Long getId_rol() {
        return id_rol;
    }

    public void setId_rol(Long id_rol) {
        this.id_rol = id_rol;
    }

    @Override
    public String toString() {
        return "UsuarioDto{" + "id_usuario=" + id_usuario + ", username=" + username + ", password=" + password + ", gmail=" + gmail + ", status=" + status + ", id_persona=" + id_persona + ", id_rol=" + id_rol + '}';
    }
}
