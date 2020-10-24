package com.example.clientestealeggs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Conexion extends AppCompatActivity implements  OnMessageListener{


    private TCPSingleton tcp;
    private int puerto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conexion);

        //El ip que nos envio MainActivity
        SharedPreferences preferences = getSharedPreferences("Cajon", MODE_PRIVATE);
        String codigo = preferences.getString("codigo1","NO_CODIGO");
        puerto = preferences.getInt("puerto",0);

        tcp = TCPSingleton.getInstance();
        tcp.setCodigo(codigo);
        tcp.setPuerto(puerto);
        tcp.setObservador(this);

    }


    @Override
    public void recibirMensaje(String mensaje) {

        runOnUiThread(
                ()->{
                    if(mensaje.equals("falta")){
                        Toast.makeText(this,"Falta un jugador",Toast.LENGTH_LONG).show();

                    } else {
                        Intent i = new Intent(this,Control.class);
                        startActivity(i);
                    }
                }
        );


    }
}

