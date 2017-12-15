package dsbmobile.com.app.servicesdatabase.network.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by giova on 11/13/2017.
 */

public class CredencialResponse {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("usuario")
    @Expose
    private String usuario;

    @SerializedName("contrasena")
    @Expose
    private String contrasena;

    @SerializedName("pin")
    @Expose
    private int pin;

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
