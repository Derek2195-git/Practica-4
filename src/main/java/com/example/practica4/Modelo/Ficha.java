package com.example.practica4.Modelo;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ficha {
    String color;

    public Ficha(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Ficha de color " + color;
    }
}
