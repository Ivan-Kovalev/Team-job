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

@Component
@RequiredArgsConstructor
public class RecommendCommand implements Command {

    private final RecommendationService recommendationService;

    @Override
    public String getCommand() {
        return "/recommend";
    }

    @Override
    public String getDescription() {
        return "Данная команда позволяет вам получить список рекоммендаций продукций для пользователя с переданным username." +
                "Пример использования команды: /recommend Ivanov.Ivan";
    }

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

    private boolean isCommandValid(String[] commandAndArguments) {
        return commandAndArguments.length == 2;
    }


}


