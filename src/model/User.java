package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {
    private final IntegerProperty userId;
    private final StringProperty username;
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty email;
    private final IntegerProperty status;
    private final StringProperty password;

    public User(int userId, String username, String firstName, String lastName, String email, int status, String password) {
        this.userId = new SimpleIntegerProperty(userId);
        this.username = new SimpleStringProperty(username);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.email = new SimpleStringProperty(email);
        this.status = new SimpleIntegerProperty(status);
        this.password = new SimpleStringProperty(password);
    }

    // Getters
    public int getUserId() { return userId.get(); }
    public String getUsername() { return username.get(); }
    public String getFirstName() { return firstName.get(); }
    public String getLastName() { return lastName.get(); }
    public String getEmail() { return email.get(); }
    public int getStatus() { return status.get(); }
    public String getPassword() { return password.get(); }

    // Property methods
    public IntegerProperty userIdProperty() { return userId; }
    public StringProperty usernameProperty() { return username; }
    public StringProperty firstNameProperty() { return firstName; }
    public StringProperty lastNameProperty() { return lastName; }
    public StringProperty emailProperty() { return email; }
    public IntegerProperty statusProperty() { return status; }
    public StringProperty passwordProperty() { return password; }

    // Setters
    public void setUserId(int userId) { this.userId.set(userId); }
    public void setUsername(String username) { this.username.set(username); }
    public void setFirstName(String firstName) { this.firstName.set(firstName); }
    public void setLastName(String lastName) { this.lastName.set(lastName); }
    public void setEmail(String email) { this.email.set(email); }
    public void setStatus(int status) { this.status.set(status); }
    public void setPassword(String password) { this.password.set(password); }
}
