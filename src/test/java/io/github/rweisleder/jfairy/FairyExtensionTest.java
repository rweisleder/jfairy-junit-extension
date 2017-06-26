package io.github.rweisleder.jfairy;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.codearte.jfairy.producer.person.Person;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * Tests for {@link FairyExtension}.
 *
 * @author Roland Weisleder
 */
@ExtendWith(FairyExtension.class)
class FairyExtensionTest {

  @Nested
  class ParameterResolverTest {

    @Test
    void person(@Random Person person) {
      assertNotNull(person);
    }

  }

  @Nested
  class TestInstancePostProcessorTest {

    @Random
    private Person person;

    @Test
    void person() {
      assertNotNull(person);
    }

  }

}
