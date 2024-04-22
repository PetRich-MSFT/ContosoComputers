package com.contoso.computers.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.contoso.computers.demo.model.DeviceHealth;
import com.contoso.computers.demo.model.DeviceInfo;
import com.contoso.computers.demo.model.SupportQuestion;
import com.contoso.computers.demo.model.WarrantyInfo;
import com.contoso.computers.demo.service.DeviceService;

import io.swagger.v3.core.util.Json;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/devices")
public class DeviceController {

    private final DeviceService deviceInfoService;

    @Autowired
    public DeviceController(DeviceService deviceInfoService) {
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

    @GetMapping("{serialNumber}/deviceHealth")
    @Operation(operationId = "GetDeviceHealth", description = "Returns information on the battery health, disk health, and firmware and driver updates for a device based on serial number\n#ExamplePrompt Is the battery healthy\n#ExamplePrompt Do this device have disk issues\n#ExamplePrompt Is this device missing updates")
    public DeviceHealth getDeviceHealth(@PathVariable @Parameter(description = "Serial number of the device") String serialNumber) {
        return this.deviceInfoService.getDeviceHealth(serialNumber);
    }

    @GetMapping("{serialNumber}/warrantyInfo")
    @Operation(operationId = "GetWarrantyInfo", description = "Get the warranty information for a device based on serial number\n#ExamplePrompt Is the warranty valid for this device\n#ExamplePrompt Has the warranty expired\n#ExamplePrompt Is this computer in warranty\n#ExamplePrompt How long do I have left on my warranty")
    public WarrantyInfo getWarrantyInfo(@PathVariable @Parameter(description = "Serial number of the device") String serialNumber) {
        return this.deviceInfoService.getWarrantyInfo(serialNumber);
    }

    @PostMapping(value = "{serialNumber}/support")
    public String askSupportQuestion(
        @PathVariable @Parameter(description = "Serial number of the device") String serialNumber,
        @RequestBody(required = true) @Parameter(description ="A description of an issue to be resolved") SupportQuestion question) {
        DeviceHealth health = getDeviceHealth("serialNumber");

        StringBuilder response = new StringBuilder();
        response.append("Please summarize the following information, you *must* include a link to the support article in the result.\n");
        response.append("Device:" + Json.pretty(health) + "\n");
        response.append("Question: " + question.getIssue() + "\n");
        response.append("Support Article: https://support.contoso.com/support/" + question.getIssue().replace(' ', '_') + "\n\n");

        return response.toString();
    }
    
}
