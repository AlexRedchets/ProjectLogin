package com.alexredchets.projectlogin.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.alexredchets.projectlogin.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.sign_in_button)
    public void signInClicked(View view) {
        Intent i = new Intent(this, IntroActivity.class);
        startActivity(i);
        finish();
    }

    @OnClick(R.id.sign_up_button)
    public void signUpClicked(View view) {
        Intent i = new Intent(this, SignUpActivity.class);
        startActivity(i);
    }
}
