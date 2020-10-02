package com.example.clientestealeggs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Conexion extends AppCompatActivity implements View.OnClickListener, OnMessageListener{


    private TCPSingleton tcp;
    private int puerto;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conexion);

        button = findViewById(R.id.button);
        button.setOnClickListener(this);
        //El ip que nos envio MainActivity
        SharedPreferences preferences= getSharedPreferences("Cajon",MODE_PRIVATE);
        String codigo = preferences.getString("codigo1","NO_CODIGO");
        puerto = preferences.getInt("puerto",0);

        tcp = TCPSingleton.getInstance();
        tcp.setCodigo(codigo);
        tcp.setPuerto(puerto);
        Log.e("imprimamos",""+tcp.getPuerto());
        Log.e("imprimamos",""+tcp.getCodigo());
        tcp.setObservador(this);

    }


    @Override
    public void onClick(View view) {

        if(puerto == 5000){
            tcp.enviar("Jugador1");
        } else if (puerto == 4000){
            tcp.enviar("Jugador2");
        }

    }

    @Override
    public void recibirMensaje(String mensaje) {

        runOnUiThread(
                ()->{
                    Toast.makeText(this,""+mensaje,Toast.LENGTH_LONG).show();
                }
        );


        Log.e("CLIENTE",""+mensaje);
    }
}

