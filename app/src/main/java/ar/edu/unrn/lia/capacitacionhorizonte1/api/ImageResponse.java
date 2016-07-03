package ar.edu.unrn.lia.capacitacionhorizonte1.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import ar.edu.unrn.lia.capacitacionhorizonte1.image.entity.Image;


public class ImageResponse {

    @SerializedName("id")
    private String id;
    @SerializedName("images")
    private List<Image>  images;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public ImageResponse() {
    }
}
