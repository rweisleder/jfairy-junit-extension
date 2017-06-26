package io.github.rweisleder.jfairy;

import io.codearte.jfairy.producer.person.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests for {@link RandomDataGenerator}.
 *
 * @author Roland Weisleder
 */
class RandomDataGeneratorTest {

  @Random(locale = "en", seed = 1337)
  private Object enAnnotationHolder;

  @Random(locale = "de", seed = 1337)
  private Object deAnnotationHolder;

  private Random getAnnotation(String fieldName) throws NoSuchFieldException {
    return getClass().getDeclaredField(fieldName).getAnnotation(Random.class);
  }

  @Test
  void person_en() throws Exception {
    Random random = getAnnotation("enAnnotationHolder");
    Person person = (Person) RandomDataGenerator.generate(random, Person.class);
    Assertions.assertEquals("Colton", person.getFirstName());
  }

  @Test
  void person_de() throws Exception {
    Random random = getAnnotation("deAnnotationHolder");
    Person person = (Person) RandomDataGenerator.generate(random, Person.class);
    Assertions.assertEquals("Friedbald", person.getFirstName());
  }

  @Test
  void unknownTargetClass() throws Exception {
    Random random = getAnnotation("enAnnotationHolder");

    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      RandomDataGenerator.generate(random, Object.class);
    });
  }

}
