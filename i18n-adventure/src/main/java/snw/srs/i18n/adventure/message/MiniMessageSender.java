package snw.srs.i18n.adventure.message;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import snw.srs.i18n.message.MessageSender;

import static net.kyori.adventure.text.minimessage.MiniMessage.miniMessage;

public enum MiniMessageSender implements MessageSender<Audience> {
    INSTANCE;

    @Override
    public void processAndSend(Audience audience, String value) {
        Component component = miniMessage().deserialize(value);
        audience.sendMessage(component);
    }
}
