package ar.edu.unrn.lia.capacitacionhorizonte1.entities;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import ar.edu.unrn.lia.capacitacionhorizonte1.api.AppDatabase;

@Table(database = AppDatabase.class)
public class ImageEntity extends BaseModel {

    @PrimaryKey(autoincrement = true)
    long id;

    @Column
    String text;

    @Column
    String imageURL;

    @Column
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

    public ImageEntity() {
    }
}