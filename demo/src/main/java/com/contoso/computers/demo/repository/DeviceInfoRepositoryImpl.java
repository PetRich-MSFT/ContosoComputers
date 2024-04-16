package com.contoso.computers.demo.repository;

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.contoso.computers.demo.model.DeviceInfo;

@Service
public class DeviceInfoRepositoryImpl implements DeviceInfoRepository{

    @Override
    public DeviceInfo findBySerialNumber(String serialNumber) {
        return DeviceInfo.create(serialNumber);
    }

    @Override
    public List<DeviceInfo> findAll() {
        return Arrays.asList(DeviceInfo.SERIAL_NUMBERS).stream().map(serialNumber -> DeviceInfo.create(serialNumber)).collect(Collectors.toList());
    }

}
