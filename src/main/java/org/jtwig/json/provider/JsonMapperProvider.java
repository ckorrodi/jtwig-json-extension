package org.jtwig.json.provider;

import com.google.common.base.Function;

public interface JsonMapperProvider {
    boolean isFound();
    Function<Object, String> jsonMapper();
}
