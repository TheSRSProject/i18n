package snw.srs.i18n.adventure.bukkit;

import net.kyori.adventure.audience.Audience;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.Contract;

public interface Player2AudienceMapper {
    Audience toAudience(Player who);

    @Contract("_ -> new")
    static Player2AudienceMapper create(Plugin plugin) {
        return new AdaptiveAudienceMapper(plugin);
    }
}
