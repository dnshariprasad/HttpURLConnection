package hari.getcall;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private Button getInfo;
    private TextView responseText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getInfo = (Button) findViewById(R.id.getInfo);
        responseText = (TextView) findViewById(R.id.responseText);
        getInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Connect and get data from http://ip-api.com/json ????
                //*What is a Task?
                GetInfo getInfo = new GetInfo();
                getInfo.execute();
            }
        });

    }

    //*AsyncTask
    class GetInfo extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //*Progress Dialog
        }

        @Override
        protected String doInBackground(String... params) {
            //URL you want to connect
            String link = "http://ip-api.com/json";
            StringBuffer responseBuffer = null;
            //*Streams
            InputStream is = null;
            try {
                //Create URL object with site you want to connect
                URL url = new URL(link);
                //call openConnection method of url
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                // *Request methods
                httpURLConnection.setRequestMethod("GET");
                //*response codes
                if (httpURLConnection.getResponseCode() == 200 || httpURLConnection.getResponseCode() == 201) {
                    httpURLConnection.setConnectTimeout(10000);
                    httpURLConnection.setReadTimeout(10000);
                    is = httpURLConnection.getInputStream();
                    int ch;
                    responseBuffer = new StringBuffer();
                    while ((ch = is.read()) != -1) {
                        responseBuffer.append((char) ch);
                    }
                    is.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return responseBuffer.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            responseText.setText(s.toString());
            //What is JSON ?
            //JSON Array ?
            //JSON Object?
            //JSON parsing?
        }
    }
}
