package com.example.myapplication;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

public class Restaurant {
    @SerializedName("name")
    private String name;
    @SerializedName("location")
    private JsonObject location;
    @SerializedName("coordinates.latitude")
    private Float latitude;
    @SerializedName("coordinates.longitude")
    private Float longitude;
    @SerializedName("price")
    private String price;

    public Restaurant(String name, JsonObject location, Float latitude, Float longitude, String price){
        this.name = name;
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
        this.price = price;
    }

    public String getName(){return name;}
    public String getAddress(){

        //Convert JsonObject to get particular array, then get information from that array
        JsonArray address = new JsonArray();
        address = this.location.getAsJsonArray("display_address");
        String result = "";
        for(JsonElement s : address){
            result += s + " ";
        }
        result = result.replace("\"", "");
        return result;}
    public Float getLatitude(){return latitude;}
    public Float getLongitude(){return longitude;}
    public String getPrice(){return price;}
}
