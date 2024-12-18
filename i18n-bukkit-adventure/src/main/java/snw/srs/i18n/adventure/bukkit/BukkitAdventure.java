package snw.srs.i18n.adventure.bukkit;

import net.kyori.adventure.audience.Audience;
import org.bukkit.entity.Player;
import snw.srs.i18n.message.MessageSender;

public final class BukkitAdventure {
    public static MessageSender<Player> asBukkitMessageSender(MessageSender<Audience> sender, Player2AudienceMapper audienceMapper) {
        return (who, value) -> {
            Audience asAudience = audienceMapper.toAudience(who);
            sender.processAndSend(asAudience, value);
        };
    }

    private BukkitAdventure() {
    }
}
