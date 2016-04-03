package org.jtwig.json.function;

import com.google.common.base.Function;
import org.jtwig.environment.Environment;
import org.jtwig.exceptions.CalculationException;
import org.jtwig.functions.FunctionRequest;
import org.jtwig.functions.SimpleJtwigFunction;
import org.jtwig.json.configuration.JsonMapperProviderConfiguration;
import org.jtwig.json.provider.JsonMapperProvider;

public class JsonMapperFunction extends SimpleJtwigFunction {
    private Function<Object, String> jsonMapper;

    @Override
    public String name() {
        return "json_encode";
    }

    @Override
    public Object execute(FunctionRequest request) {
        request.minimumNumberOfArguments(1).maximumNumberOfArguments(1);

        return jsonMapper(request.getEnvironment()).apply(request.get(0));
    }

    private Function<Object, String> jsonMapper(Environment environment) {
        if (jsonMapper == null) {
            JsonMapperProvider jsonMapperProvider = JsonMapperProviderConfiguration.jsonMapperProvider(environment);
            if (!jsonMapperProvider.isFound()) {
                throw new CalculationException("No json mapper available, have linked one of the provider libraries?");
            } else {
                jsonMapper = jsonMapperProvider.jsonMapper();
            }
        }
        return jsonMapper;
    }
}
