package io.github.rweisleder.jfairy;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
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
  String locale() default "";

  /**
   * Random seed used by jFairy
   *
   * @see io.codearte.jfairy.Bootstrap.Builder#withRandomSeed(long)
   */
  long seed() default -1;

}
