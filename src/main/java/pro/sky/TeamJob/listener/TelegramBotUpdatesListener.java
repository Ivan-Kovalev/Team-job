package pro.sky.TeamJob.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.User;
import com.pengrad.telegrambot.request.SendMessage;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.TeamJob.dto.Recommendation;
import pro.sky.TeamJob.service.RecommendationService;
import pro.sky.TeamJob.utils.Validator;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TelegramBotUpdatesListener implements UpdatesListener {

    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    @Autowired
    private TelegramBot telegramBot;
    private final RecommendationService recommendationService;

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            String messageText = update.message().text();
            logger.info("Processing update: {}", update);
            SendMessage message;
            if(messageText.equals("/start")) {
                message = new SendMessage(update.message().chat().id(), "Добро пожаловать в TJ банк. " +
                        "Я ваш персональный ассистент по подбору и созданию рекомендаций.\n\n" +
                        "Для взаимодействия со мной вы можете использовать следующие команды:\n" +
                        "/recommend [USERNAME] - получить все рекомендации, доступные текущему пользователю с данным ID.\n" +
                        "/start - прослушать данное сообщение еще раз. Если очень хочется.");
                telegramBot.execute(message);
            } else if(messageText.startsWith("/recommend")) {
                String[] commandAndArguments = messageText.split(" ");
                if (commandAndArguments.length != 2) {
                    message = new SendMessage(update.message().chat().id(), "Ошибка! В запросе должен быть только 1 аргумент (Username пользователя)");
                } else {
                    String userId = recommendationService.getUserIdByUsername(commandAndArguments[1]);
                    String userFirstnameAndLastname = recommendationService.getFirstnameAndLastnameByUsername(commandAndArguments[1]);
                    List<Recommendation> recommendations = recommendationService.getRecommendationProduct(userId);
                    if(recommendations.isEmpty()) {
                        message = new SendMessage(update.message().chat().id(), "Здравствуйте " + userFirstnameAndLastname + ".\n" +
                                "Для вас нет рекомендаций.");
                    } else {
                        StringBuilder userRecommendations = new StringBuilder();
                        for (Recommendation recommendation: recommendations) {
                            userRecommendations.append("Название продукта:\n ")
                                    .append(recommendation.getName())
                                    .append(".\n")
                                    .append(recommendation.getText())
                                    .append("\n\n");
                        }
                        message = new SendMessage(update.message().chat().id(), "Здравствуйте " + userFirstnameAndLastname + ".\n" +
                                "Новые продукты для вас:\n\n" +
                                userRecommendations);
                    }
                }
                telegramBot.execute(message);
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}