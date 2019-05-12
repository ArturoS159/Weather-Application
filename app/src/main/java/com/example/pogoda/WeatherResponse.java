package com.example.pogoda;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class WeatherResponse {
    @SerializedName("main")
    private Weather main;



    @SerializedName("weather")
    ArrayList<Weather> weather = new ArrayList<>();

    public Weather getMain() {
        return main;
    }

    public ArrayList<Weather> getWeather() {
        return weather;
    }
}

