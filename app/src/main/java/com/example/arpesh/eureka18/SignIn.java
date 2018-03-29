
package com.example.arpesh.eureka18;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.regex.Pattern;

/**
 * Created by arpesh on 5/2/18 3:22 AM Eureka18.
 */

public class SignIn extends Activity {
    private EditText InputFname, InputLname,InputMobileno, InputEmail, InputPassword ,InputCPassword,InputUserName ;
    private Button SignIn;
    private ProgressBar progressBar;
    private Object textInputLayout;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        progressBar = findViewById(R.id.ProgressBar);
        InputUserName = findViewById(R.id.activity_signin_UserName);

        InputEmail = findViewById(R.id.activity_signin_Email);

        InputFname = findViewById(R.id.activity_signin_FirstName);

        InputLname = findViewById(R.id.activity_signin_LastName);

        InputMobileno = findViewById(R.id.activity_signin_Mobileno);

        InputPassword = findViewById(R.id.activity_signin_Password);

        InputCPassword =findViewById(R.id.activity_signin_CPassword);

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



    public boolean validate()
    {
        boolean valid= true;
        if(InputUserName.length()==0||InputUserName.length()>32)
        {
            InputUserName.setError("Please Enter Valid Username");
            valid=false;
        }
        if(InputEmail.length()==0)
        {
        InputEmail.setError("Please Enter Valid Email Address ");
            valid=false;
        }
        if(InputFname.length()==0)
        {
            InputFname.setError("Please Enter The First Name");
            valid=false;
        }

        if(InputLname.length()==0)
        {
            InputLname.setError("Please Enter The Last Name");
            valid=false;
        }
        if(InputPassword.length()==0)
        {
            InputPassword.setError("Please Enter Password");
            valid=false;
        }
        if(InputCPassword.length()==0)
        {
            InputCPassword.setError("Please Enter Confirm Password");
            valid=false;
        }
        if(InputPassword.equals(InputCPassword))
        {
            Toast.makeText(this,"Password not matching",Toast.LENGTH_SHORT).show();
            valid = false;
        }

        return valid;
    }
    private void sendDataToServer() throws JSONException {
       if(validate())
       {
           String type = "register";
           final String Json = FormatDataASJSon();
           BackgroundWorker ObjBackgroundWorker = new BackgroundWorker(getApplicationContext());
           ObjBackgroundWorker.Views(progressBar);
           ObjBackgroundWorker.execute(type,Json);
       }

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
        BigInteger mobileNO = BigInteger.valueOf(Mobileno);

        user.put("User_Email",Email);
        user.put("User_Fname",Fname);
        user.put( "User_Lname",Lname);
        user.put("User_MobileNo",mobileNO);
        user.put("User_UserName",UserName);
        user.put("User_Password",Password);
        Log.d("JSp",user.toString(1));

        return user.toString(1);
    }
}