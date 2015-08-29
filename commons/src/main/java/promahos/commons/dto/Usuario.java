package promahos.commons.dto;

/**
 * Created by rbazua on 19/02/2015.
 */
public class Usuario {
    private String email;
    private Rol rol;
    private Academia academia;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Academia getAcademia() {
        return academia;
    }

    public void setAcademia(Academia academia) {
        this.academia = academia;
    }
}
