// MaterialDAO.java
package dao;

import model.Material;
import utilities.config.dbConnector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MaterialDAO {
    private dbConnector db;

    public MaterialDAO() {
        db = new dbConnector();
    }

    public List<Material> getAllMaterials() {
        List<Material> materials = new ArrayList<>();
        String query = "SELECT * FROM materials_tbl";
        
        try (ResultSet rs = db.getData(query)) {
            while (rs.next()) {
                Material material = new Material(
                    rs.getInt("materials_id"),
                    rs.getString("material_name"),
                    rs.getString("description"),
                    rs.getInt("quantity"),
                    rs.getString("unit")
                );
                materials.add(material);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return materials;
    }

    public boolean insertMaterial(Material material) {
        String query = String.format("INSERT INTO materials_tbl (material_name, description, quantity, unit) VALUES ('%s', '%s', %d, '%s')",
                material.getMaterialName(), material.getDescription(), material.getQuantity(), material.getUnit());
        return db.insertData(query);
    }
    
    public boolean deleteMaterial(int materialId) {
        String query = "DELETE FROM materials_tbl WHERE materials_id = " + materialId;
        return db.deleteData(query);
    }

    // Add updateMaterial, deleteMaterial methods here similarly
}
