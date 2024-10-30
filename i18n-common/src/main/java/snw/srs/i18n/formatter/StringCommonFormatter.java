package snw.srs.i18n.formatter;

/**
 * The string translation template formatter which uses
 * the {@link String#format(String, Object...)} method for formatting.
 */
public enum StringCommonFormatter implements MessageFormatter<Object, String, String> {
    INSTANCE;

    @Override
    public String format(Object audience, String input, Object... args) {
        return input.formatted(args);
    }
}
