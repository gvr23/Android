package dsbmobile.com.app.retrowilf.network;

import java.util.ArrayList;


import dsbmobile.com.app.retrowilf.network.response.CredencialesResponse;
import dsbmobile.com.app.retrowilf.network.response.CredencialesResponseList;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by giova on 11/15/2017.
 */

public interface APIInterface {
    @GET("v2/5a0c87312e00008c043a2c38")
    Call<CredencialesResponseList> getUsers();
}
