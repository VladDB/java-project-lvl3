package hexlet.code;

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
}
