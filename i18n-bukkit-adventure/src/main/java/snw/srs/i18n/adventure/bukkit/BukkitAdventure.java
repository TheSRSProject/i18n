package snw.srs.i18n.adventure.bukkit;

import net.kyori.adventure.audience.Audience;
import org.bukkit.entity.Player;
import snw.srs.i18n.message.MessageSender;

public final class BukkitAdventure {
    public static <C> MessageSender<Player, C> asBukkitPlayerMessageSender(MessageSender<Audience, C> sender, Player2AudienceMapper audienceMapper) {
        return (who, value) -> {
            Audience asAudience = audienceMapper.toAudience(who);
            sender.send(asAudience, value);
        };
    }

    private BukkitAdventure() {
    }
}
