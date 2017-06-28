package io.github.rweisleder.jfairy;

import static io.codearte.jfairy.producer.person.PersonProvider.MAX_AGE;
import static io.codearte.jfairy.producer.person.PersonProvider.MIN_AGE;

import io.codearte.jfairy.producer.person.Person.Sex;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Customize the generated person
 *
 * @author Roland Weisleder
 * @see io.codearte.jfairy.producer.person.PersonProperties
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Documented
public @interface PersonWith {

  /**
   * @see io.codearte.jfairy.producer.person.PersonProperties#minAge(int)
   */
  int minAge() default MIN_AGE;

  /**
   * @see io.codearte.jfairy.producer.person.PersonProperties#maxAge(int)
   */
  int maxAge() default MAX_AGE;

  /**
   * @see io.codearte.jfairy.producer.person.PersonProperties#male()
   * @see io.codearte.jfairy.producer.person.PersonProperties#female()
   */
  Sex[] sex() default {Sex.MALE, Sex.FEMALE};

}
