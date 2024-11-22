package controller;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import model.Material;
import dao.MaterialDAO; // Import the DAO

public class ManageMaterialsController {

    @FXML
    private TableView<Material> materialsTable;
    @FXML
    private TableColumn<Material, Number> idColumn;
    @FXML
    private TableColumn<Material, String> nameColumn;
    @FXML
    private TableColumn<Material, String> descriptionColumn;
    @FXML
    private TableColumn<Material, Number> quantityColumn;
    @FXML
    private TableColumn<Material, String> unitColumn;

    @FXML
    private TextField nameField, descriptionField, quantityField, unitField;
    @FXML
    private TextField searchField;

    private MaterialDAO materialDAO; // Use DAO instead of direct database connector
    private ObservableList<Material> materialsList;

    public void initialize() {
        materialDAO = new MaterialDAO();

        // Set up columns using property methods
        idColumn.setCellValueFactory(cellData -> cellData.getValue().materialIdProperty());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().materialNameProperty());
        descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
        quantityColumn.setCellValueFactory(cellData -> cellData.getValue().quantityProperty());
        unitColumn.setCellValueFactory(cellData -> cellData.getValue().unitProperty());

        // Fetch and display materials
        loadMaterials();
    }

    // Load materials from database using DAO
    private void loadMaterials() {
        materialsList = FXCollections.observableArrayList(materialDAO.getAllMaterials());
        materialsTable.setItems(materialsList);
    }

    // Handle Add Material
    @FXML
    private void handleAdd() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/add/AddEditMaterial.fxml"));
            Parent root = loader.load();

            // Get the controller for Add/Edit Material
            AddEditMaterialController controller = loader.getController();
            controller.setAddMode(); // Set it to Add mode
            controller.setMaterialDAO(materialDAO); // Pass the DAO to the controller

            // Show the form in a new window
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Handle Edit Material
    @FXML
    private void handleEdit() {
        Material selectedMaterial = materialsTable.getSelectionModel().getSelectedItem();
        if (selectedMaterial != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/add/AddEditMaterial.fxml"));
                Parent root = loader.load();

                // Get the controller for Add/Edit Material
                AddEditMaterialController controller = loader.getController();
                controller.setEditMode(selectedMaterial); // Set it to Edit mode
                controller.setMaterialDAO(materialDAO); // Pass the DAO to the controller

                // Show the form in a new window
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "Edit Material", "Please select a material to edit.");
        }
    }

    // Handle Delete Material
    @FXML
    private void handleDelete() {
        Material selectedMaterial = materialsTable.getSelectionModel().getSelectedItem();
        if (selectedMaterial != null) {
            // You would need to add a deleteMaterial method to MaterialDAO
            if (materialDAO.deleteMaterial(selectedMaterial.getMaterialId())) {
                showAlert(Alert.AlertType.INFORMATION, "Delete Material", "Material deleted successfully!");
                loadMaterials(); // Refresh table
            } else {
                showAlert(Alert.AlertType.ERROR, "Delete Material", "Failed to delete material.");
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "Delete Material", "Please select a material to delete.");
        }
    }

    // Handle Refresh Table
    @FXML
    private void handleRefresh() {
        loadMaterials(); // Reload all data from the database
    }

    // Show alert helper method
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Handle Search functionality
    @FXML
    private void handleSearch() {
        String searchQuery = searchField.getText().toLowerCase(); // Get the search term

        // Filter the materials list based on the search term
        ObservableList<Material> filteredList = FXCollections.observableArrayList();

        for (Material material : materialsList) {
            if (material.getMaterialName().toLowerCase().contains(searchQuery) ||
                material.getDescription().toLowerCase().contains(searchQuery) ||
                String.valueOf(material.getQuantity()).contains(searchQuery) ||
                material.getUnit().toLowerCase().contains(searchQuery)) {
                filteredList.add(material);
            }
        }

        // Set the filtered list to the table
        materialsTable.setItems(filteredList);
    }
}