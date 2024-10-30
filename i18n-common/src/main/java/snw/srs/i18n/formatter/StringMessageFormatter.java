package snw.srs.i18n.formatter;

import java.text.MessageFormat;

/**
 * The string translation template formatter which uses
 * the {@link MessageFormat#format(String, Object...)} for formatting.
 */
public enum StringMessageFormatter implements MessageFormatter<Object, String, String> {
    INSTANCE;

    @Override
    public String format(Object audience, String input, Object... args) {
        return MessageFormat.format(input, args);
    }
}
