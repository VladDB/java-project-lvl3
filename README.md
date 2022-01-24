### Hexlet tests and linter status:
[![Actions Status](https://github.com/VladDB/java-project-lvl3/workflows/hexlet-check/badge.svg)](https://github.com/VladDB/java-project-lvl3/actions)
[![GitHub Actions](https://github.com/VladDB/java-project-lvl3/actions/workflows/github-actions.yml/badge.svg)](https://github.com/VladDB/java-project-lvl3/actions/workflows/github-actions.yml)
<a href="https://codeclimate.com/github/VladDB/java-project-lvl3/maintainability"><img src="https://api.codeclimate.com/v1/badges/4b0a0c88f4c6c3dca28d/maintainability" /></a>
<a href="https://codeclimate.com/github/VladDB/java-project-lvl3/test_coverage"><img src="https://api.codeclimate.com/v1/badges/4b0a0c88f4c6c3dca28d/test_coverage" /></a>

## **Валидатор данных**

В проекте разработана библиотека с помощью которой можно проверять корректность числовых и строковых данных.
Для проверки есть следующие валидаторы: **данные типа String** - required (любая непустая строка), minLength (строка 
равна или длиннее указанного числа), contains (строка содержит определённую подстроку); 
**данные типа Integer** - required (любое число включая ноль), positive (положительное число), range (диапазон 
в который должны попадать числа включая границы); **данные типа Map** - required (проверка передаваемых данных по типу),
sizeof (количество пар ключ-значений в объекте Map должно быть равно заданному), shape (позволяет описывать валидацию для значений объекта Map по ключам).

##Примеры использования

**Данные типа String**
````
Validator v = new Validator();

StringSchema schema = v.string();

schema.required(); //добавление валидатора

schema.isValid("what does the fox say"); // true
schema.isValid(""); // false
schema.contains("what").isValid("what does the fox say"); // true
schema.contains("whatthe").isValid("what does the fox say"); // false
````

**Данные типа Integer**

````
Validator v = new Validator();

NumberSchema schema = v.number();

schema.required();

schema.isValid(10); // true
schema.isValid("5"); // false

schema.positive().isValid(10); // true
schema.isValid(-10); // false

schema.range(5, 10);

schema.isValid(7); // true
schema.isValid(11); // false
````

**Данные типа Map**

````
Validator v = new Validator();

MapSchema schema = v.map();

schema.required();

schema.isValid(new HashMap()); // true
Map<String, String> data = new HashMap<>();
data.put("key1", "value1");
schema.isValid(data); // true

schema.sizeof(2);

schema.isValid(data);  // false
data.put("key2", "value2");
schema.isValid(data); // true

//использование метода shape
Map<String, BaseSchema> schemas = new HashMap<>();
schemas.put("name", v.string().required());
schemas.put("age", v.number().positive());
schema.shape(schemas);

Map<String, Object> human1 = new HashMap<>();
human1.put("name", "Kolya");
human1.put("age", 100);
schema.isValid(human1); // true

Map<String, Object> human4 = new HashMap<>();
human4.put("name", "Valya");
human4.put("age", -5);
schema.isValid(actual5); // false
````