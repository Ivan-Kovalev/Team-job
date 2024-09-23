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

/**
 * Сервис по обработке входящих обновлений из бота
 * @author Daniil Topchiy & Ivan Kovalev
 * @version 1.0
 */
@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    /** Объект телеграм-бота */
    private TelegramBot telegramBot;

    /** Список команд */
    private final List<Command> commands;

    /**
     * Конструктор
     * @param telegramBot объект телеграм-бота
     * @param commands список команд
     */
    public TelegramBotUpdatesListener(TelegramBot telegramBot, List<Command> commands) {
        this.telegramBot = telegramBot;
        this.commands = commands;
    }

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    /** Метод работы с обновлениями от бота */
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