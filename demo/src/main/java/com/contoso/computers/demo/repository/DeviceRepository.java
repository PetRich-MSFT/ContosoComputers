package com.contoso.computers.demo.repository;

import com.contoso.computers.demo.model.DeviceHealth;
import com.contoso.computers.demo.model.DeviceInfo;

import java.util.List;

public interface DeviceRepository {
    DeviceInfo findBySerialNumber(String serialNumber);
    List<DeviceInfo> findAll();
    DeviceHealth getHealthBySerialNumber(String serialNumber);

}
