module ru.nsu.gorin.lab5.chat.lab5 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens ru.nsu.gorin.lab5.chat.lab5 to javafx.fxml;
    exports ru.nsu.gorin.lab5.chat.lab5;
}