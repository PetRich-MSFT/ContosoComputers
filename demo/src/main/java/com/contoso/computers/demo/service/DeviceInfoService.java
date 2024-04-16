package com.contoso.computers.demo.service;

import java.util.List;

import com.contoso.computers.demo.model.DeviceInfo;

public interface DeviceInfoService {
    DeviceInfo getDeviceInfo(String serialNumber);
    List<DeviceInfo> getAllDevices();
}
