package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.StackPane;
import java.io.IOException;

public class DashboardController {

    @FXML private StackPane contentArea;  // The container for dynamic content

    // Handle Materials button click
    @FXML
    private void handleMaterials() {
        loadView("/fxml/ManageMaterials.fxml");  // Load ManageMaterials FXML
    }

    // Handle Products button click
    @FXML
    private void handleProducts() {
        loadView("/fxml/ManageProducts.fxml");  // Load ManageProducts FXML
    }

    // Handle Orders button click
    @FXML
    private void handleOrders() {
        loadView("/fxml/Orders.fxml");  // Load Orders FXML
    }

    // Handle Storage button click
    @FXML
    private void handleStorage() {
        loadView("/fxml/Storage.fxml");  // Load Storage FXML
    }

    // Handle Users button click
    @FXML
    private void handleUsers() {
        loadView("/fxml/AdminDashboard.fxml");  // Load AdminDashboard FXML for user management
    }

    // General method for loading views dynamically
    private void loadView(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Node view = loader.load();  // Load the FXML into a Node
            contentArea.getChildren().setAll(view);  // Update the content area with the new view
        } catch (IOException e) {
            e.printStackTrace();
            showErrorDialog("Error loading view", "An error occurred while loading the view: " + fxmlPath);
        }
    }

    // Method to show error dialog to the user
    private void showErrorDialog(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText("Error");
        alert.setContentText(message);
        alert.showAndWait();  // Show the alert and wait for the user to close it
    }
}
