package pro.sky.TeamJob.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    private TelegramBot telegramBot;
    private final List<Command> commands;

    public TelegramBotUpdatesListener(TelegramBot telegramBot, List<Command> commands) {
        this.telegramBot = telegramBot;
        this.commands = commands;
    }

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            String messageText = update.message().text();
            String[] commandAndArguments = messageText.split(" ");
            logger.info("Processing update: {}", update);
            for (Command command: commands) {
                if (command.getCommand().equalsIgnoreCase(commandAndArguments[0])) {
                    SendMessage message = command.executeCommand(telegramBot, update, commandAndArguments);
                    telegramBot.execute(message);
                }
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}