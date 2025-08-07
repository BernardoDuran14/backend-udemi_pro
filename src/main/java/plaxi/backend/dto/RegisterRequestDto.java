package plaxi.backend.dto;


public class RegisterRequestDto {

    private UsuarioDto usuario;
    private PersonaDto persona;

    public RegisterRequestDto() {
    }

    public RegisterRequestDto(UsuarioDto usuario, PersonaDto persona) {
        this.usuario = usuario;
        this.persona = persona;
    }

    public UsuarioDto getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDto usuario) {
        this.usuario = usuario;
    }

    public PersonaDto getPersona() {
        return persona;
    }

    public void setPersona(PersonaDto persona) {
        this.persona = persona;
    }
}
