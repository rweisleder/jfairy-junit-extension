package com.github.rweisleder.jfairy;

import static com.github.rweisleder.jfairy.StringWith.StringType.LATIN_SENTENCE;
import static com.github.rweisleder.jfairy.StringWith.StringType.LATIN_WORD;
import static com.github.rweisleder.jfairy.StringWith.StringType.LOREM_IPSUM;
import static com.github.rweisleder.jfairy.StringWith.StringType.PARAGRAPH;
import static com.github.rweisleder.jfairy.StringWith.StringType.SENTENCE;
import static com.github.rweisleder.jfairy.StringWith.StringType.TEXT;
import static com.github.rweisleder.jfairy.StringWith.StringType.WORD;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * @author Roland Weisleder
 */
@ExtendWith(FairyExtension.class)
class StringParameterTests {

  @Test
  void randomString(@Random String s) {
    assertThat(s.length()).isBetween(1, 4000);
  }

  @Test
  void maxLength(@Random @StringWith(maxLength = 10) String s) {
    assertThat(s.length()).isBetween(1, 10);
  }

  @Test
  void loremIpsum(@Random @StringWith(type = LOREM_IPSUM) String s) {
    assertThat(s).startsWith("Lorem ipsum");
  }

  @Test
  void germanText(@Random(locale = "de") @StringWith(type = TEXT) String s) {
    assertThat(s).startsWith("Ihr naht euch wieder, schwankende Gestalten");
  }

  @Test
  void word(@Random(locale = "de", seed = 1) @StringWith(type = WORD) String s) {
    assertThat(s).isEqualTo("stunden");
  }

  @Test
  void latinWord(@Random(locale = "de", seed = 1) @StringWith(type = LATIN_WORD) String s) {
    assertThat(s).isEqualTo("etiam");
  }

  @Test
  void sentence(@Random(locale = "de", seed = 1) @StringWith(type = SENTENCE) String s) {
    assertThat(s).startsWith("Stunden lebt");
  }

  @Test
  void latinSentence(@Random(locale = "de", seed = 1) @StringWith(type = LATIN_SENTENCE) String s) {
    assertThat(s).startsWith("Etiam dolor vel nisi");
  }

  @Test
  void paragraph(@Random(locale = "de", seed = 1) @StringWith(type = PARAGRAPH) String s) {
    assertThat(s).startsWith("Ein Versuch Guten");
  }
}
