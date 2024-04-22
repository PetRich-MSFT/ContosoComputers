package com.contoso.computers.demo.model;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import com.contoso.computers.demo.util.RandomUtils;

public class WarrantyInfo extends DeviceInfo {
    public WarrantyInfo(String serialNumber, String manufacturer, String model, Date warrantyExpires) {
        super(serialNumber, manufacturer, model);
        this.warrantyExpires = warrantyExpires;
        this.warrantyValid = warrantyExpires.after(new Date());
    }

    public static WarrantyInfo create(String serialNumber) {
        int seed = serialNumber.hashCode();
        Random random = new Random(seed);
        
        return new WarrantyInfo(
            serialNumber, 
            RandomUtils.randomFromArray(MODEL_NAMES, random),
            RandomUtils.randomFromArray(OPERATING_SYSTEMS, random),
            RandomUtils.randomDate(Calendar.DAY_OF_MONTH, -30, Calendar.DAY_OF_MONTH, 365, random)
            );
    }

    private Date warrantyExpires;

    public Date getWarrantyExpires() {
        return warrantyExpires;
    }

    public void setWarrantyExpires(Date warrantyExpires) {
        this.warrantyExpires = warrantyExpires;
    }

    private boolean warrantyValid;

    public boolean isWarrantyValid() {
        return warrantyValid;
    }

    public void setWarrantyValid(boolean warrantyValid) {
        this.warrantyValid = warrantyValid;
    }
}
