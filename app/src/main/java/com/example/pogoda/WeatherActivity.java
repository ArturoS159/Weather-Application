package com.example.pogoda;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class WeatherActivity extends AppCompatActivity {
    private TextView cityName,temp,maxTemp,minTemp,pressure,humidity,time;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        cityName = findViewById(R.id.textViewNameCity);
        temp = findViewById(R.id.textViewTemp);
        maxTemp = findViewById(R.id.textViewTempMax);
        minTemp = findViewById(R.id.textViewTempMin);
        pressure = findViewById(R.id.textViewPressure);
        time = findViewById(R.id.textViewTime);
        humidity = findViewById(R.id.textViewHumidity);

        final String API = "749561a315b14523a8f5f1ef95e45864";
        final String UNITS = "METRIC";
        Intent intent = getIntent();
        String text1 = intent.getStringExtra("City");

        cityName.setText(text1);



        LocalTime lt = LocalTime.now();
        DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH:mm");
        time.setText(lt.format(tf));
        getContent(text1, API,UNITS);

    }
        private void getContent(String city, String API, String UNITS){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://api.openweathermap.org")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            JsonPlaceholderAPI jsonPlaceholderAPI = retrofit.create(JsonPlaceholderAPI.class);
            Call<WeatherResponse> call = jsonPlaceholderAPI.getWeather(city,API,UNITS);

            call.enqueue(new Callback<WeatherResponse>() {
                @Override
                public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                    if(response.isSuccessful())
                    {
                        temp.setText(response.body().getMain().getTemp() + " C");
                        pressure.setText(response.body().getMain().getPressure() + "hPa");
                        minTemp.setText(response.body().getMain().getTempMin() + " C");
                        maxTemp.setText(response.body().getMain().getTempMax() + " C");
                        humidity.setText(response.body().getMain().getHumidity() + " %");

                    }else {
                        Intent returnBtn = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(returnBtn);
                    }

                }

                @Override
                public void onFailure(Call<WeatherResponse> call, Throwable t) {
                   temp.setText(t.getMessage());
                }
            });


    }



}

