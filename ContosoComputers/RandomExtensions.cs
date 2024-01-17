namespace ContosoComputers
{
    public static class RandomExtensions
    {
        public static T NextEnumValue<T>(this Random random) where T: struct, Enum
        {
            return random.GetItems(Enum.GetValues<T>(), 1)[0];
        }

        public static bool NextBoolean(this Random random)
        {
            return random.NextDouble() > 0.5;
        }

        public static DateTimeOffset NextDateTimeOffset(this Random random, DateTimeOffset? min, DateTimeOffset? max)
        {
            min ??= DateTimeOffset.UtcNow;
            max ??= DateTimeOffset.UtcNow.AddDays(365);
            var range = max.Value - min.Value;
            var randomTimeSpan = new TimeSpan((long)(random.NextDouble() * range.Ticks));
            return min.Value + randomTimeSpan;
        }

        public static T NextFromList<T>(this Random random, IList<T> items)
        {
            int index = random.Next(0, items.Count);
            return items[index];
        }
    }
}
