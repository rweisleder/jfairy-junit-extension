package com.github.rweisleder.jfairy;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Customize the generated String
 *
 * @author Roland Weisleder
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Documented
public @interface StringWith {

  /**
   * Maximum length of the generated String
   */
  int maxLength() default DEFAULT_MAX_LENGTH;

  int DEFAULT_MAX_LENGTH = 4000;

  /**
   * @see io.codearte.jfairy.producer.text.TextProducer
   */
  StringType type() default StringType.RANDOM;

  StringType DEFAULT_TYPE = StringType.RANDOM;

  enum StringType {
    RANDOM, LOREM_IPSUM, TEXT, WORD, LATIN_WORD, SENTENCE, LATIN_SENTENCE, PARAGRAPH
  }

}
