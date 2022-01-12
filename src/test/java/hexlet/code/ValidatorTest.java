package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ValidatorTest {
    @Test
    void stringSchemaTest() {
        StringSchema testString = new Validator().string();

        Assertions.assertTrue(testString.isValid(null));
        Assertions.assertTrue(testString.isValid(""));

        testString.required();

        Assertions.assertTrue(testString.isValid("what does the fox say"));
        Assertions.assertTrue(testString.isValid("hexlet"));
        Assertions.assertFalse(testString.isValid(null));
        Assertions.assertFalse(testString.isValid(""));

        final int min = 4;

        Assertions.assertTrue(testString.minLength(min).isValid("Everything"));
        Assertions.assertFalse(testString.minLength(min).isValid("Eve"));

        Assertions.assertTrue(testString.contains("what").isValid("what is love?"));
        Assertions.assertFalse(testString.contains("some").isValid("what is love?"));
    }

    @Test
    void numberSchemaTest() {

        final int a = 5;
        final int b = 10;
        final int minus = -10;

        NumberSchema testNumber = new Validator().number();

        Assertions.assertTrue(testNumber.isValid(null));

        testNumber.required();

        Assertions.assertTrue(testNumber.isValid(b));
        Assertions.assertFalse(testNumber.isValid(null));
        Assertions.assertFalse(testNumber.isValid("5"));

        Assertions.assertTrue(testNumber.positive().isValid(b));
        Assertions.assertFalse(testNumber.isValid(minus));

        testNumber.range(a, b);

        Assertions.assertTrue(testNumber.isValid(a));
        Assertions.assertTrue(testNumber.isValid(b));
        Assertions.assertFalse(testNumber.isValid(minus));
        Assertions.assertFalse(testNumber.isValid(a + b));
    }
}
