package org.codeneur.plannerpal.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import org.codeneur.plannerpal.App;

import java.util.Objects;

public class Loader {
    public static void load(BorderPane borderPane, String fxml) {
        try {
            borderPane.setCenter(FXMLLoader.load(Objects.requireNonNull(App.class.getResource(fxml))));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
