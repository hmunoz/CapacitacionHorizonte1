package ar.edu.unrn.lia.capacitacionhorizonte1.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class ImageClient {
    private Retrofit retrofit;
    private final static String BASE_URL = "https://sizzling-heat-8971.firebaseio.com/";

    public ImageClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public ImageApiEndpointInterface getImageService() {
        return retrofit.create(ImageApiEndpointInterface.class);
    }
}
