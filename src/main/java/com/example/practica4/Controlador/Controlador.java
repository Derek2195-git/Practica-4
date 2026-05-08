package com.example.practica4.Controlador;

import com.example.practica4.Modelo.Ficha;
import com.example.practica4.Modelo.Jugador;
import com.example.practica4.Modelo.ContenedorMovimientosMaquina;
import com.example.practica4.Modelo.Shobu;
import com.example.practica4.Vista.VistaShobu;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

public class Controlador {
    @FXML

    private String tableroSeleccionado = null;
    private int filaSeleccionada = -1;
    private int colSeleccionada = -1;
    private boolean hayFichaSeleccionada = false;
    private Shobu juego;
    private VistaShobu vista;
    private boolean esFasePasiva = true;
    private String colorTableroPasivo = "";
    private int movimientoFichaX;
    private int movimientoFichaY;

    /**
     * Metodo para iniciar el juego
     * @param args Argumentos para el main
     */
    public static void main(String[] args) {
        Application.launch(VistaShobu.class, args);
        Controlador controlador;
    }

    /**
     * Setter del juego, conecta el juego con la vista
     * @param juego Objeto de la clase Shobu
     */
    public void setJuego(Shobu juego) {
        this.juego = juego;
    }

    /**
     * Setter de la vista, conecta el controlador con la vista
     * @param vista Objeto de la clase VistaShobu
     */
    public void setVista(VistaShobu vista) {
        this.vista = vista;
    }

    /**
     * Este metodo sirve para comprobar el clic realizado en una casilla
     * @param llaveTablero Nombre del tablero seleccionado por el usuario
     * @param fila Fila seleccionada por el usuario
     * @param col Columna seleccionada por el usuario
     */
    public void comprobarClicksEnCasillas(String llaveTablero, int fila, int col){
        if (juego.verificarGanador() != null) return;

        Ficha fichaSeleccionada = juego.getTableros().get(llaveTablero).getFicha(fila, col);

        boolean esFichaDelJugador = (fichaSeleccionada != null &&
                fichaSeleccionada.getColor().equalsIgnoreCase(juego.getJugadorActual().getColor()));

        if (esFichaDelJugador) {
            if (esFasePasiva) {
                boolean esLadoJugador1 = juego.getJugadorActual().getNombre().equals(juego.getJugador1().getNombre());
                boolean esTableroPropio = llaveTablero.contains("propio");
                if ((esLadoJugador1 && esTableroPropio) || (!esLadoJugador1 && !esTableroPropio)) {
                    seleccionarCasilla(llaveTablero, fila, col);
                    vista.cambiarTextoTurnoActual( "Turno de: " + juego.getJugadorActual().getNombre());
                } else {
                    vista.mostrarAlerta(Alert.AlertType.WARNING, "Tablero incorrecto", "En la fase pasiva debes elegir un tablero de tu lado.");
                }
            } else {
                if (!llaveTablero.split("_")[0].equalsIgnoreCase(colorTableroPasivo)) {
                    seleccionarCasilla(llaveTablero, fila, col);
                    vista.cambiarTextoTurnoActual("Turno de: " + juego.getJugadorActual().getNombre());
                } else {
                    vista.mostrarAlerta(Alert.AlertType.WARNING, "Tablero incorrecto", "En la fase activa debes " +
                            "elegir un tablero de distinto color al pasivo.");
                }
            }
        }
        else if (hayFichaSeleccionada) {
            moverFicha(llaveTablero, fila, col);
        }

    }

    /**
     * Este metodo sirve para seleccionar una casilla en la vista
     * @param llaveTablero Nombre del tablero seleccionado
     * @param fila Fila del tablero donde se selecciono la casilla
     * @param col Columna del tablero donde se selecciono la casilla
     */
    public void seleccionarCasilla(String llaveTablero, int fila, int col) {
        tableroSeleccionado = llaveTablero;
        filaSeleccionada = fila;
        colSeleccionada = col;
        hayFichaSeleccionada = true;
        vista.actualizarVista();
    }

