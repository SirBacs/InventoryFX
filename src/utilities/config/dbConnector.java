package utilities.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.Alert;

public class dbConnector {
    
    private Connection connect;

    public dbConnector() {
        try {
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/crochetyarndb", "root", "");
        } catch (SQLException ex) {
            showAlert("Database Unavailable", "Check database and try again: " + ex.getMessage());
        }
    }
    
    // Show alert for errors
    private void showAlert(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    // Function to retrieve data
    public ResultSet getData(String sql) throws SQLException {
        PreparedStatement pst = connect.prepareStatement(sql);
        return pst.executeQuery();
    }
    
    // Function to get the connection (for external use)
    public Connection getConnection() {
        return connect;
    }
        
    // Function to save data
    public boolean insertData(String sql) {
        try {
            PreparedStatement pst = connect.prepareStatement(sql);
            int rowsAffected = pst.executeUpdate();
            pst.close();
            return rowsAffected > 0;  // Return true if insert was successful
        } catch (SQLException ex) {
            showAlert("Insert Error", "Connection Error: " + ex.getMessage());
            return false;
        }
    }
        
    // Function to update data
    public boolean updateData(String sql) {
        try {
            PreparedStatement pst = connect.prepareStatement(sql);
            int rowsUpdated = pst.executeUpdate();
            pst.close();
            return rowsUpdated > 0;  // Return true if update was successful
        } catch (SQLException ex) {
            showAlert("Update Error", "Connection Error: " + ex.getMessage());
            return false;
        }
    }
        
    // Function to delete data
    public boolean deleteData(String sql) {
        try {
            PreparedStatement pst = connect.prepareStatement(sql);
            int rowsDeleted = pst.executeUpdate();
            pst.close();
            return rowsDeleted > 0;  // Return true if delete was successful
        } catch (SQLException ex) {
            showAlert("Delete Error", "Connection Error: " + ex.getMessage());
            return false;
        }
    }
    
    // Close connection if needed (implement this method if necessary)
    public void closeConnection() {
        try {
            if (connect != null && !connect.isClosed()) {
                connect.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
