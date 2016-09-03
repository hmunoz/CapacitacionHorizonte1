package ar.edu.unrn.lia.capacitacionhorizonte1;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by horaciomunoz on 3/7/16.
 */

public class CursoApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //Init DBFlow
        FlowManager.init(new FlowConfig.Builder(this).build());
    }
}