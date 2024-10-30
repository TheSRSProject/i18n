package snw.srs.i18n.formatter;

import java.util.Collection;
import java.util.List;

/**
 * The message formatter.
 *
 * @param <A> Audience as the context
 * @param <I> The message template key
 * @param <O> The user-friendly message, should be ready to be sent
 * @param <R> The argument type
 */
public interface MessageFormatter<A, I, O, R> {
    /**
     * Format the message.
     *
     * @param audience The audience
     * @param input    The template
     * @param args     The arguments, recommend to use {@link List#of} methods to wrap them
     * @return The formatted message
     */
    O format(A audience, I input, Collection<? extends R> args);
}
