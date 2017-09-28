package com.github.rweisleder.jfairy;

import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.BaseProducer;
import java.lang.reflect.AnnotatedElement;

/**
 * @author Roland Weisleder
 */
class EnumProvider extends ObjectProvider {

  @Override
  boolean supports(Class<?> targetType) {
    return targetType.isEnum();
  }

  @Override
  @SuppressWarnings("unchecked")
  Object createFor(AnnotatedElement annotatedElement, Class<?> targetType, Fairy fairy) {
    Class<Enum<?>> enumClass = (Class<Enum<?>>) targetType;

    BaseProducer producer = fairy.baseProducer();
    return producer.randomElement(enumClass);
  }
}
