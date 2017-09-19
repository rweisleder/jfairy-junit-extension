package com.github.rweisleder.jfairy;

import io.codearte.jfairy.producer.BaseProducer;
import java.lang.reflect.AnnotatedElement;

/**
 * @author Roland Weisleder
 */
class BooleanProvider extends ObjectProvider<Boolean> {

  @Override
  Boolean createFor(AnnotatedElement annotatedElement) {
    BaseProducer producer = fairy(annotatedElement).baseProducer();
    return producer.trueOrFalse();
  }
}
