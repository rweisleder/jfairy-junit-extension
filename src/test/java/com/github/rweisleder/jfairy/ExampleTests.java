package com.github.rweisleder.jfairy;

import static com.github.rweisleder.jfairy.StringWith.StringType.WORD;
import static io.codearte.jfairy.producer.person.Person.Sex.MALE;
import static org.assertj.core.api.Assertions.assertThat;

import io.codearte.jfairy.producer.person.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * @author Roland Weisleder
 */
@ExtendWith(FairyExtension.class)
class ExampleTests {

  @Random
  @IntegerWith(min = 50, max = 100)
  private int i;

  @Test
  void test_randomInteger() {
    assertThat(i).isBetween(50, 100);
  }

  // -------------------------------------

  @Random
  @PersonWith(sex = MALE, minAge = 13, maxAge = 19)
  private Person maleTeenager;

  @Test
  void test_maleTeenager() {
    assertThat(maleTeenager.getAge()).isBetween(13, 19);
  }

  // -------------------------------------

  @Random
  @StringWith(maxLength = 20)
  private String s;

  @Test
  void test_randomString() {
    assertThat(s.length()).isLessThanOrEqualTo(20);
  }

  // -------------------------------------

  @Random(locale = "de", seed = 1337)
  @StringWith(type = WORD)
  private String germanWord;

  @Test
  void test_germanWord() {
    assertThat(germanWord).isEqualTo("und");
  }

  // -------------------------------------

  @Random(locale = "de", seed = 1337)
  private Person germanPerson;

  @Test
  void test_germanPerson() {
    assertThat(germanPerson.getFirstName()).isEqualTo("Friedbald");
  }

  // -------------------------------------

  @Test
  void test_asParameter(@Random int x) {
    assertThat(x).isGreaterThan(0);
  }
}
