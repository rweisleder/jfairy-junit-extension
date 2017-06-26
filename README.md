# JUnit Extension for jFairy

[jFairy](http://codearte.github.io/jfairy/) is a fake data generator.
This JUnit extension provides simple injection of fake data into fields and method parameters.

Inject fake data as method parameter:
```java
@Test
@ExtendWith(FairyExtension.class)
void withRandomPerson(@Random Person person) {
  // ...
}
```

Inject fake data as field:
```java
@ExtendWith(FairyExtension.class)
class RandomTest {
  @Random
  private Person person;
}
```
