package controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utilities.config.dbConnector;

public class LoginController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;

    private dbConnector connector = new dbConnector();

    @FXML
    private void handleLogin() {
    String username = usernameField.getText();
    String password = passwordField.getText();

    if (username.isEmpty() || password.isEmpty()) {
        errorLabel.setText("Please enter both username and password.");
        return;
    }

    // Log the username and password values for debugging
    System.out.println("Attempting to login with Username: " + username + " and Password: " + password);

    // Use correct column names based on your schema
    String query = "SELECT * FROM tbl_users WHERE uname = ? AND upassword = ?";
        try {
            PreparedStatement stmt = connector.getConnection().prepareStatement(query);
            stmt.setString(1, username);  // Set the username value
            stmt.setString(2, password);  // Set the password value

            ResultSet rs = stmt.executeQuery();

            // Log the result count for debugging
            if (rs.next()) {
                String status = rs.getString("ustatus");
                System.out.println("Login successful. User status: " + status);  // Log the status for debugging
                if ("1".equals(status)) {
                    // Admin user, redirect to Admin dashboard
                    loadAdminDashboard();
                } else if ("2".equals(status)) {
                    // Staff user, redirect to Staff dashboard
                    loadStaffDashboard();
                } else {
                    errorLabel.setText("Invalid user status.");
                    System.out.println("Invalid user status: " + status);  // Log if status is unexpected
                }
            } else {
                errorLabel.setText("Invalid username or password.");
                System.out.println("No matching record found for username: " + username);  // Log if no record found
            }
        } catch (SQLException ex) {
            showError("Database Error", ex.getMessage());
        }
    }
    
    private void loadAdminDashboard() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AdminDashboard.fxml"));
            Parent root = loader.load();
            Scene adminScene = new Scene(root);
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(adminScene);
            stage.show();
        } catch (IOException e) {
            showError("Error", "Failed to load Admin Dashboard: " + e.getMessage());
        }
    }

    private void loadStaffDashboard() {
        try {
            // Make sure the path is correct and Dashboard.fxml is in the 'fxml' directory.
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Dashboard.fxml"));
            Parent root = loader.load(); // Try to load the FXML file

            Scene staffScene = new Scene(root);
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(staffScene);
            stage.show();
        } catch (IOException e) {
            showError("Error", "Failed to load Staff Dashboard: " + e.getMessage());
            // Print stack trace for debugging
            e.printStackTrace();
        }
    }


    private void showError(String header, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
