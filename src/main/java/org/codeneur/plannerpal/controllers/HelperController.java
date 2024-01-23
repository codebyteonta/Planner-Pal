package org.codeneur.plannerpal.controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class HelperController implements Initializable {
    public Label helpLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        helpLabel.setText("01. To add project, There is \"Add +\" button on left upper corner.\n" +
                "02. To view project you can either double click on project from list or right click on the project.\n" +
                "03. Right click on project from list will show three option (view, update, delete).\n" +
                "04. To add team member right click on Team members List and select add button.\n" +
                "05. To update team member right click on Team members List and select update button.\n" +
                "06. To delete team member right click on Team members List and select delete button.\n" +
                "07. To add Functional Requirement right click on Functional Requirement List and select add button.\n" +
                "08. To update Functional Requirement right click on Functional Requirement List and select update button.\n" +
                "09. To delete Functional Requirement right click on Functional Requirement List and select delete button.\n" +
                "10. To add Non-Functional Requirement right click on Non-Functional Requirement List and select add button.\n" +
                "11. To update Non-Functional Requirement right click on Non-Functional Requirement List and select update button.\n" +
                "12. To delete Non-Functional Requirement right click on Non-Functional Requirement List and select delete button.\n" +
                "10. To add Risk Data right click on Risk Table and select add button.\n" +
                "11. To update Risk Data right click on Risk Table and select update button.\n" +
                "12. To delete Risk Data right click on Risk Table and select delete button.\n" +
                "13. To Add Life Cycle hours first create project and then right click on project from list and select update button.\n");
        helpLabel.setFont(javafx.scene.text.Font.font("Arial", 18));
    }
}
