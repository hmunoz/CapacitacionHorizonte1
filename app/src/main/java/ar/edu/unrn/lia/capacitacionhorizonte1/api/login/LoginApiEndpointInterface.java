package ar.edu.unrn.lia.capacitacionhorizonte1.api.login;

import com.google.gson.JsonObject;

import ar.edu.unrn.lia.capacitacionhorizonte1.entities.LoginBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by horaciomunoz on 3/7/16.
 */

public interface LoginApiEndpointInterface {

    @Headers("Content-Type: application/json")
    @POST("auth/")
    Call<Authorization> login(@Body LoginBody loginBody);
}
