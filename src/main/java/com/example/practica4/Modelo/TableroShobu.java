package com.example.practica4.Modelo;

public class TableroShobu {
    Ficha[][] fichasJugador;

    public TableroShobu() {
        fichasJugador = new Ficha[4][4];
    }

    public Ficha getFicha(int fila, int col) {
        if (fila >= 0 && fila < 4 && col >= 0 && col < 4) {
            return fichasJugador[fila][col];
        }
        return null;
    }

    public void setFicha(int fila, int col, Ficha ficha) {
        if (fila >= 0 && fila < 4 && col >= 0 && col < 4) {
            fichasJugador[fila][col] = ficha;
        }
    }

    public Ficha[][] getFichasJugador() {
        return fichasJugador;
    }

    public void setFichasJugador(Ficha[][] fichasJugador) {
        this.fichasJugador = fichasJugador;
    }
}
