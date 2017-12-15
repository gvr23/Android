package dsbmobile.com.app.retrowilf.network.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by giova on 11/15/2017.
 */

public class CredencialesResponse {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("usuario")
    @Expose
    private String usuario;

    @SerializedName("contrasena")
    @Expose
    private String contrasena;

    @SerializedName("pin")
    @Expose
    private String pin;

    public String getId() {
        return id;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getPin() {
        return pin;
    }
}
