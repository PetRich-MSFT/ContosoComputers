package com.contoso.computers.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contoso.computers.demo.model.DeviceInfo;
import com.contoso.computers.demo.repository.DeviceInfoRepository;

@Service
public class DeviceInfoServiceImpl implements DeviceInfoService {

    private final DeviceInfoRepository deviceInfoRepository;

    @Autowired
    public DeviceInfoServiceImpl(DeviceInfoRepository deviceInfoRepository) {
        this.deviceInfoRepository = deviceInfoRepository;
    }

    @Override
    public DeviceInfo getDeviceInfo(String serialNumber) {
        // TODO Auto-generated method stub
        return new DeviceInfo(serialNumber, serialNumber, serialNumber);
    }

    @Override
    public List<DeviceInfo> getAllDevices() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
