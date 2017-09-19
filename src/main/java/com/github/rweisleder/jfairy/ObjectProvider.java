package com.github.rweisleder.jfairy;

import static org.junit.platform.commons.support.AnnotationSupport.findAnnotation;

import io.codearte.jfairy.Bootstrap.Builder;
import io.codearte.jfairy.Fairy;
import java.lang.reflect.AnnotatedElement;
import java.util.Locale;

/**
 * @author Roland Weisleder
 */
abstract class ObjectProvider<T> {

  abstract T createFor(AnnotatedElement annotatedElement);

  Fairy fairy(AnnotatedElement annotatedElement) {
    Random random = findAnnotation(annotatedElement, Random.class).get();
    Builder builder = Fairy.builder();

    String locale = random.locale();
    if (!Random.DEFAULT_LOCALE.equals(locale)) {
      builder.withLocale(Locale.forLanguageTag(locale));
    }

    long seed = random.seed();
    if (Random.DEFAULT_SEED != seed) {
      builder.withRandomSeed(seed);
    }

    return builder.build();
  }

}
