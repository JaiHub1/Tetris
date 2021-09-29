package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.media.MediaPlayer;

public class MainActivity extends AppCompatActivity {

   private Button derecha;
   private Button izquierda;
   private Button girar;
   private Button jugar;
    private Juego juego;
    private boolean pausar = true;
    private MediaPlayer pantalla;
    private int pararpantalla;

   private TextView puntos;


   private Tablero tablajuego= new Tablero();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pantalla = new MediaPlayer();



        jugar = (Button) findViewById(R.id.jugar);
        girar = (Button) findViewById(R.id.girar);
        derecha = (Button) findViewById(R.id.derecha);
        izquierda = (Button) findViewById(R.id.izquierda);
        puntos= (TextView) findViewById(R.id.puntos);

        juego = new Juego(this, tablajuego);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(480, 900);
        juego.setLayoutParams(params);
        LinearLayout relativeTetris = (LinearLayout) findViewById(R.id.linearLayout);
        juego.setBackgroundColor(Color.GRAY);
        relativeTetris.addView(juego);
        jugar.setOnClickListener(new View.OnClickListener()
          {
              int var =0;
              @Override
              public void onClick(View v) {
                  pararpantalla = pantalla.getCurrentPosition();
                  var++;

                  if(jugar.getText().equals("jugar")) {
                      pausar = false;

                      if(var ==1) {
                          pantalla.start();
                          pantalla.setLooping(true);
                      } else if(var >1) {
                          pantalla.seekTo(pararpantalla);
                          pantalla.start();
                      }
                  }


              }
          }



        );

    }

    public Button getDerecha() { return derecha;}
    public Button getIzquierda() { return izquierda;}
    public Button getGirar() { return girar; }
    public TextView getPuntos() { return puntos; }
    public MediaPlayer getPantalla() {  return pantalla; }

    public void onPause() {
        super.onPause();
        pausar = true;
        pantalla.stop();
    }
    public boolean getPausar() {  return pausar;}
    public void setPausar(boolean pause) { this.pausar=pause;}

}