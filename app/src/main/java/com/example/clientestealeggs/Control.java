package com.example.clientestealeggs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.clientestealeggs.model.Coordenadas;
import com.example.clientestealeggs.model.GameState;
import com.google.gson.Gson;

import java.util.UUID;

public class Control extends AppCompatActivity implements View.OnClickListener, OnMessageListener, View.OnTouchListener {

    private TCPSingleton tcp;

    private Button derecha;
    private Button izquierda;
    private Button saltar;
    private Button rodar;
    private ConstraintLayout colorBackground;
    private TextView score1, score2, time;
    private int puerto;
    private boolean buttonPressed;
    private int x, y;
    private boolean dir = false;
    private boolean jump = false;
    private boolean steal = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);

        derecha = findViewById(R.id.rightButton4);
        izquierda = findViewById(R.id.leftButton3);
        saltar = findViewById(R.id.jumpButton);
        rodar = findViewById(R.id.stealbutton);
        colorBackground = findViewById(R.id.background);
        score1 = findViewById(R.id.score1);
        score2 = findViewById(R.id.score2);
        time = findViewById(R.id.time);

        derecha.setOnTouchListener(this);
        izquierda.setOnTouchListener(this);
        saltar.setOnTouchListener(this);
        rodar.setOnTouchListener(this);

        tcp = TCPSingleton.getInstance();
        tcp.setObservador(this);

        SharedPreferences preferences = getSharedPreferences("Cajon", MODE_PRIVATE);
        puerto = preferences.getInt("puerto", 0);

        if (puerto == 5000) {

            colorBackground.setBackgroundColor(Color.parseColor("#EB5E55"));

        } else if (puerto == 4000) {
            colorBackground.setBackgroundColor(Color.parseColor("#9DD1FF"));
            x = 870;
        }

    }

    @Override
    public void onClick(View view) {


    }

    @Override
    public void recibirMensaje(String mensaje) {

        Gson gson = new Gson();
        GameState gameState = gson.fromJson(mensaje, GameState.class);


        runOnUiThread(() -> score1.setText("" + gameState.getPuntaje1()));
        runOnUiThread(() -> score2.setText("" + gameState.getPuntaje2()));
        runOnUiThread(() -> score2.setText("" + gameState.getPuntaje2()));
        runOnUiThread(() -> time.setText("" + gameState.getTiempo()));


    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                buttonPressed = true;

                new Thread(

                        () -> {

                            while (buttonPressed) {

                                switch (view.getId()) {

                                    case R.id.rightButton4:


                                        if (x <= 840) {
                                            x += 10;
                                            dir = false;
                                        }

                                        break;
                                    case R.id.leftButton3:
                                        if (x >= 0) {

                                            x -= 10;


                                            dir = true;
                                        }
                                        break;

                                    case R.id.jumpButton:

                                        jump = true;

                                        break;

                                    case R.id.stealbutton:

                                        steal = true;

                                        break;

                                }


                                String id = UUID.randomUUID().toString();
                                Coordenadas coordenada = new Coordenadas(x, y, dir, steal, jump, id);
                                Gson gson = new Gson();
                                String json = gson.toJson(coordenada);

                                tcp.enviar(json);


                                try {
                                    Thread.sleep(100);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                            }


                        }

                ).start();

                break;

            case MotionEvent.ACTION_UP:

                buttonPressed = false;
                jump = false;


                break;
        }
        return false;


    }
}