package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class InventoryController {

    @FXML private StackPane contentArea;  // The container for dynamic content

    // Handle Product Management button click
    @FXML
    private void handleProductManagement() {
        loadView("/fxml/ProductManagement.fxml");  // Load Product Management FXML
    }

    // Handle Material Management button click
    @FXML
    private void handleMaterialManagement() {
        loadView("/fxml/MaterialManagement.fxml");  // Load Material Management FXML
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
        // Optional: Show an alert for error handling
        System.out.println(title + ": " + message);
    }
}
