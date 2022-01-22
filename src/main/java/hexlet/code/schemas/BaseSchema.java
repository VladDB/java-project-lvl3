package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class BaseSchema {

    private List<Predicate<Object>> rules = new ArrayList<>();

    //добавление правила для проверки
    public final void addRule(Predicate<Object> rule) {
        rules.add(rule);
    }

    //валидация данных
    public final boolean isValid(Object data) {
        for (Predicate<Object> validator: rules) {

            if (!validator.test(data)) {
                return false;
            }
        }
        return true;
    }
}
