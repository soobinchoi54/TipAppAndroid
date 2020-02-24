package com.example.myapplication;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface RestaurantApiService {
    @GET("businesses/search")
    Call<RestaurantList> getRestaurantList(@Header("Authorization") String authorization,
                                       @Query("latitude") Double latitude,
                                       @Query("longitude") Double longitude,
                                           @Query("radius") int radius);
}
