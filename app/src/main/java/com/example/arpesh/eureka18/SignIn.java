package com.example.arpesh.eureka18;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by arpesh on 5/2/18 3:22 AM Eureka18.
 */

public class SignIn extends AppCompatActivity{
    private EditText InputFname, InputLname,InputMobileno, InputEmail, InputPassword ,InputUserName ;
    private Button SignIn;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        InputUserName = findViewById(R.id.activity_signin_UserName);
        InputFname = findViewById(R.id.activity_signin_FirstName);
        InputLname = findViewById(R.id.activity_signin_LastName);
        InputMobileno = findViewById(R.id.activity_signin_Mobileno);
        InputEmail = findViewById(R.id.activity_signin_Email);
        InputPassword = findViewById(R.id.activity_signin_Password);
        SignIn = findViewById(R.id.activity_signinButton);
        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{sendDataToServer();}
                catch (JSONException e){
                    e.printStackTrace();
                }
            }
        });
    }

    private void sendDataToServer() throws JSONException {
        String type = "register";

        final String Json = FormatDataASJSon();
        BackgroundWorker ObjBackgroundWorker = new BackgroundWorker(getApplicationContext());
        ObjBackgroundWorker.execute(type,Json);

    }

    private String FormatDataASJSon() throws JSONException {
        final JSONObject user = new JSONObject();
        String UserName = InputUserName.getText().toString().trim();
        String Fname = InputFname.getText().toString().trim();
        String Lname = InputLname.getText().toString().trim();
        String inputMobileno =InputMobileno.getText().toString().trim();
        String Email = InputEmail.getText().toString().trim();
        String Password = InputPassword.getText().toString().trim();
        int Mobileno = Integer.parseInt(inputMobileno);

        user.put("User_FName", Fname);
        user.put("User_LastName", Lname);
        user.put("User_MobileNo",Mobileno);
        user.put("User_Email",Email);
        user.put("User_UserName",UserName);
        user.put("User_UserPassword",Password);
        return user.toString();
    }
}
