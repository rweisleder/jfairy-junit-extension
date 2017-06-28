package io.github.rweisleder.jfairy;

import static io.codearte.jfairy.producer.person.Person.Sex.FEMALE;
import static io.codearte.jfairy.producer.person.Person.Sex.MALE;
import static org.assertj.core.api.Assertions.assertThat;
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

    @Test
    void german(@Random(locale = "de", seed = 1337) Person person) {
      assertThat(person.getFirstName()).isEqualTo("Friedbald");
    }

    @Nested
    class PersonTest {

      @Test
      void adult(@Random @PersonWith(minAge = 18) Person person) {
        assertThat(person.getAge()).isGreaterThanOrEqualTo(18);
      }

      @Test
      void young(@Random @PersonWith(maxAge = 18) Person person) {
        assertThat(person.getAge()).isLessThanOrEqualTo(18);
      }

      @Test
      void teenager(@Random @PersonWith(minAge = 13, maxAge = 19) Person person) {
        assertThat(person.getAge()).isGreaterThanOrEqualTo(13).isLessThanOrEqualTo(19);
      }

      @Test
      void flippedAgeParameters(@Random @PersonWith(minAge = 18, maxAge = 13) Person person) {
        assertThat(person.getAge()).isGreaterThanOrEqualTo(13).isLessThanOrEqualTo(19);
      }

      @Test
      void male(@Random @PersonWith(sex = MALE) Person person) {
        assertThat(person.getSex()).isEqualTo(MALE);
      }

      @Test
      void female(@Random @PersonWith(sex = FEMALE) Person person) {
        assertThat(person.getSex()).isEqualTo(FEMALE);
      }

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
