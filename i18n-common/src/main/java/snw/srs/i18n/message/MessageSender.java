package snw.srs.i18n.message;

/**
 * Represents a message sender, used to process the message being
 *  sent to audience before actual sending.
 *
 * @param <A> Audience type
 * @param <C> Message type
 */
public interface MessageSender<A, C> {

    /**
     * Process the given message and send processed message to audience.
     * @param audience The audience
     * @param value The message being sent
     */
    void send(A audience, C value);
}
