package com.github.rweisleder.jfairy;

import static org.junit.platform.commons.support.AnnotationSupport.findAnnotation;

import io.codearte.jfairy.Bootstrap.Builder;
import io.codearte.jfairy.Fairy;
import java.lang.reflect.AnnotatedElement;
import java.util.Locale;

/**
 * @author Roland Weisleder
 */
abstract class ObjectProvider {

  abstract boolean supports(Class<?> targetType);

  final boolean isAssignableFrom(Class<?> targetType, Class<?>[] supportedClasses) {
    for (Class<?> supportedClass : supportedClasses) {
      if (targetType.isAssignableFrom(supportedClass)) {
        return true;
      }
    }
    return false;
  }

  abstract Object createFor(AnnotatedElement annotatedElement, Class<?> targetType,
      Fairy fairy);

  final Object createFor(AnnotatedElement annotatedElement, Class<?> targetType) {
    Fairy fairy = buildFairy(annotatedElement);
    return createFor(annotatedElement, targetType, fairy);
  }

  private Fairy buildFairy(AnnotatedElement annotatedElement) {
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
