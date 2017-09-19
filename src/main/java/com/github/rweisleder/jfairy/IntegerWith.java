package com.github.rweisleder.jfairy;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Customize the generated Integer
 *
 * @author Roland Weisleder
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Documented
public @interface IntegerWith {

  /**
   * @see io.codearte.jfairy.producer.BaseProducer#randomBetween(int, int)
   */
  int min() default DEFAULT_MIN_VALUE;

  /**
   * @see io.codearte.jfairy.producer.BaseProducer#randomBetween(int, int)
   */
  int max() default DEFAULT_MAX_VALUE;

  int DEFAULT_MIN_VALUE = 1;

  int DEFAULT_MAX_VALUE = Integer.MAX_VALUE;

}
