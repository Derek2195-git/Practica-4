package com.example.practica4.Modelo;

/**
 * Jugador para cada tablero del shobu
 */
public class Jugador {
    private String nombre;
    private String color;
    private boolean esHumano;

    /**
     * El constructor devuelve un nuevo jugador a partir de su nombre, color y si es jugador por una persona
     * @param nombre Nombre del jugador
     * @param color Color de las fichas del jugador
     * @param esHumano Comprobacion si el jugador va a ser jugador por la maquina
     */
    public Jugador(String nombre, String color, boolean esHumano) {
        this.nombre = nombre;
        this.color = color;
        this.esHumano = esHumano;
    }

    /**
     * Este metodo nos retorna el nombre del jugador
     * @return Nombre del jugador
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Este metodo nos retorna el color de las fichas del jugador
     * @return Color de las fichas que puede mover el jugador
     */
    public String getColor() {
        return color;
    }

    /**
     * Este metodo comprueba si el jugador es jugador por la maquina o una persona
     * @return Atributo que indica si la maquina esta jugando o no
     */
    public boolean getHumano() {
        return esHumano;
    }

}
