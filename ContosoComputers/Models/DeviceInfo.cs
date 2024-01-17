namespace ContosoComputers.Models
{
    public class DeviceInfo
    {
        public static readonly string[] ModelNames =
        {
            "Contoso Quantum",
            "Contoso Helix",
            "Contoso Titan",
            "Contoso Atlas",
            "Contoso Atlas Pro",
            "Contoso Phoenix",
            "Contoso Odyssey",
            "Contoso Eclipse",
            "Contoso Zenith",
            "Contoso Horizon"
        };

        public string SerialNumber { get; set; }

        public string Model { get; set; }
    }
}
