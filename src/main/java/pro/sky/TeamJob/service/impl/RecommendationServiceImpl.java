package pro.sky.TeamJob.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sky.TeamJob.model.Recommendation;
import pro.sky.TeamJob.model.User;
import pro.sky.TeamJob.repository.ProductRepository;
import pro.sky.TeamJob.repository.UserRepository;
import pro.sky.TeamJob.service.RecommendationService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendationServiceImpl implements RecommendationService {

    private final UserRepository userRepository;

    @Override
    public List<Recommendation> getRecommendationProduct(String userId) {
        List<Recommendation> recommendations = new ArrayList<>();
        List<User> appropriateUsersForInvest500Product = userRepository.findUsersThatAppropriateToProductInvest();
        List<User> appropriateUsersForTopSaving = userRepository.findUsersThatAppropriateToProductSaving();
        List<User> appropriateUsersForSimpleCredit = userRepository.findUsersThatAppropriateToProductSimpleCredit();
        for (User user : appropriateUsersForInvest500Product) {
            if(user.getId().equals(userId)) {
                recommendations.add(new Recommendation("Invest 500",
                        "4913217f-122b-4fg4-adbf-b6ae90c5ad23",
                        "Откройте свой путь к успеху с индивидуальным " +
                                "инвестиционным счетом (ИИС) от нашего банка! Воспользуйтесь " +
                                "налоговыми льготами и начните инвестировать с умом. " +
                                "Пополните счет до конца года и получите выгоду в виде вычета " +
                                "на взнос в следующем налоговом периоде.\n" +
                                "\n" +
                                "Не упустите возможность разнообразить свой портфель, снизить риски и следить за актуальными рыночными тенденциями. Откройте ИИС сегодня и станьте ближе к финансовой независимости!"));
            break;
            }
        }
        for (User user : appropriateUsersForTopSaving) {
            if(user.getId().equals(userId)) {
                recommendations.add(new Recommendation("Top Saving",
                        "4913217f-122b-ag21-baf1-bf1e22c5af23",
                        "Откройте свою собственную «Копилку» с нашим банком!\n" +
                                "\n" +
                                "«Копилка» — это уникальный банковский инструмент, который поможет вам легко и удобно накапливать деньги на важные цели. Больше никаких забытых чеков и потерянных квитанций — всё под контролем!\n" +
                                "\n" +
                                "Преимущества «Копилки»:\n" +
                                "\n" +
                                "Накопление средств на конкретные цели. Установите лимит и срок накопления, и банк будет автоматически переводить определенную сумму на ваш счет.\n" +
                                "\n" +
                                "Прозрачность и контроль. Отслеживайте свои доходы и расходы, контролируйте процесс накопления и корректируйте стратегию при необходимости.\n" +
                                "\n" +
                                "Безопасность и надежность. Ваши средства находятся под защитой банка, а доступ к ним возможен только через мобильное приложение или интернет-банкинг.\n" +
                                "\n" +
                                "Начните использовать «Копилку» уже сегодня и станьте ближе к своим финансовым целям!"));
                break;
            }
        }
        for (User user : appropriateUsersForSimpleCredit) {
            if(user.getId().equals(userId)) {
                recommendations.add(new Recommendation("Простой кредит",
                        "2933212a-212b-ab21-ccf1-bf2222c5af23",
                        "Откройте мир выгодных кредитов с нами! Ищете способ быстро и без лишних хлопот получить нужную сумму? Тогда наш выгодный кредит — именно то, что вам нужно! Мы предлагаем низкие процентные ставки, гибкие условия и индивидуальный подход к каждому клиенту.\n" +
                                "\n" +
                                "Почему выбирают нас:\n" +
                                "\n" +
                                "Быстрое рассмотрение заявки. Мы ценим ваше время, поэтому процесс рассмотрения заявки занимает всего несколько часов.\n" +
                                "\n" +
                                "Удобное оформление. Подать заявку на кредит можно онлайн на нашем сайте или в мобильном приложении.\n" +
                                "\n" +
                                "Широкий выбор кредитных продуктов. Мы предлагаем кредиты на различные цели: покупку недвижимости, автомобиля, образование, лечение и многое другое.\n" +
                                "\n" +
                                "Не упустите возможность воспользоваться выгодными условиями кредитования от нашей компании!"));
                break;
            }
        }
        return recommendations;
    }
}