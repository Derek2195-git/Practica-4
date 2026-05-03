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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean getHumano() {
        return esHumano;
    }

    public void setHumano(boolean esHumano) {
        this.esHumano = esHumano;
    }


}
