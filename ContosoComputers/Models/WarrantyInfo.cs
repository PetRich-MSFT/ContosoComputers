namespace ContosoComputers.Models;

internal class WarrantyInfo : DeviceInfo
{ 
    public static WarrantyInfo Create(string serialNumber)
    {
        int seed = serialNumber.GetHashCode();
        Random random = new(seed);
        var model = random.NextFromList(DeviceInfo.ModelNames);
        var operatingSystem = random.NextFromList(OperatingSystems);
        var warrantyExpires = random.NextDateTimeOffset(DateTimeOffset.UtcNow.AddDays(-30), DateTimeOffset.UtcNow.AddDays(365));
        var warrantyValid = warrantyExpires > DateTime.UtcNow;
        return new WarrantyInfo
        {
            SerialNumber = serialNumber,
            Model = model,
            OperatingSystem = operatingSystem,
            WarrantyExpires = warrantyExpires,
            WarrantyValid = warrantyValid
        };
    }

    public DateTimeOffset WarrantyExpires { get; set; }
    public bool WarrantyValid { get;  set; }
}