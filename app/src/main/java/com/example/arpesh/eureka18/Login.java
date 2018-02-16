package com.example.arpesh.eureka18;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {
    private EditText inputUserName, inputPassword;
    private Button Login , SignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inputUserName = findViewById(R.id.Login_UserName);
        inputPassword = findViewById(R.id.Login_password);
        Login = findViewById(R.id.LoginButton);
        SignIn = findViewById(R.id.SignInButton);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    onLogin();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), com.example.arpesh.eureka18.SignIn.class));
            }
        });
    }

    private void onLogin() throws JSONException {
        JSONObject user = new JSONObject();
        String UserName = inputUserName.getText().toString().trim();
        String Password = inputPassword.getText().toString().trim();
        user.put("User_UserName",UserName);
        user.put("User_Password",Password);
        String type ="Login";
        BackgroundWorker ObjBackgroundWorker = new BackgroundWorker(getApplicationContext());
        ObjBackgroundWorker.execute(type ,user.toString());

    }

}
