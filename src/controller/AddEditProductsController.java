package controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AddEditProductsController {

    @FXML
    private TextField nameField;
    
    @FXML
    private TextArea descriptionField;
    
    @FXML
    private TextField quantityField;
    
    @FXML
    private TextField priceField;
    
    @FXML
    private TextField dateMadeField;

    @FXML
    private void handleSave() {
        // Get values from the fields
        String productName = nameField.getText();
        String description = descriptionField.getText();
        String quantityText = quantityField.getText();
        String priceText = priceField.getText();
        String dateMadeText = dateMadeField.getText();

        // Basic validation (you can expand this)
        if (productName.isEmpty() || quantityText.isEmpty() || priceText.isEmpty() || dateMadeText.isEmpty()) {
            showAlert("Error", "Please fill in all fields.");
            return;
        }

        // Convert quantity and price to appropriate types
        try {
            int quantity = Integer.parseInt(quantityText);
            BigDecimal price = new BigDecimal(priceText);
            LocalDate dateMade = LocalDate.parse(dateMadeText); // Assuming the date is in ISO format (yyyy-MM-dd)

            // Here you would typically save the product to your data source
            // For example: productService.saveProduct(new Product(...));

            showAlert("Success", "Product saved successfully!");
            clearFields(); // Optional: Clear fields after saving
        } catch (NumberFormatException e) {
            showAlert("Error", "Quantity and Price must be valid numbers.");
        } catch (DateTimeParseException e) {
            showAlert("Error", "Date Made must be in the format yyyy-MM-dd.");
        }
    }

    @FXML
    private void handleCancel() {
        // Clear the fields or close the window
        clearFields();
    }

    private void clearFields() {
        nameField.clear();
        descriptionField.clear();
        quantityField.clear();
        priceField.clear();
        dateMadeField.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}