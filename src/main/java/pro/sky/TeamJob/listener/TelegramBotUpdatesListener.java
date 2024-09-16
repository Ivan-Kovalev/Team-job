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
            SendMessage message = null;
            if(!Validator.isValidUUID(messageText)) {
                message = new SendMessage(update.message().chat().id(), "Ошибка! Невалидный ID");
                telegramBot.execute(message);
                return;
            }
            List<Recommendation> recommendations = recommendationService.getRecommendationProduct(messageText);
            if (recommendations.isEmpty()) {
                message = new SendMessage(update.message().chat().id(), "Нет рекомендаций для текущего пользователя");
            } else {
                message = new SendMessage(update.message().chat().id(), recommendations.toString());
            }
            telegramBot.execute(message);
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}