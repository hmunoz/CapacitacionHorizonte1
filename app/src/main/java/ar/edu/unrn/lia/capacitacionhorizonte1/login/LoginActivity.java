package ar.edu.unrn.lia.capacitacionhorizonte1.login;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import java.util.concurrent.TimeUnit;
import ar.edu.unrn.lia.capacitacionhorizonte1.R;
import ar.edu.unrn.lia.capacitacionhorizonte1.main.MainActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    static final String TAG = LoginActivity.class.getSimpleName();

    @BindView(R.id.etEmal)
    EditText etEmal;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.btnSingin)
    Button btnSingin;
    @BindView(R.id.btnSingup)
    Button btnSingup;
    @BindView(R.id.progressbar)
    ProgressBar progressbar;
    @BindView(R.id.activity_main)
    RelativeLayout activityMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        Log.i(TAG, "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }

    @OnClick({R.id.btnSingin, R.id.btnSingup})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSingin:
                new LoginProgressBarShowHide().execute();
                Log.i(TAG, "btnSingin. OK");
                break;
            case R.id.btnSingup:

                Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
                etPassword.startAnimation(shake);

                Log.i(TAG, "btnSingup: Error Login.");
                break;
        }
    }


    private class LoginProgressBarShowHide extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... args) {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            progressbar.setVisibility(View.GONE);
            startActivity(new Intent(LoginActivity.this , MainActivity.class));
            super.onPostExecute(result);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressbar.setVisibility(View.VISIBLE);

        }
    }


}
