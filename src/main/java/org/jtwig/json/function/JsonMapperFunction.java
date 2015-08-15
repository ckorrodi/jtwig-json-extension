package org.jtwig.json.function;

import com.google.common.base.Function;
import org.jtwig.context.RenderContext;
import org.jtwig.context.RenderContextHolder;
import org.jtwig.exceptions.CalculationException;
import org.jtwig.functions.annotations.JtwigFunction;
import org.jtwig.functions.annotations.Parameter;
import org.jtwig.json.configuration.JsonMapperProviderConfiguration;
import org.jtwig.json.provider.JsonMapperProvider;

public class JsonMapperFunction {
    private Function<Object, String> jsonMapper;

    @JtwigFunction("json_encode")
    public String encode (@Parameter("value") Object value) {
        return jsonMapper().apply(value);
    }

    private Function<Object, String> jsonMapper() {
        if (jsonMapper == null) {
            JsonMapperProvider jsonMapperProvider = JsonMapperProviderConfiguration.jsonMapperProvider(getRenderContext().environment());
            if (!jsonMapperProvider.isFound()) {
                throw new CalculationException(String.format("No json mapper available, have linked one of the provider libraries?"));
            } else {
                jsonMapper = jsonMapperProvider.jsonMapper();
            }
        }
        return jsonMapper;
    }

    protected RenderContext getRenderContext () {
        return RenderContextHolder.get();
    }
}
