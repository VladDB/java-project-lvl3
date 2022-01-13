package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema {

    public void required() {
        this.addRule(data -> data instanceof Map);
    }

    public void sizeof(int size) {
        this.addRule(data -> data instanceof Map && ((Map<?, ?>) data).size() == size);
    }

    public void shape(Map<String, BaseSchema> schemas) {
        this.addRule(map -> map instanceof Map && schemas.entrySet().stream()
                .allMatch(schema -> schema.getValue().isValid(((Map<?, ?>) map).get(schema.getKey()))));
    }
}
