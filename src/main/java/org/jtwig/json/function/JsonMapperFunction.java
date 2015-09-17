package org.jtwig.json.function;

import com.google.common.base.Function;
import org.jtwig.context.RenderContext;
import org.jtwig.context.RenderContextHolder;
import org.jtwig.exceptions.CalculationException;
import org.jtwig.functions.JtwigFunctionRequest;
import org.jtwig.functions.SimpleJtwigFunction;
import org.jtwig.json.configuration.JsonMapperProviderConfiguration;
import org.jtwig.json.provider.JsonMapperProvider;

public class JsonMapperFunction extends SimpleJtwigFunction {
    private Function<Object, String> jsonMapper;

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

    @Override
    public String name() {
        return "json_encode";
    }

    @Override
    public Object execute(JtwigFunctionRequest request) {
        request.minimumNumberOfArguments(1).maximumNumberOfArguments(1);

        return jsonMapper().apply(request.getArgument(0, Object.class));
    }
}
