package com.github.rweisleder.jfairy;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.Month;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * @author Roland Weisleder
 */
@ExtendWith(FairyExtension.class)
class EnumParameterTests {

  @Test
  void randomEnumValue(@Random Month month) {
    assertNotNull(month);
  }
}

