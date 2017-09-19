package com.github.rweisleder.jfairy;

import static com.github.rweisleder.jfairy.StringWith.StringType.LATIN_SENTENCE;
import static com.github.rweisleder.jfairy.StringWith.StringType.LATIN_WORD;
import static com.github.rweisleder.jfairy.StringWith.StringType.LOREM_IPSUM;
import static com.github.rweisleder.jfairy.StringWith.StringType.PARAGRAPH;
import static com.github.rweisleder.jfairy.StringWith.StringType.RANDOM;
import static com.github.rweisleder.jfairy.StringWith.StringType.SENTENCE;
import static com.github.rweisleder.jfairy.StringWith.StringType.TEXT;
import static com.github.rweisleder.jfairy.StringWith.StringType.WORD;
import static org.junit.platform.commons.support.AnnotationSupport.findAnnotation;

import com.github.rweisleder.jfairy.StringWith.StringType;
import io.codearte.jfairy.producer.text.TextProducer;
import java.lang.reflect.AnnotatedElement;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Roland Weisleder
 */
class StringProvider extends ObjectProvider<String> {

  private static final Map<StringType, TypedStringProvider> providers = new HashMap<>();

  static {
    providers.put(RANDOM, new RandomStringProvider());
    providers.put(LOREM_IPSUM, new LoremIpsumProvider());
    providers.put(TEXT, new TextProvider());
    providers.put(WORD, new WordProvider());
    providers.put(LATIN_WORD, new LatinWordProvider());
    providers.put(SENTENCE, new SentenceProvider());
    providers.put(LATIN_SENTENCE, new LatinSentenceProvider());
    providers.put(PARAGRAPH, new ParagraphProvider());
  }

  @Override
  String createFor(AnnotatedElement annotatedElement) {
    StringWith config = findAnnotation(annotatedElement, StringWith.class).orElse(null);
    int maxLength = maxLength(config);
    StringType type = type(config);

    TextProducer producer = fairy(annotatedElement).textProducer().limitedTo(maxLength);
    return providers.get(type).produce(producer, maxLength);
  }

  private int maxLength(StringWith config) {
    return config != null ? config.maxLength() : StringWith.DEFAULT_MAX_LENGTH;
  }

  private StringType type(StringWith config) {
    return config != null ? config.type() : StringWith.DEFAULT_TYPE;
  }

  private interface TypedStringProvider {

    String produce(TextProducer producer, int maxLength);
  }

  private static class RandomStringProvider implements TypedStringProvider {

    @Override
    public String produce(TextProducer producer, int maxLength) {
      return producer.randomString(maxLength);
    }
  }

  private static class LoremIpsumProvider implements TypedStringProvider {

    @Override
    public String produce(TextProducer producer, int maxLength) {
      return producer.limitedTo(maxLength).loremIpsum();
    }
  }

  private static class TextProvider implements TypedStringProvider {

    @Override
    public String produce(TextProducer producer, int maxLength) {
      return producer.limitedTo(maxLength).text();
    }
  }

  private static class WordProvider implements TypedStringProvider {

    @Override
    public String produce(TextProducer producer, int maxLength) {
      return producer.limitedTo(maxLength).word(1);
    }
  }

  private static class LatinWordProvider implements TypedStringProvider {

    @Override
    public String produce(TextProducer producer, int maxLength) {
      return producer.limitedTo(maxLength).latinWord(1);
    }
  }

  private static class SentenceProvider implements TypedStringProvider {

    @Override
    public String produce(TextProducer producer, int maxLength) {
      return producer.limitedTo(maxLength).sentence();
    }
  }

  private static class LatinSentenceProvider implements TypedStringProvider {

    @Override
    public String produce(TextProducer producer, int maxLength) {
      return producer.limitedTo(maxLength).latinSentence();
    }
  }

  private static class ParagraphProvider implements TypedStringProvider {

    @Override
    public String produce(TextProducer producer, int maxLength) {
      return producer.limitedTo(maxLength).paragraph();
    }
  }
}
