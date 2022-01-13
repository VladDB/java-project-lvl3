package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema {

    // является любым числом включая ноль
    public void required() {
        this.addRule(num -> num instanceof Integer);
    }

    //является положительным числом
    public NumberSchema positive() {
        this.addRule(num -> num == null || num instanceof Integer && (Integer) num > 0);
        return this;
    }

    //попадает в заданный диапазон
    public NumberSchema range(int a, int b) {
        this.addRule(num -> num == null || num instanceof Integer
                && (a <= ((Integer) num) && ((Integer) num) <= b));
        return this;
    }
}
