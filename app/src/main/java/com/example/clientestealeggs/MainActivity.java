package com.example.clientestealeggs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button nextButton;
    private TCPSingleton tcp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nextButton = findViewById(R.id.nextButton);


        tcp = TCPSingleton.getInstance();
        tcp.setCliente(this);

        nextButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        tcp.enviar("hola desde cliente");


        Intent i = new Intent(this,Conexion.class);
        startActivity(i);

    }

    public void recibirMensaje(String mensaje) {

        Log.e("CLIENTE",""+mensaje);


    }
}