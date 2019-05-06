package com.example.pogoda;

import com.google.gson.annotations.SerializedName;



public class WeatherResponse {
    @SerializedName("main")
    private Weather main;

    public Weather getMain() {
        return main;
    }


}

