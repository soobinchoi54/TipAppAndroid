package com.example.myapplication.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RestaurantList {

    @SerializedName("businesses")
    private List<Restaurant> restaurantList;

    public RestaurantList(List<Restaurant> currentList){
        restaurantList = currentList;
    }

    public List<Restaurant> getRestaurantList() {return restaurantList;}
}
