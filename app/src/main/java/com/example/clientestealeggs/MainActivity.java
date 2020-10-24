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
    private boolean verificaCodigo, buscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nextButton = findViewById(R.id.nextButton);
        codigoText = findViewById(R.id.codigoText);
        nextButton.setOnClickListener(this);
        buscar = true;


    }

    public void ping() {
        codigo1 = codigoText.getText().toString();

        new Thread(

                () -> {

                    for (int i = 0; i < 4; i++) {

                        try {
                            InetAddress ine = InetAddress.getByName("192.168.0." + codigo1);
                            verificaCodigo = ine.isReachable(1000);
                            if (verificaCodigo == true) {
                                break;
                            }

                        } catch (UnknownHostException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }

                    runOnUiThread(
                            () -> {
                                validar();
                            }
                    );

                }


        ).start();
    }

    public void validar() {

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


    @Override
    public void onClick(View view) {
        ping();
    }


}