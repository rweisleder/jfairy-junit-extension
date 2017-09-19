package com.github.rweisleder.jfairy;

import static com.github.rweisleder.jfairy.StringWith.StringType.LATIN_SENTENCE;
import static com.github.rweisleder.jfairy.StringWith.StringType.LATIN_WORD;
import static com.github.rweisleder.jfairy.StringWith.StringType.LOREM_IPSUM;
import static com.github.rweisleder.jfairy.StringWith.StringType.PARAGRAPH;
import static com.github.rweisleder.jfairy.StringWith.StringType.SENTENCE;
import static com.github.rweisleder.jfairy.StringWith.StringType.TEXT;
import static com.github.rweisleder.jfairy.StringWith.StringType.WORD;
import static org.junit.platform.commons.support.AnnotationSupport.findAnnotation;

import com.github.rweisleder.jfairy.StringWith.StringType;
import io.codearte.jfairy.producer.text.TextProducer;
import java.lang.reflect.AnnotatedElement;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author Roland Weisleder
 */
class StringProvider extends ObjectProvider {

  private static final Map<StringType, Function<TextProducer, String>> typeMappers = new HashMap<>();

  static {
    typeMappers.put(LOREM_IPSUM, p -> p.loremIpsum());
    typeMappers.put(TEXT, p -> p.text());
    typeMappers.put(WORD, p -> p.word(1));
    typeMappers.put(LATIN_WORD, p -> p.latinWord(1));
    typeMappers.put(SENTENCE, p -> p.sentence());
    typeMappers.put(LATIN_SENTENCE, p -> p.latinSentence());
    typeMappers.put(PARAGRAPH, p -> p.paragraph());
  }

  @Override
  Object createFor(AnnotatedElement annotatedElement) {
    StringWith config = findAnnotation(annotatedElement, StringWith.class).orElse(null);
    int maxLength = maxLength(config);
    StringType type = type(config);

    TextProducer producer = fairy(annotatedElement).textProducer().limitedTo(maxLength);
    if (typeMappers.containsKey(type)) {
      return typeMappers.get(type).apply(producer);
    }

    return producer.randomString(maxLength);
  }

  private int maxLength(StringWith config) {
    return config != null ? config.maxLength() : StringWith.DEFAULT_MAX_LENGTH;
  }

  private StringType type(StringWith config) {
    return config != null ? config.type() : StringWith.DEFAULT_TYPE;
  }

}
