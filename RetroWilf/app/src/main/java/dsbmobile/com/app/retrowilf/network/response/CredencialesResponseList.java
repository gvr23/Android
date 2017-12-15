package dsbmobile.com.app.retrowilf.network.response;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import dsbmobile.com.app.retrowilf.model.Credencial;

/**
 * Created by giova on 11/15/2017.
 */

public class CredencialesResponseList {
    @SerializedName("Credenciales")
    @Expose
    private ArrayList<CredencialesResponse> credencialesList;

    public ArrayList<CredencialesResponse> getCredencialesList() {
        return credencialesList;
    }
}
