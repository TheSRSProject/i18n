package snw.srs.i18n.bukkit.data;

import com.google.gson.Gson;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.Nullable;
import snw.srs.i18n.json.JsonTranslationLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * {@link JsonTranslationLoader} implementation which uses the Bukkit {@link Plugin}
 * instance to lookup JSON translation files.
 */
public final class PluginJsonTranslationLoader extends JsonTranslationLoader {
    private final Plugin plugin;
    private final String languageFolder;

    public PluginJsonTranslationLoader(Plugin plugin) {
        this(plugin, "lang");
    }

    public PluginJsonTranslationLoader(Plugin plugin, String languageFolder) {
        this(plugin, new Gson(), languageFolder);
    }

    public PluginJsonTranslationLoader(Plugin plugin, Gson gson, String languageFolder) {
        super(gson);
        this.plugin = plugin;
        this.languageFolder = languageFolder;
    }

    @Override
    protected @Nullable InputStream getSource(String language) {
        String requestedResource = languageFolder + "/" + language + ".json";
        File dataFolder = plugin.getDataFolder();
        File file = new File(dataFolder, requestedResource);
        if (!file.canRead()) {
            return plugin.getResource(requestedResource);
        } else {
            try {
                return new FileInputStream(file);
            } catch (FileNotFoundException e) {
                throw new AssertionError(e);
            }
        }
    }
}
