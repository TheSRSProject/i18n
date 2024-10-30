package snw.srs.i18n.bukkit.formatter;

import org.bukkit.ChatColor;
import snw.srs.i18n.formatter.MessageFormatter;

import java.util.Collection;

/**
 * {@link MessageFormatter} implementation which translates alternate color codes
 * inside the output before returning result.
 */
public class ColoredMessageFormatter implements MessageFormatter<Object, String, String, Object> {
    private final MessageFormatter<Object, String, String, Object> delegate;
    private final char alterColorCode;

    public ColoredMessageFormatter(MessageFormatter<Object, String, String, Object> delegate) {
        this(delegate, '&');
    }

    public ColoredMessageFormatter(MessageFormatter<Object, String, String, Object> delegate, char alterColorCode) {
        this.delegate = delegate;
        this.alterColorCode = alterColorCode;
    }

    @Override
    public String format(Object audience, String input, Collection<?> args) {
        String formatted = delegate.format(audience, input, args);
        return ChatColor.translateAlternateColorCodes(alterColorCode, formatted);
    }
}
