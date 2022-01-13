package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

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

    @Test
    void mapSchemaTest() {

        final int size = 2;

        MapSchema testData = new Validator().map();

        Assertions.assertTrue(testData.isValid(null));

        testData.required();

        Assertions.assertFalse(testData.isValid(null));
        Assertions.assertTrue(testData.isValid(new HashMap<>()));

        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");

        Assertions.assertTrue(testData.isValid(data));

        testData.sizeof(size);

        Assertions.assertFalse(testData.isValid(data));
        data.put("key2", "value2");
        Assertions.assertTrue(testData.isValid(data));
    }

    @Test
    void mapSchemaEnlargedTest() {

        final int ageBig = 100;
        final int ageMinus = -5;

        Validator v = new Validator();

        MapSchema schema = v.map();

        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", v.string().required());
        schemas.put("age", v.number().positive());
        schema.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", ageBig);
        Assertions.assertTrue(schema.isValid(human1));

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null);
        Assertions.assertTrue(schema.isValid(human2));

        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);
        Assertions.assertFalse(schema.isValid(human3));

        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Valya");
        human4.put("age", ageMinus);
        Assertions.assertFalse(schema.isValid(human4));
    }
}
