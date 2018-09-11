package ar.edu.unrn.lia.capacitacionhorizonte1.dao;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import ar.edu.unrn.lia.capacitacionhorizonte1.entities.ImageEntity;

@Database(entities = {ImageEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract ImageDao imageDao();

    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context,
                            AppDatabase.class, "database-image")
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }
}