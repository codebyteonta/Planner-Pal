package org.codeneur.plannerpal.controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import org.codeneur.plannerpal.App;
import org.codeneur.plannerpal.globals.Globals;
import org.codeneur.plannerpal.models.Project;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {
    @FXML
    private BorderPane root;

    @FXML
    private Button addBtn;

    @FXML
    private Button exitBtn;

    @FXML
    private Button helpBtn;

    @FXML
    private ListView<String> listView;

    private final Globals globals = Globals.getInstance();

    public static ObservableList<String> projectNames =
            FXCollections.observableArrayList();

    private final ContextMenu contextMenu = new ContextMenu();
    private final MenuItem viewBtn = new MenuItem("View");
    private final MenuItem updateBtn = new MenuItem("Update");
    private final MenuItem deleteBtn = new MenuItem("Delete");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Project> list = globals.getProjects();
        projectNames.addAll(list.stream().map(Project::getTitle).toList());

        contextMenu.getItems().add(viewBtn);
        contextMenu.getItems().add(updateBtn);
        contextMenu.getItems().add(deleteBtn);
        listView.setItems(projectNames);
        listView.setContextMenu(contextMenu);

        //styling
        addBtn.getStyleClass().setAll("btn", "btn-info");
        exitBtn.getStyleClass().setAll("btn", "btn-danger");
        viewBtn.getStyleClass().setAll("btn", "btn-primary");
        updateBtn.getStyleClass().setAll("btn", "btn-primary");
        deleteBtn.getStyleClass().setAll("btn", "btn-danger");
        helpBtn.getStyleClass().setAll("btn", "btn-info");
        viewBtn.setStyle("-fx-text-fill: white");
        updateBtn.setStyle("-fx-text-fill: white");
        deleteBtn.setStyle("-fx-text-fill: white");
        listView.setPlaceholder(new Label("No Project In List"));

        Loader.load(root, "title-view.fxml");

        actions();
    }

    private void actions() {
        helpBtn.setOnAction(e -> Loader.load(root, "helper-view.fxml"));

        listView.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getClickCount() == 2) {
                String projectName = listView.getSelectionModel().getSelectedItem();
                if (projectName == null) {
                    return;
                }
                globals.setCurrentProject(projectName);
                globals.setProjectStatus("view");
                Loader.load(root, "manage-projects.fxml");
            }
        });

        viewBtn.setOnAction(e -> {
            String projectName = listView.getSelectionModel().getSelectedItem();
            if (projectName == null) {
                return;
            }
            globals.setCurrentProject(projectName);
            globals.setProjectStatus("view");
            Loader.load(root, "manage-projects.fxml");
        });

        addBtn.setOnAction(e -> {
            globals.setProjectStatus("add");
            Loader.load(root, "manage-projects.fxml");
        });

        updateBtn.setOnAction(e -> {
            String projectName = listView.getSelectionModel().getSelectedItem();
            if (projectName == null) {
                return;
            }
            globals.setProjectStatus("update");
            globals.setCurrentProject(projectName);
            Loader.load(root, "manage-projects.fxml");
        });

        deleteBtn.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Delete Project");
            alert.setContentText("Are you sure you want to delete?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                projectNames.remove(listView.getSelectionModel().getSelectedItem());
                listView.setItems(projectNames);
                Loader.load(root, "title-view.fxml");
            } else {
                alert.close();
            }
        });


        exitBtn.setOnAction(e -> Platform.exit());
    }
}