package pro.sky.TeamJob.listener.impl;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;
import pro.sky.TeamJob.listener.Command;

import java.util.List;

/**
 * Класс компонент, обеспечивающий работу команды Start
 * @author Daniil Topchiy & Ivan Kovalev
 * @version 1.0
 */
@Component
public class StartCommand implements Command {

    /** Коллекция команд */
    private final List<Command> commands;

    /**
     * Конструктор
     * @param commands коллекция команд
     */
    public StartCommand(List<Command> commands) {
        this.commands = commands;
    }

    /**
     * Метод передачи команды Start
     * @return возвращает команду Start
     */
    @Override
    public String getCommand() {
        return "/start";
    }

    /**
     * Метод передачи команды приветвенного сообщения
     * @return строку с командой получения приветственного сообщения
     */
    @Override
    public String getDescription() {
        return "/Получить приветственное сообщение еще раз";
    }

    /**
     * Метод обработки привественного сообщения и предачи информации о функционале пользователю
     * @param telegramBot объект телеграм бота
     * @param update полученное обновление
     * @param commandAndArguments массив команд и аргументов
     * @return сообщение пользователю с информацией
     */
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
