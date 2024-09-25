package pro.sky.TeamJob.listener.impl;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pro.sky.TeamJob.dto.Recommendation;
import pro.sky.TeamJob.listener.Command;
import pro.sky.TeamJob.service.RecommendationService;

import java.util.List;
import java.util.UUID;

/**
 * Класс описывающий логику работы телеграм бота, обработку его запросов и передачи рекомендаций
 * @author Daniil Topchiy & Ivan Kovalev
 * @version 1.0
 */
@Component
@RequiredArgsConstructor
public class RecommendCommand implements Command {

    /** Сервис рекомендаций */
    private final RecommendationService recommendationService;

    /**
     * Метод передающий возможные команды для бота
     * @return возможные команды для бота
     */
    @Override
    public String getCommand() {
        return "/recommend";
    }

    /**
     * Метод передающий информацию о корректном запросе для телеграм бота
     * @return строка с информацией как корректно вводить запрос
     */
    @Override
    public String getDescription() {
        return "Данная команда позволяет вам получить список рекоммендаций продукций для пользователя с переданным username." +
                "Пример использования команды: /recommend Ivanov.Ivan";
    }

    /**
     * Метод передачи рекомендации по логину пользователя
     * @param telegramBot объект телеграм бота через который будет происходить отправка сообщения
     * @param update обновления (информация с событиями произошедшими относительно пользователя)
     * @param commandAndArguments команда и ее аргументы
     * @return сообщение для пользователя с рекомендациями
     */
    @Override
    public SendMessage executeCommand(TelegramBot telegramBot, Update update, String[] commandAndArguments) {
        SendMessage message;
        if (!isCommandValid(commandAndArguments)) {
            message = new SendMessage(update.message().chat().id(), "Ошибка! В запросе должен быть только 1 аргумент (Username пользователя)");
            telegramBot.execute(message);
            return message;
        }
        else {
            UUID userId = UUID.fromString(recommendationService.getUserIdByUsername(commandAndArguments[1]));
            String userFirstnameAndLastname = recommendationService.getFirstnameAndLastnameByUsername(commandAndArguments[1]);
            List<Recommendation> recommendations = recommendationService.getRecommendationProduct(userId);
            if(recommendations.isEmpty()) {
                message = new SendMessage(update.message().chat().id(), "Здравствуйте " + userFirstnameAndLastname + ".\n" +
                        "Для вас нет рекомендаций.");
            } else {
                StringBuilder userRecommendations = new StringBuilder();
                for (Recommendation recommendation: recommendations) {
                    userRecommendations.append("Название продукта: ")
                            .append(recommendation.getName())
                            .append("\n")
                            .append("Описание продукта: ")
                            .append(recommendation.getText())
                            .append("\n\n");
                }
                message = new SendMessage(update.message().chat().id(), "Здравствуйте " + userFirstnameAndLastname + ". " +
                        "Новые продукты для вас:\n\n" +
                        userRecommendations);
            }
        }
        return message;
    }

    /**
     * Метод валидации команды полученной из сообщения пользователя
     * @param commandAndArguments команда и ее аргументы
     * @return true, если длинна массива команд и аргументов равна двум
     */
    private boolean isCommandValid(String[] commandAndArguments) {
        return commandAndArguments.length == 2;
    }

}


