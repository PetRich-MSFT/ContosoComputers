package com.contoso.computers.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DeviceInfo {

    public DeviceInfo(String serialNumber, String model, String operatingSystem) {
        this.serialNumber = serialNumber;
        this.model = model;
        this.operatingSystem = operatingSystem;
    }

    @Id
    private String serialNumber;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    private String model;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    private String operatingSystem;

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }
}
