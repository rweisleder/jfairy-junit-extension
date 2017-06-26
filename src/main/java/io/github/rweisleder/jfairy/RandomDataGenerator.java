package io.github.rweisleder.jfairy;

import io.codearte.jfairy.Bootstrap.Builder;
import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.person.Person;
import java.util.Locale;

/**
 * @author Roland Weisleder
 */
class RandomDataGenerator {

  static Object generate(Random random, Class<? extends Object> targetClass) {
    Fairy fairy = createFairy(random);

    if (Person.class.equals(targetClass)) {
      return fairy.person();
    }

    throw new IllegalArgumentException("unable to create fake object of " + targetClass);
  }

  private static Fairy createFairy(Random random) {
    Builder builder = Fairy.builder();

    if (!"".equals(random.locale())) {
      builder.withLocale(Locale.forLanguageTag(random.locale()));
    }

    if (random.seed() != -1) {
      builder.withRandomSeed(random.seed());
    }

    return builder.build();
  }

}
