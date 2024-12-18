package snw.srs.i18n.adventure.bukkit;

import net.kyori.adventure.audience.Audience;
import org.bukkit.entity.Player;

public enum NativeAudienceMapper implements Player2AudienceMapper {
    INSTANCE;

    public static final boolean AVAILABLE;
    static {
        AVAILABLE = Audience.class.isAssignableFrom(Player.class);
    }

    @Override
    public Audience toAudience(Player who) {
        if (!AVAILABLE) {
            throw new IllegalStateException("Player is not inherited from Audience");
        }
        return ((Audience) who);
    }
}
