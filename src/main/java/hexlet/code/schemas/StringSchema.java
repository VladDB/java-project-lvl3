package hexlet.code.schemas;

public final class StringSchema extends BaseSchema {

    //любая непустая строка
    public StringSchema required() {
        this.addRule(s -> s instanceof String && !((String) s).isBlank());
        setCheckRequire(true);
        return this;
    }

    //строка равна или длиннее указанного числа
    public StringSchema contains(String comparingStr) {
        this.addRule(s -> s instanceof String && ((String) s).contains(comparingStr));
        return this;
    }

    //строка равна или длиннее указанного числа
    public StringSchema minLength(int length) {
        this.addRule(s -> s instanceof String && ((String) s).length() >= length);
        return this;
    }
}
