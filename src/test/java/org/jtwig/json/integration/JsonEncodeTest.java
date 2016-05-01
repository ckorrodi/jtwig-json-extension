package org.jtwig.json.integration;

import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;
import org.jtwig.json.JsonExtension;
import org.jtwig.json.configuration.DefaultJsonMapperProviderConfiguration;
import org.jtwig.json.configuration.JsonMapperProviderConfigurationBuilder;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.jtwig.environment.EnvironmentConfigurationBuilder.configuration;

public class JsonEncodeTest {
    @Test
    public void name() throws Exception {
        String result = JtwigTemplate.inlineTemplate("{{ variable | json_encode }}", configuration()
                .extensions().add(new JsonExtension(new JsonMapperProviderConfigurationBuilder(new DefaultJsonMapperProviderConfiguration()).build())).and()
                .build())
                .render(JtwigModel.newModel().with("variable", new TestEntity().setName("hello")));

        assertThat(result, not(is("")));
    }

    public static class TestEntity {
        private String name;

        public String getName() {
            return name;
        }

        public TestEntity setName(String name) {
            this.name = name;
            return this;
        }
    }
}
