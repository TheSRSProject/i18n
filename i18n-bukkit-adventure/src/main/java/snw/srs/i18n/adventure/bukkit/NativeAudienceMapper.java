package snw.srs.i18n.adventure.bukkit;

import net.kyori.adventure.audience.Audience;
import org.bukkit.entity.Player;

public enum NativeAudienceMapper implements Player2AudienceMapper {
    INSTANCE;

    @Override
    public Audience toAudience(Player who) {
        return ((Audience) who);
    }
}
