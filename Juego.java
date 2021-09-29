package com.example.proyecto;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import android.media.MediaPlayer;



public class Juego extends View implements View.OnClickListener{

    private MainActivity mainActivity;
    private MediaPlayer pantalla;
    private Tablero tablajuego;
    private Puntos puntos;
    private Button derecha;
    private Button izquierda;
    private Button girar;
    private Button jugar;
    private  Timer tiempo = new Timer();
    private Random random = new Random();
    private ArrayList<figuras> listapieza;
    private final int puntuacion=10;
    private TextView puntuacionactual;

    private int timerPeriod =250;




    public Juego(Context context, Tablero tablajuego){
        super(context);
        this.mainActivity = (MainActivity) context;
        this.tablajuego = tablajuego;
        listapieza = tablajuego.getPieceList();
        pantalla = mainActivity.getPantalla();

        puntos = new Puntos(context);
        puntuacionactual = mainActivity.getPuntos();
        puntuacionactual.append("0");

        girar = mainActivity.getGirar();
        derecha = mainActivity.getDerecha();

        izquierda = mainActivity.getIzquierda();

        girar .setOnClickListener(this);
        derecha.setOnClickListener(this);
        izquierda.setOnClickListener(this);
        inicio();

    }


    public void inicio() {

        tiempo.schedule(new TimerTask() {

            @Override
            public void run() {
                mainActivity.runOnUiThread(new TimerTask() {

                    @Override
                    public void run() {

                       // if(lose()==false ) {

                            tablajuego.moveDown(tablajuego.getCurrentPiece());

                           if (tablajuego.can_Move_Down(tablajuego.getCurrentPiece()) == false) {
                                int deletedRows = tablajuego.clearRows();
                                tablajuego.clearRows();
                                listapieza.remove(tablajuego.getCurrentPiece());
                                listapieza.add(new figuras(random.nextInt(7) + 1));


                                if (deletedRows> 0) {
                                    puntos.setPuntosac(puntos.getPuntosac() + deletedRows * puntuacion);
                                    int p = puntos.getPuntosac();


                                    puntuacionactual.setText("Points:" +" "+ p);

                                }

                            }
                            invalidate();
                        //}
                    }
                });
            }
        },  0, timerPeriod);
    }


    public boolean lose() {

        if( tablajuego.checklose(tablajuego.getCurrentPiece())==true ) {
            tiempo.cancel();
            listapieza.clear();
            tablajuego.clearGameBoard();
            mainActivity.setPausar(true);
            pantalla.stop();
            Intent intent = new Intent(this.getContext(), MainActivity2.class);
            getContext().startActivity(intent);
            return true;
        }
        return false;
    }

    public void mostrarjuegol() {
        Intent intent = new Intent(this.getContext(), MainActivity2.class);
        getContext().startActivity(intent);
    }



    @Override
    public void onClick(View view) {
        //if(mainActivity.getPausar()==false) {

            switch(view.getId()) {
                case R.id.derecha:
                    tablajuego.moveRight(tablajuego.getCurrentPiece());
                    invalidate();
                    break;
                case R.id.izquierda:
                    tablajuego.moveLeft(tablajuego.getCurrentPiece());
                    invalidate();
                    break;

                case R.id.girar:
                tablajuego.rotatePiece(tablajuego.getCurrentPiece());
                invalidate();
                break;

            }
        }
    //}
    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        Paint tablero = new Paint();

        for (int x = 0; x < tablajuego.getBoardHeight(); x++) {
            for (int y = 0; y < tablajuego.getBoardWidth(); y++) {

                int color  = tablajuego.codeToColor(x,y);
                tablero.setColor(color);
                canvas.drawRect(y*30, x*30, y*30+30, x*30+30,tablero);
            }
        }
    }

    public Timer getTimer() {
        return this.tiempo;
    }


}
