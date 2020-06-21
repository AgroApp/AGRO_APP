package com.example.sha.agro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CityWeather extends AppCompatActivity {
    TextView tv1,tv2,tv3,tv4,tv5;
    String city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_city);
        tv1=(TextView)findViewById(R.id.tv1);
        tv2=(TextView)findViewById(R.id.tv2);
        tv3=(TextView)findViewById(R.id.tv3);





        city =getIntent().getStringExtra("CITY");






        String url = "https://api.openweathermap.org/data/2.5/weather?q="+city+"&appid=960c8c15419cbeb3426548ac17f6afeb&units=metric";


        JsonObjectRequest json=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    JSONObject main_object = response.getJSONObject("main");
                    JSONArray array=response.getJSONArray("weather");
                    JSONObject object=array.getJSONObject(0);
                    String temp=String.valueOf(main_object.getDouble("temp"));
                    String description=object.getString("description");
                    String City=response.getString("name");
                    Log.d("city",City);
                    tv1.setText(city);
                    tv2.setText(temp);
                    tv3.setText(description);




                }catch (JSONException e){
                    e.printStackTrace();
                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
        RequestQueue queue= Volley.newRequestQueue(this);
        queue.add(json);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainactivitymenu,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case R.id.profile:
                Intent profile = new Intent(this, profile.class);
                startActivity(profile);
                break;


            case R.id.logout:

                FirebaseAuth.getInstance().signOut();
                finish();
                Intent logout = new Intent(this, MainActivity.class);
                startActivity(logout);
                break;
        }
        return true;

    }
}
