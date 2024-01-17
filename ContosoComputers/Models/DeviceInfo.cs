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

        public static readonly string[] OperatingSystems =
        {
            "Windows 10 Version 1507",
            "Windows 10 Version 1607",
            "Windows 10 Version 1809",
            "Windows 10 Version 21H2",
            "Windows 10 Version 22H2",
            "Windows 11 Version 21H2",
            "Windows 11 Version 22H2",
            "Windows 11 Version 23H2"
        };

        public static readonly string[] SerialNumbers =
        {
            "DA867Y5G",
            "DL3WXFJ8",
            "LJX7U466",
            "WF37CUTN",
            "83L4XKJC",
            "RCDRXWMS",
            "ERCYDF6X",
            "FRETGLJK",
            "2L4RRULL",
            "TTP9369G",
            "H4WDAGKW",
            "28NK6TRJ",
            "86PM96S2",
            "HLGSH6LG",
            "6UNCRS2F",
            "ML4TWWJA",
            "D8S2T588",
            "X8FJD48C",
            "4P2SRHX3",
            "5C2JXY3H",
        };

        public static DeviceInfo Create(string serialNumber)
        {
            int seed = serialNumber.GetHashCode();
            Random random = new(seed);
            return new DeviceInfo
            {
                SerialNumber = serialNumber,
                Model = random.NextFromList(DeviceInfo.ModelNames),
                OperatingSystem = random.NextFromList(OperatingSystems),
            };
        }

        public string SerialNumber { get; set; }

        public string Model { get; set; }

        public string OperatingSystem { get; set; }
    }
}
