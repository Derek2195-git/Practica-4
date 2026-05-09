package com.example.practica4.Vista;

import com.example.practica4.Controlador.Controlador;
import com.example.practica4.Modelo.Ficha;
import com.example.practica4.Modelo.Jugador;
import com.example.practica4.Modelo.Shobu;
import com.example.practica4.Modelo.TableroShobu;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;

public class VistaShobu extends Application {
    private Shobu juego;
    private Controlador controlador;
    VBox contenedorVertical;
    HBox contenedorCentral;
    Label labelTurnoActual;
    Label labelFaseActual;

    
    @Override
    public void start(Stage stage) {
        crearMenuPrincipal(stage);
    }

    
    public void crearMenuPrincipal(Stage stage) {
        VBox menu = new VBox(30);
        menu.setAlignment(Pos.CENTER);

        Label tituloShobu = new Label("Shobu");
        tituloShobu.getStyleClass().add("titulo");

        Button botonJcJ = new Button("Jugador vs Jugador");
        botonJcJ.setPrefSize(250, 50);
        botonJcJ.getStyleClass().add("button-menu");
        botonJcJ.setOnAction(event-> {
            try {
                iniciarJuego(stage, false);
            } catch (IOException e) {
                mostrarAlerta(Alert.AlertType.ERROR, "Error al crear la aplicación", e.toString());
            }
        });

        Button botonJcM = new Button("Jugador vs Maquina");
        botonJcM.setPrefSize(250, 50);
        botonJcM.getStyleClass().add("button-menu");
        menu.getStyleClass().add("menu");
        botonJcM.setOnAction(event -> {
            try {
                iniciarJuego(stage, true);
            } catch (IOException e) {
                mostrarAlerta(Alert.AlertType.ERROR, "Error al crear la aplicación", e.toString());
            }
        });

        menu.getChildren().addAll(tituloShobu, botonJcJ, botonJcM);

        Scene scene = new Scene(menu, 600, 600);
        scene.getStylesheets().add(getClass().getResource("/com/example/practica4/estilos.css").toExternalForm());

        stage.setTitle("Shobu");
        stage.setScene(scene);
        stage.show();
    }

    
    public void iniciarJuego(Stage stage, boolean contraMaquina) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(VistaShobu.class.getResource("/com/example/practica4/hello-view.fxml"));
        fxmlLoader.load();

        Jugador j1 = new Jugador("Jugador1", "Negras", true);
        Jugador j2;

        if (contraMaquina) {
            j2 = new Jugador("Maquina", "Blancas", false);
        } else {
            j2 = new Jugador("Jugador2", "Blancas", true);
        }

        juego = new Shobu(j1, j2);
        controlador = fxmlLoader.getController();
        controlador.setJuego(juego);
        controlador.setVista(this);

        contenedorCentral = new HBox(20);
        contenedorVertical = new VBox(10);
        HBox encabezado = new HBox(10);

        GridPane tableros = new GridPane();
        tableros.setHgap(20);
        tableros.setVgap(20);

        labelTurnoActual = new Label();
        labelTurnoActual.getStyleClass().add("subtitulo");
        labelFaseActual = new Label("Fase Pasiva");
        labelFaseActual.getStyleClass().add("estilo-fase-pasiva");

        actualizarVista();

        labelTurnoActual.setText("Turno de " + j1.getNombre());
        contenedorCentral.getChildren().addAll(tableros);
        contenedorCentral.setAlignment(Pos.CENTER);
        encabezado.setAlignment(Pos.CENTER);
        encabezado.getChildren().addAll(labelTurnoActual, labelFaseActual);
        contenedorVertical.getChildren().addAll(encabezado, contenedorCentral);
        contenedorVertical.getStyleClass().add("ventana-juego");
        contenedorVertical.setAlignment(Pos.CENTER);

        Scene scene = new Scene(contenedorVertical, 600, 600);

