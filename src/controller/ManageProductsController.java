package controller;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Product; // Assuming you have a Product model similar to Material
import utilities.config.dbConnector;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ManageProductsController {

    @FXML
    private TableView<Product> productsTable;
    @FXML
    private TableColumn<Product, Number> idColumn;
    @FXML
    private TableColumn<Product, String> nameColumn;
    @FXML
    private TableColumn<Product, String> descriptionColumn;
    @FXML
    private TableColumn<Product, Number> priceColumn;
    @FXML
    private TableColumn<Product, Number> quantityColumn;

    @FXML
    private TextField nameField, descriptionField, priceField, quantityField;
    @FXML
    private TextField searchField;  // Search field for filtering products

    private dbConnector db;
    private ObservableList<Product> productsList;

    public void initialize() {
        db = new dbConnector();

        // Set up columns using property methods
        idColumn.setCellValueFactory(cellData -> cellData.getValue().productIdProperty());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().productNameProperty());
        descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
        priceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty());
        quantityColumn.setCellValueFactory(cellData -> cellData.getValue().quantityProperty());

        // Fetch and display products
        loadProducts();
    }

    // Load products from database
    private void loadProducts() {
        productsList = FXCollections.observableArrayList();
        String query = "SELECT * FROM products_tbl";

        try (ResultSet rs = db.getData(query)) {
            while (rs.next()) {
                productsList.add(new Product(
                    rs.getInt("product_id"),
                    rs.getString("product_name"),
                    rs.getString("description"),
                    rs.getDouble("price"),
                    rs.getInt("quantity")
                ));
            }
            productsTable.setItems(productsList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Handle Add Product
    @FXML
    private void handleAdd() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/add/AddEditProduct.fxml"));
            Parent root = loader.load();

            // Get the controller for Add/Edit Product
            AddEditProductsController controller = loader.getController();
            controller.setAddMode();

            // Show the form in a new window
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Add Product");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Handle Edit Product
    @FXML
    private void handleEdit() {
        Product selectedProduct = productsTable.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/add/AddEditProduct.fxml"));
                Parent root = loader.load();

                // Get the controller for Add/Edit Product
                AddEditProductsController controller = loader.getController();
                controller.setEditMode(selectedProduct); // Set it to Edit mode

                // Show the form in a new window
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Edit Product");
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "Edit Product", "Please select a product to edit.");
        }
    }

    // Handle Delete Product
    @FXML
    private void handleDelete() {
        Product selectedProduct = productsTable.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            String query = "DELETE FROM products_tbl WHERE product_id = " + selectedProduct.getProductId();

            if (db.deleteData(query)) {
                showAlert(Alert.AlertType.INFORMATION, "Delete Product", "Product deleted successfully!");
                loadProducts(); // Refresh table
            } else {
                showAlert(Alert.AlertType.ERROR, "Delete Product", "Failed to delete product.");
            }
        } else {
            showAlert(Alert.AlertType