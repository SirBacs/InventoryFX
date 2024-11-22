package controller;

import dao.MaterialDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Material;
import utilities.config.dbConnector;

public class AddEditMaterialController {

    @FXML
    private TextField nameField, quantityField, unitField;
    
    @FXML
    private TextArea descriptionField;

    private dbConnector db;
    private Material materialToEdit;
    
    private MaterialDAO materialDAO;

    public void setMaterialDAO(MaterialDAO materialDAO) {
        this.materialDAO = materialDAO;
    }

// When saving, use materialDAO.insertMaterial() or materialDAO.updateMaterial()

    public void initialize() {
        db = new dbConnector();
    }

    // This method will be called when adding a new material
    public void setAddMode() {
        materialToEdit = null; // No material to edit
        nameField.clear();
        descriptionField.clear();
        quantityField.clear();
        unitField.clear();
    }

    // This method will be called when editing an existing material
    public void setEditMode(Material material) {
        materialToEdit = material;
        nameField.setText(material.getMaterialName());
        descriptionField.setText(material.getDescription());
        quantityField.setText(String.valueOf(material.getQuantity()));
        unitField.setText(material.getUnit());
    }

    // Handle Save (Add/Edit)
    @FXML
    private void handleSave() {
        String name = nameField.getText();
        String description = descriptionField.getText();
        int quantity = Integer.parseInt(quantityField.getText());
        String unit = unitField.getText();

        if (materialToEdit == null) {
            // Add new material
            String query = "INSERT INTO materials_tbl (material_name, description, quantity, unit) VALUES ('" +
                            name + "', '" + description + "', " + quantity + ", '" + unit + "')";
            if (db.insertData(query)) {
                showAlert(Alert.AlertType.INFORMATION, "Add Material", "Material added successfully!");
            } else {
                showAlert(Alert.AlertType.ERROR, "Add Material", "Failed to add material.");
            }
        } else {
            // Edit existing material
            String query = "UPDATE materials_tbl SET material_name = '" + name +
                           "', description = '" + description +
                           "', quantity = " + quantity +
                           ", unit = '" + unit +
                           "' WHERE materials_id = " + materialToEdit.getMaterialId();

            if (db.updateData(query)) {
                showAlert(Alert.AlertType.INFORMATION, "Edit Material", "Material updated successfully!");
            } else {
                showAlert(Alert.AlertType.ERROR, "Edit Material", "Failed to update material.");
            }
        }
    }

    // Handle Cancel
    @FXML
    private void handleCancel() {
        // Close the form (could be done via the stage)
        // For now, just clear the fields and return to the main screen
    }

    // Show alert helper method
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
