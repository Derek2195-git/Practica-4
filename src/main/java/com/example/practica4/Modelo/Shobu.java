package com.example.practica4.Modelo;

import com.example.practica4.Vista.VistaShobu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class Shobu {
    private HashMap<String, TableroShobu> tableros;

    private Jugador jugador1, jugador2, jugadorActual;
    private boolean ganadorDeclarado;

    public Shobu(Jugador jugador1, Jugador jugador2) {
        // Jugador 1 siempre será blancas y Jugador 2 será negras, por lo tanto, el siempre empezara
        this.jugador1 = jugador1;
        jugadorActual = jugador1;
        this.jugador2 = jugador2;
        this.tableros = new HashMap<>();

        inicializarTableros();
    }

    public void inicializarTableros() {
        String[] nombres = {"blanco_propio", "oscuro_propio", "blanco_opuesto", "oscuro_opuesto"};
        Arrays.stream(nombres)
                .forEach(nombreTablero -> {
                    TableroShobu tablero = new TableroShobu();

                    for (int rango = 0; rango < 4; rango++) {

                        tablero.setFicha(3, rango, new Ficha(jugador1.getColor()));
                        tablero.setFicha(0, rango, new Ficha(jugador2.getColor()));
                    }

                    tableros.put(nombreTablero, tablero);
                });
    }

    public boolean esMovimientoValido(int fila1, int columna1, int fila2, int columna2) {
        int distanciaFila = Math.abs(fila2 - fila1);
        int distanciaCol = Math.abs(columna2 - columna1);

        // Retornamos falso si nos movemos a la misma casilla
        if (distanciaFila == 0 && distanciaCol == 0) return false;

        // Como maximo, podemos movernos dos espacios
        if (distanciaFila > 2 || distanciaCol > 2) return false;

        // Ahora verificamos que el movimiento sea recto o diagonal
        return (distanciaFila == distanciaCol) || (distanciaCol == 0) || (distanciaFila == 0);
    }

    public boolean noHayObstaculos(String llaveTablero, int fila1, int columna1, int fila2, int columna2, boolean esFasePasiva) {
        TableroShobu tablero = tableros.get(llaveTablero);
        int direccionFilas;
        int direccionColumnas;

        direccionColumnas = Integer.compare(columna2, columna1);
        direccionFilas = Integer.compare(fila2, fila1);

        //if (columna2 > columna1) direccionColumnas = 1;
        //else if (columna2 < columna1) direccionColumnas = -1;

        int filaIntermedia = fila1 + direccionFilas;
        int columnaIntermedia = columna1 + direccionColumnas;

        if (Math.abs(fila2 - fila1) >= 2 || Math.abs(columna2 - columna1) >= 2) {
            if (tablero.getFicha(filaIntermedia, columnaIntermedia) != null) {
                // Si hay una ficha entre medio, no se puede mover nuestra ficha
                return false;
            }
        }
        // Por ultimo, revisamos que la posicion a la que moveremos tambien esta vacia
        Ficha fichaAMover = tablero.getFicha(fila2, columna2);
        if (esFasePasiva) {
            return fichaAMover == null;
        } else {
            if (fichaAMover == null) return true;

            if (fichaAMover.getColor().equalsIgnoreCase(jugadorActual.getColor())) return false;

            int filaDetras = fila2 + direccionFilas;
            int colDetras = columna2 + direccionColumnas;

            if (filaDetras >= 0 && filaDetras < 4 && colDetras >= 0 && colDetras < 4) {
                return tablero.getFicha(filaDetras, colDetras) == null;
            }

            return true;
        }
    }

    public void moverFicha(String llaveTablero, int fila1, int columna1, int fila2, int columna2, boolean esFasePasiva) {
        TableroShobu tablero = tableros.get(llaveTablero);
        Ficha fichaAMover = tablero.getFicha(fila1, columna1);
        Ficha fichaAEmpujar = tablero.getFicha(fila2, columna2);

        if (!esFasePasiva && fichaAEmpujar != null) {
            int direccionFilas;
            int direccionColumnas;

            direccionColumnas = Integer.compare(columna2, columna1);
            direccionFilas = Integer.compare(fila2, fila1);

            int filaFichaEmpujada = fila2 + direccionFilas;
            int colFichaEmpujada = columna2 + direccionColumnas;

            if (filaFichaEmpujada >= 0 && filaFichaEmpujada < 4 && colFichaEmpujada >= 0 && colFichaEmpujada < 4) {
                tablero.setFicha(filaFichaEmpujada, colFichaEmpujada, fichaAEmpujar);
            }
        }

        // Movemos la ficha y volvemos como nula la posicion de la ficha anterior
        tablero.setFicha(fila2, columna2, fichaAMover);
        tablero.setFicha(fila1, columna1, null);
    }

    public boolean hayGanador() {
        return ganadorDeclarado;
    }

    public Jugador getJugador1() {
        return jugador1;
    }

    public Jugador getJugadorActual() {
        return jugadorActual;
    }

    public HashMap<String, TableroShobu> getTableros() {
        return tableros;
    }

    public void cambiarTurno() {
        if (jugadorActual == jugador1) {
            jugadorActual = jugador2;
        } else jugadorActual = jugador1;

    }

    public boolean esCasillaDisponible(String llaveTablero, int fila, int col,
                                       int filaAMover, int columnaAMover, boolean esFasePasiva, int distanciaX, int distanciaY) {
        Ficha fichaAMover = tableros.get(llaveTablero).getFicha(filaAMover, columnaAMover);
        if (fichaAMover != null && fichaAMover.getColor().equalsIgnoreCase(jugadorActual.getColor())) {
            return false;
        }

        if (esFasePasiva) {
            if (fichaAMover != null) return false;

            return esMovimientoValido(fila, col, filaAMover, columnaAMover) &&
                    noHayObstaculos(llaveTablero, fila, col,
                            filaAMover, columnaAMover, esFasePasiva);
        } else {
            return (filaAMover - fila == distanciaX && columnaAMover - col == distanciaY) &&
                    noHayObstaculos(llaveTablero, fila, col, filaAMover, columnaAMover, esFasePasiva);
        }
    }

    // Un problema que me di cuenta es que no verifique que el movimiento
    // activo sea igual de valido como el pasivo y... pues para eso cree este metodo xd
    public boolean esMovimientoActivoValido(String llaveTablero, int fila1, int col1, int fila2, int col2) {
        int distanciaX = fila2 - fila1;
        int distanciaY = col2 - col1;

        String colorOpuesto = llaveTablero.split("_")[0].equalsIgnoreCase("blanco") ? "oscuro" : "blanco";
        String[] tablerosActivosValidos = {colorOpuesto + "_propio", colorOpuesto + "_opuesto"};

        return Arrays.stream(tablerosActivosValidos).anyMatch(nombreTablero -> {
            TableroShobu tablero = tableros.get(nombreTablero);

            for (int f = 0; f < 4; f++) {
                for (int c = 0; c < 4; c++) {
                    Ficha ficha = tablero.getFicha(f, c);
                    if (ficha != null && ficha.getColor().equalsIgnoreCase(jugadorActual.getColor())) {
                        int destinoFila = f + distanciaX;
                        int destinoCol = c + distanciaY;

                        if (destinoFila >= 0 && destinoFila < 4
                                && destinoCol >= 0 && destinoCol < 4) {
                            if (esCasillaDisponible(nombreTablero, f, c, destinoFila, destinoCol, false, distanciaX, distanciaY)) {
                                return true;
                            }
                        }
                    }
                }

            }
            return false;

        });
    }

    public Jugador verificarGanador() {
        for (TableroShobu tablero : tableros.values()) {
            int fichasJugador1;
            int fichasJugador2;

            fichasJugador1 = Arrays.stream(tablero.getcasillaTablero())
                    //flatMap
                    .mapToInt(fila -> (int) Arrays.stream(fila)
                            .filter(ficha -> ficha != null && ficha.getColor().equalsIgnoreCase(jugador1.getColor()))
                            .count())
                    .sum();


            fichasJugador2 = Arrays.stream(tablero.getcasillaTablero())
                    //flatMap
                    .mapToInt(fila -> (int) Arrays.stream(fila)
                            .filter(ficha -> ficha != null && ficha.getColor().equalsIgnoreCase(jugador2.getColor()))
                            .count())
                    .sum();

            // Si un jugador se queda sin fichas, el otro gana
            if (fichasJugador1 == 0) {
                ganadorDeclarado = true;
                return jugador2;
            }
            if (fichasJugador2 == 0) {
                ganadorDeclarado = true;
                return jugador1;
            }
        }
        return null;
    }

    public ContenedorMovimientosMaquina calcularMovimientoIA() {
        ArrayList<ContenedorMovimientosMaquina> movimientosDisponibles = new ArrayList<>();


        Random rnd = new Random();

        buscarMovimientosPasivos(movimientosDisponibles);

        if (!movimientosDisponibles.isEmpty()) {
            return movimientosDisponibles.get(rnd.nextInt(movimientosDisponibles.size()));
        }

        return null;
    }

    public void buscarMovimientosPasivos(ArrayList<ContenedorMovimientosMaquina> listaMovimientos) {
        String[] tablerosCreados = {"blanco_propio", "oscuro_propio", "blanco_opuesto", "oscuro_opuesto"};
        for (String tableroPasivo : tablerosCreados) {
            TableroShobu pasivoActual = tableros.get(tableroPasivo);
            for (int pf1 = 0; pf1 < 4; pf1++) {
                for (int pc1 = 0; pc1 < 4; pc1++) {
                    Ficha fichaPasiva = pasivoActual.getFicha(pf1, pc1);

                    if (fichaPasiva != null && fichaPasiva.getColor().equalsIgnoreCase(jugadorActual.getColor())) {
                        for (int disF = -2; disF <= 2; disF++) {
                            for (int disC = -2; disC <= 2; disC++) {
                                int pf2 = pf1 + disF;
                                int pc2 = pc1 + disC;

                                if (pf2 >= 0 && pf2 < 4 && pc2 >= 0 && pc2 < 4) {
                                    if (esCasillaDisponible(tableroPasivo, pf1, pc1, pf2, pc2, true, disF, disC)) {
                                        buscarMovimientosActivos(tableroPasivo, pf1, pf2, pc1, pc2, disF, disC, listaMovimientos);
                                    }
                                }

                            }
                        }
                    }

                }
            }
        }
    }

    public void buscarMovimientosActivos(String tableroPasivo, int pf1, int pf2, int pc1, int pc2, int disF, int disC, ArrayList<ContenedorMovimientosMaquina> listaMovimientos4) {
        String colorOpuesto = tableroPasivo.split("_")[0].equalsIgnoreCase("blanco") ? "oscuro" : "blanco";
        String[] tablerosActivos = {colorOpuesto + "_propio", colorOpuesto + "_opuesto"};

        for (String tableroActivo : tablerosActivos) {
            TableroShobu activoActual = tableros.get(tableroActivo);

            for (int af1 = 0; af1 < 4; af1++) {
                for (int ac1 = 0; ac1 < 4; ac1++) {
                    Ficha fichaActiva = activoActual.getFicha(af1, ac1);

                    if (fichaActiva != null && fichaActiva.getColor().equalsIgnoreCase(jugadorActual.getColor())) {
                        int af2 = af1 + disF;
                        int ac2 = ac1 + disC;

                        if (af2 >= 0 && af2 < 4 && ac2 >= 0 && ac2 < 4) {
                            if (esCasillaDisponible(tableroActivo, af1, ac1, af2, ac2, false, disF, disC)) {
                                listaMovimientos4.add(new ContenedorMovimientosMaquina(
                                        tableroPasivo, pf1, pc1, pf2, pc2,
                                        tableroActivo, af1, ac1, af2, ac2
                                ));
                            }
                        }
                    }
                }

            }

        }
    }


}
