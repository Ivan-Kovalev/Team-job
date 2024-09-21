package pro.sky.TeamJob.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

public interface Command {

    String getCommand();
    String getDescription();
    SendMessage executeCommand(TelegramBot telegramBot, Update update, String[] commandAndArguments);

}
