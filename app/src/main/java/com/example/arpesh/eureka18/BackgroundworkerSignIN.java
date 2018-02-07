package com.example.arpesh.eureka18;

import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by arpesh on 8/2/18 2:42 AM Eureka18 3:16 AM Eureka18.
 */

public class BackgroundworkerSignIN extends AsyncTask<String,Void, String> {
    BackgroundworkerSignIN(Context ctx1){

    }
    @Override
    protected String doInBackground(String... strings) {
        String SignIN_Url = "https://eureka18.000webhostapp.com/Register.php";
        if(strings[0].equals("SignIn")) try {
            String Fname = strings[1];
            String Lname = strings[2];
            String Mobile = strings[3];
            String Email = strings[4];
            String Password = strings[5];
            URL url = new URL(SignIN_Url);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter
                    (new OutputStreamWriter(outputStream,"UTF-8"));
            String post_data = URLEncoder.encode("User_FName","UTF-8")+"="+URLEncoder.encode(Fname,"UTF-8")+
                    "&"+
                    URLEncoder.encode("User_LName","UTF-8")+"="+URLEncoder.encode(Lname,"UTF-8")+
                    "&"+
                    URLEncoder.encode("User_MobileNo","UTF-8")+"="+URLEncoder.encode(Mobile,"UTF-8")+
                    "&"+
                    URLEncoder.encode("User_Email","UTF-8")+"="+URLEncoder.encode(Email,"UTF-8")+
                    "&"+
                    URLEncoder.encode("User_UserPassword","UTF-8")+"="+URLEncoder.encode(Password,"UTF-8");
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
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
