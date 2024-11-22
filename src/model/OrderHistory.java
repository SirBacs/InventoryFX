package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class OrderHistory {
    private final IntegerProperty orderId;
    private final IntegerProperty productId;
    private final IntegerProperty quantity;
    private final StringProperty orderDate;
    private final StringProperty status;
    private final StringProperty customerInfo;
    private final StringProperty feedback;

    public OrderHistory(int orderId, int productId, int quantity, String orderDate, String status, String customerInfo, String feedback) {
        this.orderId = new SimpleIntegerProperty(orderId);
        this.productId = new SimpleIntegerProperty(productId);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.orderDate = new SimpleStringProperty(orderDate);
        this.status = new SimpleStringProperty(status);
        this.customerInfo = new SimpleStringProperty(customerInfo);
        this.feedback = new SimpleStringProperty(feedback);
    }

    // Getters
    public int getOrderId() { return orderId.get(); }
    public int getProductId() { return productId.get(); }
    public int getQuantity() { return quantity.get(); }
    public String getOrderDate() { return orderDate.get(); }
    public String getStatus() { return status.get(); }
    public String getCustomerInfo() { return customerInfo.get(); }
    public String getFeedback() { return feedback.get(); }

    // Property methods
    public IntegerProperty orderIdProperty() { return orderId; }
    public IntegerProperty productIdProperty() { return productId; }
    public IntegerProperty quantityProperty() { return quantity; }
    public StringProperty orderDateProperty() { return orderDate; }
    public StringProperty statusProperty() { return status; }
    public StringProperty customerInfoProperty() { return customerInfo; }
    public StringProperty feedbackProperty() { return feedback; }

    // Setters
    public void setOrderId(int orderId) { this.orderId.set(orderId); }
    public void setProductId(int productId) { this.productId.set(productId); }
    public void setQuantity(int quantity) { this.quantity.set(quantity); }
    public void setOrderDate(String orderDate) { this.orderDate.set(orderDate); }
    public void setStatus(String status) { this.status.set(status); }
    public void setCustomerInfo(String customerInfo) { this.customerInfo.set(customerInfo); }
    public void setFeedback(String feedback) { this.feedback.set(feedback); }
}
