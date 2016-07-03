package ar.edu.unrn.lia.capacitacionhorizonte1.api;

import com.google.gson.JsonObject;

import ar.edu.unrn.lia.capacitacionhorizonte1.image.entity.Image;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by horaciomunoz on 3/7/16.
 */

public interface ImageApiEndpointInterface {
    @GET("images.json")
    Call<JsonObject> getImages();
}
