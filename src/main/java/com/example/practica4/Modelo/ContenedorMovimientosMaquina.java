package com.example.practica4.Modelo;

public class ContenedorMovimientosMaquina {
    private String tableroPasivo;
    private int pasivoFila1, pasivoColumna1;
    private int pasivoFila2, pasivoColumna2;

    private String tableroActivo;
    private int activoFila1, activoColumna1;
    private int activoFila2, activoColumna2;

    
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

    
    public String getTableroPasivo() {
        return tableroPasivo;
    }

    
    public int getPasivoFila1() {
        return pasivoFila1;
    }

    
    public int getPasivoColumna1() {
        return pasivoColumna1;
    }

    
    public int getPasivoFila2() {
        return pasivoFila2;
    }

    
    public int getPasivoColumna2() {
        return pasivoColumna2;
    }

    
    public String getTableroActivo() {
        return tableroActivo;
    }

    
    public int getActivoFila1() {
        return activoFila1;
    }

    
    public int getActivoColumna1() {
        return activoColumna1;
    }

    
    public int getActivoFila2() {
        return activoFila2;
    }

    
    public int getActivoColumna2() {
        return activoColumna2;
    }
}

