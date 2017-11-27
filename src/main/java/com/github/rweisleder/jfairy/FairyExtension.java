package com.github.rweisleder.jfairy;

import static org.junit.platform.commons.support.AnnotationSupport.isAnnotated;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.Parameter;
import java.util.LinkedList;
import java.util.List;
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
 * <p>
 *   Supported object types:
 *   <ul>
 *     <li>{@link Boolean} and boolean</li>
 *     <li>{@link Integer} and int (see also {@link IntegerWith})</li>
 *     <li>{@link String} (see also {@link StringWith})</li>
 *     <li>Enum Types</li>
 *     <li>{@link io.codearte.jfairy.producer.person.Person Person} (see also {@link PersonWith})</li>
 *   </ul>
 *
 * @author Roland Weisleder
 */
public class FairyExtension implements ParameterResolver, TestInstancePostProcessor {

  private static final List<ObjectProvider> providers = new LinkedList<>();

  static {
    providers.add(new BooleanProvider());
    providers.add(new IntegerProvider());
    providers.add(new StringProvider());
    providers.add(new EnumProvider());
    providers.add(new PersonProvider());
  }

  private FairyExtension() {
    // instantiated by JUnit
  }

  @Override
  public boolean supportsParameter(ParameterContext parameterContext,
      ExtensionContext extensionContext) {
    Parameter parameter = parameterContext.getParameter();
    return hasRandomAnnotation(parameter);
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
      if (hasRandomAnnotation(field)) {
        Object randomObject = resolve(field, field.getType());

        field.setAccessible(true);
        field.set(testInstance, randomObject);
      }
    }
  }

  private boolean hasRandomAnnotation(AnnotatedElement annotatedElement) {
    return isAnnotated(annotatedElement, Random.class);
  }

  private Object resolve(AnnotatedElement annotatedElement, Class<?> targetType) {
    for (ObjectProvider provider : providers) {
      if (provider.supports(targetType)) {
        return provider.createFor(annotatedElement, targetType);
      }
    }

    throw new IllegalArgumentException("no provider found for type " + targetType);
  }

}
