package ar.edu.unrn.lia.capacitacionhorizonte1.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import ar.edu.unrn.lia.capacitacionhorizonte1.entities.ImageEntity;

@Dao
public interface ImageDao {

    @Query("SELECT * FROM imageentity")
    List<ImageEntity> getAll();

    @Insert
    void insertAll(ImageEntity... imageEntities);

    @Delete
    void delete(ImageEntity imageEntity);


    @Query("SELECT * FROM imageEntity WHERE text = :text")
    List<ImageEntity> getImageEntityByText(String text);
}