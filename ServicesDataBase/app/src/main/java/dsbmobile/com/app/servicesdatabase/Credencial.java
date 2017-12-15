package dsbmobile.com.app.servicesdatabase;

/**
 * Created by giova on 11/13/2017.
 */

public class Credencial {
    private int id;
    private String usuario;
    private String contrasena;
    private  int pin;

    public Credencial(){
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

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }
}
