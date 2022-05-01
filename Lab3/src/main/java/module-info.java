module ru.nsu.gorin.lab3.lab3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens ru.nsu.gorin.lab3 to javafx.fxml;
    exports ru.nsu.gorin.lab3;
    exports ru.nsu.gorin.lab3.controller;
    opens ru.nsu.gorin.lab3.controller to javafx.fxml;
    exports ru.nsu.gorin.lab3.model;
    opens ru.nsu.gorin.lab3.model to javafx.fxml;
}