package com.example.myapplication.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

public class Restaurant {
    @SerializedName("name")
    private String name;
    @SerializedName("image_url")
    private String imageURL;
    @SerializedName("location")
    private JsonObject location;
    @SerializedName("categories")
    private JsonArray categories;
    @SerializedName("price")
    private String price;

    /*@SerializedName("coordinates.latitude")
    private Float latitude;
    @SerializedName("coordinates.longitude")
    private Float longitude;*/

    public Restaurant(String name, String imageURL, JsonObject location, JsonArray categories,/*Float latitude, Float longitude,*/ String price){
        this.name = name;
        this.imageURL = imageURL;
        this.location = location;
        this.categories = categories;
        this.price = price;

        /*this.latitude = latitude;
        this.longitude = longitude;*/
    }

    public String getName(){return name;}

    public String getImageURL(){return imageURL;}

    public String getAddress(){

        //Convert JsonObject to get particular array, then get information from that array
        JsonArray address;
        address = this.location.getAsJsonArray("display_address");
        String result = "";
        for(JsonElement s : address){
            result += s + " ";
        }
        result = result.replace("\"", "");
        return result;}

    public String getCategories(){

        String result = "";
        for(JsonElement s : categories){
            JsonObject currentObject = s.getAsJsonObject();
            result += currentObject.get("title") + " | ";
            //System.out.println("Current result: " + result);

        }
        result = result.replace("\"", "");
        result = result.substring(0, result.length() - 2);
        return result;}


    public String getPrice(){return price;}

    /*public Float getLatitude(){return latitude;}
    public Float getLongitude(){return longitude;}*/
}
