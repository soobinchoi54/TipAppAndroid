package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestaurantChooserActivity extends AppCompatActivity implements LocationListener{


    static final String TAG = MainActivity.class.getSimpleName();
    static final String BASE_URL = "https://api.yelp.com/v3/";
    static Retrofit retrofit = null;
    final static String AUTHORIZATION = "Bearer -x0Ch81E1speGUPYFA0uw3VQ9wpF7uWvBi9PZpX8GxDOBBAiGI8zFU22wdPfAbI9DuvAFeEfB9j1UxoaKGDQ9X-_zsIMq7Z0MlAGx6VZ-bK0dYSN_zRtlIKudZNLXnYx";
    private RecyclerView recyclerView;
    private double latitude;
    private double longitude;
    private LocationManager locationManager;
    private Location location;
    int TAG_CODE_PERMISSION_LOCATION;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_chooser);
        getLocation();
        System.out.println("latitude: " + latitude + " longitude: " + longitude);
        showRestaurantList();

    }

    @Override
    protected void onStart(){
        super.onStart();

    }

    private void showRestaurantList() {
        //prepare new retrofit
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        //set up api service
        RestaurantApiService restaurantApiService = retrofit.create(RestaurantApiService.class);
        //get location to update api service
        //33.6405 | 117.8443  , 37.80587 | -122.42058
        Call<RestaurantList> call = restaurantApiService.getRestaurantList(AUTHORIZATION, latitude, longitude, 17000);
        call.enqueue(new Callback<RestaurantList>() {
            @Override
            public void onResponse(Call<RestaurantList> call, Response<RestaurantList> response) {
                //get list of top rated movies response
                List<Restaurant> restaurantList = (response.body().getRestaurantList());
                //get recycler view id and adjust layout similar to program list activity
                recyclerView = findViewById(R.id.rvRestaurantList);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(RestaurantChooserActivity.this));
                recyclerView.setAdapter(new RestaurantListAdapter(restaurantList));
                System.out.println("FOUND DATA!");
            }
            @Override
            public void onFailure(Call<RestaurantList> call, Throwable throwable) {
                System.out.println("COULD NOT FIND DATA");
                Log.e(TAG, throwable.toString());
            }
        });

    }

    protected void getLocation() {

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this,
                        android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            if (location != null) {
                Log.e("TAG", "GPS is on");
                /*latitude = location.getLatitude();
                longitude = location.getLongitude();*/
                latitude = location.getLatitude();
                longitude = location.getLongitude();


            }
            else{
                //This is what you need:
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, this);
            }
        }
        else
        {
            System.out.println("Permission to access GPS denied requesting permission...");
            ActivityCompat.requestPermissions(this, new String[] {
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION },
                    TAG_CODE_PERMISSION_LOCATION);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        // locationManager.removeUpdates(this);

    }

    @Override
    public void onLocationChanged(Location location) {

        locationManager.removeUpdates(this);

        latitude = location.getLatitude();
        longitude = location.getLongitude();

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }


    //Moves to our current experience activity
    public void startExperience(View view){
        // TEMP
        Intent intent = new Intent(this, ExperienceActivity.class);
        startActivity(intent);
    }

    public void refreshLocation(View view){
        //getLocation();
        showRestaurantList();
    }
}