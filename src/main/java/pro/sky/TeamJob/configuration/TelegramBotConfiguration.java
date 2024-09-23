package pro.sky.TeamJob.configuration;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.DeleteMyCommands;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Класс конфигурации телеграм бота
 * @author Daniil Topchiy & Ivan Kovalev
 * @version 1.0
 */
@Configuration
public class TelegramBotConfiguration {

    /** Поле хранящее Api ключ телеграм бота */
    @Value("${telegram.bot.token}")
    private String token;

    /** Конструктор конфигуратора телеграм бота */
    @Bean
    public TelegramBot telegramBot() {
        TelegramBot bot = new TelegramBot(token);
        bot.execute(new DeleteMyCommands());
        return bot;
    }

}

