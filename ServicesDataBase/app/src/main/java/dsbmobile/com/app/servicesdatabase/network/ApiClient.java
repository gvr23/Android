package dsbmobile.com.app.servicesdatabase.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by giova on 11/13/2017.
 */

public class ApiClient {
    private static Retrofit retrofit = null;

    public static Retrofit getClient(){
        if (retrofit == null){
            try{
                retrofit = new Retrofit.Builder().baseUrl(URL.BASE_URL).
                                                  addConverterFactory(GsonConverterFactory.create()).
                                                  build();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return retrofit;
    }
}
