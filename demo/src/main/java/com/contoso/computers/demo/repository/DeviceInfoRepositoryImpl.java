package com.contoso.computers.demo.repository;

import java.util.List;

import org.springframework.stereotype.Service;

import com.contoso.computers.demo.model.DeviceInfo;

@Service
public class DeviceInfoRepositoryImpl implements DeviceInfoRepository{

    @Override
    public DeviceInfo findBySerialNumber(String serialNumber) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findBySerialNumber'");
    }

    @Override
    public List<DeviceInfo> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

}
