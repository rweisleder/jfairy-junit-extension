package com.github.rweisleder.jfairy;

import static org.junit.platform.commons.support.AnnotationSupport.findAnnotation;

import io.codearte.jfairy.producer.BaseProducer;
import java.lang.reflect.AnnotatedElement;

/**
 * @author Roland Weisleder
 */
class IntegerProvider extends ObjectProvider {

  @Override
  Object createFor(AnnotatedElement annotatedElement) {
    IntegerWith config = findAnnotation(annotatedElement, IntegerWith.class).orElse(null);
    int min = minValue(config);
    int max = maxValue(config);

    BaseProducer producer = fairy(annotatedElement).baseProducer();
    return producer.randomBetween(min, max);
  }

  private int minValue(IntegerWith config) {
    return config != null ? config.min() : IntegerWith.DEFAULT_MIN_VALUE;
  }

  private int maxValue(IntegerWith config) {
    return config != null ? config.max() : IntegerWith.DEFAULT_MAX_VALUE;
  }
}
