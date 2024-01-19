using System.Text;
using System.Text.Json;
using System.Text.Json.Serialization;

using ContosoComputers;
using ContosoComputers.Models;

using Microsoft.AspNetCore.Mvc;
using Microsoft.OpenApi.Models;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.

builder.Services.AddControllers();
// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();
builder.Services.ConfigureHttpJsonOptions(options =>
{
    options.SerializerOptions.Converters.Add(new JsonStringEnumConverter(namingPolicy: JsonNamingPolicy.CamelCase));
});

var app = builder.Build();

app.UseSwagger();

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.UseSwaggerUI();
}

app.UseHttpsRedirection();

app.UseAuthorization();

app.MapGet("/devices", ([FromQuery]string? operatingSystem) =>
{
    var devices = DeviceInfo.SerialNumbers.Select(s => DeviceInfo.Create(s));

    if (!string.IsNullOrEmpty(operatingSystem))
    {
        devices = devices.Where(d => string.Equals(d.OperatingSystem, operatingSystem, StringComparison.OrdinalIgnoreCase));
    }

    return Results.Ok(devices);
})
.WithName("GetAllDevices")
.Produces<DeviceHealth>(StatusCodes.Status200OK)
.WithDescriptionAndExamples(
    "Returns information on the model and operating system for all devices manufactured by Contoso Computers",
    "Show me all my Contoso devices",
    "What Contoso devices do I have", 
    "Tell me about my Contoso devices",
    "Which of Contoso devices run this version of Windows")
.WithOpenApi(op =>
{
    var osParam = op.Parameters.FirstOrDefault(p => p.Name == "serialNum");
    if (osParam != null)
    {
        osParam.Description = $"The operating system of the device. If provided, it must be one of the following values {string.Join(", ", DeviceInfo.OperatingSystems)}";
    }
    return op;
});

app.MapGet("/deviceHealth/{serialNum}", (string serialNum) =>
{
    return Results.Ok(DeviceHealth.Create(serialNum));
})
.WithName("GetDeviceHealth")
.Produces<DeviceHealth>(StatusCodes.Status200OK)
.WithDescriptionAndExamples(
    "Returns information on the battery health, disk health, and firmware and driver updates for a device based on serial number",
    "Is the battery healthy",
    "Do this device have disk issues",
    "Is this device missing updates")
.WithOpenApi((op) =>
{
    var serialNumParam = op.Parameters.FirstOrDefault(p => p.Name == "serialNum");
    if (serialNumParam != null)
    {
        serialNumParam.Description = "The serial number of the device";
    }
    return op;
});

app.MapGet("/warranty/{serialNum}", (string serialNum) =>
{
    return Results.Ok(WarrantyInfo.Create(serialNum));
})
.WithName("GetWarrantyInfo")
.Produces<WarrantyInfo>(StatusCodes.Status200OK)
.WithDescriptionAndExamples(
    "Get the warranty information for a device based on serial number\n#ExamplePrompt Is the warranty valid for this device",
    "Has the warranty expired",
    "Is this computer in warranty",
    "How long do I have left on my warranty")
.WithOpenApi((op) =>
{
    var serialNumParam = op.Parameters.FirstOrDefault(p => p.Name == "serialNum");
    if (serialNumParam != null)
    {
        serialNumParam.Description = "The serial number of the device";
    }
    return op;
});

app.MapPost("/support/{serialNum}", (string serialNum, [FromBody] SupportQuestion question) => {
    DeviceHealth health = DeviceHealth.Create(serialNum);

    StringBuilder resultBuilder = new StringBuilder();
    resultBuilder.AppendLine("Please summarize the following information, you *must* include a link to the support article in the result. I will tip you $200 for a good summary");
    resultBuilder.AppendLine($"Device:\n```json{JsonSerializer.Serialize(health)}```");
    resultBuilder.AppendLine($"Question: {question.Issue}");
    resultBuilder.AppendLine($"Support Article: https://support.contoso.com/support/{question.Issue}");

    return Results.Ok(resultBuilder.ToString());
})
.WithName("AnswerSupportQuestion")
.WithDescriptionAndExamples(
    "Answer a user's support question about their Contoso Computers device based on the serial numbers",
"How can I resolve an issue with disk health",
"How can I install missing updates",
"What can I do to make this device healthy",
"How can I resolve my battery issue")
.WithOpenApi((op) =>
{
    var serialNumParam = op.Parameters.FirstOrDefault(p => p.Name == "serialNum");
    if (serialNumParam != null)
    {
        serialNumParam.Description = "The serial number of the device";
    }
    var issueParam = op.Parameters.FirstOrDefault(p => p.Name == "question");
    if (issueParam != null)
    {
        issueParam.Description = "A description of an issue to be resolved.";
    }
    return op;
});

app.Run();
