package dsbmobile.com.app.servicesdatabase.network;

import dsbmobile.com.app.servicesdatabase.network.response.CredencialResponse;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by giova on 11/13/2017.
 */

public interface ApiInterface {
    @GET("v2/5a0a1aac2e00006910489c1d")
    Call<CredencialResponse> getUsers();
}
