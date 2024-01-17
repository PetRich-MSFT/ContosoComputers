namespace ContosoComputers.Models
{
    public enum HealthStatus
    {
        Healthy,
        PredictedFailure,
        Failed
    }

    public class DeviceHealth : DeviceInfo
    {
        public static DeviceHealth Create(string serialNumber)
        {
            int seed = serialNumber.GetHashCode();
            Random random = new(seed);
            return new DeviceHealth
            {
                SerialNumber = serialNumber,
                Model = random.NextFromList(DeviceInfo.ModelNames),
                Battery = random.NextEnumValue<HealthStatus>(),
                Drive = random.NextEnumValue<HealthStatus>(),
                MissingFirmwareUpdates = random.NextBoolean(),
                MissingDriverUpdates = random.NextBoolean(),
            };
        }

        public HealthStatus Battery { get; set; }

        public HealthStatus Drive { get; set; }

        public bool MissingFirmwareUpdates { get; set; }

        public bool MissingDriverUpdates { get; set; }
    }
}