    /**
     * Este metodo sirve para mover la ficha
     * @param llaveTablero Nombre del tablero donde se va a mover la ficha
     * @param filaAMover Fila del tablero donde se realizará el movimiento
     * @param colAMover Columna del tablero donde se realizará el movimiento
     */
    public void moverFicha(String llaveTablero, int filaAMover, int colAMover) {
        boolean huboMovimiento = false;

        if (juego.esMovimientoValido(filaSeleccionada, colSeleccionada, filaAMover, colAMover) &&
                juego.noHayObstaculos(llaveTablero, filaSeleccionada, colSeleccionada, filaAMover, colAMover, esFasePasiva)) {

            if (esFasePasiva) {
                if (!juego.esMovimientoActivoValido(llaveTablero, filaSeleccionada, colSeleccionada, filaAMover, colAMover)) {
                    vista.mostrarAlerta(Alert.AlertType.ERROR, "Movimiento anulado", "No  puedes hacer este movimiento por que" +
                            "no hay forma de replicarlo en la fase activa");

                    hayFichaSeleccionada = false;
                    tableroSeleccionado = null;
                    vista.actualizarVista();
                    return;
                }

                movimientoFichaX = filaAMover - filaSeleccionada;
                movimientoFichaY = colAMover - colSeleccionada;
                colorTableroPasivo = llaveTablero.split("_")[0];

                juego.moverFicha(llaveTablero, filaSeleccionada, colSeleccionada, filaAMover, colAMover, esFasePasiva);

                esFasePasiva = false;
                huboMovimiento = true;

                vista.cambiarTextoTurnoActual("Turno de " + juego.getJugadorActual().getNombre());

            } else {
                if (filaAMover - filaSeleccionada == movimientoFichaX
                        && colAMover - colSeleccionada == movimientoFichaY) {
                    juego.moverFicha(llaveTablero, filaSeleccionada, colSeleccionada, filaAMover, colAMover, esFasePasiva);

                    esFasePasiva = true;
                    huboMovimiento = true;

                    Jugador ganador = juego.verificarGanador();
                    if (ganador != null) {
                        vista.cambiarTextoTurnoActual("Ganó: " + ganador.getNombre());
                    } else {
                        juego.cambiarTurno();
                        vista.cambiarTextoTurnoActual("Turno de " + juego.getJugadorActual().getNombre());

                        if (!juego.getJugadorActual().getHumano()) {
                            realizarTurnoMaquina();
                        }
                    }
                }
            }
        }

        if (!huboMovimiento && hayFichaSeleccionada) {
            vista.mostrarAlerta(Alert.AlertType.WARNING, "Movimiento invalido", "El movimiento realizado no " +
                    "es valido según las reglas");
        }

        hayFichaSeleccionada = false;
        tableroSeleccionado = null;
        vista.actualizarVista();
    }

    /**
     * Este metodo sirve para que la maquina realice una jugada
     */
    public void realizarTurnoMaquina() {
        ContenedorMovimientosMaquina jugada = juego.calcularMovimientoIA();

        if (jugada != null) {
            juego.moverFicha(jugada.getTableroPasivo(), jugada.getPasivoFila1(), jugada.getPasivoColumna1(), jugada.getPasivoFila2(), jugada.getPasivoColumna2(), true);

            juego.moverFicha(jugada.getTableroActivo(), jugada.getActivoFila1(), jugada.getActivoColumna1(), jugada.getActivoFila2(), jugada.getActivoColumna2(), false);

            Jugador ganador = juego.verificarGanador();

            if(ganador != null) {
                vista.cambiarTextoTurnoActual("Ganó la maquina!");
            } else {
                juego.cambiarTurno();
                vista.cambiarTextoTurnoActual("Turno de " + juego.getJugadorActual().getNombre());
            }

            vista.actualizarVista();
        }
    }

    /**
     * Este metodo retorna un booleano dependiendo si ya se seleccionó una ficha
     * @return Retorna un valor verdadero en caso de que ya haya una ficha seleccionada
     */
    public boolean getHayFichaSeleccionada() {
        return hayFichaSeleccionada;
    }

    /**
     * Este metodo retorna el tablero seleccionado por el usuario
     * @return Nombre del tablero seleccionado (Ej. Oscuro, Blanco)
     */
    public String getTableroSeleccionado() {
        return tableroSeleccionado;
    }

    /**
     * Este metodo retorna la fila seleccionada por el usuario
     * @return Fila del tablero seleccionado por el usuario
     */
    public int getFilaSeleccionada() {
        return filaSeleccionada;
    }

    /**
     * Este metodo retorna la columna seleccionada por el usuario
     * @return Columna el tablero seleccionado por el usuario
     */
    public int getColSeleccionada() {
        return colSeleccionada;
    }

    /**
     * Este metodo retorna la distancia horizontal a la que se va a mover la ficha
     * @return Distancia horizontal de la ficha a la casilla seleccionada, medida en casillas
     */
    public int getMovimientoFichaX() {
        return movimientoFichaX;
    }

    /**
     * Este metodo retorna la distancia vertical a la que se va a mover la ficha
     * @return Distancia vertical de la ficha a la casilla seleccionada, medida en casillas
     */
    public int getMovimientoFichaY() {
        return movimientoFichaY;
    }

    /**
     * Este metodo retorna si actualmente el juego se encuentra en la fase pasiva
     * @return Booleano si el juego aun sigue en la fase pasiva, si estuvieramos en la negativa seria un false
     */
    public boolean getEsFasePasiva() {
        return esFasePasiva;
    }

    /**
     * Este metodo retorna el color del tablero donde se realizó el movimiento pasivo
     * @return Color del tablero donde se movió la ficha
     */
    public String getColorTableroPasivo() {
        return colorTableroPasivo;
    }
}
