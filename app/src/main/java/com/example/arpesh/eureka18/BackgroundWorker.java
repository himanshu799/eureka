package com.example.arpesh.eureka18;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by arpesh on 7/2/18 4:42 AM Eureka18 3:16 AM Eureka18 3:25 AM Eureka18.
 */

public class BackgroundWorker extends AsyncTask<String, Void , String> {

    private Context BackgroundWorkerContext;
    private AlertDialog alertDialog;
    BackgroundWorker(Context ctx){
        BackgroundWorkerContext = ctx;
    }

        @Override
    protected String doInBackground(String... strings) {
        String Login_Url = "https://eureka18.000webhostapp.com/Login.php";

        if(strings[0].equals("Login")){
            String data ="";
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
                data += current;}
            JSONArray jsonarray = new JSONArray(data);
            JSONObject jsonobject = jsonarray.getJSONObject(0);
            String mobile = jsonobject.getString("phone_no");
                Log.d("Mobile no",mobile);

                dataOutputStream .close();
            inputStream.close();

            return mobile;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;

    }

    private String getServerResponse(String json) {

        String SignIN_Url = "https://eureka18.000webhostapp.com/Register.php";
        Log.d("JSP",SignIN_Url);
        String data ="";
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


        alertDialog = new AlertDialog.Builder(BackgroundWorkerContext).create();
        alertDialog.setTitle("Login Status");
    }


    @Override
    protected void onPostExecute(String result) {
        System.out.println(result);

        switch (result) {
            case "Login Successful":
                Toast.makeText(BackgroundWorkerContext.getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                BackgroundWorkerContext.startActivity(
                        new Intent
                                (
                                        BackgroundWorkerContext.getApplicationContext(),
                                        AadharCard.class
                                ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));


                break;
            case "Registration Successful":
                Toast.makeText(BackgroundWorkerContext.getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                BackgroundWorkerContext.startActivity(
                        new Intent
                                (
                                        BackgroundWorkerContext.getApplicationContext(),
                                        AadharCard.class
                                ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                break;
            case "Registration Failed":
            case "Login Failed":
            case "null":
                Toast.makeText
                        (
                                BackgroundWorkerContext.getApplicationContext(),
                                result, Toast.LENGTH_SHORT
                        ).show();
                Log.d("JSonresult", result);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
