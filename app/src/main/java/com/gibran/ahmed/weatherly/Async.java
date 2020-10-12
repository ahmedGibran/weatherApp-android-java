package com.gibran.ahmed.weatherly;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Async extends AsyncTaskLoader<DataShow> {
    String city ;
    String index ;
    public Async(@NonNull Context context, String index) {
        super(context);
        this.index=index;
    }



    @Nullable
    @Override
    public DataShow loadInBackground() {
        if (index.equalsIgnoreCase("0")){
            city = "Tripoli,%20Libya";
        }else if (index.equalsIgnoreCase("1")){
            city = "Benghazi,Libya" ;
        }else if (index.equalsIgnoreCase("2")){
            city = "Sabha,Libya" ;
        }else if (index.equalsIgnoreCase("3")){
            city = "Ajdabiya,Libya" ;
        }else if (index.equalsIgnoreCase("4")){
            city = "Garyan,Libya" ;
        }else if (index.equalsIgnoreCase("5")){
            city = "Surman,Libya" ;
        }
        DataShow dataShows =null;



        String getdatajson ;
        try {
            URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q="+city+"&appid=0ab01ef0ce48d0aaffa2534bfe1f39e7");
            if (url==null){
                return null ;
            }
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode()==200){
                InputStream inputStream =httpURLConnection.getInputStream();
                getdatajson = streamreader(inputStream);
                        dataShows = jsonreader(getdatajson);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataShows;
    }

    private DataShow jsonreader(String getdatajson) {
        JSONObject jsonObject = null ;
            String date ;
            int gettemp ;
            String temp = null;
            String city ;
            String main ;
            String speed ;
            String sunrise ;
            String sunset ;
            String pressure ;
            String humidity ;
            String temp_min ;
            int gettempmin ;
            String temp_max ;
            int gettempmax ;

        DataShow dataShow = null ;
        if (getdatajson!=null){
        try {
           jsonObject =new JSONObject(getdatajson);
                    JSONObject getarray = jsonObject.getJSONObject("main");
                           //////////////////////////////////////////////////GetTemp

                            gettemp = (int) (getarray.getDouble("temp")-273.15);
                            temp =String.valueOf(gettemp);
                            ///////////////////////////////////////GETPressure
                            pressure = String.valueOf(getarray.getDouble("pressure"));
                            ////////////////////////////////Gethumidity
                            humidity = String.valueOf(getarray.getDouble("humidity"));
                            ////////////////////////GetTemp-min
                             gettempmin = (int) (getarray.getDouble("temp_min")-273.15);
                             temp_min = String.valueOf(gettempmin);
                             //////////////////////GetTemp-max
                            gettempmax = (int) (getarray.getDouble("temp_max")-273.15);
                            temp_max = String.valueOf(gettempmax);

                            ///////////////////////////GetDate

                            DateFormat df =DateFormat.getDateTimeInstance();
                            date =df.format(new Date(jsonObject.getLong("dt")*1000));

                            /////////////////////////////////////////////GetCity

                            city=jsonObject.get("name").toString();

                            ////////////////////////////////////Getmain
                            JSONArray Getweather = jsonObject.getJSONArray("weather");
                            main = Getweather.getJSONObject(0).getString("main");
                            ////////////////////////Get wind- speed ;
                            JSONObject Getarraywind = jsonObject.getJSONObject("wind");
                            speed = String.valueOf(Getarraywind.getDouble("speed"));
                            ////////////////////////Getsunrise
                           JSONObject getsys =jsonObject.getJSONObject("sys");
                           DateFormat dateFormat = DateFormat.getDateTimeInstance();
                           sunrise = dateFormat.format(new Date(getsys.getLong("sunrise")));
                           ///////////////////////////  GETsunset
                           sunset = dateFormat.format(new Date(getsys.getLong("sunset")));
                           ////////////////////////////////////////Get
            dataShow =  new DataShow( city,  date,  temp,  speed,  sunrise,  sunset,  pressure,  humidity,  temp_min,  temp_max);
        } catch (JSONException e) {
            e.printStackTrace();
        }}
        return dataShow ;
    }

    private String streamreader(InputStream inputStream) {
          StringBuilder stringBuilder
                =new StringBuilder();
        InputStreamReader inputStreamReader =
                new InputStreamReader(inputStream, Charset.forName("UTF-8"));
        BufferedReader bufferedReader = new
                BufferedReader(inputStreamReader);
        String line ;
        try {
            while ((line=bufferedReader.readLine())!=null){
                stringBuilder.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } {
        }
        return  stringBuilder.toString();
    }

}
