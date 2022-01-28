package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema {

    //Проверка типа данных
    public void required() {
        this.addRule(data -> data instanceof Map);
        setCheckRequire(true);
    }

    //проверка количества пар в мапе
    public void sizeof(int size) {
        this.addRule(data -> data instanceof Map && ((Map<?, ?>) data).size() == size);
    }

    //добавление валидаторов из мапы
    public void shape(Map<String, BaseSchema> schemas) {
        this.addRule(map -> map instanceof Map && schemas.entrySet().stream()
                .allMatch(schema -> schema.getValue().isValid(((Map<?, ?>) map).get(schema.getKey()))));
    }
}
