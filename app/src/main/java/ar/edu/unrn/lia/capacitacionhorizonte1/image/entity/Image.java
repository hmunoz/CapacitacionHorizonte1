package ar.edu.unrn.lia.capacitacionhorizonte1.image.entity;


public class Image {
    private String imageURL;
    private String text;
    private String sourceURL;


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }


    public String getSourceURL() {
        return sourceURL;
    }

    public void setSourceURL(String sourceURL) {
        this.sourceURL = sourceURL;
    }

    public Image(String imageURL, String text, String sourceURL) {
        this.imageURL = imageURL;
        this.text = text;
        this.sourceURL = sourceURL;
    }

    public Image() {
    }
}
