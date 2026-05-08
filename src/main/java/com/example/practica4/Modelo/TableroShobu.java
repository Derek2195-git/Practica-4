package com.example.practica4.Modelo;

public class TableroShobu {
    Ficha[][] tablero;

    
    public TableroShobu() {
        tablero = new Ficha[4][4];
    }

    
    public Ficha getFicha(int fila, int col) {
        if (fila >= 0 && fila < 4 && col >= 0 && col < 4) {
            return tablero[fila][col];
        }
        return null;
    }

    
    public void setFicha(int fila, int col, Ficha ficha) {
        if (fila >= 0 && fila < 4 && col >= 0 && col < 4) {
            tablero[fila][col] = ficha;
        }
    }

    
    public Ficha[][] getcasillaTablero() {
        return tablero;
    }
}
