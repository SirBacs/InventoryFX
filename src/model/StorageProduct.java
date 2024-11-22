package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class StorageProduct {
    private final IntegerProperty containerId;
    private final IntegerProperty productId;
    private final IntegerProperty quantityStored;

    public StorageProduct(int containerId, int productId, int quantityStored) {
        this.containerId = new SimpleIntegerProperty(containerId);
        this.productId = new SimpleIntegerProperty(productId);
        this.quantityStored = new SimpleIntegerProperty(quantityStored);
    }

    // Getters
    public int getContainerId() { return containerId.get(); }
    public int getProductId() { return productId.get(); }
    public int getQuantityStored() { return quantityStored.get(); }

    // Property methods
    public IntegerProperty containerIdProperty() { return containerId; }
    public IntegerProperty productIdProperty() { return productId; }
    public IntegerProperty quantityStoredProperty() { return quantityStored; }

    // Setters
    public void setContainerId(int containerId) { this.containerId.set(containerId); }
    public void setProductId(int productId) { this.productId.set(productId); }
    public void setQuantityStored(int quantityStored) { this.quantityStored.set(quantityStored); }
}
