package snw.srs.i18n.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

/**
 * Utility methods used to deal with JDK.
 */
public final class JavaUtils {
    private JavaUtils() {
    }

    public static Reader bufferedReader(final InputStream stream) {
        return new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));
    }
}
