package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema {

    public void required() {
        this.addRule(data -> data instanceof Map);
    }

    public void sizeof(int size) {
        this.addRule(data -> data instanceof Map && ((Map<Object, Object>) data).size() == size);
    }
}
