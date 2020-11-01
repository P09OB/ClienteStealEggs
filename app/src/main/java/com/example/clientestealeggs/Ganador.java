package com.example.clientestealeggs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.clientestealeggs.model.Coordenadas;
import com.example.clientestealeggs.model.Restart;
import com.google.gson.Gson;

import org.w3c.dom.Text;

public class Ganador extends AppCompatActivity implements View.OnClickListener, OnMessageListener {

    private TCPSingleton tcp;

    private ConstraintLayout fondo;
    private Button volver;
    private TextView estadoGanador;
    private TextView puntaje1;
    private TextView puntaje2;
    private boolean terminar = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ganador);

        fondo = findViewById(R.id.fondo);
        volver = findViewById(R.id.reiniciar);
        estadoGanador = findViewById(R.id.estadoGanador);
        puntaje1 = findViewById(R.id.puntaje1);
        puntaje2 = findViewById(R.id.puntaje2);

        volver.setOnClickListener(this);

        tcp = TCPSingleton.getInstance();
        tcp.setObservador(this);

        SharedPreferences preferences = getSharedPreferences("Cajon", MODE_PRIVATE);
        int puntajeRecibido1 = preferences.getInt("puntaje1",-1);
        int puntajeRecibido2 = preferences.getInt("puntaje2",-2);

        puntaje1.setText(""+puntajeRecibido1);
        puntaje2.setText(""+puntajeRecibido2);

        if(puntajeRecibido1 > puntajeRecibido2){
            fondo.setBackgroundColor(Color.parseColor("#EB5E55"));
            estadoGanador.setText("Ganador Jugador Rojo");
        }

        if(puntajeRecibido1 < puntajeRecibido2){
            fondo.setBackgroundColor(Color.parseColor("#9DD1FF"));
            estadoGanador.setText("Ganador Jugador Azul");
        }

        if(puntajeRecibido1 == puntajeRecibido2){
            fondo.setBackgroundColor(Color.parseColor("#637298"));
            estadoGanador.setText("Empate");
        }


    }


    @Override
    public void onClick(View view) {

        terminar = true;

        Restart restart = new Restart(terminar);
        Gson gson = new Gson();
        String json = gson.toJson(restart);

        tcp.enviar(json);

        if(terminar == true){

            Intent i = new Intent(this, Control.class);
            startActivity(i);


        }




    }

    @Override
    public void recibirMensaje(String mensaje) {



    }
}