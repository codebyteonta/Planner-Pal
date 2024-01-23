package org.codeneur.plannerpal.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.codeneur.plannerpal.globals.Globals;
import org.codeneur.plannerpal.models.*;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class ManageProjectsController implements Initializable {

    public Label requirementAnalysisLbl;
    public Label designingLbl;
    public Label codingLbl;
    public Label projectManagementLbl;
    public Label testingLbl;
    public ComboBox<String> lifeCycleComboBox;
    public TextField hoursTf;
    public Button addHourBtn;
    public VBox effortPane;
    public HBox addHoursPane;
    @FXML
    private Button addProjectBtn;

    @FXML
    private TextArea descriptionTf;

    @FXML
    private TextField pmTf;

    @FXML
    private TextField projectNameTf;

    @FXML
    private TableView<RiskTableItem> riskTableVIew;

    @FXML
    private ListView<String> teamMembersList;

    @FXML
    private ListView<String> functionalListView;

    @FXML
    private ListView<String> nonFunctionalListView;

    @FXML
    private TableColumn<RiskTableItem, String> riskCol;

    @FXML
    private TableColumn<RiskTableItem, String> statusCol;

    private ObservableList<RiskTableItem> riskTableItems;

    private Globals globals;

    private final ContextMenu teamMembersListContextMenu = new ContextMenu();
    private final ContextMenu riskTableContextMenu = new ContextMenu();

    private final ContextMenu functionalListContextMenu = new ContextMenu();

    private final ContextMenu nonFunctionalListContextMenu = new ContextMenu();

    private final MenuItem teamMembersAddBtn = new MenuItem("Add");
    private final MenuItem teamMembersUpdateBtn = new MenuItem("Update");
    private final MenuItem teamMembersDeleteBtn = new MenuItem("Delete");
    private final MenuItem riskTableAddBtn = new MenuItem("Add");
    private final MenuItem riskTableUpdateBtn = new MenuItem("Update");
    private final MenuItem riskTableDeleteBtn = new MenuItem("Delete");

    private final MenuItem functionalListAddBtn = new MenuItem("Add");
    private final MenuItem functionalListUpdateBtn = new MenuItem("Update");
    private final MenuItem functionalListDeleteBtn = new MenuItem("Delete");

    private final MenuItem nonFunctionalListAddBtn = new MenuItem("Add");
    private final MenuItem nonFunctionalListUpdateBtn = new MenuItem("Update");
    private final MenuItem nonFunctionalListDeleteBtn = new MenuItem("Delete");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //init
        globals = Globals.getInstance();
        riskTableItems = FXCollections.observableArrayList();
        lifeCycleComboBox.getItems().addAll(
                Arrays.stream(LifeCycle.values()).map(LifeCycle::toString).toArray(String[]::new)
        );

        //style
        addProjectBtn.getStyleClass().setAll("btn", "btn-primary");

        //placeholders
        teamMembersList.setPlaceholder(new Label("No Team Members In List"));
        riskTableVIew.setPlaceholder(new Label("No Risks In Table"));
        functionalListView.setPlaceholder(new Label("No Functionalities In List"));
        nonFunctionalListView.setPlaceholder(new Label("No Non-Functionalities In List"));

        //handle project status if it is view or update
        handleProjectStatus();

        //add buttons to context menus
        teamMembersListContextMenu.getItems().add(teamMembersAddBtn);
        teamMembersListContextMenu.getItems().add(teamMembersUpdateBtn);
        teamMembersListContextMenu.getItems().add(teamMembersDeleteBtn);
        riskTableContextMenu.getItems().add(riskTableAddBtn);
        riskTableContextMenu.getItems().add(riskTableUpdateBtn);
        riskTableContextMenu.getItems().add(riskTableDeleteBtn);
        functionalListContextMenu.getItems().add(functionalListAddBtn);
        functionalListContextMenu.getItems().add(functionalListUpdateBtn);
        functionalListContextMenu.getItems().add(functionalListDeleteBtn);
        nonFunctionalListContextMenu.getItems().add(nonFunctionalListAddBtn);
        nonFunctionalListContextMenu.getItems().add(nonFunctionalListUpdateBtn);
        nonFunctionalListContextMenu.getItems().add(nonFunctionalListDeleteBtn);
        teamMembersList.setContextMenu(teamMembersListContextMenu);
        riskTableVIew.setContextMenu(riskTableContextMenu);
        functionalListView.setContextMenu(functionalListContextMenu);
        nonFunctionalListView.setContextMenu(nonFunctionalListContextMenu);

        //refresh table
        refreshTable(riskTableItems);
        actions();
    }

    private void actions() {
        //Team members list actions
        teamMembersAddBtn.setOnAction(e -> addTeamMember());
        teamMembersUpdateBtn.setOnAction(e -> updateTeamMember());
        teamMembersDeleteBtn.setOnAction(e -> deleteTeamMember());

        //Risk table actions
        riskTableAddBtn.setOnAction(e -> addNewRisk());
        riskTableUpdateBtn.setOnAction(e -> updateRisk());
        riskTableDeleteBtn.setOnAction(e -> deleteRisk());

        //Requirements actions
        functionalListAddBtn.setOnAction(e -> addRequirement(functionalListView));
        functionalListUpdateBtn.setOnAction(e -> updateRequirement(functionalListView));
        functionalListDeleteBtn.setOnAction(e -> deleteRequirement(functionalListView));

        //Non-Functionalities actions
        nonFunctionalListAddBtn.setOnAction(e -> addRequirement(nonFunctionalListView));
        nonFunctionalListUpdateBtn.setOnAction(e -> updateRequirement(nonFunctionalListView));
        nonFunctionalListDeleteBtn.setOnAction(e -> deleteRequirement(nonFunctionalListView));

        addHourBtn.setOnAction(e -> {
            String lifeCycle = lifeCycleComboBox.getValue();
            int hours = Integer.parseInt(hoursTf.getText());
            LifeCycleHours[] lifeCycles = globals.getCurrentProject().getLifeCycleHours();
            switch (lifeCycle) {
                case "REQUIREMENT_ANALYSIS":
                    lifeCycles[0].addHours(hours);
                    requirementAnalysisLbl.setText("Requirement Analysis:" + lifeCycles[0].getHours());
                    break;
                case "DESIGN":
                    lifeCycles[1].addHours(hours);
                    designingLbl.setText("Designing:" + lifeCycles[1].getHours());
                    break;
                case "CODING":
                    lifeCycles[2].addHours(hours);
                    codingLbl.setText("Coding:" + lifeCycles[2].getHours());
                    break;
                case "TESTING":
                    lifeCycles[3].addHours(hours);
                    testingLbl.setText("Testing:" + lifeCycles[3].getHours());
                    break;
                case "PROJECT_MANAGEMENT":
                    lifeCycles[4].addHours(hours);
                    projectManagementLbl.setText("Project Management:" + lifeCycles[4].getHours());
                    break;
            }
        });

        addProjectBtn.setOnAction(e -> {
            String status = globals.getProjectStatus();

            if (projectNameTf.getText().isEmpty()) {
                alertBox("Empty Fields", "Empty Fields", "Please at least enter project name");
                return;
            }

            //check if status is added or update
            if (status.equals("add")) {
                //if status is add then all fields will be added to the project
                Project project = new Project();
                project.setTitle(projectNameTf.getText());
                project.setProjectManagerName(pmTf.getText());
                project.setDescription(descriptionTf.getText());
                project.setTeamMembers(teamMembersList.getItems());
                project.setRisks(riskTableItems);
                project.setFunctionalities(functionalListView.getItems());
                project.setNonFunctionalities(nonFunctionalListView.getItems());

                MainViewController.projectNames.add(project.getTitle());
                Globals globals = Globals.getInstance();
                globals.addProject(project);
                globals.setCurrentProject(project);

                alertBox("Project Added", "Project Added", "Project has been added successfully");
            } else if (status.equals("update")) {
                //if status is update then all fields will be updated to current project
                Project project = globals.getCurrentProject();
                updateLifeCycleHours(project);

                project.setTitle(projectNameTf.getText());
                project.setProjectManagerName(pmTf.getText());
                project.setDescription(descriptionTf.getText());
                project.setTeamMembers(teamMembersList.getItems());
                project.setRisks(riskTableItems);
                project.setFunctionalities(functionalListView.getItems());
                project.setNonFunctionalities(nonFunctionalListView.getItems());

                alertBox("Project Updated", "Project Updated", "Project has been updated successfully");
            }
        });
    }

    public void handleProjectStatus() {
        String status = globals.getProjectStatus();
        //check if status is view or update
        if (status.equals("view") || status.equals("update")) {
            //if status is view then disable all fields
            effortPane.setVisible(true);
            if (status.equals("view")) {
                addProjectBtn.setVisible(false);
                projectNameTf.setEditable(false);
                pmTf.setEditable(false);
                descriptionTf.setEditable(false);
                teamMembersList.setDisable(true);
                riskTableVIew.setDisable(true);
                addProjectBtn.setVisible(false);
                functionalListView.setDisable(true);
                nonFunctionalListView.setDisable(true);
                addHoursPane.setVisible(false);
            } else {
                //if status is update then enable all fields
                addProjectBtn.setVisible(true);
                projectNameTf.setEditable(true);
                pmTf.setEditable(true);
                descriptionTf.setEditable(true);
                teamMembersList.setDisable(false);
                riskTableVIew.setDisable(false);
                addProjectBtn.setText("Update");
                functionalListView.setDisable(false);
                nonFunctionalListView.setDisable(false);
                addHourBtn.setVisible(true);
            }

            //set values when status is view or update
            Project project = globals.getCurrentProject();
            projectNameTf.setText(project.getTitle());
            pmTf.setText(project.getProjectManagerName());
            descriptionTf.setText(project.getDescription());
            teamMembersList.setItems(FXCollections.observableArrayList(project.getTeamMembers()));
            riskTableItems.addAll(project.getRisks().stream().map(risk -> {
                RiskTableItem item = new RiskTableItem();
                item.setRisk(risk.getRisk());
                item.setRiskStatus(risk.getRiskStatus());
                return item;
            }).toList());
            functionalListView.setItems(FXCollections.observableArrayList(project.getFunctionalities()));
            nonFunctionalListView.setItems(FXCollections.observableArrayList(project.getNonFunctionalities()));
            updateLifeCycleHours(project);
            refreshTable(riskTableItems);
        }else{
            effortPane.setVisible(false);
        }
    }

    private void updateLifeCycleHours(Project project) {
        LifeCycleHours[] lifeCycles = project.getLifeCycleHours();
        requirementAnalysisLbl.setText("Requirement Analysis:" + lifeCycles[0].getHours());
        designingLbl.setText("Designing:" + lifeCycles[1].getHours());
        codingLbl.setText("Coding:" + lifeCycles[2].getHours());
        testingLbl.setText("Testing:" + lifeCycles[3].getHours());
        projectManagementLbl.setText("Project Management:" + lifeCycles[4].getHours());
    }

    //Requirements handle
    private void addRequirement(ListView<String> listView) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Add New Requirement");
        dialog.setHeaderText("Enter a new requirement:");
        dialog.setContentText("Requirement:");

        dialog.showAndWait().ifPresent(newItem -> listView.getItems().add(newItem));
    }

    private void updateRequirement(ListView<String> listView) {
        int selectedIndex = listView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            String currentItem = listView.getItems().get(selectedIndex);

            TextInputDialog dialog = new TextInputDialog(currentItem);
            dialog.setTitle("Edit Requirement");
            dialog.setHeaderText("Edit the selected Requirement:");
            dialog.setContentText("Requirement Name:");

            dialog.showAndWait().ifPresent(newItem -> listView.getItems().set(selectedIndex, newItem));
        }
    }

    private void deleteRequirement(ListView<String> listView) {
        int selectedIndex = listView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            listView.getItems().remove(selectedIndex);
        }
    }

    //Team Members List functions
    private void addTeamMember() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Add New Team Member");
        dialog.setHeaderText("Enter a new team member:");
        dialog.setContentText("Team Member Name:");

        dialog.showAndWait().ifPresent(newItem -> teamMembersList.getItems().add(newItem));
    }

    private void updateTeamMember() {
        int selectedIndex = teamMembersList.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            String currentItem = teamMembersList.getItems().get(selectedIndex);

            TextInputDialog dialog = new TextInputDialog(currentItem);
            dialog.setTitle("Edit Team Member");
            dialog.setHeaderText("Edit the selected Member:");
            dialog.setContentText("Member Name:");

            dialog.showAndWait().ifPresent(newItem -> teamMembersList.getItems().set(selectedIndex, newItem));
        }
    }

    private void deleteTeamMember() {
        int selectedIndex = teamMembersList.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            teamMembersList.getItems().remove(selectedIndex);
        }
    }

    /**
     * Refreshes the table view with the provided items.
     *
     * @param items The items to be displayed in the table view.
     */
    private void refreshTable(ObservableList<RiskTableItem> items) {
        this.riskCol.setCellValueFactory(new PropertyValueFactory<>("risk"));
        this.statusCol.setCellValueFactory(new PropertyValueFactory<>("riskStatus"));
        this.riskTableVIew.setItems(items);
    }

    //Risk Table functions
    private void addNewRisk() {
        Dialog<RiskTableItem> dialog = new Dialog<>();
        dialog.setTitle("Add New Row");
        dialog.setHeaderText("Enter values for the new row:");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        TextField textField = new TextField();
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll(
                Arrays.stream(RiskStatus.values()).map(RiskStatus::toString).toArray(String[]::new)
        );

        dialogHandle(dialog, grid, textField, comboBox);

        dialog.showAndWait().ifPresent(newItem -> {
            riskTableItems.add(newItem);
            refreshTable(riskTableItems);
        });
    }

    private void updateRisk() {
        int selectedIndex = riskTableVIew.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            RiskTableItem currentItem = riskTableVIew.getItems().get(selectedIndex);

            Dialog<RiskTableItem> dialog = new Dialog<>();
            dialog.setTitle("Edit Row");
            dialog.setHeaderText("Edit the selected row:");

            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);

            TextField textField = new TextField(currentItem.getRisk());
            ComboBox<String> comboBox = new ComboBox<>();
            comboBox.getItems().addAll(
                    Arrays.stream(RiskStatus.values()).map(RiskStatus::toString).toArray(String[]::new)
            );
            comboBox.setValue(currentItem.getRiskStatus());

            dialogHandle(dialog, grid, textField, comboBox);

            dialog.showAndWait().ifPresent(newItem -> {
                riskTableItems.set(selectedIndex, newItem);
                refreshTable(riskTableItems);
            });
        }
    }

    private void deleteRisk() {
        int selectedIndex = riskTableVIew.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            riskTableItems.remove(selectedIndex);
            refreshTable(riskTableItems);
        }
    }

    //Dialog handle on risk table
    private void dialogHandle(Dialog<RiskTableItem> dialog, GridPane grid, TextField textField, ComboBox<String> comboBox) {
        grid.add(new Label("Risk:"), 0, 0);
        grid.add(textField, 1, 0);
        grid.add(new Label("Risk Status"), 0, 1);
        grid.add(comboBox, 1, 1);

        dialog.getDialogPane().setContent(grid);

        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialog.setResultConverter(buttonType -> {
            if (buttonType == ButtonType.OK) {
                RiskTableItem item = new RiskTableItem();
                item.setRisk(textField.getText());
                item.setRiskStatus(comboBox.getValue());
                return item;
            }
            return null;
        });
    }

    private void alertBox(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}

