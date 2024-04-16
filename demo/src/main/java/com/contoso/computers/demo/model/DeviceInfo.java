package com.contoso.computers.demo.model;

import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.context.annotation.Description;

@Entity
public class DeviceInfo {
    public static final String[] MODEL_NAMES = {
            "Contoso Quantum",
            "Contoso Helix",
            "Contoso Titan",
            "Contoso Atlas",
            "Contoso Atlas Pro",
            "Contoso Phoenix",
            "Contoso Odyssey",
            "Contoso Eclipse",
            "Contoso Zenith",
            "Contoso Horizon"
    };

    public static final String[] OPERATING_SYSTEMS = {
            "Windows 10 Version 1507",
            "Windows 10 Version 1607",
            "Windows 10 Version 1809",
            "Windows 10 Version 21H2",
            "Windows 10 Version 22H2",
            "Windows 11 Version 21H2",
            "Windows 11 Version 22H2",
            "Windows 11 Version 23H2"
    };

    public static final String[] SERIAL_NUMBERS = {
            "DA867Y5G",
            "DL3WXFJ8",
            "LJX7U466",
            "WF37CUTN",
            "83L4XKJC",
            "RCDRXWMS",
            "ERCYDF6X",
            "FRETGLJK",
            "2L4RRULL",
            "TTP9369G",
            "H4WDAGKW",
            "28NK6TRJ",
            "86PM96S2",
            "HLGSH6LG",
            "6UNCRS2F",
            "ML4TWWJA",
            "D8S2T588",
            "X8FJD48C",
            "4P2SRHX3",
            "5C2JXY3H",
    };
    
    public static DeviceInfo create(String serialNumber) {
        int seed = serialNumber.hashCode();
        Random random = new Random(seed);
        return new DeviceInfo(
            serialNumber, 
            MODEL_NAMES[random.nextInt(MODEL_NAMES.length)], 
            OPERATING_SYSTEMS[random.nextInt(OPERATING_SYSTEMS.length)]);
    }

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
