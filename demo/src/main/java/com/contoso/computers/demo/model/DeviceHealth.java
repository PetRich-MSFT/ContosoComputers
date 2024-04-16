package com.contoso.computers.demo.model;

import java.util.Random;

import com.contoso.computers.demo.util.RandomUtils;

public class DeviceHealth extends DeviceInfo {
    
    public DeviceHealth(String serialNumber, String operatingSystem, String model, HealthStatus battery, HealthStatus drive, boolean missingFirmwareUpdates, boolean missingDriverUpdates) {
        super(serialNumber, operatingSystem, model);
        this.battery = battery;
        this.drive = drive;
        this.missingFirmwareUpdates = missingFirmwareUpdates;
        this.missingDriverUpdates = missingDriverUpdates;
    }

    public static DeviceHealth create(String serialNumber) {
        int seed = serialNumber.hashCode();
        Random random = new Random(seed);
        
        return new DeviceHealth(
            serialNumber, 
            RandomUtils.randomFromArray(MODEL_NAMES, random),
            RandomUtils.randomFromArray(OPERATING_SYSTEMS, random),
            RandomUtils.randomEnum(HealthStatus.class, random),
            RandomUtils.randomEnum(HealthStatus.class, random),
            random.nextBoolean(), 
            random.nextBoolean());
    }

    private HealthStatus battery;
    
    public HealthStatus getBattery() {
        return battery;
    }

    public void setBattery(HealthStatus battery) {
        this.battery = battery;
    }

    private HealthStatus drive;

    public HealthStatus getDrive() {
        return drive;
    }

    public void setDrive(HealthStatus drive) {
        this.drive = drive;
    }

    private boolean missingFirmwareUpdates;

    public boolean isMissingFirmwareUpdates() {
        return missingFirmwareUpdates;
    }

    public void setMissingFirmwareUpdates(boolean missingFirmwareUpdates) {
        this.missingFirmwareUpdates = missingFirmwareUpdates;
    }

    private boolean missingDriverUpdates;

    public boolean isMissingDriverUpdates() {
        return missingDriverUpdates;
    }

    public void setMissingDriverUpdates(boolean missingDriverUpdates) {
        this.missingDriverUpdates = missingDriverUpdates;
    }
}
