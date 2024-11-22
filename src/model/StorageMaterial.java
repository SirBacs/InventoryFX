package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class StorageMaterial {
    private final IntegerProperty containerId;
    private final IntegerProperty materialId;
    private final IntegerProperty quantityStored;

    public StorageMaterial(int containerId, int materialId, int quantityStored) {
        this.containerId = new SimpleIntegerProperty(containerId);
        this.materialId = new SimpleIntegerProperty(materialId);
        this.quantityStored = new SimpleIntegerProperty(quantityStored);
    }

    // Getters
    public int getContainerId() { return containerId.get(); }
    public int getMaterialId() { return materialId.get(); }
    public int getQuantityStored() { return quantityStored.get(); }

    // Property methods
    public IntegerProperty containerIdProperty() { return containerId; }
    public IntegerProperty materialIdProperty() { return materialId; }
    public IntegerProperty quantityStoredProperty() { return quantityStored; }

    // Setters
    public void setContainerId(int containerId) { this.containerId.set(containerId); }
    public void setMaterialId(int materialId) { this.materialId.set(materialId); }
    public void setQuantityStored(int quantityStored) { this.quantityStored.set(quantityStored); }
}
