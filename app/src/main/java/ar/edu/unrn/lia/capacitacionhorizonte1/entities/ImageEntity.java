package ar.edu.unrn.lia.capacitacionhorizonte1.entities;



import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;


@Entity
public class ImageEntity  {

    @PrimaryKey(autoGenerate = true)
    long id;

    @ColumnInfo
    String text;

    @ColumnInfo
    String imageURL;

    @ColumnInfo
    String sourceURL;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public ImageEntity(String text, String imageURL, String sourceURL) {
        this.text = text;
        this.imageURL = imageURL;
        this.sourceURL = sourceURL;
    }
}