package com.github.rweisleder.jfairy;

import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.BaseProducer;
import java.lang.reflect.AnnotatedElement;

/**
 * @author Roland Weisleder
 */
class BooleanProvider extends ObjectProvider {

  @Override
  boolean supports(Class<?> targetType) {
    return isAssignableFrom(targetType, new Class[]{Boolean.class, Boolean.TYPE});
  }

  @Override
  Object createFor(AnnotatedElement annotatedElement, Class<?> targetType, Fairy fairy) {
    BaseProducer producer = fairy.baseProducer();
    return producer.trueOrFalse();
  }

}
