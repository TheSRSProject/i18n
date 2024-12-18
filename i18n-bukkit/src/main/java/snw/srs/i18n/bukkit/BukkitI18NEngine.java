package snw.srs.i18n.bukkit;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;
import snw.srs.i18n.I18NEngine;
import snw.srs.i18n.bukkit.message.AsIsMessageSender;
import snw.srs.i18n.data.storage.TranslationStorage;
import snw.srs.i18n.formatter.MessageFormatter;
import snw.srs.i18n.message.MessageSender;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;

import static java.util.Objects.requireNonNullElse;

/**
 * The I18N engine implemented on top of standard Bukkit API.
 */
public final class BukkitI18NEngine implements I18NEngine<Player, String, String, String, Object> {
    // Some formatter does not care about the audience object, so we use "? super Player"
    private final MessageFormatter<? super Player, String, String, Object> messageFormatter;
    private final TranslationStorage<String, String, String> translationStorage;
    private final MessageSender<Player> messageSender;

    @Deprecated
    public BukkitI18NEngine(
            MessageFormatter<? super Player, String, String, Object> messageFormatter,
            TranslationStorage<String, String, String> translationStorage
    ) {
        this(messageFormatter, translationStorage, AsIsMessageSender.INSTANCE);
    }

    public BukkitI18NEngine(
            MessageFormatter<? super Player, String, String, Object> messageFormatter,
            TranslationStorage<String, String, String> translationStorage,
            MessageSender<Player> messageSender
    ) {
        this.messageFormatter = messageFormatter;
        this.translationStorage = translationStorage;
        this.messageSender = messageSender;
    }

    @Override
    public String getLanguage(Player audience) {
        return audience.getLocale();
    }

    @Override
    public String getTemplateOrAsIs(Player audience, String key) {
        return requireNonNullElse(getTemplate(audience, key), key);
    }

    @Override
    public @Nullable String getTemplate(Player audience, String key) {
        String language = getLanguage(audience);
        return translationStorage.getOrLoad(language, key);
    }

    @Override
    public String formatMessage(Player audience, String key, Collection<Object> args) {
        String template = getTemplateOrAsIs(audience, key);
        return messageFormatter.format(audience, template, args);
    }

    @Override
    public CompletableFuture<Void> sendMessage(Player audience, String key, Collection<Object> args) {
        // it is safe to send message asynchronously as it is just a packet operation
        return CompletableFuture.supplyAsync(() -> formatMessage(audience, key, args))
                .thenAcceptAsync(formatted -> getMessageSender().processAndSend(audience, formatted));
    }

    @Override
    public MessageSender<Player> getMessageSender() {
        return messageSender;
    }
}
