package com.github.rweisleder.jfairy;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * @author Roland Weisleder
 */
@ExtendWith(FairyExtension.class)
class BooleanParameterTests {

  @Test
  void primitiveBoolean(@Random boolean b) {
    // nothing to do
  }

  @Test
  void booleanObject(@Random Boolean b) {
    assertNotNull(b);
  }
}
