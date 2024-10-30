package snw.srs.i18n.json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jetbrains.annotations.Nullable;
import snw.srs.i18n.data.MapBackedTranslationMapping;
import snw.srs.i18n.data.TranslationLoader;
import snw.srs.i18n.data.TranslationMapping;

import java.io.InputStream;
import java.io.Reader;
import java.util.Map;

import static snw.srs.i18n.util.JavaUtils.bufferedReader;

/**
 * {@link TranslationLoader} implementation which uses GSON on Bukkit platform
 * for loading translations. Use UTF-8 as the charset to read json files.
 */
public abstract class JsonTranslationLoader
        implements TranslationLoader<String, String, String> {
    private final Gson gson;

    protected JsonTranslationLoader(Gson gson) {
        this.gson = gson;
    }

    @Override
    public TranslationMapping<String, String> load(String language) throws IllegalArgumentException {
        InputStream source = getSource(language);
        if (source == null) {
            return null;
        }
        Reader reader = bufferedReader(source);
        Map<String, String> handle = gson.fromJson(reader, new TypeToken<>() {
        });
        return new MapBackedTranslationMapping(handle);
    }

    protected abstract @Nullable InputStream getSource(String language);
}
