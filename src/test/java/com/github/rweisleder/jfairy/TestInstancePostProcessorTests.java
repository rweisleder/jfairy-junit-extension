package com.github.rweisleder.jfairy;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.codearte.jfairy.producer.person.Person;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * @author Roland Weisleder
 */
@ExtendWith(FairyExtension.class)
class TestInstancePostProcessorTests {

  @Nested
  class BooleanTests {

    @Random
    private Boolean b;

    @Test
    void booleanObject() {
      assertNotNull(b);
    }
  }

  @Nested
  class IntegerTests {

    @Random
    private Integer i;

    @Test
    void integerObject() {
      assertNotNull(i);
    }
  }

  @Nested
  class StringTests {

    @Random
    private String s;

    @Test
    void string() {
      assertNotNull(s);
    }
  }

  @Nested
  class PersonTests {

    @Random
    private Person person;

    @Test
    void person() {
      assertNotNull(person);
    }
  }
}
