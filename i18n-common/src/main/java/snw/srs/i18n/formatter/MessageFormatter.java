package snw.srs.i18n.formatter;

/**
 * The message formatter.
 *
 * @param <A> Audience as the context
 * @param <I> The message template key
 * @param <O> The user-friendly message, should be ready to be sent
 */
public interface MessageFormatter<A, I, O> {
    /**
     * Format the message.
     *
     * @param audience The audience
     * @param input    The template
     * @param args     The arguments
     * @return The formatted message
     */
    O format(A audience, I input, Object... args);
}
