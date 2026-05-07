package com.example.practica4.Modelo;


public class ContenedorMovimientosMaquina {
    private String tableroPasivo;
    private int pasivoFila1, pasivoColumna1;
    private int pasivoFila2, pasivoColumna2;

    private String tableroActivo;
    private int activoFila1, activoColumna1;
    private int activoFila2, activoColumna2;

    /**
     * Constructor para una clase que contiene los movimientos de la maquina
     * @param tableroPasivo Nombre del tablero donde se selecciono la ficha para moverla en la fase pasiva
     * @param pasivoFila1 Fila del tablero donde se selecciono la ficha para moverla en la fase pasiva
     * @param pasivoColumna1 Columna del tablero donde se selecciono la ficha para moverla en la fase pasiva
     * @param pasivoFila2 Fila del tablero a donde vamos a mover la ficha
     * @param pasivoColumna2 Columna del tablero a donde vamos a mover la ficha
     * @param tableroActivo Nombre del tablero donde se selecciono la ficha para moverla en la fase agresiva
     * @param activoFila1 Fila del tablero donde se selecciono la ficha para moverla en la fase agresiva
     * @param activoColumna1 Columna del tablero donde se selecciono la ficha para moverla en la fase agresiva
     * @param activoFila2 Fila del tablero a donde vamos a mover la ficha
     * @param activoColumna2 Columna del tablero a donde vamos a mover la ficha
     */
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

    /**
     * Esté metodo retorna el tablero donde se van a realizar los movimientos pasivos
     * @return Tablero donde se movio en la fase pasiva
     */
    public String getTableroPasivo() {
        return tableroPasivo;
    }

    /**
     * Este metodo retorna la fila donde se selecciono la ficha en la fase pasiva
     * @return Fila seleccionada en la fase pasiva
     */
    public int getPasivoFila1() {
        return pasivoFila1;
    }

    /**
     * Este metodo retorna la columna donde se selecciono la ficha en la fase pasiva
     * @return Columna seleccionada en la fase pasiva
     */
    public int getPasivoColumna1() {
        return pasivoColumna1;
    }

    /**
     * Este metodo retorna la fila a donde nos vamos a mover en la fase pasiva
     * @return Fila a donde se moverá la ficha en la fase pasiva
     */
    public int getPasivoFila2() {
        return pasivoFila2;
    }

    /**
     * Este metodo retorna la columna a donde nos vamos a mover en la fase pasiva
     * @return Columna a donde se moverá la ficha en la fase pasiva
     */
    public int getPasivoColumna2() {
        return pasivoColumna2;
    }

    /**
     * Esté metodo retorna el tablero donde se van a realizar los movimientos de la fase activa
     * @return Tablero donde se movio en la fase activa
     */
    public String getTableroActivo() {
        return tableroActivo;
    }

    /**
     * Este metodo retorna la fila donde se selecciono la ficha en la fase activa
     * @return Fila seleccionada en la fase activa
     */
    public int getActivoFila1() {
        return activoFila1;
    }

    /**
     * Este metodo retorna la columna donde se selecciono la ficha en la fase activa
     * @return Columna seleccionada en la fase activa
     */
    public int getActivoColumna1() {
        return activoColumna1;
    }

    /**
     * Este metodo retorna la fila a donde nos vamos a mover en la fase activa
     * @return Fila a donde se moverá la ficha en la fase activa
     */
    public int getActivoFila2() {
        return activoFila2;
    }

    /**
     * Este metodo retorna la columna a donde nos vamos a mover en la fase activa
     * @return Columna a donde se moverá la ficha en la fase activa
     */
    public int getActivoColumna2() {
        return activoColumna2;
    }
}


