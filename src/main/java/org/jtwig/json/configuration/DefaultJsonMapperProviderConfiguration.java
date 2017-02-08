package org.jtwig.json.configuration;

import org.jtwig.json.provider.Jackson2JsonMapperProvider;
import org.jtwig.json.provider.JacksonJsonMapperProvider;
import org.jtwig.reflection.model.java.JavaClassManager;
import org.jtwig.util.ClasspathFinder;

import static java.util.Arrays.asList;

public class DefaultJsonMapperProviderConfiguration extends JsonMapperProviderConfiguration {
    private static final ClasspathFinder CLASSPATH_FINDER = new ClasspathFinder(DefaultJsonMapperProviderConfiguration.class.getClassLoader(), JavaClassManager.classManager());

    public DefaultJsonMapperProviderConfiguration() {
        super(asList(
                new Jackson2JsonMapperProvider(CLASSPATH_FINDER),
                new JacksonJsonMapperProvider(CLASSPATH_FINDER)
        ));
    }
}
