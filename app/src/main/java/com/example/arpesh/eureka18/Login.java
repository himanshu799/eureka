package com.example.arpesh.eureka18;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {
    private EditText inputEmail, inputPassword;
    private Button Login , SignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inputEmail = findViewById(R.id.Login_email);
        inputPassword = findViewById(R.id.Login_password);
        Login = findViewById(R.id.LoginButton);
        SignIn = findViewById(R.id.SignInButton);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLogin();
            }
        });
        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), com.example.arpesh.eureka18.SignIn.class));
            }
        });
    }

    private void onLogin() {
        String Email = inputEmail.getText().toString().trim();
        String Password = inputPassword.getText().toString().trim();
        String type = "Login";
        BackgroundWorker ObjBackgroundWorker = new BackgroundWorker(getApplicationContext());
        ObjBackgroundWorker.execute(type , Email, Password);

    }

}
