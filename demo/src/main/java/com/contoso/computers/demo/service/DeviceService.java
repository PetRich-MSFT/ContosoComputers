package com.contoso.computers.demo.service;

import java.util.List;
import com.contoso.computers.demo.model.DeviceHealth;
import com.contoso.computers.demo.model.DeviceInfo;
import com.contoso.computers.demo.model.WarrantyInfo;

public interface DeviceService {
    DeviceInfo getDeviceInfo(String serialNumber);
    List<DeviceInfo> getAllDevices(String operatingSystem);
    DeviceHealth getDeviceHealth(String serialNumber);
    WarrantyInfo getWarrantyInfo(String serialNumber);
}
