package com.contoso.computers.demo.service;

import java.util.List;
import java.util.Optional;

import com.contoso.computers.demo.model.DeviceInfo;

public interface DeviceInfoService {
    DeviceInfo getDeviceInfo(String serialNumber);
    List<DeviceInfo> getAllDevices(String operatingSystem);
}
