package com.gibran.ahmed.weatherly;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<DataShow> {
TextView city,time,temp ;
Spinner Cityoflist ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        city = (TextView)findViewById(R.id.city);
        temp = (TextView)findViewById(R.id.temp);
        // time = (TextView)findViewById(R.id.data);
      //  speed = (TextView)findViewById(R.id.speed);
        Cityoflist = (Spinner)findViewById(R.id.spinner);
        ArrayList<String> listspinner = new ArrayList<>();
        listspinner.add("طرابلس");
        listspinner.add("بنغازي");
        listspinner.add("سبها");
        listspinner.add("اجدابيا");
        listspinner.add("غريان");
        listspinner.add("صرمان");
        ArrayAdapter<String> adapterspinner = new
                ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,listspinner);
        Cityoflist.setAdapter(adapterspinner);
        //Cityoflist.setSelection(0);
        Cityoflist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String text = Cityoflist.getSelectedItem().toString();

                if (i==0){
                    String v = String.valueOf(i);
                    initLoader(v);
                }else if (i==1){
                    String v = String.valueOf(i);

                    initLoader1(v);
                    Cityoflist.setSelection(1);
                }else if (i==2){
                    String v = String.valueOf(i);

                    initLoader1(v);
                    Cityoflist.setSelection(2);
                }else if (i==3){
                    String v = String.valueOf(i);

                    initLoader1(v);
                    Cityoflist.setSelection(3);
                }else if (i==4){
                    String v = String.valueOf(i);

                    initLoader1(v);
                    Cityoflist.setSelection(4);
                }else if (i==5){
                    String v = String.valueOf(i);

                    initLoader1(v);
                    Cityoflist.setSelection(5);
                }
                if (text.equalsIgnoreCase("طرابلس")){
                    initLoader1("0");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
       // temp_maxANDtemp_min = (TextView)findViewById(R.id.temp_minANDtemp_max);
       // sunsetANDsunrise = (TextView)findViewById(R.id.sunriseANDsunset);
       // humidity = (TextView)findViewById(R.id.humidity);
       // pressure = (TextView)findViewById(R.id.pressure);

    }

    @NonNull
    @Override
    public Loader<DataShow> onCreateLoader(int id, @Nullable Bundle args) {
        Cityoflist.setSelection(0);

        return new Async(MainActivity.this, args.getString("API_URL"));
    }

    @Override
    public void onLoadFinished(@NonNull Loader<DataShow> loader, final DataShow data) {
String cityName = data.getCity();
if (cityName.equalsIgnoreCase("Tripoli")){
    show1(data);

}else if (cityName.equalsIgnoreCase("Benghazi")){
    show2(data);

}else if (cityName.equalsIgnoreCase("Sabha")){
    show3(data);

}else if (cityName.equalsIgnoreCase("Ajdabiya")){
    show4(data);

}else if (cityName.equalsIgnoreCase("Garyan")){
    show5(data);

}else if (cityName.equalsIgnoreCase("Surman")){
    show6(data);

}

/*Cityoflist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Cityoflist.setSelection(0);
        if (i==1){

        }else if (i==0){
           Toast.makeText(getApplicationContext()," 'gh",Toast.LENGTH_LONG).show();
show1(data);
        }else {
         //   Toast.makeText(getApplicationContext()," 'طرابلس",Toast.LENGTH_LONG).show();
           // show1(data);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
});
Benghazi,Libya
"Tripoli,%20Libya"
*/
    }

    @Override
    public void onLoaderReset(@NonNull Loader<DataShow> loader) {

    }
    private void initLoader(String s){

        Bundle bundle = new Bundle();
        bundle.putString("API_URL", s);

        getSupportLoaderManager().initLoader(
                1,
                bundle,
                this).forceLoad();
    }
    private void initLoader1(String s){

        Bundle bundle = new Bundle();
        bundle.putString("API_URL", s);

        getSupportLoaderManager().restartLoader(
                1,
                bundle,
                this).forceLoad();
    }
    public  void show1 (DataShow data){
        city.setText(data.getCity());
        temp.setText(data.getTemp());
        final RelativeLayout layout = (RelativeLayout)findViewById(R.id.r);
        layout.setBackgroundResource(R.drawable.tripoli);

        ArrayList<List> lists = new ArrayList<List>();
        lists.add(new List("اخر تحديث لحالة الطقس",data.getTime()));
        lists.add(new List("سرعة الرياح",data.getSpeed()+" km/h"));
        lists.add(new List("الرطوبة",data.getHumidity()+" %"));
        lists.add(new List("الضغط",data.getPressure()+ " hpd"));
        lists.add(new List("درجة الحرارة الكبرى والصغرى ",data.getTemp_min()+" ℃"+" , "+data.getTemp_max()+" ℃"));
        Adaptlist adaptlist = new Adaptlist(getApplicationContext(),lists);
        final ListView listView = (ListView)findViewById(R.id.listview);
        listView.setAdapter(adaptlist);
    }
    public  void show2 (DataShow data){
        final RelativeLayout layout = (RelativeLayout)findViewById(R.id.r);
        layout.setBackgroundResource(R.drawable.benghazi);
        city.setText(data.getCity());
        temp.setText(data.getTemp());
        ArrayList<List> lists = new ArrayList<List>();
        lists.add(new List("اخر تحديث لحالة الطقس",data.getTime()));
        lists.add(new List("سرعة الرياح",data.getSpeed()+" km/h"));
        lists.add(new List("الرطوبة",data.getHumidity()+" %"));
        lists.add(new List("الضغط",data.getPressure()+ " hpd"));
        lists.add(new List("درجة الحرارة الكبرى والصغرى ",data.getTemp_min()+" ℃"+" , "+data.getTemp_max()+" ℃"));
        Adaptlist adaptlist = new Adaptlist(getApplicationContext(),lists);
        final ListView listView = (ListView)findViewById(R.id.listview);
        listView.setAdapter(adaptlist);
    }
    public  void show3 (DataShow data){
        final RelativeLayout layout = (RelativeLayout)findViewById(R.id.r);
        layout.setBackgroundResource(R.drawable.sbha);
        city.setText(data.getCity());
        temp.setText(data.getTemp());
        ArrayList<List> lists = new ArrayList<List>();
        lists.add(new List("اخر تحديث لحالة الطقس",data.getTime()));
        lists.add(new List("سرعة الرياح",data.getSpeed()+" km/h"));
        lists.add(new List("الرطوبة",data.getHumidity()+" %"));
        lists.add(new List("الضغط",data.getPressure()+ " hpd"));
        lists.add(new List("درجة الحرارة الكبرى والصغرى ",data.getTemp_min()+" ℃"+" , "+data.getTemp_max()+" ℃"));
        Adaptlist adaptlist = new Adaptlist(getApplicationContext(),lists);
        final ListView listView = (ListView)findViewById(R.id.listview);
        listView.setAdapter(adaptlist);
    }
    public  void show4 (DataShow data){
        final RelativeLayout layout = (RelativeLayout)findViewById(R.id.r);
        layout.setBackgroundResource(R.drawable.ajdabya);
        city.setText(data.getCity());
        temp.setText(data.getTemp());
        ArrayList<List> lists = new ArrayList<List>();
        lists.add(new List("اخر تحديث لحالة الطقس",data.getTime()));
        lists.add(new List("سرعة الرياح",data.getSpeed()+" km/h"));
        lists.add(new List("الرطوبة",data.getHumidity()+" %"));
        lists.add(new List("الضغط",data.getPressure()+ " hpd"));
        lists.add(new List("درجة الحرارة الكبرى والصغرى ",data.getTemp_min()+" ℃"+" , "+data.getTemp_max()+" ℃"));
        Adaptlist adaptlist = new Adaptlist(getApplicationContext(),lists);
        final ListView listView = (ListView)findViewById(R.id.listview);
        listView.setAdapter(adaptlist);
    }
    public  void show5 (DataShow data){
        final RelativeLayout layout = (RelativeLayout)findViewById(R.id.r);
        layout.setBackgroundResource(R.drawable.garyan);
        city.setText(data.getCity());
        temp.setText(data.getTemp());
        ArrayList<List> lists = new ArrayList<List>();
        lists.add(new List("اخر تحديث لحالة الطقس",data.getTime()));
        lists.add(new List("سرعة الرياح",data.getSpeed()+" km/h"));
        lists.add(new List("الرطوبة",data.getHumidity()+" %"));
        lists.add(new List("الضغط",data.getPressure()+ " hpd"));
        lists.add(new List("درجة الحرارة الكبرى والصغرى ",data.getTemp_min()+" ℃"+" , "+data.getTemp_max()+" ℃"));
        Adaptlist adaptlist = new Adaptlist(getApplicationContext(),lists);
        final ListView listView = (ListView)findViewById(R.id.listview);
        listView.setAdapter(adaptlist);
    }
    public  void show6 (DataShow data){
        final RelativeLayout layout = (RelativeLayout)findViewById(R.id.r);
        layout.setBackgroundResource(R.drawable.surman);
        city.setText(data.getCity());
        temp.setText(data.getTemp());
        ArrayList<List> lists = new ArrayList<List>();
        lists.add(new List("اخر تحديث لحالة الطقس",data.getTime()));
        lists.add(new List("سرعة الرياح",data.getSpeed()+" km/h"));
        lists.add(new List("الرطوبة",data.getHumidity()+" %"));
        lists.add(new List("الضغط",data.getPressure()+ " hpd"));
        lists.add(new List("درجة الحرارة الكبرى والصغرى ",data.getTemp_min()+" ℃"+" , "+data.getTemp_max()+" ℃"));
        Adaptlist adaptlist = new Adaptlist(getApplicationContext(),lists);
        final ListView listView = (ListView)findViewById(R.id.listview);
        listView.setAdapter(adaptlist);
    }



}
