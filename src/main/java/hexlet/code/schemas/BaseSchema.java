package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class BaseSchema {

    private List<Predicate<Object>> rules = new ArrayList<>();

    private boolean checkRequire = false;

    //добавление правила для проверки
    public final void addRule(Predicate<Object> rule) {
        rules.add(rule.and(Objects::nonNull));
    }

    //валидация данных
    public final boolean isValid(Object data) {
        for (Predicate<Object> validator: rules) {

            if (data == null && !checkRequire) {
                return true;
            }

            if (!validator.test(data)) {
                return false;
            }
        }
        return true;
    }

    //флаг для использования метода required()
    public final void setCheckRequire(boolean checkRequire) {
        this.checkRequire = checkRequire;
    }
}
