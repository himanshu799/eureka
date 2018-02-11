package com.example.arpesh.eureka18;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

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
        String Login_Url = "https://eureka18.000webhostapp.com/login.php";

        if(strings[0].equals("Login")){
            try {
                String user_name = strings[1];
                String Password = strings[2];
                URL url = new URL(Login_Url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter
                        (new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data = URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")+
                        "&"+
                        URLEncoder.encode("Password","UTF-8")+"="+URLEncoder.encode(Password,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line;
                while((line = bufferedReader.readLine())!= null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                Log.d("JSon",e.toString());
            } catch (IOException e) {
                Log.d("JSon",e.toString());
            }


        }
        if(strings[0].equals("register")){
            Log.d("JSP",strings[0]);
                return getServerResponse(strings[1]);
        }

       return null;
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

       if(result.equals("Login Successful")){
           Toast.makeText(BackgroundWorkerContext.getApplicationContext(),result,Toast.LENGTH_SHORT).show();
           BackgroundWorkerContext.startActivity(
                   new Intent
                   (
                           BackgroundWorkerContext.getApplicationContext(),
                           AadharCard.class
                   ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));


                                                            }
      else if(result.equals("Registration Successful")){
           Toast.makeText(BackgroundWorkerContext.getApplicationContext(),result,Toast.LENGTH_SHORT).show();
           BackgroundWorkerContext.startActivity(
                   new Intent
                           (
                                   BackgroundWorkerContext.getApplicationContext(),
                                   com.example.arpesh.eureka18.Login.class
                           ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                                                        }
       else{
                Toast.makeText
                       (
                               BackgroundWorkerContext.getApplicationContext(),
                               result,Toast.LENGTH_SHORT
                       ).show();
                Log.d("JSonresult",result);
           }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
