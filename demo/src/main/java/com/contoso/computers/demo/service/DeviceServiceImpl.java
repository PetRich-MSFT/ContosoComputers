package com.contoso.computers.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contoso.computers.demo.model.DeviceHealth;
import com.contoso.computers.demo.model.DeviceInfo;
import com.contoso.computers.demo.model.WarrantyInfo;
import com.contoso.computers.demo.repository.DeviceRepository;

@Service
public class DeviceServiceImpl implements DeviceService {

    private final DeviceRepository deviceRepository;

    @Autowired
    public DeviceServiceImpl(DeviceRepository deviceInfoRepository) {
        this.deviceRepository = deviceInfoRepository;
    }

    @Override
    public DeviceInfo getDeviceInfo(String serialNumber) {
        return this.deviceRepository.findBySerialNumber(serialNumber);
    }

    @Override
    public List<DeviceInfo> getAllDevices(String operatingSystem) {
        return this.deviceRepository
            .findAll()
            .stream()
            .filter(deviceInfo -> operatingSystem == null || operatingSystem.equalsIgnoreCase(deviceInfo.getOperatingSystem()))
            .collect(Collectors.toList());
    }

    @Override
    public DeviceHealth getDeviceHealth(String serialNumber) {
        return this.deviceRepository.getHealthBySerialNumber(serialNumber);
    }

    @Override
    public WarrantyInfo getWarrantyInfo(String serialNumber) {
        return this.deviceRepository.getWarrantyInfoBySerialNumber(serialNumber);
    }
    
}
