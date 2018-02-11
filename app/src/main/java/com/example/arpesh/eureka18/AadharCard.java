package com.example.arpesh.eureka18;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AadharCard extends AppCompatActivity {
    private EditText inputAadhar;
    private Button OKay;
    private TextView outMObile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aadharcard);
        inputAadhar = findViewById(R.id.UniqueID_EditText);
        outMObile = findViewById(R.id.UniqueID_TextView);
        OKay = findViewById(R.id.UniqueID_BUTTON);
        OKay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AskDataToServer();
            }
        });
    }

    private void AskDataToServer() {


    }
}
