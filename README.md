# JUnit Extension for jFairy

[![Build Status](https://travis-ci.org/rweisleder/jfairy-junit-extension.svg?branch=master)](https://travis-ci.org/rweisleder/jfairy-junit-extension)

[jFairy](http://codearte.github.io/jfairy/) is a fake data generator.
This JUnit extension provides simple injection of fake data into fields and method parameters.

```java
@Test
@ExtendWith(FairyExtension.class)
void withRandomPerson(@Random Person person) {
  System.out.println(person.getFullName());
  // Chloe Barker
}
```

## Documentation

### Setup
This extension resolves fields and method parameters annotated with `@Random`.
```java
@ExtendWith(FairyExtension.class)
class MyTest {
  
  @Random
  private Person person1;

  @Test
  void example(@Random Person person2) {
    // ...
  }
}
```

The random generator can be customized with a locale and a seed.
```java
@Random(locale = "de", seed = 1234)
```

### Supported Object Types
*   `boolean` and `Boolean`
*   `int` and `Integer` which can be customized using `@IntegerWith`

    ```java
    @Random
    @IntegerWith(min = 50, max = 100)
    private int i;
    ```
*   `Person` which can be customized using `@PersonWith`

    ```java
    @Random
    @PersonWith(sex = MALE, minAge = 13, maxAge = 19)
    private Person maleTeenager;
    ```


## Samples
Look into the [ExampleTests](src/test/java/com/github/rweisleder/jfairy/ExampleTests.java).
