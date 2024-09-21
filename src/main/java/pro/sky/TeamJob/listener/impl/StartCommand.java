package pro.sky.TeamJob.listener.impl;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pro.sky.TeamJob.listener.Command;

import java.util.List;

@Component
public class StartCommand implements Command {

    private final List<Command> commands;

    public StartCommand(List<Command> commands) {
        this.commands = commands;
    }

    @Override
    public String getCommand() {
        return "/start";
    }

    @Override
    public String getDescription() {
        return "/Получить приветственное сообщение еще раз";
    }

    @Override
    public SendMessage executeCommand(TelegramBot telegramBot, Update update, String[] commandAndArguments) {
        StringBuilder message = new StringBuilder();
        message.append("Добро пожаловать в TJ банк.\n")
                .append("Я ваш персональный ассистент по подбору и созданию рекомендаций.\n\n")
                .append("Для взаимодействия со мной вы можете использовать следующие команды:\n");
        for (Command command: commands) {
            message.append(command.getCommand() + ": ")
                    .append(command.getDescription())
                    .append("\n");
        }
        SendMessage sendMessage = new SendMessage(update.message().chat().id(), message.toString());
        return sendMessage;
    }
}
