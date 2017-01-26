package com.alexredchets.projectlogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.sign_in_button)
    public void signInClicked(View view){
        Toast.makeText(this, "Sign In Button Clicked", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.sign_up_button)
    public void signUpClicked(View view){
        Toast.makeText(this, "Sign Up Button Clicked", Toast.LENGTH_SHORT).show();

        Intent i = new Intent(this, SignUpActivity.class);
        startActivity(i);
    }
}
