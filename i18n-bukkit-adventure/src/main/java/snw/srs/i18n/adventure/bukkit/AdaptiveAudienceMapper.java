package snw.srs.i18n.adventure.bukkit;

import com.google.common.base.Suppliers;
import net.kyori.adventure.audience.Audience;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.function.Supplier;

public final class AdaptiveAudienceMapper implements Player2AudienceMapper {
    private final Supplier<Player2AudienceMapper> legacyMapper;

    public AdaptiveAudienceMapper(Plugin plugin) {
        this.legacyMapper = Suppliers.memoize(() -> new LegacyAudienceMapper(plugin));
    }

    @Override
    public Audience toAudience(Player who) {
        if (who instanceof Audience asNative) {
            return asNative;
        } else {
            return legacyMapper.get().toAudience(who);
        }
    }
}
