package com.example.arpesh.eureka18;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by arpesh on 21/2/18 12:06 AM Eureka18.
 */

public class GetAddress extends AppCompatActivity {
    private TextView mAddress;
    private Button mFetch;
    private ProgressBar progressBar;
    private  int BtnTyp = 0 ;
    private String Aadhar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getaddress);
        progressBar = findViewById(R.id.GetAddress_ProgressBar);
        Aadhar = getIntent().getExtras().getString("Aadhar no");
        mAddress = findViewById(R.id.GetAddress_textView);
        mFetch = findViewById(R.id.GetAddress_Btn);
        mFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    mFetch.setEnabled(false);
                    fetchDataFromServer();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void fetchDataFromServer() throws JSONException {
        String type = "GetAddress";
        String Json = CreateJSOn();
        AddressBackground addressBackground = new AddressBackground(getApplicationContext());
        addressBackground.Views(mAddress,mFetch,progressBar);
        addressBackground.execute(type,Json);
    }

    private String CreateJSOn() throws JSONException {
        JSONObject AadharObj = new JSONObject();

        return AadharObj.put("aadhar",Aadhar).toString();
    }
}
