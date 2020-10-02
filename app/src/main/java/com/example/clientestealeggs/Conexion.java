package com.example.clientestealeggs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Conexion extends AppCompatActivity implements View.OnClickListener, OnMessageListener{


    private TCPSingleton tcp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conexion);

        //El ip que nos envio MainActivity
        SharedPreferences preferences= getSharedPreferences("Cajon",MODE_PRIVATE);
        String codigo = preferences.getString("codigo1","NO_CODIGO");
        int puerto = preferences.getInt("puerto",0);



        //Se ejecuta cuando el usuario ingrese el ip correcto, ¿COMO SABER QUE NO ES EL IP CORRECTO, COMO OPTENEMOS EL DATO DEL IP DEL SERVIOR?
        tcp = TCPSingleton.getInstance();
        tcp.setCodigo(codigo);
        tcp.setPuerto(puerto);
        Log.e("imprimamos",""+tcp.getPuerto());
        Log.e("imprimamos",""+tcp.getCodigo());
        tcp.setCliente(this);



    }


    @Override
    public void onClick(View view) {



    }

    @Override
    public void recibirMensaje(String mensaje) {
        Log.e("CLIENTE",""+mensaje);
    }
}

