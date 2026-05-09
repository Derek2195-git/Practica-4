module com.example.practica4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.desktop;

    exports com.example.practica4.Modelo;
    opens com.example.practica4.Modelo to javafx.fxml;
    exports com.example.practica4.Vista;
    opens com.example.practica4.Vista to javafx.fxml;
    exports com.example.practica4.Controlador;
    opens com.example.practica4.Controlador to javafx.fxml;
}