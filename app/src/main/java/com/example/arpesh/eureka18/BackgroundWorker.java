package com.example.arpesh.eureka18;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by arpesh on 7/2/18 4:42 AM Eureka18 3:16 AM Eureka18 3:25 AM Eureka18.
 */

public class BackgroundWorker extends AsyncTask<String, Void , String> {

    private Context BackgroundWorkerContext;
    private TextView MobileNo;
    private AlertDialog alertDialog , alertDialogOtp;

    BackgroundWorker(Context ctx){
        BackgroundWorkerContext = ctx;

    }
    void textView(TextView textView){
        this.MobileNo = textView;
    }

    @Override
    protected String doInBackground(String... strings) {

        String Login_Url = "https://eureka18.000webhostapp.com/Login.php";

        if(strings[0].equals("Login")){
            String data = "";
            try {
                URL url = new URL(Login_Url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setFixedLengthStreamingMode(strings[1].getBytes().length);
                httpURLConnection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
                Log.d("JSP",httpURLConnection.getRequestProperties().toString());
                httpURLConnection.setRequestProperty("X-Requested-With", "XMLHttpRequest");
                Log.d("JSP",httpURLConnection.getRequestProperties().toString());
                httpURLConnection.setRequestProperty("Connection", "close");
                httpURLConnection.connect();

                DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
                Log.d("JSP",wr.toString());
                wr.write(strings[1].getBytes());
                Log.d("JSP",wr.toString());
                wr.flush();


                InputStream in = httpURLConnection.getInputStream();
                Log.d("JSP",in.toString());
                InputStreamReader inputStreamReader = new InputStreamReader(in);
                Log.d("JSP",inputStreamReader.toString());
                int inputStreamData = inputStreamReader.read();
                Log.d("JSPppp", String.valueOf(inputStreamData));
                while (inputStreamData != -1) {
                    char current = (char) inputStreamData;
                    inputStreamData = inputStreamReader.read();
                    data += current;
                Log.d("Data", data);}
                wr.close();
                in.close();



            } catch (MalformedURLException e) {
                Log.d("JSon1",e.toString());
            } catch (IOException e) {
                Log.d("JSon2",e.toString());
            }
            return data;

        }
        if(strings[0].equals("register")){
            Log.d("JSP",strings[0]);
                return getServerResponse(strings[1]);
        }
        if(strings[0].equals("Aadhar")){
            return getNumberFServer(strings[1]);

        }

       return null;
    }

    private String getNumberFServer(String json) {
        String Aadhar_Url = "https://eureka18.000webhostapp.com/aadhar.php";
        String data="";
        String mobile= null;
        try {
            URL url = new URL(Aadhar_Url);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            httpURLConnection.setRequestProperty("X-Requested-With", "XMLHttpRequest");
            httpURLConnection.connect();
            DataOutputStream dataOutputStream
                    = new DataOutputStream(httpURLConnection.getOutputStream());

            dataOutputStream.writeBytes(json);
            dataOutputStream.flush();



            InputStream inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

            int inputStreamData = inputStreamReader.read();
            Log.d("inputstream", String.valueOf(inputStreamData));
            while (inputStreamData != -1) {
                char current = (char) inputStreamData;
                inputStreamData = inputStreamReader.read();
                data += current;
            System.out.println("Mobile no" +data);}
            JSONArray jsonarray = new JSONArray(data);
            JSONObject jsonobject = jsonarray.getJSONObject(0);
             mobile = jsonobject.getString("phone_no");
                Log.d("Mobile no",mobile);

                dataOutputStream .close();
            inputStream.close();



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("Mobile no", mobile);
        return mobile;

    }

    private String getServerResponse(String json) {

        String SignIN_Url = "https://eureka18.000webhostapp.com/Register.php";
        Log.d("JSP",SignIN_Url);
        String data =null;
        try {
            URL url = new URL(SignIN_Url);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setFixedLengthStreamingMode(json.getBytes().length);
            httpURLConnection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            Log.d("JSP",httpURLConnection.getRequestProperties().toString());
            httpURLConnection.setRequestProperty("X-Requested-With", "XMLHttpRequest");
            Log.d("JSP",httpURLConnection.getRequestProperties().toString());
            httpURLConnection.connect();

            DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
            Log.d("JSP",wr.toString());
            wr.write(json.getBytes());
            Log.d("JSP",wr.toString());
            wr.flush();


            InputStream in = httpURLConnection.getInputStream();
            Log.d("JSP",in.toString());
            InputStreamReader inputStreamReader = new InputStreamReader(in);
            Log.d("JSP",inputStreamReader.toString());
            int inputStreamData = inputStreamReader.read();
            Log.d("JSPppp", String.valueOf(inputStreamData));
            while (inputStreamData != -1) {
                char current = (char) inputStreamData;
                inputStreamData = inputStreamReader.read();
                data += current;}
                wr.close();
                in.close();



    } catch (MalformedURLException e) {
            Log.d("JSon1",e.toString());
        } catch (IOException e) {
            Log.d("JSon2",e.toString());
        }
        return data;
    }

        @Override
    protected void onPreExecute() {
        super.onPreExecute();


        alertDialog = new AlertDialog.Builder(BackgroundWorkerContext).create();
       // alertDialogOtp.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        alertDialogOtp = new AlertDialog.Builder(BackgroundWorkerContext.getApplicationContext()).create();
        alertDialogOtp.setTitle("Your mobile no");
        alertDialog.setTitle("Login Status");
    }


    @Override
    protected void onPostExecute(String result) {
            super.onPostExecute(result);
       // System.out.println(result);


        if (result == null) {
            Toast.makeText
                    (
                            BackgroundWorkerContext.getApplicationContext(),
                            "Failed....", Toast.LENGTH_SHORT
                    ).show();
          //  Log.d("JSonresult", result);
        } else if (result.equals("Registration Failed") || result.equals("Login Failed")) {
            Toast.makeText
                    (
                            BackgroundWorkerContext.getApplicationContext(),
                            "Failed....", Toast.LENGTH_SHORT
                    ).show();
        //    Log.d("JSonresult", result);
        } else {
            if (result.equals("Registration Successful")) {
                Toast.makeText(BackgroundWorkerContext.getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                BackgroundWorkerContext.startActivity(
                        new Intent
                                (
                                        BackgroundWorkerContext.getApplicationContext(),
                                        AadharCard.class
                                ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            } else if (result.equals("Login Successful")) {

                Toast.makeText(BackgroundWorkerContext.getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                BackgroundWorkerContext.startActivity(
                        new Intent
                                (
                                        BackgroundWorkerContext.getApplicationContext(),
                                        AadharCard.class
                                ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            } else {

                Intent intent = new Intent(BackgroundWorkerContext.getApplicationContext(),OtpForUser.class);
                intent.putExtra("Mobile no",result);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                BackgroundWorkerContext.startActivities(new Intent[]{intent});

            }
        }

        }


    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
