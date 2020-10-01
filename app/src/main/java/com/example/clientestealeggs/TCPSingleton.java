package com.example.clientestealeggs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPSingleton extends Thread {

    private static TCPSingleton instancia;

    private TCPSingleton(){}


    public static TCPSingleton getInstance(){

        if(instancia == null){
            instancia = new TCPSingleton();
            instancia.start();
        }
        return instancia;
    }

    private BufferedWriter writer;
    private Socket socket;
    private MainActivity observador;
    private String mensaje;


     //Suscripcion
    public void setCliente(MainActivity observador){
        this.observador = observador;
    }


    public void run(){

        try {

            //Conexion
            System.out.println("Enviando solicitud de conexion...");
            socket = new Socket("192.168.0.42",5000);
            System.out.println("Conectamos");


            //Declaracion

            InputStream is = socket.getInputStream();
            OutputStream out = socket.getOutputStream();

            writer = new BufferedWriter(new OutputStreamWriter(out));
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            //Recepcion

            while (true){

                mensaje = reader.readLine();
                observador.recibirMensaje(mensaje);

            }


        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void enviar(String mensaje){

        new Thread(

                ()->{

                    try {
                        writer.write(mensaje+ "/n");
                        writer.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

        ).start();
    }

    public String getMensaje(){
        return mensaje;
    }




}
