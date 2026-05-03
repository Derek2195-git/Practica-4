package com.example.practica4.Modelo;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ficha {
    String color;
    int xPos, yPos;

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

    public void setColor(String color) {
        this.color = color;
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }
}
