package com.example.arpesh.eureka18;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by arpesh on 20/2/18 6:40 AM Eureka18.
 */

public class OtpForUser extends AppCompatActivity {
   private EditText mOtp;
    private TextView mNumber;
    private  Button mVerify;




    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otpforuser);
        mNumber = findViewById(R.id.OtpForUser_MobileNo);
        mOtp = findViewById(R.id.OtpForUser_OTP);
        mVerify = findViewById(R.id.OtpForUser_OTPBtn);
        String mNumbers = getIntent().getExtras().getString("Mobile no");
        mNumber.setText(mNumbers);
    }
}
