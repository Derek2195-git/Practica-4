package com.example.practica4.Modelo;

public class Jugador {
    private String nombre;
    private String color;
    private boolean esHumano;

    
    public Jugador(String nombre, String color, boolean esHumano) {
        this.nombre = nombre;
        this.color = color;
        this.esHumano = esHumano;
    }

    
    public String getNombre() {
        return nombre;
    }

    
    public String getColor() {
        return color;
    }

    
    public boolean getHumano() {
        return esHumano;
    }

}
