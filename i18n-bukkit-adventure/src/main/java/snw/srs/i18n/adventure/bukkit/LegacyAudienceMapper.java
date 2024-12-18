package snw.srs.i18n.adventure.bukkit;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public final class LegacyAudienceMapper implements Player2AudienceMapper, AutoCloseable {
    private final BukkitAudiences platform;

    public LegacyAudienceMapper(Plugin plugin) {
        this.platform = BukkitAudiences.create(plugin);
    }

    // Use this if you already have your own instance of BukkitAudiences
    public LegacyAudienceMapper(BukkitAudiences platform) {
        this.platform = platform;
    }

    @Override
    public Audience toAudience(Player who) {
        return platform.player(who);
    }

    @Override
    public void close() {
        platform.close();
    }
}
