package com.example.practica4.Modelo;

public class Ficha {
    String color;

    /**
     * Constructor de la clase
     * @param color Color de la ficha
     */
    public Ficha(String color) {
        this.color = color;
    }

    /**
     * Este metodo regresa el color de la ficha
     * @return Color de la ficha
     */
    public String getColor() {
        return color;
    }

    /**
     * Metodo toString de la Ficha
     * @return Representación de la ficha como un string
     */
    @Override
    public String toString() {
        return "Ficha de color " + color;
    }

}
