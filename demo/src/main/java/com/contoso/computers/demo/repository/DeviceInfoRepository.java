package com.contoso.computers.demo.repository;

import com.contoso.computers.demo.model.DeviceInfo;

import java.util.List;

public interface DeviceInfoRepository {
    DeviceInfo findBySerialNumber(String serialNumber);
    List<DeviceInfo> findAll();

}
