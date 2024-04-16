package com.contoso.computers.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.contoso.computers.demo.model.DeviceInfo;
import com.contoso.computers.demo.service.DeviceInfoService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/devices")
public class DeviceInfoController {

    private final DeviceInfoService deviceInfoService;

    @Autowired
    public DeviceInfoController(DeviceInfoService deviceInfoService) {
        this.deviceInfoService = deviceInfoService;
    }

    @GetMapping()
    public List<DeviceInfo> getAllDevices() {
        return this.deviceInfoService.getAllDevices();
    }
    
    @GetMapping("/{serialNumber}")
    public DeviceInfo getDeviceInfo(@PathVariable String serialNumber) {
        return this.deviceInfoService.getDeviceInfo(serialNumber);
    }

}
