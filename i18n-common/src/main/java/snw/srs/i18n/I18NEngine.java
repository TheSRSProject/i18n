package snw.srs.i18n;

import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

/**
 * The I18N engine.
 *
 * @param <A> Audience type
 * @param <L> Language representation type
 * @param <K> Translation key type
 * @param <O> Output message template type
 */
public interface I18NEngine<A, L, K, O> {
    /**
     * Get the language that the audience uses.
     *
     * @param audience The audience
     * @return The language representation
     */
    L getLanguage(A audience);

    /**
     * Attempts to look up the template matching the provided key
     * and the language which the audience uses. <br>
     * The key will be wrapped as the output if lookup fails.
     *
     * @param audience The audience
     * @param key      The translation key
     * @return The translated content or the key as fallback
     */
    O getTemplateOrAsIs(A audience, K key);

    /**
     * Attempts to get the translated content matching the provided key
     * and the language which the audience uses. <br>
     * {@code null} will be returned if lookup fails.
     *
     * @param audience The audience
     * @param key      The translation key
     * @return The translated content or null if nothing could be returned
     */
    @Nullable
    O getTemplate(A audience, K key);

    /**
     * Send the translated message to the audience. <br>
     * The engine will format the out-coming message using the provided arguments
     * before sending it. <br>
     * There is no guarantee that the message will be sent synchronously.
     * Async template loading maybe performed by the underlying data storage.
     *
     * @param audience The audience
     * @param key      The language key
     * @param args     The arguments used to format the message
     */
    CompletableFuture<Void> sendMessage(A audience, K key, Object... args);
}