package ar.edu.unrn.lia.capacitacionhorizonte1.api.login;

import ar.edu.unrn.lia.capacitacionhorizonte1.api.ImageApiEndpointInterface;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class LoginClient {
    private Retrofit retrofit;
    private final static String BASE_URL = "http://preprod.lia.unrn.edu.ar:8080/ubicua_dev/";

    public LoginClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public LoginApiEndpointInterface getLoginService() {
        return retrofit.create(LoginApiEndpointInterface.class);
    }
}
