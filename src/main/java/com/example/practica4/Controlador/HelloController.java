package com.example.practica4.Controlador;

import com.example.practica4.Modelo.Ficha;
import com.example.practica4.Modelo.Jugador;
import com.example.practica4.Modelo.Shobu;
import com.example.practica4.Vista.HelloApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

public class HelloController {
    @FXML

    private String tableroSeleccionado = null;
    private int filaSeleccionada = -1;
    private int colSeleccionada = -1;
    private boolean hayFichaSeleccionada = false;
    private Shobu juego;
    private HelloApplication vista;
    private boolean esFasePasiva = true;
    private String colorTableroPasivo = "";
    private int movimientoFichaX;
    private int movimientoFichaY;

    public void setJuego(Shobu juego) {
        this.juego = juego;
    }

    public void setVista(HelloApplication vista) {
        this.vista = vista;
    }

    public void comprobarClicksEnCasillas(String llaveTablero, int fila, int col) {
        if (juego.verificarGanador() != null) return;

        Ficha fichaSeleccionada = juego.getTableros().get(llaveTablero).getFicha(fila, col);

        boolean esFichaDelJugador = (fichaSeleccionada != null &&
                fichaSeleccionada.getColor().equalsIgnoreCase(juego.getJugadorActual().getColor()));

        if (esFichaDelJugador) {
            if (esFasePasiva) {
                boolean esLadoJugador1 = juego.getJugadorActual().getNombre().equals("Jugador1");
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
                    vista.mostrarAlerta(Alert.AlertType.WARNING, "Tablero incorrecto", "En la fase activa debes" +
                            "elegir un tablero de distinto color al pasivo.");
                }
            }
        }
        else if (hayFichaSeleccionada) {
            moverFicha(llaveTablero, fila, col);
        }

    }

    public void seleccionarCasilla(String llaveTablero, int fila, int col) {
        tableroSeleccionado = llaveTablero;
        filaSeleccionada = fila;
        colSeleccionada = col;
        hayFichaSeleccionada = true;
        vista.actualizarVista();
    }

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

    public boolean getHayFichaSeleccionada() {
        return hayFichaSeleccionada;
    }

    public String getTableroSeleccionado() {
        return tableroSeleccionado;
    }

    public int getFilaSeleccionada() {
        return filaSeleccionada;
    }

    public int getColSeleccionada() {
        return colSeleccionada;
    }

    public int getMovimientoFichaX() {
        return movimientoFichaX;
    }

    public int getMovimientoFichaY() {
        return movimientoFichaY;
    }

    public boolean getEsFasePasiva() {
        return esFasePasiva;
    }

    public String getColorTableroPasivo() {
        return colorTableroPasivo;
    }
}
