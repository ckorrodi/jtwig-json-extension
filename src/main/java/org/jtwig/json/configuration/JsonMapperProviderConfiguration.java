package org.jtwig.json.configuration;

import org.jtwig.environment.Environment;
import org.jtwig.environment.EnvironmentConfigurationBuilder;
import org.jtwig.json.provider.CompositeJsonMapperProvider;
import org.jtwig.json.provider.JsonMapperProvider;

import java.util.Collection;

public class JsonMapperProviderConfiguration {
    private static final String JSON_MAPPER_PROVIDER = "jsonMapperProvider";

    public static JsonMapperProvider jsonMapperProvider (Environment environment) {
        return environment.parameter(JSON_MAPPER_PROVIDER);
    }

    private final Collection<JsonMapperProvider> jsonMapperProviders;

    public JsonMapperProviderConfiguration(Collection<JsonMapperProvider> jsonMapperProviders) {
        this.jsonMapperProviders = jsonMapperProviders;
    }

    public Collection<JsonMapperProvider> getJsonMapperProviders() {
        return jsonMapperProviders;
    }

    public void configure(EnvironmentConfigurationBuilder environmentConfigurationBuilder) {
        environmentConfigurationBuilder.withParameter(JSON_MAPPER_PROVIDER, new CompositeJsonMapperProvider(jsonMapperProviders));
    }
}
