package snw.srs.i18n.data;

import org.jetbrains.annotations.Nullable;

/**
 * The translation mapping. <br>
 * This interface does <b>NOT</b> expose the modification methods to users,
 * as it should be regarded as an immutable mapping.
 *
 * @param <K> The key type
 * @param <V> The value type
 */
public interface TranslationMapping<K, V> {

    /**
     * Get the value from the mapping.
     *
     * @param key The key
     * @return The value, or {@code null} if nothing was found
     */
    @Nullable
    V get(K key);

    /**
     * Get the value from mapping or return {@code fallback} if nothing was found.
     *
     * @param key          The key
     * @param defaultValue The default value, {@code null} is permitted but not encouraged
     * @return The value
     */
    V getOrDefault(K key, V defaultValue);

    /**
     * Check if this mapping contains the key-value pair bound to the provided key.
     *
     * @param key The key
     * @return True if key-value pair exists in this mapping
     */
    boolean containsKey(K key);
}
