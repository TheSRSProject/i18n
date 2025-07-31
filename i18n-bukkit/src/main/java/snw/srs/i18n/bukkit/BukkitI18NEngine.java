package snw.srs.i18n.bukkit;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;
import snw.srs.i18n.I18NEngine;
import snw.srs.i18n.data.storage.TranslationStorage;
import snw.srs.i18n.formatter.MessageFormatter;
import snw.srs.i18n.message.MessageSender;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;

import static java.util.Objects.requireNonNullElse;

/**
 * The I18N engine implemented on top of standard Bukkit API.
 */
public final class BukkitI18NEngine<C> implements I18NEngine<Player, String, String, String, C, Object> {
    // Some formatter does not care about the audience object, so we use "? super Player"
    private final MessageFormatter<? super Player, String, C, Object> messageFormatter;
    private final TranslationStorage<String, String, String> translationStorage;
    private final MessageSender<Player, C> messageSender;

    public BukkitI18NEngine(
            MessageFormatter<? super Player, String, C, Object> messageFormatter,
            TranslationStorage<String, String, String> translationStorage,
            MessageSender<Player, C> messageSender
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
    public String getTemplateByLanguageOrAsIs(String language, String key) {
        return requireNonNullElse(getTemplateByLanguage(language, key), key);
    }

    @Override
    public @Nullable String getTemplate(Player audience, String key) {
        String language = getLanguage(audience);
        return getTemplateByLanguage(language, key);
    }

    @Override
    public @Nullable String getTemplateByLanguage(String language, String key) {
        return translationStorage.getOrLoad(language, key);
    }

    @Override
    public C formatMessage(Player audience, String key, Collection<Object> args) {
        String template = getTemplateOrAsIs(audience, key);
        return messageFormatter.format(audience, template, args);
    }

    @Override
    public CompletableFuture<Void> sendMessage(Player audience, String key, Collection<Object> args) {
        // it is safe to send message asynchronously as it is just a packet operation
        return CompletableFuture.supplyAsync(() -> formatMessage(audience, key, args))
                .thenAcceptAsync(formatted -> getMessageSender().send(audience, formatted));
    }

    @Override
    public MessageSender<Player, C> getMessageSender() {
        return messageSender;
    }

    @Override
    public TranslationStorage<String, String, String> getTranslationStorage() {
        return translationStorage;
    }

    @Override
    public MessageFormatter<? super Player, String, C, Object> getMessageFormatter() {
        return messageFormatter;
    }
}
