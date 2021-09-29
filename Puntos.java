package com.example.proyecto;

import android.content.Context;

public class Puntos {
    private int puntosac =0;
    private MainActivity mainActivity;

    public Puntos(Context context) {
        mainActivity = (MainActivity) context;
    }
    public void setPuntosac(int puntosac) {
        this.puntosac = puntosac;
    }

    public int getPuntosac() {
        return this.puntosac;
    }
}
