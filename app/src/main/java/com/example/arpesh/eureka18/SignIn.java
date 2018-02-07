package com.example.arpesh.eureka18;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by arpesh on 5/2/18.
 */

public class SignIn extends AppCompatActivity{
    private EditText InputFname, InputLname,InputMobileno, InputEmail, InputPassword ;
    private Button SignIn;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        InputFname = findViewById(R.id.activity_signin_FirstName);
        InputLname = findViewById(R.id.activity_signin_LastName);
        InputMobileno = findViewById(R.id.activity_signin_Mobileno);
        InputEmail = findViewById(R.id.activity_signin_Email);
        InputPassword = findViewById(R.id.activity_signin_Password);
        SignIn = findViewById(R.id.activity_signinButton);
        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Fname = InputFname.getText().toString().trim();
                String Lname = InputLname.getText().toString().trim();
                String type = "SignIn";
                String Mobileno =InputMobileno.getText().toString().trim();
                String Email = InputEmail.getText().toString().trim();
                String Password = InputPassword.getText().toString().trim();

                BackgroundworkerSignIN objbackgroundworkerSignIN = new BackgroundworkerSignIN(getApplicationContext());
                objbackgroundworkerSignIN.execute(type , Fname, Lname, Mobileno, Email, Password);
            }
        });
    }
}
