package ar.edu.unrn.lia.capacitacionhorizonte1.api;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by horaciomunoz on 3/7/16.
 */

@Database(name = AppDatabase.NAME, version = AppDatabase.VERSION)
public class AppDatabase {

    public static final String NAME = "curso2016"; // we will add the .db extension

    public static final int VERSION = 1;
}
