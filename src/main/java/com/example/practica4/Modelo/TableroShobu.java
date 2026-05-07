package com.example.practica4.Modelo;

public class TableroShobu {
    Ficha[][] casillaTablero;

    /**
     * Constructor de la clase tableroShobu
     */
    public TableroShobu() {
        casillaTablero = new Ficha[4][4];
    }

    /**
     * Getter para una ficha en el tablero
     * @param fila Fila de la ficha buscada
     * @param col Columna de la ficha buscada
     * @return Retorna la ficha encontrada, o un
     * dato de tipo null si la fila y columna estan fuera del rango del tablero
     */
    public Ficha getFicha(int fila, int col) {
        if (fila >= 0 && fila < 4 && col >= 0 && col < 4) {
            return casillaTablero[fila][col];
        }
        return null;
    }

    /**
     * Setter para una ficha en el tablero
     * @param fila Fila de la ficha a agregar
     * @param col Columna de la ficha a agregar
     * @param ficha Ficha a agregar al tablero
     */
    public void setFicha(int fila, int col, Ficha ficha) {
        if (fila >= 0 && fila < 4 && col >= 0 && col < 4) {
            casillaTablero[fila][col] = ficha;
        }
    }

    /**
     * Este metodo retorna todas las casillas de un tablero
     * @return Todos los elementos que hay en el tablero, sean fichas o valores nulos
     */
    public Ficha[][] getcasillaTablero() {
        return casillaTablero;
    }
}