        scene.getStylesheets().add(getClass().getResource("/com/example/practica4/estilos.css").toExternalForm());
        stage.setTitle("Shobu");
        stage.setScene(scene);
        stage.show();
    }

    
    public GridPane crearTableros(String nombreTablero, TableroShobu tablero, Controlador controlador) {
        GridPane grid = new GridPane();
        grid.getStyleClass().add("tablero");

        for (int fila = 0; fila < 4; fila++) {
            for (int col = 0; col < 4; col++) {
                int filaActual = fila;
                int colActual = col;
                Button botonCasilla = new Button();
                botonCasilla.setPrefSize(60,60);

                Ficha fichaActual = tablero.getFicha(fila, col);

                if (fichaActual != null) {
                    Circle circuloFicha = new Circle(20);
                    if (fichaActual.getColor().equalsIgnoreCase("Negras")) {
                        circuloFicha.setFill(Color.BLACK);
                    } else {
                        circuloFicha.setFill(Color.WHITE);
                        circuloFicha.setStroke(Color.BLACK);
                        circuloFicha.setStrokeWidth(2);
                    }

                    botonCasilla.setGraphic(circuloFicha);
                    botonCasilla.setId(nombreTablero + "," + fila + "," + col);

                }
                if (controlador.getHayFichaSeleccionada() && nombreTablero.equalsIgnoreCase(controlador.getTableroSeleccionado())) {
                    if (juego.esCasillaDisponible(nombreTablero,
                            controlador.getFilaSeleccionada(), controlador.getColSeleccionada(),
                            fila, col, controlador.getEsFasePasiva(),
                            controlador.getMovimientoFichaX(), controlador.getMovimientoFichaY())) {
                        botonCasilla.getStyleClass().add("casilla-disponible");
                    }
                }
                botonCasilla.setOnMouseClicked(e ->
                        controlador.comprobarClicksEnCasillas(nombreTablero, filaActual, colActual));

                grid.add(botonCasilla, col, fila);
            }
        }

        return grid;
    }

    
    public void actualizarVista() {
        contenedorCentral.getChildren().clear();

        GridPane tableros = new GridPane();
        tableros.setHgap(20);
        tableros.setVgap(20);

        GridPane tableroBlancoOp = crearTableros("blanco_opuesto", juego.getTableros().get("blanco_opuesto"), controlador);
        GridPane tableroOscuroOp = crearTableros("oscuro_opuesto", juego.getTableros().get("oscuro_opuesto"), controlador);
        GridPane tableroBlancoPr = crearTableros("blanco_propio", juego.getTableros().get("blanco_propio"), controlador);
        GridPane tableroOscuroPr = crearTableros("oscuro_propio", juego.getTableros().get("oscuro_propio"), controlador);

        tableroBlancoOp.getStyleClass().add("tablero-claro");
        tableroOscuroOp.getStyleClass().add("tablero-oscuro");
        tableroBlancoPr.getStyleClass().add("tablero-claro");
        tableroOscuroPr.getStyleClass().add("tablero-oscuro");

        labelFaseActual.getStyleClass().removeAll("estilo-fase-pasiva", "estilo-fase-agresiva");

        if (controlador.getEsFasePasiva()) {

            if (!juego.hayGanador()) {
                labelFaseActual.setText("Fase Pasiva");
                labelFaseActual.getStyleClass().add("estilo-fase-pasiva");
            } else {
                labelFaseActual.setText("");
            }

            if (juego.getJugadorActual().getNombre().equalsIgnoreCase(juego.getJugador1().getNombre())) {
                tableroBlancoOp.getStyleClass().add("tablero-inactivo");
                tableroOscuroOp.getStyleClass().add("tablero-inactivo");
            } else {

                tableroBlancoPr.getStyleClass().add("tablero-inactivo");
                tableroOscuroPr.getStyleClass().add("tablero-inactivo");
            }
        } else {

            if (!juego.hayGanador()) {
                labelFaseActual.setText("Fase Agresiva");
                labelFaseActual.getStyleClass().add("estilo-fase-agresiva");
            } else {
                labelFaseActual.setText("");
            }

            String colorPasivo = controlador.getColorTableroPasivo();
            if (colorPasivo != null) {
                if (colorPasivo.equalsIgnoreCase("blanco")) {
                    tableroBlancoPr.getStyleClass().add("tablero-inactivo");
                    tableroBlancoOp.getStyleClass().add("tablero-inactivo");
                } else if (colorPasivo.equalsIgnoreCase("oscuro")){
                    tableroOscuroPr.getStyleClass().add("tablero-inactivo");
                    tableroOscuroOp.getStyleClass().add("tablero-inactivo");

                }
            }
        }

        tableros.add(tableroOscuroOp, 0, 0);
        tableros.add(tableroBlancoOp, 1, 0);
        tableros.add(tableroOscuroPr, 0, 1);
        tableros.add(tableroBlancoPr, 1, 1);

        tableros.setAlignment(Pos.CENTER);

        contenedorCentral.getChildren().add(tableros);
        contenedorCentral.setAlignment(Pos.CENTER);

    }

    
    public void cambiarTextoTurnoActual(String nuevoMensaje) {
        labelTurnoActual.setText(nuevoMensaje);
        labelTurnoActual.setAlignment(Pos.CENTER);
    }

    
    public void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

}
