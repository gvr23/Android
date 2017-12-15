package dsbmobile.com.app.retrowilf.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by giova on 11/15/2017.
 */

public class Credencial implements Parcelable {
    private int id;
    private String usuario;
    private String contrasena;
    private int pin;

    public Credencial(){}

    public Credencial(Parcel source) {
        id = source.readInt();
        usuario = source.readString();
        contrasena = source.readString();
        pin = source.readInt();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(usuario);
        dest.writeString(contrasena);
        dest.writeInt(pin);
    }

    public static final Parcelable.Creator<Credencial>  CREATOR = new Parcelable.Creator<Credencial>(){

        @Override
        public Credencial createFromParcel(Parcel source) {
            return new Credencial(source);
        }

        @Override
        public Credencial[] newArray(int size) {
            return new Credencial[size];
        }
    };
}
