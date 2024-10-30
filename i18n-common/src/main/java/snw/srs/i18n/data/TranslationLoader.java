package snw.srs.i18n.data;

import org.jetbrains.annotations.Nullable;
import snw.srs.i18n.data.storage.TranslationStorage;

/**
 * The translation loader. Used to load translation mapping. <br>
 * To save translation mapping in memory, consider using {@link TranslationStorage}.
 *
 * @param <L> The language representation type
 * @param <K> The mapping key type
 * @param <V> The mapping value type
 */
public interface TranslationLoader<L, K, V> {

    /**
     * Load the translation mapping.
     *
     * @param language The language
     * @return The loaded translation mapping, null if nothing was loaded
     * @throws IllegalArgumentException Thrown if the source does not contain a valid translation mapping
     */
    @Nullable
    TranslationMapping<K, V> load(L language) throws IllegalArgumentException;
}
