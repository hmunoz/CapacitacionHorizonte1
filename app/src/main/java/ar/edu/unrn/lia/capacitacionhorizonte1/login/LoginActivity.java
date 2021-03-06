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

import com.auth0.android.jwt.JWT;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import ar.edu.unrn.lia.capacitacionhorizonte1.R;
import ar.edu.unrn.lia.capacitacionhorizonte1.api.ImageApiEndpointInterface;
import ar.edu.unrn.lia.capacitacionhorizonte1.api.ImageClient;
import ar.edu.unrn.lia.capacitacionhorizonte1.api.login.Authorization;
import ar.edu.unrn.lia.capacitacionhorizonte1.api.login.LoginApiEndpointInterface;
import ar.edu.unrn.lia.capacitacionhorizonte1.api.login.LoginClient;
import ar.edu.unrn.lia.capacitacionhorizonte1.entities.LoginBody;
import ar.edu.unrn.lia.capacitacionhorizonte1.main.MainActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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


    LoginApiEndpointInterface loginApiEndpointInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        Log.i(TAG, "onCreate");

        loginApiEndpointInterface = new LoginClient().getLoginService();
    }


    private void loginService(String email, String password) {


        showProgress();
        setInputs(false);

        Call<Authorization> call = loginApiEndpointInterface.login(new LoginBody(email,password));
        call.enqueue(new Callback<Authorization>() {
            @Override
            public void onResponse(Call<Authorization> call, Response<Authorization> response) {

                if (response.isSuccessful()){
                    JWT jwt = new JWT(response.body().getToken());
                    List<String> fxs = jwt.getClaim("fxs").asList(String.class);
                    Log.d("FXS", fxs.toString());
                    navigateToMainScreen();
                }else {
                   Log.d(TAG, "Login Error" +  response.code());
                }

                hideProgress();
                setInputs(true);
            }

            @Override
            public void onFailure(Call<Authorization> call, Throwable t) {
                Log.d("TEST", call.toString());
                hideProgress();
                setInputs(true);
            }
        });
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
                //new LoginProgressBarShowHide().execute();
                loginService(etEmal.getText().toString(), etPassword.getText().toString());
                break;
            case R.id.btnSingup:

                loginError("Usuario y/o clave Invalidos");

                Log.i(TAG, "btnSingup: Error Login.");
                break;
        }
    }




    public void showProgress() {
        progressbar.setVisibility(View.VISIBLE);
    }


    public void hideProgress() {
        progressbar.setVisibility(View.GONE);
    }


    public void disableInputs() {
        setInputs(false);
    }


    public void enableInputs() {
        setInputs(true);
    }

    private void setInputs(boolean enabled){
        btnSingup.setEnabled(enabled);
        btnSingin.setEnabled(enabled);
        etEmal.setEnabled(enabled);
        etPassword.setEnabled(enabled);
    }


    public void loginError(String error) {

        etPassword.setText("");
        String msgError = String.format(getString(R.string.login_error_message_signin), error);
        etPassword.setError(msgError);

        Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
        etPassword.startAnimation(shake);
    }

    public void navigateToMainScreen() {
        startActivity(new Intent(this, MainActivity.class));
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
            hideProgress();
            setInputs(true);
            navigateToMainScreen();
            super.onPostExecute(result);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgress();
            setInputs(false);

        }
    }


}
