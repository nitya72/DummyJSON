package com.example.nitya.jsontask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NetworkTask nt=new NetworkTask();
        nt.execute("https://robotux.in/android/dummy.json");
    }

    class NetworkTask extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            String stringUrl=strings[0];

            try{
                URL url=new URL(stringUrl);

                HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();

                InputStream inputStream=httpURLConnection.getInputStream();

                Scanner sc=new Scanner(inputStream);

                sc.useDelimiter("\\A");

                if (sc.hasNext()){
                    return sc.next();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "Error in Connection";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            ArrayList<ContactUser> users=parseJson(s);


            RecyclerView rvContact=findViewById(R.id.rvContacts);

            rvContact.setLayoutManager(new LinearLayoutManager(MainActivity.this));

            rvContact.setAdapter(new ContactUserAdapter(MainActivity.this,users));

            Log.i("Messagee",String.valueOf(users.size()));
        }
    }

    ArrayList<ContactUser> parseJson(String s){
        ArrayList<ContactUser> users=new ArrayList<>();

        try {
            JSONObject root=new JSONObject(s);

            JSONArray data=root.getJSONArray("data");

            for (int i=0;i<data.length();i++){

                JSONObject object=data.getJSONObject(i);
                String name=object.getString("name");
                String contact=object.getString("contact");

                ContactUser c=new ContactUser(contact,name);

                users.add(c);

            }

        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        return  users;
    }
}
