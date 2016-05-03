package org.jtwig.json.configuration;

import org.apache.commons.lang3.builder.Builder;
import org.jtwig.json.provider.JsonMapperProvider;

import java.util.ArrayList;
import java.util.Collection;

public class JsonMapperProviderConfigurationBuilder implements Builder<JsonMapperProviderConfiguration> {
    public static JsonMapperProviderConfigurationBuilder jsonConfiguration () {
        return new JsonMapperProviderConfigurationBuilder(new DefaultJsonMapperProviderConfiguration());
    }

    private final Collection<JsonMapperProvider> providers = new ArrayList<>();

    public JsonMapperProviderConfigurationBuilder () {}
    public JsonMapperProviderConfigurationBuilder (JsonMapperProviderConfiguration prototype) {
        providers.addAll(prototype.getJsonMapperProviders());
    }

    public JsonMapperProviderConfigurationBuilder withJsonMapperProvider(JsonMapperProvider mapperProvider) {
        this.providers.add(mapperProvider);
        return this;
    }

    @Override
    public JsonMapperProviderConfiguration build() {
        return new JsonMapperProviderConfiguration(providers);
    }
}
