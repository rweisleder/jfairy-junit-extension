package com.github.rweisleder.jfairy;

import static org.junit.platform.commons.support.AnnotationSupport.findAnnotation;

import io.codearte.jfairy.producer.person.Person;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;

/**
 * {@link FairyExtension} provides fake data for fields and method parameters annotated with {@link
 * Random @Random}.
 *
 * <p>
 * <pre>
 * &#064;Test
 * &#064;ExtendWith(FairyExtension.class)
 * void withRandomPerson(&#064;Random Person person) {
 * }
 * </pre>
 *
 * @author Roland Weisleder
 */
public class FairyExtension implements ParameterResolver, TestInstancePostProcessor {

  private static final Map<Class<?>, ObjectProvider> providers = new HashMap<>();

  static {
    BooleanProvider booleanProvider = new BooleanProvider();
    providers.put(boolean.class, booleanProvider);
    providers.put(Boolean.class, booleanProvider);

    IntegerProvider integerProvider = new IntegerProvider();
    providers.put(int.class, integerProvider);
    providers.put(Integer.class, integerProvider);

    providers.put(String.class, new StringProvider());

    providers.put(Person.class, new PersonProvider());
  }

  private FairyExtension() {
    // instantiated by JUnit
  }

  @Override
  public boolean supportsParameter(ParameterContext parameterContext,
      ExtensionContext extensionContext) {
    Parameter parameter = parameterContext.getParameter();
    return supports(parameter, parameter.getType());
  }

  @Override
  public Object resolveParameter(ParameterContext parameterContext,
      ExtensionContext extensionContext) {
    Parameter parameter = parameterContext.getParameter();
    return resolve(parameter, parameter.getType());
  }

  @Override
  public void postProcessTestInstance(Object testInstance, ExtensionContext context)
      throws ReflectiveOperationException {
    for (Field field : testInstance.getClass().getDeclaredFields()) {
      if (supports(field, field.getType())) {
        Object randomObject = resolve(field, field.getType());

        field.setAccessible(true);
        field.set(testInstance, randomObject);
      }
    }
  }

  private boolean supports(AnnotatedElement annotatedElement, Class<?> targetType) {
    boolean hasRandomAnnotation = findAnnotation(annotatedElement, Random.class).isPresent();
    boolean hasProvider = providers.containsKey(targetType);
    return hasRandomAnnotation && hasProvider;
  }

  private Object resolve(AnnotatedElement annotatedElement, Class<?> targetType) {
    return providers.get(targetType).createFor(annotatedElement);
  }

}
