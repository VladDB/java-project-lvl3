package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public final class StringSchema {
    private List<Predicate<Object>> validators = new ArrayList();

    //любая непустая строка
    public void required() {
        validators.add(s -> s instanceof String && !((String) s).isBlank());
    }

    //строка равна или длиннее указанного числа
    public StringSchema contains(String comparingStr) {
        this.addRule(s -> s == null || s instanceof String && ((String) s).contains(comparingStr));
        return this;
    }

    //строка равна или длиннее указанного числа
    public StringSchema minLength(int length) {
        this.addRule(s -> s == null || s instanceof String && ((String) s).length() >= length);
        return this;
    }

    //добавление правила для проверки
    public void addRule(Predicate<Object> rule) {
        validators.add(rule);
    }

    //валидация данных
    public boolean isValid(Object data) {
        for (Predicate<Object> validator: validators) {

            if (!validator.test(data)) {
                return false;
            }
        }
        return true;
    }
}
