package com.contoso.computers.demo.repository;

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.contoso.computers.demo.model.DeviceHealth;
import com.contoso.computers.demo.model.DeviceInfo;
import com.contoso.computers.demo.model.WarrantyInfo;

@Service
public class DeviceRepositoryImpl implements DeviceRepository{

    @Override
    public DeviceInfo findBySerialNumber(String serialNumber) {
        return DeviceInfo.create(serialNumber);
    }

    @Override
    public List<DeviceInfo> findAll() {
        return Arrays.asList(DeviceInfo.SERIAL_NUMBERS).stream().map(serialNumber -> DeviceInfo.create(serialNumber)).collect(Collectors.toList());
    }

    @Override
    public DeviceHealth getHealthBySerialNumber(String serialNumber) {
        return DeviceHealth.create(serialNumber);
    }

    @Override
    public WarrantyInfo getWarrantyInfoBySerialNumber(String serialNumber) {
        return WarrantyInfo.create(serialNumber);
    }

}
