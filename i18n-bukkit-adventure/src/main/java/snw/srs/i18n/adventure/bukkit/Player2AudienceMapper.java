package snw.srs.i18n.adventure.bukkit;

import net.kyori.adventure.audience.Audience;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.Contract;

public interface Player2AudienceMapper {
    Audience toAudience(Player who);

    @Contract("_ -> new")
    static Player2AudienceMapper create(Plugin plugin) {
        if (Audience.class.isAssignableFrom(Player.class)) { // then we're on a platform with native Adventure support
            return NativeAudienceMapper.INSTANCE;
        } else {
            return new LegacyAudienceMapper(plugin);
        }
    }
}
