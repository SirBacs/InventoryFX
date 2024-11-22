// Material.java
package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Material {
    private final IntegerProperty materialId;
    private final StringProperty materialName;
    private final StringProperty description;
    private final IntegerProperty quantity;
    private final StringProperty unit;

    public Material(int materialId, String materialName, String description, int quantity, String unit) {
        this.materialId = new SimpleIntegerProperty(materialId);
        this.materialName = new SimpleStringProperty(materialName);
        this.description = new SimpleStringProperty(description);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.unit = new SimpleStringProperty(unit);
    }

    // Getters
    public int getMaterialId() { return materialId.get(); }
    public String getMaterialName() { return materialName.get(); }
    public String getDescription() { return description.get(); }
    public int getQuantity() { return quantity.get(); }
    public String getUnit() { return unit.get(); }

    // Property methods
    public IntegerProperty materialIdProperty() { return materialId; }
    public StringProperty materialNameProperty() { return materialName; }
    public StringProperty descriptionProperty() { return description; }
    public IntegerProperty quantityProperty() { return quantity; }
    public StringProperty unitProperty() { return unit; }

    // Setters
    public void setMaterialId(int materialId) { this.materialId.set(materialId); }
    public void setMaterialName(String materialName) { this.materialName.set(materialName); }
    public void setDescription(String description) { this.description.set(description); }
    public void setQuantity(int quantity) { this.quantity.set(quantity); }
    public void setUnit(String unit) { this.unit.set(unit); }
}
