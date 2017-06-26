package io.github.rweisleder.jfairy;

import java.lang.reflect.Field;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;

/**
 * {@link FairyExtension} provides fake data for fields and method parameters
 * annotated with {@link Random @Random}.
 *
 * <p>
 * <pre>
 * &#064;Test
 * &#064;ExtendWith(FairyExtension.class)
 * void withRandomPerson(@Random Person person) {
 * }
 * </pre>
 * </p>
 *
 * @author Roland Weisleder
 */
public class FairyExtension implements ParameterResolver, TestInstancePostProcessor {

  private FairyExtension() {
    // instantiated by JUnit
  }

  public boolean supports(ParameterContext parameterContext, ExtensionContext extensionContext)
      throws ParameterResolutionException {
    return parameterContext.getParameter().isAnnotationPresent(Random.class);
  }

  public Object resolve(ParameterContext parameterContext, ExtensionContext extensionContext)
      throws ParameterResolutionException {
    Random random = parameterContext.getParameter().getAnnotation(Random.class);
    Class<?> targetClass = parameterContext.getParameter().getType();

    return RandomDataGenerator.generate(random, targetClass);
  }

  @Override
  public void postProcessTestInstance(Object o, ExtensionContext extensionContext)
      throws Exception {
    for (Field field : o.getClass().getDeclaredFields()) {
      Random random = field.getAnnotation(Random.class);
      if (random != null) {
        Class<?> targetClass = field.getType();
        Object randomObject = RandomDataGenerator.generate(random, targetClass);

        field.setAccessible(true);
        field.set(o, randomObject);
      }
    }
  }

}
