# JUnit Extension for jFairy

[![Build Status](https://travis-ci.org/rweisleder/jfairy-junit-extension.svg?branch=master)](https://travis-ci.org/rweisleder/jfairy-junit-extension)
[![Coverage Status](https://coveralls.io/repos/github/rweisleder/jfairy-junit-extension/badge.svg?branch=master)](https://coveralls.io/github/rweisleder/jfairy-junit-extension?branch=master)

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
