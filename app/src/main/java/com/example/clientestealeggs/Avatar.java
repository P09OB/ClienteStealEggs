package com.example.clientestealeggs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Avatar extends AppCompatActivity implements View.OnClickListener{

    private ImageView jug1;
    private ImageView jug2;
    private int puerto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar);

       jug1 = findViewById(R.id.jugador1Img);
       jug2 = findViewById(R.id.jugador2Img);

       jug1.setOnClickListener(this);
       jug2.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.jugador1Img:

               puerto = 5000;

               break;

            case R.id.jugador2Img:

                puerto = 4000;

                break;
        }

        Intent i = new Intent(this,Conexion.class);
        startActivity(i);

        SharedPreferences preferences = getSharedPreferences("Cajon",MODE_PRIVATE);
        preferences.edit().putInt("puerto",puerto).apply();

    }
}