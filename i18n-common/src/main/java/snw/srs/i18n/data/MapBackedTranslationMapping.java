package snw.srs.i18n.data;

import org.jetbrains.annotations.Nullable;

import java.util.Map;

/**
 * {@link TranslationMapping} implementation which uses a map as lookup.
 */
public class MapBackedTranslationMapping implements TranslationMapping<String, String> {
    private final Map<String, String> handle;

    public MapBackedTranslationMapping(Map<String, String> handle) {
        this.handle = handle;
    }

    @Override
    public @Nullable String get(String key) {
        return handle.get(key);
    }

    @Override
    public String getOrDefault(String key, String defaultValue) {
        return handle.getOrDefault(key, defaultValue);
    }

    @Override
    public boolean containsKey(String key) {
        return handle.containsKey(key);
    }
}
