package com.example.sha.agro;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.example.sha.agro.R.string.toast_enter_verification;

public class WeatherActivity extends AppCompatActivity {

        private int locationRequestCode = 1000;
        private double lat = 0.0, log = 0.0;
        TextView tv1, tv2, tv3, tv4;

        String url;
        EditText city;
    Button btn;
    String City;

    private LocationRequest locationRequest;
    private LocationCallback locationCallback;
    FusedLocationProviderClient fusedLocationProviderClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);


      
        tv3 = (TextView) findViewById(R.id.temp);
        tv4 = (TextView) findViewById(R.id.description);
        tv2 = (TextView) findViewById(R.id.unknown);
        city = (EditText) findViewById(R.id.city);
        btn = (Button) findViewById(R.id.btn);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(20 * 1000);
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {


                                                Toast.makeText(WeatherActivity.this, "Please enable your location service", Toast.LENGTH_SHORT).show();

                                                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);

                                                startActivity(intent);

                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    if (location != null) {
                        lat = location.getLatitude();
                        log = location.getLongitude();

                    }
                }
            }
        };


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                City = city.getText().toString();
                if (TextUtils.isEmpty(City)) {

                    Toast.makeText(WeatherActivity.this, "Please enter a location", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(WeatherActivity.this, CityWeather.class);
                    intent.putExtra("CITY", City);
                    startActivity(intent);
                }
            }
        });

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    locationRequestCode);

        } else {
            // already permission granted

            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {

       
                @Override
                public void onSuccess(Location location) {

                     if(location != null) {
                         lat = location.getLatitude();
                         log = location.getLongitude();
                     }

                }
            });

        }


        //960c8c15419cbeb3426548ac17f6afeb
        //String url = "https://api.openweathermap.org/data/2.5/weather?q="+place+"&appid=439d4b804bc8187953eb36d2a8c26a02";

//        if(city!=null){
//            String url = "https://api.openweathermap.org/data/2.5/weather?q="+city+"&appid=439d4b804bc8187953eb36d2a8c26a02";
//        }
//        else{
//            String url = "https://api.openweathermap.org/data/2.5/weather?lat="+lat+"&lon="+log+"&appid=27a7aa100de9f1d5f7470c469d607c34";}
        String url = "https://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon=" + log + "&appid=960c8c15419cbeb3426548ac17f6afeb&units=metric";


        JsonObjectRequest json = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    JSONObject main_object = response.getJSONObject("main");
                    JSONArray array = response.getJSONArray("weather");
                    JSONObject object = array.getJSONObject(0);
                    String temp = String.valueOf(main_object.getDouble("temp"));
                    String description = object.getString("description");
                    String city = response.getString("name");
                    Log.d("city", city);
                    tv2.setText(city);
                    tv3.setText(temp);
                    tv4.setText(description);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(json);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1000: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


                    if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    Activity#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for Activity#requestPermissions for more details.
                        return;
                    }
                    fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location != null) {
                                lat = location.getLatitude();
                                log = location.getLongitude();

                            }
                            else
                            {
                                Toast.makeText(WeatherActivity.this, "Please enable your location service", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                startActivity(intent);
                            }
                        }
                    });


                  /* fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {

                        @Override
                        public void onComplete(@NonNull Task<Location> task) {
                            Location location = task.getResult();
                            lat = location.getLatitude();
                            log = location.getLongitude();

                        }
                    });   */
                } else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
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

