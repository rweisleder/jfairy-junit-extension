package com.github.rweisleder.jfairy;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Customize the random generator
 *
 * @author Roland Weisleder
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Documented
public @interface Random {

  /**
   * {@link java.util.Locale} used by jFairy
   *
   * @see io.codearte.jfairy.Bootstrap.Builder#create(java.util.Locale)
   */
  String locale() default DEFAULT_LOCALE;

  String DEFAULT_LOCALE = "";

  /**
   * Random seed used by jFairy
   *
   * @see io.codearte.jfairy.Bootstrap.Builder#withRandomSeed(int)
   */
  int seed() default DEFAULT_SEED;

  int DEFAULT_SEED = -1;

}
