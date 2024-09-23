package pro.sky.TeamJob.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

/**
 * Класс интерфейс, описывающий методы работы с командами телеграм бота
 * @author Daniil Topchiy & Ivan Kovalev
 * @version 1.0
 */
public interface Command {

    String getCommand();
    String getDescription();
    SendMessage executeCommand(TelegramBot telegramBot, Update update, String[] commandAndArguments);

}
