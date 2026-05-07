package com.example.practica4.Modelo;

public class TableroShobu {
    Ficha[][] casillaTablero;

    public TableroShobu() {
        casillaTablero = new Ficha[4][4];
    }

    public Ficha getFicha(int fila, int col) {
        if (fila >= 0 && fila < 4 && col >= 0 && col < 4) {
            return casillaTablero[fila][col];
        }
        return null;
    }

    public void setFicha(int fila, int col, Ficha ficha) {
        if (fila >= 0 && fila < 4 && col >= 0 && col < 4) {
            casillaTablero[fila][col] = ficha;
        }
    }

    public Ficha[][] getcasillaTablero() {
        return casillaTablero;
    }
}
