package snw.srs.i18n.bukkit.message;

import org.bukkit.entity.Player;
import snw.srs.i18n.message.MessageSender;

public enum LegacyMessageSender implements MessageSender<Player, String> {
    INSTANCE;

    @Override
    public void send(Player audience, String value) {
        audience.sendMessage(value);
    }
}
