// Product.java
package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.ObjectProperty;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Product {
    private final IntegerProperty productId;
    private final StringProperty productName;
    private final StringProperty description;
    private final IntegerProperty quantity;
    private final ObjectProperty<BigDecimal> price; // Use ObjectProperty for BigDecimal
    private final ObjectProperty<LocalDate> dateMade;

    public Product(int productId, String productName, String description, int quantity, BigDecimal price, LocalDate dateMade) {
        this.productId = new SimpleIntegerProperty(productId);
        this.productName = new SimpleStringProperty(productName);
        this.description = new SimpleStringProperty(description);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.price = new SimpleObjectProperty<>(price); // Initialize with BigDecimal
        this.dateMade = new SimpleObjectProperty<>(dateMade);
    }

    // Getters
    public int getProductId() { return productId.get(); }
    public String getProductName() { return productName.get(); }
    public String getDescription() { return description.get(); }
    public int getQuantity() { return quantity.get(); }
    public BigDecimal getPrice() { return price.get(); }
    public LocalDate getDateMade() { return dateMade.get(); }

    // Property methods
    public IntegerProperty productIdProperty() { return productId; }
    public StringProperty productNameProperty() { return productName; }
    public StringProperty descriptionProperty() { return description; }
    public IntegerProperty quantityProperty() { return quantity; }
    public ObjectProperty<BigDecimal> priceProperty() { return price; } // Property method for BigDecimal
    public ObjectProperty<LocalDate> dateMadeProperty() { return dateMade; }

    // Setters
    public void setProductId(int productId) { this.productId.set(productId); }
    public void setProductName(String productName) { this.productName.set(productName); }
    public void setDescription(String description) { this.description.set(description); }
    public void setQuantity(int quantity) { this.quantity.set(quantity); }
    public void setPrice(BigDecimal price) { this.price.set(price); }
    public void setDateMade(LocalDate dateMade) { this.dateMade.set(dateMade); }
}