package com.github.rweisleder.jfairy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * @author Roland Weisleder
 */
@ExtendWith(FairyExtension.class)
class IntegerParameterTests {

  @Test
  void primitiveInt(@Random int i) {
    assertThat(i).isGreaterThan(0);
  }

  @Test
  void integerObject(@Random Integer i) {
    assertNotNull(i);
  }

  @Test
  void minValue(@Random @IntegerWith(min = 100) int i) {
    assertThat(i).isGreaterThanOrEqualTo(100);
  }

  @Test
  void maxValue(@Random @IntegerWith(max = 100) int i) {
    assertThat(i).isLessThanOrEqualTo(100);
  }

  @Test
  void range(@Random @IntegerWith(min = 50, max = 100) int i) {
    assertThat(i).isBetween(50, 100);
  }
}
