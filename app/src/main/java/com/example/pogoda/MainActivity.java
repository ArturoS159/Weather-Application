package com.example.pogoda;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText text;
    private SharedPreferences shared;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = findViewById(R.id.editTextCity);
        shared = this.getPreferences(Context.MODE_PRIVATE);
        shared();
    }

    private void shared(){
        text.setText(shared.getString("City", ""));
    }

    public void checkTheWeather(View view) {
        SharedPreferences.Editor editor = shared.edit();
        editor.putString("city", text.getText().toString());
        editor.commit();

        Intent intent =new Intent(MainActivity.this,WeatherActivity.class);
        intent.putExtra("City",text.getText().toString());

        startActivity(intent);


    }
}
