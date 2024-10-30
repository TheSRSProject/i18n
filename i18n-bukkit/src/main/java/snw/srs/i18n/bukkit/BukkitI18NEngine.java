package snw.srs.i18n.bukkit;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;
import snw.srs.i18n.I18NEngine;
import snw.srs.i18n.data.storage.TranslationStorage;
import snw.srs.i18n.formatter.MessageFormatter;

import java.util.concurrent.CompletableFuture;

import static java.util.Objects.requireNonNullElse;

/**
 * The I18N engine implemented on top of standard Bukkit API.
 */
public final class BukkitI18NEngine implements I18NEngine<Player, String, String, String> {
    // Some formatter does not care about the audience object, so we use "? super Player"
    private final MessageFormatter<? super Player, String, String> messageFormatter;
    private final TranslationStorage<String, String, String> translationStorage;

    public BukkitI18NEngine(
            MessageFormatter<? super Player, String, String> messageFormatter,
            TranslationStorage<String, String, String> translationStorage
    ) {
        this.messageFormatter = messageFormatter;
        this.translationStorage = translationStorage;
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
    public CompletableFuture<Void> sendMessage(Player audience, String key, Object... args) {
        return CompletableFuture.supplyAsync(() -> getTemplateOrAsIs(audience, key))
                .thenAcceptAsync(template -> {
                    String formatted = messageFormatter.format(audience, template, args);
                    // it is safe to send message asynchronously as it is just a packet operation
                    audience.sendMessage(formatted);
                });
    }
}
