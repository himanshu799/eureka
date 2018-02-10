package com.example.arpesh.eureka18;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

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
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        if(strings[0].equals("register")){
                return getServerResponse(strings[1]);
        }

       return null;
    }

    private String getServerResponse(String json) {
        String SignIN_Url = "https://eureka18.000webhostapp.com/Register.php";
        String data ="";
        try {
            URL url = new URL(SignIN_Url);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);

            DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
            wr.writeBytes("PostData=" + json);
            wr.flush();
            wr.close();

            InputStream in = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(in);

            int inputStreamData = inputStreamReader.read();
            while (inputStreamData != -1) {
                char current = (char) inputStreamData;
                inputStreamData = inputStreamReader.read();
                data += current;}



    } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
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
       if(result.equals("Login Sucessful")){
           Toast.makeText(BackgroundWorkerContext.getApplicationContext(),result,Toast.LENGTH_SHORT).show();
           BackgroundWorkerContext.startActivity(
                   new Intent
                   (
                           BackgroundWorkerContext.getApplicationContext(),
                           com.example.arpesh.eureka18.UniqueIdGenerator.class
                   ));


       }
       if(result.equals("Registration Sucessful")){
           Toast.makeText(BackgroundWorkerContext.getApplicationContext(),result,Toast.LENGTH_SHORT).show();
           BackgroundWorkerContext.startActivity(
                   new Intent
                           (
                                   BackgroundWorkerContext.getApplicationContext(),
                                   com.example.arpesh.eureka18.Login.class
                           ));
       }
       if(result.equals("Registration Failed") | result.equals("Login Failed")){
           Toast.makeText
                   (
                           BackgroundWorkerContext.getApplicationContext(),
                           result,Toast.LENGTH_SHORT
                   ).show();
       }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
