module ru.nsu.gorin.lab3.lab3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires org.apache.logging.log4j;

    opens ru.nsu.gorin.lab3 to javafx.fxml;
    exports ru.nsu.gorin.lab3;
    exports ru.nsu.gorin.lab3.viewControllers.tabsControllers;
    opens ru.nsu.gorin.lab3.viewControllers.tabsControllers to javafx.fxml;
    exports ru.nsu.gorin.lab3.controllers;
    opens ru.nsu.gorin.lab3.controllers to javafx.fxml;
    exports ru.nsu.gorin.lab3.model;
    opens ru.nsu.gorin.lab3.model to javafx.fxml;
    exports ru.nsu.gorin.lab3.viewControllers;
    opens ru.nsu.gorin.lab3.viewControllers to javafx.fxml;
}