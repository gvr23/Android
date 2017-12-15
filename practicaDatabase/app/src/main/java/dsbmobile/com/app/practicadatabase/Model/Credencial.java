package dsbmobile.com.app.practicadatabase.Model;

/**
 * Created by giova on 11/12/2017.
 */

public class Credencial {
    private int id;
    private String usuario;
    private String contrasena;

    public Credencial(){}

    public Credencial(String usuario, String contrasena) {
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
