package ar.edu.unrn.lia.capacitacionhorizonte1.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import ar.edu.unrn.lia.capacitacionhorizonte1.R;
import ar.edu.unrn.lia.capacitacionhorizonte1.main.MainActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

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
    }


    @OnClick({R.id.btnSingin, R.id.btnSingup})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSingin:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.btnSingup:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }
}
