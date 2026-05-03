package com.example.practica4.Modelo;


public class ContenedorMovimientosMaquina {
    public String tableroPasivo;
    public int pasivoFila1, pasivoColumna1;
    public int pasivoFila2, pasivoColumna2;

    public String tableroActivo;
    public int activoFila1, activoColumna1;
    public int activoFila2, activoColumna2;

    public ContenedorMovimientosMaquina(String tableroPasivo, int pasivoFila1, int pasivoColumna1, int pasivoFila2, int pasivoColumna2,
                                        String tableroActivo, int activoFila1, int activoColumna1, int activoFila2, int activoColumna2) {
        this.tableroPasivo = tableroPasivo;
        this.pasivoFila1 = pasivoFila1;
        this.pasivoColumna1 = pasivoColumna1;
        this.pasivoFila2 = pasivoFila2;
        this.pasivoColumna2 = pasivoColumna2;
        this.tableroActivo = tableroActivo;
        this.activoFila1 = activoFila1;
        this.activoColumna1 = activoColumna1;
        this.activoFila2 = activoFila2;
        this.activoColumna2 = activoColumna2;
    }
}


