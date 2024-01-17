using System.Text.Json;
using System.Text.Json.Serialization;

using ContosoComputers.Models;

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

app.MapGet("/deviceHealth/{serialNum}", (string serialNum) =>
{
    return Results.Ok(DeviceHealth.Create(serialNum));
})
.WithName("GetDeviceHealth")
.Produces<DeviceHealth>(StatusCodes.Status200OK)
.WithDescription("Get the health status for a device.")
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
.WithDescription("Get the warranty information for a device based on serial number\n#ExamplePrompt Is the warranty valid for this device")
.WithOpenApi((op) =>
{
    var serialNumParam = op.Parameters.FirstOrDefault(p => p.Name == "serialNum");
    if (serialNumParam != null)
    {
        serialNumParam.Description = "The serial number of the device";
    }
    return op;
});

app.Run();
