package snw.srs.i18n.data.storage;

import org.jetbrains.annotations.Nullable;
import snw.srs.i18n.data.TranslationLoader;
import snw.srs.i18n.data.TranslationMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * Simple {@link TranslationStorage} implementation. Use strings for templates.
 */
public class SimpleTranslationStorage implements TranslationStorage<String, String, String> {
    private final Map<String, TranslationMapping<String, String>> cache;
    private final TranslationLoader<String, String, String> loader;

    public SimpleTranslationStorage(TranslationLoader<String, String, String> loader) {
        this.cache = new HashMap<>();
        this.loader = loader;
    }

    @Override
    public @Nullable String getOrLoad(String language, String key) {
        TranslationMapping<String, String> mapping = getMapping(language);
        if (mapping != null) {
            return mapping.get(key);
        } else {
            return null;
        }
    }

    private TranslationMapping<String, String> getMapping(String language) {
        return cache.computeIfAbsent(language, loader::load);
    }
}
