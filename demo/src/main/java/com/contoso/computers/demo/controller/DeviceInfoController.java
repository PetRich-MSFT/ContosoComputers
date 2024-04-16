package com.contoso.computers.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.contoso.computers.demo.model.DeviceInfo;
import com.contoso.computers.demo.service.DeviceInfoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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

    @GetMapping("/")
    @Operation(operationId = "GetAllDevices", description = "Returns information on the model and operating system for all devices manufactured by Contoso Computers\n#ExamplePrompt Show me all my Contoso devices\n#ExamplePrompt What Contoso devices do I have\n#ExamplePrompt Tell me about my Contoso devices\n#ExamplePrompt Which of Contoso devices run this version of Windows")
    public List<DeviceInfo> getAllDevices(
        @Parameter(description = "The operating system of the device. If provided, it must be one of the following values: Windows 10 Version 1507, Windows 10 Version 1607, Windows 10 Version 1809, Windows 10 Version 21H2, Windows 10 Version 22H2, Windows 11 Version 21H2, Windows 11 Version 22H2, Windows 11 Version 23H2")
        @RequestParam(required = false) String operatingSystem) {
        return this.deviceInfoService.getAllDevices(operatingSystem);
    }
    
    @GetMapping("/{serialNumber}")
    public DeviceInfo getDeviceInfo(@PathVariable @Parameter(description = "Serial number of the device") String serialNumber) {
        return this.deviceInfoService.getDeviceInfo(serialNumber);
    }

}
