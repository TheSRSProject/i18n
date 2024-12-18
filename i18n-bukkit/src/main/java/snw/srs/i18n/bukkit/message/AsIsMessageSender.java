package snw.srs.i18n.bukkit.message;

import org.bukkit.entity.Player;
import snw.srs.i18n.message.MessageSender;

public enum AsIsMessageSender implements MessageSender<Player> {
    INSTANCE;

    @Override
    public void processAndSend(Player audience, String value) {
        audience.sendMessage(value);
    }
}
