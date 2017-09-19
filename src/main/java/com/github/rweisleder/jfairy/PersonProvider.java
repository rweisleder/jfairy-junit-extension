package com.github.rweisleder.jfairy;

import static io.codearte.jfairy.producer.person.Person.Sex.FEMALE;
import static io.codearte.jfairy.producer.person.Person.Sex.MALE;
import static org.junit.platform.commons.support.AnnotationSupport.findAnnotation;

import io.codearte.jfairy.producer.person.Person;
import io.codearte.jfairy.producer.person.Person.Sex;
import io.codearte.jfairy.producer.person.PersonProperties;
import io.codearte.jfairy.producer.person.PersonProperties.PersonProperty;
import java.lang.reflect.AnnotatedElement;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * @author Roland Weisleder
 */
class PersonProvider extends ObjectProvider<Person> {

  @Override
  Person createFor(AnnotatedElement annotatedElement) {
    PersonProperty[] properties = createProperties(annotatedElement);

    return fairy(annotatedElement).person(properties);
  }

  private PersonProperty[] createProperties(AnnotatedElement annotatedElement) {
    return findAnnotation(annotatedElement, PersonWith.class).map(config -> {
      List<PersonProperty> properties = new LinkedList<>();

      createSexProperty(config).ifPresent(properties::add);
      createAgeProperty(config).ifPresent(properties::add);

      return properties.toArray(new PersonProperty[properties.size()]);
    }).orElseGet(() -> new PersonProperty[0]);
  }

  private Optional<PersonProperty> createSexProperty(PersonWith config) {
    Sex[] sex = config.sex();
    if (sex.length == 1) {
      if (sex[0] == MALE) {
        return Optional.of(PersonProperties.male());
      }
      if (sex[0] == FEMALE) {
        return Optional.of(PersonProperties.female());
      }
    }

    return Optional.empty();
  }

  private Optional<PersonProperty> createAgeProperty(PersonWith config) {
    return Optional.of(PersonProperties.ageBetween(config.minAge(), config.maxAge()));
  }

}
