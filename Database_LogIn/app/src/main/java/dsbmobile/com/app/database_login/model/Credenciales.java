package dsbmobile.com.app.database_login.model;

/**
 * Created by giova on 11/10/2017.
 */

public class Credenciales {
    private int id;
    private String usuario;
    private String contrasena;
    private String pin;

    public Credenciales(){}

    public Credenciales(String usuario, String contrasena, String pin) {
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.pin = pin;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
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

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    @Override
    public String toString() {
        String cad = usuario + " " + contrasena;
        return cad;
    }
}
