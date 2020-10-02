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

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button nextButton;
    private EditText codigoText;
    private String codigo1;
    private boolean verificaCodigo,buscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nextButton = findViewById(R.id.nextButton);
        codigoText = findViewById(R.id.codigoText);
        nextButton.setOnClickListener(this);
        buscar = true;


    }

    public void ping(){
        new Thread(

                ()-> {

                    while (buscar) {

                              try {
                                InetAddress ine = InetAddress.getByName("192.168.0." + codigo1);
                                verificaCodigo = ine.isReachable(1000);
                                Log.e("boolean",""+verificaCodigo);


                            } catch (UnknownHostException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }



                    }

                }

        ).start();
    }

    @Override
    public void onClick(View view) {

        codigo1 = codigoText.getText().toString();
        ping();


        if(codigo1 != null) {

            if (verificaCodigo == true) {


                SharedPreferences preferences = getSharedPreferences("Cajon", MODE_PRIVATE);
                preferences.edit().putString("codigo1", codigo1).apply();

                Intent i = new Intent(this, Avatar.class);
                startActivity(i);
            }

            if (verificaCodigo == false) {
                Toast.makeText(this, "El código digitado no correponde", Toast.LENGTH_LONG).show();
            }

        }

    }



}