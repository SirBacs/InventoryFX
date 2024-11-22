package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Storage {
    private final IntegerProperty containerId;
    private final StringProperty containerName;
    private final IntegerProperty currentLoad;
    private final IntegerProperty containerStatus;

    public Storage(int containerId, String containerName, int currentLoad, int containerStatus) {
        this.containerId = new SimpleIntegerProperty(containerId);
        this.containerName = new SimpleStringProperty(containerName);
        this.currentLoad = new SimpleIntegerProperty(currentLoad);
        this.containerStatus = new SimpleIntegerProperty(containerStatus);
    }

    // Getters
    public int getContainerId() { return containerId.get(); }
    public String getContainerName() { return containerName.get(); }
    public int getCurrentLoad() { return currentLoad.get(); }
    public int getContainerStatus() { return containerStatus.get(); }

    // Property methods
    public IntegerProperty containerIdProperty() { return containerId; }
    public StringProperty containerNameProperty() { return containerName; }
    public IntegerProperty currentLoadProperty() { return currentLoad; }
    public IntegerProperty containerStatusProperty() { return containerStatus; }

    // Setters
    public void setContainerId(int containerId) { this.containerId.set(containerId); }
    public void setContainerName(String containerName) { this.containerName.set(containerName); }
    public void setCurrentLoad(int currentLoad) { this.currentLoad.set(currentLoad); }
    public void setContainerStatus(int containerStatus) { this.containerStatus.set(containerStatus); }
}
