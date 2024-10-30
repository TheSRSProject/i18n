package snw.srs.i18n.formatter;

import java.util.Collection;

/**
 * The string translation template formatter which uses
 * the {@link String#format(String, Object...)} method for formatting.
 */
public enum StringCommonFormatter implements MessageFormatter<Object, String, String, Object> {
    INSTANCE;

    @Override
    public String format(Object audience, String input, Collection<?> args) {
        return input.formatted(args.toArray());
    }
}
