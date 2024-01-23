module org.codeneur.plannerpal {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens org.codeneur.plannerpal to javafx.fxml;
    exports org.codeneur.plannerpal;
    exports org.codeneur.plannerpal.controllers;
    exports org.codeneur.plannerpal.models;
    opens org.codeneur.plannerpal.controllers to javafx.fxml;
}