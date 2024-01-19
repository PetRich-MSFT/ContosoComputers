namespace ContosoComputers
{
    public static class OpenApiExtensions
    {
        public static TBuilder WithDescriptionAndExamples<TBuilder>(this TBuilder builder, string description, params string[] examples) where TBuilder : IEndpointConventionBuilder
        {
            string examplesDescription = string.Join("\n", examples.Select(e => $"#ExamplePrompt {e}"));

            return builder.WithDescription($"{description}\n{examplesDescription}");
        }
    }
}
