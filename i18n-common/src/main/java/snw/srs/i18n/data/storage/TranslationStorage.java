package snw.srs.i18n.data.storage;

import org.jetbrains.annotations.Nullable;
import snw.srs.i18n.data.TranslationLoader;

/**
 * The storage of translation mappings.
 *
 * @param <L> The language used to lookup
 * @param <K> The translation key
 * @param <O> The message template
 */
public interface TranslationStorage<L, K, O> {

    /**
     * Get the template from this storage,
     * or attempt to load it using an internal {@link TranslationLoader}. <br>
     * The loaded content will be stored in the storage, so we can use them
     * for multiple times. But fallback values will never be stored.
     *
     * @param language The language
     * @param key      The translation key
     * @return The template or {@code null} if lookup fails
     * and {@code fallback} is {@code null}
     */
    @Nullable
    O getOrLoad(L language, K key);
}
